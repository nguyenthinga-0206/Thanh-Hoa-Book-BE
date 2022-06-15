package dut.udn.vn.thanhhoabook.service.impl.user;

import dut.udn.vn.thanhhoabook.dto.user.ChangePasswordRequest;
import dut.udn.vn.thanhhoabook.model.user.User;
import dut.udn.vn.thanhhoabook.reponsitory.user.IUserReponsitory;
import dut.udn.vn.thanhhoabook.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserReponsitory userReponsitory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Boolean isEmailExists(String email) {
        return userReponsitory.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userReponsitory.findByEmail(email);
    }

    @Override
    public Boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        Optional<User> userOptional = userReponsitory.findByEmail(changePasswordRequest.getEmail());
        return userOptional.map(user -> {
            if (passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getAccount().getPassword())) {
                user.getAccount().setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                userReponsitory.save(user);
                return true;
            }
            return false;
        }).orElse(false);
    }

    @Override
    public List<User> getAll() {
        return userReponsitory.findUserByDeleteFlagFalse();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userReponsitory.findById(id);
    }

    @Override
    public User save(User user) {
        user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
        return userReponsitory.save(user);
    }
}
