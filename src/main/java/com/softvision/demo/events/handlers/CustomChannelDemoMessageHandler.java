package com.softvision.demo.events.handlers;


import com.softvision.demo.events.channels.CustomInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Custom Consumer Handler that maps to a binding name other than "input" via CustomInput Interface
 *       bindings:
 *         customInput:
 *           destination: demo.custom.topic
 *           content-type: application/text
 *           group: demoCustomConsumer
 *           binder: kafka1
 */

@EnableBinding(CustomInput.class)
public class CustomChannelDemoMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomChannelDemoMessageHandler.class);

    @StreamListener(CustomInput.INPUT_NAME)
    public void loggerSink(String  message) {
        logger.info("CustomChannelDemoMessageHandler:Received a message ->>>>  {} " ,  message);

    }

}


