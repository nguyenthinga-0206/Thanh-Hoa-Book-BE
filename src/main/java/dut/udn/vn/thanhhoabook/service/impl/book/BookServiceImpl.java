package dut.udn.vn.thanhhoabook.service.impl.book;

import dut.udn.vn.thanhhoabook.model.book.Book;
import dut.udn.vn.thanhhoabook.reponsitory.book.IBookReponsitory;
import dut.udn.vn.thanhhoabook.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private IBookReponsitory bookReponsitory;

    @Override
    public List<Book> getAll() {
        return bookReponsitory.findBookByDeleteFlagFalse();
    }

    public List<Book> getBycategory(Long id) {
        return bookReponsitory.getBycategory(id);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return bookReponsitory.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookReponsitory.save(book);
    }

    @Override
    public Boolean existsByCode(String code) {
        return bookReponsitory.existsByCode(code);
    }
}
