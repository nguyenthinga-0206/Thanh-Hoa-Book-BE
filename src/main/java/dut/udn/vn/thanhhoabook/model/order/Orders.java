package dut.udn.vn.thanhhoabook.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dut.udn.vn.thanhhoabook.model.user.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String fullName;

    private String phone;

    private String address;

    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    List<OrderDetails> orderDetailsList;

    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    List<TimeStatus> timeStatusList;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    private Boolean deleteFlag = Boolean.FALSE;

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;
}
