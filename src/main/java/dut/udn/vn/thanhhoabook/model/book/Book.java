package dut.udn.vn.thanhhoabook.model.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dut.udn.vn.thanhhoabook.contans.book.ECover;
import dut.udn.vn.thanhhoabook.contans.book.ELanguage;
import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

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

    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    List<Image> imageList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authorList;

    @ManyToMany
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "book_producer",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private List<Producer> producerList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    List<OrderDetails> orderDetailsList;

    private Boolean deleteFlag = Boolean.FALSE;

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;
}
