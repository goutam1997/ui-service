package com.example.coltuiservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("DEPARTMENT-SERVICE")
public interface DepartmentClient {
    @GetMapping("/department-service/view-all")
    List<Department> viewDepartments();
}