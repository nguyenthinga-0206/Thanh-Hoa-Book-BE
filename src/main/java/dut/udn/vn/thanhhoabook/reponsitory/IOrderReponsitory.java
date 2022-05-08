package dut.udn.vn.thanhhoabook.reponsitory;

import dut.udn.vn.thanhhoabook.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderReponsitory extends JpaRepository<Orders, Integer> {
    List<Orders> findOrdersByDeleteFlagFalse();
}
