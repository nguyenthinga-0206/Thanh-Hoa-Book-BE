package dut.udn.vn.thanhhoabook.reponsitory.book;

import dut.udn.vn.thanhhoabook.model.book.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProducerReponsitory extends JpaRepository<Producer, Long> {
    List<Producer> findProducerByDeleteFlagFalse();
}
