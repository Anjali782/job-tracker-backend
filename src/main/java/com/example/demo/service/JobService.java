package com.example.demo.service;

import com.example.demo.dto.JobRequest;
import com.example.demo.dto.JobResponse;
import com.example.demo.entity.Job;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public JobResponse saveJob(JobRequest request){
        Job job = new Job();
        job.setCompanyName(request.getCompanyName());
        job.setCtc(request.getCtc());
        job.setRole(request.getRole());
        job.setStatus(request.getStatus());
        job.setAppliedDate(request.getAppliedDate());
        job.setNotes(request.getNotes());
        Job saved = jobRepository.save(job);
        return mapToResponse(saved);
    }

    public void deleteJob(int id){
        jobRepository.deleteById(id);
    }

    public JobResponse getJobById(int id){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + id));

        return mapToResponse(job);
    }

    public List<JobResponse> getAllJobs(){
        List<Job> jobs = jobRepository.findAll();
        List<JobResponse> responseList = new ArrayList<>();

        for(Job job : jobs){
            responseList.add(mapToResponse(job));
        }

        return responseList;
    }

    public JobResponse updateJob(int id, JobRequest updatedJob){
        Job existingJob = jobRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Job not found with id " + id));

        existingJob.setCompanyName(updatedJob.getCompanyName());
        existingJob.setRole(updatedJob.getRole());
        existingJob.setStatus(updatedJob.getStatus());
        existingJob.setCtc(updatedJob.getCtc());
        existingJob.setAppliedDate(updatedJob.getAppliedDate());
        existingJob.setNotes(updatedJob.getNotes());
        Job updated = jobRepository.save(existingJob);
        return mapToResponse(updated);
    }

    private JobResponse mapToResponse(Job job){
        JobResponse res = new JobResponse();
        res.setId(job.getId());
        res.setCompanyName(job.getCompanyName());
        res.setRole(job.getRole());
        res.setStatus(job.getStatus());
        res.setCtc(job.getCtc());
        res.setAppliedDate(job.getAppliedDate());
        res.setNotes(job.getNotes());
        return res;
    }
}