package dut.udn.vn.thanhhoabook.controller;

import dut.udn.vn.thanhhoabook.model.user.User;
import dut.udn.vn.thanhhoabook.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> listUser(){
        List<User> userList = userService.getAll();
        return userList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
