package dut.udn.vn.thanhhoabook.reponsitory.order;

import dut.udn.vn.thanhhoabook.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderReponsitory extends JpaRepository<Orders, Long> {
    List<Orders> findOrdersByDeleteFlagFalse();
}
