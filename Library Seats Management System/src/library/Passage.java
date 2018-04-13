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

public class Passage extends JFrame{                        //��λ�����д��󣡣�����
	private String getSeatID;              //��λ����õ����Զ���ţ����Ǻ�����Ȼ������string����
	private String getStuID;
	Passage(){
		super();
		setTitle("�ϱ�������λͼ");
		setResizable(false);
		setBounds(200,100,500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		Container pas=getContentPane();
		
		final JPanel leftPanel=new JPanel(new GridLayout(14,3,10,10));
		final JPanel rightPanel=new JPanel(new GridLayout(14,3,10,10)); 
     	final JButton[] seatid = new JButton[84];//=new JLabel[80];    //�뽨��һ���������水ť  ��ǩ�Ա����ù��������������settext������= =,�����ָ��Ϊ�յ���ʾ
     	                                                              //̽��֮������ѭ�����滹��Ҫnewһ�Σ�
		for(int i=0;i<9;i++){                    //ע��������Ƶ���9 ������10  ��������010�����
			int k=i+1;
			seatid[i]=new JButton("0"+k);  //��һ������Ҫ������������������
//			seatid[i].setText(""+k);    //�¼���get����intת��Ϊstring
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
		final JLabel tipLabel1=new JLabel("������ѧ�Ų��Կո������");
		final JLabel tipLabel2=new JLabel("��������λ�Ų��Կո������");
		final JTextField IDTextField=new JTextField();
		final JTextField seatTextField=new JTextField();
		final JButton OKButton=new JButton("ȷ��");
		final JButton cancelButton=new JButton("ȡ��");
		
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
		
		for(int i=0;i<9;i++){                 //�����ݿ������ȡÿһ����λ�Ŀ���������ѱ��õ���λ��Ϊ��ɫ
		   int k=i+1;
		   String K="0"+k;                     //10���µ����±����Ϊ�˿��Ƴ����൱����ֹ��Ϊ����û�д�ո񣬱���71����Ӧû��������ȷ��λ��������Ȼ���뵽��
		   String isUsedSQL="SELECT * FROM seat_Use WHERE seatID='"+K+"'";
		   Vector<Object> vector=new Vector();
		   vector=OperationDao.selectOneNote(isUsedSQL);
		   boolean isUsed=(boolean) vector.get(1);      //��ʱ������һֱ��������Ϊ�����ݿ�������λ��ŵ�ʱ��  ��1-9��Ϊ01-09��©��9��Ȼ��һֱ˵�п�ָ��  java.lang.NullPointerException
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
				getStuID=IDTextField.getText().trim();            //Ҫ��#
			}
		});
		
		seatTextField.addKeyListener(new KeyListener(){          //�ٴ��븴������ı���û�и�
			public void keyPressed(KeyEvent e){return;}
			public void keyReleased(KeyEvent e){return;}
			public void keyTyped(KeyEvent e){
				getSeatID=seatTextField.getText().trim();        //Ҫ���ո�
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
        		if((i==OKButton)&&(j==MouseEvent.BUTTON1)&&(k==1)){     //��֤�����Ч
        			if((getStuID!=null)&&(getSeatID!=null)){            //ȷ������������ݲ�Ϊ��
        				System.out.println(getStuID);
        				System.out.println(getSeatID);
        				String searchStuSQL="SELECT * FROM stu_Info WHERE ID='"+getStuID+"'";
        				String searchSeatSQL="SELECT * FROM seat_Use WHERE seatID='"+getSeatID+"'";  //������ط���SQL���д����= =
//        				String searchSeat="SELECT * FROM��seat_Use WHERE seatID='"+getSeatID+"'";
        				
        				Vector<Object> vectorStu=new Vector();      
        				Vector<Object> vectorSeat=new Vector();
        				
        				vectorStu=OperationDao.selectOneNote(searchStuSQL);     //�٢ڴ�����Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
        				vectorSeat=OperationDao.selectOneNote(searchSeatSQL);
        		
//        				for(int x=0;x<vectorStu.size();x++){         
//        					System.out.println(vectorStu.get(x));          
//        				}
//        				for(int y=0;y<vectorSeat.size();y++){
//        					System.out.println(vectorSeat.get(y));
//        				}
//        				
        			    if((vectorStu!=null)&&(vectorSeat!=null)){        //��һ��ε�������������   //null�����������һ�����������ˣ���һ����û�д�ո�ֻ������9λ
        			    	boolean a=(boolean)vectorSeat.get(1);
        			    	if(a==true){
        			    		JOptionPane.showMessageDialog(null,"��λ�ѱ�ռ�ã�","����ʧ��",JOptionPane.INFORMATION_MESSAGE);
        			    		seatTextField.setText("");
        			    	}else if((boolean)vectorStu.get(7)){
        			    			JOptionPane.showMessageDialog(null,"�����������λ��","����ʧ��",JOptionPane.INFORMATION_MESSAGE);
        			    			dispose();
        			    		}else{
//        			    	   SimpleDateFormat tpl=new SimpleDateFormat("HH:MM:SS");
//        	        		   String startTime=tpl.format(new Date());
//        			    	   String updateStu="UPDATE stu_Info SET (isApplySeat,applySeatID) VALUES (true,'"+getSeatID+"') WHERE ID='"+getStuID+"'";
        			    	   String updateStuSQL="UPDATE stu_Info SET isApplySeat=true, applySeatID='"+getSeatID+"' WHERE ID='"+getStuID+"'";
        			    	   String updateSeatSQL="UPDATE seat_Use SET isUsed=true,userID='"+getStuID+"' WHERE seatID='"+getSeatID+"'";
        			    	   OperationDao.longHaul(updateStuSQL);
        			    	   OperationDao.longHaul(updateSeatSQL);
        			    	   JOptionPane.showMessageDialog(null,"ѡ���ɹ���","�����ɹ�",JOptionPane.INFORMATION_MESSAGE);
        			    	   dispose();
        			    	}
        			    }else{
        			    	JOptionPane.showMessageDialog(null,"ѧ�Ż���λ����������","����ʧ��",JOptionPane.INFORMATION_MESSAGE);
        			    	IDTextField.setText("");
        			    	seatTextField.setText("");
        			    	}
        			    }
    
        		else{         //��ѧ�Ż���λ��Ϊ�յ�ʱ��
        			JOptionPane.showMessageDialog(null,"��δ����ѧ�Ż���λ�ţ�","����ʧ��",JOptionPane.INFORMATION_MESSAGE);
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

