package dut.udn.vn.thanhhoabook.service.order;

import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import dut.udn.vn.thanhhoabook.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderDetailsService extends IService<OrderDetails, Long> {
    List<OrderDetails> getByOrderId(Long id);
}
