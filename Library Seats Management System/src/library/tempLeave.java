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

public class tempLeave extends JFrame{                //��ʱ�뿪�����Ĵ���
	public static final int IDLength=10;          
	String stuID;                                     
	int len;
	
	tempLeave(){
			super();
			setTitle("��ʱ�뿪");
			setResizable(false);
			setBounds(1000,500,300,200);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new GridBagLayout());                   
			
			Container temp=getContentPane();
			
			final JLabel tipLabel=new JLabel("�밴ʱ������");  //���������еĸ������
			final JLabel IDLabel=new JLabel("ѧ�ţ�");
			final JTextField IDTextField=new JTextField();
			final JButton OKButton=new JButton("ȷ��");
			final JButton cancelButton=new JButton("ȡ��");
			
			GridBagConstraints GBC=new GridBagConstraints();
			Insets ins;
			
			/*
			 * ����������Թ涨��λ�á���С���������
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
			 * �¼�
			 */
			//ΪIDTextField��Ӽ��̼���������ȡѧ����Ϣ���޸����ݿ�
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
        		        Vector <Object> userVector=OperationDao.selectOneNote(selectSQL);    //ȷ��ѧ���Ƿ�������ȷ�������ݿ����Ƿ��������
        		        if(userVector!=null){           //���ܹ����ҵ�����˵���Ϣ�Ƿ���һ����Ϊ�յ�����
        			    String hisSeat=(String) userVector.get(8); //8���ڱ�stu_Info�е�applySeatID��Ӧ�����
        			    if(hisSeat!=null){                    //���ж������ѡ����λ������λִ�������
//        				   String modifySeatUseSQL="UPDATE seat_Use SET isUsed=false,userID=null WHERE seatID="+hisSeat; 
//            			   System.out.println("zhuxiaozuoweihao wei"+hisSeat);
        			       SimpleDateFormat tpl=new SimpleDateFormat("HH:MM:SS");
        			       String tmpl=tpl.format(new Date());
            			   String tempLeaveSQL="UPDATE stu_Info SET tempLeaveStartTime='"+tmpl+"' WHERE ID='"+stuID+"'";
            			   boolean a=OperationDao.longHaul(tempLeaveSQL);
//            			   boolean b=OperationDao.longHaul(modifySeatUseSQL);
            			   JOptionPane.showMessageDialog(null,"���뿪��","�����ɹ�",JOptionPane.INFORMATION_MESSAGE);
            			   dispose();                //�����������ֵĶԻ���ġ�ȷ���Ժ󡱣���ȫ�뿪���ںʹ˶Ի��򶼻ᱻ�ر�
        			}else{             //��δѡ��
        				JOptionPane.showMessageDialog(null,"�㻹δѡ����λ��","����ʧ��",JOptionPane.INFORMATION_MESSAGE);
        				dispose();
        			}
        		  }else{                     //�Ҳ�������˵���Ϣʱ������Ϊ�գ���Ҫ������������
        			JOptionPane.showMessageDialog(null,"ѧ�������������������룡","����ʧ��",JOptionPane.INFORMATION_MESSAGE);
//        			dispose();                //�����������ֵĶԻ���ġ�ȷ���Ժ󡱣���ȫ�뿪���ںʹ˶Ի��򶼻ᱻ�ر�
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
			
			//Ϊȷ���������������������IDTextField�еġ�Enter��һ��������
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
						 * ���ݿ����   д����
						 */
	        			JOptionPane.showMessageDialog(null,"��ע����λ��","�����ɹ�",JOptionPane.INFORMATION_MESSAGE);
	        			dispose();                //�����������ֵĶԻ���ġ�ȷ���Ժ󡱣���ȫ�뿪���ںʹ˶Ի��򶼻ᱻ�ر�
	        		}  
	        	}
			});

			//Ϊȡ����������������������رյ�ǰ����
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




