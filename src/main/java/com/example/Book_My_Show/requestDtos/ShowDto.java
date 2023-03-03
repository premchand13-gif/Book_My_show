package com.example.Book_My_Show.requestDtos;

import com.example.Book_My_Show.enums.ShowType;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
import lombok.Data;
//import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Data
public class ShowDto {
    private LocalDate showDate;
    private LocalTime showTime;

   private int classicPrice;
   private int premiumPrice;
   private int movieId;
   private int theaterId;

    private ShowType showType;
}
