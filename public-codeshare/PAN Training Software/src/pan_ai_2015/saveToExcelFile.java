/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.StringTokenizer;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Promita
 */
public class saveToExcelFile {
    public void saveFile(String cell1, String filename, String data) {
        try {
                        // write data to Excel file
            //String mfile= "C:\\Users\\Promita\\Desktop\\PAN\\"+filename;
            File file = new File(filename); 
            HSSFWorkbook wb = null;
            HSSFSheet sheet;
            Row row = null;
            Cell cell;
            int rowIndex = 0;
 
            if (file.exists()) {
                InputStream excelFile = new FileInputStream(file);
                wb = new HSSFWorkbook(excelFile);
                sheet = wb.getSheetAt(0);
                rowIndex = sheet.getLastRowNum();
                rowIndex += 1;
                row = sheet.createRow(rowIndex);
            }
            else
            {
               FileOutputStream out = new FileOutputStream(new File(filename));
               file = new File(filename); 
               InputStream excelFile = new FileInputStream(file);
                wb = new HSSFWorkbook(excelFile);
                sheet = wb.getSheetAt(0);
                rowIndex = sheet.getLastRowNum();
                rowIndex += 1;
                row = sheet.createRow(rowIndex);
            
            }
 
            CellStyle cs = wb.createCellStyle();
            StringTokenizer tokenizer = new StringTokenizer (data);
	    int count= tokenizer.countTokens();
            int cellcount=0;
            cell = row.createCell(cellcount++);
            cell.setCellValue(cell1);
            cell = row.createCell(cellcount++);
            cell.setCellValue(tokenizer.nextToken());
	     for(int i=0; i<count-1; i++)
	      {
		String word = tokenizer.nextToken();
                cell = row.createCell(cellcount++);
                cell.setCellValue(word);
              }
 
            FileOutputStream out = new FileOutputStream(filename);
            wb.write(out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
