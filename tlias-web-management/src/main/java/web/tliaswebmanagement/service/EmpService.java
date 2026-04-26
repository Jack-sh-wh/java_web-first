package web.tliaswebmanagement.service;

import web.tliaswebmanagement.pojo.Emp;
import web.tliaswebmanagement.pojo.EmpQueryPrarm;
import web.tliaswebmanagement.pojo.LoginInfo;
import web.tliaswebmanagement.pojo.PageResult;

import java.util.List;


public interface EmpService {
    PageResult<Emp> page(EmpQueryPrarm queryPrarm);

    void delete(List<Integer> ids);
    void save(Emp emp) ;

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}
