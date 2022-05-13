package dut.udn.vn.thanhhoabook.reponsitory.book;

import dut.udn.vn.thanhhoabook.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookReponsitory extends JpaRepository<Book, Long> {
    Boolean existsByCode(String code);

    List<Book> findBookByDeleteFlagFalse();
}
