package com.comp.mywebappv2.post;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    public Long countById(Integer id);
}
