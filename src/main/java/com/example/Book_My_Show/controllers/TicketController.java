package com.example.Book_My_Show.controllers;

import com.example.Book_My_Show.requestDtos.TicketDto;
import com.example.Book_My_Show.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/book_ticket")
    public ResponseEntity<String > bookTicket(@RequestBody TicketDto ticketDto){
        try {
            String s=ticketService.bookTicket(ticketDto);
            return new ResponseEntity<>(s,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/cancel_ticket")
    public ResponseEntity<String> cancelTicket(@RequestParam int ticketId){
        String s=ticketService.cancelTicket(ticketId);
        return new ResponseEntity<>(s,HttpStatus.FOUND);
    }
}
