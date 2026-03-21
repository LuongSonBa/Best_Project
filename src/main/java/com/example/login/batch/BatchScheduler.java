package com.example.login.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job expireJob;

    public BatchScheduler(JobLauncher jobLauncher, Job expireJob) {
        this.jobLauncher = jobLauncher;
        this.expireJob = expireJob;
    }

    @Scheduled(fixedRate = 60000) 
    public void runJob() throws Exception {

        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(expireJob, params);
    }
}