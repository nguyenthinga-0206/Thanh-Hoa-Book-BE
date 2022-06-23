package dut.udn.vn.thanhhoabook.security.service;

import dut.udn.vn.thanhhoabook.model.user.Account;
import dut.udn.vn.thanhhoabook.model.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Account account;

    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(String email, Account account, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.account = account;
        this.authorities = authorities;
    }

    public static MyUserDetails build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getAccount().getRole().toString()));

        return new MyUserDetails(
                user.getEmail(),
                user.getAccount(),
                authorities);
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
        return this.account.getEnable();
    }
}
