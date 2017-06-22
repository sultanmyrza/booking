package com.sultanmyrza.booking.service;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.model.Customer;
import com.sultanmyrza.booking.repository.BookingRepository;
import com.sultanmyrza.booking.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * Created by sultanmyrza on 17. 6. 22.
 */

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private CustomerService customerService = new CustomerService();

    @Test
    public void deleteCustomer_CustomerBookingsCascadeDelete() throws Exception {

        // TODO-ASK: why asList not working
        // List<Booking> customerBookings = new Arrays.asList(new Booking(), new Booking(), new Booking());
        List<Booking> customerBookings = new ArrayList<>();
        customerBookings.add(new Booking());
        customerBookings.add(new Booking());
        customerBookings.add(new Booking());
        Customer mockCustomer = new Customer();
        mockCustomer.setBookings(customerBookings);

        Mockito.when(customerRepository.findOne(anyInt())).thenReturn(mockCustomer);

        // TODO-ASK: how to delete customer bookings
        // when(bookingRepository.delete((Booking) anyObject())).then(customerBookings.remove(0));

        // TODO-ASK: how to check if customer bookings delete
        Customer returnedCustomer = customerService.deleteCustomer(anyInt());


        // or returnedCustomer
        assertEquals(0, returnedCustomer.getBookings().size());
    }

}