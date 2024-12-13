package com.learn.controller;

import com.learn.anno.Log;
import com.learn.pojo.PageBean;
import com.learn.pojo.*;
import com.learn.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询，参数：{}，{}，{}，{}，{}，{}", page, pageSize, name, gender, begin, end);
        // 调用service查询分页数据
        PageBean pageBean = empService.page(page, pageSize, name, gender,
                begin, end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除，id为{}", ids);

        empService.delete(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工，员工信息：{}", emp);
        empService.add(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询员工信息，id为{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息，员工信息为：{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
