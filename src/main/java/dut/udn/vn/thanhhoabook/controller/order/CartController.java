package dut.udn.vn.thanhhoabook.controller.order;

import dut.udn.vn.thanhhoabook.dto.order.CartRequet;
import dut.udn.vn.thanhhoabook.dto.order.CartResponse;
import dut.udn.vn.thanhhoabook.model.book.Book;
import dut.udn.vn.thanhhoabook.model.order.Cart;
import dut.udn.vn.thanhhoabook.security.service.MyUserDetails;
import dut.udn.vn.thanhhoabook.service.impl.book.BookServiceImpl;
import dut.udn.vn.thanhhoabook.service.impl.order.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private String getUsername() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getAccount().getUsername();
    }

    @GetMapping()
    public ResponseEntity<CartResponse> listCart() {
        List<Cart> cartList = cartService.getByUser(getUsername());
        if (cartList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BigDecimal total = totalPrice(cartList);
        CartResponse cartResponse = new CartResponse(cartList, total);
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Status> addBookToCart(@RequestBody CartRequet cartRequet) {
        if (cartRequet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Cart> cartOptional = this.cartService.getByBookId(getUsername(), cartRequet.getId());
        if (cartOptional.isPresent()) {
            cartOptional.get().setQuantity(cartOptional.get().getQuantity() + cartRequet.getQuantity());
            cartService.save(cartOptional.get());
        } else {
            Book book = bookService.getById(cartRequet.getId()).get();
            cartService.save(new Cart(book, cartRequet.getQuantity()));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    private BigDecimal totalPrice(List<Cart> cartList) {
        BigDecimal total = new BigDecimal(0);
        for (Cart cart1 : cartList) {
            total = total.add(BigDecimal.valueOf(cart1.getQuantity()).multiply(cart1.getBook().getPrice()));
        }
        return total;
    }
}
