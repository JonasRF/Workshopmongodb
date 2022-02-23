package com.devsuperior.WorksShopmongo.services;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.WorksShopmongo.models.dto.PostDTO;
import com.devsuperior.WorksShopmongo.models.entities.Post;
import com.devsuperior.WorksShopmongo.repositories.PostRepository;
import com.devsuperior.WorksShopmongo.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public PostDTO postById(String id) {
		Post entity = getEntityById(id);
		return new PostDTO(entity);
	}
	
	private Post getEntityById(String id) {
		Optional<Post> result = repository.findById(id);
	    return result.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
	}
	
	public List<PostDTO> findByTitle(String text) {
		List<Post> list = repository.searchTitle(text);
		return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}
	
	public List<PostDTO> fullSearch(String text, String start, String end) {
		Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
		Instant endMoment = convertMoment(end, Instant.now());
		List<Post> list = repository.fullSearch(text, startMoment, endMoment);
		return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}

	private Instant convertMoment(String originalString, Instant alternative) {
		try {
			return Instant.parse(originalString);
		}
		catch(DateTimeParseException e) {
			return alternative;
		}
	}
}
