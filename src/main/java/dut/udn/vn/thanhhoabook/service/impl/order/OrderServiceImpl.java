package dut.udn.vn.thanhhoabook.service.impl.order;

import dut.udn.vn.thanhhoabook.dto.order.OrderResponse;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import dut.udn.vn.thanhhoabook.reponsitory.order.IOrderReponsitory;
import dut.udn.vn.thanhhoabook.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderReponsitory orderReponsitory;

    @Override
    public List<Orders> getAll() {
        return orderReponsitory.findOrdersByDeleteFlagFalse();
    }

    @Override
    public Optional<Orders> getById(Long id) {
        return orderReponsitory.findById(id);
    }

    @Override
    public Orders save(Orders order) {
        return orderReponsitory.save(order);
    }

//    @Override
//    public Optional<OrderResponse> getOrderById(Long id) {
//        return orderReponsitory.getOrderById(id);
//    }
}
