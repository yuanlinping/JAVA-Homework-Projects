package library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.ConnectAccessFile;
import dao.OperationDao;

public class Menu extends JFrame{
//	private Dimension screenSize;
	Menu(){
		super();                       // ʵ���ʱ��������������ڡ���һ�С�   �̳и���Ĺ��췽��
        setTitle("ͼ�����λ����ϵͳ");      //���ô������
        setResizable(false);          //����Ĵ�С�Ƿ���Ըı䣺�Ƿ�֧����󻯣�������Ƿ���Ըı����С    �������Ϊtrue�Ļ���setBounds�����û����= =   Ȼ��ĿǰͼƬ�����������Ŵ�������Ŷ����ţ�׼ȷ��˵��ͼƬ��������Ļ���󣬸�����װ����= =
        setBounds(500,150, 1024, 786); //���ô������ʱ��λ�ã�ǰ�����������ʹ�С��������������
        setExtendedState(Menu.MAXIMIZED_BOTH);   //������󻯺��״̬    �кü�����صĳ���    ������һ��BOTH��ָˮƽ�ʹ�ֱ�������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //���ô���رշ�ʽ
        setLayout(new BorderLayout());              //���ô��岼�ַ�ʽ
//        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        Container zct=getContentPane();  //�����������������أ� �����Ұ�����ɾ�ˣ�ͼƬ�������ԷŽ���������   ����������Ĺ�ϵ��ʲô��   JFrame����һ�����壬 ���������һ��JFrame�����ct����Ҫ��container�Ѵ���ת��Ϊ������������������ˣ���set*��Щ����Ҫ��Ϊct.set*�� ���û�ж�����󣬾�ֱ����set*
        
        /*
         * ������˵�ͼƬ                                 
         */
        final JLabel topLabel = new JLabel();
        topLabel.setPreferredSize(new Dimension(0,200));    //����һ����Ӧ���ڵ���õĴ�С��Dimension��0,100�����ø߶�Ϊ200������洰�ڱ仯�Ĵ�С    ���ǣ��ҰѲ�����һ�£���Щû�仯����Щ�仯�����ҵ����֮��  ������̽��һ��   Ȼ����һ�������û�б仯������Ĳ���north��û�й�ϵ
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);   //���ñ�ǩ�е����־���
        URL topUrl = this.getClass().getResource("/img/top2.png"); //��ȡͼƬ��·��  Ӧ�������·������ͬһ����Ŀ��Դ�ļ��ļ������棨ͬһ����Ŀ�ļ��У���ͬ�İ���
        ImageIcon topIcon = new ImageIcon(topUrl);                //����ͼ�꣬Ȼ���ٽ����ͼ�괫��Label��ٰ�Labelװ�봰�壨��������
        topLabel.setIcon(topIcon);                               //Ҳ����˵ͼ����Label��һ�����ԣ�Label�Ǵ����һ�����
        zct.add(topLabel, BorderLayout.NORTH);
       
        /*
         * �����м����ֲ������
         */
        JPanel selectSeatPanel=new JPanel(new GridLayout(3,1));
        JPanel leaveChoicePanel=new JPanel(new GridLayout(3,1));
        JPanel checkInfoPanel=new JPanel(new GridLayout(3,1));     
        
        //ѡ������
        final JLabel xuanzuoLable=new JLabel("ѡ��",SwingConstants.CENTER);
        final JButton secondFloorButton=new JButton("��¥");
        final JButton thirdFloorButton=new JButton("��¥");
//        final JButton fourthFloorButton=new JButton("��¥");
        selectSeatPanel.add(xuanzuoLable);
        selectSeatPanel.add(secondFloorButton);
        selectSeatPanel.add(thirdFloorButton);
//        selectSeatPanel.add(fourthFloorButton);
        zct.add(selectSeatPanel,BorderLayout.CENTER);     
        
        //�뿪����
        final JLabel likaiPanel=new JLabel("�뿪",SwingConstants.CENTER);
        final JButton tempLeaveButton=new JButton("");
        final JButton compLeaveButton=new JButton("��ȫ�뿪");
        leaveChoicePanel.add(likaiPanel);
        leaveChoicePanel.add(tempLeaveButton);
        leaveChoicePanel.add(compLeaveButton);
        zct.add(leaveChoicePanel,BorderLayout.WEST);
        
        //�鿴��Ϣ����
        final JLabel checkButton=new JLabel("�鿴",SwingConstants.CENTER);
        final JButton checkMySeatButton=new JButton("�鿴�ҵ���λ");
        final JLabel timeLabel=new JLabel();     //ʱ���ǩ�ľ�������Լ���ȡʱ��ķ�ʽ������д
        checkInfoPanel.add(checkButton);
        checkInfoPanel.add(checkMySeatButton);
        checkInfoPanel.add(timeLabel);
        zct.add(checkInfoPanel,BorderLayout.EAST);
             
            //�ڲ��ࣺÿ��һ�����ʱ���ǩ�����ʱ��
        class ShowTime extends Thread{
        	public void run(){
        		while(true){
        			Date today=new Date();
        			timeLabel.setText("��ǰʱ��:"+today.toString().substring(11,19));
        			try{
        				Thread.sleep(1000);
        			}catch(InterruptedException e){
        				e.printStackTrace();
        			}
        		}
        	}
        }
        
              //��ʾʱ��
        timeLabel.setFont(new Font("����",Font.BOLD,14));
//        timeLabel.setForeground(new Color(255,0,0));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        new ShowTime().start();
        
           
        
        /*
         * ���õײ��Ĳ�����ʾ  ����ֻ����ͼƬ
         */
        
        final JLabel tipsPanel=new JLabel();
        tipsPanel.setPreferredSize(new Dimension(0,200));
        tipsPanel.setHorizontalAlignment(SwingConstants.CENTER);
        URL tipsUrl=this.getClass().getResource("/img/buttom2.png");
        ImageIcon tips=new ImageIcon(tipsUrl);
        tipsPanel.setIcon(tips);
        zct.add(tipsPanel,BorderLayout.SOUTH);
        
        setVisible(true);
        
        /*
         * �¼�     �е��   ��Ϊ�����е��¼���������������ϵģ��Ҷ�д���˹��캯����   ������˳��ṹ   ���Ǻ�����Ҫ���ʲô��������  ������ô����= =
         */
        
        //ѡ�����
        //����������еġ���¥����ť
        secondFloorButton.addMouseListener(new MouseListener(){    //��Ӽ�����
        	public void mouseEntered(MouseEvent e){return;}
    		public void mousePressed(MouseEvent e){return;}
    		public void mouseReleased(MouseEvent e){return;}
    		public void mouseExited(MouseEvent e){return;}
        	public void mouseClicked(MouseEvent e){      //���������¥����ťһ��ʱ����������¥ƽ��ͼ��
        		Object i=e.getSource();                  //��¼��������Ǹ���ť
        		int j=e.getButton();                     //��¼����������ġ���������Ҽ��������ǹ���   BUTTON1�����
        		int k=e.getClickCount();                 //��¼����˼���
        		if((i==secondFloorButton)&&(j==MouseEvent.BUTTON1)&&(k==1))   //ֻ��������������ǡ���¥���Żᴥ����������¥ƽ��ͼ�������
        			new secondPlan();
        	}
        });   //ע�����������line99�е��������
	
	/*
	 * �����¥����¥���Թ�
	 */
	
	
	//�뿪���
	//�������ʱ�뿪����ť
        tempLeaveButton.addMouseListener(new MouseListener(){    
        	public void mouseEntered(MouseEvent e){return;}
    		public void mousePressed(MouseEvent e){return;}
    		public void mouseReleased(MouseEvent e){return;}
    		public void mouseExited(MouseEvent e){return;}
        	public void mouseClicked(MouseEvent e){      
        		Object i=e.getSource();                  
        		int j=e.getButton();                     
        		int k=e.getClickCount();                 
        		if((i==tempLeaveButton)&&(j==MouseEvent.BUTTON1)&&(k==1))   
        			new tempLeave();
        	}
        });   
        
    //�������ȫ�뿪����ť
        compLeaveButton.addMouseListener(new MouseListener(){
        	public void mouseEntered(MouseEvent e){return;}
        	public void mousePressed(MouseEvent e){return;}
        	public void mouseReleased(MouseEvent e){return;}
        	public void mouseExited(MouseEvent e){return;}
        	public void mouseClicked(MouseEvent e){
        		Object i=e.getSource();
        		int j=e.getButton();
        		int k=e.getClickCount();
        		if((i==compLeaveButton)&&(j==MouseEvent.BUTTON1)&&(k==1))  
        			new compLeave();
        	}
        });
        
      //������鿴�ҵ���λ����ť
        checkMySeatButton.addMouseListener(new MouseListener(){
        	public void mouseEntered(MouseEvent e){return;}
        	public void mousePressed(MouseEvent e){return;}
        	public void mouseReleased(MouseEvent e){return;}
        	public void mouseExited(MouseEvent e){return;}
        	public void mouseClicked(MouseEvent e){
        		Object i=e.getSource();
        		int j=e.getButton();
        		int k=e.getClickCount();
        		if((i==checkMySeatButton)&&(j==MouseEvent.BUTTON1)&&(k==1))  
        			new checkInfo();
        	}
        });
	       
	}
	
    
	
	
	
	
	
	public static void main(String[] args) {
		new Menu();
//		String DRIVERCLASS = "sun.jdbc.odbc.JdbcOdbcDriver";
//		try{
//			Class.forName(DRIVERCLASS);
//		}catch(ClassNotFoundException e){
//			e.printStackTrace();
//		}
//		String SQL1="SELECT * From stu_Info WHERE ID='2151600005'";
//		Vector<Object> result=OperationDao.selectOneNote(SQL1);
//		for(int i=0;i<result.size();i++){
//			System.out.println(result.get(i));
//		}
		
		
		
		
//	    String URL = "jdbc:odbc:driver={Microsoft Access Driver(*.accdb)};DatabaseName=G://db_StudentInfo.accdb";
//		String URL = "jdbc:odbc:dataS1";
//	    String USERNAME = "sa";
//	    String PASSWORD = "";
//	    try{    	
//	    	Connection conn=ConnectAccessFile.getConnection();
//	    	String sql="insert into stu_Info(ID,stuName,stuGrade,stuMajor,stuClass)values('215160001','��','���','�������','���51')";
//	    	Statement stmt=conn.createStatement();
//		    stmt.executeUpdate(sql);
//		    conn.close();
//	    }catch (SQLException e){
//			e.printStackTrace();
//		}
//		new compLeave();
		// TODO �Զ����ɵķ������

	}

}
