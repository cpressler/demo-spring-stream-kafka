package com.softvision.demo.events.source;


import com.softvision.demo.events.channels.CustomOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Custom Producer Bean that maps to a binding name other than "output" via the CustomOutput Interface
 *
 *       bindings:
 *         customOutput:
 *           destination: demo.custom.topic
 *           binder: kafka1
 */

@Component
@EnableBinding(CustomOutput.class)
public class CustomSourceBean {

    CustomOutput customOutput;

    private static final Logger logger = LoggerFactory.getLogger(CustomSourceBean.class);

    @Autowired
    public CustomSourceBean(CustomOutput customOutput){
        this.customOutput = customOutput;
    }

    @Autowired
    private MessageChannel output;

    public void publishMessage(String message){
        logger.info("CustomSourceBean:Sending JMS message {} ", message);
        customOutput.output().send(MessageBuilder.withPayload(message).build());
    }
}
