package com.example.imdb.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.domain.Movie;
import com.example.imdb.model.CriteriaBean;
import com.example.imdb.service.MovieService;

@RestController
@RequestScope
@RequestMapping("movies")
@CrossOrigin
public class ImdbController {
	private MovieService movieService;
	
	public ImdbController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping
	public Collection<Movie> findMoviesByCriteria(@RequestBody CriteriaBean criteria){
		return movieService.findAllMoviesByCriteria(criteria);
	}
}
