package com.nttdata.bootcamp.customer.service.Impl;

import com.nttdata.bootcamp.customer.entity.Customer;
import com.nttdata.bootcamp.customer.entity.dto.BusinessCustomerDto;
import com.nttdata.bootcamp.customer.entity.dto.PersonalCustomerDto;
import com.nttdata.bootcamp.customer.repository.CustomerRepository;
import com.nttdata.bootcamp.customer.service.CustomerService;
import com.nttdata.bootcamp.customer.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Flux<Customer> findAll() {
        log.info("Searching for all customers");
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> findByDni(String dni) {
        log.info("Searching for customer with DNI:" + dni);
        return customerRepository.findAll()
                .filter(x -> x.getDni().equals(dni))
                .next();
    }

    @Override
    public Mono<Customer> savePersCust(PersonalCustomerDto dataCustomer) {
        return findByDni(dataCustomer.getDni())
                .hasElement()
                .flatMap(dniExists -> {
                    if (dniExists) {
                        log.info("There is already a customer with that DNI");
                        return Mono.empty();
                    } else {
                        log.info("Saving for personal customer " + dataCustomer.getDni());

                        Customer p = new Customer();
                        p.setDni(dataCustomer.getDni());
                        p.setTypeCustomer(Constant.PERSONAL_CUSTOMER);
                        p.setFlagVip(false);
                        p.setFlagPyme(false);
                        p.setName(dataCustomer.getName());
                        p.setSurName(dataCustomer.getSurName());
                        p.setAddress(dataCustomer.getAddress());
                        p.setStatus(Constant.CUSTOMER_ACTIVE);
                        p.setCreationDate(new Date());
                        p.setModificationDate(new Date());
                        return customerRepository.save(p);
                    }
                });
    }

    public Mono<Customer> saveBusCust(BusinessCustomerDto dataCustomer) {
        return findByDni(dataCustomer.getDni())
                .hasElement()
                .flatMap(dniExists -> {
                    if (dniExists) {
                        log.info("There is already a customer with that DNI");
                        return Mono.empty();
                    } else {
                        log.info("Saving for business customer " + dataCustomer.getDni());

                        Customer b = new Customer();
                        b.setDni(dataCustomer.getDni());
                        b.setTypeCustomer(Constant.BUSINESS_CUSTOMER);
                        b.setFlagPyme(false);
                        b.setFlagVip(false);
                        b.setName(dataCustomer.getName());
                        b.setSurName(dataCustomer.getSurName());
                        b.setAddress(dataCustomer.getAddress());
                        b.setStatus(Constant.CUSTOMER_ACTIVE);
                        b.setCreationDate(new Date());
                        b.setModificationDate(new Date());
                        return customerRepository.save(b);
                    }
                });
    }

    /*@Override
    public Mono<Customer> updateAddress(Customer dataCustomer) {
        Mono<Customer> customerMono = findByDni(dataCustomer.getDni());
        try {
            Customer customer = customerMono.block();
            assert customer != null;
            customer.setAddress(dataCustomer.getAddress());
            customer.setModificationDate(dataCustomer.getModificationDate());
            return customerRepository.save(customer);
        }catch (Exception e){
            return Mono.<Customer>error(new Error("The customer with DNI " + dataCustomer.getDni() + " do not exists"));
        }
    }*/

    @Override
    public Mono<Customer> updateStatus(Customer dataCustomer) {
        Mono<Customer> customerMono = findByDni(dataCustomer.getDni());
        try {
            Customer customer = customerMono.block();
            assert customer != null;
            customer.setStatus(dataCustomer.getStatus());
            customer.setModificationDate(dataCustomer.getModificationDate());
            return customerRepository.save(customer);
        } catch (Exception e) {
            return Mono.<Customer>error(new Error("The customer with DNI " + dataCustomer.getDni() + " do not exists"));
        }
    }

    @Override
    public Mono<Customer> delete(String dni) {
        log.info("Deleting client by DNI: " + dni);
        return findByDni(dni)
                .flatMap(customer -> customerRepository.delete(customer).then(Mono.just(customer)));
    }

}
