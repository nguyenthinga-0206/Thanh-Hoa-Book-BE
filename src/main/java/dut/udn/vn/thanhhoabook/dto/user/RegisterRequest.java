package dut.udn.vn.thanhhoabook.dto.user;

import dut.udn.vn.thanhhoabook.contans.user.EGender;
import dut.udn.vn.thanhhoabook.model.user.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;

    private String password;

    private String fullName;

    private LocalDate birthday;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private EGender gender;
}
