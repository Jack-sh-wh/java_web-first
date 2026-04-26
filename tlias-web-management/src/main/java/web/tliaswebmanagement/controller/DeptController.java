package web.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tliaswebmanagement.pojo.Dept;
import web.tliaswebmanagement.pojo.Result;
import web.tliaswebmanagement.service.DeptService;

import java.util.List;
@Slf4j
@RestController
public class DeptController {

   @Autowired
   private DeptService deptService;

   @GetMapping("/dept")
    public Result findAll() {
       List<Dept> dept = deptService.findAll();
         return Result.success(dept);
   }

    @DeleteMapping("/dept")
    public Result delete(@RequestParam("id") Integer id) {
        log.info("删除部门数据：{}", id);
        deptService.delete(id);
        return Result.success();

    }


    @PostMapping("/dept")
    public Result add(@RequestBody Dept dept) {
        log.info("添加部门数据：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/dept/{id}")
    public Result getInfo(@PathVariable("id") Integer id) {
        log.info("查询部门数据：{}", id);
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }

    @PutMapping("/dept")
    public Result update(@RequestBody Dept dept){
        log.info("更新部门数据：{}", dept);
        deptService.update(dept);
        return Result.success();
    }


}
