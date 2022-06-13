package dut.udn.vn.thanhhoabook.reponsitory.order;

import dut.udn.vn.thanhhoabook.model.order.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartReponsitoty extends JpaRepository<Cart, Long> {
    List<Cart> findCartByDeleteFlagFalse();
}
