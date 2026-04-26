package web.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import web.tliaswebmanagement.pojo.Emp;
import web.tliaswebmanagement.pojo.EmpQueryPrarm;
import web.tliaswebmanagement.pojo.PageResult;
import web.tliaswebmanagement.pojo.Result;
import web.tliaswebmanagement.service.EmpService;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RestController
public class EmpController {
    //注入EmpMapper
    @Autowired
    private EmpService empService;


//    @GetMapping("/emp")
//    public Result page(int page, int pageSize, String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//
//    }



    @GetMapping("/emp")
    public Result page(EmpQueryPrarm queryPrarm) {
        PageResult<Emp> pageResult = empService.page(queryPrarm);
        return Result.success(pageResult);
    }

    @PostMapping("/emp")
    public Result save(@RequestBody Emp emp) {
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping("/emp")
    public Result delete(@RequestParam List<Integer> ids) {
        empService.delete(ids);
        return Result.success();

    }

    //修改员工信息的时候的信息回显
    //根据id员工信息
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping("/emp")
    public Result update(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }




}
