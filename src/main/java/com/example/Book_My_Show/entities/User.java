package com.example.Book_My_Show.entities;

import jakarta.persistence.*;
import lombok.*;
//import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true,nullable = false)
    private String mobNo;
    private  int age;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Ticket> ticketList=new ArrayList<>();

}
