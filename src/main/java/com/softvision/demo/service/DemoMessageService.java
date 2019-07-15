package com.softvision.demo.service;

import com.softvision.demo.events.source.CustomSourceBean;
import com.softvision.demo.events.source.CustomSourceBean2;
import com.softvision.demo.events.source.SimpleSourceBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DemoMessageService {

    @Autowired
    SimpleSourceBean simpleSourceBean;

    @Autowired
    CustomSourceBean customSourceBean;

    @Autowired
    CustomSourceBean2 customSourceBean2;

    public void sendMessage(String  message){

        log.info("DemoMessageService:service call");
        simpleSourceBean.publishMessage(message);
    }

    public void sendMessageCustomOutput(String  message){

        log.info("DemoMessageService:sendMessageCustomOutput:service call");
        customSourceBean.publishMessage(message);
    }

    public void sendMessageCustomOutput2(String  message){

        log.info("DemoMessageService:sendMessageCustomOutput2:service call");
        customSourceBean2.publishMessage(message);
    }
}
