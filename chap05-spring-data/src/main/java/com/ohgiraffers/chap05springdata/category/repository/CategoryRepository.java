package com.ohgiraffers.chap05springdata.category.repository;

import com.ohgiraffers.chap05springdata.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                             // 카테고리 엔티티 아이디가 인티저 인티저로바꿔야함
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryCode(int categoryCode); // findBy는 고정이고 뒤에 코드로넘어가서

    Category findByCategoryName(String categoryName);
}
