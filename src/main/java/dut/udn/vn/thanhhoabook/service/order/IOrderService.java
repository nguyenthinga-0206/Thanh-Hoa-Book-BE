package dut.udn.vn.thanhhoabook.service.order;

import dut.udn.vn.thanhhoabook.contans.order.EStatus;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import dut.udn.vn.thanhhoabook.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService extends IService<Orders, Long> {
    List<Orders> getOrderHistory(String user, String status);
}
