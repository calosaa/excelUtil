package batch;

import data.MysqlConnection;
import data.entity.Staff;
import data.entity.TimeTable;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import service.BaseService;

import java.sql.Time;
import java.util.Iterator;

public class DataInputHandler implements BaseService.BatchHandler {
    private MysqlConnection connection;
    private String curStaff;
    private int month;
    private FestivalHandler festivalHandler;

    public DataInputHandler(MysqlConnection connection,int month) {
        this.connection = connection;
        this.month = month;
        this.festivalHandler = new FestivalHandler();
    }

    @Override
    public void doHandler(int index, Row row) {
        if(index>=2){
            if(index%2==0){
                curStaff = row.getCell(0).toString();
                insertStaff(curStaff,row);
                System.out.println("插入员工信息 : "+curStaff);
                insertTimeTable(curStaff,month,row,true);
                System.out.println("插入员工 "+curStaff+" 上午时间戳");
            }else{
                insertTimeTable(curStaff,month,row,false);
                System.out.println("插入员工 "+curStaff+" 下午时间戳");
            }


        }
    }

    public void insertStaff(String name,Row row){
        Staff staff = new Staff(name //name
                ,row.getCell(2).toString() //dep_name
                ,Integer.parseInt(row.getCell(3).toString())  //att_day
                ,Integer.parseInt(row.getCell(7).toString()) //late
                ,Integer.parseInt(row.getCell(4).toString()) //act_att_day
                ,Integer.parseInt(row.getCell(9).toString()) //leave_early
                ,Integer.parseInt(row.getCell(11).toString()) //on_miss
                ,Integer.parseInt(row.getCell(12).toString()) //off_miss
                ,Integer.parseInt(row.getCell(13).toString()) //no_work
                );
        connection.insertStaff(staff);
    }

    public void insertTimeTable(String name,int month,Row row,boolean d){
        for (int i = 1; i <= 31; i++) {
            Cell cell = row.getCell(i+13);
            if(cell==null) return;
            String timeStr = cell.toString();
            int type = 0;
            try {
                type = getType(d,timeStr,month+"-"+i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Time time = parseTime(timeStr);

            TimeTable timeTable = new TimeTable(-1 //id
                    ,name //name
                    ,month//month
                    ,i// day
                    ,time//on_time
                    ,d// morning or afternoon
                    ,type//type
                     );
            connection.insertTimeTable(timeTable);
        }
    }

    private Time parseTime(String timeStr){
        if(timeStr==null || "".equals(timeStr) || "-".equals(timeStr))return null;
        String[] split = timeStr.split(":");
        return new Time(Integer.parseInt(split[0]),Integer.parseInt(split[1]),0);
    }

    public int getType(boolean d,String timeStr,String day) throws Exception {
        if (timeStr==null) throw new Exception("空内容");
        if("-".equals(timeStr)) {
            if(festivalHandler.getRest(day)) return 3;
            else return 5;
        }
        if ("".equals(timeStr) ) return 3;
        String[] split = timeStr.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        if(d){
            if((hour==8 && minute>30) || (hour>8)){
                return 1;
            }else return 0;
        }else {
            if ((hour==17 && minute<30) || hour<17){
                return -1;
            }else return 0;
        }
    }
}
