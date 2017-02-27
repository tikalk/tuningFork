package com.tikal.fuse.config;

/**
 * Created by Chaim on 27/02/2017.
 */

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ExportMetricReader;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.reader.MetricReader;
import org.springframework.boot.actuate.metrics.reader.MetricRegistryMetricReader;
import org.springframework.boot.actuate.metrics.statsd.StatsdMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class MetricsConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsConfiguration.class);


//    @Autowired
//    MetricRegistry metricRegistry;

    @Bean
    public CsvReporter configureReporters() {
        CsvReporter reporter = CsvReporter.forRegistry(metricRegistry).build(new File("/tmp/stat/out.csv"));
        reporter.start(5, TimeUnit.SECONDS);

        return reporter;
    }





    @Autowired
    private MetricRegistry metricRegistry;

    @Bean
    @ExportMetricReader
    public MetricReader metricReader() {
        return new MetricRegistryMetricReader(metricRegistry);
    }

//    @Bean
//    @ExportMetricWriter
//    public MetricWriter metricWriter() {
//        LOGGER.info("Configuring StatsdMetricWriter to export to {}:{}", host, port);
//        return new StatsdMetricWriter(host, port);
//    }
}
