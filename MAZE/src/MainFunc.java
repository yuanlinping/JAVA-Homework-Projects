import java.util.Scanner;

public class MainFunc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int length,width,sx,sy,ex,ey;
		Scanner input=new Scanner(System.in);
		System.out.println("�������Թ���С����*��(��󲻳���70)��");
		length=input.nextInt();
		width=input.nextInt();
		System.out.println("�������Թ�������꣺");
		sx=input.nextInt();
		sy=input.nextInt();
		while((sx<0)||(sx>=length)||(sy<0)||(sy>=width)){
			System.out.println("��Ч��㣡");
			sx=input.nextInt();
			sy=input.nextInt();
		}
		System.out.println("�������Թ��յ����꣺");
		ex=input.nextInt();
		ey=input.nextInt();
		while((ex<0)||(ex>=length)||(ey<0)||(ey>=width)){
			System.out.println("��Ч�յ㣡");
			ex=input.nextInt();
			ey=input.nextInt();
		}
		input.close();
		
		Maze m=new Maze(length,width,sx,sy,ex,ey);
		m.createMaze();
		printMaze pm=new printMaze(m);
		FindPath fm=new FindPath(m);
		/*
		 * ��ӣ� ���յ����ɫ    ·����ͼ    �޸ģ� ͳһ������ʹ�ø����߼���
		 */
	}

}
