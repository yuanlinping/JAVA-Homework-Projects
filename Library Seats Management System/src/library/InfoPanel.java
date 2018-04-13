package library;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InfoPanel extends JFrame{
	InfoPanel(String Name,String ID,String SeatID){
		super();
		setTitle("我的座位信息");
		setResizable(false);
		setBounds(500,500,300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());                   
		
		Container info=getContentPane();
		
		final JLabel nameLabel=new JLabel("姓名：");  //定义此面板中的各种组件
		final JLabel IDLabel=new JLabel("学号：");
		final JLabel seatLabel=new JLabel("座位：");
		final JLabel currNameLabel=new JLabel();
		final JLabel currIDLabel=new JLabel();
		final JLabel currSeatLabel=new JLabel();
		
		currNameLabel.setText(Name);
		currIDLabel.setText(ID);
		currSeatLabel.setText(SeatID);
		
		GridBagConstraints GBC=new GridBagConstraints();
		Insets ins;
		
		/*
		 * 将各种组件以规定的位置、大小放入面板中
		 */
		
		ins=new Insets(0,0,0,0);
		GBC=compLeave.setGBC(GBC,0,1,2,1,0,0,ins,GridBagConstraints.NONE,GridBagConstraints.CENTER); 
		info.add(nameLabel,GBC);
		
		ins=new Insets(0,5,0,0);
		GBC=compLeave.setGBC(GBC,GridBagConstraints.RELATIVE,1,3,1,0,0,ins,GridBagConstraints.NONE,GridBagConstraints.CENTER); 
		info.add(currNameLabel,GBC);
		
		
		
		ins=new Insets(10,0,0,0);           
		GBC=compLeave.setGBC(GBC,0,2,2,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		info.add(IDLabel,GBC);
		
		ins=new Insets(10,5,0,0);          
		GBC=compLeave.setGBC(GBC,GridBagConstraints.RELATIVE,2,3,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);  
		info.add(currIDLabel,GBC);  
		
		ins=new Insets(10,0,0,0);
		GBC=compLeave.setGBC(GBC,0,3,2,2,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		info.add(seatLabel,GBC);
		
		ins=new Insets(10,5,0,0);
		GBC=compLeave.setGBC(GBC,GridBagConstraints.RELATIVE,3,3,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER); 
		info.add(currSeatLabel,GBC); 
		
		setVisible(true);
}
}
