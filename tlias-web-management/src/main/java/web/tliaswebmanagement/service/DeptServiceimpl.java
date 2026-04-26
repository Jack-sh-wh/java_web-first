package web.tliaswebmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import web.tliaswebmanagement.mapper.DeptMapper;
import web.tliaswebmanagement.pojo.Dept;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DeptServiceimpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }

    @Override
    public void add(Dept dept) {
        deptMapper.add(dept);
    }

    @Override
    public Dept getInfo(Integer id) {
        return deptMapper.getInfo(id);
    }
    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }
}
