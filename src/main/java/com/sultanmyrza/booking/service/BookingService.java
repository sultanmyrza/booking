package com.sultanmyrza.booking.service;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.model.Customer;
import com.sultanmyrza.booking.repository.BookingRepository;
import com.sultanmyrza.booking.repository.CustomerRepository;
import com.sultanmyrza.booking.service.statePattern.BookingStateMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

@Service
public class BookingService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    private HashMap<String, String> response = new HashMap<>();

    public List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();
        bookingRepository.findAll().forEach(bookings::add);

        return bookings;
    }

    public Booking addBooking(Integer customerId, Booking newBooking) {

        // TODO: if user not exist return detailed error
        // TODO: UNITTEST add booking
        // this method add booking with null user if user
        // not exist but must return error that user not exist
        // which layer user existance should be checked?
        Customer customer = customerRepository.findOne(customerId);
        newBooking.setCustomer(customer);
        bookingRepository.save(newBooking);

        return newBooking;
    }

    // TODO: refactor update Booking further
    public Booking updateBooking(Integer bookingId, Booking updatedBooking) {

        Booking booking = bookingRepository.findOne(bookingId);

        // TODO: refactor state updating logic
        String initialState = booking.getState();
        String newState = updatedBooking.getState();

        BookingStateMachine bookingStateMachine = new BookingStateMachine(initialState);

        if (newState.equals("booked")) {
            response = bookingStateMachine.book();
        }

        if (newState.equals("canceled")) {
            response = bookingStateMachine.cancel();
        }

        if (response.get("status").equals("success")) {

            booking.setState(updatedBooking.getState());
            bookingRepository.save(booking);

            return booking;
        }

        return null;
    }

    public Booking deleteBooking(Integer bookingId) {

        Booking booking = bookingRepository.findOne(bookingId);
        bookingRepository.delete(booking);

        return booking;
    }

    public List<Booking> findByState(String state) {

        List<Booking> bookings;
        bookings = bookingRepository.findByStateEquals(state);

        return bookings;
    }
}
