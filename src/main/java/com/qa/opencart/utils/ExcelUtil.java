package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.SheetUtil;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.WorksheetDocument;

import com.google.common.collect.ObjectArrays;

public class ExcelUtil {

	public static String Test_DATA_FILE = "D:\\EclipseWorkspace\\Oct2020POMSeries\\src\\test\\resources\\testdata\\DemoShopData.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		Object data[][] = null;
		try {
			FileInputStream fis = new FileInputStream(Test_DATA_FILE);
			book=WorkbookFactory.create(fis);   //with apache poi help create object pf workbook
			sheet = book.getSheet(sheetName); // pass the sheetname to workbook reference
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; // create object of data size

			for (int i = 0; i < sheet.getLastRowNum(); i++) { //to read row . using poi function ' getLastRowNum' to get row count
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) { //to read coumn . using poi function ' getLastCellNum' to get column count

					data[i][j] = sheet.getRow(i + 1).getCell(j).toString(); // converting data to string

				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
