package dut.udn.vn.thanhhoabook.utils;

import dut.udn.vn.thanhhoabook.security.service.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class Listener {

    private LocalDateTime dateTime = LocalDateTime.now();

    /**
     * Thực hiện trước khi thêm mới
     */
    @PrePersist
    public void preInser(TimeUser timeUser) {
        try {
            String user = null;
            MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userRequest != null) {
                user = userRequest.getAccount().getUsername();
            }
            timeUser.setTimeCreateFlag(dateTime);
            timeUser.setTimeUpdateFlag(dateTime);
            timeUser.setUserCreateFlag(user);
            timeUser.setUserUpdateFlag(user);
        } catch (Exception e) { }
    }

    /**
     * Thực hiện trước khi cập nhật lại
     */
    @PreUpdate
    public void preUpdate(TimeUser timeUser) {
        try {
            String user = null;
            MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userRequest != null) {
                user = userRequest.getAccount().getUsername();
            }
            timeUser.setTimeUpdateFlag(dateTime);
            timeUser.setUserUpdateFlag(user);
        } catch (Exception e) { }
    }
}
