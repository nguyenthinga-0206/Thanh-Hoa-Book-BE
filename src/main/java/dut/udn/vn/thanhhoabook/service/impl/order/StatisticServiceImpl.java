package dut.udn.vn.thanhhoabook.service.impl.order;

import dut.udn.vn.thanhhoabook.dto.order.ITopBookResponse;
import dut.udn.vn.thanhhoabook.dto.order.TopBookResponse;
import dut.udn.vn.thanhhoabook.reponsitory.order.IStatisticReponsitory;
import dut.udn.vn.thanhhoabook.service.order.IStatisticService;
import dut.udn.vn.thanhhoabook.dto.order.MyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements IStatisticService {

    @Autowired
    private IStatisticReponsitory statisticReponsitory;

//    @Override
//    public List<MyItem> reportReceipt(Date date, int limit) {
//        List<MyItem> list = new ArrayList<>();
//        for (int i = limit - 1; i >= 0; i--) {
//            Date d = subDays(date, i);
//            MyItem myItem = new MyItem();
//            myItem.setTime(covertD2S(d));
//            myItem.setValue(statisticReponsitory.reportReceipt(d));
//            list.add(myItem);
//        }
//        return list;
//    }

    @Override
    public List<TopBookResponse> topBook() {
        return statisticReponsitory.topBook()
                .stream()
                .map(this::convertResponse)
                .collect(Collectors.toList());
    }

    public TopBookResponse convertResponse(ITopBookResponse bookResponse) {
        TopBookResponse topBookResponse = new TopBookResponse();
        topBookResponse.setName(bookResponse.getName());
        topBookResponse.setCount(bookResponse.getCount());
        topBookResponse.setTotal(bookResponse.getTotal());
        return topBookResponse;
    }

    public static Date subDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    private String covertD2S(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }
}
