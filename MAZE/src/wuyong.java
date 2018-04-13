
public class wuyong {
	
}
for(int i=0;i<print_maze.length()-1;i++){
	  for(int j=0;j<print_maze.width();j++){
		  for(int k=0;k<4;k++){
			  if(!print_maze.maze[i][j].wall[3].isVisit){
				  if(k==0||k==3) System.out.print("_"); 
				  else System.out.print("|");
				  
			  }
		  }
	  }
System.out.println();
}

//�ļ�������ôд��
//import java.io.PrintStream; 
//import java.io.FileNotFoundException; 
//
//
//public class printMaze {
//	private int length,width,startx,starty,endx,endy;
//	Maze print_maze;
	
//	public printMaze(Maze m){
//		print_maze=m;
//		try{ 
//		      PrintStream out = System.out; //����ԭ����� 
//		      PrintStream ps = new PrintStream("maze");//�����ļ������ 
//		      System.setOut(ps); //����ʹ���µ������ 
//		      for(int j=0;j<print_maze.width();j++) {
//		    	  if(j==print_maze.startx()||j==print_maze.endx()) System.out.print(" ");
//		    	  else System.out.print("_ ");
//		      }
//		      System.out.println();
//		      
//		      for(int i=0;i<print_maze.length();i++){
//		    	  System.out.print("|");
//		    	  for(int j=0;j<print_maze.width();j++){
//		    		  if(!print_maze.maze[i][j].wall[2].isVisit) System.out.print("|"); 
//		    		  if(!print_maze.maze[i][j].wall[3].isVisit) System.out.print("_");
//		    	  }
//		      System.out.println();
//		      }
//		      System.setOut(out); //�ָ�ԭ������� 
//		      System.out.println("����������ϣ���鿴��־�ļ���"); 
//		    } 
//		    catch(FileNotFoundException e){ 
//		      e.printStackTrace();} 
//  	}
	



import java.util.*;
public class BFS {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int m=sc.nextInt();
            int n=sc.nextInt();
            int[][] map = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            int[][] dir = {{1, 0}, {0, 1}};
            int[][] visited=new int[m][n];
            Node start=new Node(0,0);
            Node end=new Node(m-1,n-1);
            Queue<Node> queue=new LinkedList<Node>();
            ArrayList<Node> arrayList=new ArrayList<Node>();// ��������ÿһ�������еĽڵ�
            queue.offer(start);
            while (!queue.isEmpty()){
                Node local=queue.remove();
                arrayList.add(local);
                for (int i=0;i<2;i++){
                    Node nbr=new Node(local.x+dir[i][0],local.y+dir[i][1]);
                    if(nbr.x>=0&&nbr.x<m&&nbr.y>=0&&nbr.y<n&&map[nbr.x][nbr.y]==0&&visited[nbr.x][nbr.y]==0){
                        visited[nbr.x][nbr.y]=1;
                        queue.offer(nbr);
                        nbr.prex=local.x;// ����ǰ���ڵ�
                        nbr.prey=local.y;// ����ǰ���ڵ�
                    }
                }
            }
            
            Stack<Integer> stack=new Stack<Integer>();
            int  px=arrayList.get(arrayList.size()-1).prex;// ���Ŀ�Ľڵ��ǰ���ڵ�
            int  py=arrayList.get(arrayList.size()-1).prey;
            stack.push(arrayList.size()-1);// ��Ŀ�Ľڵ���arrayList�е�λ�ü�¼�������������
            while (true){
                if(px==0&&py==0){// �ҵ���ʼ�ڵ��ֹͣ
                    break;
                }
                for(int i=0;i<arrayList.size();i++){// ѭ���ҳ�ÿһ���ڵ��ǰ�����ҵ���������ǰѭ��
                    if(arrayList.get(i).x==px&&arrayList.get(i).y==py){
                        px=arrayList.get(i).prex;
                        py=arrayList.get(i).prey;
                        stack.push(i);// ����ڵ���arrayList�е�λ��
                        break;
                    }
                }
            }
            System.out.println("(0,0)");
            while (!stack.isEmpty()){
                System.out.println("("+arrayList.get(stack.peek()).x+","+arrayList.get(stack.peek()).y+")");
                stack.pop();
            }
        }
    }
}
class Node{
    int x;
    int y;
    int prex;// ����ǰ���ڵ�λ��
    int prey;
    Node(int x,int y){
        this.x=x;
        this.y=y;
    }
}
