package Algorism;

import java.util.Scanner;
/**
 * 
 * @Project��random  
 * @File��DPChange2  
 * @Author����Ψ   
 * @Date��2017��4��17�� ����10:48:23      
 * @Description��
 */
public class DPChange2 {

	
	public static int change(int C[],int [][]P,int d[], int M,int n){
		int i,j,a,b,min;
		int k=n,t;
		
		for(i=n;i>=1;i--)
		{
			if(M>d[i])
			{
				
			min=change(C,P,d,M-d[i],n);
			a=M-d[i];//������Ҫ�������Ǯ���
			b=i;//��ǰ��Ǯ����
			for(k=1;k<=n;k++)
			{
				P[M][k]=P[M-d[i]][k];
				P[M][i]=P[M-d[i]][i]+1;
			}
			for(int z=1;z<i;z++)
			{
				if(min>change(C,P,d,M-d[i-z],n)&&M-d[i-z]>0){
					min=change(C,P,d,M-d[i-z],n);
					
					
					for(k=1;k<=n;k++)
					{
						P[M][k]=P[M-d[i-z]][k];
						P[M][i]=P[M-d[i-z]][i]+1;
					}
					
				}
			}
			C[M]=min+1;
			return C[M];
			
			}else if(M==d[i])//��ǰ��Ҫ���������ڵ�ǰ��Ǯ���
			{
				for(k=1;k<=n;k++)
				{
					P[M][k]=0;
					P[M][i]=1;
				}
				C[M]=1;
				return C[M];
				
			}else//��ǰ��Ҫ�������С�ڵ�ǰ��Ǯ���
			{continue;}
		}
		
		return C[M];
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n,i=0;
		Scanner scan =new Scanner(System.in);
		n=scan.nextInt();
		int[] d=new int[100];
		System.out.println("�����Ǳ���¼��");
		System.out.println("�����������d[1] to d["+n+"]");
		
		for (i=1;i<=n;i++)	
			d[i]=scan.nextInt();
		
		int [] C=new int [100];
		int [][] P=new int [100][100];

		change(C,P,d,30,n);
		for(int M=1;M<=30;M++)
		{
			System.out.print("\n j="+M+"  "+C[M]);
			for(i=1;i<=n;i++)
			{
				System.out.print("  d["+i+"]="+P[M][i]);
			}
		}
		
	}

}
