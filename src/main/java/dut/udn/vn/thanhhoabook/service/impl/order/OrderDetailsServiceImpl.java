package dut.udn.vn.thanhhoabook.service.impl.order;

import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import dut.udn.vn.thanhhoabook.reponsitory.order.IOrderDetailsReponsitory;
import dut.udn.vn.thanhhoabook.service.order.IOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements IOrderDetailsService {
    @Autowired
    private IOrderDetailsReponsitory orderDetailsReponsitory;

    @Override
    public List<OrderDetails> getAll() {
        return orderDetailsReponsitory.findOrderDetailsByDeleteFlagFalse();
    }

    @Override
    public Optional<OrderDetails> getById(Long id) {
        return orderDetailsReponsitory.findById(id);
    }

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsReponsitory.save(orderDetails);
    }

    @Override
    public List<OrderDetails> getByOrderId(Long id) {
        return orderDetailsReponsitory.findOrderDetailsByOrders_IdAndDeleteFlagFalse(id);
    }
}
