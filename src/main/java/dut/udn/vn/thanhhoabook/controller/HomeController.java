package dut.udn.vn.thanhhoabook.controller;

import dut.udn.vn.thanhhoabook.dto.user.LoginRequest;
import dut.udn.vn.thanhhoabook.dto.user.LoginResponse;
import dut.udn.vn.thanhhoabook.dto.user.RegisterRequest;
import dut.udn.vn.thanhhoabook.model.user.Account;
import dut.udn.vn.thanhhoabook.model.user.User;
import dut.udn.vn.thanhhoabook.security.jwt.JwtUtil;
import dut.udn.vn.thanhhoabook.security.service.MyUserDetails;
import dut.udn.vn.thanhhoabook.service.user.IAccountService;
import dut.udn.vn.thanhhoabook.service.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/login")
    private ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        String jwt = jwtUtil.generateJwtToken(userDetails);
        String role = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.joining());

        return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getFullName(), userDetails.getEmail(), userDetails.getUsername(),
                role, userDetails.getImage()));
    }

    @PostMapping("/register")
    private ResponseEntity<Status> register(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (userService.isEmailExists(registerRequest.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = new User(registerRequest.getEmail(), new Account(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getRole()));
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
