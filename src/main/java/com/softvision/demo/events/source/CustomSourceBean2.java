package com.softvision.demo.events.source;


import com.softvision.demo.events.channels.CustomOutput;
import com.softvision.demo.events.channels.CustomOutput2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
@EnableBinding(CustomOutput2.class)

public class CustomSourceBean2 {

    private static final Logger logger = LoggerFactory.getLogger(CustomSourceBean2.class);

    CustomOutput2 customOutput2;

    @Autowired
    public CustomSourceBean2(CustomOutput2 customOutput2){
        this.customOutput2 = customOutput2;
    }

    @Autowired
    private MessageChannel output;

    public void publishMessage(String message){
       logger.info("CustomSourceBean2:Sending JMS message {} ", message);

        customOutput2.output().send(MessageBuilder.withPayload(message).build());

    }
}
