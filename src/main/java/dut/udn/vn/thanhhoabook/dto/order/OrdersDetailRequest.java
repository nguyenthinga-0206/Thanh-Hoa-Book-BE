package dut.udn.vn.thanhhoabook.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrdersDetailRequest {
    private Long id;
    private Integer quantity;
}
