package dut.udn.vn.thanhhoabook.reponsitory.order;

import dut.udn.vn.thanhhoabook.dto.statisstic.IStatisticResponse;
import dut.udn.vn.thanhhoabook.dto.statisstic.ITopBookResponse;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStatisticReponsitory extends JpaRepository<Orders, Long> {

    @Query(nativeQuery = true, value = "select b.name, count(b.id) as count, sum(b.price) as total " +
            "from public.order_details as od " +
            "inner join public.book as b on od.book_id = b.id " +
            "where b.delete_flag = false " +
            "group by b.id order by count desc limit 10")
    List<ITopBookResponse> topBook();

    @Query(nativeQuery = true, value = "select date_part('month', time_create_flag) as  milestone,  sum(od.price) as total " +
            "from public.order_details as od " +
            "where date_part('year', time_create_flag) = :year " +
            "group by date_part('month', time_create_flag) " +
            "order by date_part('month', time_create_flag)")
    List<IStatisticResponse> statisticByMonth(@Param("year") Integer year);

    @Query(nativeQuery = true, value = "select date_part('quarter', time_create_flag) as  milestone,  sum(od.price) as total " +
            "from public.order_details as od " +
            "where date_part('year', time_create_flag) = :year " +
            "group by date_part('quarter', time_create_flag) " +
            "order by date_part('quarter', time_create_flag)")
    List<IStatisticResponse> statisticByQuarterly(@Param("year") Integer year);

    @Query(nativeQuery = true, value = "select date_part('year', time_create_flag) as  milestone, sum(od.price) as total " +
            "from public.order_details as od " +
            "group by date_part('year', time_create_flag) " +
            "order by date_part('year', time_create_flag) desc limit 10")
    List<IStatisticResponse> statisticByYear();
}
