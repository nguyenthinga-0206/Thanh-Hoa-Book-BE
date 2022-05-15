package dut.udn.vn.thanhhoabook.controller.book;

import dut.udn.vn.thanhhoabook.model.book.Author;
import dut.udn.vn.thanhhoabook.service.book.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/author")
public class AuthorController {
    @Autowired
    private IAuthorService authorService;

    @GetMapping()
    public ResponseEntity<List<Author>> listAuthor() {
        List<Author> authorList = authorService.getAll();
        return authorList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Status> createAuthor(@RequestBody Author author) {
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.save(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Status> updateAuthor(@RequestBody Author author) {
        Optional<Author> authorOptional = authorService.getById(author.getId());
        if (!authorOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.save(author);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Status> deleteAuthor(@RequestParam("id") Long id) {
        Optional<Author> authorOptional = authorService.getById(id);
        if (authorOptional.isPresent()) {
            authorOptional.get().setDeleteFlag(true);
            this.authorService.save(authorOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
