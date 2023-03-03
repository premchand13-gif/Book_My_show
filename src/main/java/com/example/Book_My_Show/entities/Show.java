package com.example.Book_My_Show.entities;

import com.example.Book_My_Show.enums.ShowType;
import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate showDate;
    private LocalTime showTime;
    @CreationTimestamp
    private Date createdDate;
    @UpdateTimestamp
    private Date updatedDate;
    @Enumerated(value = EnumType.STRING)
    private ShowType showType;
    @ManyToOne
    @JoinColumn
    private Movie movie;
    @OneToMany(mappedBy = "show_ticket",cascade = CascadeType.ALL)
    private List<Ticket> ticketShowList;
    @ManyToOne
    @JoinColumn
    private Theatre theatre_show;
    @OneToMany(mappedBy = "show_showSeat",cascade = CascadeType.ALL)
    private List<ShowSeat> showSeatList=new ArrayList<>();
}
