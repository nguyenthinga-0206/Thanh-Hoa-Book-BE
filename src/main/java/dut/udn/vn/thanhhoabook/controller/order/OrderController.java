package dut.udn.vn.thanhhoabook.controller.order;

import dut.udn.vn.thanhhoabook.dto.order.*;
import dut.udn.vn.thanhhoabook.model.book.Book;
import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import dut.udn.vn.thanhhoabook.service.impl.book.BookServiceImpl;
import dut.udn.vn.thanhhoabook.service.impl.order.OrderDetailsServiceImpl;
import dut.udn.vn.thanhhoabook.service.impl.order.OrderServiceImpl;
import dut.udn.vn.thanhhoabook.utils.Custom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl ordersService;

    @Autowired
    private OrderDetailsServiceImpl orderDetailsService;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<List<Orders>> listOrder() {
        List<Orders> ordersList = ordersService.getAll();
        return ordersList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("history")
    public ResponseEntity<List<OrderResponse>> listOrderHistory(@RequestParam("status") String status) {
        List<Orders> ordersList = ordersService.getOrderHistory(Custom.getUsername(), status);
        if (ordersList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<OrderResponse> orderResponsesList = new ArrayList<>();
        for (Orders orders : ordersList) {
            BigDecimal total = Custom.totalPriceOrder(orders.getOrderDetailsList());
            orderResponsesList.add(new OrderResponse(orders, total));
        }
        return new ResponseEntity<>(orderResponsesList, HttpStatus.OK);
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

    @PutMapping("/status")
    public ResponseEntity<Status> editStatus(@RequestBody StatusRequest statusRequest) {
        Optional<Orders> ordersOptional = ordersService.getById(statusRequest.getId());
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersOptional.get().setStatus(statusRequest.getStatus());
        ordersService.save(ordersOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Status> createOrder(@RequestBody OrderRequest orderRequest) {
        if (orderRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Orders order = modelMapper.map(orderRequest, Orders.class);
        for (OrderDetails details : orderRequest.getDetailsList()) {
            Book book = bookService.getById(details.getBook().getId()).get();
            if (book.getQuantity() >= details.getQuantity()) {

            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        ordersService.save(order);
        for (OrderDetails details : orderRequest.getDetailsList()) {
            Book book = bookService.getById(details.getBook().getId()).get();
            book.setQuantity(book.getQuantity() - details.getQuantity());
            bookService.save(book);
            details.setOrders(order);
            details.setPrice(details.getBook().getPrice());
            orderDetailsService.save(details);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
