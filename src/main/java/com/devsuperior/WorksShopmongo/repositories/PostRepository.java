package com.devsuperior.WorksShopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.WorksShopmongo.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
