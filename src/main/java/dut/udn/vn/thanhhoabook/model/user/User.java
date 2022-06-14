package dut.udn.vn.thanhhoabook.model.user;

import dut.udn.vn.thanhhoabook.contans.user.EGender;

import dut.udn.vn.thanhhoabook.utils.TimeUser;
import dut.udn.vn.thanhhoabook.utils.Listener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(Listener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements TimeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private LocalDate birthday;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    private Boolean deleteFlag = Boolean.FALSE;

    private String userCreateFlag;

    private LocalDateTime timeCreateFlag;

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;

    public User(String email, Account account) {
        this.email = email;
        this.account = account;
    }
}
