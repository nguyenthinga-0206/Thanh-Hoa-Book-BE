package dut.udn.vn.thanhhoabook.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dut.udn.vn.thanhhoabook.contans.user.ERole;
import dut.udn.vn.thanhhoabook.model.order.Order;
import dut.udn.vn.thanhhoabook.model.order.OrderDetails;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    private boolean deleteFlag = Boolean.FALSE;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<Order> orderList;
}