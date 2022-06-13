package dut.udn.vn.thanhhoabook.service.impl.order;

import dut.udn.vn.thanhhoabook.model.order.Cart;
import dut.udn.vn.thanhhoabook.reponsitory.order.ICartReponsitoty;
import dut.udn.vn.thanhhoabook.service.order.IcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements IcartService {
    @Autowired
    private ICartReponsitoty cartReponsitoty;

    @Override
    public List<Cart> getAll() {
        return cartReponsitoty.findCartByDeleteFlagFalse();
    }

    public List<Cart> getByUser(String user) {
        return cartReponsitoty.getByUser(user);
    }

    @Override
    public Optional<Cart> getById(Long id) {
        return cartReponsitoty.findById(id);
    }

    public Optional<Cart> getByBookId(String user, Long id) {
        return cartReponsitoty.findCartByDeleteFlagFalseAndUserCreateFlagAndBook_Id(user, id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartReponsitoty.save(cart);
    }
}
