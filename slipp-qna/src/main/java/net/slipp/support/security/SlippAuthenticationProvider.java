package net.slipp.support.security;

import javax.annotation.Resource;

import net.slipp.domain.user.PasswordMismatchException;
import net.slipp.domain.user.UserService;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SlippAuthenticationProvider implements AuthenticationProvider {
	@Resource (name = "userService")
	private UserService userService;
	
	@Resource (name = "userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        
        try {
			userService.login(userId, password);
		} catch (PasswordMismatchException e) {
			throw new BadCredentialsException(e.getMessage());
		}
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
		return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
