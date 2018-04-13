package library;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class secondPlan extends JFrame {           //���ƶ�¥��ƽ��ͼ
	secondPlan(){
		super();
		setTitle("��¥ƽ��ͼ");
		setResizable(false);
		setBounds(600,200,512,384);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		Container erlou=getContentPane();       //���д�� Container erlou=new Container()  �ǲ��������õ���Щ������ֵ�;
		
		final JLabel tipLabel=new JLabel("��ѡ����λ���ڵ�����",SwingConstants.LEFT);      //�ϲ��ı�ǩ
		final JButton literRoomButton=new JButton("��ѧ���");                               //�в���������λ�ֲ�����
		final JButton southHallButton=new JButton("���Ŵ���");
		final JButton passageButton=new JButton("�ϱ�����");
		
		JPanel operationPanel=new JPanel(new GridLayout(1,2));                             //��壬�ŵ���:ȷ����ȡ������
//		final JButton selectButton=new JButton("ѡ��");
		final JButton cancelButton=new JButton("ȡ��");
//		operationPanel.add(selectButton);
		operationPanel.add(cancelButton);
		
		erlou.add(tipLabel,BorderLayout.NORTH);
		erlou.add(literRoomButton,BorderLayout.WEST);
		erlou.add(southHallButton,BorderLayout.CENTER);
		erlou.add(passageButton,BorderLayout.EAST);
		erlou.add(operationPanel,BorderLayout.SOUTH);                          //��Ŀǰ���ԣ��Ҷ�������Ŀ�ĸ������Ϊ���ֿ��ǣ���Ŀǰ��û�и������ķ�������ʹ�����ϵĲ��ִﵽ����Ҫ�����ӣ����������߼��ϵ���֯��ϣ������Щ�й�ϵ����������һ��
		
		/*
		 * �¼�
		 */
		//����ϱ�����
		passageButton.addMouseListener(new MouseListener(){
			public void mouseEntered(MouseEvent e){return;}
        	public void mousePressed(MouseEvent e){return;}
        	public void mouseReleased(MouseEvent e){return;}
        	public void mouseExited(MouseEvent e){return;}
        	public void mouseClicked(MouseEvent e){
        		Object i=e.getSource();
        		int j=e.getButton();
        		int k=e.getClickCount();
        		if((i==passageButton)&&(j==MouseEvent.BUTTON1)&&(k==1))  
        			new Passage();
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
