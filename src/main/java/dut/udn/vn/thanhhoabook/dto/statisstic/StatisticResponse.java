package dut.udn.vn.thanhhoabook.dto.statisstic;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class StatisticResponse {
    private Integer milestone;
    private BigDecimal total;
}
