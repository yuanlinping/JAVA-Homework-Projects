import java.util.Scanner;

public class MainFunc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int length,width,sx,sy,ex,ey;
		Scanner input=new Scanner(System.in);
		System.out.println("请输入迷宫大小（长*宽）(最大不超过70)：");
		length=input.nextInt();
		width=input.nextInt();
		System.out.println("请输入迷宫起点坐标：");
		sx=input.nextInt();
		sy=input.nextInt();
		while((sx<0)||(sx>=length)||(sy<0)||(sy>=width)){
			System.out.println("无效起点！");
			sx=input.nextInt();
			sy=input.nextInt();
		}
		System.out.println("请输入迷宫终点坐标：");
		ex=input.nextInt();
		ey=input.nextInt();
		while((ex<0)||(ex>=length)||(ey<0)||(ey>=width)){
			System.out.println("无效终点！");
			ex=input.nextInt();
			ey=input.nextInt();
		}
		input.close();
		
		Maze m=new Maze(length,width,sx,sy,ex,ey);
		m.createMaze();
		printMaze pm=new printMaze(m);
		FindPath fm=new FindPath(m);
		/*
		 * 添加： 起终点的颜色    路径绘图    修改： 统一变量，使得更有逻辑性
		 */
	}

}
