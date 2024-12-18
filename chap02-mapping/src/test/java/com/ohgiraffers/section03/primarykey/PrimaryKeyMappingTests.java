package com.ohgiraffers.section03.primarykey;

import com.ohgiraffers.section03.primarykey.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class PrimaryKeyMappingTests {
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
    /*
    * PrimaryKey에는 @Id 어노테이션과 @GeneratedValue 어노테이션을 사용한다.
    * @Id 어노테이션은 엔티티 클래스에서 Primary Key 역할을 하는 필드를 지정할 때 사용한다.
    * 데이터 베이스마다 기본 키를 생성하는 방식이 서로 다르다.
    * @GeneratedValue 는 다음 과 같은 속성을 지니고있다.
    *
    * - strategy : 자동 생성 전략을 지정
    * -GenerationType.INDENTITY : 기본 키 생성을 데이터 베이스에 위임 mysql의 오토인크리먼트
    * -GenerationType.SEQUENCE : 데이터베이스 시퀸스 객체 사용 (오라클의 SEQUENCE) //똑같음 오라클은 이것만
    * -GenerationType.TABLE : 키 생성 테이블 사용 거의안씀
    * GenerationType.AUTO : 자동 선택 디비맞게 오라클껀 오라클꺼 마이에스큐엘은 마이에스큐엘
    * */

    @Test
    public void 식별자_매핑_테스트() {

        Member member = new Member();


        member.setMemeberId("user1");
        member.setMemberPwd("pass01");
        member.setNickName("디아루가"); //닉네임빠짐
        member.setPhone("010-1234-5678");

        Member member2 = new Member();


        member2.setMemeberId("user2");
        member2.setMemberPwd("pass02");
        member2.setNickName("펄기아"); //닉네임빠짐
        member2.setPhone("010-1233-5678");


        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(member); //하나의 한번만 가능
        em.persist(member2);
        tx.commit();

        String jpql = "SELECT A.memeberNo FROM member_section03 A";
        List<Integer> memberNos =
                em.createQuery(jpql, Integer.class).getResultList();
        System.out.println("memberNos = " + memberNos);

        //크리에이트 싹밀고 생성
    }
}
