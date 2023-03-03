package com.example.Book_My_Show.entities;

import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    private List<TheatreSeat> theatreSeats=new ArrayList<>();
    @OneToMany(mappedBy = "theatre_show",cascade = CascadeType.ALL)
    private List<Show> showList=new ArrayList<>();
}
