package com.linkedin.events.promotion;

import com.linkedin.events.customer.CustomerRegisteredEvent;
import com.linkedin.events.order.OrderCompletedEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PromotionListener {

    private final PromotionService promotionService;
    
    @EventListener(condition = "#event.customer.newsletter") // in Customer DAO, has Boolean newsletter field
    public void onRegistrationEvent(CustomerRegisteredEvent event) {
    
    promotionService.applyPromotion(event.getCustomer());
    
    }

    @EventListener
    public void onOrderCompleted(OrderCompletedEvent event) {
        promotionService.calculateRewardPoints(event.getOrder());
    }
}
