package dut.udn.vn.thanhhoabook.dto.order;

import dut.udn.vn.thanhhoabook.model.order.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartResponse {

    private List<Cart> cartList;

    private BigDecimal total;

}
