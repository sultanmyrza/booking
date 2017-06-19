package com.sultanmyrza.booking.service.statePattern;

import java.util.HashMap;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */
public interface BookingState {

    public HashMap<String, String> book();
    public HashMap<String, String> cancel();
}
