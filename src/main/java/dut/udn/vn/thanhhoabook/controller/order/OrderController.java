package dut.udn.vn.thanhhoabook.controller.order;

import dut.udn.vn.thanhhoabook.dto.order.OrderResponse;
import dut.udn.vn.thanhhoabook.dto.order.StatusRequest;
import dut.udn.vn.thanhhoabook.model.book.Author;
import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import dut.udn.vn.thanhhoabook.service.order.IOrderDetailsService;
import dut.udn.vn.thanhhoabook.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private IOrderService ordersService;

    @Autowired
    private IOrderDetailsService orderDetailsService;

    @GetMapping()
    public ResponseEntity<List<Orders>> listOrder() {
        List<Orders> ordersList = ordersService.getAll();
        return ordersList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> findByOrder(@PathVariable Long id) {
        Optional<Orders> order = ordersService.getById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<List<OrderDetails>> findOrderDetailsByOrderId(@PathVariable Long id) {
        List<OrderDetails> orderDetailsList = orderDetailsService.getByOrderId(id);
        return orderDetailsList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(orderDetailsList, HttpStatus.OK);
    }

    @PutMapping("status")
    public ResponseEntity<Status> editStatus(@RequestBody StatusRequest statusRequest) {
        Optional<Orders> ordersOptional = ordersService.getById(statusRequest.getId());
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersOptional.get().setStatus(statusRequest.getStatus());
        ordersService.save(ordersOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
