package com.sultanmyrza.booking.service;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.model.Customer;
import com.sultanmyrza.booking.repository.BookingRepository;
import com.sultanmyrza.booking.repository.CustomerRepository;
import com.sultanmyrza.booking.service.statePattern.BookingMachine;
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

    public HashMap<String, String> addBooking(Integer customerId, Booking newBooking) {

        try {
            Customer customer = customerRepository.findOne(customerId);
            newBooking.setCustomer(customer);
            bookingRepository.save(newBooking);
            response.put("status", "success");
            response.put("info", "succesfully added new booking");
        }
        catch (Exception e) {

            response.put("status", "error");
            response.put("info", e.toString());
        }
        finally {

            return response;
        }
    }

    public HashMap<String, String> updateBooking(Integer bookingId, Booking updatedBooking) {

        try {

            Booking booking = bookingRepository.findOne(bookingId);

            String initialState = booking.getState();
            String newState = updatedBooking.getState();

            BookingMachine bookingMachine = new BookingMachine(initialState);

            if (newState.equals("booked")) {
                response = bookingMachine.book();
            }

            if (newState.equals("canceled")) {
                response = bookingMachine.cancel();
            }

            if (response.get("status").equals("success")) {

                booking.setState(updatedBooking.getState());
                bookingRepository.save(booking);
                response.put("info", "booking successfully updated");
            }
        }
        catch (Exception e) {

            response.put("status", "error");
            response.put("info", e.toString());
        }
        finally {

            return response;
        }
    }

    public HashMap<String, String> deleteBooking(Integer bookingId) {


        try {

            bookingRepository.delete(bookingId);
            response.put("status", "success");
            response.put("info", "booking deleted id: " + bookingId);
        }
        catch (Exception e) {

            response.put("status", "error");
            response.put("info", e.toString());
        }
        finally {

            return response;
        }
    }

    public List<Booking> findByState(String state) {

        List<Booking> bookings;
        bookings = bookingRepository.findByStateEquals(state);

        return bookings;
    }
}
