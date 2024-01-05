package ca.sheridancollege.mohamriz.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import ca.sheridancollege.mohamriz.repositories.SecurityRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Lazy
	private SecurityRepository secRepo;
	
	@Override
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ca.sheridancollege.mohamriz.beans.User user = secRepo.findUserAccount(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User " + username + " was not found.");
		}
		
		List<String> roleNames = secRepo.getRolesById(user.getUserId());
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if(roleNames != null) {
			for (String role : roleNames) {
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		
		User springUser = new User(user.getUsername(), user.getEncryptedPassword(), grantList);
		
		return springUser;
	}

}
