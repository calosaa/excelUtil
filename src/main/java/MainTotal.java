import data.MysqlConnection;
import service.BaseService;
import service.DepartmentStaffDataService;
import service.impl.XLSXServiceImpl;

import java.io.File;

public class MainTotal {
    public static void main(String[] args) {
        File file = new File("E:\\jetbrainsProject\\IDEA-U\\excelUtilApplication\\excel-files\\output\\汇总表.xlsx");
        BaseService service = new XLSXServiceImpl();
        MysqlConnection connection = null;
        try {
            connection = new MysqlConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.readFile(file);
        service.addSheet("汇总");
        service.readSheet("汇总");
        DepartmentStaffDataService dataService = null;
        dataService = new DepartmentStaffDataService(service,connection);
        dataService.totalCount(4);
        service.writeFile(file);

    }
}
