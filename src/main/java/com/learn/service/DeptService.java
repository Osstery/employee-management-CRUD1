package com.learn.service;

import com.learn.pojo.Dept;

import java.util.List;

public interface DeptService {
    /*
    查询全部部门数据
     */

    List<Dept> list();

    /*
    删除部门
     */

    void delete(Integer id);

    /*
    新增部门
     */
    void add(Dept dept);

    /*
    根据部门id查询部门信息
     */
    Dept getById(Integer id);

    /*
    修改部门信息
     */
    void update(Dept dept);


}
