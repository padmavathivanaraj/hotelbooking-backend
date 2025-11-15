package com.example.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.entity.Payment;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // find payment by booking
    Optional<Payment> findByBooking(Booking booking);

    // find payment by paymentId
    Optional<Payment> findByPaymentId(String paymentId);
}
