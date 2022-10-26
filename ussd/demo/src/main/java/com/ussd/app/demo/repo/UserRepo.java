package com.ussd.app.demo.repo;

import com.ussd.app.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,String> {
}
