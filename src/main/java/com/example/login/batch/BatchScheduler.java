package com.example.login.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduler {
//add plus
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