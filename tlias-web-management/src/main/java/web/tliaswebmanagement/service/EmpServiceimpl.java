package web.tliaswebmanagement.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import web.tliaswebmanagement.mapper.EmpExprMapper;
import web.tliaswebmanagement.mapper.EmpMapper;
import web.tliaswebmanagement.pojo.*;
import web.tliaswebmanagement.utils.JwtUtil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceimpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;


//    @Override
//    public PageResult<Emp> page(int page, int pageSize) {
//        Long total = empMapper.count();
//        List<Emp> rows = empMapper.page((page - 1) * pageSize, pageSize);
//
//        return new PageResult<Emp>(total, rows);
//    }
    
    @Override
    public PageResult<Emp> page(EmpQueryPrarm queryPrarm) {
        PageHelper.startPage(queryPrarm.getPage(), queryPrarm.getPageSize());


        List<Emp> empList = empMapper.list(queryPrarm);

        PageInfo<Emp> pageInfo = new PageInfo<>(empList);



        return new PageResult<Emp>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void delete(List<Integer> ids) {
        //删除员工
        empMapper.delete(ids);
        //删除员工工作经历信息
        empExprMapper.delete(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(Emp emp) {
        //更新员工信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        //更新员工工作经历信息
        //1.删除员工工作经历信息
        empExprMapper.delete(Arrays.asList(emp.getId()));
        //2.再添加员工工作经历信息
         List<EmpExpr> empExprList = emp.getExprList();
         if (!CollectionUtils.isEmpty(empExprList)) {
             //遍历集合将emp中的id赋值给empExpr中的empId
             empExprList.forEach(empExpr ->
                     empExpr.setEmpId(emp.getId()));
             empExprMapper.insertBatch(empExprList);
         }

    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPassword(emp.getUsername(), emp.getPassword());

        if(e != null) {
            log.info("登录成功,员工信息:{}",e);
            Map<String, Object> datemap = new HashMap<>();
            datemap.put("id", e.getId());
            datemap.put("username", e.getUsername());
            String token = JwtUtil.generateToken(datemap);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(),token);
        }
        return null;
    }





    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void save(Emp emp){
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.save(emp);


            //保存员工工作经历信息
            List<EmpExpr> empExprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(empExprList)) {
                //遍历集合将emp中的id赋值给empExpr中的empId
                empExprList.forEach(empExpr ->
                        empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(empExprList);
            }
        } finally {
            //保存添加员工操作日志信息
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "添加员工" + emp);
            empLogService.insert(empLog);

        }

    }
}
