package com.sultanmyrza.booking.repository;

import com.sultanmyrza.booking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    List<Booking> findByStateEquals(String state);
}
