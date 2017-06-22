package com.sultanmyrza.booking.service.statePattern.states;

import com.sultanmyrza.booking.service.statePattern.BookingStateMachine;
import com.sultanmyrza.booking.service.statePattern.BookingState;

import java.util.HashMap;

/**
 * Created by sultanmyrza on 17. 5. 30.
 */
public class CanceledState implements BookingState {

    BookingStateMachine bookingStateMachine;
    private HashMap<String, String> response;

    public CanceledState(BookingStateMachine bookingStateMachine) {
        this.bookingStateMachine = bookingStateMachine;
        response = new HashMap<>();
    }

    @Override
    public HashMap<String, String> book() {

        response.put("status", "success");
        response.put("info", "Can book.");
        bookingStateMachine.setCurrentState(bookingStateMachine.getBookedState());

        return response;
    }

    @Override
    public HashMap<String, String> cancel() {

        response.put("status", "error");
        response.put("info", "Can not cancel. Already canceled.");

        return response;
    }
}
