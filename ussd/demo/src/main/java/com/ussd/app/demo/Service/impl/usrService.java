package com.ussd.app.demo.Service.impl;

import com.ussd.app.demo.model.Session;
import com.ussd.app.demo.model.Users;

public interface usrService {
    public  Users findUserById(String sessionId);
    public Users updateUser(Users users, String Sessionid);
}
