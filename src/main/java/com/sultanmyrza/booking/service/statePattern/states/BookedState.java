package com.sultanmyrza.booking.service.statePattern.states;

import com.sultanmyrza.booking.service.statePattern.BookingMachine;
import com.sultanmyrza.booking.service.statePattern.BookingState;

import java.util.HashMap;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */
public class BookedState implements BookingState {

    private BookingMachine bookingMachine;
    private HashMap<String, String> response;

    public BookedState(BookingMachine bookingMachine) {
        this.bookingMachine = bookingMachine;
        response = new HashMap<>();
    }

    @Override
    public HashMap<String, String> book() {

        System.out.println("BookedState.book");

        response.put("status", "error");
        response.put("info", "Can't book! Already booked");

        return response;
    }

    @Override
    public HashMap<String, String> cancel() {

        System.out.println("BookedState.cancel");

        response.put("status", "success");
        response.put("info", "Can cancel");
        // TODO: 17. 5. 30 Change state to canceled [DONE]
        bookingMachine.setCurrentState(bookingMachine.getCanceledState());

        return response;
    }
}
