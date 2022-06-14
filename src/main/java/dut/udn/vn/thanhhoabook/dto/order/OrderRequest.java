package dut.udn.vn.thanhhoabook.dto.order;

import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import dut.udn.vn.thanhhoabook.model.user.Account;
import dut.udn.vn.thanhhoabook.security.service.MyUserDetails;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private String fullName;
    private String phone;
    private String address;
    private BigDecimal ship;
    private List<OrderDetails> detailsList;
}
