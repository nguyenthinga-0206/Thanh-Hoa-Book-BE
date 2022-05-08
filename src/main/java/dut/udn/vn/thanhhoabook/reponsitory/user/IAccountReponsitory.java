package dut.udn.vn.thanhhoabook.reponsitory.user;

import dut.udn.vn.thanhhoabook.model.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountReponsitory extends JpaRepository<Account, String> {
    Boolean existsByUsername(String username);

}
