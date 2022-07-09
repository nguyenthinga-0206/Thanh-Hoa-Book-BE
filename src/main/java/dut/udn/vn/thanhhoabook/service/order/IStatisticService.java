package dut.udn.vn.thanhhoabook.service.order;

import dut.udn.vn.thanhhoabook.dto.statisstic.StatisticResponse;
import dut.udn.vn.thanhhoabook.dto.statisstic.TopBookResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStatisticService {
    List<StatisticResponse> StatisticByMonth(Integer year);

    List<StatisticResponse> StatisticByQuarterly(Integer year);

    List<StatisticResponse> StatisticByYear();

    List<TopBookResponse> topBook();
}
