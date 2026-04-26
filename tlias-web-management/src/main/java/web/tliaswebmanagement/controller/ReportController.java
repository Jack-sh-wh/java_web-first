package web.tliaswebmanagement.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.tliaswebmanagement.pojo.JobOption;
import web.tliaswebmanagement.pojo.Result;
import web.tliaswebmanagement.service.ReportService;

@Slf4j
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;
    @GetMapping("/report/empJobData")
    public Result getEmpJobData(){

        log.info("获取员工职位分布数据成功");
        JobOption jobOption = reportService.getEmpJobData();

        return Result.success(jobOption);
    }


    @GetMapping("/report/empGenderData")
    public Result getEmpGenderData(){
        log.info("获取员工性别分布数据成功");
        JobOption jobOption = reportService.getEmpGenderData();
        return Result.success(jobOption);
    }
}
