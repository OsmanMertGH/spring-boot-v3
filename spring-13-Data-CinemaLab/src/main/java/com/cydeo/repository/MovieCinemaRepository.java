package com.cydeo.repository;

import com.cydeo.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieCinemaRepository extends JpaRepository<MovieCinema, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read movie cinema with id
    Optional<MovieCinema> findById(Long id);

    //Write a derived query to count all movie cinemas with a specific cinema id
    Integer countAllByCinemaId(Long id);

    //Write a derived query to count all movie cinemas with a specific movie id
    Integer countAllByMovieId(Long id);

    //Write a derived query to list all movie cinemas with higher than a specific date
    List<MovieCinema> findByDateTimeAfter(LocalDate date);

    //Write a derived query to find the top 3 expensive movies
    List<MovieCinema> findTop3ByOrderByCinemaPriceDesc();

    //Write a derived query to list all movie cinemas that contain a specific movie name
    List<MovieCinema> findByMovieNameContains(String name);

    //Write a derived query to list all movie cinemas in a specific location name
    List<MovieCinema> findByCinemaLocationName(String name);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movie cinemas with higher than a specific date

    @Query("SELECT m FROM MovieCinema m WHERE m.dateTime>?1")
    List<MovieCinema> fetchAllCinemasHigherThan(LocalDate date);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count all movie cinemas by cinema id

    @Query(value = "SELECT count(*) from MovieCinema where cinema.id=?1",nativeQuery = true)
    Integer retrieveCountAllMovieCinemas(Long cinemaId);

    //Write a native query that returns all movie cinemas by location name

    @Query(value = "SELECT * FROM movie_cinema mc JOIN cinema c ON c.id = mc.cinema_id " +
            "JOIN location l ON l.id = c.location_id WHERE l.name = ?1", nativeQuery = true)
    List<MovieCinema> retrieveAllMCBasedLocationName(String locationName);

}