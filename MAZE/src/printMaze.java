	import java.awt.Color;
	import java.awt.Container;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;

	import javax.swing.JFrame;
	 
	public class printMaze extends JFrame{
			
			private Maze oneMaze;
		    private final int sx = 50;//迷宫最左上角点横坐标（对应二维数组的第二个下标）
		    private final int sy = 50;//迷宫最左上角点纵坐标（对应二维数组的第一个下标）
		    private int w=10;    //小方格的边长
		    private int hor;   //迷宫的横向长度
		    private int ver;//迷宫的纵向长度
		   
		    private Graphics jg;
		    
		    private Color rectColor = new Color(0xf5f5f5);
		    
		    public printMaze(Maze m) {
		    	oneMaze=m;
		    	w=Math.min(700/oneMaze.length(),700/oneMaze.width());   //700可以理解成迷宫形状的最大大小，由Maze中的MAXS和原定的w=10决定   由于不知道怎么把MAXS通过静态方式传进来，所以现在不写
		    	hor=oneMaze.width()*w;
		    	ver=oneMaze.length()*w;
//		    	super();
		        Container p = getContentPane();
		        setBounds(50, 50, 800, 800);
		        setTitle("迷宫");
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
		       
		        jg =  this.getGraphics(); // 获取专门用于在窗口界面上绘图的对象		        
		      
		        paint(jg);    // 绘制
		    }
		    

		    public void paint(Graphics g) {
		        try {
		            g.setColor(Color.RED);// 设置线条颜色为红色
		            g.drawRect(sx, sy,hor,ver ); // 绘制外层矩形框

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
