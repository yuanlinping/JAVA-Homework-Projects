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

public class checkInfo extends JFrame{     //����鿴�ҵ���λ�������Ĵ���
	public static final int IDLength=10;          
	String stuID;                                     
	int len;                                     
	
	checkInfo(){
		super();
		setTitle("�鿴�ҵ���λ");
		setResizable(false);
		setBounds(1000,500,300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());                   
		
		Container info=getContentPane();
		
		final JLabel tipLabel=new JLabel("������ѧ�ţ�");  //���������еĸ������
		final JLabel IDLabel=new JLabel("ѧ�ţ�");
		final JTextField IDTextField=new JTextField(10);
		final JButton OKButton=new JButton("ȷ��");
		final JButton cancelButton=new JButton("ȡ��");
		
		GridBagConstraints GBC=new GridBagConstraints();
		Insets ins;
		
		/*
		 * ����������Թ涨��λ�á���С���������
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
				else OKButton.setEnabled(false);         //��ʵ���������ϱ�Ϊ��ɫ�������Լ��������ַ���Ϊ8��ʱ�򣬲ű�ɻ�ɫ   ��������JTextField��������ͺ��԰�
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
	}
			
			public void keyTyped(KeyEvent e){                    //��������ĸ����Լ�������ַ���Χ
				len=IDTextField.getText().trim().length();
				if(len<IDLength){    
					String num="0123456789";
				    if(num.indexOf(e.getKeyChar())<0) e.consume();
				}
				else e.consume();        //���Ѿ���10���󣬲��ٽ��������ַ���
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
        			stuID=IDTextField.getText().trim();
    		        String selectSQL="SELECT * FROM stu_Info WHERE ID='"+stuID+"'";
    		        Vector <Object> userVector=OperationDao.selectOneNote(selectSQL);    
    		        if(userVector!=null){           
    			    String hisSeat=(String) userVector.get(8); 
    			    if(hisSeat!=null){                    
    			       String stuName=(String) userVector.get(1);
    			       String stuID=(String) userVector.get(0);	
    			       new InfoPanel(stuName,stuID,hisSeat);
    			}else{             //��δѡ��
    				JOptionPane.showMessageDialog(null,"�㻹δѡ����λ��","����ʧ��",JOptionPane.INFORMATION_MESSAGE);
    				dispose();
    			}
    		  }else{                     //�Ҳ�������˵���Ϣʱ������Ϊ�գ���Ҫ������������
    			JOptionPane.showMessageDialog(null,"ѧ�������������������룡","����ʧ��",JOptionPane.INFORMATION_MESSAGE);
//    			dispose();                //�����������ֵĶԻ���ġ�ȷ���Ժ󡱣���ȫ�뿪���ںʹ˶Ի��򶼻ᱻ�ر�
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
