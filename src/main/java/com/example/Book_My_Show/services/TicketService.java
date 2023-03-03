package com.example.Book_My_Show.services;

import com.example.Book_My_Show.entities.*;
import com.example.Book_My_Show.enums.SeatType;
import com.example.Book_My_Show.repositories.ShowRepository;
import com.example.Book_My_Show.repositories.TicketRepository;
import com.example.Book_My_Show.repositories.UserRepository;
import com.example.Book_My_Show.requestDtos.TicketDto;
import jakarta.mail.internet.MimeMessage;
//import javax.persistence.*;
//import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;
    public String bookTicket(TicketDto ticketDto) throws Exception{
        Ticket ticket=new Ticket();

        Show show=showRepository.findById(ticketDto.getShowId()).get();

        List<String> requiredSeats=ticketDto.getBookedSeats();
        boolean seatsAvailable=checkSeats(requiredSeats,show);
        if(!seatsAvailable){
            throw new Exception("Seats not available");
        }


        Movie movie=show.getMovie();
        Theatre theatre=show.getTheatre_show();
        User user=userRepository.findById(ticketDto.getUserId()).get();

        ticket.setMovieName(movie.getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setShow_ticket(show);
        ticket.setUser(user);
        ticket.setTheatreName(theatre.getName());

        ticket.setTotalPrice(totalTicketsPrice(requiredSeats,show));

        StringBuilder bookedSeats= new StringBuilder();
        for(int i=0;i<requiredSeats.size();i++){
            if(i==requiredSeats.size()-1){
                bookedSeats.append(requiredSeats.get(i));
            }
            else{
                bookedSeats.append(requiredSeats.get(i)).append(",");
            }
        }
        ticket.setBookedSeats(bookedSeats.toString());
        ticketRepository.save(ticket);

        List<Ticket> ticketList=show.getTicketShowList();
        ticketList.add(ticket);
        show.setTicketShowList(ticketList);
        showRepository.save(show);

        List<Ticket> tickets=user.getTicketList();
        tickets.add(ticket);
        user.setTicketList(tickets);
        userRepository.save(user);

        //email service
        String body = "Hi this is to confirm your booking for seat No "+bookedSeats.toString() +" for the movie : " + ticket.getMovieName()+" and ticket Id is "+ticket.getTicketId();


        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("backeendacciojob@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);

        return "Tickets booked";



    }
    public int totalTicketsPrice(List<String> requiredSeats,Show show) {
        int totalPrice = 0;
        List<ShowSeat> seatList = show.getShowSeatList();
        for (ShowSeat ss : seatList) {
            String seatNo = ss.getSeatNo();
            if (requiredSeats.contains(seatNo)) {
                totalPrice+=ss.getPrice();
                ss.setBooked(true);
                ss.setBookedOn(new Date());

            }
        }
        return totalPrice;
    }
    public boolean checkSeats(List<String> requiredSeats,Show show) throws Exception{
        List<ShowSeat> seatList=show.getShowSeatList();
        for(ShowSeat ss:seatList){
            String seatNo=ss.getSeatNo();
            if(requiredSeats.contains(seatNo)){
                if(ss.isBooked()){
                    return false;
                }

            }
        }
        return true;
    }
    public String cancelTicket(int ticketId){
        Ticket ticket=ticketRepository.findById(ticketId).get();
        User user=ticket.getUser();
        Show show=ticket.getShow_ticket();
        List<String> bookedList= List.of(ticket.getBookedSeats().split(","));
        List<ShowSeat> showSeatList=show.getShowSeatList();
        for(ShowSeat ss:showSeatList){
            String seatNo=ss.getSeatNo();
            if(bookedList.contains(seatNo)){
                ss.setBooked(false);
            }
        }

        user.getTicketList().remove(ticket);
        show.getTicketShowList().remove(ticket);
        ticketRepository.delete(ticket);
        userRepository.save(user);
        showRepository.save(show);
        return "ticket cancelled successfully";
    }

}
