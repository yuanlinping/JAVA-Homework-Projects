package library;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Vector;

import dao.ConnectAccessFile;
import dao.OperationDao;

public class compLeave extends JFrame{          //��ȫ�뿪�����Ĵ���
	
	public static final int IDLength=10;          //����һ����̬���������ѧ�ŵĴ�С�� ��Ϊ�������ݿ��ʱ���֣��Ǹ���Ų��ܵ�ʮλ��2151800107��= =
	String stuID;                                     //����ѧ�ţ��Ǵ������ݿ�Ĳ���
	int len;                                     //��¼IDTextFiled�е��ַ�����
	
	static public GridBagConstraints setGBC(GridBagConstraints G,int x,int y,int gw,int gh,double wx,double wy,Insets is,int fill, int anc){       //ͳһ����GridBagConstraints�Ĳ���
		//��GridBagConstraints�����������һ������д�˳��ˣ���ʵ����GBC���й��캯���ģ�����֪�����Ĺ��캯���м��֣����Բ���ֱ�������乹�캯����֮��ȥ��һ�°�
		//�ðɣ�Ŀ���������Ѿ���GridBagConstraints�����в�����д��һ�飬����ֱ���ù��캯����= =  �����Ҳ���ɾ��~~~
		G.gridx=x;      //gridx����������ģ�������
		G.gridy=y;      //gridy����������ģ�������
		G.gridwidth=gw;
		G.gridheight=gh;
		G.weightx=wx;
		G.weighty=wy;
		G.insets=is;
		G.fill=fill;
		G.anchor=anc;
		return G;
	}
	
	compLeave(){       //���캯��
		super();
		setTitle("��ȫ�뿪");
		setResizable(false);
		setBounds(1000,500,300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());                   //��������ֲ��� 
		                                                  //��������Һ��ɻ���ǣ�������ڵ�������������ôȷ����  �Ǹ��ݾ��������������� ����̬�ĸı�����Ĵ�С��������
		                                                  //Ȼ��������ʲôΪ��λ�ģ� ����˵����������д��ȷ�����͡�ȡ������ť��ʱ����һ�л�Ӱ�쵽����ѧ����һ�еĲ�����
		
		Container comp=getContentPane();
		
		final JLabel tipLabel=new JLabel("ллʹ�ã�");  //���������еĸ������
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
		GBC=setGBC(GBC,0,0,0,1,0,0,ins,GridBagConstraints.NONE,GridBagConstraints.CENTER); // ������������GBC��gridwidth��Ϊ��ʱ�����ڸ���ֻ����һ�����
		comp.add(tipLabel,GBC);
		
		ins=new Insets(10,0,0,0);           //IDLabel(��) ��tipLabel(��)���10������ 
		GBC=setGBC(GBC,0,1,2,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		comp.add(IDLabel,GBC);
		
		ins=new Insets(10,5,0,0);          //IDTextFiled���ң���IDLabel�������5�����أ� Ȼ����Ҫע����ǣ���10����ͬ������IDLabel(��) ��tipLabel(��)���10�����أ�����IDLabel��IDTextField�ϱ߽�᲻��һ��ˮƽ����
		GBC=setGBC(GBC,GridBagConstraints.RELATIVE,1,3,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);   //��gridx��ΪRELATIVE����ǰ����������������һ��������ұ�
		comp.add(IDTextField,GBC);  
		
		ins=new Insets(10,0,0,0);
		GBC=setGBC(GBC,0,2,2,2,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		comp.add(OKButton,GBC);
		
		ins=new Insets(10,55,0,0);
		GBC=setGBC(GBC,3,2,2,2,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);    //�Ҹо�insets����ļ���gridx��gridy����ì����ֻ������һ��
		comp.add(cancelButton,GBC); 
	
		//��Ϊ����ѧ�ŵ�ʱ�����á�ȷ����ť�����á�
		OKButton.setEnabled(false);
		
		setVisible(true);
		
		/*
		 * �¼�
		 */
		//ΪIDTextField��Ӽ��̼���������ȡѧ����Ϣ���޸����ݿ�
	    //������ӿ��ǣ���֮�����ı����ж��Ƿ�����������Ƿ�Ϸ� ��������������ʽ
		IDTextField.addKeyListener(new KeyListener(){         //Ŀ������¼������û�һ������Ϊ��һ�Ρ���,����ÿ�û�һ�μ������������������������һ��
			public void keyPressed(KeyEvent e){
				len=IDTextField.getText().trim().length();    //�������������Ҷ�д�����  Ϊʲô������д��ǰ����֮�䣬��дһ���أ���
				if((len==IDLength-1)||(len==IDLength)){   //��ѧ���������㹻ʱ����ȷ����������ʹ��   9�����ֻ�������ʱ��10�ǵ��Ѿ�������10�����û������õ����
					                                           //�������ԣ������ʮ������ʱ��length�����ĳ���Ӧ��ֻ��9    Ҳ����˵����ʮ������û������д��ȥ ������
					                                           //������Ҳ����  ��Ϊ�������жϵ�����10= =  ��ô���£�����
					                                            //�ԣ�û��  9��10��ͳһ�ġ�  ���ǵ�ǰ�û���K+1�£���û�б�д����ȥ��gettext���������������� ����enter��ʱ���൱���ǵ�11���û����������10������ʱ���ǵ�10���û�
					                                           //(len==IDLength)����Ϊ���ǵ����˴���10���Ժ���Ȼ������
					OKButton.setEnabled(true);
				}
				else OKButton.setEnabled(false);        //���û����һ����䣬��ô�ڱ�Ϊtrue֮������ɾȥ��һЩ���֣��ͱ䲻������     //��ʵ���������ϱ�Ϊ��ɫ�������Լ��������ַ���Ϊ8��ʱ�򣬲ű�ɻ�ɫ   ��������JTextField��������ͺ��԰�
			}    
			
			public void keyReleased(KeyEvent e){
				len=IDTextField.getText().trim().length();
				if(len==IDLength){      
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						stuID=IDTextField.getText().trim();
        		        String selectSQL="SELECT * FROM stu_Info WHERE ID='"+stuID+"'";
        		        Vector <Object> userVector=OperationDao.selectOneNote(selectSQL);    //ȷ��ѧ���Ƿ�������ȷ�������ݿ����Ƿ��������
        		        if(userVector!=null){           //���ܹ����ҵ�����˵���Ϣ�Ƿ���һ����Ϊ�յ�����
        			    String hisSeat=(String) userVector.get(8); //8���ڱ�stu_Info�е�applySeatID��Ӧ�����
        			    if(hisSeat!=null){                    //���ж������ѡ����λ������λִ�������
        				   String modifySeatUseSQL="UPDATE seat_Use SET isUsed=false,userID=null WHERE seatID='"+hisSeat+"'"; 
//            			   System.out.println("zhuxiaozuoweihao wei"+hisSeat);
            			   String compLeaveSQL="UPDATE stu_Info SET isApplySeat=false,applySeatID=null WHERE ID='"+stuID+"'";
            			   boolean a=OperationDao.longHaul(compLeaveSQL);
            			   boolean b=OperationDao.longHaul(modifySeatUseSQL);
            			   JOptionPane.showMessageDialog(null,"��ע����λ��","�����ɹ�",JOptionPane.INFORMATION_MESSAGE);
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
	}
			
			public void keyTyped(KeyEvent e){                    //��������ĸ����Լ�������ַ���Χ
				len=IDTextField.getText().trim().length();
				if(len<IDLength){    //������������ַ���û�дﵽ10��ʱ����Ҫ�ж�������ַ��ǲ��������ַ����������ҪΪInteger.parseInt(�������ַ�����)��׼����
					String num="0123456789";
				    if(num.indexOf(e.getKeyChar())<0) e.consume();
				}
				else e.consume();        //���Ѿ���10���󣬲��ٽ��������ַ���
//				if(IDTyped.length()==10){
//					OKButton.setEnabled(true);
//					ID=Integer.parseInt(IDTyped);
//					System.out.println(ID);
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
        			/*
        			 * ��ûд��
        			 */
//        		System.out.println(stuID); 
        		String selectSQL="SELECT * FROM stu_Info WHERE ID='"+stuID+"'";
        		Vector <Object> userVector=OperationDao.selectOneNote(selectSQL);    //ȷ��ѧ���Ƿ�������ȷ�������ݿ����Ƿ��������
        		if(userVector!=null){           //���ܹ����ҵ�����˵���Ϣ�Ƿ���һ����Ϊ�յ�����
        			String hisSeat=(String) userVector.get(8); //8���ڱ�stu_Info�е�applySeatID��Ӧ�����
        			if(hisSeat!=null){                    //���ж������ѡ����λ������λִ�������
        				String modifySeatUseSQL="UPDATE seat_Use SET isUsed=false,userID=null WHERE seatID='"+hisSeat+"'";    //��Ҫ���� = =��д��һ�� ����compLeaveSQLд��where��䣬������Բ���,һ��һ���ԣ��Ե��ʼ�ķ����Ŷ�= = ����
//            			String modifySeatUseSQL="UPDATE seat_Use SET isUsed=false,userID=null WHERE seatID='"+hisSeat+"'";    //������ʹ�õ���λʹ��״̬��Ϊδʹ��,�ҰѶ�Ӧ��ʹ�ö���ɾȥ
//            			String modifySeatUseSQL="UPDATE seat_Use SET isUsed=false,userID=null WHERE seatID=84";   //ֱ���������ֿ���   ֮ǰд����'84'����ô���ǳ��֡���׼���ʽ���������Ͳ�ƥ�䡱 = =  ������''ɾ���ͺ���
//            			System.out.println("zhuxiaozuoweihao wei"+hisSeat);
            			String compLeaveSQL="UPDATE stu_Info SET isApplySeat=false,applySeatID null WHERE ID='"+stuID+"'";
//          		    String compLeaveSQL="Update stu_Info SET stuName='ylp' WHERE ID='"+stuID+"'";   //ԭ��Ҫ��ôд������ID='"+stuID+"'
//            			String SQL1="INSERT INTO stu_Info(isApplySeat) VALUES (yes) ";//WHERE ID="+"2151600010";
            			boolean a=OperationDao.longHaul(compLeaveSQL);
            			boolean b=OperationDao.longHaul(modifySeatUseSQL);
//            			System.out.println("���޸ģ�"+a);
            			JOptionPane.showMessageDialog(null,"��ע����λ��","�����ɹ�",JOptionPane.INFORMATION_MESSAGE);
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
        			dispose();                //�˳���ȫ�뿪����  ��setVisible��false����ȣ��������ر��˴��ڶ����ͷ���һ������Դ�� ��setfalse(true)��������������
        			                         //����ʲôWIndowEvent  ����ȥѧϰ
        		}
        	}
		});
	}
		 
}
