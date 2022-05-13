package dut.udn.vn.thanhhoabook.service.impl.book;

import dut.udn.vn.thanhhoabook.model.book.Author;
import dut.udn.vn.thanhhoabook.reponsitory.book.IAuthorReponsitory;
import dut.udn.vn.thanhhoabook.service.book.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements IAuthorService {
    @Autowired
    private IAuthorReponsitory authorReponsitory;

    @Override
    public List<Author> getAll() {
        return authorReponsitory.findAuthorByDeleteFlagFalse();
    }

    @Override
    public Optional<Author> getById(Long id) {
        return authorReponsitory.findById(id);
    }

    @Override
    public Author save(Author author) {
        return authorReponsitory.save(author);
    }
}
