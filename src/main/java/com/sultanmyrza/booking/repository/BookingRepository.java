package com.sultanmyrza.booking.repository;

import com.sultanmyrza.booking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

/**
 * Since most of applications do same CRUD operations
 * we can use CrudRepository to handle all that for us
 * and if we will need something special we can add it later.
 * Using CrudRepository is handy we don't have to create
 * SessionFactory get current session and commit transaction
 * close it and so on we just focus on our business logic
 *
 * if we want to create custom query (filters) we can always use
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
 * those methods cover 90% of our need in real projects
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
    // custom JPQL(java persistance query language)
    // will be generated for us, something like
    // select * from Booking where state = "some state"
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<Booking> findByStateEquals(String state);
}
