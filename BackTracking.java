package Algorism;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
/**
 * 
 * @Project��Algorithm  	
 * @File��BackTracking  
 * @Author����Ψ   
 * @Date��2017��5��7�� ����10:28:12      
 * @Description��
 */
public class BackTracking {

	private static int[][] Intdata=null;//��й�ϵ����
	private static int total;//��ϵ����
	private static int best;//�������Ž�(��������)
	private static int currentbest;//��ǰ���Ž�
	private static int[] bestplan;//���żƻ�(ÿ�����Ƿ�������)
	private static int[] currentplan;//��ǰ�ƻ�
	private static int size;//������
	private static int count;//ĳ�˳����Ŀ
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//���Ƚ���Input.txt�ļ�����
		String[] Stringdata=null;
		File file=new File("F:\\Desktop\\JAVA project eric\\Algorithm\\src\\Input.txt");
		try {
			FileInputStream in=new FileInputStream(file);
		
			InputStreamReader read=new InputStreamReader(in);
			BufferedReader bufferedReader=new BufferedReader(read);
			String x=null;
			x=bufferedReader.readLine();
			
			Stringdata =x.split(" ");
			size=Integer.parseInt(Stringdata[0]);
			Intdata=new int[size][size];
			total=Integer.parseInt(Stringdata[1]);
			
			//���ó�й�ϵ����
			int x1,x2;
			while((x=bufferedReader.readLine()) != null)
				{
				Stringdata =x.split(" ");
				x1=Integer.parseInt(Stringdata[0]);
				x2=Integer.parseInt(Stringdata[1]);
				Intdata[x1-1][x2-1]=1;
				Intdata[x2-1][x1-1]=1;
				}
			
			System.out.println("sortǰ:");
			for(int[]i:Intdata){
				  for(int j:i){
				      System.out.print(j);
				    }
				    System.out.println();
				  }
			Sort();//���ȹ������������Ľ�ռ�ṹ
			System.out.println("Sort��");
			for(int[]i:Intdata){
				  for(int j:i){
				      System.out.print(j);
				    }
				    System.out.println();
				  }
			
			currentbest=0;//��ʼ����ǰ����
			best=0;//��ʼ����������
			currentplan=new int[size];//��ʼ����ǰ�ƻ�
			bestplan=new int[size]; //��ʼ�����żƻ�
			
			//��ʼ�ݹ�������
			BackTrack(0);//���ڵ������Ϊ0����ʼ�������������
			read.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//������
		FileWriter output;
		try {
			output = new FileWriter("F:\\Desktop\\JAVA project eric\\Algorithm\\src\\Output.txt");
			BufferedWriter bf = new BufferedWriter(output);
			bf.write(best+" \n");
			bf.newLine();
			
			for(int t:bestplan)
				bf.write(t+" ");
			bf.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch blockf
			e.printStackTrace();
		}
		
			System.out.println("���������");
		System.out.println(best);
		System.out.println("��ѷ�����");
		for(int t:bestplan)
			System.out.print(t+" ");
	}
	
//�Ծ��񰴳����Ŀ�Ӷൽ������
	private static void Sort() {
		// TODO Auto-generated method stub
		/*for(int j:Separate)
			System.out.println(j);*/
		for(int i = 0;i<size;i++)
		{
			for(int j=i;j<size;j++)
				if(Separate(i)<Separate(j))
					swap(i,j);//�������������Ӧ��ϵ
		}
	}
	
	//�����ض�λ�����и���
	private static int Separate(int index) {
		// TODO Auto-generated method stub
		
			int c=0;
			for(int temp:Intdata[index])
				{
				if(temp==1)
					c++;
				}
		return c;
	}

	//������λ����λ��
	private static void swap(int i, int j) {
		// TODO Auto-generated method stub
		int temp1,temp2;
		for(int k=0;k<size;k++)
			{
			if(k!=i&&k!=j)
			{
				temp1=Intdata[i][k];
				temp2=Intdata[k][i];
				Intdata[i][k]=Intdata[j][k];
				Intdata[k][i]=Intdata[k][j];
				Intdata[j][k]=temp1;
				Intdata[k][j]=temp2;
			}
			}
	}

	//���ݷ����
	private static void BackTrack(int currentdeep) {
		// TODO Auto-generated method stub
		count++;
		System.out.println("��ǰ��ȣ�");
		System.out.println(currentdeep);
		
		boolean flag=false;
		
		//����Ҷ�ӽڵ�Ļ�������н�
		if(currentdeep>size-1)
		{
			//System.out.println("������н�");
			best=currentbest;
			for(int k=0;k<currentplan.length;k++)
			bestplan[k]=currentplan[k];
			return;
		}
		//�޽纯������ȥ�ò������Ž�(С�ڵ��ڵ�ǰ���Ž��)����
		if(currentbest+size-currentdeep<=best)
			{
			//System.out.println("��֦����");
			return;
			}
		//����flag�����ж��Ƿ����������
		for(int i=0;i<size;i++)
		{	
			//�Ǹ��ڵ�Ļ��Ƿ��������ڵ㲻Ϊ��
			if(currentdeep==0&&Intdata[currentdeep][i]!=1)
				{
				flag=true;
				break;
				}
			else if(Intdata[currentdeep][i]!=1)//�ڵ��Ƿ��������˲�Ϊ��
				flag=true;
			else if(currentplan[i]==1&&Intdata[currentdeep][i]==1)
				{
				//����ѡ�ڵ��Ƿ�Ϊ��
				flag=false;
				break;
				}
		}
		if(flag==true)//����������
			{
			System.out.println("��");
				currentplan[currentdeep]=1;
				currentbest++;
				BackTrack(currentdeep+1);//�ݹ���ȱ�������
				currentbest--;
				currentplan[currentdeep]=0;
			}
		//����������
		if(currentbest+size-currentdeep>best)
				{
				currentplan[currentdeep]=0;
				BackTrack(currentdeep+1);
				}
		
		System.out.println("�ݹ������");
		System.out.println(count);
		
		}
	
}
