package dut.udn.vn.thanhhoabook.security.service;

import dut.udn.vn.thanhhoabook.model.user.User;
import dut.udn.vn.thanhhoabook.service.impl.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return MyUserDetails.build(user);
    }

}
