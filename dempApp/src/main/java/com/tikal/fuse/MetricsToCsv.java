package com.tikal.fuse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.impl.CsvEncoder;
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
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Scheduled(fixedDelay=10000)
    public void saveCsv() throws FileNotFoundException {
        String dateTime = datetimeFormater.format(LocalDateTime.now());
        CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
        csvWriterSettings.setEscapeUnquotedValues(true);
        csvWriterSettings.setQuoteAllFields(true);
        csvWriterSettings.setHeaderWritingEnabled(false);
        FileOutputStream out = new FileOutputStream(new File(String.format("%s/metircs-%s.csv",csvDir,dateTime)));
        List<String> headers = Arrays.asList("throughput","latency");
        csvWriterSettings.setHeaders(headers.toArray(new String[headers.size()]));
        CsvWriter writer = new CsvWriter(out, csvWriterSettings);
        Map<String, Object> csvRow = new HashMap<>();
        csvRow.put("throughput",minimum + (long)(Math.random() * maximum));
        csvRow.put("latency",minimum + (long)(Math.random() * maximum));
        writer.writeRow(csvRow);
        writer.close();

        publicMetrics.forEach(publicMetrics1 -> {
            System.out.println(publicMetrics1.toString());
        });

    }

}
