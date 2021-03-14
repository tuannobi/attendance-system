package com.ty.attendancesystem.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ty.attendancesystem.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
  private Long id;

  private String username;

  @JsonIgnore
  private String password;

  private String email;

  private Collection authorities;

  public UserDetailsImpl(Long id, String username, String password, String email, Collection authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user){
    List authorities = user.getRoles().stream().map(role ->
        new SimpleGrantedAuthority(role.getName())
    ).collect(Collectors.toList());
    return new UserDetailsImpl(user.getId(),
        user.getUsername(),
        user.getPassword(),
        user.getEmail(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
