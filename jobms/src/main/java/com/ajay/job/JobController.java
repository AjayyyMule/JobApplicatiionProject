//  package com.ajay.job;
//  
//  
//  import java.util.List;
//  
//  import org.springframework.http.HttpStatus; import
//  org.springframework.http.HttpStatusCode; import
//  org.springframework.http.ResponseEntity; import
//  org.springframework.web.bind.annotation.DeleteMapping; import
//  org.springframework.web.bind.annotation.GetMapping; import
//  org.springframework.web.bind.annotation.PathVariable; import
//  org.springframework.web.bind.annotation.PostMapping; import
//  org.springframework.web.bind.annotation.PutMapping; import
//  org.springframework.web.bind.annotation.RequestBody; import
//  org.springframework.web.bind.annotation.RequestMapping; import
// org.springframework.web.bind.annotation.RequestMethod; import
//  org.springframework.web.bind.annotation.RestController;
//  
//  //below we can use RequestMapping at class level in such a way
//  
//  @RestController
//  
//  @RequestMapping("/jobs") public class JobController {
//  
//  
//  private JobService jobService;
//  
//  
//  
//  public JobController(JobService jobService) { super(); this.jobService =
//  jobService; }
//  
//  
//  @GetMapping("/jobs") public ResponseEntity<List<Job>> findAll(){
//  
//  return ResponseEntity.ok(jobService.findAll());
//  
//  }
//  
//  
//  @PostMapping("/jobs") public ResponseEntity<String> createJob(@RequestBody
//  Job job) { jobService.createJob(job); return new
//  ResponseEntity<>("Job added successfully!!!", HttpStatus.OK);
//  
//  }
//  
//  
//  
//  // @GetMapping("/{id}")
//  
//  @GetMapping("/jobs/{id}") public ResponseEntity<Job> getJobById(@PathVariable
//  Long id) {
//  
//  Job job = jobService.getJobById(id); if(job!= null) return new
//  ResponseEntity<>(job, HttpStatus.OK); return new
//  ResponseEntity<>(HttpStatus.NOT_FOUND);
//  
//  }
//  
//  
//  // @DeleteMapping("/{id}")
//  
//  @DeleteMapping("/jobs/{id}") public ResponseEntity<String>
//  deleteJob(@PathVariable Long id) {
//  
//  
//  boolean deleted = jobService.deleteJobById(id); if(deleted) return new
//  ResponseEntity<>("Job deleted successfully", HttpStatus.OK); return new
//  ResponseEntity<>(HttpStatus.NOT_FOUND);
//  
//  }
//  
//  
//  
//  // // @PutMapping("/{id}") // @RequestMapping(value = "/jobs/{id}", method =RequestMethod.PUT) // we can use RequestMapping at method level in such a way
//  
//  @PutMapping("/jobs/{id}") public ResponseEntity<String>
//  updateJob(@PathVariable Long id, @RequestBody Job updatedJob){ boolean
//  updated = jobService.updateJob(id, updatedJob); if(updated) return new
//  ResponseEntity<>("Job updated successfully!!!", HttpStatus.OK); return new
//  ResponseEntity<>(HttpStatus.NOT_FOUND);
//  
//  }
//  
//  
//  
//  }



















// JobController.java
package com.ajay.job;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);

        return new ResponseEntity<>("Job added successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        if (jobService.deleteJobById(id)) {
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        if (jobService.updateJob(id, updatedJob)) {
            return new ResponseEntity<>("Job updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
 



