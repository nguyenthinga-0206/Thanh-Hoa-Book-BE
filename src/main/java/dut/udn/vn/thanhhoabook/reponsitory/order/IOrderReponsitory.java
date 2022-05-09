package dut.udn.vn.thanhhoabook.reponsitory.order;

import dut.udn.vn.thanhhoabook.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderReponsitory extends JpaRepository<Orders, Long> {
    List<Orders> findOrdersByDeleteFlagFalse();
}
