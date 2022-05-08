package dut.udn.vn.thanhhoabook.service.book;

import dut.udn.vn.thanhhoabook.model.book.Book;
import dut.udn.vn.thanhhoabook.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface IBookService extends IService<Book, Long> {
    Boolean existsByCode(String code);
}
