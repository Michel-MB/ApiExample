package com.ussd.app.demo.controller;

import com.ussd.app.demo.Service.impl.IUssdService;
import com.ussd.app.demo.Service.impl.usrService;
import com.ussd.app.demo.model.Session;
import com.ussd.app.demo.model.Users;
import com.ussd.app.demo.model.UssdReq;
import com.ussd.app.demo.repo.SessRepo;
import com.ussd.app.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@RestController
@RequestMapping("/api/ussd")
public class UssdControler {
    @Autowired
    private SessRepo sessRepo;
    @Autowired
    private IUssdService iUssdService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private usrService usService;
    public UssdControler(SessRepo sessRepo, UserRepo userRepo) {
        this.sessRepo = sessRepo;
        this.userRepo = userRepo;
    }

    @PostMapping
    public void postRequest(HttpServletResponse hresponse, HttpServletRequest hrequest/*,@RequestBody UssdReq ussdReq*/) throws IOException {
        Session sess = new Session();
        Users usr = new Users();
//        String sessionId=ussdReq.getSessionId();
//        String msisdn=ussdReq.getSessionId();
//        String input=ussdReq.getInput();
//        PrintWriter printWriter = hresponse.getWriter();


        String sessionId = hrequest.getParameter("sessionid");
        String msisdn = hrequest.getParameter("msisdn");
        String input = hrequest.getParameter("input");
        //String newReq=hrequest.getParameter("newRequest");
        PrintWriter printWriter = hresponse.getWriter();
        int level=0;
        int levelprt=1;
        String language=null;
        String newReq;
        Optional<Session> findSess=sessRepo.findById(sessionId);
        if(!findSess.isPresent()){
            newReq="1";
        }else{
            newReq="0";
        }
        if(newReq.equals("1")){ //new request
            //register new session
            newReq="0";
            sessRepo.save(new Session(sessionId,msisdn,level,levelprt,input));
            printWriter.println("1. English");
            printWriter.println("2. Kinyarwanda");

        }else if(newReq.equals("0")) {
            //check level
            level = iUssdService.findSessionBySessionId(sessionId).getSessionLvl();
            usr.setId(sessionId);
            //levelprt to check
            levelprt = iUssdService.findSessionBySessionId(sessionId).getLvlprt();
            Optional<Users> us=userRepo.findById(sessionId);
            if (level == 0) {

                if(!us.isPresent()) {

                    language = input;
                    if (language.equals("1")) {
                        printWriter.print("enter your name");
                    }
                    else if (language.equals("2")) {
                        printWriter.print("Amazina: ");
                    }
                    usr.setLanguage(input);
                    usr.setId(sessionId);
                    userRepo.save(usr);
                }else {
                    usr=us.get();
                    language = usService.findUserById(sessionId).getLanguage();
                    if (language.equals("1")) {

                        if (levelprt == 1) {
                            usr.setFirstName(input);
                            usService.updateUser(usr, sessionId);
                            printWriter.print("enter Your Last name");
                        } else if (levelprt == 2) {
                            usr.setLastName(input);
                            usService.updateUser(usr, sessionId);
                            printWriter.print("enter Your Age");
                        } else {
                            level++;
                            usr.setAge(input);
                            usService.updateUser(usr, sessionId);
                            printWriter.println("User Saved");
                        }
                        levelprt++;
                    } else if (language.equals("2")) {
                        if (levelprt == 1) {
                            usr.setFirstName(input);
                            usService.updateUser(usr, sessionId);
                            printWriter.print("Irindi Zina:");
                        } else if (levelprt == 2) {
                            usr.setLastName(input);
                            usService.updateUser(usr, sessionId);
                            printWriter.print("Imyaka");
                        } else {
                            level++;
                            usr.setAge(input);
                            usService.updateUser(usr, sessionId);
                            printWriter.println("Murakoze Kwiyandikisha");
                        }
                        levelprt++;

                    } else {
                        printWriter.println("Please select correctly/muhitemo neza");
                    }

                }
                    sessRepo.save(new Session(sessionId, msisdn, level, levelprt, input));
                } else if (level == 1) {
                    language = usService.findUserById(sessionId).getLanguage();
                    if (language.equals("1")) {
                        printWriter.print("thanks for registering");
                    } else {
                        printWriter.print("Murakoze Kwiyandikisha");
                    }
                    usr.setFirstName(input);
                    usr.setId(sessionId);
                    userRepo.save(usr);
                }
            }
        }

    }


//        Integer level;
//        if (input == null) {
//            printWriter.println("1.Kinyarwanda \n2.English");
//        } else {
//            Optional<Session> findSess = sessRepo.findById(sessionId);
//            if (!findSess.isPresent()) {
//                level = 1;
//
//                printWriter.println("Welcome Please Enter Your Name");
//                sess.setSessionId(sessionId);
//                sess.setMsisdn(msisdn);
//                sess.setSessionLvl(level);
//                sessRepo.save(sess);
//            } else {
//                sess = findSess.get();
//                level = sess.getSessionLvl();
//
//                Optional<Users> findUser = userRepo.findById(sessionId);
//                if (!findUser.isPresent()) {
//
//
//                    usr.setId(sessionId);
//                    usr.setFirstName(input);
//                    userRepo.save(usr);
//                } else {
//                    usr=findUser.get();
//                    switch (level) {
//                        case 1:
//                            printWriter.println("Please Enter Last Name");
//                            break;
//                        case 2:
//                            printWriter.println("Please Enter Your Age ");
//                            usr.setLastName(input);
//                            usService.updateUser(usr,sessionId);
//                            break;
//                        case 3:
//                            printWriter.print("User Saved");
//                            usr.setAge(input);
//                            usService.updateUser(usr,sessionId);
//                            break;
//
//                        default:
//                            printWriter.println("an error occured ");
//                            break;
//                    }
//
//                    level++;
//                    sess.setSessionLvl(level);
//
//                    sessRepo.save(sess);
//                }
//            }
//        }
//
//    }
//}


