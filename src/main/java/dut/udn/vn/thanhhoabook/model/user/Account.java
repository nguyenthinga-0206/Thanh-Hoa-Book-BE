package dut.udn.vn.thanhhoabook.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dut.udn.vn.thanhhoabook.contans.user.ERole;
import dut.udn.vn.thanhhoabook.model.order.Orders;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Orders> ordersList;

    private Boolean deleteFlag = Boolean.FALSE;

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;
}