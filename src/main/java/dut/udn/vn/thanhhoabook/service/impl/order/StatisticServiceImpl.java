package dut.udn.vn.thanhhoabook.service.impl.order;

import dut.udn.vn.thanhhoabook.dto.statisstic.IStatisticResponse;
import dut.udn.vn.thanhhoabook.dto.statisstic.ITopBookResponse;
import dut.udn.vn.thanhhoabook.dto.statisstic.StatisticResponse;
import dut.udn.vn.thanhhoabook.dto.statisstic.TopBookResponse;
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
    public List<StatisticResponse> StatisticByMonth(Integer year) {
        return statisticReponsitory.statisticByMonth(year)
                .stream()
                .map(this::convertStatistic)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatisticResponse> StatisticByQuarterly(Integer year) {
        return statisticReponsitory.statisticByQuarterly(year)
                .stream()
                .map(this::convertStatistic)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatisticResponse> StatisticByYear() {
        return statisticReponsitory.statisticByYear()
                .stream()
                .map(this::convertStatistic)
                .collect(Collectors.toList());
    }

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

    public StatisticResponse convertStatistic(IStatisticResponse response) {
        StatisticResponse statisticResponse = new StatisticResponse();
        statisticResponse.setMilestone(response.getMilestone());
        statisticResponse.setTotal(response.getTotal());
        return statisticResponse;
    }

}
