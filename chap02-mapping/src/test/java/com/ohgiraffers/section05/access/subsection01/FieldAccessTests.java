package com.ohgiraffers.section05.access.subsection01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class FieldAccessTests {
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
    void 필드_접근_테스트(){

        Member member = new Member();

        member.setMemeberNo(1);
        member.setMemeberId("user1");
        member.setMemberPwd("pass01");
        member.setNickName("홍길동");
        member.setPhone("010-1234-5678");

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(member);
        tx.commit();


        Member foundMember = em.find(Member.class, 1);
        Assertions.assertEquals(member, foundMember);
        System.out.println(foundMember);
    }
}
