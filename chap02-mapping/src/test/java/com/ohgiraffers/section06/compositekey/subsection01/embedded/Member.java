

import com.ohgiraffers.section06.compositekey.subsection01.embedded.MemberPK;
import jakarta.persistence.*;

@Entity(name = "member_section06_subsection01")
@Table(name = "tbl_member_section06_subsection01")
@IdClass(MemberPK.class)
public class Member
{

    @Id
    @Column(name = "member_no")
    private int member_no;

    @Id
    @Column(name = "member_id")
    private String member_id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    public Member() {
    }

    public Member(int member_no, String member_id, String phone, String address) {
        this.member_no = member_no;
        this.member_id = member_id;
        this.phone = phone;
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "member_no='" + member_no + '\'' +
                ", member_id='" + member_id + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

