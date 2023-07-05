package com.example.DAO;

import com.example.model.ResultBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResultBeanDAO {

    public <T> ResultBean reponseResultBean(List<T> result,int code,String message,String type) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(code);
        resultBean.setResult(result);
        resultBean.setMessage(message);
        resultBean.setType(type);
        return resultBean;
    }
}
