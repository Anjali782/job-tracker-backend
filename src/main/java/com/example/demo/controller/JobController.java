package com.example.demo.controller;

import com.example.demo.dto.JobRequest;
import com.example.demo.dto.JobResponse;
import com.example.demo.entity.Job;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobResponse> getJob1(@PathVariable int id) {
        JobResponse job = jobService.getJobById(id);

        if(job != null){
            return ResponseEntity.ok(job);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobResponse>> getJobs(){
        List<JobResponse> jobs = jobService.getAllJobs();
        if(!jobs.isEmpty()){
            return ResponseEntity.ok(jobs);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/jobs")
    public ResponseEntity<JobResponse> addJob(@RequestBody JobRequest job){
        try{
            JobResponse addedJob = jobService.saveJob(job);
            return ResponseEntity.ok(addedJob);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id) {
        JobResponse job = jobService.getJobById(id);

        if (job == null) {
            return ResponseEntity.notFound().build();
        }

        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/jobs/{id}")
    public JobResponse updateJob(@PathVariable int id , @RequestBody JobRequest newJob){
        return jobService.updateJob(id, newJob);
    }
}
