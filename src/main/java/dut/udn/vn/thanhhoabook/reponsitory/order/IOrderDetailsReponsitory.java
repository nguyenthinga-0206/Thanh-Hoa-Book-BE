package dut.udn.vn.thanhhoabook.reponsitory.order;

import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailsReponsitory extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findOrderDetailsByDeleteFlagFalse();

    List<OrderDetails> findOrderDetailsByOrders_IdAndDeleteFlagFalse(Long id);
}
