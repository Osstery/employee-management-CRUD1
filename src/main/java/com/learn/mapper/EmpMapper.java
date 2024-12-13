package com.learn.mapper;

import com.learn.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /*
     * 查询总记录数
     * @return
     */
//    @Select("select count(*) from emp")
//    public Long count();

    /**
     * 分页查询员工数据
     * @param start
     * @param pageSize
     * @return
     */
//    @Select("select * from emp limit #{start}, #{pageSize}")
//    public List<Emp> page(Integer start, Integer pageSize);

    /**
     * 查询员工信息
     * @return
     */
    //@Select("select * from emp")
    public List<Emp> list( String name, Short gender,
                            LocalDate begin, LocalDate end);
    /**
     * 批量删除员工信息
     * @param ids
     */
    void delete(List<Integer> ids);

    void insert(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);

    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 根据部门id删除员工信息
     * @param deptId
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
