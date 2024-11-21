package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    public static List<Map<String, String>> read(String path, String sheetName) throws IOException {

        //To bring the data from file into the java program
        List<Map<String, String>> excelData = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(path);

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);

            //There can be many sheets.We are getting the sheet1 from Excel
            Sheet sheet = xssfWorkbook.getSheet(sheetName);

            //getting the number of actual rows that contain the data
            int numberOfActualRowsWithData = sheet.getPhysicalNumberOfRows();

            //getting the row number 0 as we will be using this for all maps
            Row headerRow = sheet.getRow(0);

            //A loop to go through all the rows
            for (int i = 1; i < numberOfActualRowsWithData; i++) {
                //getting each row one by one from the map
                Row currentRow = sheet.getRow(i);

                //A new map for every row
                Map<String, String> rowMap = new LinkedHashMap<>();

                //The actual number of cells that contains the data in each row
                int numberOfActualCellsWithData = currentRow.getPhysicalNumberOfCells();

                //A loop to go through all the cells
                for (int j = 0; j < numberOfActualCellsWithData; j++) {

                    //From header we get the keys
                    String key = headerRow.getCell(j).toString();

                    //From current row we get the values
                    String value = currentRow.getCell(j).toString();

                    //Store these keys and values in the map
                    rowMap.put(key, value);
                }
                //Store each map in the list
                excelData.add(rowMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelData;
    }

    public static List<Map<String, String>> read(String sheetName) throws IOException {
        return read(Constants.EXCEL_FILE_PATH, sheetName);
    }

    public static List<Map<String, String>> readFromDefaultSheet(String sheetName) throws IOException {
        return read(Constants.EXCEL_FILE_PATH, "Sheet1");

    }
    public static List<Map<String, String>> read()throws IOException{
        return read(Constants.EXCEL_FILE_PATH, "Sheet1");
    }
}

