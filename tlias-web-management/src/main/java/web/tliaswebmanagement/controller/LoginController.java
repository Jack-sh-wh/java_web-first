package web.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.tliaswebmanagement.pojo.Emp;
import web.tliaswebmanagement.pojo.LoginInfo;
import web.tliaswebmanagement.pojo.Result;
import web.tliaswebmanagement.service.EmpService;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;


    @PostMapping("/emp/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录，员工信息：{}", emp);

       LoginInfo loginInfo = empService.login(emp);
       if (loginInfo != null) {
           return Result.success(loginInfo);
       }
       return Result.error("登录失败");
    }

}
