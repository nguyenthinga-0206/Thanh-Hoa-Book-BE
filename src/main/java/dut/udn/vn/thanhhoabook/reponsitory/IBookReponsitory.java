package dut.udn.vn.thanhhoabook.reponsitory;

import dut.udn.vn.thanhhoabook.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookReponsitory extends JpaRepository<Book, Integer> {
    List<Book> findBookByDeleteFlagFalse();
}
