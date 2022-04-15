package dut.udn.vn.thanhhoabook.model.order;

import dut.udn.vn.thanhhoabook.model.book.Book;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    private Boolean deleteFlag = Boolean.FALSE;

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;
}
