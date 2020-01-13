package com.hof.wovenyautoproductentry.domain.csv;

import org.springframework.objenesis.ObjenesisHelper;

import java.util.List;

public interface CsvFileReader {

    List<List<String>> read();
}
