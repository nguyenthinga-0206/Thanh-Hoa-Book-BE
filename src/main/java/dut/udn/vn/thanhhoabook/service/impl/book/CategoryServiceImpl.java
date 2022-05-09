package dut.udn.vn.thanhhoabook.service.impl.book;

import dut.udn.vn.thanhhoabook.model.book.Category;
import dut.udn.vn.thanhhoabook.reponsitory.book.ICategoryReponsitory;
import dut.udn.vn.thanhhoabook.service.book.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryReponsitory categoryReponsitory;

    @Override
    public List<Category> getAll() {
        return categoryReponsitory.findCategoryByDeleteFlagFalse();
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryReponsitory.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryReponsitory.save(category);
    }
}
