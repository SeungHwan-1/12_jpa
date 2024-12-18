package com.ohgiraffers.section06.compositekey.subsection02.idclass;

import java.io.Serializable;
import java.util.Objects;

// 임베디드 될 수 있는 복합키 타입을 지정할 때 사용하는 어노테이션
public class MemberPK implements Serializable
{
    private int member_no;

    private String member_id;

    public MemberPK() {
    }

    public MemberPK(int member_no, String member_id) {
        this.member_no = member_no;
        this.member_id = member_id;
    }

    public int getMember_no() {
        return member_no;
    }

    public void setMember_no(int member_no) {
        this.member_no = member_no;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    @Override
    public String toString() {
        return "MemberPK{" +
                "member_no=" + member_no +
                ", member_id='" + member_id + '\'' +
                '}';
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(member_no, member_id);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null || getClass() != obj.getClass())
            return false;

        MemberPK memberPK = (MemberPK) obj;
        return member_no == memberPK.member_no && Objects.equals(member_id, memberPK.member_id);
    }
}

