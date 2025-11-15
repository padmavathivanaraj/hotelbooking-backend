package com.example.hotelbooking.service;

import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.entity.Payment;
import com.example.hotelbooking.repository.BookingRepository;
import com.example.hotelbooking.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepo;
    private final BookingRepository bookingRepo;

    public PaymentService(PaymentRepository paymentRepo, BookingRepository bookingRepo) {
        this.paymentRepo = paymentRepo;
        this.bookingRepo = bookingRepo;
    }

    @Transactional
    public Payment createPayment(long bookingId, String paymentId, Double amount) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setPaymentId(paymentId);
        payment.setAmount(amount == null ? 0.0 : amount);
        payment.setStatus("PENDING");

        return paymentRepo.save(payment);
    }

    public Payment getPayment(long id) {
        return paymentRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
    }
}
