package com.ym.springbootdemo.controller;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.ym.springbootdemo.Response;
import com.ym.springbootdemo.model.User;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 代表这个类会声明一些REST接口
@RestController
// 声明这个类下面的所有接口都有一个公共前缀/user
@RequestMapping("/user")
public class UserController {

    // 声明这个接口是一个POST方法的接口, url是/add, 结合公共前缀, 实际url = /user/add
    @PostMapping("/add")
    // @RequestBody代表被修饰的这个对象是请求体, 一个接口里只能声明一个@RequestBody
    public Response addUser(@RequestBody User user) throws SQLException {
        // hutool的db工具会把变动行数作为返回值返回
        Db.use().insert(
                Entity.create("user")
                        .set("name", user.getName())
                        .set("age", user.getAge())
        );
        return Response.ok(null);
    }

    @GetMapping("/query")
    public Response queryUser(Integer id, String name) throws SQLException {
        Entity queryCondition = Entity.create("user");
        if (id != null) {
            queryCondition.set("id", id);
        }
        if (name != null) {
            queryCondition.set("name", name);
        }
        List<Entity> entityList = Db.use().findAll(queryCondition);
        List<User> userList = new ArrayList<>();
        for (Entity entity : entityList) {
            User user = new User();
            user.setId(entity.getLong("id"));
            user.setName(entity.getStr("name"));
            user.setAge(entity.getInt("age"));
            userList.add(user);
        }
        return Response.ok(userList);
    }

}
