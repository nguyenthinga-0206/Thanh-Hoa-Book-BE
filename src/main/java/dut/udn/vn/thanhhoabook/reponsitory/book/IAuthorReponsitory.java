package dut.udn.vn.thanhhoabook.reponsitory.book;

import dut.udn.vn.thanhhoabook.model.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAuthorReponsitory extends JpaRepository<Author, Long> {
    List<Author> findAuthorByDeleteFlagFalse();
}
