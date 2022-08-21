import data.MysqlConnection;

public class TestApp {
    public static void main(String[] args) {

    }

    public static void test(){
        try {
            MysqlConnection connection = new MysqlConnection();
            for (String dep : connection.getDeps()) {
                System.out.println(dep);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
