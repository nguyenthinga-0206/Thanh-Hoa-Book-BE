package dut.udn.vn.thanhhoabook.controller;

import dut.udn.vn.thanhhoabook.model.order.Orders;
import dut.udn.vn.thanhhoabook.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
