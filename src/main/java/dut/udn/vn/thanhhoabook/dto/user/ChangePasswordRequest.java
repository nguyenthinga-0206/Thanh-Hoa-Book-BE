package dut.udn.vn.thanhhoabook.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}
