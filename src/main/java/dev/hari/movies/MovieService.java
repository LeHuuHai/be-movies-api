package dev.hari.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private IMovieRepository movieRepository;

    @Autowired
    public MovieService(IMovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> SingleMovie(String id){
        return movieRepository.findMovieByImdbId(id);
    }
}
