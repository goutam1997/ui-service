package com.example.coltuiservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/ui-service")
@EnableFeignClients
public class ColtUiServiceApplication {
    // There are 3 options to access other service REST call
    // 1. RestTemplate, 2. EurekaClient, 3. FeignClient(Auto load balanced)
    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ColtUiServiceApplication.class);

    @Autowired
    private ApplicantClient applicantClient;

    @Autowired
    private DepartmentClient departmentClient;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/applicants")
    public List<String> getApplicants() {
        return restTemplate.getForObject("http://APPLICANT-SERVICE/applicant-service/applicants", List.class);
    }

    @GetMapping("/applicantsInfo")
    public List<ApplicantInfo> getApplicantsInfo() {
        logger.info("Get applicantsInfo from Applicant Service");
        return applicantClient.getApplicantsInfo();
    }

    @GetMapping("/departments")
    public List<Department> viewDepartments() {
        logger.info("Get departments from Department Service");
        return departmentClient.viewDepartments();
    }
    public static void main(String[] args) {
        SpringApplication.run(ColtUiServiceApplication.class, args);
    }

}
