package dut.udn.vn.thanhhoabook.controller.book;

import dut.udn.vn.thanhhoabook.dto.book.AddBookRequest;
import dut.udn.vn.thanhhoabook.dto.book.BookRequest;
import dut.udn.vn.thanhhoabook.model.book.*;
import dut.udn.vn.thanhhoabook.service.impl.book.BookServiceImpl;
import dut.udn.vn.thanhhoabook.service.impl.book.ImageServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private ImageServiceImpl imageService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<List<Book>> listBook() {
        List<Book> bookList = bookService.getAll();
        return bookList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id) {
        Optional<Book> book = bookService.getById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> getByName(@RequestParam("name") String name) {
        List<Book> bookList = bookService.getByName(name);
        return bookList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Book>> getByCategory(@PathVariable("id") Long id) {
        List<Book> bookList = bookService.getBycategory(id);
        return bookList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Status> createBook(@RequestBody BookRequest bookRequest) {
        if (bookRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (bookService.existsByCode(bookRequest.getCode())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Book book = modelMapper.map(bookRequest, Book.class);
        bookService.save(book);
        if (book.getImageList() != null) {
            for (Image image : book.getImageList()) {
                image.setBook(book);
                imageService.save(image);
            }
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("add-book")
    public ResponseEntity<Status> addBook(@RequestBody AddBookRequest addBookRequest) {
        Optional<Book> bookOptional = bookService.getById(addBookRequest.getId());
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookOptional.get().setQuantity(bookOptional.get().getQuantity() + addBookRequest.getQuantity());
        bookService.save(bookOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping()
    public ResponseEntity<Status> updateBook(@RequestBody BookRequest bookRequest) {
        Optional<Book> bookOptional = bookService.getById(bookRequest.getId());
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (Image image: bookOptional.get().getImageList()) {
            imageService.delete(image);
        }
        Book book = modelMapper.map(bookRequest, Book.class);
        bookService.save(book);
        if (book.getImageList().size() != 0) {
            for (Image image : book.getImageList()) {
                image.setName(image.getName());
                image.setPath(image.getPath());
                image.setBook(book);
                imageService.save(image);
            }
        }
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
