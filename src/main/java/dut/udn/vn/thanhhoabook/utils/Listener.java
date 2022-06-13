package dut.udn.vn.thanhhoabook.utils;

import dut.udn.vn.thanhhoabook.security.service.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class Listener {

    private LocalDateTime dateTime = LocalDateTime.now();

    private MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication();

    /**
     * Thực hiện trước khi thêm mới
     */
    @PrePersist
    public void preInser(TimeUser timeUser) {
        timeUser.setTimeCreateFlag(dateTime);
        timeUser.setTimeUpdateFlag(dateTime);
//        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication();
        System.out.println(userRequest);
        timeUser.setUserCreateFlag(userRequest.getAccount().getUsername());
        timeUser.setUserUpdateFlag(userRequest.getAccount().getUsername());
    }

    /**
     * Thực hiện trước khi cập nhật lại
     */
    @PreUpdate
    public void preUpdate(TimeUser timeUser) {
        timeUser.setTimeUpdateFlag(dateTime);
//        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        timeUser.setUserUpdateFlag(userRequest.getAccount().getUsername());
    }
}
