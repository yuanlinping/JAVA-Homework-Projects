package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectAccessFile {
	private static final String DRIVERCLASS = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static final String URL = "jdbc:odbc:dataS1";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    
    
    static {// 通过静态方法加载数据库驱动
        try {
            Class.forName(DRIVERCLASS).newInstance();// 加载数据库驱动
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    private static Connection con;              //为什么要有各条connection呢？ 因为con是私有成员变量    但是并不知道closeconnection有啥用= =
    public static Connection getConnection() {// 创建数据库连接的方法
            try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 创建新的数据库连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return con;
   }
    
    public static boolean closeConnection() {// 关闭数据库连接的方法
        boolean isClosed = true;
        if (con != null) {// 数据库连接可用
            try {
                con.close();// 关闭数据库连接
            } catch (SQLException e) {
                isClosed = false;
                e.printStackTrace();
            }
        }
        return isClosed;
    }
    
}
