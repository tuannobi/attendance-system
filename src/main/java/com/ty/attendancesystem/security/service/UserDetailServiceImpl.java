package com.ty.attendancesystem.security.service;

import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public UserDetailServiceImpl(UserRepository userRepository){
    this.userRepository=userRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = null;
    try {
      user = (User) userRepository.findByUsername(username)
          .orElseThrow(() ->
              new UsernameNotFoundException("User Not Found with -> username or email : " + username)
          );
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }

    return UserDetailsImpl.build(user);
  }
}
