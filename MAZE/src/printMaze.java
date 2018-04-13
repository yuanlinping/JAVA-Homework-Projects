	import java.awt.Color;
	import java.awt.Container;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;

	import javax.swing.JFrame;
	 
	public class printMaze extends JFrame{
			
			private Maze oneMaze;
		    private final int sx = 50;//�Թ������Ͻǵ�����꣨��Ӧ��ά����ĵڶ����±꣩
		    private final int sy = 50;//�Թ������Ͻǵ������꣨��Ӧ��ά����ĵ�һ���±꣩
		    private int w=10;    //С����ı߳�
		    private int hor;   //�Թ��ĺ��򳤶�
		    private int ver;//�Թ������򳤶�
		   
		    private Graphics jg;
		    
		    private Color rectColor = new Color(0xf5f5f5);
		    
		    public printMaze(Maze m) {
		    	oneMaze=m;
		    	w=Math.min(700/oneMaze.length(),700/oneMaze.width());   //700���������Թ���״������С����Maze�е�MAXS��ԭ����w=10����   ���ڲ�֪����ô��MAXSͨ����̬��ʽ���������������ڲ�д
		    	hor=oneMaze.width()*w;
		    	ver=oneMaze.length()*w;
//		    	super();
		        Container p = getContentPane();
		        setBounds(50, 50, 800, 800);
		        setTitle("�Թ�");
		        setVisible(true);
		        p.setBackground(rectColor);
		        setLayout(null);   
		        setResizable(false);
		        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
		        
		        try {
		            Thread.sleep(500);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }        
		       
		        jg =  this.getGraphics(); // ��ȡר�������ڴ��ڽ����ϻ�ͼ�Ķ���		        
		      
		        paint(jg);    // ����
		    }
		    

		    public void paint(Graphics g) {
		        try {
		            g.setColor(Color.RED);// ����������ɫΪ��ɫ
		            g.drawRect(sx, sy,hor,ver ); // ���������ο�

		            for(int i = 0; i <oneMaze.length()-1;i++) {
		            	for(int j=0;j<oneMaze.width();j++){
		            		if(!oneMaze.maze[i][j].wall[3].isVisit) g.drawLine(sx+j*w, sy+(i+1)*w,sx+(j+1)*w , sy+(i+1)*w);
		            	}
		            }
		            
		            for(int j=0;j<oneMaze.width()-1;j++){
		            	for(int i=0;i<oneMaze.length();i++){
		            		if(!oneMaze.maze[i][j].wall[2].isVisit) g.drawLine(sx+(j+1)*w, sy+i*w,sx+(j+1)*w , sy+(i+1)*w);
		            	}
		            	
		            }
		          
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    
		    
	}
