import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.impl.XLSXServiceImpl;
import tools.StyleFactory;

import java.io.File;
import java.util.Random;

public class ColorTest {
    public static void main(String[] args) {
        XLSXServiceImpl xlsxService = new XLSXServiceImpl();
        xlsxService.createWorkBook(new String[]{"foreground"});
        xlsxService.readSheet("foreground");
        short blue = HSSFColor.HSSFColorPredefined.BLUE.getIndex();
        short yellow = HSSFColor.HSSFColorPredefined.YELLOW.getIndex();
        short green = HSSFColor.HSSFColorPredefined.GREEN.getIndex();
        short red = HSSFColor.HSSFColorPredefined.RED.getIndex();
        for (int i = 0; i < 20; i++) {
            xlsxService.addRow(new String[]{"hello"});
        }

       xlsxService.batchProcess(((index, row) -> {
            System.out.println(index);
            if(index>20) return;

            if(index%4==0)StyleFactory.setForegroundColor(xlsxService.getWorkBook(), row.getCell(0),blue);
            else if(index%4==1)StyleFactory.setForegroundColor(xlsxService.getWorkBook(), row.getCell(0),yellow);
            else if(index%4==2)StyleFactory.setForegroundColor(xlsxService.getWorkBook(), row.getCell(0),green);
            else StyleFactory.setForegroundColor(xlsxService.getWorkBook(), row.getCell(0),red);
        }));

        xlsxService.writeFile(new File("E:\\jetbrainsProject\\IDEA-U\\excelUtilApplication\\excel-files\\output\\color.xlsx"));
    }
}
