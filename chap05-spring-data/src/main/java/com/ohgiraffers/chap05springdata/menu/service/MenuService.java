package com.ohgiraffers.chap05springdata.menu.service;

import com.ohgiraffers.chap05springdata.category.entity.Category;
import com.ohgiraffers.chap05springdata.menu.dto.MenuDTO;
import com.ohgiraffers.chap05springdata.menu.entity.Menu;
import com.ohgiraffers.chap05springdata.menu.infra.CategoryFind;
import com.ohgiraffers.chap05springdata.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired //느슨한 결합구조를 위해 서비스를 직접넣지않는다.
    private CategoryFind categoryFind;

    public List<Menu> selectAllMenu() {
        List<Menu> MenuList = menuRepository.findAll();  //JPA
        if(MenuList.isEmpty()) {
            return null;
        }
        return MenuList;
    }

    public Object insertMenu(MenuDTO menuDTO) {
        //메뉴이름 중복체크
        Menu menu = menuRepository.findByMenuName(menuDTO.getMenuName());
        if(!Objects.isNull(menu)){
            return new String(menuDTO.getMenuName() + "메뉴가 존재함.");


        }
        //가격 유효성 검사
        if(menuDTO.getMenuPrice() <= 0){
            return new String(menuDTO.getMenuPrice() + "가격이 잘못 입력됨");
        }
        //카테고리 코드 검사
        Integer categoryCode = categoryFind.getCategory((menuDTO.getCategoryCode()));

        if(Objects.isNull(categoryCode))
        {
            return menuDTO.getCategoryCode()+"는 존재하지 않습니다.";

        }
        Menu newMenu = new Menu();
        newMenu.setMenuName(menuDTO.getMenuName());
        newMenu.setCategoryCode(categoryCode);
        newMenu.setMenuPrice(menuDTO.getMenuPrice());
        newMenu.setOrderableStatus(menuDTO.getOrderableStatus());

        Menu result = menuRepository.save(newMenu);

        return result;
    }

    public Object updateMenu(MenuDTO menuDTO) {

        Menu menu = menuRepository.findByMenuCode(menuDTO.getMenuCode());
        Integer categoryCode = categoryFind.getCategory((menuDTO.getCategoryCode()));

        menu.setMenuName(menuDTO.getMenuName());
        menu.setCategoryCode(categoryCode);
        menu.setMenuPrice(menuDTO.getMenuPrice());
        menu.setOrderableStatus(menuDTO.getOrderableStatus());

        Menu result = menuRepository.save(menu);

        return result;

    }

    public Object deleteMenu(MenuDTO menuDTO) {
        Menu menu = menuRepository.findByMenuName(menuDTO.getMenuName());
        if (menu != null) {
            menuRepository.delete(menu);
        }
        return null;
    }
}
