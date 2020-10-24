package com.health.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.api.common.ApiResponse;
import com.health.api.servce.EnrollmentService;

@RestController
@RequestMapping("/api")
public class EnrolleeCtrl {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enrollee")
    public ApiResponse addEnrollee(@RequestBody Map<String, Object> data) throws Exception {
        enrollmentService.addEnrollee(data);
        return ApiResponse.ok();
    }

    @PutMapping("/enrollee/{id}")
    public ApiResponse updateEnrollee(@PathVariable("id") long id, @RequestBody Map<String, Object> data) throws Exception {
        enrollmentService.updateEnrollee(id, data);
        return ApiResponse.ok();
    }

    @GetMapping("/enrollee/{id}")
    public ApiResponse getEnrollee(@PathVariable("id") long id) throws Exception {
        return ApiResponse.ok(enrollmentService.getEnrollee(id));
    }

    @DeleteMapping("/enrollee/{id}")
    public ApiResponse deleteEnrollee(@PathVariable("id") long id) throws Exception {
        enrollmentService.deleteEnrollee(id);
        return ApiResponse.ok();
    }

    @GetMapping("/enrollees")
    public ApiResponse getEnrollees() throws Exception {
        return ApiResponse.ok(enrollmentService.getEnrollees());
    }

}
