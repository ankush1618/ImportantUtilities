package org.reader.excelReader;

import db.jdbc.connection.Utility;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelDataComparator implements Comparator<Map<String, List<String>>> {
    private static final String RED_COLOR_HEX = "FF0000";

    public static Map<String, List<String>> getDatafromExcel(String filepath, int sheetNum)  {
        Map<String,List<String>> data=new HashMap<>();
        //loading the excel file from resource folder
        InputStream input=Utility.class.getClassLoader().getResourceAsStream(filepath);
        try(Workbook wb=new XSSFWorkbook(input)){
           // Workbook wb=new XSSFWorkbook(fs);
            Sheet sh=wb.getSheetAt(sheetNum);
            //Iterate through first row to get the column headers
            //first row will contain header
            Row rowHeader=sh.getRow(0);
            //storing headers to the map
            for(Cell cell: rowHeader){
                String columnName=cell.getStringCellValue();
                //System.out.println(columnName);
                //put the Column Names in the Map with empty values in the start
                data.put(columnName,new ArrayList<>());
            }

            //now Iterate through each row in the Sheet
            for(Row row:sh){

                //ignore the first row
                if(row.getRowNum()==0){
                    continue;
                }
                //Iterate through each cell in a row
                for(Cell cell: row){
                    //get column Name
                    String colName=sh.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
                    //get Value from each cell
                    String value = "";

                    // Handle different cell types
                    switch (cell.getCellType()) {
                        case STRING:
                            value = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            value = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            value = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case BLANK:
                            value = "";
                            break;
                        default:
                            value = "";
                            break;
                    }
                    data.get(colName).add(value);
                }
            }
            // System.out.println(data);
            wb.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return data;
    }
    @Override
    public int compare(Map<String, List<String>> map1, Map<String, List<String>> map2) {
        // Compare the values for each key in the maps
        for (String key : map1.keySet()) {
            List<String> list1 = map1.get(key);
            List<String> list2 = map2.get(key);

            // Compare the lists
            if (!list1.equals(list2)) {
                return -1; // Data is not equal
            }
        }

        return 0; // Data is equal
    }

    static void generateComparisonResult(Map<String, List<String>> data1,
                                         Map<String, List<String>> data2,
                                         Sheet resultSheet){
        //create a header row in the result Sheet
        Row rowheader=resultSheet.createRow(0);
        int colIndex=0;

        for(String columnName: data1.keySet()){
            Cell cell=rowheader.createCell(colIndex);
            cell.setCellValue(columnName);
            colIndex++;
        }

        //Iterate through each row in file1 and file2
        int rowIndex=1;
        for(int i=0;i<data1.get("Name").size();i++){
            Row resultRow=resultSheet.createRow(rowIndex);
            colIndex=0;
            for(String columnName: data1.keySet()){
                Cell cell=resultRow.createCell(colIndex);
                //get the value from file1 and file2
                String left_Value=data1.get(columnName).get(i);
                String right_Value=data2.get(columnName).get(i);

                //set the Cell value in the result Sheet
                cell.setCellValue(left_Value);

                //compare the Values and Update the cell value if mismatched
                if(!left_Value.equals(right_Value)){
                    cell.setCellValue(left_Value);
                    cell.setCellStyle(getRedCellStyle(resultSheet.getWorkbook()));
                }
                colIndex++;
            }
            rowIndex++;
        }
    }

     static void generateComparison(Map<String, List<String>> data1, Map<String, List<String>> data2, Sheet resultSheet) {
        // Create header row in the result sheet
        Row headerRow = resultSheet.createRow(0);
        int columnIndex = 0;
        for (String columnName : data1.keySet()) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue("Left_" + columnName);
            columnIndex++;
        }
        for (String columnName : data2.keySet()) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue("Right_" + columnName);
            columnIndex++;
        }

        // Iterate through each row in data1 and data2
        int rowIndex = 1;
        int maxRows = Math.max(data1.get("Name").size(), data2.get("Name").size());
        for (int i = 0; i < maxRows; i++) {
            Row resultRow = resultSheet.createRow(rowIndex);
            columnIndex = 0;

            for (String columnName : data1.keySet()) {
                Cell cell = resultRow.createCell(columnIndex);

                // Get the values from data1 and data2
                List<String> values1 = data1.get(columnName);
                List<String> values2 = data2.get(columnName);

                // Set the cell value in the result sheet
                if (i < values1.size()) {
                    cell.setCellValue(values1.get(i));
                }

                columnIndex++;
            }

            for (String columnName : data2.keySet()) {
                Cell cell = resultRow.createCell(columnIndex);

                // Get the values from data1 and data2
                List<String> values1 = data1.get(columnName);
                List<String> values2 = data2.get(columnName);

                // Set the cell value in the result sheet
                if (i < values2.size()) {
                    cell.setCellValue(values2.get(i));
                }

                columnIndex++;
            }

            // Compare the values and update the cell value if mismatched
            for (int j = 0; j < resultRow.getLastCellNum(); j++) {
                Cell cell = resultRow.getCell(j);
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String cellValue = cell.getStringCellValue();
                    if (cellValue.contains("|")) {
                        cell.setCellValue(cellValue);
                        cell.setCellStyle(getRedCellStyle(resultSheet.getWorkbook()));
                    }
                }
            }

            rowIndex++;
        }
    }


    //working Code
     static void generateComparisonResults(Map<String, List<String>> data1, Map<String, List<String>> data2, Sheet resultSheet) {
        // Create header row in the result sheet
        Row headerRow = resultSheet.createRow(0);
        int columnIndex = 0;
        for (String columnName : data1.keySet()) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue("Left_" + columnName);
            columnIndex++;
        }
        for (String columnName : data2.keySet()) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue("Right_" + columnName);
            columnIndex++;
        }

        // Iterate through each row in data1 and data2
        int rowIndex = 1;
        int maxRows = Math.max(data1.get("Name").size(), data2.get("Name").size());
        for (int i = 0; i < maxRows; i++) {
            Row resultRow = resultSheet.createRow(rowIndex);
            columnIndex = 0;

            for (String columnName : data1.keySet()) {
                Cell cell = resultRow.createCell(columnIndex);

                // Get the values from data1 and data2
                List<String> values1 = data1.get(columnName);
                List<String> values2 = data2.get(columnName);

                // Set the cell value in the result sheet
                if (i < values1.size()) {
                    cell.setCellValue(values1.get(i));
                }

                // Compare the values and update the cell value if mismatched
                if (i < values1.size() && i < values2.size() && !values1.get(i).equals(values2.get(i))) {
                    cell.setCellValue(values1.get(i));
                    cell.setCellStyle(getRedCellStyle(resultSheet.getWorkbook()));
                }

                columnIndex++;
            }

            for (String columnName : data2.keySet()) {
                Cell cell = resultRow.createCell(columnIndex);

                // Get the values from data1 and data2
                List<String> values1 = data1.get(columnName);
                List<String> values2 = data2.get(columnName);

                // Set the cell value in the result sheet
                if (i < values2.size()) {
                    cell.setCellValue(values2.get(i));
                }

                // Compare the values and update the cell value if mismatched
                if (i < values1.size() && i < values2.size() && !values1.get(i).equals(values2.get(i))) {
                    cell.setCellValue(values2.get(i));
                    cell.setCellStyle(getRedCellStyle(resultSheet.getWorkbook()));
                }

                columnIndex++;
            }

            rowIndex++;
        }
    }
    static void comparisonResult(Map<String, List<String>> data1, Map<String, List<String>> data2, Sheet resultSheet) {
        // Create header row in the result sheet
        Row headerRow = resultSheet.createRow(0);
        int columnIndex = 0;
        for (String columnName : data1.keySet()) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue("Left_" + columnName);
            columnIndex++;
        }
        for (String columnName : data2.keySet()) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue("Right_" + columnName);
            columnIndex++;
        }

        // Get the unique column names from both data1 and data2
        Set<String> columnNames = new HashSet<>(data1.keySet());
        columnNames.addAll(data2.keySet());

        // Iterate through each row in data1 and data2
        int rowIndex = 1;
        int maxRows = getMaxRows(data1, data2);
        for (int i = 0; i < maxRows; i++) {
            Row resultRow = resultSheet.createRow(rowIndex);
            columnIndex = 0;

            for (String columnName : columnNames) {
                Cell cell = resultRow.createCell(columnIndex);

                // Get the values from data1 and data2
                List<String> values1 = data1.getOrDefault(columnName, Collections.emptyList());
                List<String> values2 = data2.getOrDefault(columnName, Collections.emptyList());

                // Set the cell value in the result sheet
                if (i < values1.size()) {
                    cell.setCellValue(values1.get(i));
                }

                // Compare the values and update the cell value if mismatched
                if (i < values1.size() && i < values2.size() && !values1.get(i).equals(values2.get(i))) {
                    cell.setCellValue(values1.get(i));
                    cell.setCellStyle(getRedCellStyle(resultSheet.getWorkbook()));
                }

                columnIndex++;
            }

            for (String columnName : columnNames) {
                Cell cell = resultRow.createCell(columnIndex);

                // Get the values from data1 and data2
                List<String> values1 = data1.getOrDefault(columnName, Collections.emptyList());
                List<String> values2 = data2.getOrDefault(columnName, Collections.emptyList());

                // Set the cell value in the result sheet
                if (i < values2.size()) {
                    cell.setCellValue(values2.get(i));
                }

                // Compare the values and update the cell value if mismatched
                if (i < values1.size() && i < values2.size() && !values1.get(i).equals(values2.get(i))) {
                    cell.setCellValue(values2.get(i));
                    cell.setCellStyle(getRedCellStyle(resultSheet.getWorkbook()));
                }

                columnIndex++;
            }

            rowIndex++;
        }
    }

    private static int getMaxRows(Map<String, List<String>> data1, Map<String, List<String>> data2) {
        int maxRows = 0;
        for (List<String> values : data1.values()) {
            maxRows = Math.max(maxRows, values.size());
        }
        for (List<String> values : data2.values()) {
            maxRows = Math.max(maxRows, values.size());
        }
        return maxRows;
    }

    static void generateResults(Map<String, List<String>> data1, Map<String, List<String>> data2, Sheet resultSheet) {
        // Create header row in the result sheet
        Row headerRow = resultSheet.createRow(0);
        int columnIndex = 0;
        for (String columnName : data1.keySet()) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue("Left_" + columnName);
            columnIndex++;
        }
        for (String columnName : data2.keySet()) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue("Right_" + columnName);
            columnIndex++;
        }

        // Get the unique column names from both data1 and data2
        Set<String> columnNames = new HashSet<>(data1.keySet());
        columnNames.addAll(data2.keySet());

        // Iterate through each row in data1 and data2
        int rowIndex = 1;
        int maxRows = Math.max(data1.get("Name").size(), data2.get("Name").size());
        for (int i = 0; i < maxRows; i++) {
            Row resultRow = resultSheet.createRow(rowIndex);
            columnIndex = 0;

            for (String columnName : columnNames) {
                Cell cell = resultRow.createCell(columnIndex);

                // Get the values from data1 and data2
                List<String> values1 = data1.getOrDefault(columnName, Collections.emptyList());
                List<String> values2 = data2.getOrDefault(columnName, Collections.emptyList());

                // Set the cell value in the result sheet
                if (i < values1.size()) {
                    cell.setCellValue(values1.get(i));
                }

                // Compare the values and update the cell value if mismatched
                if (i < values1.size() && i < values2.size() && !values1.get(i).equals(values2.get(i))) {
                    cell.setCellValue(values1.get(i));
                    cell.setCellStyle(getRedCellStyle(resultSheet.getWorkbook()));
                }

                columnIndex++;
            }

            for (String columnName : columnNames) {
                Cell cell = resultRow.createCell(columnIndex);

                // Get the values from data1 and data2
                List<String> values1 = data1.getOrDefault(columnName, Collections.emptyList());
                List<String> values2 = data2.getOrDefault(columnName, Collections.emptyList());

                // Set the cell value in the result sheet
                if (i < values2.size()) {
                    cell.setCellValue(values2.get(i));
                }

                // Compare the values and update the cell value if mismatched
                if (i < values1.size() && i < values2.size() && !values1.get(i).equals(values2.get(i))) {
                    cell.setCellValue(values2.get(i));
                    cell.setCellStyle(getRedCellStyle(resultSheet.getWorkbook()));
                }

                columnIndex++;
            }

            rowIndex++;
        }
    }


//    private static void highlightMismatchedCells(Sheet sheet) {
//        // Iterate through each row in the sheet
//        for (Row row : sheet) {
//            // Skip the first row (header row)
//            if (row.getRowNum() == 0) {
//                continue;
//            }
//
//            // Iterate through each cell in the row
//            for (Cell cell : row) {
//                // Check if the cell has a red font color
//                if (cell.getCellStyle().getFont().getColor().getHexString().equals(RED_COLOR_HEX)) {
//                    // Set the cell background color to red
//                    cell.getCellStyle().setFillForegroundColor(IndexedColors.RED.getIndex());
//                    cell.getCellStyle().setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                }
//            }
//        }
//    }

    //Provide Cell Content the Red color
    private static CellStyle getRedCellStyle(Workbook wb){
       CellStyle redCellStyle= wb.createCellStyle();
       Font redfont=wb.createFont();
       redfont.setColor(IndexedColors.RED.getIndex());
       redCellStyle.setFont(redfont);
       return redCellStyle;
    }
}

