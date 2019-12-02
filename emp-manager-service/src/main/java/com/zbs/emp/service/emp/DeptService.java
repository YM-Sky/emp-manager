package com.zbs.emp.service.emp;

import com.github.pagehelper.PageInfo;
import com.zbs.emp.common.EasyUIResult;
import com.zbs.emp.pojo.Dept;
import com.zbs.emp.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class DeptService extends BaseService<Dept> {
    public EasyUIResult queryDept() {
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(super.queryAll());
        return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
