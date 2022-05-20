package com.comp.mywebappv2.post;

import com.comp.mywebappv2.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired private PostRepository repoP;

    public List<Post> listAllP(){
        return (List<Post>) repoP.findAll();
    }

    public void save(Post post){
        repoP.save(post);
    }

    public Post get(Integer id) throws PostNotFoundExceotion {
        Optional<Post> result = repoP.findById(id);
        if (result.isPresent()){
             return result.get();
        }
        throw new PostNotFoundExceotion("could not found any post ID: " + id);
    }

    public void delete(Integer id) throws PostNotFoundExceotion {
        Long count = repoP.countById(id);
        if (count == null || count == 0){
            throw new PostNotFoundExceotion("could not found any Post ID: " + id);
        }
        repoP.deleteById(id);
    }
}
