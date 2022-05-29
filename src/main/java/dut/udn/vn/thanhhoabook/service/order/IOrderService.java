package dut.udn.vn.thanhhoabook.service.order;

import dut.udn.vn.thanhhoabook.dto.order.OrderResponse;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import dut.udn.vn.thanhhoabook.service.IService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IOrderService extends IService<Orders, Long> {
//    Optional<OrderResponse> getOrderById(Long id);
}
