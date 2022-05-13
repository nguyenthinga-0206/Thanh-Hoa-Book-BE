package dut.udn.vn.thanhhoabook.reponsitory.user;

import dut.udn.vn.thanhhoabook.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserReponsitory extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Optional<User> findByEmail(String email);

    List<User> findUserByDeleteFlagFalse();
}
