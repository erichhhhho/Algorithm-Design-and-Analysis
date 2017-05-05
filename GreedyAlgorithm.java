package Algorism;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import org.junit.Test;
/**
 * 
 * @Project��random  
 * @File��GreedyAlgorithm  
 * @Author����Ψ   
 * @Date��2017��4��18�� ����9:12:32      
 * @Description��
 */
public class GreedyAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("������С���� �뾶 С������ \n(������С�������ܱ��״︲�ǽ��������-1)");

		int total = scan.nextInt();// С������
		double radius = scan.nextDouble();// �״�뾶
		double[][] coordinate = new double[total][2];// ������С����������Ϊһ��double��ά����

		// ����С������
		for (int i = 0; i < total; i++) {
			coordinate[i][0] = scan.nextDouble();
			coordinate[i][1] = scan.nextDouble();
			if (radius < coordinate[i][1]) {
				// ��������״�뾶С��С�������꣬�������ܸ���
				System.out.println("-1");
				return;
			}
		}

		/*
		 * for(int[]a:coordinate) for(int k:a) System.out.println(k);
		 */
		System.out.println("���ǣ�");
		System.out.println(findRadarNum(coordinate, radius, total));
	}

	// ��ƺ���findRadarNum������������Ž�
	private static int findRadarNum(double[][] coordinate, double radius,
			int total) {
		// TODO Auto-generated method stub
		int ans = 0;
		int i;
		// �������������ʾ��С���Ƿ��ѱ�����
		boolean[] flag = new boolean[total];
		// ��С��ΪԲ�ģ��״�̽��뾶Ϊ�뾶���л�Բ����Բ��x�ύ�㼴Ϊ��Ҫ�״ｨ��ķ�Χ���޽������������Ϊ-1����һ�������������ý��㿴������������
		// ���״��ܸ��ǵ�x���������ֵ��Сֵ��������
		LinkedList<Radar> radar = new LinkedList<Radar>();
		for (i = 0; i < total; i++) {
			double augment = Math.sqrt(Math.pow(radius, 2)
					- Math.pow(coordinate[i][1], 2));
			Radar p1 = new Radar(coordinate[i][0] - augment, coordinate[i][0]
					+ augment);
			radar.add(p1);

		}
		// ��������Ԫ�ذ�ÿ���״��ܸ��ǵ������䣬��С��������
		SortRadar(radar);
		// ̰�Ĳ���Ϊ�������ҽ����״̰���ǿ��Ե����״��ܸ��ǵĵ������
		// ����j�������������С�ڵ�ǰ����������Ļ�������ʹ��ͬһ���״������Ҫ����һ��ѭ������������������վ
		for (i = 0; i < total; i++) {
			if (flag[i] == false) {
				flag[i] = true;
				for (int j = i + 1; j < total; j++) {
					if (radar.get(j).getMin() < radar.get(i).getMax())
						flag[j] = true;
				}
				// �����״����
				ans++;
			}
		}
		return ans;

	}

	// �������е����䣬�����������С����x������������
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
