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
 * @Project：Algorithm  	
 * @File：BackTracking  
 * @Author：何唯   
 * @Date：2017年5月7日 下午10:28:12      
 * @Description：
 */
public class BackTracking {

	private static int[][] Intdata=null;//仇敌关系矩阵
	private static int total;//关系总数
	private static int best;//问题最优解(卫队人数)
	private static int currentbest;//当前最优解
	private static int[] bestplan;//最优计划(每个人是否在卫队)
	private static int[] currentplan;//当前计划
	private static int size;//总人数
	private static int count;//某人仇敌数目
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//首先接收Input.txt文件数据
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
			
			//设置仇敌关系矩阵
			int x1,x2;
			while((x=bufferedReader.readLine()) != null)
				{
				Stringdata =x.split(" ");
				x1=Integer.parseInt(Stringdata[0]);
				x2=Integer.parseInt(Stringdata[1]);
				Intdata[x1-1][x2-1]=1;
				Intdata[x2-1][x1-1]=1;
				}
			
			System.out.println("sort前:");
			for(int[]i:Intdata){
				  for(int j:i){
				      System.out.print(j);
				    }
				    System.out.println();
				  }
			Sort();//首先构造易于搜索的解空间结构
			System.out.println("Sort后：");
			for(int[]i:Intdata){
				  for(int j:i){
				      System.out.print(j);
				    }
				    System.out.println();
				  }
			
			currentbest=0;//初始化当前最优
			best=0;//初始化总体最优
			currentplan=new int[size];//初始化当前计划
			bestplan=new int[size]; //初始化最优计划
			
			//开始递归回溯求解
			BackTrack(0);//根节点算深度为0，开始深度优先搜索树
			read.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//输出结果
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
		
			System.out.println("最佳人数：");
		System.out.println(best);
		System.out.println("最佳方案：");
		for(int t:bestplan)
			System.out.print(t+" ");
	}
	
//对居民按仇敌数目从多到少排序
	private static void Sort() {
		// TODO Auto-generated method stub
		/*for(int j:Separate)
			System.out.println(j);*/
		for(int i = 0;i<size;i++)
		{
			for(int j=i;j<size;j++)
				if(Separate(i)<Separate(j))
					swap(i,j);//交换两个居民对应关系
		}
	}
	
	//计算特定位居民仇敌个数
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

	//交换两位居民位置
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

	//回溯法求解
	private static void BackTrack(int currentdeep) {
		// TODO Auto-generated method stub
		count++;
		System.out.println("当前深度：");
		System.out.println(currentdeep);
		
		boolean flag=false;
		
		//到达叶子节点的话输出可行解
		if(currentdeep>size-1)
		{
			//System.out.println("输出可行解");
			best=currentbest;
			for(int k=0;k<currentplan.length;k++)
			bestplan[k]=currentplan[k];
			return;
		}
		//限界函数，减去得不到最优解(小于等于当前最优解的)子树
		if(currentbest+size-currentdeep<=best)
			{
			//System.out.println("剪枝函数");
			return;
			}
		//设置flag进行判断是否进入左子树
		for(int i=0;i<size;i++)
		{	
			//是根节点的话是否与其他节点不为敌
			if(currentdeep==0&&Intdata[currentdeep][i]!=1)
				{
				flag=true;
				break;
				}
			else if(Intdata[currentdeep][i]!=1)//节点是否与所有人不为敌
				flag=true;
			else if(currentplan[i]==1&&Intdata[currentdeep][i]==1)
				{
				//与已选节点是否为敌
				flag=false;
				break;
				}
		}
		if(flag==true)//进入左子树
			{
			System.out.println("左");
				currentplan[currentdeep]=1;
				currentbest++;
				BackTrack(currentdeep+1);//递归深度遍历子树
				currentbest--;
				currentplan[currentdeep]=0;
			}
		//进入右子树
		if(currentbest+size-currentdeep>best)
				{
				currentplan[currentdeep]=0;
				BackTrack(currentdeep+1);
				}
		
		System.out.println("递归次数：");
		System.out.println(count);
		
		}
	
}
