package web.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import web.tliaswebmanagement.pojo.Emp;
import web.tliaswebmanagement.pojo.EmpQueryPrarm;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    //删除员工

    public void delete(List<Integer> ids);


    @Options(useGeneratedKeys = true, keyProperty = "id")//插入成功后，将id返回给emp对象，主键返回
    @Insert("INSERT INTO emp (username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
            "VALUES (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    public void save(Emp emp);

    public List<Emp> list(EmpQueryPrarm queryPrarm);


    public Emp getInfo(Integer id);

    public void update(Emp emp);

    //统计员工职位数据
    //MyBatis注解误报误，可以不去处理，不影响程序运行
    List<Map<String,Object>> countEmpJobData();
    //统计员工性别数据
    List<Map<String,Object>> countEmpGenderData();

    //根据用户名和密码查询员工统计员工职位数据
    @Select("SELECT id,username,name FROM emp WHERE username=#{username} AND password=#{password}")
    Emp selectByUsernameAndPassword(String username, String password);
}
