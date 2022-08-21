import batch.DataInputHandler;
import data.MysqlConnection;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.BaseService;
import service.impl.XLSXServiceImpl;

import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class MainApplication {
    private static String file1 = "月度汇总表_20220101_20220131";
    private static String file2 = "月度汇总表_20220201_20220228";
    private static String file3 = "月度汇总表_20220301_20220331";
    public static void main(String[] args) throws IOException {
        String filename = file3;
        String[] fs = {file1,file2,file3};
        MysqlConnection connection = null;
        try {
            System.out.println("建立数据库连接");
            connection = new MysqlConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i=1;i<=3;i++) {
            String filePath = "E:\\jetbrainsProject\\IDEA-U\\excelUtilApplication\\excel-files\\input\\"+fs[i-1]+".xlsx";
            BaseService service = new XLSXServiceImpl();
            File file = new File(filePath);
            System.out.println("读取文件");
            service.readFile(file);
            System.out.println("读取sheet");
            service.readSheet(fs[i-1]);
            System.out.println("开始批处理");
            service.batchProcess(new DataInputHandler(connection,i));

        }




    }
}
