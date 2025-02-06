package com.example.restapijpaproject.model;

import jakarta.persistence.*;

/**
 * As per the MVC(model,view,controller) this is the model class.This class initialise the class
 * attributes which describes the state of the application. The values
 * are modified by the CRUD operations and stored in the database,they exist till the life-time of the
 * application. It also follows the single role responsibility principle.
 */
@Entity   //Entity is referred as a java class
@Table(name = "movieDetails") // Table name
public class Movie {

    /**
     * We are using ID as the key-value pair. ID is the key it will display the attribute field values
     * associated to that particular ID. The ID value is generated automatically and the value should
     * not be null
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)

    /** Initialised class attributes with required data types and defined the access modifier as private
     * so that we can access the attributes within this class only so that other users cannot access them
     * directly. If you want to access them outside the class we need to specify getter and setter methods
     * for them.
     */
    private Long id;
    private String movieTitle;
    private String movieGenre;
    private int releaseYear;
    private String shortDescription;
    private String director;

    /**
     * The empty constructor or no-argument constructor
     * It is used to create class object with default values of class attributes
     * If the movie constructor is having some arguments and if you want to inject other class as argument
     * via constructor there will be a conflict between them inorder to avoid it we are using empty
     * constructor here. A class can have one or more constructors.
     */
    public Movie() {

    }

    public Movie(String movieTitle, String movieGenre, int releaseYear, String shortDescription, String director) {
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
        this.releaseYear = releaseYear;
        this.shortDescription = shortDescription;
        this.director = director;
    }

    /**
     * getter method for id
     *
     * @return id value
     */
    public Long getId() {
        return id;
    }

    /**
     * setter method for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To fetch the value of the movieTitle attribute
     *
     * @return returns the value assigned to movieTitle field. The return type is String so it returns
     * String value only.
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * If you want to change the value of MovieTitle. We will call the set method of that particular
     * field. This method does not return anything so it is mentioned as void
     *
     * @param movieTitle is passed as the parameter, when the set method was called it will directly
     *                   assign a new value
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * As mentioned get method is used to fetch value of movieGenre field.
     *
     * @return it returns the movieGenre fields associated value
     */
    public String getMovieGenre() {
        return movieGenre;
    }

    /**
     * set method is used to assign new value to the attribute
     *
     * @param movieGenre is passed as the parameter to change the existing value and set a new value
     */
    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    /**
     * get method for release year
     * To retrieve releaseYear field value we will call this method
     *
     * @return returns the int value of releaseYear to the method
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * to modify an assigned value
     *
     * @param releaseYear is the parameter used to modify the releaseYear value
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Same principle applies for all the get and set methods
     * fetches the value of shortDescription
     *
     * @return returns the value of the shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * If you want to update old description with new one we can use this setShortDescription
     *
     * @param shortDescription is the parameter passed to this method
     *                         when the method is called we can pass the value in place of parameter
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Gives the name of the director
     *
     * @return the name of the director to the method
     */
    public String getDirector() {
        return director;
    }

    /**
     * set method is used for changing the old values with new ones
     *
     * @param director is the parameter
     *                 the director value passed as parameter is assigned to the class attribute
     */
    public void setDirector(String director) {
        this.director = director;
    }
}
