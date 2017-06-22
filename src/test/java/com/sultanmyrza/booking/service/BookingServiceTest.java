package com.sultanmyrza.booking.service;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.model.Customer;
import com.sultanmyrza.booking.repository.BookingRepository;
import com.sultanmyrza.booking.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by sultanmyrza on 17. 6. 22.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService = new BookingService();

    @Test
    public void addBooking_AddBookingToExistingCustomer() throws Exception {

        Customer mockCustomer = new Customer();
        Booking mockBooking = new Booking();

        when(customerRepository.findOne(anyInt())).thenReturn(mockCustomer);

        Booking newBooking = bookingService.addBooking(anyInt(), mockBooking);

        assertNotNull(bookingService.addBooking(anyInt(), new Booking()).getCustomer());

    }

    @Test
    public void addBooking_AddBookingToNonExistingCustomer() throws Exception {

        when(customerRepository.findOne(anyInt())).thenReturn(null);

        Booking newBooking = bookingService.addBooking(anyInt(), new Booking());

        // TODO-ASK: when it fails it did not say expected: NotNull, actual: null
        assertNotNull(newBooking.getCustomer());
    }

    @Test
    public void updateBooking_BookingStateMachineChangeStateFromBookedToCanceled() throws Exception {

        Booking oldBooking = new Booking();
        // TODO: change state to ENUMs
        oldBooking.setState("booked");

        Booking updatedBooking = new Booking();
        updatedBooking.setState("canceled");

        when(bookingRepository.findOne(anyInt()))
                .thenReturn(oldBooking);

        Booking returnedBooking = bookingService.updateBooking(anyInt(), updatedBooking);

        assertNotNull(returnedBooking);


    }
}