import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import service.BaseService;
import service.impl.XLSXServiceImpl;

import java.io.File;

public class BookMain {
    public static void main(String[] args) {
        BaseService service = new XLSXServiceImpl();
        String filePath = "E:\\jetbrainsProject\\IDEA-U\\excelUtilApplication\\excel-files\\input\\Book.xlsx";
        service.readFile(new File(filePath));
        service.readSheet("Sheet1");
        service.batchProcess(new BaseService.BatchHandler() {
            @Override
            public void doHandler(int index, Row row) {
                if(index>=3 && index<=35){
                    int start = 4;
                    StringBuilder sb = new StringBuilder();
                    while (true){
                        Cell cell = row.getCell(start);
                        if (cell != null){
                            if(start!=4) sb.append(", ");
                            sb.append(cell.toString());
                            cell.setCellValue("");
                            start++;
                        }else break;
                    }
                    row.getCell(4).setCellValue(sb.toString());
                }
            }
        });

        String outputPath = "E:\\jetbrainsProject\\IDEA-U\\excelUtilApplication\\excel-files\\output\\Book.xlsx";
        service.writeFile(new File(outputPath));

    }
}
