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
		super();                       // 实验的时候好像这个必须放在“第一行”   继承父类的构造方法
        setTitle("图书馆座位管理系统");      //设置窗体标题
        setResizable(false);          //窗体的大小是否可以改变：是否支持最大化，用鼠标是否可以改变其大小    但如果设为true的话，setBounds好像就没用了= =   然后目前图片并不可以随着窗体的缩放而缩放，准确的说，图片比整个屏幕还大，根本就装不完= =
        setBounds(500,150, 1024, 786); //设置窗体出现时的位置（前两个参数）和大小（后两个参数）
        setExtendedState(Menu.MAXIMIZED_BOTH);   //窗体最大化后的状态    有好几个相关的常数    现在这一个BOTH是指水平和垂直方向都最大化
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //设置窗体关闭方式
        setLayout(new BorderLayout());              //设置窗体布局方式
//        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        Container zct=getContentPane();  //容器的意义在哪里呢？ 好像我把容器删了，图片照样可以放进窗体里面   窗体和容器的关系是什么？   JFrame就是一个窗体， 如果定义了一个JFrame类对象ct，需要用container把窗体转化为容器（就是这条语句了），set*这些函数要变为ct.set*， 如果没有定义对象，就直接是set*
        
        /*
         * 设置最顶端的图片                                 
         */
        final JLabel topLabel = new JLabel();
        topLabel.setPreferredSize(new Dimension(0,200));    //设置一个适应窗口的最好的大小，Dimension（0,100）设置高度为200，宽度随窗口变化的大小    但是，我把参数改一下，有些没变化，有些变化不在我的理解之内  后面再探究一下   然后想一下这个有没有变化跟后面的布局north有没有关系
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);   //设置标签中的文字居中
        URL topUrl = this.getClass().getResource("/img/top2.png"); //获取图片的路径  应该是相对路径，在同一个项目的源文件文件夹里面（同一个项目文件夹，不同的包）
        ImageIcon topIcon = new ImageIcon(topUrl);                //创建图标，然后再将这个图标传进Label里，再把Label装入窗体（容器）里
        topLabel.setIcon(topIcon);                               //也就是说图标是Label的一个属性，Label是窗体的一个组件
        zct.add(topLabel, BorderLayout.NORTH);
       
        /*
         * 设置中间三种操作面板
         */
        JPanel selectSeatPanel=new JPanel(new GridLayout(3,1));
        JPanel leaveChoicePanel=new JPanel(new GridLayout(3,1));
        JPanel checkInfoPanel=new JPanel(new GridLayout(3,1));     
        
        //选座操作
        final JLabel xuanzuoLable=new JLabel("选座",SwingConstants.CENTER);
        final JButton secondFloorButton=new JButton("二楼");
        final JButton thirdFloorButton=new JButton("三楼");
//        final JButton fourthFloorButton=new JButton("四楼");
        selectSeatPanel.add(xuanzuoLable);
        selectSeatPanel.add(secondFloorButton);
        selectSeatPanel.add(thirdFloorButton);
//        selectSeatPanel.add(fourthFloorButton);
        zct.add(selectSeatPanel,BorderLayout.CENTER);     
        
        //离开操作
        final JLabel likaiPanel=new JLabel("离开",SwingConstants.CENTER);
        final JButton tempLeaveButton=new JButton("");
        final JButton compLeaveButton=new JButton("完全离开");
        leaveChoicePanel.add(likaiPanel);
        leaveChoicePanel.add(tempLeaveButton);
        leaveChoicePanel.add(compLeaveButton);
        zct.add(leaveChoicePanel,BorderLayout.WEST);
        
        //查看信息操作
        final JLabel checkButton=new JLabel("查看",SwingConstants.CENTER);
        final JButton checkMySeatButton=new JButton("查看我的座位");
        final JLabel timeLabel=new JLabel();     //时间标签的具体情况以及获取时间的方式过后在写
        checkInfoPanel.add(checkButton);
        checkInfoPanel.add(checkMySeatButton);
        checkInfoPanel.add(timeLabel);
        zct.add(checkInfoPanel,BorderLayout.EAST);
             
            //内部类：每隔一秒更改时间标签里面的时间
        class ShowTime extends Thread{
        	public void run(){
        		while(true){
        			Date today=new Date();
        			timeLabel.setText("当前时间:"+today.toString().substring(11,19));
        			try{
        				Thread.sleep(1000);
        			}catch(InterruptedException e){
        				e.printStackTrace();
        			}
        		}
        	}
        }
        
              //显示时间
        timeLabel.setFont(new Font("楷体",Font.BOLD,14));
//        timeLabel.setForeground(new Color(255,0,0));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        new ShowTime().start();
        
           
        
        /*
         * 设置底部的操作提示  先在只放了图片
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
         * 事件     有点慌   因为我所有的事件（包括其他面板上的）我都写在了构造函数里   明明是顺序结构   但是好像需要点击什么的来触发  这是怎么回事= =
         */
        
        //选座相关
        //点击主窗口中的“二楼”按钮
        secondFloorButton.addMouseListener(new MouseListener(){    //添加监听器
        	public void mouseEntered(MouseEvent e){return;}
    		public void mousePressed(MouseEvent e){return;}
    		public void mouseReleased(MouseEvent e){return;}
    		public void mouseExited(MouseEvent e){return;}
        	public void mouseClicked(MouseEvent e){      //当点击“二楼”按钮一次时，弹出“二楼平面图”
        		Object i=e.getSource();                  //记录点击的是那个按钮
        		int j=e.getButton();                     //记录是怎样点击的——左键，右键，或者是滚轮   BUTTON1是左键
        		int k=e.getClickCount();                 //记录点击了几次
        		if((i==secondFloorButton)&&(j==MouseEvent.BUTTON1)&&(k==1))   //只有用左键单击的是“二楼”才会触发弹出“二楼平面图”这件事
        			new secondPlan();
        	}
        });   //注意这个括号与line99中的括号配对
	
	/*
	 * 点击三楼、四楼先略过
	 */
	
	
	//离开相关
	//点击“暂时离开”按钮
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
        
    //点击“完全离开”按钮
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
        
      //点击“查看我的座位”按钮
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
//	    	String sql="insert into stu_Info(ID,stuName,stuGrade,stuMajor,stuClass)values('215160001','戊','大二','软件工程','软件51')";
//	    	Statement stmt=conn.createStatement();
//		    stmt.executeUpdate(sql);
//		    conn.close();
//	    }catch (SQLException e){
//			e.printStackTrace();
//		}
//		new compLeave();
		// TODO 自动生成的方法存根

	}

}
