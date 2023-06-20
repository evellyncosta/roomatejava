package com.RoomMate.service;

import com.RoomMate.model.Room;
import com.RoomMate.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return  roomRepository.findAll();
    }

    @Cacheable("rooms")
    public List<Room> getAllAvailable() {
        return  roomRepository.findAllAvailableRooms();
    }


    @CacheEvict(value = "rooms", allEntries = true)
    public Optional<Room> bookRoom(String id) {
        Optional<Room> roomSaved = roomRepository.findById(UUID.fromString(id));
        if(roomSaved.isPresent()){
            Room roomUpdated = roomSaved.get();
            roomUpdated.setAvailable(false);
            roomRepository.save(roomUpdated);
            return Optional.of(roomUpdated);
        }
        return roomSaved;
    }
}
