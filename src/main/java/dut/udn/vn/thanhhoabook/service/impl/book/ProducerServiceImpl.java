package dut.udn.vn.thanhhoabook.service.impl.book;

import dut.udn.vn.thanhhoabook.model.book.Producer;
import dut.udn.vn.thanhhoabook.reponsitory.book.IProducerReponsitory;
import dut.udn.vn.thanhhoabook.service.book.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerServiceImpl implements IProducerService {
    @Autowired
    private IProducerReponsitory producerReponsitory;

    @Override
    public List<Producer> getAll() {
        return producerReponsitory.findProducerByDeleteFlagFalse();
    }

    @Override
    public Optional<Producer> getById(Long id) {
        return producerReponsitory.findById(id);
    }

    @Override
    public Producer save(Producer producer) {
        return producerReponsitory.save(producer);
    }
}
