package com.ussd.app.demo.repo;

import com.ussd.app.demo.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SessRepo extends JpaRepository<Session, String> {


}
