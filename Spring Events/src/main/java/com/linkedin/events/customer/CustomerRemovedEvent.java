package com.linkedin.events.customer;

import lombok.Data;

//annotation that generates all the boilerplate codes for POJO and beans. Includes getter and setter, constructor & toString.
@Data
public class CustomerRemovedEvent {
    
    private final Customer customer;
}
