package com.digitalmedia.movies.model.data;


import com.digitalmedia.movies.model.Movie;
import com.digitalmedia.movies.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class DataLoader implements ApplicationRunner {

    private final MovieService service;
    private final MongoTemplate mongoTemplate;

    public DataLoader(MovieService service, MongoTemplate mongoTemplate) {
        this.service = service;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        mongoTemplate.dropCollection("movie");

        Movie movie1 = new Movie();
        movie1.setTitle("Movie1");
        movie1.setYear("2000");
        movie1.setPoster("poster1");
        movie1.setDirector("director1");
        movie1.setImdbId("1");
        Movie.Comment comment1= new Movie.Comment();
        comment1.setText("text1");
        comment1.setUsername("user1");
        comment1.setTimestamp(LocalDateTime.now());
        movie1.setComments(List.of(comment1));

        service.saveMovie(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Movie2");
        movie2.setYear("2002");
        movie2.setPoster("poster2");
        movie2.setDirector("director2");
        movie2.setImdbId("2");
        Movie.Comment comment2= new Movie.Comment();
        comment2.setText("text2");
        comment2.setUsername("user2");
        comment2.setTimestamp(LocalDateTime.now());
        movie2.setComments(List.of(comment2));

        service.saveMovie(movie2);
    }
}
