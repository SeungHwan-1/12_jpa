package com.ohgiraffers.section05.access.subsection02;

import jakarta.persistence.*;

@Entity(name = "member_section05_subsection02")
@Table(name = "tbl_member_section05_subsection02")
@Access(AccessType.PROPERTY)
public class Member {

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


    @Id
    public int getMemeberNo() { //다른건 필드접근하고 얘는 프로퍼티로할거야
        System.out.println("getMemberNo를 이용한 access 확인");
        return memeberNo;
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
        System.out.println("getMemberNamepwd로 access 되는지 확인");
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getNickName() {
        System.out.println("getMemberName닉넴로 access 되는지 확인");
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        System.out.println("getMemberName폰로 access 되는지 확인");
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
