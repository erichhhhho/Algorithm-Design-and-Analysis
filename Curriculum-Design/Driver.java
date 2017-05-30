package Algorism;

import java.util.Scanner;

/**
 * 
 * @Project��Algorithm
 * @File��Driver
 * @Author����Ψ
 * @Date��2017��5��25�� ����10:42:38
 * @Description���㷨��������ƿγ����֮��ʿ˾���ļӰ��,����̰���㷨�����Ž�
 * ̰�Ĳ��ԣ����ڲ��Ӱ�ɹ���ʱ�䳤��˾������ƥ��ʱ�䳤��ҹ����·
 */
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ��ʼ�������ղ���
		Scanner scan = new Scanner(System.in);
		// �Ӱ�ѵ�֧��ʱ����d
		int d = scan.nextInt();
		// ˾������n
		int n = scan.nextInt();

		// ͨ���Զ������Match�������칤��ʱ��daytime�Լ�ҹ����·nighttime
		Match[] daytime = new Match[n];
		Match[] nighttime = new Match[n];

		// ͨ���Զ������Match��������Ľ������result
		Match[] result = new Match[n];

		// System.out.println("����"+n+"��������·��");

		// ���ն�Ӧ��˾��������·����ʻʱ�䣬����Ӱ�ѵ�֧��ʱ����d����浽daytime����
		// ����ʽΪ(˾�����j,˾��j���Ӱ�ɹ�����ʱ��)
		for (int i = 0; i < n; i++) {
			int temp = scan.nextInt();
			daytime[i] = new Match(i + 1, d - temp);
		}

		// System.out.println("����"+n+"��ҹ����·��");

		// ���ն�Ӧ��i��ҹ����·����ʻʱ��
		// ��ʽΪ(���i,��i��ҹ����·��ʻʱ��)
		for (int j = 0; j < n; j++) {
			int temp = scan.nextInt();
			nighttime[j] = new Match(j + 1, temp);

		}
		
		scan.close();
		/*
		 * System.out.println("sortǰ"); for(Match i:daytime)
		 * System.out.print(i.getOrigin()+":"+i.getValue()+" ");
		 * System.out.println("sort��");
		 */

		//����������̰���㷨������
		
		// ��daytime���飬���ݲ��Ӱ�ɹ���ʱ����з������򣬱���Ʋ��ÿ�������
		quicksort(daytime, 0, n - 1);

		/*
		 * for(Match i:daytime)
		 * System.out.print(i.getOrigin()+":"+i.getValue()+" ");
		 */

		// ��nighttime���飬����ҹ����·��ʻʱ����з������򣬱���Ʋ��ÿ�������
		quicksort(nighttime, 0, n - 1);

		// ����(ҹ����·,˾�����)��ƥ������result
		for (int i = 0; i < n; i++) {
			result[i] = new Match(nighttime[i].getOrigin(),
					daytime[i].getOrigin());
		}

		// ����������õ�ԭ��˾�����ŵ�ҹ����·
		quicksort(result, 0, n - 1);

		for (int i = n - 1; i >= 0; i--) {
			System.out.print(result[i].getOrigin() + " ");
		}
		System.out.println();
		System.out.println(cost(daytime, nighttime));
	}

	// ���㵱ǰ���Ž��ֵcost
	private static int cost(Match[] daytime, Match[] nighttime) {
		// TODO Auto-generated method stub
		int sum = 0;
		int temp;
		for (int i = 0; i < daytime.length; i++) {
			temp = nighttime[i].getValue() - daytime[i].getValue();
			sum += (temp > 0) ? temp : 0;
		}
		return sum;
	}

	// ��Match�������value�ֶν�������
	static void quicksort(Match[] a, int left, int right) {
	
		int i, j;
		Match t = new Match();
		Match temp = new Match();

		if (left > right)
			return;
		
		// temp�д��׼Ԫ��(Ĭ�������MatchԪ��)
		temp.copy(a[left]); 
		i = left;
		j = right;

		while (i != j) {
			// ˳�����Ҫ��Ҫ�ȴ��ұ߿�ʼ��
			while (a[j].getValue() <= temp.getValue() && i < j)
				j--;
			// �����ұߵ�
			while (a[i].getValue() >= temp.getValue() && i < j)
				i++;
			// ��������Match�����������е�λ��
			if (i < j) {
				t.copy(a[i]);
				a[i].copy(a[j]);
				a[j].copy(t);
			}
		}
		// ���ս���׼����λ
		a[left].copy(a[i]);
		a[i].copy(temp);

		quicksort(a, left, i - 1);// ����������ߵģ�������һ���ݹ�Ĺ���
		quicksort(a, i + 1, right);// ���������ұߵ� ��������һ���ݹ�Ĺ���
	}

}
