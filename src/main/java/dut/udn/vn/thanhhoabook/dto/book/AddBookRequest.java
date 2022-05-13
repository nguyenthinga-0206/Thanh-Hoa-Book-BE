package dut.udn.vn.thanhhoabook.dto.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookRequest {
    private Long id;
    private Integer quantity;
}
