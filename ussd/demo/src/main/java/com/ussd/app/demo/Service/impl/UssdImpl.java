package com.ussd.app.demo.Service.impl;

import com.ussd.app.demo.model.Session;
import com.ussd.app.demo.repo.SessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UssdImpl implements  IUssdService{
    @Autowired
    private SessRepo sessionRepo;

    @Override
    public Session findSessionBySessionId(String sessionid) {
        Optional<Session> sess=sessionRepo.findById(sessionid);
        Session session=new Session();
        session=sess.get();
        return session;
    }

    @Override
    public Session updateSession(String sessionid) {
        Session session=sessionRepo.findById(sessionid).get();
        Session existingSession=new Session();
        existingSession.setSessionLvl(session.getSessionLvl());

        sessionRepo.save(existingSession);

        return existingSession;
    }
}
