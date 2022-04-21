package dut.udn.vn.thanhhoabook.reponsitory;

import dut.udn.vn.thanhhoabook.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserReponsitory extends JpaRepository<User, Integer> {
    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Optional<User> findByEmail(String email);
}
