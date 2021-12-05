package com.learnjava.apiClient;

import com.learnjava.util.CommonUtil;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieClientTest {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/movies")
            .build();

    MovieClient movieClient = new MovieClient(webClient);

    @RepeatedTest(10)
    void retrieveMovie() {
        // given
        var movieInfoId = 1L;

        // when
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        var movie = movieClient.retrieveMovie(movieInfoId);
        CommonUtil.timeTaken();

        // then
//        System.out.println("moview: " + movie);
        assert movie != null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @RepeatedTest(10)
    void retrieveMovie_CF() {
        // given
        var movieInfoId = 1L;


        // when
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        var movie = movieClient
                .retrieveMovie_CF(movieInfoId)
                .join();
        CommonUtil.timeTaken();

        // then
//        System.out.println("moview: " + movie);
        assert movie != null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @RepeatedTest(10)
    void retrieveMovieList() {
        // given
        var movieInfoIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);

        // when
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        var movie = movieClient.retrieveMovieList(movieInfoIds);
        CommonUtil.timeTaken();

        // then
        assert movie != null;
        assert movie.size() == 7;
    }

    @RepeatedTest(10)
    void retrieveMovieList_CF() {
        // given
        var movieInfoIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);

        // when
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        var movie = movieClient.retrieveMovieList_CF(movieInfoIds);
        CommonUtil.timeTaken();

        // then
        assert movie != null;
        assert movie.size() == 7;
    }

    @RepeatedTest(10)
    void retrieveMovieList_CF_allOf() {
        // given
        var movieInfoIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);

        // when
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        var movie = movieClient.retrieveMovieList_CF_allOf(movieInfoIds);
        CommonUtil.timeTaken();

        // then
        assert movie != null;
        assert movie.size() == 7;
    }
}
