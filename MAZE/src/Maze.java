import java.util.Random;

public class Maze {
	class edge{    //�ڲ��࣬v1��v2֮��ġ�ǽ��
		public int v1,v2;
		public boolean isVisit;  //��Ǳ�������ǽ�Ƿ��ѱ�����  true��ʾ��ǽ��false��ʾ��ǽ
		public edge(int a,int b){
			v1=a;v2=b;
			isVisit=false;
		}
	}
	
	class gridNode{  //�ڲ��࣬�Թ��еĵ�Ԫ��
		public int x,y;  //��Ԫ�������
		public edge EU,EL,ER,ED;   //�õ�Ԫ�����ܱߵ�Ԫ��֮��ġ�ǽ��
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
	
	
	//��Ա����
	private final int MAXS=75;
	private int length,width,startx,starty,endx,endy;//tatalEdge��¼�Թ�����ȦΧǽ���ǽ
	public gridNode[][] maze;     //�����Ȳ����ѽ�maze��Ϊ��public����Ϊ��printMazeҪ����maze�������Ϊprivate���Ҳ�֪��Ҫ��ôͨ��������������  �����������дһ��maze������װ
	private gridNode start,end;
	
	//���캯��
	public Maze(int len,int wid,int s1,int s2,int e1,int e2){
		length=len; width=wid; startx=s1;starty=s2;endx=e1;endy=e2;
		//totalEdge=2*(length*width)-(length+width);
		maze=new gridNode[MAXS][MAXS];
		for(int i=0;i<length;i++){   //��ʼ���Թ�
			for(int j=0;j<width;j++){
				maze[i][j]=new gridNode(i,j);  //�Լ����Լ��ĸ���㣬ֻ��һ��Ԫ�صĵȼ���   ע�����ָ��Լ��ĸ�ֵ
				maze[i][j].setParent(maze[i][j]);
			}
		}
		start=maze[startx][starty];
		end=maze[endx][endy];
		
//		for(int i=0;i<length;i++){   //��ʼ���Թ�
//			for(int j=0;j<width;j++){
//				System.out.println(maze[i][j].parent());
//			}
//		}
	}

	//��ͨ��Ա����
	public int length(){return length;}
	public int width(){return width;}
	public int startx(){return startx;}
	public int starty(){return starty;}
	public int endx(){return endx;}
	public int endy(){return endy;}
//	public 
	
	//���鼯
//	public gridNode Find(gridNode curr){
//		if(curr!=null){     //�жϷ�����׳�NullPointerException, �׳�����˵������Ĵ��������
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
	
	/*	���鼯��������Թ�
	�������һ����㣬һ�����򣬵õ���һ�����
	�ж����������Ƿ����Թ���Χ��
	find/union
	��������ǽ
	 */
	public void createMaze(){   
		int[] moveX={-1,0,0,1};    //���򣬷ֱ��Ӧ��������
		int[] moveY={0,-1,1,0};
//		while((Find(start)!=Find(end))&&(totalEdge!=0)){
		while(Find(start)!=Find(end)){
			int X=new Random().nextInt(length);  //����0-length��������������length��
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
			if(!(islegal1&&islegal2)) continue;   //��Ԫ�񲻷���Ҫ��
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
