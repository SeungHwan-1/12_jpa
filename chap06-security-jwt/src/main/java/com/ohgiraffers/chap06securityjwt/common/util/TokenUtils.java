package com.ohgiraffers.chap06securityjwt.common.util;

import com.ohgiraffers.chap06securityjwt.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {
    //토큰 관련 수행 클래스

    private static String jwtSecretKey;

    private static long tokenValidateTime;

    @Value("${jwt.key}") //yml 맋적은거 불러오는거
    public void setJwtSecretKey(String jwtSecretKey) {
        TokenUtils.jwtSecretKey = jwtSecretKey;
    }
    @Value("${jwt.time}")
    public void setTokenValidateTime(long tokenValidateTime) {
        TokenUtils.tokenValidateTime = tokenValidateTime;
    }
    /**
     * header의 token을 분리하는 메소드
     * @Param header : Authrization의 header 값을 가져온다.
     * @return token : Authrization의 token 을 반환한다.
     * */

    public static String splitHeader(String header) {
        if(!header.isEmpty()){
            return header.split(" ")[1]; //bearer를 제외한 토근 값만 반환
        }else {
            return null;
        }
    }

    /**
     * 유효한 토근인지 확인하는 메소드
     * @Param token : 토큰
     * @return boolean : 유효여부
     * */
    public static boolean isTokenValid(String token) {
        try{
            Claims claims = getClaimsFromToken(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * 토큰을 복호화 하는 메소드
     * @param token : 토큰
     * @return Claims
     * */
    public static Claims getClaimsFromToken(String token) {
        //jwts.parser 와 parseClaimsJws를 이용해   JWT의 유효성을 검증한다.
        //만약 JWT가 유효하지않으면 예외가 발생한다.
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
                .parseClaimsJws(token).getBody();
    }
    /**
     * 토큰을 생성하는 메소드
     * @param user userEntity
     * @return String token
     * */

    public static String generateJwtToken(User user) {
        //토큰 만료 시간을 현재 시간에서 지정된 휴요 시간 이후로 설정
        Date expireTime = new Date(System.currentTimeMillis() + tokenValidateTime);
        //JwtBuilder 를 사용해 JWT 토큰을 생성하는 객체 초기화
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())//토큰 헤더 설정(헤더에는 토큰의 타입 및 알고리즘 정보가 담김)
                .setClaims(createClaims(user))// 토큰의 클레임 설정(사용자 ㅇ정보와 같은 데이터를 담음)
                .setSubject("ohgiraffers token :" + user.getUserNo())// 토큰의 설명 정보를 담아줌
                .signWith(SignatureAlgorithm.HS256,createSignature())//토큰암호화방식정의
                .setExpiration(expireTime); // 만료설정
        //최종적으로 생성된 토큰을 문자열 형태로 반환
        return builder.compact();
    }



    /**
     * 토큰의 해더를 설정하는 메소드
     *
     * @return Map<String,Object> header의 설정정보
     * */

    private static Map<String,Object> createHeader(){ //필수는 아니다.
        Map<String,Object> header = new HashMap<>();
        //헤더의토근 타입을 JWT로 설정
        header.put("type","JWT");
        // 헤더의 토큰의 알고리즘을 HS256로 설정
        header.put("alg","HS256");
        // 헤더에 토큰 생성 시간을 밀리초 단위로 추가
        header.put("date",System.currentTimeMillis());

        return header;
    }

    /**
     * 사용자 정보를 기반으로 클레임을 생성해주는 메소드
     * @param user 사용자 정보
     * @return Map<String,Object> Claims 정보
     */
    private static Map<String, Object> createClaims(User user) {
        Map<String, Object> claims = new HashMap<>(); //얘네가 리턴값 맵으로받음
        claims.put("userId",user.getUserId());
        claims.put("role",user.getRole());
        claims.put("userEmail",user.getUserEmail());
        return claims;
    }
    /**
     *Jwt 서명을 발급해주는 메소드
     * @return Key
     */
    private static Key createSignature(){
        // 비밀 키 문자열을 Base64 로 디코딩하여 바이트 배열로 반환
        byte[] secretKey = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        //반환된 바이트 배열을 알고리즘을 사용해 key 객체로 반환
        return new SecretKeySpec(secretKey,SignatureAlgorithm.HS256.getJcaName());
    }

}
