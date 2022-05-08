package dut.udn.vn.thanhhoabook.service.user;

import dut.udn.vn.thanhhoabook.model.user.User;
import dut.udn.vn.thanhhoabook.service.IService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService extends IService<User, Long> {
    Boolean isEmailExists(String email);

    Boolean isPhoneExists(String phone);

    Optional<User> findByEmail(String email);
}
