package com.hof.wovenyautoproductentry.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class CSVReader {

    public Iterable<CSVRecord> read(String filename) throws IOException {
        Reader in = new FileReader(filename);
        return CSVFormat.DEFAULT
                .withDelimiter(';')
                .withFirstRecordAsHeader()
                //.withAllowMissingColumnNames()
                .parse(in);
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        try {
            Iterable<CSVRecord> read = csvReader.read("/Users/fatih.yilmaz/Desktop/woveny.csv");
            System.out.println("Model\tPrice");
            for (CSVRecord record : read) {
                String model = record.get("model");
                String price = record.get("price");
                System.out.println(model + "\t" + price);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
