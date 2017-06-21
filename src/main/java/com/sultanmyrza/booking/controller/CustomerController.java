package com.sultanmyrza.booking.controller;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.model.Customer;
import com.sultanmyrza.booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "/customer/{customerId}/bookings", method = RequestMethod.GET)
    public List<Booking> getCustomerBookings(@PathVariable Integer customerId) {

        return customerService.getCustomerBookings(customerId);
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public HashMap<String, String> addCustomer(@RequestBody Customer newCustomer) {

        HashMap<String, String> response;
        response = customerService.addCustomer(newCustomer);

        return response;
    }


    @RequestMapping(value = "/customer/update/{customerId}", method = RequestMethod.PUT)
    public HashMap<String, String> updateCustomer(@PathVariable Integer customerId,
                                                 @RequestBody Customer updatedCustomer) {

        HashMap<String, String> response;
        response = customerService.updateCustomer(customerId, updatedCustomer);

        return response;
    }


    @RequestMapping(value = "/customer/delete/{customerId}", method = RequestMethod.DELETE)
    public HashMap<String, String> deleteCustomer(@PathVariable Integer customerId) {

        HashMap<String, String> response;
        response = customerService.deleteCustomer(customerId);

        return response;
    }
}
