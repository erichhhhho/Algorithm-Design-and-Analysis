package Algorism;

import java.util.Scanner;

/**
 * 
 * @Project：Algorithm
 * @File：Driver
 * @Author：何唯
 * @Date：2017年5月25日 上午10:42:38
 * @Description：算法分析与设计课程设计之巴士司机的加班费,利用贪心算法求最优解
 * 贪心策略：对于不加班可工作时间长的司机优先匹配时间长的夜间线路
 */
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 初始化并接收参数
		Scanner scan = new Scanner(System.in);
		// 加班费的支出时间限d
		int d = scan.nextInt();
		// 司机总数n
		int n = scan.nextInt();

		// 通过自定义对象Match声明白天工作时间daytime以及夜间线路nighttime
		Match[] daytime = new Match[n];
		Match[] nighttime = new Match[n];

		// 通过自定义对象Match声明输出的结果排序result
		Match[] result = new Match[n];

		// System.out.println("输入"+n+"个白天线路：");

		// 接收对应个司机白天线路的行驶时间，并与加班费的支出时间限d作差保存到daytime数组
		// 即形式为(司机序号j,司机j不加班可工作的时间)
		for (int i = 0; i < n; i++) {
			int temp = scan.nextInt();
			daytime[i] = new Match(i + 1, d - temp);
		}

		// System.out.println("输入"+n+"个夜间线路：");

		// 接收对应第i个夜班线路的行驶时间
		// 形式为(序号i,第i个夜班线路行驶时间)
		for (int j = 0; j < n; j++) {
			int temp = scan.nextInt();
			nighttime[j] = new Match(j + 1, temp);

		}
		
		scan.close();
		/*
		 * System.out.println("sort前"); for(Match i:daytime)
		 * System.out.print(i.getOrigin()+":"+i.getValue()+" ");
		 * System.out.println("sort后");
		 */

		//下面是利用贪心算法求解过程
		
		// 对daytime数组，根据不加班可工作时间进行非增排序，本设计采用快速排序
		quicksort(daytime, 0, n - 1);

		/*
		 * for(Match i:daytime)
		 * System.out.print(i.getOrigin()+":"+i.getValue()+" ");
		 */

		// 对nighttime数组，根据夜班线路行驶时间进行非增排序，本设计采用快速排序
		quicksort(nighttime, 0, n - 1);

		// 生成(夜班线路,司机序号)的匹配序列result
		for (int i = 0; i < n; i++) {
			result[i] = new Match(nighttime[i].getOrigin(),
					daytime[i].getOrigin());
		}

		// 对序列排序得到原来司机安排的夜间线路
		quicksort(result, 0, n - 1);

		for (int i = n - 1; i >= 0; i--) {
			System.out.print(result[i].getOrigin() + " ");
		}
		System.out.println();
		System.out.println(cost(daytime, nighttime));
	}

	// 计算当前最优解的值cost
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

	// 对Match数组根据value字段进行排序
	static void quicksort(Match[] a, int left, int right) {
	
		int i, j;
		Match t = new Match();
		Match temp = new Match();

		if (left > right)
			return;
		
		// temp中存基准元素(默认最左端Match元素)
		temp.copy(a[left]); 
		i = left;
		j = right;

		while (i != j) {
			// 顺序很重要，要先从右边开始找
			while (a[j].getValue() <= temp.getValue() && i < j)
				j--;
			// 再找右边的
			while (a[i].getValue() >= temp.getValue() && i < j)
				i++;
			// 交换两个Match对象在数组中的位置
			if (i < j) {
				t.copy(a[i]);
				a[i].copy(a[j]);
				a[j].copy(t);
			}
		}
		// 最终将基准数归位
		a[left].copy(a[i]);
		a[i].copy(temp);

		quicksort(a, left, i - 1);// 继续处理左边的，这里是一个递归的过程
		quicksort(a, i + 1, right);// 继续处理右边的 ，这里是一个递归的过程
	}

}
