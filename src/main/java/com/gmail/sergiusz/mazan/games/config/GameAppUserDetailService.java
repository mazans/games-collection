package com.gmail.sergiusz.mazan.games.config;

import com.gmail.sergiusz.mazan.games.dao.GameUserDao;
import com.gmail.sergiusz.mazan.games.model.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class GameAppUserDetailService implements UserDetailsService {

    private GameUserDao gameUserDao;

    @Autowired
    private void setGameUserDao(GameUserDao gameUserDao) {
        this.gameUserDao = gameUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GameUser gameUser = gameUserDao.getByUsername(username);

        if(gameUser == null)
            throw new UsernameNotFoundException("Cannot find a gameUser with username: " + username);

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        return new User(gameUser.getUsername(), gameUser.getPassword(), authorities);
    }
}
