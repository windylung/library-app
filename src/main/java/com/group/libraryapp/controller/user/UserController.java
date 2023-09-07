package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UpdateRequest;
import com.group.libraryapp.dto.user.request.UserRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @PostMapping("/user")
    void storeUser(@RequestBody UserRequest userRequest) {

        String sql = "insert into user (name, age) values (?, ?)";
        jdbcTemplate.update(sql, userRequest.getName(), userRequest.getAge());

    }

    @GetMapping("/user")
    List<UserResponse> getUser() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse((int) id, name, age);

            }
        });
    }

    @PutMapping("/user")
    public void updateName(@RequestBody UpdateRequest updateRequest) {

        String sql = "select * from user where id = ?";
        boolean isUserExist = jdbcTemplate.query(sql, (rs, rowNum) -> 0, updateRequest.getId()).isEmpty();

        if (updateRequest.getName() == "" || isUserExist) {
            throw new IllegalArgumentException();
        }
        String updateSql = "update user set name = ? where id = ?";
        jdbcTemplate.update(updateSql, updateRequest.getName(), updateRequest.getId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        String sql = "select * from user where name = ?";
        boolean isUserExist = jdbcTemplate.query(sql, (rs, rowNum) -> 0, name).isEmpty();

        if (isUserExist) {
            throw new IllegalArgumentException();
        }
        String deleteSql = "delete from user where name = ?";
        jdbcTemplate.update(deleteSql, name);
    }
}
