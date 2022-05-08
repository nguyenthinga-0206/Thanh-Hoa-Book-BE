package dut.udn.vn.thanhhoabook.reponsitory.book;

import dut.udn.vn.thanhhoabook.model.book.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryReponsitory extends JpaRepository<Category, Long> {
    List<Category> findCategoryByDeleteFlagFalse();
}
