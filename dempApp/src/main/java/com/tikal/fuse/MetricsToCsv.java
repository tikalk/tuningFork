package com.tikal.fuse;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chaim on 27/02/2017.
 */
@Component
public class MetricsToCsv {

    @Autowired private List<PublicMetrics> publicMetrics;

    @Value("${csv.dir}")
    private String csvDir;

    private String fileName = "metric-app-demo.csv";


    private static int minimum = 1;
    private static int maximum = 10;


//    public static final DateTimeFormatter datetimeFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /**
     * -XX:+UseSerialGC (1), -XX:+USeParNewGC (2), –XX:+UseG1GC (3)
     * <p>
     * format of file is: cores, garbageCollectorType, throughput, latency
     *
     *
     * @throws FileNotFoundException
     */
    @Scheduled(fixedDelay = 10000)
    public void saveCsv() throws FileNotFoundException {
        File readFileBck = new File((csvDir + "/" + fileName + ".bck"));
        if (readFileBck.exists()) {
            readFileBck.delete();
        }
        File readFile = new File(csvDir + "/" + fileName);
        if (readFile.exists()){
            readFile.renameTo(readFileBck);
        }
        File writeFile = new File(csvDir + "/" + fileName);
        CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
        csvWriterSettings.setEscapeUnquotedValues(true);
        csvWriterSettings.setQuoteAllFields(true);
        csvWriterSettings.setHeaderWritingEnabled(true);

        FileOutputStream out = new FileOutputStream(writeFile);
        List<String> headers = Arrays.asList("app_id","i_cpu","i_ram","i_xx","i_th","o_cpu","o_ram","o_syload","o_thpic","o_gc","o_throughput","o_latency");
        csvWriterSettings.setHeaders(headers.toArray(new String[headers.size()]));
        CsvWriter writer = new CsvWriter(out, csvWriterSettings);
        Map<String, Object> csvRow = new HashMap<>();

        if (readFileBck.exists()) {
            CsvParserSettings csvParserSettings = new CsvParserSettings();
            csvParserSettings.getFormat().setLineSeparator("\n");

            Reader reader = new InputStreamReader(new FileInputStream(readFileBck));
            CsvParser parser = new CsvParser(csvParserSettings);
            parser.beginParsing(reader);

            String[] fileHeaders = parser.parseNext();
            String[] row;
            while ((row = parser.parseNext()) != null) {
                for (int pos=0; pos<row.length; pos++){
                    String entry = row[pos];
                    csvRow.put(fileHeaders[pos],entry);
                }
                writer.writeRow(csvRow);
            }

            parser.stopParsing();
            readFileBck.delete();
        }


        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        int garbageCollectorType = 0;
        if (arguments.stream().filter(s -> s.equals("-XX:+UseSerialGC")).count() == 1) {
            garbageCollectorType = 1;
        } else if (arguments.stream().filter(s -> s.equals("-XX:+USeParNewGC")).count() == 1) {
            garbageCollectorType = 1;
        } else if (arguments.stream().filter(s -> s.equals("-XX:+UseG1GC")).count() == 1) {
            garbageCollectorType = 1;
        }

        csvRow.put("app_id","1");
        csvRow.put("i_cpu", Runtime.getRuntime().availableProcessors());
        csvRow.put("i_ram",2000);
        csvRow.put("i_xx",33);
        csvRow.put("i_th",23);
        csvRow.put("o_cpu",3);
        csvRow.put("o_ram",2000);
        csvRow.put("o_syload",3);
        csvRow.put("o_thpic",4);
        csvRow.put("o_gc",garbageCollectorType);
        csvRow.put("o_throughput",minimum + (long) (Math.random() * maximum));
        csvRow.put("o_latency",minimum + (long) (Math.random() * maximum));

        writer.writeRow(csvRow);
        writer.close();

//        publicMetrics.forEach(publicMetrics1 -> {
//            System.out.println(publicMetrics1.toString());
//        });

    }

}
