package library;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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

public class tempLeave extends JFrame{                //临时离开弹出的窗口
	public static final int IDLength=10;          
	String stuID;                                     
	int len;
	
	tempLeave(){
			super();
			setTitle("临时离开");
			setResizable(false);
			setBounds(1000,500,300,200);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new GridBagLayout());                   
			
			Container temp=getContentPane();
			
			final JLabel tipLabel=new JLabel("请按时回来！");  //定义此面板中的各种组件
			final JLabel IDLabel=new JLabel("学号：");
			final JTextField IDTextField=new JTextField();
			final JButton OKButton=new JButton("确定");
			final JButton cancelButton=new JButton("取消");
			
			GridBagConstraints GBC=new GridBagConstraints();
			Insets ins;
			
			/*
			 * 将各种组件以规定的位置、大小放入面板中
			 */
			
			ins=new Insets(0,0,0,0);
			GBC=compLeave.setGBC(GBC,0,0,0,1,0,0,ins,GridBagConstraints.NONE,GridBagConstraints.CENTER); 
			temp.add(tipLabel,GBC);
			
			ins=new Insets(10,0,0,0);           
			GBC=compLeave.setGBC(GBC,0,1,2,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
			temp.add(IDLabel,GBC);
			
			ins=new Insets(10,5,0,0);          
			GBC=compLeave.setGBC(GBC,GridBagConstraints.RELATIVE,1,3,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);  
			temp.add(IDTextField,GBC);  
			
			ins=new Insets(10,0,0,0);
			GBC=compLeave.setGBC(GBC,0,2,2,2,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
			temp.add(OKButton,GBC);
			
			ins=new Insets(10,55,0,0);
			GBC=compLeave.setGBC(GBC,3,2,2,2,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER); 
			temp.add(cancelButton,GBC); 
			
			
			OKButton.setEnabled(false);
		
		    setVisible(true);
		    
		    
		    /*
			 * 事件
			 */
			//为IDTextField添加键盘监听器，获取学号信息，修改数据库
			IDTextField.addKeyListener(new KeyListener(){         
				public void keyPressed(KeyEvent e){
				    len = IDTextField.getText().trim().length();    
					if((len==IDLength-1)||(len==IDLength)){
						OKButton.setEnabled(true);
					}
					else OKButton.setEnabled(false);    
				}    
				
				public void keyReleased(KeyEvent e){
					len=IDTextField.getText().trim().length();
					if(len==IDLength){      
						if(e.getKeyCode()==KeyEvent.VK_ENTER)
							stuID=IDTextField.getText().trim();
        		        String selectSQL="SELECT * FROM stu_Info WHERE ID='"+stuID+"'";
        		        Vector <Object> userVector=OperationDao.selectOneNote(selectSQL);    //确定学号是否输入正确，在数据库中是否有这个人
        		        if(userVector!=null){           //当能够查找到这个人的信息是返回一个不为空的向量
        			    String hisSeat=(String) userVector.get(8); //8是在表stu_Info中的applySeatID对应的序号
        			    if(hisSeat!=null){                    //先判断这个人选了座位，有座位执行下面的
//        				   String modifySeatUseSQL="UPDATE seat_Use SET isUsed=false,userID=null WHERE seatID="+hisSeat; 
//            			   System.out.println("zhuxiaozuoweihao wei"+hisSeat);
        			       SimpleDateFormat tpl=new SimpleDateFormat("HH:MM:SS");
        			       String tmpl=tpl.format(new Date());
            			   String tempLeaveSQL="UPDATE stu_Info SET tempLeaveStartTime='"+tmpl+"' WHERE ID='"+stuID+"'";
            			   boolean a=OperationDao.longHaul(tempLeaveSQL);
//            			   boolean b=OperationDao.longHaul(modifySeatUseSQL);
            			   JOptionPane.showMessageDialog(null,"已离开！","操作成功",JOptionPane.INFORMATION_MESSAGE);
            			   dispose();                //当点击上面出现的对话框的“确定以后”，完全离开窗口和此对话框都会被关闭
        			}else{             //还未选座
        				JOptionPane.showMessageDialog(null,"你还未选择座位！","操作失败",JOptionPane.INFORMATION_MESSAGE);
        				dispose();
        			}
        		  }else{                     //找不到这个人的信息时，向量为空，需要让他重新输入
        			JOptionPane.showMessageDialog(null,"学号输入有误，请重新输入！","操作失败",JOptionPane.INFORMATION_MESSAGE);
//        			dispose();                //当点击上面出现的对话框的“确定以后”，完全离开窗口和此对话框都会被关闭
        			IDTextField.setText("");
        		  }
				}
			}
				
				public void keyTyped(KeyEvent e){                    
					len=IDTextField.getText().trim().length();
					if(len<IDLength){    
						String num="0123456789";
					    if(num.indexOf(e.getKeyChar())<0) e.consume();
					}
					else e.consume();    
					}
			});
			
			//为确定键添加鼠标监听器，做与IDTextField中的“Enter”一样的事情
			OKButton.addMouseListener(new MouseListener(){
				public void mouseEntered(MouseEvent e){return;}
	        	public void mousePressed(MouseEvent e){return;}
	        	public void mouseReleased(MouseEvent e){return;}
	        	public void mouseExited(MouseEvent e){return;}
	        	public void mouseClicked(MouseEvent e){
	        		Object i=e.getSource();
	        		int j=e.getButton();
	        		int k=e.getClickCount();
	        		if((i==OKButton)&&(j==MouseEvent.BUTTON1)&&(k==1)){
	        			/*
						 * 数据库操作   写操作
						 */
	        			JOptionPane.showMessageDialog(null,"已注销座位！","操作成功",JOptionPane.INFORMATION_MESSAGE);
	        			dispose();                //当点击上面出现的对话框的“确定以后”，完全离开窗口和此对话框都会被关闭
	        		}  
	        	}
			});

			//为取消键添加鼠标监听器，点击后关闭当前窗口
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
	}

}




