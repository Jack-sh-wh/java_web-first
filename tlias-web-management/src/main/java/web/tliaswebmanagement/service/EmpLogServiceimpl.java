package web.tliaswebmanagement.service;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import web.tliaswebmanagement.mapper.EmpLogMapper;
import web.tliaswebmanagement.pojo.EmpLog;


@Service
public class EmpLogServiceimpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Override

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(EmpLog empLog) {

        empLogMapper.insert(empLog);
    }
}
