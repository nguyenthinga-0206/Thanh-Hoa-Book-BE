package dut.udn.vn.thanhhoabook.model.order;

import dut.udn.vn.thanhhoabook.utils.TimeUser;
import dut.udn.vn.thanhhoabook.model.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(TimeUser.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements TimeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Integer quantity;

    private Boolean deleteFlag = Boolean.FALSE;

    private String userCreateFlag;

    private LocalDateTime timeCreateFlag;

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;

    public Cart(Book book, Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }
}
