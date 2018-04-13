import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FindPath {
	class Node{
		public int x,y;
		public int perx,pery,dir;    //dir���ڴ��ǰ�����ͨ�������ķ��������˽ڵ�
		public Node(int a,int b){
			x=a;y=b;
		}
	}
	
	private final int  MAXS=75;
	private Maze tarMaze;
	private Node start,end;
	private int[][] visited;
	Queue<Node> queue=new LinkedList<Node>();
    ArrayList<Node> outList=new ArrayList<Node>();// ��������ÿһ�������еĽڵ�
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
		int[] dx={-1,0,0,1};    //���򣬷ֱ��Ӧ��������
		int[] dy={0,-1,1,0};
//		Queue<Node> queue=new LinkedList<Node>();
//        ArrayList<Node> outList=new ArrayList<Node>();// ��������ÿһ�������еĽڵ�
		queue.offer(start);
		visited[start.x][start.y]=1;
		while(!queue.isEmpty()){
			Node curr=queue.poll();
			outList.add(curr);
			if((curr.x==end.x)&&(curr.y==end.y)) break; //һ���յ���Ӿ�BFS��ֹͣ
			for(int i=0;i<4;i++){
				Node nextN=new Node(curr.x+dx[i],curr.y+dy[i]);
				if((nextN.x>=0)&&(nextN.x<tarMaze.length())&&(nextN.y>=0)&&(nextN.y<tarMaze.width())){
					if((visited[nextN.x][nextN.y]==0)&&(tarMaze.maze[curr.x][curr.y].wall[i].isVisit)){   //isVisitΪtrue��ʾ��ǽ
						nextN.dir=i;
						nextN.perx=curr.x;
						nextN.pery=curr.y;
						visited[nextN.x][nextN.y]=1;
						queue.offer(nextN);
					}
				}
			}	
		}
		
//		for(int i=0;i<outList.size();i++){   //�м������
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
		
//		Stack<Node> stack=new Stack<Node>();   //����·����������ջ��Ԫ�������
//		stack.push(outList.get(outList.size()-1));   //outList�����һ��Ԫ�ض�Ӧ�ľ����յ�    ����ֱ��stack.push(end),��Ϊ�������ԣ�end����û�б��޸�= =
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
        int  px=outList.get(outList.size()-1).perx;   // ���Ŀ�Ľڵ��ǰ���ڵ�
        int  py=outList.get(outList.size()-1).pery;
        stack.push(outList.size()-1);// ��Ŀ�Ľڵ���outList�е�λ�ü�¼�������������
        while (true){
            if(px==start.x&&py==start.y){// �ҵ���ʼ�ڵ��ֹͣ
                break;
            }
            for(int i=0;i<outList.size();i++){// ѭ���ҳ�ÿһ���ڵ��ǰ�����ҵ���������ǰѭ��
                if(outList.get(i).x==px&&outList.get(i).y==py){
                    px=outList.get(i).perx;
                    py=outList.get(i).pery;
                    stack.push(i);// ����ڵ���outList�е�λ��
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



