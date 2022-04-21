package dut.udn.vn.thanhhoabook.service;

import dut.udn.vn.thanhhoabook.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService {
    Boolean isEmailExists(String email);

    Boolean isPhoneExists(String phone);

    Optional<User> findByEmail(String email);
}
