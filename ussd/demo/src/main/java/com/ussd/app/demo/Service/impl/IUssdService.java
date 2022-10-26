package com.ussd.app.demo.Service.impl;

import com.ussd.app.demo.model.Session;
import org.springframework.stereotype.Service;


public interface IUssdService {
    public Session findSessionBySessionId(String sessionid);
    public Session updateSession(String Sessionid);

}
