package com.ohgiraffers.section05.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class GroupFunctionTests {
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
** JPQL 의 그룹함수는 COUNT,MAX,MIN,SUM,AVG로 SQL의 그룹함수와
* 별반 차이가 없다
* 단, 몇가지 주의사항이 있다.
* 1. 그룹함수의 반환 타입은 결과값이 정수면 Long, 실수면  Double 로 반환된다.
* 2. 값이 없는 상태에서 count 를 제외한 그룹 함수는 null 이되고
* count 만 0이 된다. 따라서 반환 값을 담기 위해 선언하는
* 변수 타입을 기본 자료형으로 하게 되면 , 조회 결과를 언박싱 할 때 NPE 가 발생한다.
* 3. 그룹함수의 반환 자료형은 Long or Double 형 이기 때문에
* Having 절에서 그룹함수 결과 값을 비교하기 위한 파라미터 타입은
* Long or Double 로 해야한다.
 */

    @Test
    void 특정_카테고리에_등록된_메뉴수_조회(){
        int categoryCodeParameter = 4;

        String jpql = "SELECT COUNT(m.menuPrice) FROM menu_section05 m " +
                "WHERE m.categoryCode = :categoryCode";
        long countOfMenu = em.createQuery(jpql, Long.class).
                setParameter("categoryCode", categoryCodeParameter).getSingleResult();

        Assertions.assertTrue(countOfMenu > 0);
        System.out.println("countOfMenu = " + countOfMenu);

    }
    @Test
    void count_제외_조회결과_없을시_테스트(){

        int categoryCode = 2;

        String jpql ="SELECT SUM(m.menuPrice) FROM menu_section05 m " +
                "WHERE m.categoryCode = :categoryCode";
        Long sumOfPrice = em.createQuery(jpql, Long.class). //래퍼클래스로 바꾸면됨
                setParameter("categoryCode", categoryCode).getSingleResult();


        System.out.println("sumOfPrice = " + sumOfPrice);
    }

    @Test
    void groupby절과_having절을_사용한_조회_테스트(){
        //having 절에서의 파라미터는 Long 을 사용한다.
        //그룹함수의 반환타입이 Long 이므로...
        long minPrice = 10L;

        String jpql = "SELECT m.categoryCode, SUM(m.menuPrice) FROM menu_section05 m " +
                "GROUP BY m.categoryCode " +
                "HAVING SUM(m.menuPrice) >= :minPrice";

        List<Object[]> sumPriceOfCategoryList = em.createQuery(jpql,Object[].class)
                .setParameter("minPrice", minPrice).getResultList();

        Assertions.assertNotNull(sumPriceOfCategoryList);
        for (Object[] sumPriceOfCategory : sumPriceOfCategoryList) {
            for(Object obj1 : sumPriceOfCategory){
                System.out.println("obj1  " + obj1);
            }
        }
        for(Object[] sumPriceOfCategory : sumPriceOfCategoryList)
        {
            System.out.println(sumPriceOfCategory[0] + "의 총합: " + sumPriceOfCategory[1]);
        }
    }

}

