package com.tikal.fuse.config;

/**
 * Created by Chaim on 27/02/2017.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class MetricsConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsConfiguration.class);

    @Autowired PublicMetrics systemPublicMetrics;

//    @Scheduled(fixedDelay=5000)
//    public void dd(){
//        Collection<Metric<?>> metrics = systemPublicMetrics.metrics();
//        counterService.increment("a");
//        SortedMap<String, Counter> counters = metricRegistry.getCounters();
//        SortedMap<String, Gauge> gauges = metricRegistry.getGauges();
//
//    }


}
