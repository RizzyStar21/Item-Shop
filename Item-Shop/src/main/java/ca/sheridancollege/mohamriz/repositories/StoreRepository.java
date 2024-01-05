package ca.sheridancollege.mohamriz.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import ca.sheridancollege.mohamriz.beans.Store;

@Repository
public class StoreRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void addItem(Store store) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String query = "insert into Store (name, price, quantity) values (:name, :price, :quan)";
		param.addValue("name", store.getName());
		param.addValue("price", store.getPrice());
		param.addValue("quan", store.getQuantity());
		jdbc.update(query, param);
	}
	
	public ArrayList<Store> getStoreItems(){
		ArrayList<Store> items = new ArrayList<Store>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM Store";
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			Store s = new Store();
			s.setId((long)row.get("mainId"));
			s.setName((String)row.get("name"));
			s.setPrice((double)row.get("price"));
			s.setQuantity((int)row.get("quantity"));
			items.add(s);
		}
		return items;
	}
	
	public Store getItemById(long id) {
		ArrayList<Store> items = new ArrayList<Store>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM store WHERE mainId=:id";
		parameters.addValue("id", id);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			Store s = new Store();
			s.setId((long)row.get("mainId"));
			s.setName((String)row.get("name"));
			s.setPrice((double)row.get("price"));
			s.setQuantity((int)row.get("quantity"));
			items.add(s);
		}
		if(items.size() == 1) {
			return items.get(0);
		}else {
			return null;
		}
	}
	
	public void editItem(Store store) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String query = "UPDATE store SET name=:name, price=:price, quantity=:quan WHERE mainId=:id";
		param.addValue("id", store.getId());
		param.addValue("name", store.getName());
		param.addValue("price", store.getPrice());
		param.addValue("quan", store.getQuantity());
		jdbc.update(query, param);
	}
	
	public void deleteItem(long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM store where mainId=:id";
		parameters.addValue("id", id);
		jdbc.update(query, parameters);
	}
	
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
