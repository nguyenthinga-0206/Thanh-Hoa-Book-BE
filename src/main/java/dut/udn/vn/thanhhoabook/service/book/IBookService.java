package dut.udn.vn.thanhhoabook.service.book;

import dut.udn.vn.thanhhoabook.model.book.Book;
import dut.udn.vn.thanhhoabook.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService extends IService<Book, Long> {
    Boolean existsByCode(String code);

    List<Book> getBycategory(Long id);

    List<Book> getByName(String name);
}
