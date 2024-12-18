package com.ohgiraffers.section03;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityLifeCycleTests {
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

    /*
    * 영속성 컨텍스트는 엔티티 메니저가 앤티티 객체를 저장하는 공간으로,
     앤티티 객체를 보관하고 정리한다.
     앤티티 메니저가 생성될때 하나의 영속성 컨텍스트가 만들어진다.
     앤티티의 생명주기
     비영속,영속,준영속
     */



    @Test
    void 비영속_테스트(){

        Menu foundMenu  = em.find(Menu.class,11);
        //객체만 생성하면, 영속성 컨텍스트나 디비와 관련없는 비영속 상태
        Menu newMenu = new Menu();
        newMenu.setMenuCode(foundMenu.getMenuCode());
        newMenu.setMenuName(foundMenu.getMenuName());
        newMenu.setMenuPrice(foundMenu.getMenuPrice());
        newMenu.setCategoryCode(foundMenu.getCategoryCode());
        newMenu.setOrderableStatus(foundMenu.getOrderableStatus());

        Assertions.assertFalse(foundMenu == newMenu);
        //다름
    }
    @Test
    void 영속성_연속_조회_테스트(){
        /*
        * 엔티티 매니저가 영속성 컨텍스트에 엔티티 객체를 저장(persist)하면
        * 영속성 컨텍스트가 엔티티 객체를 관리하게 되고 이를 영속 상태라고 한다.
        * find(), jpql을 사용한 조회도 영속 상태가 된다.'
        * */
        Menu foundMenu1  = em.find(Menu.class,11);
        Menu foundMenu2  = em.find(Menu.class,11);

        Assertions.assertTrue(foundMenu1 == foundMenu2);
        //같음
    }

    @Test
    void 영속성_객체_추가_테스트(){
        Menu menuToRegist = new Menu();
        menuToRegist.setMenuCode(500);
        menuToRegist.setMenuName("수박죽");
        menuToRegist.setCategoryCode(String.valueOf(1));
        menuToRegist.setOrderableStatus("Y");

        em.persist(menuToRegist);
        Menu foundMenu  = em.find(Menu.class,500); //디비에없어도 꺼내올수있다.
        Assertions.assertTrue(foundMenu == menuToRegist);
        //같음
    }
    @Test
    void 준영속_detach_테스트() {
        Menu foundMenu = em.find(Menu.class, 11);
        Menu foundMenu1 = em.find(Menu.class, 12);

        /*
         * 영속성 컨텍스트가 관리하던 엔티티 객체가 더 이상 관리되지 않는 상태
         * 로 전환된면(detach), 해당 객체는 준 영속 상태로 바뀐다.
         * 이는JPA 객체의 변경 사항을 데이터베이스에 자동 반영되지 않는 상태이다\\
         *
         * Detach 메소드를 사용하면 특정 엔티티를 준영속 상태로 만들 수 있다.
         * 즉, 원하는 객체만 선택적으로 영속성 컨텍스트에서 분리할수 있다.
         * */
        //데이터가 영속성 컨텍스트에 포함되어 있는지 확인
        em.detach(foundMenu1);
        foundMenu.setMenuPrice(String.valueOf(5000));
        foundMenu1.setMenuPrice(String.valueOf(5000));

                Assertions.assertEquals(String.valueOf(5000),em.find(Menu.class,11).getMenuPrice());
                em.merge(foundMenu1);
                 Assertions.assertEquals(String.valueOf(5000),em.find(Menu.class,12).getMenuPrice());


    }
    @Test
    void close_테스트(){
        Menu foundMenu = em.find(Menu.class, 11);
        Menu foundMenu2 = em.find(Menu.class, 12);

        em.close();

        foundMenu.setMenuPrice(String.valueOf(5000));
        foundMenu2.setMenuPrice(String.valueOf(5000));

        Assertions.assertEquals(String.valueOf(5000),
                em.find(Menu.class,11).getMenuPrice());
        Assertions.assertEquals(String.valueOf(5000),
                em.find(Menu.class,12).getMenuPrice());




    }
    @Test
    void 삭제_remove_테스트() {
        /*
         *  remove : 엔티티를 영속성 컨텍스트에서 삭제한다.
         * 트랜잭션을 커밋하는 순간 데이터베이스에 반영된다
         * */
        Menu foundMenu = em.find(Menu.class, 2);
        em.remove(foundMenu);

        Menu refoundMenu = em.find(Menu.class, 2); //지울걸로 알고있어서 안가져옴
        Assertions.assertEquals(2,foundMenu.getMenuCode()); //아직지우진않아서 괜춘
        Assertions.assertEquals(2,refoundMenu.getMenuCode());
    }
    @Test
    void 삭제2_테스트(){

        Menu refoundMenu = em.find(Menu.class, 2);
        Assertions.assertEquals(2,refoundMenu.getMenuCode());
    }
    @Test
    void 병합_merge_수정_테스트(){
        Menu menuToDetach = em.find(Menu.class, 3);
        em.detach(menuToDetach); //나눠서 다른개체
        menuToDetach.setMenuName("수박죽");

        Menu refoundMenu = em.find(Menu.class, 3);
        System.out.println(menuToDetach.hashCode());
        System.out.println(refoundMenu.hashCode());

        em.merge(menuToDetach);

        Menu mergedMenu = em.find(Menu.class, 3);
        Assertions.assertEquals("수박죽",mergedMenu.getMenuName());
    }
    /*@Test
    void 병합_merge_수정_테스트2(){
        Menu menuToDetach = em.find(Menu.class, 3);
        em.detach(menuToDetach); //나눠서 다른개체
        menuToDetach.setMenuCode(999);
        menuToDetach.setMenuName("수박죽");

        em.merge(menuToDetach);

        Menu mergedMenu = em.find(Menu.class, 999);
        Assertions.assertEquals("수박죽",mergedMenu.getMenuName());
    }*/
    @Test
    void 병합_merge_삽입_테스트(){
        Menu menuToDetach = em.find(Menu.class, 3);
        em.detach(menuToDetach);

        menuToDetach.setMenuCode(998);
        menuToDetach.setMenuName("수박죽");

        EntityTransaction et = em.getTransaction(); //머지할게없으면 새롭게 간다근데 오토인크리먼트가 더상위
        et.begin();
        em.merge(menuToDetach);
        et.commit();

        Menu mergedMenu = em.find(Menu.class, 998);
        Assertions.assertEquals("수박죽",mergedMenu.getMenuName());

    }
}


