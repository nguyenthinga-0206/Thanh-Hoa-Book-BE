package dut.udn.vn.thanhhoabook.dto.book;

import dut.udn.vn.thanhhoabook.contans.book.ECover;
import dut.udn.vn.thanhhoabook.contans.book.ELanguage;
import dut.udn.vn.thanhhoabook.model.book.Author;
import dut.udn.vn.thanhhoabook.model.book.Category;
import dut.udn.vn.thanhhoabook.model.book.Image;
import dut.udn.vn.thanhhoabook.model.book.Producer;
import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BookRequest {
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[0-9]{8}$")
    private String code;

    @NotBlank
    @Size(min = 3)
    private String name;

    private Integer yearPublishing;

    private Integer quantity;

    //    @Pattern(regexp = "^\\d+(.\\d{1,2})?$")
    private Integer weight;

    private Float width;

    private Float lenght;

    private Float height;

    private Integer pageNumber;

    private ELanguage language;

    private ECover formCover;

//    @Pattern(regexp = "^(?!^0\\.00$)(([1-9][\\d]{0,6})|([0]))\\.[\\d]{2}$")
    @Positive
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @Size(max = 2000)
    private String description;

    private List<Image> imageList;

    private List<Author> authorList;

    private List<Producer> producerList;

    private List<Category> categoryList;

    private List<OrderDetails> orderDetailsList;
}
