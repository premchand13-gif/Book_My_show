package com.example.Book_My_Show.entities;

import com.example.Book_My_Show.enums.Genre;
import com.example.Book_My_Show.enums.Languages;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String movieName;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    @Enumerated(value = EnumType.STRING)
    private Languages languages;
    private int duration;
    private double rating;
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> showsList=new ArrayList<>();
}
