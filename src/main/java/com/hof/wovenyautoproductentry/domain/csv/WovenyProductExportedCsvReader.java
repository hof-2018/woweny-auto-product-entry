package com.hof.wovenyautoproductentry.domain.csv;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class WovenyProductExportedCsvReader implements CsvFileReader {

    public static final String CSV_FILE_PATH = "export_2020-01-08(1).csv";
    public static final String CVS_SPLIT_BY = ";";

    @Override
    public List<List<String>> read() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(CSV_FILE_PATH);
        String csvFile = Objects.requireNonNull(url).getPath();

        BufferedReader br = null;
        String line = "";
        List<List<String>> products = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] attributeArray = line.split(CVS_SPLIT_BY);
                products.add(Arrays.asList(attributeArray));

                List.of(attributeArray).forEach(System.out::println);
            }
        } catch (IOException e) {
            log.error("WovenyProductExportedCsvReader read exception thrown");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("WovenyProductExportedCsvReader buffer reader is not closed");
                }
            }
        }
        return products;
    }

}
