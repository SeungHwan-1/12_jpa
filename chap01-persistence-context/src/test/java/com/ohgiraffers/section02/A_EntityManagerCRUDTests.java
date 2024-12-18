package com.ohgiraffers.section02;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerCRUDTests {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    public static void init() {
        emf = Persistence.createEntityManagerFactory("jpatest");
    }
    @BeforeEach
    public void initEntityManager() {
        em = emf.createEntityManager();
    }
    @AfterAll
    public static void closeEntityManager() {
        em.close();
    }
    @AfterEach
    public void closeEntityManagerFactory() {
        emf.close();
    }
    @Test
    public void 메뉴코드로_메뉴조회_테스트(){
        int menuCode = 1;

        Menu foundMenu = em.find(Menu.class,menuCode);

        System.out.println("foundMenu = " + foundMenu);
        Assertions.assertNotNull(foundMenu);
    }
    @Test
    public void 새로운_메뉴_추가_테스트(){
        Menu menu = new Menu();
        menu.setMenuName("jpa 테슽느 메뉴");
        menu.setMenuPrice(String.valueOf(5000));
        menu.setCategoryCode(String.valueOf(1));
        menu.setOrderableStatus("Y");

        //데이터베이스의 상태 변화를 하나의 단위로 묶어주는 기능을 할 객체
        EntityTransaction entityTransaction = em.getTransaction();

        entityTransaction.begin();//트랜잭션 활성화

        // 엔티티 매니저를 사용해 영속성 컨텍스트에 추가
       try {
           em.persist(menu);  // 메모리 단계의 저장 영속성
           entityTransaction.commit(); //디비에 명령을 넣음
       }catch (Exception e){
           entityTransaction.rollback();
           e.printStackTrace();
       }
       //데이터가 영속성 컨텍스트에 포함되어 있는지 확인
        Assertions.assertTrue(em.contains(menu));



    }
    @Test
    public void 메뉴_이름_수정_테스트(){

        Menu menu =em.find(Menu.class,2);
        System.out.println("menu = " + menu);

        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();

        try{
            menu.setMenuName("쿠쿠렐라");
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }

        Assertions.assertEquals("쿠쿠렐라", /*얘랑얘랑같으면 통과*/
                em.find(Menu.class,2).getMenuName());
    }
    @Test
    public void 메뉴_삭제하기_테스트(){

        Menu menuToRemove = em.find(Menu.class,2);

        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();

        try{
            em.remove(menuToRemove);
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }
        Menu removedMenu = em.find(Menu.class,2);
        Assertions.assertNull(removedMenu);
    }

}
