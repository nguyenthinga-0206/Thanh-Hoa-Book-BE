package dut.udn.vn.thanhhoabook.dto.statisstic;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TopBookResponse {
    private String name;
    private Integer count;
    private BigDecimal total;
}
