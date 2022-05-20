package com.comp.mywebappv2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repoU;

    public List<User> listAllU() {
        return (List<User>) repoU.findAll();
    }

    public void save(User user) {
        repoU.save(user);
    }

    public User get(@PathVariable("id") Integer id)  throws UserNotFoundException {
        Optional<User> result = repoU.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("could not found any user ID: " + id);
    }

     public void delete(Integer id) throws UserNotFoundException {
        Long count = repoU.countById(id);
        if (count == null || count == 0){
            throw new UserNotFoundException("could not found any user ID: " + id);
        }
         repoU.deleteById(id);
     }
}


