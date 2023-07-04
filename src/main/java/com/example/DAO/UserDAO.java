package com.example.DAO;

import com.example.model.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDAO {
    @Results({
            @Result(column = "f_id",property = "id")
    })
    @Select("select * from users where username=#{username}")
    Account getUserByUsername(String username);
    @Insert("insert into users(username,password,role) values(#{username},#{password},#{role})")
    int insertUserBase(Account account);
}
