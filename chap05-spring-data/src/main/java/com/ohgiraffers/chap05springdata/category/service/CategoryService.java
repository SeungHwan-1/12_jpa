package com.ohgiraffers.chap05springdata.category.service;

import com.ohgiraffers.chap05springdata.category.entity.Category;
import com.ohgiraffers.chap05springdata.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> selectAllCategory() {

        List<Category> categoryList = categoryRepository.findAll();  //JPA
        if(categoryList.isEmpty()) {
            return null;
        }
        return categoryList;
    }

    public Category selectCategoryBycode(int categoryCode) {
        Category category = categoryRepository.findByCategoryCode(categoryCode);

        if(Objects.isNull(category)){
            return null;
        }
        return category;
    }

    public Category insertCategory(String categoryName) {

        //이름 중복 체크
        Category foundCategory = categoryRepository.findByCategoryName(categoryName);
        //있으면 널을 리턴(중복됬다는거니까 받아온게 카테고리찾으면)
        if(!Objects.isNull(foundCategory)){
            return null;

        }else {


            Category insertCategory = new Category();
            insertCategory.setCategoryName(categoryName);

            Category result = categoryRepository.save(insertCategory);
            /*
             * save() 는 jpa 에서 EntityManager 를 통해 데이터를 저장하는 메소드
             * 기본적으로 jpa 는 트랜젝션 내에서 자동으로 커밋을 처리한다.
             * 새로운 엔티티의 경우 : db에 저장하고 저장한 객체 반환
             * 기존에 존재하는 엔티티의 경우 : 해당 엔티티의 정보를 업데이트하고 업데이트한 객체 반환
             */
            return result;
        }
    }


    public Category updateCategory(int categoryCode, String categoryName) {
        //이름 중복 체크
        Category foundCategoryCode = categoryRepository.findByCategoryCode(categoryCode);

        //있으면 널을 리턴(중복됬다는거니까 받아온게 카테고리찾으면)
        if(Objects.isNull(foundCategoryCode)){ //있으면 이프문 없으면 엘스
            return null;

        }

            Category updateCategory = new Category();
            updateCategory.setCategoryName(categoryName);

            Category result = categoryRepository.save(updateCategory);

            return result;

    }

    public boolean deleteCategory(Integer categoryCode) {
        Category foundCategoryCode = categoryRepository.findById(categoryCode).orElse(null); //없을수있다 없을때 대처도해야함
        //여기에 담아와서
        if(foundCategoryCode == null){
            return false;
        }
        categoryRepository.delete(foundCategoryCode); //같은거 지우면
        return true;
    }

    public Integer findByCategory(int code) {

        Category foundCategory = categoryRepository.findByCategoryCode(code);

        if(Objects.isNull(foundCategory)){
            return null;
        }
        return foundCategory.getCategoryCode();

    }
}
