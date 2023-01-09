package com.example.shopping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ShoppingController {

    /**
     * home
     */
    @RequestMapping("/")
    public String home(){
        //permit-all
        //http://localhost:3001
        return "shopping/home";
    }

    /**
     * 로그인한 사용자에게만 허용
     * createItemForm
     */
    @GetMapping("/items/new")
    public String create(){
        //onlyUsers
        return "shopping/createItemForm";
    }


    /**
     * 로그인하지 않은 사용자에게도 허용
     * hello
     */
    @GetMapping("/shopping/hello")
    public String hello(){
        //permit-all
        return "/shopping/hello";
    }




    //TODO: role 권한 설정
//    @RequestMapping("/authenticate/role/admin")
//    public String roleTest3() {
//        //http://localhost:3001/only-role-admin
//        return "Admin Role을 가지고 있는 사용자만 접근 가능합니다";
//    }
//
//    @RequestMapping("/authenticate/role/user")
//    public String roleTest4() {
//        //http://localhost:3001/only-role-user
//        return "User Role을 가지고 있는 사용자만 접근 가능합니다";
//    }

}
