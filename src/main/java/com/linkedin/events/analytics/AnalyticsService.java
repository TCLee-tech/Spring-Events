package com.linkedin.events.analytics;

import static java.lang.Thread.sleep;

import com.linkedin.events.customer.Customer;

import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AnalyticsService
{

    @SneakyThrows
    public void registerNewCustomer(Customer customer)
    {
        log.info("calling analytics service for customer {}");
        sleep(5_000); //5 seconds, to simulate long-running process
    }
}
