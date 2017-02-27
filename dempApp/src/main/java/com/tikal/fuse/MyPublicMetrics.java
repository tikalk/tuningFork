package com.tikal.fuse;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Chaim on 27/02/2017.
 */
@Component
public class MyPublicMetrics implements PublicMetrics {
    private static int minimum = 1;
    private static int maximum = 10;
    @Override
    public Collection<Metric<?>> metrics() {
        HashSet<Metric<?>> set = new HashSet<Metric<?>>();
        set.add(new Metric<Long>("app.throughput",minimum + (long)(Math.random() * maximum)));
        set.add(new Metric<Long>("app.latency",minimum + (long)(Math.random() * maximum)));
        return set;
    }
}
