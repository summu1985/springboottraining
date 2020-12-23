package com.sanaari.jdbcdemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdbcUserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return jdbcTemplate.query("select * from users",new UserRowMapper());
	}
	
	@SuppressWarnings("deprecation")
	@Transactional(readOnly=true)
	public User findUserById(int id) {
		return (User) jdbcTemplate.queryForObject("select * from users where id=?", new Object[]{id}, new UserRowMapper());
	}
	
	public User create(final User user) {
		final String sql = "insert into users(name,email) values(?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement preparedStatment = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
				preparedStatment.setString(1,user.getName());
				preparedStatment.setString(2, user.getEmail());
				return preparedStatment;
			}
			
		}, holder);
		
		int newUserId = holder.getKey().intValue();
		user.setId(newUserId);
		return user;
	}
}
