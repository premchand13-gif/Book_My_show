package com.example.Book_My_Show.entities;

import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int totalPrice;
    private String theatreName;
    private String movieName;
    private String ticketId= UUID.randomUUID().toString();
    private LocalTime showTime;
    private LocalDate showDate;
    private String bookedSeats;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Show show_ticket;
}
