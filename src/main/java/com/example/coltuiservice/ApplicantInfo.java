package com.example.coltuiservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantInfo {
    private String name;
    private String email;
    private int mobile;
}