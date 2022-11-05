import data.MysqlConnection;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition;
import service.impl.XLSXServiceImpl;

import java.io.File;

public class TestApp {
    public static void main(String[] args) {
        XLSXServiceImpl xlsxService = new XLSXServiceImpl();
        xlsxService.createWorkBook();

        xlsxService.addSheet("Sheet2");
        xlsxService.readSheet("Sheet2");
        for (int i=0;i<=10;i++){
            if(i==0){
                xlsxService.addRow(new Object[]{"name","age","sex"});
            }else {
                XSSFRow row = xlsxService.addRow();
                XSSFCell name = row.createCell(0);
                name.setCellValue("user"+i);
                XSSFCell age = row.createCell(1);
                age.setCellValue(i+5);
                XSSFCell sex = row.createCell(2);
                sex.setCellValue(i%2);

            }
        }
        xlsxService.addSheet("Sheet3");
        XSSFSheet sheet3 = (XSSFSheet)xlsxService.getSheet("Sheet3");
        XSSFPivotTable pivotTable = sheet3.createPivotTable(new AreaReference("Sheet2!A1:C11", null), new CellReference("Sheet3!C3"));
        pivotTable.addRowLabel(0);
        pivotTable.addColumnLabel(DataConsolidateFunction.SUM, 1,"age");
        pivotTable.addColumnLabel(DataConsolidateFunction.SUM, 2,"sex");
        File file = new File("C:\\Users\\86136\\Desktop\\test1.xlsx");
        xlsxService.writeFile(file);
    }

}
