package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Configuration
public class UserJdbcRepository {

//    private final JdbcTemplate jdbcTemplate;
//    public UserRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isUserExist(long id){
        String sql = "select * from user where id = ?";
       return jdbcTemplate.query(sql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public boolean isUserExist(String name){
        String sql = "select * from user where name = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void saveUser(String name, Integer age){
        String sql = "insert into user (name, age) values (?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUsers(){

        String sql = "select * from user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });
    }

    public void updateUserName(String name, Long id){
        String updateSql = "update user set name = ? where id = ?";
        jdbcTemplate.update(updateSql, name, id);
    }

    public void deleteUser(String name){
        String deleteSql = "delete from user where name = ?";
        jdbcTemplate.update(deleteSql, name);
    }
}
