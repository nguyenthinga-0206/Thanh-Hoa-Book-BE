package dut.udn.vn.thanhhoabook.reponsitory.book;

import dut.udn.vn.thanhhoabook.model.book.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProducerReponsitory extends JpaRepository<Producer, Long> {
    List<Producer> findProducerByDeleteFlagFalse();
}
