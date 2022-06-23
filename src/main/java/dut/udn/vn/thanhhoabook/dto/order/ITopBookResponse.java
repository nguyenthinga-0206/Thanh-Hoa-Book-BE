package dut.udn.vn.thanhhoabook.dto.order;

import java.math.BigDecimal;

public interface ITopBookResponse {
    String getName();
    Integer getCount();
    BigDecimal getTotal();
}
