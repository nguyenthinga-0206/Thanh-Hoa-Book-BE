package dut.udn.vn.thanhhoabook.service.impl;

import dut.udn.vn.thanhhoabook.model.user.Account;
import dut.udn.vn.thanhhoabook.reponsitory.IAccountReponsitory;
import dut.udn.vn.thanhhoabook.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountReponsitory accountReponsitory;

    @Override
    public Boolean isUsernameExists(String username) {
        return accountReponsitory.existsByUsername(username);
    }

    @Override
    public Optional<Account> findById(String username) {
        return accountReponsitory.findById(username);
    }
}
