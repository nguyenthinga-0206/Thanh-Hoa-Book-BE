package dut.udn.vn.thanhhoabook.security.service;

import dut.udn.vn.thanhhoabook.model.user.Account;
import dut.udn.vn.thanhhoabook.model.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String fullName;

    private Account account;

    private String email;

    private String image;

    private String role;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.fullName = user.getFullName();
        this.account = user.getAccount();
        this.image = user.getImage();
        this.role = user.getAccount().getRole().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
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
