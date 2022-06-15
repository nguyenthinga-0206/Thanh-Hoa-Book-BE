package dut.udn.vn.thanhhoabook.utils;

import dut.udn.vn.thanhhoabook.model.order.Cart;
import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import dut.udn.vn.thanhhoabook.security.service.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.List;

public class Custom {
    public static String getUsername() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getAccount().getUsername();
    }

    public static BigDecimal totalPriceCart(List<Cart> cartList) {
        BigDecimal total = new BigDecimal(0);
        for (Cart cart1 : cartList) {
            total = total.add(BigDecimal.valueOf(cart1.getQuantity()).multiply(cart1.getBook().getPrice()));
        }
        return total;
    }

    public static BigDecimal totalPriceOrder(List<OrderDetails> details) {
        BigDecimal total = new BigDecimal(0);
        for (OrderDetails detail : details) {
            total = total.add(BigDecimal.valueOf(detail.getQuantity()).multiply(detail.getBook().getPrice()));
        }
        return total;
    }
}
