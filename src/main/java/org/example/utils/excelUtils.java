package org.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class excelUtils {

    // Method to load the Excel file and return the data from a specific sheet
    public List<List<String>> getExcelData(String filePath, String sheetName) {
        List<List<String>> excelData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    String cellValue = formatter.formatCellValue(cell); // Get cell data as a String
                    rowData.add(cellValue);
                }
                excelData.add(rowData); // Add the row data to the list
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelData;
    }

    // Method to get username and password from the Excel data (assuming data starts from row 1)
    public String[] getLoginData(String filePath, String sheetName, int rowNumber) {
        List<List<String>> allData = getExcelData(filePath, sheetName);
        String[] loginData = new String[2];

        if (rowNumber < allData.size()) {
            loginData[0] = allData.get(rowNumber).get(0); // Username
            loginData[1] = allData.get(rowNumber).get(1); // Password
        }

        return loginData;
    }
}