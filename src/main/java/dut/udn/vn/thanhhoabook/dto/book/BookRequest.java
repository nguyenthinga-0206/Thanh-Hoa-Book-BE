package dut.udn.vn.thanhhoabook.dto.book;

import dut.udn.vn.thanhhoabook.contans.book.ECover;
import dut.udn.vn.thanhhoabook.contans.book.ELanguage;
import dut.udn.vn.thanhhoabook.model.book.Author;
import dut.udn.vn.thanhhoabook.model.book.Category;
import dut.udn.vn.thanhhoabook.model.book.Image;
import dut.udn.vn.thanhhoabook.model.book.Producer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BookRequest {
    private Long id;
    private String code;
    private String name;
    private Integer yearPublishing;
    private Integer quantity;
    private Integer weight;
    private Float width;
    private Float lenght;
    private Float height;
    private Integer pageNumber;
    @Enumerated(EnumType.STRING)
    private ELanguage language;
    @Enumerated(EnumType.STRING)
    private ECover formCover;
    private BigDecimal price;
    private String description;
    private List<Image> imageList;
    private List<Author> authorList;
    private Producer producer;
    private List<Category> categoryList;
}
