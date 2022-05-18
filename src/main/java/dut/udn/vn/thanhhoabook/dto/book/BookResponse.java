package dut.udn.vn.thanhhoabook.dto.book;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
public class BookResponse {
    private String code;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
