package dut.udn.vn.thanhhoabook.controller.order;

import dut.udn.vn.thanhhoabook.dto.statisstic.StatisticResponse;
import dut.udn.vn.thanhhoabook.dto.statisstic.TopBookResponse;
import dut.udn.vn.thanhhoabook.service.impl.order.StatisticServiceImpl;
import dut.udn.vn.thanhhoabook.utils.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/statistic")
public class StatisticController {

    @Autowired
    private StatisticServiceImpl statisticService;

    @GetMapping("/statistic-top")
    public ResponseEntity<List<TopBookResponse>> statisticTop() {
        List<TopBookResponse> topBook = statisticService.topBook();
        return new ResponseEntity<>(topBook, HttpStatus.OK);
    }

    @GetMapping("/statistic-by-month")
    public ResponseEntity<List<StatisticResponse>> statisticByMonth(@RequestParam(value = "year", required = false) Integer year) {
        if (year == null){
            year = Custom.getYearNow();
        }
        List<StatisticResponse> statistics = statisticService.StatisticByMonth(year);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @GetMapping("/statistic-by-quaterly")
    public ResponseEntity<List<StatisticResponse>> statisticByQuarterly(@RequestParam(value = "year", required = false) Integer year) {
        if (year == null){
            year = Custom.getYearNow();
        }
        List<StatisticResponse> statistics = statisticService.StatisticByQuarterly(year);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @GetMapping("/statistic-by-year")
    public ResponseEntity<List<StatisticResponse>> statisticByYear() {
        List<StatisticResponse> statistics = statisticService.StatisticByYear();
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
