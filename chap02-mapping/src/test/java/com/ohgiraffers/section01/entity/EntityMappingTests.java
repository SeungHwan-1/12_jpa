package com.ohgiraffers.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class EntityMappingTests {

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
    void 테이블_만들기_테스트(){ // z커밋안해도 만들어짐
        Member member = new Member();

        member.setMemeberNo(1);
        member.setMemeberId("user1");
        member.setMemberPwd("pass01");
        member.setNickName("홍길동");
        member.setPhone("010-1234-5678");

        em.persist(member);

        Member foundMember = em.find(Member.class, member.getMemeberNo());
        Assertions.assertEquals(member.getMemeberNo(), foundMember.getMemeberNo());

        /*
        Commit 하지 않았기 때문에 dml은 db에 등록되지 않았지만, ddl 구문은
        autoCommit 이기 때문에 테이블은 생성되어 있다.
        생성되는 컬럼의 순서는 pk 가 우선이며,
        일반 컬럼은 유니코드 오름차순으로 생성된다.
         */
    }
}
