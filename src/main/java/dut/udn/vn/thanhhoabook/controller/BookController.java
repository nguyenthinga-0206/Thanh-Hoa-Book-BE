package dut.udn.vn.thanhhoabook.controller;

import dut.udn.vn.thanhhoabook.dto.book.AddBookRequest;
import dut.udn.vn.thanhhoabook.dto.book.BookRequest;
import dut.udn.vn.thanhhoabook.model.book.Book;
import dut.udn.vn.thanhhoabook.model.book.Image;
import dut.udn.vn.thanhhoabook.service.book.IBookService;
import dut.udn.vn.thanhhoabook.service.book.IImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<List<Book>> listBook() {
        List<Book> bookList = bookService.getAll();
        return bookList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Status> createBook(@Valid @RequestBody BookRequest bookRequest) {
        if (bookRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (bookService.existsByCode(bookRequest.getCode())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Book book = modelMapper.map(bookRequest, Book.class);
        for (Image image: book.getImageList()) {
            imageService.save(image);
        }
        bookService.save(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("add-book")
    public ResponseEntity<Status> addBook(@Valid @RequestBody AddBookRequest addBookRequest) {
        Optional<Book> bookOptional = bookService.getById(addBookRequest.getId());
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookOptional.get().setQuantity(bookOptional.get().getQuantity()+addBookRequest.getQuantity());
        bookService.save(bookOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping()
    public ResponseEntity<Status> updateBook(@Valid @RequestBody BookRequest bookRequest) {
        Optional<Book> bookOptional = bookService.getById(bookRequest.getId());
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Book book = modelMapper.map(bookRequest, Book.class);
        bookService.save(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Status> deleteBook(@RequestParam("id") Long id) {
        Optional<Book> bookOptional = bookService.getById(id);
        if (bookOptional.isPresent()) {
            bookOptional.get().setDeleteFlag(true);
            this.bookService.save(bookOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
