package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

//���ݿ������
public class OperationDao {

	//��ѯһ����¼
	public static Vector selectOneNote(String SQL){                              
		Vector<Object> vec=null;                      //vec������Ų鵽�ļ�¼�ĸ����ֶε�ֵ
		Connection conn=ConnectAccessFile.getConnection();
		try{
			Statement stmt=conn.createStatement();
			ResultSet res=stmt.executeQuery(SQL);         //�����
			int columnCount=res.getMetaData().getColumnCount();   //�����õ�һ����¼���ֶ���
			while(res.next()){
				vec=new Vector<Object>();
				for(int column=1;column<=columnCount;column++){
					vec.add(res.getObject(column));               //��������¼�ĸ����ֶ�����ӵ�vec��
				}
			}
			res.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return vec;
	}
	
	
	//�޸ġ���ӡ�ɾ����¼
	public static boolean longHaul(String SQL){
		boolean isLongHaul=true;                              //Ĭ�ϳ־û��ɹ�����������      �־û���ʲô��������
		Connection conn=ConnectAccessFile.getConnection();    //������ݿ�����
		try{
			conn.setAutoCommit(false);                       //��Ϊ�ֶ��ύ����������
			Statement stmt=conn.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();
		    conn.commit();                        //�ύ�־û�
		}catch(SQLException e){
			isLongHaul=false;                       //�־û�ʧ��
			try{ 
				conn.rollback();                 //�ع�
			}catch(SQLException e1){ 
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return isLongHaul;   //���س־û����
	}

}
