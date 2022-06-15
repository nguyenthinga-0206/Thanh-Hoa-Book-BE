package dut.udn.vn.thanhhoabook.controller.users;

import dut.udn.vn.thanhhoabook.dto.user.ChangePasswordRequest;
import dut.udn.vn.thanhhoabook.dto.user.UserRequest;
import dut.udn.vn.thanhhoabook.model.user.User;
import dut.udn.vn.thanhhoabook.service.impl.user.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<List<User>> listUser() {
        List<User> userList = userService.getAll();
        return userList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("profile")
    public ResponseEntity<User> getUser(@RequestParam("email") String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.isPresent() ? new ResponseEntity<>(user.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<Status> createUser(@RequestBody UserRequest userRequest) {
        if (userRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (userService.isEmailExists(userRequest.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = modelMapper.map(userRequest, User.class);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Status> updateManagement(@RequestBody UserRequest userRequest) {
        Optional<User> userOptional = userService.getById(userRequest.getId());
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = modelMapper.map(userRequest, User.class);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Status> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        Boolean isSuccessful = this.userService.changePassword(changePasswordRequest);
        if (isSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<Status> updateProfile(@RequestBody UserRequest userRequest) {
        Optional<User> userOptional = userService.getById(userRequest.getId());
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = modelMapper.map(userRequest, User.class);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Status> deleteuUser(@RequestParam("id") Long id) {
        Optional<User> userOptional = userService.getById(id);
        if (userOptional.isPresent()) {
            userOptional.get().setDeleteFlag(true);
            this.userService.save(userOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
