package com.ohgiraffers.section02.column;


import jakarta.persistence.*;

/*
* 컬럼 매핑시 @Column 어노테이션에 사용 가능한 속성들
*
* name : 매핑할 테이블의 컬럼 이름
* insertable : 엔티티 저장시 필드 저장 여부(default : true) 쿼리가 있더라도 포함안됨
* updatable : 엔티티 수정 시 필드 저장 여부(defualt : true)
* table : 엔티티와 매핑될 테이블 이름. 하ㅣ나의 엔티티를 두개이상의 테이블에 매핑할 때 사용
* nullable : null 값 허용 여부 설정, not null 제약조건에 해당함 (default :true)
* unique : 컬럼에 유일성 제약조건
* length : 문자열 길이 String type 에서만 사용 (default : 255)
* columnDefinition : 직접 컬럼의 ddl 조정
* */
@Entity(name = "member_section02")
@Table(name = "tbl_member_section02")
public class Member {
    @Id
    @Column(name = "member_no")
    private int memeberNo;

    @Column(name = "member_id")
    private String memeberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickname")
    @Transient // 테이블 생성시 무시. 자바에서는 필요한 값이지만 db에 넣지 않을 때
    private String nickName;

    @Column(name = "phone",columnDefinition = "varchar(200) default '010-0000-0000'")
    private String phone;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "address",nullable = false)
    private String address;

    public Member(int memeberNo, String memeberId, String memberPwd, String nickName, String phone, String email, String address) {
        this.memeberNo = memeberNo;
        this.memeberId = memeberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memeberNo=" + memeberNo +
                ", memeberId='" + memeberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Member() {

    }
}
