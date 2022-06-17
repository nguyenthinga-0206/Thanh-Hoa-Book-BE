package dut.udn.vn.thanhhoabook.model.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dut.udn.vn.thanhhoabook.contans.book.ECover;
import dut.udn.vn.thanhhoabook.contans.book.ELanguage;
import dut.udn.vn.thanhhoabook.utils.Listener;
import dut.udn.vn.thanhhoabook.utils.TimeUser;
import dut.udn.vn.thanhhoabook.model.order.Cart;
import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(Listener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements TimeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    /*
    Mã sách
     */
    private String code;

    private Integer yearPublishing;

    private Integer quantity;

    private Integer weight;

    private Float width;

    private Float lenght;

    @Null
    private Float height;
    /*
    Số trang
     */
    private Integer pageNumber;

    @Enumerated(EnumType.STRING)
    private ELanguage language;

    /*
    Kiểu bìa
     */
    @Enumerated(EnumType.STRING)
    private ECover formCover;

    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "book")
    private List<Image> imageList;

    /*
    Tác giả
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authorList;

    /*
    Nhà xuất bản
     */
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    /*
    Thể loại
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Cart> cartList;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<OrderDetails> orderDetailsList;

    private Boolean deleteFlag = Boolean.FALSE;

    private String userCreateFlag;

    private LocalDateTime timeCreateFlag;

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;
}
