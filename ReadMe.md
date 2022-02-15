# Spring-Events

| File name |Description |
| ---                                               | ---                                           |
| registerCustomer_forNewCustomer_sendsAnEmail Test | Test result for synchronous event processing. \Object -> event -> publish Event -> EventListener -> implementation |
| removeCustomer_forExistingCustomer Test | Same as above. Instead of register new Customer, this is to remove Customer. |
| AnalyticsService Sync EventListener 5s simulated delay in event processing | Test result with simulated 5s processing delay. No async annotation. Sync processing. |
| AnalyticsService Async EventListener | Test result for with/without @Async. Processing time reduced to 204ms. |
| Conditional Transactional EventListener Test | Test result for @EventListener(condition = “”). If newsletter==TRUE for customer, promotion event listener sends promotion email. |
| EmailOrderServiceTest send email or rollback transaction | Test result for @Transactional. All-or-none. Rollback with exception (3-digit rewardPoints), or successful completion (2-digit rewardPoints). |
| forPlaceOrder_whenRolledBacked_createTicket Test TransactionalEventListener for after_rollback | Test result for @TransactionalEventListener (phase = TransactionPhase.AFTER_ROLLBACK). Invoke TicketService only after rollback. |

## References 

Spring documentation 

1.15.2 Standard and Custom Events (standard Observer design pattern) 

https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#context-functionality-events 

Project Lombok 

https://projectlombok.org/ 

-@Data 

https://projectlombok.org/features/Data 

- @Log 

https://projectlombok.org/features/log 

- @NoArgsConstructor, @RequiredArgsConstructor 

https://projectlombok.org/features/constructor 

- @SneakyThrows 

https://projectlombok.org/features/SneakyThrows 

Configure Log4j for logging 

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto.logging.log4j 
