package com.linkedin.events.email;

import com.linkedin.events.customer.Customer;
import com.linkedin.events.customer.CustomerRepository;
import com.linkedin.events.order.Order;
import com.linkedin.events.order.OrderRepository;
import com.linkedin.events.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static com.linkedin.events.order.Order.OrderStatus.SAVED;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmailOrderServiceTest
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @SpyBean
    private EmailService emailService;
    @Test
    void forPlaceOrder_whenRolledBacked_dontSendEmail()
    {

        //given
        Customer customer = givenCustomer(90); // In Customer.java, see that rewardPoints restricted to 2 digits. If we append 10 more points per order, will need 3 digits and commit shall fail.
        Order order = givenOrder(customer);

        //when
        try
        {
            orderService.placeOrder(order);
        }
        catch (Exception e)
        {
            log.error("Exception while placing an order", e);
        }

        //then
        then(emailService).shouldHaveNoInteractions();

    }

    @Test
    void forPlaceOrder_whenCommitSuccessful_sendAnEmail()
    {

        //given
        Customer customer = givenCustomer(50);
        Order order = givenOrder(customer);

        //when
        orderService.placeOrder(order);

        //then
        then(emailService).should(times(1)).sendOrderEmail(order);

    }

    private Order givenOrder(Customer customer)
    {
        Order order = new Order(SAVED);
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    private Customer givenCustomer(int rewardPoints)
    {
        Customer customer = new Customer("john@email.com");
        customer.setRewardPoints(BigDecimal.valueOf(rewardPoints));
        return customerRepository.save(customer);
    }



}