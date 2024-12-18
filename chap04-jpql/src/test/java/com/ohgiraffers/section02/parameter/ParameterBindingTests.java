package com.ohgiraffers.section02.parameter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Scanner;

public class ParameterBindingTests {
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
    * 파라미터를 바인딩 하는 방벙
    * 1. 이름 기준 파라미터 ':' 다음에 이름 기준 파라미터를 지정한다.
    * 2. 위치 기준 파라미터 '?' 다음에 값을 주고 위치 값은 1부터 시작한다.
    *
     */

    @Test
    void 이름_기준_파라미터_바인딩_메뉴_조회_테스트(){

        String menuNameParameter = "한우딸기국밥";

        String jpql = "SELECT m FROM menu_section02 m WHERE m.menuName = :menuName ";
        List<Menu> menuList = em.createQuery(jpql, Menu.class)
                .setParameter("menuName", menuNameParameter)
                .getResultList();

        Assertions.assertNotNull(menuList);
        for (Menu menu : menuList) {
            System.out.println(menu);
        }
    }
    @Test
    void 위치_기준_파라미터_바인딩_메뉴_목록_조회_테스트(){

        String menuNameParameter = "한우딸기국밥";

        String jpql = "SELECT m FROM menu_section02 m WHERE m.menuName = ?1 ";

        List<Menu> menuList = em.createQuery(jpql, Menu.class)
                .setParameter(1,menuNameParameter)
                .getResultList();
        Assertions.assertNotNull(menuList);
        for (Menu menu : menuList) {
            System.out.println(menu);
        }

    }


    @Test
    void 메뉴_이름_입력시_포함된_메뉴_조회(){

        String jpql = "SELECT m FROM menu_section02 AS m WHERE m.menuName LIKE :KEY ";
        List<Menu> foundMenu = em.createQuery(jpql,Menu.class).setParameter("KEY","%마늘%").getResultList();

        Assertions.assertNotNull(foundMenu);
        for(Menu menu : foundMenu) {
            System.out.println("menu = " + menu);
        }
    }
}
