package dut.udn.vn.thanhhoabook.controller;

import dut.udn.vn.thanhhoabook.dto.user.LoginRequest;
import dut.udn.vn.thanhhoabook.dto.user.LoginResponse;
import dut.udn.vn.thanhhoabook.security.jwt.JwtUtil;
import dut.udn.vn.thanhhoabook.security.service.MyUserDetails;
import dut.udn.vn.thanhhoabook.service.user.IAccountService;
import dut.udn.vn.thanhhoabook.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api")
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    private ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        String jwt = jwtUtil.generateJwtToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getFullName(), userDetails.getUsername(),
                userDetails.getAuthorities().toString()));
    }

    @PostMapping("/register")
    private ResponseEntity<?> register() {
        return null;
    }

    @PostMapping("/logout")
    private ResponseEntity<?> logout() {
        return null;
    }
}
