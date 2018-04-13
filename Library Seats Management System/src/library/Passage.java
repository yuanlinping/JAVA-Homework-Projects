package library;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.OperationDao;

public class Passage extends JFrame{                        //座位那里有错误！！！！
	private String getSeatID;              //座位编号用的是自动编号，但是好像仍然可以用string类型
	private String getStuID;
	Passage(){
		super();
		setTitle("南北走廊座位图");
		setResizable(false);
		setBounds(200,100,500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		Container pas=getContentPane();
		
		final JPanel leftPanel=new JPanel(new GridLayout(14,3,10,10));
		final JPanel rightPanel=new JPanel(new GridLayout(14,3,10,10)); 
     	final JButton[] seatid = new JButton[84];//=new JLabel[80];    //想建立一个数组来存按钮  标签以便后面好管理，结果像下面用settext不可以= =,会出现指针为空的提示
     	                                                              //探索之后发现在循环里面还需要new一次，
		for(int i=0;i<9;i++){                    //注意这个控制点是9 而不是10  否则会出现010的情况
			int k=i+1;
			seatid[i]=new JButton("0"+k);  //这一步很重要！！！！！！！！！
//			seatid[i].setText(""+k);    //新技能get：将int转换为string
//			leftPanel.add(seatid[i]);
			leftPanel.add(seatid[i]);
		}
		
		for(int i=9;i<42;i++){
			int k=i+1;
			seatid[i]=new JButton(""+k);
//			seatid[i].setText("heiehi");
//			rightPanel.add(seatid[i]);
			leftPanel.add(seatid[i]);
		}
		
		for(int i=42;i<84;i++){
			int k=i+1;
			seatid[i]=new JButton(""+k);
//			seatid[i].setText("heiehi");
//			rightPanel.add(seatid[i]);
			rightPanel.add(seatid[i]);
		}
		
		final JPanel operationPanel=new JPanel(new GridLayout(11,1));
		final JLabel tipLabel1=new JLabel("请输入学号并以空格结束：");
		final JLabel tipLabel2=new JLabel("请输入座位号并以空格结束：");
		final JTextField IDTextField=new JTextField();
		final JTextField seatTextField=new JTextField();
		final JButton OKButton=new JButton("确定");
		final JButton cancelButton=new JButton("取消");
		
		operationPanel.add(new JLabel());
		operationPanel.add(tipLabel1);
		operationPanel.add(IDTextField);
		operationPanel.add(new JLabel());
		operationPanel.add(tipLabel2);
		operationPanel.add(seatTextField);
		operationPanel.add(new JLabel());
		operationPanel.add(OKButton);
		operationPanel.add(new JLabel());
		operationPanel.add(cancelButton);
		operationPanel.add(new JLabel());
		
		
		
		pas.add(leftPanel,BorderLayout.WEST);
		pas.add(rightPanel,BorderLayout.EAST);
		pas.add(operationPanel, BorderLayout.CENTER);
		
		for(int i=0;i<9;i++){                 //从数据库里面读取每一个座位的可用情况，已被用的座位设为灰色
		   int k=i+1;
		   String K="0"+k;                     //10以下的重新编号是为了控制长度相当，防止因为后面没有打空格，比如71，本应没有申请正确座位，但是仍然申请到了
		   String isUsedSQL="SELECT * FROM seat_Use WHERE seatID='"+K+"'";
		   Vector<Object> vector=new Vector();
		   vector=OperationDao.selectOneNote(isUsedSQL);
		   boolean isUsed=(boolean) vector.get(1);      //当时在这里一直报错，是因为该数据库里面座位编号的时候  把1-9改为01-09，漏了9，然后一直说有空指针  java.lang.NullPointerException
		   if(isUsed){seatid[i].setEnabled(false);}
		}
		for(int i=9;i<84;i++){                 
			   int k=i+1;
			   String K=""+k;
			   String isUsedSQL="SELECT * FROM seat_Use WHERE seatID='"+K+"'";
			   Vector<Object> vector=new Vector();
			   vector=OperationDao.selectOneNote(isUsedSQL);
			   boolean isUsed=(boolean) vector.get(1);
			   if(isUsed){seatid[i].setEnabled(false);}
			}
		
		
//		OKButton.setEnabled(false);
		
	
		IDTextField.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){return;}
			public void keyReleased(KeyEvent e){return;}
			public void keyTyped(KeyEvent e){
				getStuID=IDTextField.getText().trim();            //要打#
			}
		});
		
		seatTextField.addKeyListener(new KeyListener(){          //①代码复制上面的变量没有改
			public void keyPressed(KeyEvent e){return;}
			public void keyReleased(KeyEvent e){return;}
			public void keyTyped(KeyEvent e){
				getSeatID=seatTextField.getText().trim();        //要到空格
			}
		});
		
		OKButton.addMouseListener(new MouseListener(){
			public void mouseEntered(MouseEvent e){return;}
        	public void mousePressed(MouseEvent e){return;}
        	public void mouseReleased(MouseEvent e){return;}
        	public void mouseExited(MouseEvent e){return;}
        	public void mouseClicked(MouseEvent e){
        		Object i=e.getSource();
        		int j=e.getButton();
        		int k=e.getClickCount();
        		if((i==OKButton)&&(j==MouseEvent.BUTTON1)&&(k==1)){     //保证点击有效
        			if((getStuID!=null)&&(getSeatID!=null)){            //确保已输入的数据不为空
        				System.out.println(getStuID);
        				System.out.println(getSeatID);
        				String searchStuSQL="SELECT * FROM stu_Info WHERE ID='"+getStuID+"'";
        				String searchSeatSQL="SELECT * FROM seat_Use WHERE seatID='"+getSeatID+"'";  //②这个地方的SQL语句写错了= =
//        				String searchSeat="SELECT * FROM　seat_Use WHERE seatID='"+getSeatID+"'";
        				
        				Vector<Object> vectorStu=new Vector();      
        				Vector<Object> vectorSeat=new Vector();
        				
        				vectorStu=OperationDao.selectOneNote(searchStuSQL);     //①②错误导致Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
        				vectorSeat=OperationDao.selectOneNote(searchSeatSQL);
        		
//        				for(int x=0;x<vectorStu.size();x++){         
//        					System.out.println(vectorStu.get(x));          
//        				}
//        				for(int y=0;y<vectorSeat.size();y++){
//        					System.out.println(vectorSeat.get(y));
//        				}
//        				
        			    if((vectorStu!=null)&&(vectorSeat!=null)){        //这一大段的括号配对配错了   //null有两种情况：一个是真的输错了，另一个是没有打空格，只读到了9位
        			    	boolean a=(boolean)vectorSeat.get(1);
        			    	if(a==true){
        			    		JOptionPane.showMessageDialog(null,"座位已被占用！","操作失败",JOptionPane.INFORMATION_MESSAGE);
        			    		seatTextField.setText("");
        			    	}else if((boolean)vectorStu.get(7)){
        			    			JOptionPane.showMessageDialog(null,"你已申请过座位！","操作失败",JOptionPane.INFORMATION_MESSAGE);
        			    			dispose();
        			    		}else{
//        			    	   SimpleDateFormat tpl=new SimpleDateFormat("HH:MM:SS");
//        	        		   String startTime=tpl.format(new Date());
//        			    	   String updateStu="UPDATE stu_Info SET (isApplySeat,applySeatID) VALUES (true,'"+getSeatID+"') WHERE ID='"+getStuID+"'";
        			    	   String updateStuSQL="UPDATE stu_Info SET isApplySeat=true, applySeatID='"+getSeatID+"' WHERE ID='"+getStuID+"'";
        			    	   String updateSeatSQL="UPDATE seat_Use SET isUsed=true,userID='"+getStuID+"' WHERE seatID='"+getSeatID+"'";
        			    	   OperationDao.longHaul(updateStuSQL);
        			    	   OperationDao.longHaul(updateSeatSQL);
        			    	   JOptionPane.showMessageDialog(null,"选座成功！","操作成功",JOptionPane.INFORMATION_MESSAGE);
        			    	   dispose();
        			    	}
        			    }else{
        			    	JOptionPane.showMessageDialog(null,"学号或座位号输入有误！","操作失败",JOptionPane.INFORMATION_MESSAGE);
        			    	IDTextField.setText("");
        			    	seatTextField.setText("");
        			    	}
        			    }
    
        		else{         //当学号或座位号为空的时候
        			JOptionPane.showMessageDialog(null,"还未输入学号或座位号！","操作失败",JOptionPane.INFORMATION_MESSAGE);
        		} 
        	  }
        	}
		});
		
		cancelButton.addMouseListener(new MouseListener(){
			public void mouseEntered(MouseEvent e){return;}
        	public void mousePressed(MouseEvent e){return;}
        	public void mouseReleased(MouseEvent e){return;}
        	public void mouseExited(MouseEvent e){return;}
        	public void mouseClicked(MouseEvent e){
        		Object i=e.getSource();
        		int j=e.getButton();
        		int k=e.getClickCount();
        		if((i==cancelButton)&&(j==MouseEvent.BUTTON1)&&(k==1)){
        			dispose();                
        		}
        	}
		});
		
		setVisible(true);
	}
	
	
}

