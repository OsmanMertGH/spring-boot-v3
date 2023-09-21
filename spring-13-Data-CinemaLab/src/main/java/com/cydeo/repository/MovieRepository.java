package com.cydeo.repository;

import com.cydeo.entity.Movie;
import com.cydeo.enums.State;
import com.cydeo.enums.Type;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a movie with a name
    List<Movie> findByName(String name);

    //Write a derived query to list all movies between a range of prices
    List<Movie> findByPriceBetween(BigDecimal price1, BigDecimal price2);

    //Write a derived query to list all movies where duration exists in the specific list of duration
    List<Movie> findByDurationIn(List<Integer> durations);

    //Write a derived query to list all movies with higher than a specific release date
    List<Movie> findByReleaseDateAfter(LocalDate releaseDate);

    //Write a derived query to list all movies with a specific state and type
    List<Movie> findByStateAndType(State state,Type type);


    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movies between a range of prices
    @Query("SELECT m FROM Movie m WHERE m.price BETWEEN ?1 AND ?2")
    List<Movie> fetchAllMoviesBetween(BigDecimal p1,BigDecimal p2);


    //Write a JPQL query that returns all movie names

    @Query("SELECT m.name FROM Movie m ")
    List<Movie> fetchAllMoviesName();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns a movie by name

    @Query(value = "SELECT DISTINCT Movie.name FROM Movie",nativeQuery = true)
    List<Movie> retrieveAllMovieNames();

    //Write a native query that return the list of movies in a specific range of prices
    @Query(value = "SELECT * FROM Movie WHERE Movie.price BETWEEN ?1 AND ?2",nativeQuery = true)
    List<Movie> retrieveBetweenTwoPrices(BigDecimal p1,BigDecimal p2);

    //Write a native query to return all movies where duration exists in the range of duration
    @Query(value = "SELECT * FROM Movie WHERE duration IN ?1",nativeQuery = true)
    List<Movie> retrieveExistDuration(List<Duration> durations);

    //Write a native query to list the top 5 most expensive movies
    @Query(value = "SELECT * FROM Movie order by price DESC LIMIT 5",nativeQuery = true)
    List<Movie> retrieveTop5ExpensiveMovie();

}