package com.example.Book_My_Show.entities;

import com.example.Book_My_Show.enums.SeatType;
import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "show_seats")
@Data
@NoArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int price;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private boolean isBooked;
    private Date bookedOn;
    @ManyToOne
    @JoinColumn
    private Show show_showSeat;
}
