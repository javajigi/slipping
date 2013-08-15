package net.slipp.support.security;

import javax.annotation.Resource;

import net.slipp.domain.user.User;
import net.slipp.repository.user.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 로그인 사용자를 관리한다.
 */
public class SessionService {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    public User getLoginUser() {
        if (!isAuthenticated()) {
            return null;
        }
        
        return userRepository.findOne(getAuthenticatedUserName());
    }
    
    public boolean isAuthenticated() {
        return getAuthentication() == null ? false : getAuthentication().isAuthenticated();
    }
    
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private String getAuthenticatedUserName() {
        return getAuthentication() == null ? null : getAuthentication().getName();
    }
}
