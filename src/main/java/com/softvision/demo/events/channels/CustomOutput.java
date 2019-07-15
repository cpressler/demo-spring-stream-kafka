package com.softvision.demo.events.channels;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


/**
 * Custom Producer that maps to a binding name other than "output"
 *
 *       bindings:
 *         customOutput:
 *           destination: demo.custom.topic
 *           binder: kafka1
 */

public interface CustomOutput {
    String OUTPUT_NAME = "customOutput";


    @Output(CustomOutput.OUTPUT_NAME)
    MessageChannel output();
}