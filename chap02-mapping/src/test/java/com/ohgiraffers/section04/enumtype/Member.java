package com.ohgiraffers.section04.enumtype;

import jakarta.persistence.*;

@Entity(name = "member_section04")
@Table(name = "tbl_member_section04")
public class Member {
    @Id
    @Column(name = "member_no")
    private int memeberNo;

    @Column(name = "member_id")
    private String memeberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nick_name")
    private String nickName;


    @Column(name = "member_role")
   // @Enumerated(EnumType.ORDINAL)
     @Enumerated(EnumType.STRING) //설정된 상수 필드의 이름으로 db에 들어감
    private RoleType memberRole;

    @Override
    public String toString() {
        return "Member{" +
                "memeberNo=" + memeberNo +
                ", memeberId='" + memeberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", memberRole=" + memberRole +
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

    public RoleType getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(RoleType memberRole) {
        this.memberRole = memberRole;
    }

    public Member() {
    }

    public Member(int memeberNo, String memeberId, String memberPwd, String nickName, RoleType memberRole) {
        this.memeberNo = memeberNo;
        this.memeberId = memeberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
        this.memberRole = memberRole;
    }
}
