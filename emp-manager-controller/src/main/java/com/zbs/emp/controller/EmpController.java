package com.zbs.emp.controller;

import com.zbs.emp.common.EasyUIResult;
import com.zbs.emp.pojo.Emp;
import com.zbs.emp.service.emp.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public ResponseEntity<EasyUIResult> queryByEmpPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "rows", defaultValue = "20") Integer rows,
                                                       @RequestParam(value = "empname", defaultValue = "", required = false) String empname,
                                                       @RequestParam(value = "phone", defaultValue = "", required = false) String phone,
                                                       @RequestParam(value = "deptid", defaultValue = "0", required = false) Integer deptid) {
        try {
            EasyUIResult easyUIResult = this.empService.queryByEmpPage(page, rows, empname, phone, deptid);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PostMapping
    public ResponseEntity<Void> addEmp(Emp emp) {
        try {
            boolean f = this.empService.addEmp(emp);
            if (!f){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateEmp(Emp emp) {
        try {
            boolean f = this.empService.updateEmp(emp);
            if (!f){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteByEmpId(@RequestParam(value = "id")Integer id) {
        try {
            boolean f = this.empService.deleteByEmpId(id);
            if (!f){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
