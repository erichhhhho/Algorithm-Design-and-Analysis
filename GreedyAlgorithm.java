package Algorism;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import org.junit.Test;
/**
 * 
 * @Project：random  
 * @File：GreedyAlgorithm  
 * @Author：何唯   
 * @Date：2017年4月18日 下午9:12:32      
 * @Description：
 */
public class GreedyAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入小岛数 半径 小岛坐标 \n(若输入小岛不可能被雷达覆盖将马上输出-1)");

		int total = scan.nextInt();// 小岛个数
		double radius = scan.nextDouble();// 雷达半径
		double[][] coordinate = new double[total][2];// 将各个小岛坐标设置为一个double二维数组

		// 接收小岛坐标
		for (int i = 0; i < total; i++) {
			coordinate[i][0] = scan.nextDouble();
			coordinate[i][1] = scan.nextDouble();
			if (radius < coordinate[i][1]) {
				// 若输入的雷达半径小于小岛纵坐标，即不可能覆盖
				System.out.println("-1");
				return;
			}
		}

		/*
		 * for(int[]a:coordinate) for(int k:a) System.out.println(k);
		 */
		System.out.println("答案是：");
		System.out.println(findRadarNum(coordinate, radius, total));
	}

	// 设计函数findRadarNum来求出问题最优解
	private static int findRadarNum(double[][] coordinate, double radius,
			int total) {
		// TODO Auto-generated method stub
		int ans = 0;
		int i;
		// 声明布尔数组表示该小岛是否已被覆盖
		boolean[] flag = new boolean[total];
		// 以小岛为圆心，雷达探测半径为半径进行画圆，该圆与x轴交点即为需要雷达建造的范围（无交点的情况已输出为-1，有一个交点的情况将该交点看成两个来处理）
		// 将雷达能覆盖的x轴区域最大值最小值放入链表
		LinkedList<Radar> radar = new LinkedList<Radar>();
		for (i = 0; i < total; i++) {
			double augment = Math.sqrt(Math.pow(radius, 2)
					- Math.pow(coordinate[i][1], 2));
			Radar p1 = new Radar(coordinate[i][0] - augment, coordinate[i][0]
					+ augment);
			radar.add(p1);

		}
		// 将此链表元素按每个雷达能覆盖的右区间，从小到大排序
		SortRadar(radar);
		// 贪心策略为：从左到右建立雷达，贪的是可以单个雷达能覆盖的岛屿个数
		// 若第j个区域的左区间小于当前区间右区间的话，即可使用同一个雷达，否则需要等下一次循环，以其右区间来建站
		for (i = 0; i < total; i++) {
			if (flag[i] == false) {
				flag[i] = true;
				for (int j = i + 1; j < total; j++) {
					if (radar.get(j).getMin() < radar.get(i).getMax())
						flag[j] = true;
				}
				// 计算雷达个数
				ans++;
			}
		}
		return ans;

	}

	// 将链表中的区间，按照右区间大小，按x轴正方向排序
	private static void SortRadar(LinkedList<Radar> radar) {
		// TODO Auto-generated method stub
		for (int i = 0; i < radar.size(); i++) {
			for (int j = i + 1; j < radar.size(); j++) {
				if (radar.get(j - 1).getMax() > radar.get(j).getMax()) {
					Radar radar1 = radar.get(j - 1);
					Radar radar2 = radar.get(j);
					radar.set(j - 1, radar2);
					radar.set(j, radar1);
				}

			}
		}
	}

}
