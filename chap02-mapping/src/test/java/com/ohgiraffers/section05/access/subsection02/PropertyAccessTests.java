package com.ohgiraffers.section05.access.subsection02;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class PropertyAccessTests {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    public static void init() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("jpatest");
        }
    }

    @BeforeEach
    public void initManager() {
        if (em == null) {
            em = emf.createEntityManager();
        }
    }
    @AfterEach
    public void closeEntityManager() {
        if (em != null) {
            em.close();
        }
    }
    @AfterAll
    public static void closeManager() {
        if (em != null) {
            emf.close();
        }
    }
    @Test
    void 프로퍼티_접근_테스트(){

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

        String jpql =
                "SELECT memeberId FROM member_section05_subsection02 WHERE memberNo = 1";
        String registedId = em.createQuery(jpql,String.class).getSingleResult();
        System.out.println(registedId);
        Assertions.assertEquals("user1", registedId);

    }
}
