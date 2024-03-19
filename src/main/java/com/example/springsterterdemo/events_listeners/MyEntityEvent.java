package com.example.springsterterdemo.events_listeners;

import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@ToString(callSuper = true)
public class MyEntityEvent extends ApplicationEvent {
    private final AccessType accessType;

    public MyEntityEvent(AccessType accessType, Object entity){
        super(entity);
        this.accessType = accessType;
    }

    public AccessType getAccessType() {
        return accessType;
    }
}
