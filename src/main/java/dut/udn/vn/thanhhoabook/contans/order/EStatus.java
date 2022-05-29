package dut.udn.vn.thanhhoabook.contans.order;

public enum EStatus {
    /*
     Chờ xác nhận
     */
    PENDING,
    /*
     Chờ lấy hàng
     */
    WAITING,
    /*
     Đang giao
     */
    DELIVERY,
    /*
     Đã giao
     */
    DELIVERED
}
