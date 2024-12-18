package com.ohgiraffers.section02.column;

import com.ohgiraffers.section02.column.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class ColumnMappingTests {
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
    public void 컬럼에서_사용하는_속성() {

        Member member = new Member();

        member.setMemeberNo(2);
        member.setMemeberId("user1");
        member.setMemberPwd("pass01");
        member.setNickName("홍길동"); //닉네임빠짐
        member.setPhone("010-1234-5678");
        member.setAddress("서울시 서초구");
        member.setEmail("cookie3013@naver.com");

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(member);
        tx.commit();

        Member foundMember = em.find(Member.class, member.getMemeberNo());
        Assertions.assertEquals(member.getMemeberNo(), foundMember.getMemeberNo());
        //크리에이트 싹밀고 생성
    }
}
