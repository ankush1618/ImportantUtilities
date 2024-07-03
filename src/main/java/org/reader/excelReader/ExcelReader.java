package org.reader.excelReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * To read an Excel file and store the column and row data in a map in Java,
 * you can use the Apache POI library. Here's an example code snippet that
 * demonstrates how to do this:
 *Key will be the RowNUm and Vaules will be in Key and Value form from the rows
 * Columnm name will be key and Cell value will be the Value
 */

public class ExcelReader {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\ankpal2\\IdeaProjects\\Automation\\ExcelComparison.xlsx";
        Map<String, Map<String, String>> data = new HashMap<>();
        try (FileInputStream file = new FileInputStream(new File(fileName))) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through each row in the sheet
            for (Row row : sheet) {
                Map<String, String> rowData = new HashMap<>();

                // Iterate through each cell in the row
                for (Cell cell : row) {
                    String key = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
                    String value = cell.getStringCellValue();
                    rowData.put(key, value);
                }

                // Add the row data to the map
                data.put(String.valueOf(row.getRowNum()), rowData);
            }

            // Print the map
            System.out.println(data);

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
