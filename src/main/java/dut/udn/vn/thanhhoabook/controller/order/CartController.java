package dut.udn.vn.thanhhoabook.controller.order;

import dut.udn.vn.thanhhoabook.dto.order.CartRequet;
import dut.udn.vn.thanhhoabook.model.book.Book;
import dut.udn.vn.thanhhoabook.model.order.Cart;
import dut.udn.vn.thanhhoabook.service.impl.book.BookServiceImpl;
import dut.udn.vn.thanhhoabook.service.impl.order.CartServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<Cart>> listCart() {
        List<Cart> cartList = cartService.getAll();
        return cartList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    @PostMapping("add-cart")
    public ResponseEntity<Status> addBookToCart(@RequestBody CartRequet cartRequet) {
        if (cartRequet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Book book = bookService.getById(cartRequet.getId()).get();
        Cart cart = new Cart(book, cartRequet.getQuantity());
        cartService.save(cart);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
