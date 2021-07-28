package com.cloudwise.cactus_demo.job.elastic_job;//package com.mayikt.elastic_job;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="job")
@Data
public class JobConfig {
    private Integer baseSleepTimeMilliseconds;
    private Integer maxSleepTimeMilliseconds;
    private Integer maxRetries;
    private Integer sessionTimeoutMilliseconds;
    private Integer connectionTimeoutMilliseconds;
    private String zkServerList;
    private String namespace;

    private Integer job1Shards;
    private String job1Cron;

    private Integer job2Shards;
    private String job2Cron;

}