package com.tikal.fuse;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chaim on 27/02/2017.
 */
@Component
public class MetricsToCsv {

    @Autowired  private  List<PublicMetrics> publicMetrics;

    @Value("${csv.dir}")
    private String csvDir;


    private static int minimum = 1;
    private static int maximum = 10;


    public static final DateTimeFormatter datetimeFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /**
     *
     * -XX:+UseSerialGC (1), -XX:+USeParNewGC (2), –XX:+UseG1GC (3)
     *
     * format of file is: cores, garbageCollectorType, throughput, latency
     * @throws FileNotFoundException
     */
    @Scheduled(fixedDelay=10000)
    public void saveCsv() throws FileNotFoundException {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        int garbageCollectorType = 0;
        if (arguments.stream().filter(s -> s.equals("-XX:+UseSerialGC")).count()==1){
            garbageCollectorType = 1;
        }
        else if (arguments.stream().filter(s -> s.equals("-XX:+USeParNewGC")).count()==1){
            garbageCollectorType = 1;
        }
        else if (arguments.stream().filter(s -> s.equals("-XX:+UseG1GC")).count()==1){
            garbageCollectorType = 1;
        }

        String dateTime = datetimeFormater.format(LocalDateTime.now());
        CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
        csvWriterSettings.setEscapeUnquotedValues(true);
        csvWriterSettings.setQuoteAllFields(true);
        csvWriterSettings.setHeaderWritingEnabled(true);
        FileOutputStream out = new FileOutputStream(new File(String.format("%s/metircs-%s.csv",csvDir,dateTime)));
        List<String> headers = Arrays.asList("cores", "garbageCollectorType", "throughput", "latency");
        csvWriterSettings.setHeaders(headers.toArray(new String[headers.size()]));
        CsvWriter writer = new CsvWriter(out, csvWriterSettings);
        Map<String, Object> csvRow = new HashMap<>();

        csvRow.put("cores",Runtime.getRuntime().availableProcessors());
        csvRow.put("garbageCollectorType",garbageCollectorType);
        csvRow.put("throughput",minimum + (long)(Math.random() * maximum));
        csvRow.put("latency",minimum + (long)(Math.random() * maximum));
        writer.writeRow(csvRow);
        writer.close();

//        publicMetrics.forEach(publicMetrics1 -> {
//            System.out.println(publicMetrics1.toString());
//        });

    }

}
