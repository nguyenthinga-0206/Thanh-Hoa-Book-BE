package dut.udn.vn.thanhhoabook.reponsitory.book;

import dut.udn.vn.thanhhoabook.model.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorReponsitory extends JpaRepository<Author, Long> {
    List<Author> findAuthorByDeleteFlagFalse();
}
