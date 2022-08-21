import data.MysqlConnection;

public class MainDelete {
    public static void main(String[] args) throws Exception {
        MysqlConnection connection = new MysqlConnection();
        connection.deleteStaff();
    }
}
