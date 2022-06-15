package dut.udn.vn.thanhhoabook.reponsitory.order;

import dut.udn.vn.thanhhoabook.contans.order.EStatus;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderReponsitory extends JpaRepository<Orders, Long> {
    List<Orders> findOrdersByDeleteFlagFalse();

    @Query(value = "select o from Orders as o where o.deleteFlag = false " +
            "and o.userCreateFlag = :user and o.status = :status " +
            "order by o.id DESC")
    List<Orders> getOrderHistoryStatus(@Param("user") String user, @Param("status") EStatus status);
}
