package ca.sheridancollege.mohamriz.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.mohamriz.beans.User;

@Repository
public class SecurityRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public User findUserAccount(String userName) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String query = "SELECT * FROM SEC_USER WHERE userName=:user";
		param.addValue("user", userName);
		ArrayList<User> users = (ArrayList<User>) jdbc.query(query, param, new BeanPropertyRowMapper<User>(User.class));

		if (!users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}
	}


	public List<String> getRolesById(long userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String query = "SELECT user_role.userId, sec_role.roleName "
				+ "FROM user_role, sec_role WHERE user_role.roleId=sec_role.roleId" + " and userId=:id";
		param.addValue("id", userId);

		List<Map<String, Object>> rows = jdbc.queryForList(query, param);
		ArrayList<String> roles = new ArrayList<String>();
		for (Map<String, Object> row : rows) {
			String role = (String) row.get("roleName");
			roles.add(role);
		}
		return roles;
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void createNewUser(String username, String password) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String query = "insert into SEC_User (userName, encryptedPassword, ENABLED) "
				+ "values (:userName , :encryptedPassword, 1)";
		param.addValue("userName", username);
		param.addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, param);
	}

	public void addRole(long userId, long roleId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String query = "insert into user_role (userId, roleId) " + "values (:userId, :roleId)";
		param.addValue("userId", userId);
		param.addValue("roleId", roleId);
		jdbc.update(query, param);
	}
}
