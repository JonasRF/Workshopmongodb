package com.devsuperior.WorksShopmongo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.WorksShopmongo.models.dto.PostDTO;
import com.devsuperior.WorksShopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
	
	@Autowired
	private PostService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> PostById(@PathVariable String id){
		PostDTO obj = service.postById(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		List<PostDTO> list = service.findByTitle(text);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<PostDTO>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "start", defaultValue = "") String start,
			@RequestParam(value = "end", defaultValue = "") String end){
		List<PostDTO> list = service.fullSearch(text, start, end);
		return ResponseEntity.ok(list);
	}
}
