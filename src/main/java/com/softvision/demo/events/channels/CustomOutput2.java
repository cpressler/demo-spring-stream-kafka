package com.softvision.demo.events.channels;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


public interface CustomOutput2 {
    String OUTPUT_NAME = "customOutput2";


    @Output(CustomOutput2.OUTPUT_NAME)
    MessageChannel output();
}