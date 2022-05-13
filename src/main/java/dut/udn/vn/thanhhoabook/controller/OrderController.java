package dut.udn.vn.thanhhoabook.controller;

import dut.udn.vn.thanhhoabook.model.order.Orders;
import dut.udn.vn.thanhhoabook.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private IOrderService ordersService;

    @GetMapping()
    public ResponseEntity<List<Orders>> listOrder(){
        List<Orders> ordersList = ordersService.getAll();
        return ordersList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> findByOrder(@PathVariable Long id){
        Optional<Orders> ordersList = ordersService.getById(id);
        return ordersList.isPresent() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(ordersList.get(), HttpStatus.OK);
    }
}
