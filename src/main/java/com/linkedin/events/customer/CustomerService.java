package com.linkedin.events.customer;

//import com.linkedin.events.email.EmailService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerService
{
    
    private final CustomerRepository customerRepository;

    //private final EmailService emailService;

    private final ApplicationEventPublisher publisher;

    //method
    public void register(Customer customer)
    {
        //functionalities, e.g. save customer info to repo, send email to customer informing registered
        //class.method(parameter)
        customerRepository.save(customer);
        //emailService.sendRegisterEmail(customer); ==> code refactoring. Remove direct method call/dependency, transition to event implementation
        publisher.publishEvent(new CustomerRegisteredEvent(customer));
    }

    public void remove(Customer customer)
    {
        customerRepository.delete(customer);
        publisher.publishEvent(new CustomerRemovedEvent(customer));
    }
}
