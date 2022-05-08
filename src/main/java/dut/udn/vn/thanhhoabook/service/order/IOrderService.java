package dut.udn.vn.thanhhoabook.service.order;

import dut.udn.vn.thanhhoabook.model.order.Orders;
import dut.udn.vn.thanhhoabook.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface IOrderService extends IService<Orders, Long> {
}
