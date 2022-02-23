package com.devsuperior.WorksShopmongo.services;

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
}
