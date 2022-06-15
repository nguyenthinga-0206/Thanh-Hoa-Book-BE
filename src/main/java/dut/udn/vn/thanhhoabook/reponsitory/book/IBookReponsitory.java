package dut.udn.vn.thanhhoabook.reponsitory.book;

import dut.udn.vn.thanhhoabook.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookReponsitory extends JpaRepository<Book, Long> {
    Boolean existsByCode(String code);

    List<Book> findBookByDeleteFlagFalse();

    @Query(nativeQuery = true, value = "select * from public.book " +
            "inner join public.book_category on book.id = book_category.book_id " +
            "where delete_flag = false and book_category.category_id = :id")
    List<Book> getBycategory(@Param("id") Long id);
}
