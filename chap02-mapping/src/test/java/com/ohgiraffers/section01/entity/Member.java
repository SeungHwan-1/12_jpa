package com.ohgiraffers.section01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/*
* entity 어노테이션은 jpa 에서 사ㅣ용되는 클래스임을 표시한다.
* 이 어노테이션을 사용하면 해당 클래스가 데이터베이스의 테이블과 매핑된다.
*
* 프로젝트 내에 다른 패키지에도 동일한 엔티티가 존재하는 경우 해당 엔티티를
* 식별하기 위한 중복 되지 않는 name 을 지정해 주어야 한다.
* enum ,interface 에서는 사용할 수 없다.
* 저장한 필드에 final을 사용하면 안된다.*/
@Entity(name = "member_section01") //클래스가 데이터베이스의 테이블과 매핑됨
@Table(name = "tbl_member_section01")
public class Member {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Id
    @Column(name = "member_no")
    private int memeberNo;

    @Column(name = "member_id")
    private String memeberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "phone")
    private String phone;

    public int getMemeberNo() {
        return memeberNo;
    }

    public void setMemeberNo(int memeberNo) {
        this.memeberNo = memeberNo;
    }

    public String getMemeberId() {
        return memeberId;
    }

    public void setMemeberId(String memeberId) {
        this.memeberId = memeberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Member() {
    }

    public Member(int memeberNo, String memeberId, String memberPwd, String nickName, String phone) {
        this.memeberNo = memeberNo;
        this.memeberId = memeberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
        this.phone = phone;
    }
}
