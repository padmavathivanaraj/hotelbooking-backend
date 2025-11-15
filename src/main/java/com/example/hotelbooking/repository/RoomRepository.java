package com.example.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotelbooking.entity.Hotel;
import com.example.hotelbooking.entity.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // find rooms by hotel
    List<Room> findByHotel(Hotel hotel);

    // find available rooms for a hotel
    List<Room> findByHotelAndAvailableTrue(Hotel hotel);

    // find room by room number and hotel
    Room findByRoomNumberAndHotel(String roomNumber, Hotel hotel);
}
