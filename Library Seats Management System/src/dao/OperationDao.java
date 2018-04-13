package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

//数据库操作类
public class OperationDao {

	//查询一条记录
	public static Vector selectOneNote(String SQL){                              
		Vector<Object> vec=null;                      //vec用来存放查到的记录的各个字段的值
		Connection conn=ConnectAccessFile.getConnection();
		try{
			Statement stmt=conn.createStatement();
			ResultSet res=stmt.executeQuery(SQL);         //结果集
			int columnCount=res.getMetaData().getColumnCount();   //用来得到一条记录的字段数
			while(res.next()){
				vec=new Vector<Object>();
				for(int column=1;column<=columnCount;column++){
					vec.add(res.getObject(column));               //将这条记录的各个字段数添加到vec中
				}
			}
			res.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return vec;
	}
	
	
	//修改、添加、删除记录
	public static boolean longHaul(String SQL){
		boolean isLongHaul=true;                              //默认持久化成功？？？？？      持久化是什么！！！！
		Connection conn=ConnectAccessFile.getConnection();    //获得数据库连接
		try{
			conn.setAutoCommit(false);                       //设为手动提交？？？？？
			Statement stmt=conn.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();
		    conn.commit();                        //提交持久化
		}catch(SQLException e){
			isLongHaul=false;                       //持久化失败
			try{ 
				conn.rollback();                 //回滚
			}catch(SQLException e1){ 
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return isLongHaul;   //返回持久化结果
	}

}
