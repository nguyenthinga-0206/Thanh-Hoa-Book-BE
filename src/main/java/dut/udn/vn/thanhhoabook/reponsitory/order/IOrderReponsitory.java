package dut.udn.vn.thanhhoabook.reponsitory.order;

import dut.udn.vn.thanhhoabook.dto.order.OrderResponse;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderReponsitory extends JpaRepository<Orders, Long> {
    List<Orders> findOrdersByDeleteFlagFalse();

//    @Query(value="select o.id, o.code, o.account, o.full_name, o.phone, o.address, o.status, count(od.id) as count, sum(od.quantity*b.price) as total_price " +
//            "from public.orders as o " +
//            "inner join public.order_details as od on od.orders_id = o.id " +
//            "inner join public.book as b on od.book_id = b.id " +
//            "where o.id = :id " +
//            "group by o.id ", nativeQuery = true)
//    Optional<OrderResponse> getOrderById(Long id);
}
