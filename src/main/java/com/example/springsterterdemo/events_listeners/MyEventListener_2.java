package com.example.springsterterdemo.events_listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyEventListener_2 {
    // root.args[0](firstParam) == MyEntityEvent (root.args == our params in method)
    @EventListener(condition = "#root.args[0].accessType.name() == 'WRITE'")
    @Order(2)// this listener will be called second
    public void doSomeThing(MyEntityEvent event){
        log.info("MyEventListener_2 ->  AccessType: WRITE, Some event occurred: {}", event);
    }
    @EventListener(condition = "#root.args[0].accessType.name() == 'READ'")
    @Order(2)// this listener will be called second
    public void doSomeThing_2(MyEntityEvent event){
        log.info("MyEventListener_2 -> AccessType: READ, Some event occurred: {}", event);
    }}
