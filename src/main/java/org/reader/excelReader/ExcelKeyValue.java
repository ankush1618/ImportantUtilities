package org.reader.excelReader;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;

import static org.reader.excelReader.ExcelDataComparator.*;

public class ExcelKeyValue {

    public static void main(String[] args) throws IOException {
        String filepath= "ExcelComparison.xlsx";
        Map<String,List<String>> map=getDatafromExcel(filepath,1);
//        System.out.println(map);
//        System.out.println(map.get("Age"));
        Map<String,List<String>> map1=getDatafromExcel(filepath,2);
        System.out.println(map1);
//        System.out.println(map1.get("Name"));
        ExcelDataComparator com=new ExcelDataComparator();
        int result=com.compare(map,map1);
        if(result==0){
            System.out.println("Data is Equal");
        }else{
            System.out.println("Data is not Equal");
        }
        String resultFile = "Result.xlsx";
        Workbook resultWorkbook = new XSSFWorkbook();
//        Sheet resSheet=resultWorkbook.createSheet("Result_Su");
//        Sheet resSh=resultWorkbook.createSheet("Result");
        Sheet res=resultWorkbook.createSheet("Result_Summary");
        //generateComparisonResults(map,map1,resSheet);
        //generateResults(map,map1,resSh);
        //this works fine for comparison
        comparisonResult(map,map1,res);
       // generateComparison(map,map1,resSheet);
        // Write the result to a new Excel file
        FileOutputStream fileOut = new FileOutputStream(resultFile);
        resultWorkbook.write(fileOut);
        fileOut.close();

        System.out.println("Comparison result saved to " + resultFile);
    }
}
