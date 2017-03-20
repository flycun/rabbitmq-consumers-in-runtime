package br.com.cedran.rabbit.config.rabbitmq;

import java.io.IOException;
import java.util.Optional;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jackson2JsonMessageConverterCustom extends Jackson2JsonMessageConverter {

    public Jackson2JsonMessageConverterCustom(Class clazz) {
        DefaultClassMapper defaultClassMapper = new DefaultClassMapper();
        defaultClassMapper.setDefaultType(clazz);
        this.setClassMapper(defaultClassMapper);
    }

    private ObjectMapper jsonObjectMapper = new ObjectMapper();

    public void setJsonObjectMapper(ObjectMapper jsonObjectMapper) {
        super.setJsonObjectMapper(jsonObjectMapper);
        this.jsonObjectMapper = jsonObjectMapper;
    }

    public Object fromMessage(Message message) throws MessageConversionException {
        Object content;
        MessageProperties properties = message.getMessageProperties();
        String encoding = Optional.ofNullable(properties).map(MessageProperties::getContentEncoding).orElse(this.getDefaultCharset());

        try {
            if (this.getClassMapper() == null) {
                JavaType e = this.getJavaTypeMapper().toJavaType(message.getMessageProperties());
                content = this.convertBytesToObject(message.getBody(), encoding, e);
            } else {
                Class e1 = this.getClassMapper().toClass(message.getMessageProperties());
                content = this.convertBytesToObject(message.getBody(), encoding, e1);
            }
        } catch (IOException var7) {
            throw new MessageConversionException("Failed to convert Message content", var7);
        }

        if (content == null) {
            content = message.getBody();
        }

        return content;
    }

    private Object convertBytesToObject(byte[] body, String encoding, JavaType targetJavaType) throws IOException {
        String contentAsString = new String(body, encoding);
        return this.jsonObjectMapper.readValue(contentAsString, targetJavaType);
    }

    private Object convertBytesToObject(byte[] body, String encoding, Class<?> targetClass) throws IOException {
        String contentAsString = new String(body, encoding);
        return this.jsonObjectMapper.readValue(contentAsString, this.jsonObjectMapper.constructType(targetClass));
    }
}
