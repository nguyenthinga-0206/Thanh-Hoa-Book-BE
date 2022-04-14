package dut.udn.vn.thanhhoabook.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dut.udn.vn.thanhhoabook.model.book.Image;
import dut.udn.vn.thanhhoabook.model.user.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;

    private String fullName;

    private String phone;

    private String address;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    List<OrderDetails> orderDetailsList;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    List<TimeStatus> timeStatusList;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;
}
