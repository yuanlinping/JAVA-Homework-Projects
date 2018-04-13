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

public class compLeave extends JFrame{          //完全离开弹出的窗口
	
	public static final int IDLength=10;          //定义一个静态变量，存放学号的大小。 因为在做数据库的时候发现，那个编号不能到十位（2151800107）= =
	String stuID;                                     //储存学号，是传给数据库的参数
	int len;                                     //记录IDTextFiled中的字符长度
	
	static public GridBagConstraints setGBC(GridBagConstraints G,int x,int y,int gw,int gh,double wx,double wy,Insets is,int fill, int anc){       //统一设置GridBagConstraints的参数
		//把GridBagConstraints的相关设置用一个函数写了出了，但实际上GBC是有构造函数的，并不知道他的构造函数有几种，所以不大直接套用其构造函数，之后去查一下把
		//好吧，目测现在我已经把GridBagConstraints的所有参数重写了一遍，可以直接用构造函数了= =  但是我不想删掉~~~
		G.gridx=x;      //gridx是左右走向的！！！！
		G.gridy=y;      //gridy是上下走向的！！！！
		G.gridwidth=gw;
		G.gridheight=gh;
		G.weightx=wx;
		G.weighty=wy;
		G.insets=is;
		G.fill=fill;
		G.anchor=anc;
		return G;
	}
	
	compLeave(){       //构造函数
		super();
		setTitle("完全离开");
		setResizable(false);
		setBounds(1000,500,300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());                   //采用网格粗布局 
		                                                  //这个布局我很疑惑的是，这个窗口的总网格数是怎么确定的  是根据具体的情况决定的吗 即动态的改变网格的大小和数量吗
		                                                  //然后他是以什么为单位的？ 比如说，我在下面写“确定”和“取消”按钮的时候，那一行会影响到上面学号那一行的布局吗？
		
		Container comp=getContentPane();
		
		final JLabel tipLabel=new JLabel("谢谢使用！");  //定义此面板中的各种组件
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
		GBC=setGBC(GBC,0,0,0,1,0,0,ins,GridBagConstraints.NONE,GridBagConstraints.CENTER); // 第三个参数（GBC的gridwidth）为零时，即在该行只有这一个组件
		comp.add(tipLabel,GBC);
		
		ins=new Insets(10,0,0,0);           //IDLabel(下) 与tipLabel(上)相距10个像素 
		GBC=setGBC(GBC,0,1,2,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		comp.add(IDLabel,GBC);
		
		ins=new Insets(10,5,0,0);          //IDTextFiled（右）和IDLabel（左）相距5个像素， 然后需要注意的是，“10”，同样设置IDLabel(下) 与tipLabel(上)相距10个像素，否则IDLabel和IDTextField上边界会不在一个水平面上
		GBC=setGBC(GBC,GridBagConstraints.RELATIVE,1,3,1,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);   //将gridx设为RELATIVE，则当前加入的组件将放在上一个组件的右边
		comp.add(IDTextField,GBC);  
		
		ins=new Insets(10,0,0,0);
		GBC=setGBC(GBC,0,2,2,2,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		comp.add(OKButton,GBC);
		
		ins=new Insets(10,55,0,0);
		GBC=setGBC(GBC,3,2,2,2,0,0,ins,GridBagConstraints.BOTH,GridBagConstraints.CENTER);    //我感觉insets里面的间距和gridx和gridy存在矛盾是只能满足一个
		comp.add(cancelButton,GBC); 
	
		//当为输入学号的时候，设置“确定按钮不可用”
		OKButton.setEnabled(false);
		
		setVisible(true);
		
		/*
		 * 事件
		 */
		//为IDTextField添加键盘监听器，获取学号信息，修改数据库
	    //还需添加考虑：随之而来的便是判断是否输入的数字是否合法 ————正则表达式
		IDTextField.addKeyListener(new KeyListener(){         //目测键盘事件是以敲击一个键作为“一次”的,所以每敲击一次键盘下面的三个函数都会运行一次
			public void keyPressed(KeyEvent e){
				len=IDTextField.getText().trim().length();    //三个函数里面我都写了这个  为什么不可以写在前两行之间，就写一次呢？？
				if((len==IDLength-1)||(len==IDLength)){   //当学号数输入足够时，“确定键”可以使用   9是数字还不够的时候，10是当已经输入了10个，用户还多敲的情况
					                                           //经过测试，输入第十个数的时候，length读到的长度应该只有9    也就是说，第十个数并没有立即写进去 ？？？
					                                           //但好像也不对  因为在下面判断的又是10= =  怎么回事！！！
					                                            //对，没错  9和10是统一的。  就是当前敲击第K+1下，还没有被写进进去，gettext（）还不承认他。 当按enter的时候，相当于是第11此敲击，而输入第10个数的时候是第10次敲击
					                                           //(len==IDLength)是因为考虑到有人打了10个以后仍然继续打
					OKButton.setEnabled(true);
				}
				else OKButton.setEnabled(false);        //如果没有这一条语句，那么在变为true之后，我在删去了一些数字，就变不会来了     //其实并不会马上变为灰色，会在自己看到的字符串为8的时候，才变成灰色   啊啊啊，JTextField的这个的滞后性啊
			}    
			
			public void keyReleased(KeyEvent e){
				len=IDTextField.getText().trim().length();
				if(len==IDLength){      
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						stuID=IDTextField.getText().trim();
        		        String selectSQL="SELECT * FROM stu_Info WHERE ID='"+stuID+"'";
        		        Vector <Object> userVector=OperationDao.selectOneNote(selectSQL);    //确定学号是否输入正确，在数据库中是否有这个人
        		        if(userVector!=null){           //当能够查找到这个人的信息是返回一个不为空的向量
        			    String hisSeat=(String) userVector.get(8); //8是在表stu_Info中的applySeatID对应的序号
        			    if(hisSeat!=null){                    //先判断这个人选了座位，有座位执行下面的
        				   String modifySeatUseSQL="UPDATE seat_Use SET isUsed=false,userID=null WHERE seatID='"+hisSeat+"'"; 
//            			   System.out.println("zhuxiaozuoweihao wei"+hisSeat);
            			   String compLeaveSQL="UPDATE stu_Info SET isApplySeat=false,applySeatID=null WHERE ID='"+stuID+"'";
            			   boolean a=OperationDao.longHaul(compLeaveSQL);
            			   boolean b=OperationDao.longHaul(modifySeatUseSQL);
            			   JOptionPane.showMessageDialog(null,"已注销座位！","操作成功",JOptionPane.INFORMATION_MESSAGE);
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
	}
			
			public void keyTyped(KeyEvent e){                    //控制输入的个数以及输入的字符范围
				len=IDTextField.getText().trim().length();
				if(len<IDLength){    //当输入的数字字符还没有达到10个时，需要判段输入的字符是不是数字字符——这个是要为Integer.parseInt(“数字字符串”)做准备，
					String num="0123456789";
				    if(num.indexOf(e.getKeyChar())<0) e.consume();
				}
				else e.consume();        //当已经有10个后，不再接受输入字符串
//				if(IDTyped.length()==10){
//					OKButton.setEnabled(true);
//					ID=Integer.parseInt(IDTyped);
//					System.out.println(ID);
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
        			/*
        			 * 还没写完
        			 */
//        		System.out.println(stuID); 
        		String selectSQL="SELECT * FROM stu_Info WHERE ID='"+stuID+"'";
        		Vector <Object> userVector=OperationDao.selectOneNote(selectSQL);    //确定学号是否输入正确，在数据库中是否有这个人
        		if(userVector!=null){           //当能够查找到这个人的信息是返回一个不为空的向量
        			String hisSeat=(String) userVector.get(8); //8是在表stu_Info中的applySeatID对应的序号
        			if(hisSeat!=null){                    //先判断这个人选了座位，有座位执行下面的
        				String modifySeatUseSQL="UPDATE seat_Use SET isUsed=false,userID=null WHERE seatID='"+hisSeat+"'";    //我要哭了 = =我写这一条 按照compLeaveSQL写的where语句，结果不对不对,一个一个试，试到最开始的方法才对= = 哇呜
//            			String modifySeatUseSQL="UPDATE seat_Use SET isUsed=false,userID=null WHERE seatID='"+hisSeat+"'";    //将此人使用的座位使用状态设为未使用,且把对应的使用读者删去
//            			String modifySeatUseSQL="UPDATE seat_Use SET isUsed=false,userID=null WHERE seatID=84";   //直接输数字又可以   之前写的是'84'，怎么老是出现“标准表达式中数据类型不匹配” = =  后来把''删掉就好了
//            			System.out.println("zhuxiaozuoweihao wei"+hisSeat);
            			String compLeaveSQL="UPDATE stu_Info SET isApplySeat=false,applySeatID null WHERE ID='"+stuID+"'";
//          		    String compLeaveSQL="Update stu_Info SET stuName='ylp' WHERE ID='"+stuID+"'";   //原来要这么写！！！ID='"+stuID+"'
//            			String SQL1="INSERT INTO stu_Info(isApplySeat) VALUES (yes) ";//WHERE ID="+"2151600010";
            			boolean a=OperationDao.longHaul(compLeaveSQL);
            			boolean b=OperationDao.longHaul(modifySeatUseSQL);
//            			System.out.println("义修改！"+a);
            			JOptionPane.showMessageDialog(null,"已注销座位！","操作成功",JOptionPane.INFORMATION_MESSAGE);
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
        			dispose();                //退出完全离开窗口  与setVisible（false）相比，这条语句关闭了窗口而且释放了一部分资源。 而setfalse(true)仅仅是隐藏了它
        			                         //还有什么WIndowEvent  过后去学习
        		}
        	}
		});
	}
		 
}
