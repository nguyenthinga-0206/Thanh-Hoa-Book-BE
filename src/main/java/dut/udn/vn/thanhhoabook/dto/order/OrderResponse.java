package dut.udn.vn.thanhhoabook.dto.order;

import dut.udn.vn.thanhhoabook.contans.order.EStatus;
import dut.udn.vn.thanhhoabook.dto.book.BookResponse;
import dut.udn.vn.thanhhoabook.model.user.Account;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class OrderResponse {
    private Long id;
    private String code;
    private String username;
    private String fullName;
    private String phone;
    private String address;
    private EStatus status;
    private Integer count;
    private BigDecimal totalPrice;
}
