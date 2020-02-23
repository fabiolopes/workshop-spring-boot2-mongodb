package com.bios.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bios.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
