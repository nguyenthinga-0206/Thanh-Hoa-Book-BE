package dut.udn.vn.thanhhoabook.controller.order;

import dut.udn.vn.thanhhoabook.dto.order.CartRequet;
import dut.udn.vn.thanhhoabook.dto.order.CartResponse;
import dut.udn.vn.thanhhoabook.model.book.Book;
import dut.udn.vn.thanhhoabook.model.order.Cart;
import dut.udn.vn.thanhhoabook.service.impl.book.BookServiceImpl;
import dut.udn.vn.thanhhoabook.service.impl.order.CartServiceImpl;
import dut.udn.vn.thanhhoabook.utils.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping()
    public ResponseEntity<CartResponse> listCart() {
        List<Cart> cartList = cartService.getByUser(Custom.getUsername());
        if (cartList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BigDecimal total = Custom.totalPriceCart(cartList);
        CartResponse cartResponse = new CartResponse(cartList, total);
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Status> addBookToCart(@RequestBody CartRequet cartRequet) {
        if (cartRequet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Cart> cartOptional = this.cartService.getByBookId(Custom.getUsername(), cartRequet.getId());
        if (cartOptional.isPresent()) {
            cartOptional.get().setQuantity(cartOptional.get().getQuantity() + cartRequet.getQuantity());
            cartService.save(cartOptional.get());
        } else {
            Book book = bookService.getById(cartRequet.getId()).get();
            cartService.save(new Cart(book, cartRequet.getQuantity()));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{quantity}")
    public ResponseEntity<Status> editCart(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity) {
        Optional<Cart> cartOptional = cartService.getById(id);
        if (cartOptional.isPresent()) {
            cartOptional.get().setQuantity(quantity);
            this.cartService.save(cartOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Status> deleteCart(@PathVariable("id") Long id) {
        Optional<Cart> cartOptional = cartService.getById(id);
        if (cartOptional.isPresent()) {
            cartOptional.get().setDeleteFlag(true);
            this.cartService.save(cartOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
