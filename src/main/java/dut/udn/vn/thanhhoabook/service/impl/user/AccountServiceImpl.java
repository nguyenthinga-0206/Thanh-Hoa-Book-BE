package dut.udn.vn.thanhhoabook.service.impl.user;

import dut.udn.vn.thanhhoabook.model.user.Account;
import dut.udn.vn.thanhhoabook.reponsitory.user.IAccountReponsitory;
import dut.udn.vn.thanhhoabook.service.user.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {
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

    @Override
    public List<Account> getAll() {
        return accountReponsitory.findAccountByDeleteFlagFalse();
    }

    @Override
    public Optional<Account> getById(String id) {
        return accountReponsitory.findById(id);
    }

    @Override
    public Account save(Account account) {
        return accountReponsitory.save(account);
    }
}
