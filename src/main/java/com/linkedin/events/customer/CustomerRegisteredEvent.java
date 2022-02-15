package com.linkedin.events.customer;

//takes care of getter, setter and constructor
import lombok.Data;

@Data
public class CustomerRegisteredEvent {
    
    private final Customer customer;

}
