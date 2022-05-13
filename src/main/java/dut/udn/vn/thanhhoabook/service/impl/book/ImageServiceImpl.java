package dut.udn.vn.thanhhoabook.service.impl.book;

import dut.udn.vn.thanhhoabook.model.book.Image;
import dut.udn.vn.thanhhoabook.reponsitory.book.IImageReponsitory;
import dut.udn.vn.thanhhoabook.service.book.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    private IImageReponsitory imageReponsitory;

    @Override
    public List<Image> getAll() {
        return imageReponsitory.findImageByDeleteFlagFalse();
    }

    @Override
    public Optional<Image> getById(Long id) {
        return imageReponsitory.findById(id);
    }

    @Override
    public Image save(Image image) {
        return imageReponsitory.save(image);
    }
}
