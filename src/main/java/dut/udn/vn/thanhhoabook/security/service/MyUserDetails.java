package dut.udn.vn.thanhhoabook.security.service;

import dut.udn.vn.thanhhoabook.model.user.Account;
import dut.udn.vn.thanhhoabook.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String fullName;

    private Account account;

    private String email;

    private String phone;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.fullName = user.getFullName();
        this.account = user.getAccount();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(user.getAccount().getRole().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    public String getPhone() {
        return phone;
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