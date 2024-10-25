package Utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * This class provides utility methods for reading and writing Excel files using Apache POI.
 */
public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    /**
     * Constructor to initialize the ExcelUtility with the path to the Excel file.
     *
     * @param path the path of the Excel file
     */
    public ExcelUtility(String path) {
        this.path = path;
    }

    /**
     * Retrieves the count of rows in the specified sheet.
     *
     * @param sheetName the name of the sheet
     * @return the number of rows in the sheet
     * @throws IOException if there is an error reading the file
     */
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum(); // Get the last row number
        workbook.close();
        fi.close();
        return rowCount;
    }

    /**
     * Retrieves the count of cells in a specified row of the sheet.
     *
     * @param sheetName the name of the sheet
     * @param rownum    the row number
     * @return the number of cells in the specified row
     * @throws IOException if there is an error reading the file
     */
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellCount = row.getLastCellNum(); // Get the last cell number
        workbook.close();
        fi.close();
        return cellCount; // Return the cell count
    }

    /**
     * Retrieves the data from a specified cell in the sheet.
     *
     * @param sheetName the name of the sheet
     * @param rownum    the row number
     * @param column    the column number
     * @return the data in the specified cell as a string
     * @throws IOException if there is an error reading the file
     */
    public String getCellData(String sheetName, int rownum, int column) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(column);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); // Format the cell value to string
        } catch (Exception e) {
            data = ""; // Return an empty string in case of an exception
        }
        workbook.close();
        fi.close();
        return data; // Return the cell data
    }

    /**
     * Sets data in a specified cell of the sheet.
     *
     * @param sheetName the name of the sheet
     * @param rownum    the row number
     * @param colnum    the column number
     * @param data      the data to be set in the cell
     * @throws IOException if there is an error writing to the file
     */
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlFile = new File(path);
        if (!xlFile.exists()) { // Create a new file if it does not exist
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        // Create a new sheet if it does not exist
        if (workbook.getSheetIndex(sheetName) == -1) {
            workbook.createSheet(sheetName);
        }
        sheet = workbook.getSheet(sheetName);

        // Create a new row if it does not exist
        if (sheet.getRow(rownum) == null) {
            sheet.createRow(rownum);
        }
        row = sheet.getRow(rownum);

        cell = row.createCell(colnum);
        cell.setCellValue(data); // Set the cell value
        fo = new FileOutputStream(path);
        workbook.write(fo); // Write the data to the file
        workbook.close();
        fi.close();
        fo.close();
    }

    /**
     * Fills the specified cell with a green background color.
     *
     * @param sheetName the name of the sheet
     * @param rownum    the row number
     * @param colnum    the column number
     * @throws IOException if there is an error writing to the file
     */
    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style); // Apply the style to the cell
        fo = new FileOutputStream(path);
        workbook.write(fo); // Write the changes to the file
        workbook.close();
        fi.close();
        fo.close();
    }

    /**
     * Fills the specified cell with a red background color.
     *
     * @param sheetName the name of the sheet
     * @param rownum    the row number
     * @param colnum    the column number
     * @throws IOException if there is an error writing to the file
     */
    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style); // Apply the style to the cell
        fo = new FileOutputStream(path);
        workbook.write(fo); // Write the changes to the file
        workbook.close();
        fi.close();
        fo.close();
    }
}
