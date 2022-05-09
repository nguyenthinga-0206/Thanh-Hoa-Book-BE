package dut.udn.vn.thanhhoabook.service.user;

import dut.udn.vn.thanhhoabook.model.user.Account;
import dut.udn.vn.thanhhoabook.service.IService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IAccountService extends IService<Account, String> {
    Boolean isUsernameExists(String username);

    Optional<Account> findById(String username);
}
