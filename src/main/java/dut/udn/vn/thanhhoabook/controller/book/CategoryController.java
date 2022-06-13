package dut.udn.vn.thanhhoabook.controller.book;

import dut.udn.vn.thanhhoabook.model.book.Category;
import dut.udn.vn.thanhhoabook.service.book.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> listCategory() {
        List<Category> categoryList = categoryService.getAll();
        return categoryList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Status> createCategory(@RequestBody Category category) {
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Status> updateCategory(@RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.getById(category.getId());
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Status> deleteCategory(@RequestParam("id") Long id) {
        Optional<Category> categoryOptional = categoryService.getById(id);
        if (categoryOptional.isPresent()) {
            categoryOptional.get().setDeleteFlag(true);
            this.categoryService.save(categoryOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
