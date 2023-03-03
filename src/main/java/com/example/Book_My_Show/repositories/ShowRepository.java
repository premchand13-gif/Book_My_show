package com.example.Book_My_Show.repositories;

import com.example.Book_My_Show.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {
}
