package com.ohgiraffers.section06.join;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class JoinTests {
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
    public void closeEntityManager() {em.close();}

    @AfterAll
    public static void closeManager() { emf.close(); }

    /*
    * 조인의 종류
    * 1.  일반 조인 : 일반적인 sql 조인을 의미
    * 2. 패치 조인 : JPQL 에서 성능최적화를 위해 제공하는 기능으로 연관 된 엔티티나 컬렉션을
    * 한 번에 조회할 수 있다.
     */
    @Test
    void 내부조인을_이용한_조회_테스트(){

        String jpql = "SELECT m FROM menu_section06 m JOIN m.category c";
        List<Menu> menuList = em.createQuery(jpql, Menu.class).getResultList();

        Assertions.assertNotNull(menuList);
        for (Menu menu : menuList) {
            System.out.println(menu);
        }
    }
    @Test
    void RIGHT_JOIN_테스트() {

        String jpql = "SELECT m.menuName, c.categoryName FROM menu_section06 m RIGHT JOIN m.category c" +
                " ORDER BY m.category.categoryCode";

        List<Object[]> menuList = em.createQuery(jpql, Object[].class).getResultList();

        Assertions.assertNotNull(menuList);
        for (Object[] menu : menuList) {
            for (Object menuName : menu) {
                System.out.println(menuName);
            }
        }

    }


        @Test
                void fetchJoin() {
            String jpql = "SELECT m FROM menu_section06 m JOIN FETCH m.category c";
            List<Menu> menuList = em.createQuery(jpql, Menu.class).getResultList();

            Assertions.assertNotNull(menuList);
            for (Menu menu : menuList)
                System.out.println(menu);
            //영속화도되고 성능도좋고

        }
    /*
    * 페치 조인을 하면 처음 sql 실행 후 로딩할 때 조인 결과를 다 조회한 뒤에 사용하는 방식
    * 그래서 쿼리 실행 횟수가 줄어든다.
    * 대부분의 경우 성능이 향상된다.
     */




}
