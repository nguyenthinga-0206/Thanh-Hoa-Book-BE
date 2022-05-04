package dut.udn.vn.thanhhoabook.service.impl;

import dut.udn.vn.thanhhoabook.model.book.Book;
import dut.udn.vn.thanhhoabook.reponsitory.IBookReponsitory;
import dut.udn.vn.thanhhoabook.service.IBookService;
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

    @Override
    public Optional<Book> getById(Integer id) {
        return bookReponsitory.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookReponsitory.save(book);
    }
}
