package com.devsuperior.WorksShopmongo.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.WorksShopmongo.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);

	@Query("{ \r\n"
			+ "	$and: [ \r\n"
			+ "		{ \r\n"
			+ "			moment: {\r\n"
			+ "				$gte: ?1\r\n"
			+ "			} \r\n"
			+ "		}, \r\n"
			+ "		{ 	\r\n"
			+ "			moment: { \r\n"
			+ "				$lte: ?2} \r\n"
			+ "		} , \r\n"
			+ "		{ 	\r\n"
			+ "			$or: [ \r\n"
			+ "				{ \r\n"
			+ "					'title': { \r\n"
			+ "						$regex: ?0, \r\n"
			+ "						$options: 'i' \r\n"
			+ "					} \r\n"
			+ "				}, \r\n"
			+ "				{ \r\n"
			+ "					'body': { \r\n"
			+ "						$regex: ?0, \r\n"
			+ "						$options: 'i' \r\n"
			+ "					} \r\n"
			+ "				}, \r\n"
			+ "				{ \r\n"
			+ "					'comments.text': { \r\n"
			+ "						$regex: ?0, $options: 'i' \r\n"
			+ "					} \r\n"
			+ "				} \r\n"
			+ "			] \r\n"
			+ "		} \r\n"
			+ "	] \r\n"
			+ "}")
	List<Post> fullSearch(String text, Instant startMoment, Instant endMoment);
}
