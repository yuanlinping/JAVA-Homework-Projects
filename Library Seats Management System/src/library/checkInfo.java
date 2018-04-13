package library;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.OperationDao;

public class checkInfo extends JFrame{     //点击查看我的座位，弹出的窗口
	public static final int IDLength=10;          
	String stuID;                                     
	int len;                                     
	
	checkInfo(){
		super();
		setTitle("查看我的座位");
		setResizable(false);
		setBounds(1000,500,300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());                   
		
		Container info=getContentPane();
		
		final JLabel tipLabel=new JLabel("请输入学号！");  //定义此面板中的各种组件
		final JLabel IDLabel=new JLabel("学号：");
		final JTextField IDTextField=new JTextField(10);
		final JButton OKButton=new JButton("确定");
		final JButton cancelButton=new JButton("取消");
		
		GridBagConstraints GBC=new GridBagConstraints();
		Insets ins;
		
		/*
		 * 将各种组件以规定的位置、大小放入面板中
		 */
		
		ins=new Insets(0,0,0,0);
		GBC=compLeave.setGBC(GBC,0,0,0,1,0,0,ins,GridBagConstraints.NONE,GridBagConstraints.CENTER); 
		info.add(tipLabel,GBC);
		
		ins=new Insets(10,0,0,0);           
		GBC=compLeave.setGBC(GBC,0,1,2,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		info.add(IDLabel,GBC);
		
		ins=new Insets(10,5,0,0);          
		GBC=compLeave.setGBC(GBC,GridBagConstraints.RELATIVE,1,3,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);  
		info.add(IDTextField,GBC);  
		
		ins=new Insets(10,0,0,0);
		GBC=compLeave.setGBC(GBC,0,2,2,2,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		info.add(OKButton,GBC);
		
		ins=new Insets(10,55,0,0);
		GBC=compLeave.setGBC(GBC,3,2,2,2,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER); 
		info.add(cancelButton,GBC); 
		
		
		OKButton.setEnabled(false);
		
		
		
		IDTextField.addKeyListener(new KeyListener(){         
			public void keyPressed(KeyEvent e){
				len=IDTextField.getText().trim().length();    
				if((len==IDLength-1)||(len==IDLength)){     
//				if((len==IDLength-1)){ 
					OKButton.setEnabled(true);
				}
				else OKButton.setEnabled(false);         //其实并不会马上变为灰色，会在自己看到的字符串为8的时候，才变成灰色   啊啊啊，JTextField的这个的滞后性啊
			}    
			
			public void keyReleased(KeyEvent e){
				len=IDTextField.getText().trim().length();
				if(len==IDLength){      
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						stuID=IDTextField.getText().trim();
        		        String selectSQL="SELECT * FROM stu_Info WHERE ID='"+stuID+"'";
        		        Vector <Object> userVector=OperationDao.selectOneNote(selectSQL);    
        		        if(userVector!=null){           
        			    String hisSeat=(String) userVector.get(8); 
        			    if(hisSeat!=null){                    
        			       String stuName=(String) userVector.get(1);
        			       String stuID=(String) userVector.get(0);	
        			       new InfoPanel(stuName,stuID,hisSeat);
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
	}
			
			public void keyTyped(KeyEvent e){                    //控制输入的个数以及输入的字符范围
				len=IDTextField.getText().trim().length();
				if(len<IDLength){    
					String num="0123456789";
				    if(num.indexOf(e.getKeyChar())<0) e.consume();
				}
				else e.consume();        //当已经有10个后，不再接受输入字符串
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
        			stuID=IDTextField.getText().trim();
    		        String selectSQL="SELECT * FROM stu_Info WHERE ID='"+stuID+"'";
    		        Vector <Object> userVector=OperationDao.selectOneNote(selectSQL);    
    		        if(userVector!=null){           
    			    String hisSeat=(String) userVector.get(8); 
    			    if(hisSeat!=null){                    
    			       String stuName=(String) userVector.get(1);
    			       String stuID=(String) userVector.get(0);	
    			       new InfoPanel(stuName,stuID,hisSeat);
    			}else{             //还未选座
    				JOptionPane.showMessageDialog(null,"你还未选择座位！","操作失败",JOptionPane.INFORMATION_MESSAGE);
    				dispose();
    			}
    		  }else{                     //找不到这个人的信息时，向量为空，需要让他重新输入
    			JOptionPane.showMessageDialog(null,"学号输入有误，请重新输入！","操作失败",JOptionPane.INFORMATION_MESSAGE);
//    			dispose();                //当点击上面出现的对话框的“确定以后”，完全离开窗口和此对话框都会被关闭
    			IDTextField.setText("");
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
