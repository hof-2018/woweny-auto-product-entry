package com.hof.wovenyautoproductentry.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Objects;

@Component
public class CSVReader {

    private static final String CSV_FILE_PATH = "export_2020-01-08(1).csv";

    public Iterable<CSVRecord> read(String filename, Character delimiter) throws IOException {
        Reader in = new FileReader(filename);
        return CSVFormat.DEFAULT
                .withDelimiter(delimiter)
                .withFirstRecordAsHeader()
                //.withAllowMissingColumnNames()
                .parse(in);
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        try {

            String fileUrl = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(CSV_FILE_PATH)).getPath();
            Iterable<CSVRecord> read = csvReader.read(fileUrl,';');
            System.out.println("Model\tPrice");
            int i = 1;
            for (CSVRecord record : read) {
                String model = record.get("model");
                String price = record.get("price");
                System.out.println(i + "  " + model + "\t" + price);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
