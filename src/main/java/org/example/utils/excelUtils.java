package org.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    public String[] getCustomerData(String filePath, String sheetName, int rowNumber) {
        List<List<String>> allData = getExcelData(filePath, sheetName);
        String[] customerData = new String[8];

        if (rowNumber < allData.size()) {
            customerData[0] = allData.get(rowNumber).get(2); // Username
            customerData[1] = allData.get(rowNumber).get(3); // Password
            customerData[2] = allData.get(rowNumber).get(4); // Username
            customerData[3] = allData.get(rowNumber).get(5); // Password
            customerData[4] = allData.get(rowNumber).get(6); // Username
            customerData[5] = allData.get(rowNumber).get(7); // Password
            customerData[6] = allData.get(rowNumber).get(8); // Username
            customerData[7] = allData.get(rowNumber).get(9); // Password
        }

        return customerData;
    }


    public static void writePasswordToExcel(String EXCEL_FILE_PATH,String sheetName, int rowNumber, int cellNumber, String newPassword) throws IOException {
        // Open the Excel file
        FileInputStream fileInputStream = new FileInputStream(EXCEL_FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        // Get the row and create it if it doesn't exist
        Row row = sheet.getRow(rowNumber);
        if (row == null) {
            row = sheet.createRow(rowNumber);
        }

        // Get the cell and create it if it doesn't exist
        Cell cell = row.getCell(cellNumber);
        if (cell == null) {
            cell = row.createCell(cellNumber);
        }

        // Set the new password in the cell
        cell.setCellValue(newPassword);

        // Close the input stream
        fileInputStream.close();

        // Write the changes back to the Excel file
        FileOutputStream fileOutputStream = new FileOutputStream(EXCEL_FILE_PATH);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
    }
    public static String readPasswordFromExcel(String EXCEL_FILE_PATH,String sheetName, int rowNumber, int cellNumber) throws IOException, NumberFormatException{
        FileInputStream fileInputStream = new FileInputStream(EXCEL_FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(rowNumber);
        Cell cell = row.getCell(cellNumber);
        String password = cell.getStringCellValue();

        workbook.close();
        fileInputStream.close();

        return password;
    }
}