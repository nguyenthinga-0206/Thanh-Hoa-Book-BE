package dut.udn.vn.thanhhoabook.service.impl;

import dut.udn.vn.thanhhoabook.model.user.User;
import dut.udn.vn.thanhhoabook.reponsitory.IUserReponsitory;
import dut.udn.vn.thanhhoabook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserReponsitory userReponsitory;

    @Override
    public Boolean isEmailExists(String email) {
        return userReponsitory.existsByEmail(email);
    }

    @Override
    public Boolean isPhoneExists(String phone) {
        return userReponsitory.existsByPhone(phone);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userReponsitory.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userReponsitory.findUserByDeleteFlagFalse();
    }

    @Override
    public Optional<User> getById(Integer id) {
        return userReponsitory.findById(id);
    }

    @Override
    public User save(User user) {
        return userReponsitory.save(user);
    }
}
