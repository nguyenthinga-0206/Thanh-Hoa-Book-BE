package dut.udn.vn.thanhhoabook.controller;

import dut.udn.vn.thanhhoabook.model.book.Book;
import dut.udn.vn.thanhhoabook.service.IBookService;
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
@RequestMapping("api/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> listBook(){
        List<Book> bookList = bookService.getAll();
        return bookList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
