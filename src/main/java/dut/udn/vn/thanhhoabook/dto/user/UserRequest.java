package dut.udn.vn.thanhhoabook.dto.user;

import dut.udn.vn.thanhhoabook.contans.user.EGender;
import dut.udn.vn.thanhhoabook.model.user.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Long id;
    private Account account;
    private String fullName;
    private LocalDate birthday;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    private String image;
}
