package web.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import web.tliaswebmanagement.pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {

   // @Insert("INSERT INTO emp_expr(`emp_id`,`begin`,`end`,`company`,`job`) VALUES(#{empId},#{begin},#{end},#{company},#{job})")
    void insertBatch(List<EmpExpr> empExprList);



    void delete(List<Integer> ids);
}
