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

public class secondPlan extends JFrame {           //绘制二楼的平面图
	secondPlan(){
		super();
		setTitle("二楼平面图");
		setResizable(false);
		setBounds(600,200,512,384);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		Container erlou=getContentPane();       //如果写成 Container erlou=new Container()  是不会有设置的那些组件出现的;
		
		final JLabel tipLabel=new JLabel("请选择座位所在的区域：",SwingConstants.LEFT);      //上部的标签
		final JButton literRoomButton=new JButton("文学书库");                               //中部的三个座位分布区域
		final JButton southHallButton=new JButton("南门大厅");
		final JButton passageButton=new JButton("南北走廊");
		
		JPanel operationPanel=new JPanel(new GridLayout(1,2));                             //面板，放的是:确定和取消操作
//		final JButton selectButton=new JButton("选座");
		final JButton cancelButton=new JButton("取消");
//		operationPanel.add(selectButton);
		operationPanel.add(cancelButton);
		
		erlou.add(tipLabel,BorderLayout.NORTH);
		erlou.add(literRoomButton,BorderLayout.WEST);
		erlou.add(southHallButton,BorderLayout.CENTER);
		erlou.add(passageButton,BorderLayout.EAST);
		erlou.add(operationPanel,BorderLayout.SOUTH);                          //就目前而言，我定义面板的目的更多的是为布局考虑，是目前我没有更高明的方法可以使物理上的布局达到我想要的样子，而不是在逻辑上的组织，希望让那些有关系的组件组合在一起。
		
		/*
		 * 事件
		 */
		//点击南北走廊
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
