package dut.udn.vn.thanhhoabook.utils;

import java.time.LocalDateTime;

public interface TimeUser {
    String getUserCreateFlag();

    void setUserCreateFlag(String userCreateFlag);

    LocalDateTime getTimeCreateFlag();

    void setTimeCreateFlag(LocalDateTime timeCreateFlag);

    String getUserUpdateFlag();

    void setUserUpdateFlag(String userUpdateFlag);

    LocalDateTime getTimeUpdateFlag();

    void setTimeUpdateFlag(LocalDateTime timeUpdateFlag);
}
