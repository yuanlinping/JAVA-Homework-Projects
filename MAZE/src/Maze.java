import java.util.Random;

public class Maze {
	class edge{    //内部类，v1和v2之间的“墙”
		public int v1,v2;
		public boolean isVisit;  //标记变量，该墙是否已被处理  true表示无墙，false表示有墙
		public edge(int a,int b){
			v1=a;v2=b;
			isVisit=false;
		}
	}
	
	class gridNode{  //内部类，迷宫中的单元格
		public int x,y;  //单元格的坐标
		public edge EU,EL,ER,ED;   //该单元格与周边单元格之间的“墙”
		public edge[] wall;
		private gridNode par;
		public gridNode(int x1,int y1){
			x=x1;y=y1;
			EU=new edge(x-1,y);
			EL=new edge(x,y-1);
			ER=new edge(x,y+1);
			ED=new edge(x+1,y);
			wall=new edge[4];
			wall[0]=EU;
			wall[1]=EL;
			wall[2]=ER;
			wall[3]=ED;
		}
		public gridNode parent(){return par;}
		public gridNode setParent(gridNode newpar){return par=newpar;}
	}
	
	
	//成员变量
	private final int MAXS=75;
	private int length,width,startx,starty,endx,endy;//tatalEdge记录迷宫除外圈围墙外的墙
	public gridNode[][] maze;     //这里迫不得已将maze设为了public，因为在printMaze要访问maze，如果设为private，我不知道要怎么通过函数传给他。  你可以试着在写一个maze类来封装
	private gridNode start,end;
	
	//构造函数
	public Maze(int len,int wid,int s1,int s2,int e1,int e2){
		length=len; width=wid; startx=s1;starty=s2;endx=e1;endy=e2;
		//totalEdge=2*(length*width)-(length+width);
		maze=new gridNode[MAXS][MAXS];
		for(int i=0;i<length;i++){   //初始化迷宫
			for(int j=0;j<width;j++){
				maze[i][j]=new gridNode(i,j);  //自己是自己的父结点，只含一个元素的等价类   注意这种给自己的赋值
				maze[i][j].setParent(maze[i][j]);
			}
		}
		start=maze[startx][starty];
		end=maze[endx][endy];
		
//		for(int i=0;i<length;i++){   //初始化迷宫
//			for(int j=0;j<width;j++){
//				System.out.println(maze[i][j].parent());
//			}
//		}
	}

	//普通成员函数
	public int length(){return length;}
	public int width(){return width;}
	public int startx(){return startx;}
	public int starty(){return starty;}
	public int endx(){return endx;}
	public int endy(){return endy;}
//	public 
	
	//并查集
//	public gridNode Find(gridNode curr){
//		if(curr!=null){     //判断否则会抛出NullPointerException, 抛出错误说明是真的错误出现了
//			if(curr.parent()==curr) return curr;
//			return curr.setParent(Find(curr.parent()));
//		}
//		return null;
//	}
//	
	public gridNode Find(gridNode curr){
			if(curr.parent()==curr) return curr;
			return curr.setParent(Find(curr.parent()));
		}
	
	public boolean Union(gridNode a,gridNode b){
		gridNode root1=Find(a);
		gridNode root2=Find(b);
		if(root1!=root2) {
			root2.setParent(root1);
			return true;
		}
		else return false;
	}
	
	/*	并查集随机生成迷宫
	随机生成一个格点，一个方向，得到另一个格点
	判断两个顶点是否都在迷宫范围内
	find/union
	打破两堵墙
	 */
	public void createMaze(){   
		int[] moveX={-1,0,0,1};    //方向，分别对应上左右下
		int[] moveY={0,-1,1,0};
//		while((Find(start)!=Find(end))&&(totalEdge!=0)){
		while(Find(start)!=Find(end)){
			int X=new Random().nextInt(length);  //产生0-length的整数（不包括length）
			int Y=new Random().nextInt(width);
//			System.out.println(X);
//			System.out.println(Y);
			int randD=new Random().nextInt(4);
//			System.out.println(randD);
			int X2=X+moveX[randD];
			int Y2=Y+moveY[randD];
		
			boolean islegal1=(X>=0)&&(X<length)&&(Y>=0)&&(Y<width);
			boolean islegal2=(X2>=0)&&(X2<length)&&(Y2>=0)&&(Y2<width);
			
//			System.out.println(maze[X][Y].x);
//			System.out.print(maze[X][Y].x);
//			System.out.println(maze[X][Y].EU);
//			System.out.println(maze[X][Y].wall[randD].isVisit);
		
//			System.out.println(System.out.println(maze[X][Y].wall[randD]));
			if(!(islegal1&&islegal2)) continue;   //单元格不符合要求
			else if(maze[X][Y].wall[randD].isVisit) continue;
			else{
				if(Union(maze[X][Y],maze[X2][Y2])){
					maze[X][Y].wall[randD].isVisit=true;
					maze[X2][Y2].wall[3-randD].isVisit=true;
				}
			}	
//			System.out.println("zhihou");
//			System.out.println(maze[X][Y].wall[randD].isVisit);
//			System.out.println(maze[X2][Y2].wall[3-randD].isVisit);
//			System.out.println();
		}
	}
}
