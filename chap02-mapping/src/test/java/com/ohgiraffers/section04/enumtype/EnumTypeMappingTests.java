package com.ohgiraffers.section04.enumtype;

import com.ohgiraffers.section04.enumtype.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class EnumTypeMappingTests {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    public static void init() {
        emf = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        em = emf.createEntityManager();
    }
    @AfterEach
    public void closeEntityManager() {
        em.close();
    }
    @AfterAll
    public static void closeManager() {
        emf.close();
    }
    @Test
    void enum타입_매핑_테스트(){
        Member member = new Member();

        member.setMemeberNo(1);
        member.setMemeberId("user1");
        member.setMemberPwd("pass01");
        member.setNickName("디아루가"); //닉네임빠짐
        member.setMemberRole(RoleType.ADMIN);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(member);
        tx.commit();

        Member foundMember = em.find(Member.class, member.getMemeberNo());
        Assertions.assertEquals(member.getMemeberNo(), foundMember.getMemeberNo());
    }
}
