import com.example.restapijpaproject.MovieResource;
import com.example.restapijpaproject.database.MovieRepository;
import com.example.restapijpaproject.model.Movie;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class MovieResourceTest extends JerseyTest {

    @AfterEach
    public void afterEachTest() throws Exception {
        // Stänger ner servern
        tearDown();
    }

    private MovieRepository myRepository;

    @Override
    protected Application configure() {
        // Mocka repositoryt med Mockito
        myRepository = Mockito.mock(MovieRepository.class);
        // Registrera mocken och UserResource
        return new ResourceConfig(MovieResource.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(myRepository).to(MovieRepository.class);
                    }
                });
    }

    @Test
    public void testFindAll() {
        List<Movie> usersInDb = new ArrayList<>();
//vi tänker oss en User-klass med namn och email
//du ersätter med dina värden och attribut
        Movie movie = new Movie("game","comedy",2025,"political","shankar");
        usersInDb.add(movie);
//när metoden som hämtar alla users anropas - returnera listan ovan
        Mockito.when(myRepository.displayAllMovies()).thenReturn(usersInDb);

        Response response = target("/movies").request().get();
        List<Movie> movies = response.readEntity(new GenericType<List<Movie>>() {});

        assertEquals(200, response.getStatus());
        assertEquals(1, movies.size());
        assertEquals("game", movies.get(0).getMovieTitle());
    }

    @Test
    public void testFindById() {
        Movie movie = new Movie("urgent","thriller",2024,"sfd efe","hari");
        movie.setId(1L);
        Mockito.when(myRepository.getMovieById(1L)).thenReturn(movie);
        Response response = target("/movies/1").request().get();
        Movie result = response.readEntity(Movie.class);

        assertEquals(200, response.getStatus());
        assertEquals("urgent", result.getMovieTitle());
        assertEquals("hari", result.getDirector());
    }


    @Test
    public void testCreateMovie() {
        MockitoAnnotations.openMocks(this);

        Movie movie = new Movie("Fun", "suspense",2023,"sdssa jtgfg","siddu");
        doNothing().when(myRepository).newMovie(any(Movie.class));
        Entity<Movie> entity = Entity.entity(movie, MediaType.APPLICATION_JSON);
        Response response = target("/movies").request().post(entity);

        assertEquals(201, response.getStatus());

        verify(myRepository, times(1)).newMovie(any(Movie.class));

    }

    @Test
    public void testUpdateMovie() {
        MockitoAnnotations.openMocks(this);

        Movie movie = new Movie("Funny", "suspense",2023,"sdssa jtgfg","siddu");
        movie.setId(1L);

        Mockito.when(myRepository.getMovieById(1L)).thenReturn(movie);
        doNothing().when(myRepository).updateMovieInfo(movie);
        Entity<Movie> entity = Entity.entity(movie, MediaType.APPLICATION_JSON);
        Response response = target("/movies/1").request().put(entity);

        assertEquals(200, response.getStatus());

        verify(myRepository, times(1)).updateMovieInfo(any(Movie.class));

    }

    @Test
    public void testDeleteMovie() {
        MockitoAnnotations.openMocks(this);

        Movie movie = new Movie("Funny", "suspense",2023,"sdssa jtgfg","siddu");
        movie.setId(1L);

        Mockito.when(myRepository.getMovieById(1L)).thenReturn(movie);
        doNothing().when(myRepository).deleteMovieById(1L);

        Response response = target("/movies/1").request().delete();

        assertEquals(200, response.getStatus());

        verify(myRepository, times(1)).deleteMovieById(1L);

    }





}
