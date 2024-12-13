package com.learn.controller;

import com.learn.anno.Log;
import com.learn.pojo.Dept;
import com.learn.pojo.Result;
import com.learn.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

//    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "/depts", method = RequestMethod.GET) //指定请求方式为get
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");

        // 调用service查询部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);

    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门，id：{}", id);

        deptService.delete(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门，数据：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询部门数据，id：{}", id);

        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门，数据：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
