package com.ussd.app.demo.Service.impl;

import com.ussd.app.demo.model.Session;
import com.ussd.app.demo.model.Users;
import com.ussd.app.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsrImpl implements usrService {
    @Autowired
    private UserRepo userRepo;



    @Override
    public Users findUserById(String sessionId) {
        Optional<Users> us=userRepo.findById(sessionId);
        Users user=new Users();
        user=us.get();
        return user;

    }

    @Override
    public Users updateUser(Users users, String Sessionid) {
        Optional<Users> user=userRepo.findById(Sessionid);
        Users usr=new Users();
        usr=user.get();
        usr.setFirstName(users.getFirstName());
        usr.setLastName(users.getLastName());
        usr.setAge(users.getAge());
        userRepo.save(usr);
        return usr;
    }
}
