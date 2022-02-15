package com.linkedin.events.ticket;

import com.linkedin.events.order.OrderCompletedEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketListener {

    private final TicketService ticketService;
    
    //listen for roll-back of transaction. Roll-back, happens after unsuccessful commit, means order not completed.
    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onOrderRolledBack (OrderCompletedEvent event) {
        
        ticketService.createTicket(event.getOrder());
    
    }
}
