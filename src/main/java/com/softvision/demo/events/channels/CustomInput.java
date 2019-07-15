package com.softvision.demo.events.channels;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Custom Consumer that maps to a binding name other than "input"
 *       bindings:
 *         customInput:
 *           destination: demo.custom.topic
 *           content-type: application/text
 *           group: demoCustomConsumer
 *           binder: kafka1
 */

public interface CustomInput {
    String INPUT_NAME = "customInput";
    @Input(CustomInput.INPUT_NAME)

    SubscribableChannel input();
}
