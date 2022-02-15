package com.linkedin.events.analytics;

import com.linkedin.events.customer.CustomerRegisteredEvent;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AnalyticsCustomerRegisteredListener {

    private final AnalyticsService analyticsService; //constructor injected using @RequiredArgsConstructor
    
    @Async
    @EventListener
    public void onRegisterEvent(CustomerRegisteredEvent event) {
         analyticsService.registerNewCustomer(event.getCustomer());
    }
}
