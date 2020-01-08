package com.hof.wovenyautoproductentry.google;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSpreadSheetTest {

    private static Sheets sheetsService;

    // this id can be replaced with your spreadsheet id
    // otherwise be advised that multiple people may run this test and update the public spreadsheet
    private static final String SPREADSHEET_ID = "1lbmj4XCYva8xLDt25K5irPL_2SG8jBsvvf9Sj2P6Lc0";

    @BeforeAll
    public static void setup() throws GeneralSecurityException, IOException {
        sheetsService = SheetsServiceUtil.getSheetsService();
    }


    @Test
    public void read_woveny_info_thenReadSheetOk() throws IOException {
        List<String> ranges = Arrays.asList("A1", "E20bn");
        BatchGetValuesResponse readResult = sheetsService.spreadsheets().values().batchGet(SPREADSHEET_ID).setRanges(ranges).execute();

        ValueRange januaryTotal = readResult.getValueRanges().get(0);
        assertThat(januaryTotal.getValues().get(0).get(0)).isEqualTo("40");

        ValueRange febTotal = readResult.getValueRanges().get(1);
        assertThat(febTotal.getValues().get(0).get(0)).isEqualTo("25");

        ValueRange appendBody = new ValueRange().setValues(Arrays.asList(Arrays.asList("Total", "=E1+E4")));
        AppendValuesResponse appendResult = sheetsService.spreadsheets().values().append(SPREADSHEET_ID, "A1", appendBody).setValueInputOption("USER_ENTERED").setInsertDataOption("INSERT_ROWS").setIncludeValuesInResponse(true).execute();

        ValueRange total = appendResult.getUpdates().getUpdatedData();
        assertThat(total.getValues().get(0).get(1)).isEqualTo("65");
    }
}
