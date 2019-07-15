package com.softvision.demo.service;

//import com.softvision.demo.events.source.SimpleSourceBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DemoMessageService {

//    @Autowired
//    SimpleSourceBean simpleSourceBean;

    public void sendMessage(String  message){

        log.info("service call");
        //simpleSourceBean.publishMessage(message);
    }
}
