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
public class DependentCtrl {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enrollee/{enrolleeId}/dependent")
    public ApiResponse addEnrollee(@PathVariable("enrolleeId") long enrolleeId, @RequestBody Map<String, Object> data)
            throws Exception {
        enrollmentService.addDependent(enrolleeId, data);
        return ApiResponse.ok();
    }

    @PutMapping("/dependent/{id}")
    public ApiResponse updateEnrollee(@PathVariable("id") long id, @RequestBody Map<String, Object> data) throws Exception {
        enrollmentService.updateDependent(id, data);
        return ApiResponse.ok();
    }

    @GetMapping("/dependent/{id}")
    public ApiResponse getEnrollee(@PathVariable("id") long id) throws Exception {
        return ApiResponse.ok(enrollmentService.getDependent(id));
    }

    @DeleteMapping("/dependent/{id}")
    public ApiResponse deleteEnrollee(@PathVariable("id") long id) throws Exception {
        enrollmentService.deleteDependent(id);
        return ApiResponse.ok();
    }

}
