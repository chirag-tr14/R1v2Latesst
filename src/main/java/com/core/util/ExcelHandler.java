package com.core.util;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandler {
	
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;

	public ExcelHandler(File ExcelPath) {
	
		try {

			//fis = new FileInputStream(src);
			workbook = new XSSFWorkbook(ExcelPath);
		}

		catch (Exception e) {
			System.out.println("Exception is :" + e.getMessage());
		}

	}

	public String getData(int sheetIndex, int row, int coloumn)
	{
		String data = workbook.getSheetAt(sheetIndex).getRow(row).getCell(coloumn).getStringCellValue();
		return data;
	}

	public String getData(String sheetName, int row, int coloumn) {
		String data = workbook.getSheet(sheetName).getRow(row).getCell(coloumn).getStringCellValue().toString();
		return data;
	}

	public int getRowCount(int Index) {
		int row = workbook.getSheetAt(Index).getPhysicalNumberOfRows();
		row = row++;
		return row;

	}
	
	

	
    
    
}

