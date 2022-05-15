package dut.udn.vn.thanhhoabook.controller.book;

import dut.udn.vn.thanhhoabook.model.book.Producer;
import dut.udn.vn.thanhhoabook.service.book.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/producer")
public class ProducerController {
    @Autowired
    private IProducerService producerService;

    @GetMapping()
    public ResponseEntity<List<Producer>> listProducer() {
        List<Producer> producerList = producerService.getAll();
        return producerList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(producerList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Status> createProducer(@RequestBody Producer producer) {
        if (producer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        producerService.save(producer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Status> updateProducer(@RequestBody Producer producer) {
        Optional<Producer> producerOptional = producerService.getById(producer.getId());
        if (!producerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        producerService.save(producer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Status> deleteProducer(@RequestParam("id") Long id) {
        Optional<Producer> producerOptional = producerService.getById(id);
        if (producerOptional.isPresent()) {
            producerOptional.get().setDeleteFlag(true);
            this.producerService.save(producerOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
