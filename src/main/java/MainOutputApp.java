import data.MysqlConnection;
import service.BaseService;
import service.DepartmentStaffDataService;
import service.DepartsService;
import service.impl.XLSXServiceImpl;

import java.io.File;

public class MainOutputApp {
    public static void main(String[] args) {
        BaseService service = new XLSXServiceImpl();
        File file = new File("E:\\jetbrainsProject\\IDEA-U\\excelUtilApplication\\excel-files\\output\\1季度.xlsx");

        service.createWorkBook();
        MysqlConnection connection = null;
        try {
            connection = new MysqlConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DepartsService departsService = new DepartsService(connection);
        DepartmentStaffDataService dataService = null;
        for (String dep : departsService.getDeps()) {
            System.out.println("write dep : "+dep+"=========================");
            service.addSheet(dep);
            service.readSheet(dep);
            dataService = new DepartmentStaffDataService(dep,connection,service);
            dataService.getStaffs(4);
        }
        service.writeFile(file);
//        service.addSheet("汇总");
//        service.readSheet("汇总");
//        dataService = new DepartmentStaffDataService(service,connection);
//        dataService.totalCount(1);
//        service.writeFile(file);
    }
}
