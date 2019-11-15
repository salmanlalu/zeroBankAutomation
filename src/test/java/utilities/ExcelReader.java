package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	
	public static XSSFSheet sheet;
	public static XSSFWorkbook workbook;
	private XSSFCell cell;
	private XSSFRow row;

	public ExcelReader(String excelPath) {

		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheetAt(0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
	
	//counting the row number
	public int getRowCount(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);
		int rowCount = 0;

		try {
			sheet = workbook.getSheetAt(index);
			rowCount = sheet.getLastRowNum() + 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return rowCount;
	}

	//counting the column number
	public int getColCount(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);
		int colCount = 0;

		try {
			sheet = workbook.getSheetAt(index);
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return colCount;
	}

	//reading data from a cell that is determined by the row number and column number
	public String getCelldata(String sheetName, int rowNum, int colNum) {

		int index = workbook.getSheetIndex(sheetName);

		try {
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);

			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getNumericCellValue());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();

			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}

	}
}
