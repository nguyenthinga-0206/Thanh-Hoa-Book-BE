package dut.udn.vn.thanhhoabook.reponsitory.order;

import dut.udn.vn.thanhhoabook.model.order.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICartReponsitoty extends JpaRepository<Cart, Long> {

    List<Cart> findCartByDeleteFlagFalse();

    @Query(value = "select c from Cart as c where c.deleteFlag = false and c.userCreateFlag = :user")
    List<Cart> getByUser(@Param("user") String user);

    Optional<Cart> findCartByDeleteFlagFalseAndUserCreateFlagAndBook_Id(String user, Long id);
}
