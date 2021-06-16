package com.auth.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.auth.model.UserData;

@Repository
public interface UserRepo extends JpaRepository<UserData,String>{

	 UserData findByMailidAndPassword(String mailid,String pwd);


}
