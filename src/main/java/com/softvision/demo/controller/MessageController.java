package com.softvision.demo.controller;

import com.softvision.demo.service.DemoMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Test Controller", tags = "demo", consumes = "application/text", produces = "application/text")

@RestController
@RequestMapping(value="/messages")
public class MessageController {

    @Autowired
    private DemoMessageService demoMessageService;

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @ApiOperation("Get Demo")
    @ApiResponses({
            @ApiResponse(code = 400, message = "There was an error")
    })
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String getMessage( ) {
        logger.debug("getMessage");

        String test = "test string";
        demoMessageService.sendMessage(test);
        return test;
    }

    @ApiOperation(value= "Send a message to topic",  consumes = "application/text", produces = "application/text")
    @ApiResponses({
            @ApiResponse(code = 400, message = "There was an errors")
    })
    @RequestMapping(value="/test",method = RequestMethod.PUT)
    public void sendMessage( @RequestBody String  testStr) {
        logger.debug("sendMessage {}" , testStr);
        demoMessageService.sendMessage(testStr);
    }
}
