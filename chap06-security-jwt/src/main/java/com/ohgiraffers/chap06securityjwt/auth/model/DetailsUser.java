package com.ohgiraffers.chap06securityjwt.auth.model;


import com.ohgiraffers.chap06securityjwt.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DetailsUser implements UserDetails {
    //오버라이딩전부
    //Spring Security 에서 인증을 위해 사용자 정보를 정의하는 역할

   private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DetailsUser() {
    }

    public DetailsUser(Optional<User> user) {
        this.user = user.get();
        //빈 값이면 에러가 발생하는데 이를 방지하기 위해 optional 객체로 반환해줌
        // 빈 값일 경우 에러 방지용 널이면 에러남
    }



    //권한 목록 반환 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String role:user.getRoleList()){
            authorities.add(new GrantedAuthority(){
                @Override
                public String getAuthority() {
                    return role;
                }
            });
        }
        return authorities;
    }



    @Override
    public String getPassword() {
        return user.getUserId();
    }

    //아래부터는 해당 내용을 관리할 시에 필요.. DB에 존재해야함.. 필수요소 x
    @Override
    public boolean isAccountNonExpired() {
        //계정 만료 여부 메소드 -펄스면 사용 없음
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠겨있는지 학인하는 메소드 - false 이면 사용할 수 없음
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        //탈토 예부 표현 메소드
        return true;
    }

    @Override
    public boolean isEnabled() {
        //계정 비활성화 여부
        return true;
    }
    @Override
    public String getUsername() {
        return "";
    }
}
