package dut.udn.vn.thanhhoabook.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String jwt;
    private String fullName;
    private String username;
    private String role;
    private String image;
}