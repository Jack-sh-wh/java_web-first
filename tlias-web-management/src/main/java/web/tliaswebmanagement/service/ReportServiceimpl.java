package web.tliaswebmanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.tliaswebmanagement.mapper.EmpMapper;
import web.tliaswebmanagement.pojo.JobOption;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceimpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();

        List jobList = list.stream().map(m -> m.get("pos")).toList();
        List dataList = list.stream().map(m -> m.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    @Override
    public JobOption getEmpGenderData() {
        List<Map<String,Object>> list = empMapper.countEmpGenderData();

        List genderList = list.stream().map(m -> m.get("name")).toList();
        List dataList = list.stream().map(m -> m.get("value")).toList();
        return new JobOption(genderList,dataList);
    }


}
