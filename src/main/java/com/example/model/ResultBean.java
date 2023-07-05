package com.example.model;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Data
@Accessors(chain = true)
@Component
public class ResultBean {
    int code;
    Object result;
    String message;
    String type;

}
