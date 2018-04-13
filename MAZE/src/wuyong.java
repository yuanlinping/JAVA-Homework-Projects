
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

//文件输入怎么写？
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
//		      PrintStream out = System.out; //保留原输出流 
//		      PrintStream ps = new PrintStream("maze");//创建文件输出流 
//		      System.setOut(ps); //设置使用新的输出流 
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
//		      System.setOut(out); //恢复原有输出流 
//		      System.out.println("程序运行完毕，请查看日志文件。"); 
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
            ArrayList<Node> arrayList=new ArrayList<Node>();// 用来保存每一个出队列的节点
            queue.offer(start);
            while (!queue.isEmpty()){
                Node local=queue.remove();
                arrayList.add(local);
                for (int i=0;i<2;i++){
                    Node nbr=new Node(local.x+dir[i][0],local.y+dir[i][1]);
                    if(nbr.x>=0&&nbr.x<m&&nbr.y>=0&&nbr.y<n&&map[nbr.x][nbr.y]==0&&visited[nbr.x][nbr.y]==0){
                        visited[nbr.x][nbr.y]=1;
                        queue.offer(nbr);
                        nbr.prex=local.x;// 保存前驱节点
                        nbr.prey=local.y;// 保存前驱节点
                    }
                }
            }
            
            Stack<Integer> stack=new Stack<Integer>();
            int  px=arrayList.get(arrayList.size()-1).prex;// 获得目的节点的前驱节点
            int  py=arrayList.get(arrayList.size()-1).prey;
            stack.push(arrayList.size()-1);// 将目的节点在arrayList中的位置记录下来，便于输出
            while (true){
                if(px==0&&py==0){// 找到起始节点就停止
                    break;
                }
                for(int i=0;i<arrayList.size();i++){// 循环找出每一个节点的前驱，找到就跳出当前循环
                    if(arrayList.get(i).x==px&&arrayList.get(i).y==py){
                        px=arrayList.get(i).prex;
                        py=arrayList.get(i).prey;
                        stack.push(i);// 保存节点在arrayList中的位置
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
    int prex;// 保存前驱节点位置
    int prey;
    Node(int x,int y){
        this.x=x;
        this.y=y;
    }
}
