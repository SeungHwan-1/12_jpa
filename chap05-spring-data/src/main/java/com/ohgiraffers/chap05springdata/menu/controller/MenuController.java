package com.ohgiraffers.chap05springdata.menu.controller;

import com.ohgiraffers.chap05springdata.category.entity.Category;
import com.ohgiraffers.chap05springdata.category.service.CategoryService;
import com.ohgiraffers.chap05springdata.menu.dto.MenuDTO;
import com.ohgiraffers.chap05springdata.menu.entity.Menu;
import com.ohgiraffers.chap05springdata.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/select")
    public List<Menu> selectAllCategory() {
        List<Menu> categoryList = menuService.selectAllMenu();

        return categoryList;
    }

    @PostMapping("insert")
    public ResponseEntity insert(@RequestBody MenuDTO menuDTO) {

        Object result = menuService.insertMenu(menuDTO);

        if(result instanceof Menu) {
            Menu reponse = (Menu)result;
            return  ResponseEntity.ok(reponse);
        }
        return ResponseEntity.status(404).body(result);
    }

   @PostMapping("update")
    public ResponseEntity<Object> update(@RequestBody MenuDTO menuDTO) {

        Object result = menuService.updateMenu(menuDTO);

        return ResponseEntity.ok(result);
    }

    @PostMapping("delete")
    public ResponseEntity<Object> delete(@RequestBody MenuDTO menuDTO) {

        Object result = menuService.deleteMenu(menuDTO);
        
        return ResponseEntity.ok(result);
    }

}
