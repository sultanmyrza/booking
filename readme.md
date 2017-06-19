# booking
Rest app for crud operations with bookings
+ You can list all bookings
+ You can get a booking by id
+ You can add booking 
+ You can update booking
+ You can delete booking

## Usage

Method    | Path                          | Response
-------   | ----------------------------- | ------------------------------------
`GET`     |  /bookings/                   | *return all bookings*
`GET`     |  /booking/{bookingId}         | *return booking with given id if exist*
`POST`    |  /booking/add                 | *add booking to in memory db (derby)*
`PUT`     |  /booking/update/{bookingId}  | *update booking with given id if exist*
`DELETE`  |  /booking/delete/{bookingId}  | *delete booking with given id if exist*


### Balázs requirements
- [X] MVC design pattern
- [ ] Sate design pattern
- [x] Spring framework
- [x] Hibernate
- [x] Derby
- [x] Tomcat
- [x] Maven
- [x] Intelij IDEA


## Tools used
+ Intelij IDEA

## Technologies
+ Spring framework 
