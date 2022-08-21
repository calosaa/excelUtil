package service;

import data.DeleteStaffInfo;
import data.MysqlConnection;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DepartmentStaffDataService {
    private String departName;
    private ArrayList<String> depStaffs = new ArrayList<>();
    private BaseService service;
    private MysqlConnection connection;
    private DeleteStaffInfo info = new DeleteStaffInfo();
    private DepartsService departsService;

    public DepartmentStaffDataService(String departName, MysqlConnection connection,BaseService service) {
        this.departName = departName;
        this.connection = connection;
        this.service = service;
        this.departsService = new DepartsService(connection);
    }

    public DepartmentStaffDataService(BaseService service, MysqlConnection connection) {
        this.service = service;
        this.connection = connection;
        this.departsService = new DepartsService(connection);
    }

    public void getStaffs(int month){

        ArrayList<String> depStaffs = this.connection.getDepStaffs(departName);
        Object[] header = new Object[5];
        header[0] = "姓名";
        header[1] = "未打卡次数";
        header[2] = "迟到次数";
        header[3] = "早退次数";
        header[4] = "刷脸失败";
        service.addRow(header);
        for (String depStaff : depStaffs) {
            if (info.ispass(depStaff,month)) {
                continue;
            }
            System.out.println("write staff : "+depStaff);
            Object[] staffinfo = new Object[5];
            staffinfo[0] = depStaff;
            staffinfo[1] = this.connection.getOnMiss(depStaff,month);
            staffinfo[2] = this.connection.getLate(depStaff,month);
            staffinfo[3] = this.connection.getLeaveEarly(depStaff,month);
            staffinfo[4] = this.connection.getTakeFailed(depStaff,month);
            service.addRow(staffinfo);
        }
    }

    public void totalCount(int month){
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        Object[] header = new Object[10];
        header[0] = "序号";
        header[1] = "部门";
        header[2] = "未打卡人次";
        header[3] = "人员";
        header[4] = "应打卡人次";
        header[5] = "实际打卡人次";
        header[6] = "打卡率";
        header[7] = "迟到人次";
        header[8] = "早退人次";
        header[9] = "人均未打卡人次";
        service.addRow(header);
        int count = 1;
        for (String dep : departsService.getDeps()) {
            Object[] row = new Object[10];

            row[0] = count;
            row[1] = dep;
            row[2] = connection.depOnMiss(dep, month);
            row[3] = connection.getDepStaffCount(dep);
            row[4] = connection.getDepShouldTake(dep, month);
            row[5] = connection.getDepActTake(dep, month);
            row[6] = decimalFormat.format(100*((Integer) row[5]).floatValue() / ((Integer) row[4]).floatValue())+"%";
            row[7] = connection.getDepLate(dep, month);
            row[8] = connection.getDepEarly(dep, month);
            row[9] = decimalFormat.format(((Integer) row[2]).floatValue() / ((Integer) row[3]).floatValue());
            service.addRow(row);
            count++;
        }
    }
}
