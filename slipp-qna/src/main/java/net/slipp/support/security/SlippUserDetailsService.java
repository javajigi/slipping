package net.slipp.support.security;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import net.slipp.repository.user.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SlippUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(SlippUserDetailsService.class);
	
	@Resource (name = "userRepository")
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("loadUserByUsername : {}", username);
		
		net.slipp.domain.user.User user = userRepository.findOne(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + " does not found!");
		}
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		authorities.add(authority);
		
		return new User(username, user.getPassword(), true, true, true, true, authorities);
	}
}
