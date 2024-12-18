package com.ohgiraffers.section06.compositekey.subsection01.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

//임베드 될 수 있는 복합키 타입을 지정할 때 사용하는 어노테이션
@Embeddable
public class MemberPK implements Serializable {

    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "phone")
    private String phone;

    public MemberPK() {
    }

    public MemberPK(int memberNo, String memberId, String memberPwd, String nickName, String phone) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MemberPK{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    @Override
    public int hashCode() {
        return Objects.hash(memberNo,memberId);
    }

    @Override
    public boolean equals(Object obj) {

        //현재 비교할 객체가 현재 객체와 동일한 경우 트루
        if(this == obj) return true;
        //비교할 객체가 null이거나, 두객체의 클래스가 다르면 false
        if(obj == null || obj.getClass() != this.getClass()) return false;
        MemberPK memberPK = (MemberPK) obj;
        //멤버아이디가 동일하고, memberno가 동일하면 true 반환
        return memberNo == memberPK.memberNo && Objects.equals(memberId, memberPK.memberId);
    }
}
