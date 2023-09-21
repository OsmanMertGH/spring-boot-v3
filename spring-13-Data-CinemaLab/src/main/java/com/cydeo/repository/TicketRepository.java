package com.cydeo.repository;

import com.cydeo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to count how many tickets a user bought
    Integer countAllByUserAccountId(Long userId);

    //Write a derived query to list all tickets by specific email
    List<Ticket> findByUserAccountEmail(String email);

    //Write a derived query to count how many tickets are sold for a specific movie
    Integer countAllByMovieCinemaName(String name);

    //Write a derived query to list all tickets between a range of dates
    List<Ticket> findByDateTimeBetween(LocalDateTime dt1, LocalDateTime dt2);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all tickets are bought from a specific user
    @Query("SELECT t FROM Ticket t WHERE t.userAccount.id=?1")
    List<Ticket> fetchAllTicketsBoughtByUser(Long userId);

    //Write a JPQL query that returns all tickets between a range of dates

    @Query("SELECT t FROM Ticket t WHERE t.dateTime BETWEEN ?1 AND ?2")
    List<Ticket> fetchAllTicketsBetweenDates(LocalDate dt1, LocalDate dt2);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count the number of tickets a user bought

    @Query(value = "SELECT COUNT(*) FROM Ticket where user_account_id=?1",nativeQuery = true)
    Integer retrieveNumberOfTickets(Long userId);

    //Write a native query to count the number of tickets a user bought in a specific range of dates
    @Query(value = "SELECT COUNT(*) FROM Ticket WHERE user_account_id=?1 and date_time BETWEEN ?2 AND ?3",nativeQuery = true)
    Integer retrieveNumberOfTicketsSpecificRange(Long userId,LocalDate dt1, LocalDate dt2);

    //Write a native query to distinct all tickets by movie name

    @Query(value = "SELECT * FROM Ticket WHERE ")
    List<Ticket> retrieveAllTicketsByMovieName();

    //Write a native query to find all tickets by user email


    //Write a native query that returns all tickets


    //Write a native query to list all tickets where a specific value should be containable in the username or account name or movie name


}