package com.example.coltuiservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("APPLICANT-SERVICE")
public interface ApplicantClient {
    @GetMapping("/applicant-service/applicantsInfo")
    List<ApplicantInfo> getApplicantsInfo();
}
