package com.example.springsterterdemo.events_listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyEventListener {


    // root.args[0](firstParam) == MyEntityEvent (root.args == our params in method)
    @EventListener(condition = "#root.args[0].accessType.name() == 'WRITE'")
    @Order(1)// this listener will be called first
    public void doSomeThing(MyEntityEvent event){
        log.info("MyEventListener ->  AccessType: WRITE, Some event occurred: {}", event);
    }


    @EventListener(condition = "#root.args[0].accessType.name() == 'READ'")
    @Order(1)// this listener will be called first
    public void doSomeThing_2(MyEntityEvent event){
        log.warn("MyEventListener -> AccessType: READ, Some event occurred: {}", event);
    }
}
