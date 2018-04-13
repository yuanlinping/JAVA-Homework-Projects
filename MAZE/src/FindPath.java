import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FindPath {
	class Node{
		public int x,y;
		public int perx,pery,dir;    //dir用于存放前驱结点通过怎样的方向来到此节点
		public Node(int a,int b){
			x=a;y=b;
		}
	}
	
	private final int  MAXS=75;
	private Maze tarMaze;
	private Node start,end;
	private int[][] visited;
	Queue<Node> queue=new LinkedList<Node>();
    ArrayList<Node> outList=new ArrayList<Node>();// 用来保存每一个出队列的节点
    Stack<Integer> stack=new Stack<Integer>();
	
	public FindPath(Maze m){
		tarMaze=m;
		start=new Node(tarMaze.startx(),tarMaze.starty());
		end=new Node(tarMaze.endx(),tarMaze.endy());
		
		//BFS
		visited=new int[MAXS][MAXS];
		for(int i=0;i<tarMaze.length();i++){
			for(int j=0;j<tarMaze.width();j++) visited[i][j]=0;
		}
		int[] dx={-1,0,0,1};    //方向，分别对应上左右下
		int[] dy={0,-1,1,0};
//		Queue<Node> queue=new LinkedList<Node>();
//        ArrayList<Node> outList=new ArrayList<Node>();// 用来保存每一个出队列的节点
		queue.offer(start);
		visited[start.x][start.y]=1;
		while(!queue.isEmpty()){
			Node curr=queue.poll();
			outList.add(curr);
			if((curr.x==end.x)&&(curr.y==end.y)) break; //一旦终点出队就BFS就停止
			for(int i=0;i<4;i++){
				Node nextN=new Node(curr.x+dx[i],curr.y+dy[i]);
				if((nextN.x>=0)&&(nextN.x<tarMaze.length())&&(nextN.y>=0)&&(nextN.y<tarMaze.width())){
					if((visited[nextN.x][nextN.y]==0)&&(tarMaze.maze[curr.x][curr.y].wall[i].isVisit)){   //isVisit为true表示无墙
						nextN.dir=i;
						nextN.perx=curr.x;
						nextN.pery=curr.y;
						visited[nextN.x][nextN.y]=1;
						queue.offer(nextN);
					}
				}
			}	
		}
		
//		for(int i=0;i<outList.size();i++){   //中间结果输出
//			Node temp3=outList.get(i);
//			System.out.println(i);
//			System.out.print(temp3.x+" ");
//			System.out.print(temp3.y+" ");
//			System.out.print(temp3.perx+" ");
//			System.out.print(temp3.pery+" ");
//			System.out.print(temp3.dir+" ");
//			System.out.println();
//			
//		}
		
//		System.out.print(end.dir+" ");
		
//		Stack<Node> stack=new Stack<Node>();   //保存路径，结束后栈顶元素是起点
//		stack.push(outList.get(outList.size()-1));   //outList的最后一个元素对应的就是终点    不能直接stack.push(end),因为经过测试，end好像没有被修改= =
//		int px=stack.firstElement().perx;
//		int py=stack.firstElement().pery;
//		System.out.print(stack.firstElement().dir+" ");
//		while(!((px==start.x)&&(py==start.y))){
//			for(int i=0;i<outList.size();i++){
//				Node temp=outList.get(i);
//				if((temp.x==px)&&(temp.y==py)){
//					stack.push(temp);
//					break;
//				}
//			}
//			px=stack.firstElement().perx;
//			py=stack.firstElement().pery;
//		}
//		stack.push(start);
		
//        Stack<Integer> stack=new Stack<Integer>();
        int  px=outList.get(outList.size()-1).perx;   // 获得目的节点的前驱节点
        int  py=outList.get(outList.size()-1).pery;
        stack.push(outList.size()-1);// 将目的节点在outList中的位置记录下来，便于输出
        while (true){
            if(px==start.x&&py==start.y){// 找到起始节点就停止
                break;
            }
            for(int i=0;i<outList.size();i++){// 循环找出每一个节点的前驱，找到就跳出当前循环
                if(outList.get(i).x==px&&outList.get(i).y==py){
                    px=outList.get(i).perx;
                    py=outList.get(i).pery;
                    stack.push(i);// 保存节点在outList中的位置
                    break;
                }
            }
        }
        
		while(!stack.isEmpty()){
			int index=stack.peek();
			switch(outList.get(index).dir)
			{case 0:System.out.print('N'); break;
			case 1: System.out.print('W');break;
			case 2: System.out.print('E');break;
			case 3: System.out.print('S');break;
			default:break;
		    }
			stack.pop();
		}
        
//        System.out.println("(0,0)");
//        while (!stack.isEmpty()){
//            System.out.println("("+outList.get(stack.peek()).x+","+outList.get(stack.peek()).y+")");
//            stack.pop();
//        }
   }	
}



