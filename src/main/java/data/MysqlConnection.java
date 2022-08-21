package data;

import data.entity.Staff;
import data.entity.TimeTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MysqlConnection {

    private Connection connection;

    public MysqlConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ccb_timecard","root","43007884Ct!");
        System.out.println("数据库连接成功");
    }

    public void insertStaff(Staff staff){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select s.name from staff s where s.name=?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1,staff.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            if(resultSet.getRow()>0){
                System.out.println(staff.getName()+"用户以存在");
                return;
            }
            PreparedStatement prepared = connection.prepareStatement("insert into staff" +
                    " (name,dep_name,att_day,late,act_att_day,leave_early,on_miss,off_miss,no_work) value " +
                    "(?,?,?,?,?,?,?,?,?)");
            prepared.setString(1,staff.getName());
            prepared.setString(2,staff.getDepName());
            prepared.setInt(3,staff.getAttDay());
            prepared.setInt(4,staff.getLate());
            prepared.setInt(5,staff.getActAttDay());
            prepared.setInt(6,staff.getLeaveEarly());
            prepared.setInt(7,staff.getOnMiss());
            prepared.setInt(8,staff.getOffMiss());
            prepared.setInt(9,staff.getNoWork());
            prepared.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTimeTable(TimeTable timeTable){
        try {
            PreparedStatement prepared = connection.prepareStatement("insert into time_table " +
                    "(name,month,day,on_time,m_o_a,type) value " +
                    "(?,?,?,?,?,?)");

            prepared.setString(1,timeTable.getName());
            prepared.setInt(2,timeTable.getMonth());
            prepared.setInt(3,timeTable.getDay());
            prepared.setTime(4,timeTable.getOnTime());
            prepared.setBoolean(5,timeTable.isMorningOrAfternoon());
            prepared.setInt(6,timeTable.getType());
            prepared.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateType(String name,int month,int day,int type){
        try {
            PreparedStatement prepared = connection.prepareStatement("update time_table t set t.type=? where t.name=? and t.month=? and t.day=?");
            prepared.setInt(1,type);
            prepared.setString(2,name);
            prepared.setInt(3,month);
            prepared.setInt(4,day);
            prepared.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getDepStaffs(String depname){
        try {
            PreparedStatement prepared = this.connection.prepareStatement("select s.name from staff s where s.dep_name=?");
            prepared.setString(1,depname);
            ResultSet resultSet = prepared.executeQuery();
            ArrayList<String> staffs = new ArrayList<>();

            while (resultSet.next()) {
                staffs.add(resultSet.getString(1));
            }
            return staffs;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getDeps(){
        try {
            PreparedStatement prepared = this.connection.prepareStatement("select distinct s.dep_name from staff s");
            ResultSet resultSet = prepared.executeQuery();
            ArrayList<String> staffs = new ArrayList<>();

            while (resultSet.next()) {
                staffs.add(resultSet.getString(1));
            }
            staffs.remove("商户中心");
            staffs.remove("行领导");
            return staffs;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public int getOnMiss(String staffname,int month){
        try {
            String sql = null;
            if(month<4) sql = "select count(*) from time_table t where t.type=5 and t.name=? and t.month=?";
            else sql = "select count(*) from time_table t where t.type=5 and t.name=?";
            PreparedStatement prepared = this.connection.prepareStatement(sql);
            prepared.setString(1,staffname);
            if (month<4) prepared.setInt(2,month);
            ResultSet resultSet = prepared.executeQuery();
            int miss = -1;
            if(resultSet.next()) miss = resultSet.getInt(1);
            return miss;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getLate(String staffname,int month){
        try {
            String sql = null;
            if (month<4) sql = "select count(*) from time_table t where t.type=1 and t.name=? and t.month=?";
            else sql = "select count(*) from time_table t where t.type=1 and t.name=?";
            PreparedStatement prepared = this.connection.prepareStatement(sql);
            prepared.setString(1,staffname);
            if (month<4)prepared.setInt(2,month);
            ResultSet resultSet = prepared.executeQuery();
            int miss = -1;
            if(resultSet.next()) miss = resultSet.getInt(1);
            return miss;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    public int getLeaveEarly(String staffname,int month){
        try {
            String sql = null;
            if (month<4) sql = "select count(*) from time_table t where t.type=-1 and t.name=? and t.month=?";
            else sql = "select count(*) from time_table t where t.type=-1 and t.name=?";
            PreparedStatement prepared = this.connection.prepareStatement(sql);
            prepared.setString(1,staffname);
            if (month<4)prepared.setInt(2,month);
            ResultSet resultSet = prepared.executeQuery();
            int miss = -1;
            if(resultSet.next()) miss = resultSet.getInt(1);
            return miss;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getTakeFailed(String staffname,int month){
        try {
            String sql = null;
            if(month<4) sql = "select count(*) from time_table t where t.comments='刷脸失败' and t.name=? and t.month=?";
            else sql = "select count(*) from time_table t where t.comments='刷脸失败' and t.name=?";
            PreparedStatement prepared = this.connection.prepareStatement(sql);
            prepared.setString(1,staffname);
            if (month<4)prepared.setInt(2,month);
            ResultSet resultSet = prepared.executeQuery();
            int miss = -1;
            if(resultSet.next()) miss = resultSet.getInt(1);
            return miss;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    //=======================================================================

    /**
     * 部门未打卡人次
     * @param depname
     * @param month
     * @return
     */
    public int depOnMiss(String depname,int month){
        try {
            String sql = null;
            if(month<4) {
                sql = "select distinct count(*) from time_table t right join staff s on t.name = s.name where t.type=5 and s.dep_name=? and month=?;";
            }else {
                sql = "select distinct count(*) from time_table t right join staff s on t.name = s.name where t.type=5 and s.dep_name=?;";
            }
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,depname);
            if (month<4)preparedStatement.setInt(2,month);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = -1;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getDepStaffCount(String depname){
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("select count(*) from staff s where s.dep_name=?;");
            preparedStatement.setString(1,depname);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = -1;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;

    }

    public int getDepShouldTake(String depname,int month){
        try {
            String sql = null;
            if (month<4) sql = "select distinct count(*) from time_table t right join staff s on t.name = s.name where (t.type=5 or t.type=0) and s.dep_name=? and month=?;";
            else sql = "select distinct count(*) from time_table t right join staff s on t.name = s.name where (t.type=5 or t.type=0) and s.dep_name=?;";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,depname);
            if (month<4)preparedStatement.setInt(2,month);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = -1;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getDepActTake(String depname,int month){
        try {
            String sql = null;
            if (month<4) sql = "select distinct count(*) from time_table t right join staff s on t.name = s.name where t.type=0 and s.dep_name=? and month=?;";
            else sql = "select distinct count(*) from time_table t right join staff s on t.name = s.name where t.type=0 and s.dep_name=?;";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,depname);
            if (month<4)preparedStatement.setInt(2,month);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = -1;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getDepLate(String depname,int month){
        try {
            String sql = null;
            if(month<4) sql = "select distinct count(*) from time_table t right join staff s on t.name = s.name where t.type=1 and s.dep_name=? and month=?;";
            else sql = "select distinct count(*) from time_table t right join staff s on t.name = s.name where t.type=1 and s.dep_name=?;";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,depname);
            if (month<4)preparedStatement.setInt(2,month);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = -1;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getDepEarly(String depname,int month){
        try {
            String sql = null;
            if (month<4) sql = "select distinct count(*) from time_table t right join staff s on t.name = s.name where t.type=-1 and s.dep_name=? and month=?;";
            else sql = "select distinct count(*) from time_table t right join staff s on t.name = s.name where t.type=-1 and s.dep_name=?;";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,depname);
            if (month<4) preparedStatement.setInt(2,month);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = -1;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    private DeleteStaffInfo info = new DeleteStaffInfo();

    public void deleteStaff(){
        HashMap<String, Integer[]> map = info.getMap();
        for (String s : map.keySet()) {
            Integer[] m = map.get(s);
            if(m.length==3){
                System.out.println("删除员工 ："+s);
                deleteInStaff(s);
            }
            for (Integer month : m) {
                System.out.println("删除员工 "+s+" "+month+"月数据");
                deleteInTimeTable(s,month);
            }

        }
    }

    public void deleteInStaff(String name){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement("delete from staff s where s.name=?");
            preparedStatement.setString(1,name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInTimeTable(String name,int month){
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("delete from time_table t where t.name=? and month=?");
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,month);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public int getStaffMissTakeCount(String staffname){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            count+=getOnMiss(staffname,i);
        }
        return count;

    }

}
