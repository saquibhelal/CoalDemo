package com.qa.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static String TESTDATA_SHEET_PATH="C:\\Users\\Rehan\\git\\CoalDemo\\DemoCoal"
			+ "\\src\\main\\java\\com\\qa\\testdata\\TestData.xlsx";
			 
    //public static final String File_TestData = "TestData.xlsx"
		 
	private static XSSFSheet ExcelWSheet;
	 
    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

    
    public static void setExcelFile(String TESTDATA_SHEET_PATH,String SheetName) throws Exception{
    	try {
    		 
            // Open the Excel file
  
  FileInputStream ExcelFile = new FileInputStream(TESTDATA_SHEET_PATH);
  
  // Access the required test data sheet
  ExcelWBook = new XSSFWorkbook(ExcelFile);
  
  ExcelWSheet = ExcelWBook.getSheet(SheetName);
    }catch (Exception e){
    	 
    	 throw (e);
    	 
    	 }
    }
    
    public static String getCellData(int RowNum, int ColNum) throws Exception{
    	 
        try{
  
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
  
            String CellData = Cell.getStringCellValue();
  
            return CellData;
  
            }catch (Exception e){
  
  return"";
  
            }
  
      }
    
    public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception {
    	 
        try{
  
            Row  = ExcelWSheet.getRow(RowNum);
  
  Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
  
  if (Cell == null) {
  
  Cell = Row.createCell(ColNum);
  
  Cell.setCellValue(Result);
  
  } else {
  
  Cell.setCellValue(Result);
  
  }
    
  FileOutputStream fileOut = new FileOutputStream(TESTDATA_SHEET_PATH  );
  
  ExcelWBook.write(fileOut);

  fileOut.flush();

fileOut.close();

}catch(Exception e){

throw (e);

}

}
    
}
