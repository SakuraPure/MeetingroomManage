package com.example.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.DAO.UserDAO;
import com.example.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorizeService {
    @Autowired
    UserDAO userDAO;

    public JSONObject login(String username,String password) {
        if (username == null) {
            JSONObject responseObject = new JSONObject();
            responseObject.put("code", -1);
            responseObject.put("result", "");
            responseObject.put("message", "用户名不能为空");
            responseObject.put("type", "error");
            return responseObject;
        }
        Account account = userDAO.getUserByUsername(username);
        if (account == null) {
            JSONObject responseObject = new JSONObject();
            responseObject.put("code", -1);
            responseObject.put("result", "");
            responseObject.put("message", "用户不存在");
            responseObject.put("type", "error");
            return responseObject;
        }
        if (!account.getPassword().equals(password)) {
            JSONObject responseObject = new JSONObject();
            responseObject.put("code", -1);
            responseObject.put("result", "");
            responseObject.put("message", "密码错误");
            responseObject.put("type", "error");
            return responseObject;
        }
        if (password == null) {
            JSONObject responseObject = new JSONObject();
            responseObject.put("code", -1);
            responseObject.put("result", "");
            responseObject.put("message", "密码不能为空");
            responseObject.put("type", "error");
            return responseObject;
        }
        if (account.getPassword().equals(password)) {
            JSONArray rolesArray = new JSONArray();
            JSONObject roleObject = new JSONObject();
            roleObject.put("roleName", "");
            roleObject.put("value", account.getRole());
            rolesArray.add(roleObject);

            JSONObject resultObject = new JSONObject();
            resultObject.put("roles", rolesArray);
            resultObject.put("userId", account.getId());
            resultObject.put("username", account.getUsername());
            resultObject.put("token", account.getId());
            resultObject.put("realName", "");
            resultObject.put("desc", "");

            JSONObject responseObject = new JSONObject();
            responseObject.put("code", 0);
            responseObject.put("result", resultObject);
            responseObject.put("message", "ok");
            responseObject.put("type", "success");
            return responseObject;
        }
        return null;
    }

    public JSONObject register(String username,String password){
        if(username==null){
            JSONObject responseObject = new JSONObject();
            responseObject.put("code", -1);
            responseObject.put("result", "");
            responseObject.put("message", "用户名不能为空");
            responseObject.put("type", "error");
            return responseObject;
        }
        Account account = userDAO.getUserByUsername(username);
        if(account!=null){
            JSONObject responseObject = new JSONObject();
            responseObject.put("code", -1);
            responseObject.put("result", "");
            responseObject.put("message", "用户已存在");
            responseObject.put("type", "error");
            return responseObject;
        }
        if(password==null){
            JSONObject responseObject = new JSONObject();
            responseObject.put("code", -1);
            responseObject.put("result", "");
            responseObject.put("message", "密码不能为空");
            responseObject.put("type", "error");
            return responseObject;
        }
        if(username!=null&&password!=null){
            Account account1 = new Account(username,password);
            userDAO.insertUserBase(account1);
            account1=userDAO.getUserByUsername(username);
            JSONArray rolesArray = new JSONArray();
            JSONObject roleObject = new JSONObject();
            roleObject.put("roleName","");
            roleObject.put("value",account1.getRole());
            rolesArray.add(roleObject);

            JSONObject resultObject = new JSONObject();
            resultObject.put("roles",rolesArray);
            resultObject.put("userId",account1.getId());
            resultObject.put("username",account1.getUsername());
            resultObject.put("token",account1.getId());
            resultObject.put("realName","");
            resultObject.put("desc","");

            JSONObject responseObject = new JSONObject();
            responseObject.put("code",0);
            responseObject.put("result",resultObject);
            responseObject.put("message","ok");
            responseObject.put("type","success");
            return responseObject;
        }
        return null;
    }

    public JSONObject getInfo(int token){
        Account account = userDAO.getUserById(token);
        if(account!=null){
            JSONArray rolesArray = new JSONArray();
            JSONObject roleObject = new JSONObject();
//            roleObject.put("roleName", "");
            roleObject.put("value", account.getRole());
            rolesArray.add(roleObject);

            JSONObject resultObject = new JSONObject();
            resultObject.put("userId", account.getId());
            resultObject.put("username", account.getUsername());
            resultObject.put("password", account.getPassword());
            resultObject.put("token", account.getId());
//            resultObject.put("realName", "");
//            resultObject.put("desc", "");
            resultObject.put("roles", rolesArray);

            JSONObject responseObject = new JSONObject();
            responseObject.put("code", 0);
            responseObject.put("result", resultObject);
            responseObject.put("message", "ok");
            responseObject.put("type", "success");
            return responseObject;
        }
        return null;
    }
}

