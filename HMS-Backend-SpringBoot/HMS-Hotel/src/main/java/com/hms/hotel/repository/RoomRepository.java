package com.hms.hotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.hms.hotel.entity.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {

}
