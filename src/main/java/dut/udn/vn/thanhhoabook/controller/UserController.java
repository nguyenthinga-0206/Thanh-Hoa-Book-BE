package dut.udn.vn.thanhhoabook.controller;

import dut.udn.vn.thanhhoabook.dto.user.UserRequest;
import dut.udn.vn.thanhhoabook.model.user.User;
import dut.udn.vn.thanhhoabook.service.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<List<User>> listUser() {
        List<User> userList = userService.getAll();
        return userList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Status> createUser(@Valid @RequestBody UserRequest userRequest) {
        if (userRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (userService.isEmailExists(userRequest.getEmail()) || userService.isPhoneExists(userRequest.getPhone())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = modelMapper.map(userRequest, User.class);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Status> updateUser(@Valid @RequestBody UserRequest userRequest) {
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
