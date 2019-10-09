package org.adam.aliasswitcher.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Value("${user.adam}")
    String bcryptHashAdam;
    @Value("${user.erika}")
    String bcryptHashErika;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("adam".equals(username)) {
            return new User("adam", bcryptHashAdam,
                    new ArrayList<>());
        }
        else if ("erika".equals(username)) {
            return new User("erika", bcryptHashErika,
                    new ArrayList<>());

        }

        else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

