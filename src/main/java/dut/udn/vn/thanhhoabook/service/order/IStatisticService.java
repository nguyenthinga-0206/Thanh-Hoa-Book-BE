package dut.udn.vn.thanhhoabook.service.order;

import dut.udn.vn.thanhhoabook.dto.order.MyItem;
import dut.udn.vn.thanhhoabook.dto.order.TopBookResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface IStatisticService {
//    List<MyItem> reportReceipt(Date date, int limit);

    List<TopBookResponse> topBook();
}
