package Algorism;
import java.util.Arrays;
import java.util.Scanner;
/**
 * 
 * @Project��random  
 * @File��DPChange  
 * @Author����Ψ   
 * @Date��2017��4��17�� ����10:49:07      
 * @Description��
 */
public class DPChange {
	public static void change(int d[], int M,int n){
		int i,k,a,b,min,flag;
		int[] C=new int [100];
		int[][] P=new int [100][100];
		
		for(int j=1;j<=M;j++)//����������
		{
			if(n>1)//�������һ�ֻ���
			{
				k=n;//����������k
				flag=1;//δ���
				while(k>1&&flag==1)
				{
					flag=1;
					if(j==d[k])//��ǰ��Ҫ���������ڵ�ǰ��Ǯ���
					{
						C[j]=1;//һ�ŵ�ǰ��Ǯ�Ϳ�����
						
						//j�������Ҫ��������������Ϣ
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
							
					}else if(j<d[k])//��ǰ��Ҫ�������С�ڵ�ǰ��Ǯ���
							k--;//��һ����Ǯ
					else{
						//��ǰ��Ҫ���������ڵ�ǰ��Ǯ���
							min=C[j-d[k]];//��ʼ��minΪj-��ǰ��Ǯ���
							a=j-d[k];//������Ҫ�������Ǯ���
							b=k;//��ǰ��Ǯ����
							for(i=1;i<k;i++)
								if(min>C[j-d[k-i]])//�ҵ����������Ž�
								{
									min=C[j-d[k-i]];
									a=j-d[k-i];
									b=k-i;
								}
								C[j]=min+1;//���Ž�Ϊ���������Ž����1öӲ��
							
								//j�������Ҫ��������������Ϣ
								if(j==M)
									System.out.print("\n j="+j+"  "+C[j]);
									flag=0;
							for(i=1;i<=n;i++)//�洢����j����ѷ���
							{
								if(C[j]>=1000)P[i][j]=0;
								else{
									P[i][j]=P[i][a];//������a�Ķ�Ӧ����
									P[b][j]=P[b][a]+1;//������a�Ķ�Ӧ����+1
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
			else//һ�ֻ���
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
		
		System.out.println("�����������d[1] to d["+n+"]");
		
		for (i=1;i<=n;i++)	
			d[i]=scan.nextInt();
		
		for(i=1;i<=30;i++)
		change(d,i,n);
	}
}
