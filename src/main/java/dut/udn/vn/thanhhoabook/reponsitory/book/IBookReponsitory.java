package dut.udn.vn.thanhhoabook.reponsitory.book;

import dut.udn.vn.thanhhoabook.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookReponsitory extends JpaRepository<Book, Long> {
    Boolean existsByCode(String code);

    List<Book> findBookByDeleteFlagFalse();
}
