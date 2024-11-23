package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("根据emp.username和emp.password查询员工是否存在{}", emp);
        Emp e=empService.login(emp);
        if(e!=null){
            //生成jwt令牌
            Map<String, Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            claims.put("password",e.getPassword());
            String s = JwtUtils.generateJwt(claims);
            return Result.success(s);
        }
        return Result.error("用户名或密码错误");
    }
}
