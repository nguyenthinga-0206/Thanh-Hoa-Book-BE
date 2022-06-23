package dut.udn.vn.thanhhoabook.reponsitory.order;

import dut.udn.vn.thanhhoabook.dto.order.ITopBookResponse;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IStatisticReponsitory extends JpaRepository<Orders, Long> {

//    @Query(nativeQuery = true, value = "SELECT * as total FROM public.orders")
//    int reportReceipt(Date date);

    @Query(nativeQuery = true, value = "select b.name, count(b.id) as count, sum(b.price) as total " +
            "from public.order_details as od " +
            "inner join public.book as b on od.book_id = b.id " +
            "where b.delete_flag = false " +
            "group by b.id order by count desc limit 10")
    List<ITopBookResponse> topBook();
}
