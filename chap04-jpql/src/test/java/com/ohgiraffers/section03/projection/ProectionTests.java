package com.ohgiraffers.section03.projection;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import javax.management.Query;
import javax.management.QueryExp;
import java.util.List;

public class ProectionTests {
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
    * 프로젝션(projection)
    *
    * SELECT 프로젝션 대상 FROM
    * 프로젝션은 4가지 방식이 있다.
    *
    * 1. 엔티티 프로젝션
    * -원하는 객체를 바로 조회할수있다
    * 조회된 엔티티는 영속성 컨텍스트가 관리한다.
    *
    * 2. 임베디드 타입 프로젝션
    * -엔티티와거의 비슷하게 사용되며 조회의 시작점이 될 수 없다.
    * -임베디드 타입은 영속성 컨텍스트에서 관리되지 않는다..
    *
    * 3. 스칼라 타입 프로젝션
    * -숫자,문자, 날짜 같은 기본 데이터 타입이다. (주로 db 기본 타입)
    * -스칼라 타입은 영속성 컨텍스트에서 관리하지 않는다.
    * (자바의 프리미티브 타입과 비슷한 개념)
    *
    * 4. new 명령어를 활용한 프로젝션
    * -다양한 종류의 단순 값들을 dto로 바로 조회하는 방식으로 new 패키지명.dto명
    * -을 쓰면 해당 dto로 바로 반환 받을 수 있다.
    * -new 명령어를 사용한 클래스의 객체는 엔티티가 아니므로 영속성 컨텍스트에서 관리되지 않는다.
     */

    //1. 엔티티 프로젝션
    @Test
    void 단일_엔티티_프로젝션_테스트(){

        String jpql = "SELECT m FROM menu_section03 m";
        List<Menu> menuList = em.createQuery(jpql, Menu.class).getResultList();

        Assertions.assertNotNull(menuList);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        menuList.get(1).setMenuName("메뉴이름 변경 테스트");
        tx.commit();

    }
    
    @Test
    void 양방향_연관관계_엔티티_프로젝션_테스트(){
        
        int menuCodeParameter = 3;
        
        String jpql = "SELECT m.category FROM bidirection_menu m WHERE m.menuCode=:menuCode";
        
        BiDirectionCategory category = em.createQuery(jpql,BiDirectionCategory.class)
                .setParameter("menuCode",menuCodeParameter)
                .getSingleResult();
        
        Assertions.assertNotNull(category);
        System.out.println("category = " + category);

        List<BiDirectionMenu> list = category.getMenuList();
        Assertions.assertNotNull(list);


    }

    @Test
    void 임베디드_프로젝션_테스트(){
        String jpql = "SELECT m.menuInfo FROM embedded_menu m";
        List<MenuInfo> menuList = em.createQuery(jpql, MenuInfo.class).getResultList();

        Assertions.assertNotNull(menuList);
        for (MenuInfo menuInfo : menuList) {
            System.out.println("menuInfo = " + menuInfo);
        }
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        menuList.get(2).setMenuName("메뉴이름 변경 테스트");
        tx.commit();
        Assertions.assertNotNull(menuList);
        for (MenuInfo menuInfo : menuList) {
            System.out.println("menuInfo = " + menuInfo);
        }

        /*
        * @Embeddable 은 데이터베이스에서 중복을 방지하는 역할은 하지 않으며
        * 중복을 방지하려면 테이블서 유니크 제약 추가
        *
        * @Embeddable 은 복합키를 표현할때 사용되며 해당 필드나
        * 조합이 유일해야하는 요구사항은 데이ㅂ터베이스 제약조건으로 해결
         */
    }
    //3. 스칼라 타입 프로젝션
    @Test
    void Typedquery_이용한_스칼라_타입_프로젝션_테스트(){
        String jpql = "SELECT c.categoryName FROM category_section03 c";
        List<String> categoryNameList = em.createQuery(jpql, String.class).getResultList();
        Assertions.assertNotNull(categoryNameList);

        for(String categoryName : categoryNameList){
            System.out.println("categoryName = " + categoryName);
        }

    }

    /*
    * 조회하려는 컬럼 값이 2개 이상인 경우 , TypedQuery로 반환 타입을 지정하지 못한다.
    * 그 때 Query를 사용하여 object[]로 받을 수 있다.
    *
    * 카테고리 코드 , 카테고리 네임 반환 받아서 출력해보기
     */

    @Test
    void xxx_xxx() {
        String jpql = "SELECT c.categoryCode, c.categoryName FROM category_section03 c";

        List<Object[]> categoryNameList = em.createQuery(jpql).getResultList();

        Assertions.assertNotNull(categoryNameList);

        for (Object a : categoryNameList) {
            Object[] arr = (Object[]) a;
            for(Object arr2: arr){
                System.out.println("arr2 = " + arr2);
            }
        }
    }
}