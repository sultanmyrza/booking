package com.sultanmyrza.booking.repository;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
