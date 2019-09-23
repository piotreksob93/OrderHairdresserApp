package com.piotrek.apps.orderHaircutApp.services;

import com.piotrek.apps.orderHaircutApp.dto.UserDto;
import com.piotrek.apps.orderHaircutApp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(UserDto userDto);
}
