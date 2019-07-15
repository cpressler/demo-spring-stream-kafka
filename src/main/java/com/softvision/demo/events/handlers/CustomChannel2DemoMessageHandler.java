package com.softvision.demo.events.handlers;


import com.softvision.demo.events.channels.CustomInput2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


@EnableBinding(CustomInput2.class)
public class CustomChannel2DemoMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomChannel2DemoMessageHandler.class);

    @StreamListener(CustomInput2.INPUT_NAME)
    public void loggerSink(String  message) {
        logger.info("CustomChannel2DemoMessageHandler:Received a message ->>>>  {} " ,  message);

    }

}


