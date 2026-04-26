package web.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.*;
import web.tliaswebmanagement.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("SELECT id,`name`,create_time,update_time FROM dept ORDER BY update_time DESC")
    List<Dept> findAll();

    @Delete("DELETE FROM dept WHERE id=#{id}")
    int delete(Integer id);

    @Insert("INSERT INTO dept(`name`,create_time,update_time) VALUES(#{name},now(),now())")
    void add(Dept dept);

    @Select("SELECT id,`name`,create_time,update_time FROM dept WHERE id=#{id}")
    Dept getInfo(Integer id);

    @Update("UPDATE dept SET `name`=#{name},update_time=now() WHERE id=#{id}")
    void update(Dept dept);


}
