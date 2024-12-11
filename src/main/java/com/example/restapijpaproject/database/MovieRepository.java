package com.example.restapijpaproject.database;

import com.example.restapijpaproject.model.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * This is the controller class. All the database operations are done that are not visible.
 * The EntityManager acts as a bridge between database and the applications.
 * This entity is referred as @ApplicationScoped means MovieRepository exists throughout the lifecycle of
 * application.
 *
 * @Transactional refers to the transaction methods like creating EntityFactory manager,EntityManager,commiting
 * changes and closing the connections.These are all done automatically when we define the entity as
 * @transactional.
 * @PersistenceContext enables EntityManger to perform CRUD operations and automatically detects the change
 * made and sync them with the database
 */

@ApplicationScoped
@Transactional
public class MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * This method displayAllMovies() method is used to display the list of movies in the database table
     *
     * @return the fetched movie data is returned
     */
    public List<Movie> displayAllMovies() {
        String sql = "select * from movieDetails";
        Query query = entityManager.createNativeQuery(sql);

        return query.getResultList();
    }

    /**
     * The method getMovieById(id) is used when we want to display only particular movie data
     *
     * @param id parameter to find movie details with id
     * @return it searches for movie data with that specific id and returns them
     */
    public Movie getMovieById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    /**
     * newMovie(movie) is used to create new movie details. find,persist,merge and remove are entitymanager's
     * methods to read, create,update and delete data into database.
     *
     * @param movie object is the parameter and the fields in Movie class are also accessed.
     */
    public void newMovie(Movie movie) {
        entityManager.persist(movie);
    }

    /**
     * updateMovieInfo as the name suggests it is used to write updated info into the database
     *
     * @param movie parameter
     */
    public void updateMovieInfo(Movie movie) {

        entityManager.merge(movie);
    }

    /**
     * deleteMovieById method is used to delete the data from database
     *
     * @param id parameter is used to delete the column related to this id
     */
    public void deleteMovieById(Long id) {

        Movie movieID = entityManager.find(Movie.class, id);

        entityManager.remove(movieID);

    }
}

