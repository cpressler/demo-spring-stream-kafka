package com.softvision.demo.events.channels;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomInput2 {
    String INPUT_NAME = "customInput2";
    @Input(CustomInput2.INPUT_NAME)

    SubscribableChannel input();
}
