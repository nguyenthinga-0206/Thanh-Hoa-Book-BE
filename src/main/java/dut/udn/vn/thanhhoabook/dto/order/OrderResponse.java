package dut.udn.vn.thanhhoabook.dto.order;

import dut.udn.vn.thanhhoabook.contans.order.EStatus;
import dut.udn.vn.thanhhoabook.dto.book.BookResponse;
import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import dut.udn.vn.thanhhoabook.model.user.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class OrderResponse {
    private Orders orders;
    private BigDecimal totalPrice;
}
