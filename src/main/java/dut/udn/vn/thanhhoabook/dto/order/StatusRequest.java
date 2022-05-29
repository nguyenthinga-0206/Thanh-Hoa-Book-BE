package dut.udn.vn.thanhhoabook.dto.order;

import dut.udn.vn.thanhhoabook.contans.order.EStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatusRequest {
    private Long id;
    private EStatus status;
}
