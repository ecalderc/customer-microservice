package com.nttdata.bootcamp.customer.controller;

import com.nttdata.bootcamp.customer.entity.Customer;
import com.nttdata.bootcamp.customer.entity.dto.PersonalCustomerDto;
import com.nttdata.bootcamp.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Test
    public void testFindAllCustomers() {
        // given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());
        when(customerService.findAllCustomers()).thenReturn(Flux.fromIterable(customers));

        // when
        Flux<Customer> result = customerController.findAllCustomers();

        // then
        verify(customerService).findAllCustomers();
        assertEquals(customers, result.collectList().block());
    }

    @Test
    public void testFindCustomerByDni() {
        // given
        String dni = "12345678";
        Customer customer = new Customer();
        when(customerService.findCustomerByDni(dni)).thenReturn(Mono.just(customer));

        // when
        Mono<Customer> result = customerController.findCustomerByDni(dni);

        // then
        verify(customerService).findCustomerByDni(dni);
        assertEquals(customer, result.block());
    }

    @Test
    public void testSavePersonalCustomer() {
        // given
        PersonalCustomerDto personalCustomerDto = new PersonalCustomerDto();
        Customer customer = new Customer();
        when(customerService.savePersonalCustomer(personalCustomerDto)).thenReturn(Mono.just(customer));

        // when
        Mono<Customer> result = customerController.savePersonalCustomer(personalCustomerDto);

        // then
        verify(customerService).savePersonalCustomer(personalCustomerDto);
        assertEquals(customer, result.block());
    }

    @Test
    public void testUpdateCustomerStatus() {
        // create a test customer object
        Customer customer = new Customer();
        customer.setDni("12345678");
        customer.setStatus("Active");

        // configure the mock service behavior
        when(customerService.updateCustomerStatus(customer))
                .thenReturn(Mono.just(customer));

        // call the method to test
        Mono<Customer> result = customerController.updateCustomerStatus(customer);

        // verify the result
        assertEquals(customer, result.block());
    }

    @Test
    public void testUpdateCustomerAddress() {
        // setup
        Customer customer = new Customer();
        customer.setDni("12345678");
        customer.setAddress("742 Evergreen Terrace");

        // when
        when(customerService.updateCustomerAddress(customer))
                .thenReturn(Mono.just(customer));

        // test
        Mono<Customer> result = customerController.updateCustomerAddress(customer);

        // verify
        verify(customerService).updateCustomerAddress(customer);
        assertEquals("742 Evergreen Terrace", result.block().getAddress());
    }

    /*
    @Test
    public void testDeleteCustomer() {
        // setup
        Customer customer = new Customer();

        when(customerService.deleteCustomer("12345678")).thenReturn(Mono.just(customer));

        // test
        Mono<Customer> result = customerController.deleteCustomer("12345678");

        // verify
        verify(customerService).deleteCustomer("12345678");
        assertEquals(Mono.just(customer), result);
    }

    @Test
    public void findCustomerByDni_serviceDown_fallbackCalled() {
        // Given
        String dni = "12345678";
        Mono<String> expectedResult = Mono.just("Customer Microservice is not responding");

        when(customerService.findCustomerByDni(dni)).thenReturn(Mono.error(new RuntimeException()));

        // When
        Mono<Customer> result = customerController.findCustomerByDni(dni);

        // Then
        assertEquals(expectedResult.block(), result.block());
        verify(customerService).findCustomerByDni(dni);
    }
     */

}