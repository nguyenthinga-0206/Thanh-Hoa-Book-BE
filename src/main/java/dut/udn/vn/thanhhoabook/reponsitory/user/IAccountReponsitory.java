package dut.udn.vn.thanhhoabook.reponsitory.user;

import dut.udn.vn.thanhhoabook.model.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountReponsitory extends JpaRepository<Account, String> {
    List<Account> findAccountByDeleteFlagFalse();

    Boolean existsByUsername(String username);
}
