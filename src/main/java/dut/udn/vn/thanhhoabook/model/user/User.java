package dut.udn.vn.thanhhoabook.model.user;

import dut.udn.vn.thanhhoabook.contans.user.EGender;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

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

    private String userUpdateFlag;

    private LocalDateTime timeUpdateFlag;
}
