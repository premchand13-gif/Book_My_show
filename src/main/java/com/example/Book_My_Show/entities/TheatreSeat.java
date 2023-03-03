package com.example.Book_My_Show.entities;

import com.example.Book_My_Show.enums.SeatType;
import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_seats")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheatreSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private String seatNo;

    @ManyToOne
    @JoinColumn
    private Theatre theatre;
}
