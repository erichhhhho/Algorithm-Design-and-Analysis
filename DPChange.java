package Algorism;
import java.util.Arrays;
import java.util.Scanner;
/**
 * 
 * @Project：random  
 * @File：DPChange  
 * @Author：何唯   
 * @Date：2017年4月17日 上午10:49:07      
 * @Description：
 */
public class DPChange {
	public static void change(int d[], int M,int n){
		int i,k,a,b,min,flag;
		int[] C=new int [100];
		int[][] P=new int [100][100];
		
		for(int j=1;j<=M;j++)//遍历子问题
		{
			if(n>1)//假如大于一种货币
			{
				k=n;//货币种数给k
				flag=1;//未解决
				while(k>1&&flag==1)
				{
					flag=1;
					if(j==d[k])//当前需要找零面额等于当前零钱面额
					{
						C[j]=1;//一张当前零钱就可以了
						
						//j是最大需要找零面额，输出解决信息
						if(j==M)
							System.out.print("\n j="+j+"  "+C[j]);
							flag=0;
							
							for(i=1;i<=n;i++)
							{
								P[i][j]=0;
								P[k][j]=1;
								if(j==M)
									System.out.print("  d["+i+"]="+P[i][j]);
							}
							
					}else if(j<d[k])//当前需要找零面额小于当前零钱面额
							k--;//下一个零钱
					else{
						//当前需要找零面额大于当前零钱面额
							min=C[j-d[k]];//初始化min为j-当前零钱面额
							a=j-d[k];//子问题要解决的零钱面额
							b=k;//当前零钱种类
							for(i=1;i<k;i++)
								if(min>C[j-d[k-i]])//找到子问题最优解
								{
									min=C[j-d[k-i]];
									a=j-d[k-i];
									b=k-i;
								}
								C[j]=min+1;//最优解为子问题最优解加上1枚硬币
							
								//j是最大需要找零面额，输出解决信息
								if(j==M)
									System.out.print("\n j="+j+"  "+C[j]);
									flag=0;
							for(i=1;i<=n;i++)//存储对于j的最佳方案
							{
								if(C[j]>=1000)P[i][j]=0;
								else{
									P[i][j]=P[i][a];//子问题a的对应方案
									P[b][j]=P[b][a]+1;//子问题a的对应方案+1
								}
								if(j==M)
									System.out.print("  d["+i+"]="+P[i][j]);
							}
							
						}
					}if(j%d[1]!=0&&flag==1)//
					{
						C[j]=Integer.MAX_VALUE;
						if(j==M)System.out.print("\n j="+j+"  "+C[j]);
						for(i=1;i<=n;i++)
						{
							P[i][j]=0;
							if(j==M)System.out.print("  d["+i+"]="+P[i][j]);
						}
					}else if(flag==1){
						C[j]=j/d[1];
						if(j==M)System.out.print("\n j="+j+"  "+C[j]);
						P[1][j]=j/d[1];
								if(j==M)System.out.print("\t d[1]="+P[1][j]);
								for(i=2;i<=n;i++)
								{
									P[i][j]=0;
									if(j==M)System.out.print("\t d["+i+"]="+P[i][j]);
								}
					}
				}
			else//一种货币
				{
					if(j%d[1]!=0)C[j]=Integer.MAX_VALUE;
					else C[j]=j/d[1];
					if(j==M)System.out.print("\n j="+j+"  "+C[j]);
					P[1][j]=j/d[1];
					if(j==M)System.out.print("\t d[1]="+P[1][j]);
				}
			}
		
	}
	public static void main(String[] args) {
		int n,i=0;
		Scanner scan =new Scanner(System.in);
		n=scan.nextInt();
		int[] d=new int[100];
		
		System.out.println("请输入面额金额d[1] to d["+n+"]");
		
		for (i=1;i<=n;i++)	
			d[i]=scan.nextInt();
		
		for(i=1;i<=30;i++)
		change(d,i,n);
	}
}
