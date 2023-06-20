package com.RoomMate.controller;

import com.RoomMate.model.Room;
import com.RoomMate.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }


    @GetMapping("/available")
    public List<Room> getAllAvailable() {
        return roomService.getAllAvailable();
    }


    @GetMapping("/book/{id}")
    public ResponseEntity<Room> bookRoom(@PathVariable String id) {
        Optional<Room> room = roomService.bookRoom(id);
        if(room.isPresent()){
            return new ResponseEntity<>(room.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}