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
    
    
    static {// ͨ����̬�����������ݿ�����
        try {
            Class.forName(DRIVERCLASS).newInstance();// �������ݿ�����
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    private static Connection con;              //ΪʲôҪ�и���connection�أ� ��Ϊcon��˽�г�Ա����    ���ǲ���֪��closeconnection��ɶ��= =
    public static Connection getConnection() {// �������ݿ����ӵķ���
            try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);// �����µ����ݿ�����
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return con;
   }
    
    public static boolean closeConnection() {// �ر����ݿ����ӵķ���
        boolean isClosed = true;
        if (con != null) {// ���ݿ����ӿ���
            try {
                con.close();// �ر����ݿ�����
            } catch (SQLException e) {
                isClosed = false;
                e.printStackTrace();
            }
        }
        return isClosed;
    }
    
}
