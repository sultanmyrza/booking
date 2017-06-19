package com.sultanmyrza.booking.service;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.repository.BookingRepository;
import com.sultanmyrza.booking.service.statePattern.BookingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

/**
 * As far as I understood it is another layer of
 * abstraction. We don't have to implement what Service
 * Layer do in Controller Class. Controller don't have to know
 * where it takes data from is it (DB, another service etc).
 * Controller only say give me all bookings and Service layer
 * give it to controller. And Service layer handle where it takes
 * data from. In this case we take data from derby using CrudRepository
 * so when spring start it will create bean with name bookingRepository
 * so we have to inject it here
 */

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private HashMap<String, String> response = new HashMap<>();

    public List<Booking> getAllBookings() {

        System.out.println("BookingService.getAllBookings");
        /**
         * bookingRepository.findAll() returns iterable
         * but I return List so a little googling find me this solution
         * https://stackoverflow.com/questions/6416706/easy-way-to-change-iterable-into-collection
         * as I understood from java 8 it supports lambda functions
         * now I don't know how exactly this lambdas work in Java
         * but if necessary I can learn it later on
         * but for now it solved my issue so I can focus on other function
         */
        List<Booking> bookings = new ArrayList<>();
        bookingRepository.findAll().forEach(bookings::add);

        return bookings;
    }

    public HashMap<String, String> addBooking(Booking newBooking) {

        System.out.println("BookingService.addBooking");
        // TODO: 17. 5. 30 Wrap to try catch block [DONE]
        try {

            bookingRepository.save(newBooking);
            response.put("status", "success");
            response.put("info", "succesfully added new booking");
        }
        catch (Exception e) {

            response.put("status", "error");
            response.put("info", e.toString());
        }
        finally {

            // TODO: 17. 5. 30 Return HashMap response [DONE]
            return response;
        }
    }

    public HashMap<String, String> updateBooking(Integer bookingId, Booking updatedBooking) {

        /**
         * We can use .save() method for both creating and updating entities
         * if row with the id == updatedBooking.getId() exist then it updates
         * else it creates new entity
         * I could just do bookingRepository.save(updatedBooking)
         * but decided to somehow use bookinId
         */
        System.out.println("BookingService.updateBooking");
        // TODO: 17. 5. 30 Wrap to try catch [DONE]
        try {

            Booking booking = bookingRepository.findOne(bookingId);

            System.out.println("Here is booking " + booking);
            String initialState = booking.getState();
            String newState = updatedBooking.getState();

            // TODO: 17. 5. 30 Implement state pattern here [DONE]
            BookingMachine bookingMachine = new BookingMachine(initialState);

            if (newState.equals("booked")) {
                response = bookingMachine.book();
            }

            if (newState.equals("canceled")) {
                response = bookingMachine.cancel();
            }

            // TODO: 17. 5. 30 Add response for success case [DONE]
            if (response.get("status").equals("success")) {

                System.out.println("updating in DB");
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

            // TODO: 17. 5. 30 Return HashMap response [DONE]
            return response;
        }
    }

    public HashMap<String, String> deleteBooking(Integer bookingId) {

        System.out.println("BookingService.deleteBooking");

        // TODO: 17. 5. 30 Wrap to try catch [DONE]
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

            // TODO: 17. 5. 30 Return HashMap response [DONE]
            return response;
        }
    }

    public List<Booking> findByState(String state) {

        System.out.println("BookingService.findByState");
        // TODO: 17. 6. 10 Wrap to try catch block
        List<Booking> bookings;

        bookings = bookingRepository.findByStateEquals(state);

        return bookings;
    }
}
