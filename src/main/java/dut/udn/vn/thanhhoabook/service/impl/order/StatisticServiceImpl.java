package dut.udn.vn.thanhhoabook.service.impl.order;

import dut.udn.vn.thanhhoabook.dto.order.ITopBookResponse;
import dut.udn.vn.thanhhoabook.dto.order.TopBookResponse;
import dut.udn.vn.thanhhoabook.reponsitory.order.IStatisticReponsitory;
import dut.udn.vn.thanhhoabook.service.order.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements IStatisticService {

    @Autowired
    private IStatisticReponsitory statisticReponsitory;

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

}
