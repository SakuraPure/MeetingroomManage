package com.example.DAO;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.model.ResultBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultBeanDAO {

    public <T> JSONObject roomSelectResult(List<T> selectResult, int code, String message, String type,int total) {
        JSONObject result = new JSONObject();
        result.put("items", selectResult);
        result.put("total", total);

        JSONObject reponseObject = new JSONObject();
        reponseObject.put("code", code);
        reponseObject.put("result", result);
        reponseObject.put("message", message);
        reponseObject.put("type", type);

        return reponseObject;
    }
}
