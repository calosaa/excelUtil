import data.MysqlConnection;
import service.DepartmentStaffDataService;

public class OnMissTakeCountMain {
    public static void main(String[] args) throws Exception {
        MysqlConnection connection = new MysqlConnection();
        int count = 0;
        for (String dep : connection.getDeps()) {
            for (String depStaff : connection.getDepStaffs(dep)) {
                int time = connection.getStaffMissTakeCount(depStaff);
                if(time>20) {
                    System.out.println(depStaff+"in "+dep+" miss take :"+ time);
                    count++;
                }
            }
        }

        System.out.println("大于20次以上的有 ："+count);
    }
}
