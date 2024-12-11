package com.example.restapijpaproject;


import com.example.restapijpaproject.database.MovieRepository;
import com.example.restapijpaproject.model.Movie;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * This is the view class means the interaction source between the user and server. The user sends a
 * http request and receives a http response. URL is the path or way where our application is deployed.
 * In this project we are using GlassFish webserver, server is running on port 8080. We will use the
 * Glassfish url link given in the edit configuration file till ex: http://********:8080/
 * /api is the default path used in this project to run our application. If I want to view the
 * contents of particular page I can navigate to that page using the @Path Annotation.
 */

@Path("/movies")
public class MovieResource {

    /**
     * The MovieRepository entity class have been injected to this MovieResource class. The
     * CDI(Context and dependency injection) will
     * create a no argument constructor to create class object and movieRepository instance is initialised
     */
    @Inject
    private MovieRepository movieRepository;


    /**
     * Under the /movies path all the list of movies are displayed.
     * GET is a read operation. It will read the values from the database table and displays the list of
     * movies. Under the /movies path all the movies with movie class fields are displayed.
     * produces this annotation is used to display the @GET operation output on screen in a json format.
     * These CRUD operations are the end-points of REST api.
     *
     * @return the displayAllMovies() method from MovieRepository is called, it returns the movie list
     * to the getAllMovies
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAllMovies() {
        return movieRepository.displayAllMovies();

    }

    /**
     * In this method we are doing read operations based on the id path. Under /movies path we will
     * receive information about all movies, but under id path we will display specific column information
     * associated to that particular id only.
     * GET is the read operation of REST api
     * path is the route of the application
     *
     * @param id is the parameter. The path and pathparam is id here. When you want to display only the column values
     *           of particular id so you can give the id value so it will only show the user movie details of associated id
     *           only
     * @return if movieid is null it returns not found status or if movieid is notnull it returns movie
     * details
     */
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response readMovieById(@PathParam("id") long id) {
        Movie movieid = movieRepository.getMovieById(id);

        if (movieid == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(movieid).build();
    }

    /**
     * POST is the create operation. It creates new movie details using POSTMAN,these details are written
     * into the database.
     * consumes annotation is used to input movie details. The body format is json. It looks like a text
     * file only.
     *
     * @return when the new movie details are created it returns a response stating movie created.
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewMovie(Movie movie) {
        movieRepository.newMovie(movie);
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     * PUT is an update operation. If we want to update any existing movie information the PUT resource
     * is used.
     * PATH Inorder to change the details of that particular column details we need the id value so
     * we will include the id value as path along with the appserver url.
     * CONSUME consumes the old movie details information and updates it with new info.
     *
     * @param id the parameter.To change the value related to that particular id it is passed as
     *           parameter. Pathparam is used to give dynamic values in the url.They are represented in {}
     *           Below we can see so the id value is passed to the PathParam and details associated to it are
     *           updated.
     * @return if id value is null then it returns response stating not found otherwise we will receive
     * "Movie Information updated successfully"
     * @return the info is updated and gives back response movie
     * info updated successfully.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") long id, Movie movie) {

        Movie oldMovie = movieRepository.getMovieById(id);
        if (oldMovie == null) {

            return Response.status(Response.Status.NOT_FOUND).build();
        }


        oldMovie.setMovieTitle(movie.getMovieTitle());
        oldMovie.setMovieGenre(movie.getMovieGenre());
        oldMovie.setReleaseYear(movie.getReleaseYear());
        oldMovie.setShortDescription(movie.getShortDescription());
        oldMovie.setDirector(movie.getDirector());

        movieRepository.updateMovieInfo(oldMovie);

        return Response.ok("Movie Information updated successfully").build();
    }


    /**
     * As the name itself suggests it will delete the movie information from the table.
     * PATH here we are passing dynamic id value to delete the movie info related to that id
     * Consumes will take the movie class field attributes value as input and deletes the details
     * from the database table
     *
     * @param id is the pathparameter we are passing here. The path value is passed as the parameter
     *           here.
     * @return if id is null then it returns http not found response or else if the id value is valid
     * then it returns a text message "Movie Information deleted successfully".
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") long id) {

        Movie deleteMovie = movieRepository.getMovieById(id);
        if (deleteMovie == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Movie is not found").build();
        }

        movieRepository.deleteMovieById(id);
        return Response.ok("Movie Information deleted successfully").build();
    }
}
