//package com.ajay.jobImpl;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.ajay.job.Job;
//import com.ajay.job.JobRepository;
//import com.ajay.job.JobService;
////import com.ajay.job;
//
//
//@Service
//public class JobServiceImpl implements JobService {
//	
////	private List<Job> jobs = new ArrayList<>();
//	
//	JobRepository jobRepository;
//	
//	
//	public JobServiceImpl(JobRepository jobRepository) {
//		super();
//		this.jobRepository = jobRepository;
//	}
//
//
//
//
//	private long nextId = 1L;
//	
//
//	@Override
//	public List<Job> findAll() {
//		
//		return jobRepository.findAll();
//		
//	}
//
//
//	
//	
//	@Override
//	public void createJob(Job job) {
//		job.setId(nextId++);
//		jobRepository.save(job);
//		
//	}
//
//
//
//
//	@Override
//	public Job getJobById(Long id) {
//		return jobRepository.findById(id).orElse(null);
//	}
//
//
//
//
////	@Override
////	public boolean deleteJobById(Long id) {
////		Iterator<Job> iterator = jobs.iterator();
////		while(iterator.hasNext()) {
////			Job job = iterator.next();
////			if(job.getId()==id) {
////				iterator.remove();
////				return true;
////			}
////		}
////		return false;
////	}
////	
//	
//	
//	
//	
//	
//	
//	
//	
////	@Override
////	public boolean deleteJobById(Long id) {
////	    if (id == null) {
////	        return false; // Return early if id is null
////	    }
////	    Iterator<Job> iterator = jobs.iterator();
////	    while (iterator.hasNext()) {
////	        Job job = iterator.next();
////	        if (id.equals(job.getId())) { // Use equals() to safely compare Long objects
////	            iterator.remove();
////	            return true;
////	        }
////	    }
////	    return false;
////	}
//	
//	
//
//	@Override
//	public boolean deleteJobById(Long id) {
//		try {
//			jobRepository.deleteById(id);
//			return true;
//			
//		}catch(Exception e){
//			return false;
//			
//		}
//	}
//
//
//
//
//	@Override
//	public boolean updateJob(Long id, Job updatedJob) {
//	    if (id == null || updatedJob == null) {
//	        return false; // Return false if input is invalid
//	    }
//	    	Optional<Job> jobOptional = jobRepository.findById(id);
//	    
//	        if (jobOptional.isPresent()) { // Check if the job with the given ID exists
//	            // Update the job details
//	        	Job job = jobOptional.get();
//	            job.setTitle(updatedJob.getTitle());
//	            job.setDescription(updatedJob.getDescription());
//	            job.setMaxSalary(updatedJob.getMaxSalary());
//	            job.setLocation(updatedJob.getLocation());
//	            return true; // Return true after successful update
//	        }
//	
//	    return false; // Return false if no job with the given ID is found
//	}
//
//	
//	
//	
//	
//
//	
//
//}









// JobServiceImpl.java
package com.ajay.jobImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ajay.job.Job;
import com.ajay.job.JobRepository;
import com.ajay.job.JobService;
import com.ajay.job.external.Company;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
    	RestTemplate restTemplate = new RestTemplate();
    	Company company = restTemplate.getForObject("http://localhost:8082/companies/1", Company.class);
    	System.out.println("COMPANY" + company.getName());
    	System.out.println("COMPANY" + company.getId());
    	System.out.println();
    	System.out.println();
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
