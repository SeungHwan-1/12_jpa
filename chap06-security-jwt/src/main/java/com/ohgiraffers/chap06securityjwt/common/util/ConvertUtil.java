package com.ohgiraffers.chap06securityjwt.common.util;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ConvertUtil {
    // 전달 받은 객체를 json 형태로 변환 후 파싱하는 메소드

    public static Object convertObjectToJsonObject(Object object) {
        //자바 객체를 json 문자열로 반환하는 라이브러리
        ObjectMapper mapper = new ObjectMapper();
        // json 문자열을 json 객체로 파싱하는 객체
        JSONParser parser = new JSONParser();

        String convertJsonString = null;
        Object convertObj = null;

        try{
            //자바 객체를 json 문자열로 반환
            convertJsonString = mapper.writeValueAsString(object);
            //json 문자열을 json 객체로 파싱
            convertObj = parser.parse(convertJsonString);

        }catch (JsonProcessingException | ParseException e){
            throw new RuntimeException(e);
        }
        return convertObj;
    }
}
