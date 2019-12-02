package com.zbs.emp.service.emp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zbs.emp.common.EasyUIResult;
import com.zbs.emp.mapper.EmpMapper;
import com.zbs.emp.pojo.Emp;
import com.zbs.emp.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class EmpService extends BaseService<Emp> {

    @Autowired
    private EmpMapper empMapper;

    public EasyUIResult queryByEmpPage(Integer page, Integer rows, String empname, String phone, Integer deptid) {
        PageHelper.startPage(page,rows);
        Example example = new Example(Emp.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(empname)) {
            criteria.andLike("empname","%"+empname+"%");
        }
        if (!StringUtils.isEmpty(phone)) {
            criteria  .andLike("phone","%"+phone+"%");
        }
        if (deptid != 0) {
            criteria.andEqualTo("deptid",deptid);
        }

        List<Emp> emps = this.empMapper.selectByExample(example);
        PageInfo<Emp> pageInfo = new PageInfo<Emp>(emps);
        return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
    }

    public boolean addEmp(Emp emp) {
        emp.setId(null);
        emp.setBirthday(new Date());
        return super.save(emp) == 1;
    }

    public boolean updateEmp(Emp emp) {
        return updateSelective(emp) == 1;
    }

    public boolean deleteByEmpId(Integer id) {
        return super.deleteById(Long.valueOf(id)) == 1;
    }
}
