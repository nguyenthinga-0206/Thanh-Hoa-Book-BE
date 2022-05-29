package dut.udn.vn.thanhhoabook.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dut.udn.vn.thanhhoabook.contans.order.EStatus;
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

    /*
    Mã đơn hàng
     */
    private String code;

    private String fullName;

    private String phone;

    private String address;

    /*
    Chi tiết đơn hàng
     */
    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    private List<OrderDetails> orderDetailsList;

    /*
    Trạng thái đơn hàng
     */
    @Enumerated(EnumType.STRING)
    private EStatus status = EStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    private Boolean deleteFlag = Boolean.FALSE;

    private String userCreateFlag;

    private LocalDateTime timeCreateFlag;

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;
}
