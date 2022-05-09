package dut.udn.vn.thanhhoabook.reponsitory.book;

import dut.udn.vn.thanhhoabook.model.book.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageReponsitory extends JpaRepository<Image, Long> {
    List<Image> findImageByDeleteFlagFalse();
}
