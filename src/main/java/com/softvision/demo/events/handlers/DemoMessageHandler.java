package com.softvision.demo.events.handlers;


import com.softvision.demo.events.CustomChannels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


@EnableBinding(CustomChannels.class)
public class DemoMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(DemoMessageHandler.class);

    //@StreamListener("inboundData")
    @StreamListener(target = Sink.INPUT)
    public void loggerSink(String  message) {
        logger.info("Received a message ->>>>  {} " ,  message);

    }

}


