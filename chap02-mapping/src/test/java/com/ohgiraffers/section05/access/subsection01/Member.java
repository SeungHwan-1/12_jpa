package com.ohgiraffers.section05.access.subsection01;

import jakarta.persistence.*;

@Entity(name = "member_section05_subsection01")
@Table(name = "tbl_member_section05_subsection01")
@Access(AccessType.FIELD) //기본값은 필드기반으로 한다 필드접근 게터접근
public class Member {

    /*
    * 필드 접근은 기본 값이므로 해당 설정은 제거해도 동일하게 동작한다
    * 또한 필드 레벨과 프로퍼티 레벨 모두 선언하면
    * 프로퍼티 레벨을 우선으로 사용한다.
    * */
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

    public Member() {
    }

    public Member(int memeberNo, String memeberId, String memberPwd, String nickName, String phone) {
        this.memeberNo = memeberNo;
        this.memeberId = memeberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memeberNo=" + memeberNo +
                ", memeberId='" + memeberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public int getMemeberNo() {
        System.out.println("getMemberNameNo로 access 되는지 확인");
        return memeberNo;
    }

    public void setMemeberNo(int memeberNo) {
        this.memeberNo = memeberNo;
    }

    public String getMemeberId() {
        System.out.println("getMemberNameId로 access 되는지 확인");

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
}
