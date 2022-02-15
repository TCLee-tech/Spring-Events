package com.linkedin.events.email;

import com.linkedin.events.customer.CustomerRegisteredEvent;
import com.linkedin.events.customer.CustomerRemovedEvent;
import com.linkedin.events.order.OrderCompletedEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor //use  Project Lombok, annotate, create required constructors
public class EmailListeners {

    //inject dependency
    private final EmailService emailService;

    @EventListener
    public void onRegisterEvent(CustomerRegisteredEvent event) {
        emailService.sendRegisterEmail(event.getCustomer());
        //Customer is POJO; CustomerRegisteredEvent is the "event".
        //sendRegisterEmail is method of EmailService class. Argument is the customer POJO
    }

    @EventListener
    public void onRemovedEvent(CustomerRemovedEvent event) {
        emailService.sendCustomerRemovedEmail(event.getCustomer());
    }

    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) //added so that email sent after all other events commit
    public void onOrderCompletedEvent(OrderCompletedEvent event){
        emailService.sendOrderEmail(event.getOrder());
    }
}

