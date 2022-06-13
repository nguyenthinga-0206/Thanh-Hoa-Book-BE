package dut.udn.vn.thanhhoabook.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dut.udn.vn.thanhhoabook.contans.order.EStatus;
import dut.udn.vn.thanhhoabook.utils.Listener;
import dut.udn.vn.thanhhoabook.utils.TimeUser;
import dut.udn.vn.thanhhoabook.model.user.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(Listener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements TimeUser {
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
