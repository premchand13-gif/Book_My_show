package com.example.Book_My_Show.repositories;

import com.example.Book_My_Show.entities.TheatreSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreSeatRepository extends JpaRepository<TheatreSeat,Integer> {
}
