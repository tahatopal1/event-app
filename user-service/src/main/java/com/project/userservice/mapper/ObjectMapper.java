package com.project.userservice.mapper;

public interface ObjectMapper <T, R>{

    R map(T t);

    T reverseMap(R r);

}
