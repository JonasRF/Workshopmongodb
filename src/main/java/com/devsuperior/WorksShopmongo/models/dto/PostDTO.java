package com.devsuperior.WorksShopmongo.models.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.WorksShopmongo.models.embedded.Author;
import com.devsuperior.WorksShopmongo.models.embedded.Comment;
import com.devsuperior.WorksShopmongo.models.entities.Post;

public class PostDTO {

	private Instant moment;
	private String title;
	private String body;
	
	private Author author;
	
	private List<Comment> comments = new ArrayList<>(); 
	
	public PostDTO() {
	}

	public PostDTO(Instant moment, String title, String body, Author author) {
		this.moment = moment;
		this.title = title;
		this.body = body;
		this.author = author;
	}
	
	public PostDTO(Post entity) {
		this.moment = entity.getMoment();
		this.title = entity.getTitle();
		this.body = entity.getBody();
		this.author = entity.getAuthor();
		this.comments.addAll(entity.getComments());
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}
}
