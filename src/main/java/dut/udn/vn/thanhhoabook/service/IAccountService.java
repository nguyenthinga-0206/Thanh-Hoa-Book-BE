package dut.udn.vn.thanhhoabook.service;

import dut.udn.vn.thanhhoabook.model.user.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IAccountService {
    Boolean isUsernameExists(String username);

    Optional<Account> findById(String username);
}
