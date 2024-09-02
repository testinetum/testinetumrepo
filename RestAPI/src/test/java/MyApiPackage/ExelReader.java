package MyApiPackage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExelReader {

    private Workbook workbook;

    public ExelReader(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fileInputStream);
        
    }

    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row != null) {
            Cell cell = row.getCell(colNum);
            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        return cell.getStringCellValue();
                    case NUMERIC:
                        // Check if the cell contains an integer value
                        if (DateUtil.isCellDateFormatted(cell)) {
                            // Handle date values (if needed)
                            return cell.getDateCellValue().toString();
                        } else {
                            return String.valueOf(cell.getNumericCellValue());
                        }
                    default:
                        return null;
                }
            }
        }
        return null;
    }

        
    

    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
            
        }
    }
}
