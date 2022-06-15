package dut.udn.vn.thanhhoabook.dto.user;

import dut.udn.vn.thanhhoabook.contans.user.EGender;
import dut.udn.vn.thanhhoabook.contans.user.ERole;
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
    private ERole role;
    private String email;
}
