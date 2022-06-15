package dut.udn.vn.thanhhoabook.service.order;

import dut.udn.vn.thanhhoabook.model.order.Cart;
import dut.udn.vn.thanhhoabook.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IcartService extends IService<Cart, Long> {
    List<Cart> getByUser(String user);

    Optional<Cart> getByBookId(String user, Long id);
}
