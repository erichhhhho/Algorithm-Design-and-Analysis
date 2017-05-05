
/**   
 * @Project：Algorithm  
 * @File：ChessBoard  
 * @Author：何唯   
 * @Date：2017年3月27日 下午11:13:46      
 * @Description：   棋盘覆盖问题
 */   

package Algorism;

import java.util.Scanner;
import java.lang.Math;

public class ChessBoard{
	int tile=1;//全局变量
	/**  
	*   
	* @Description: TODO   
	* @author ：何唯   
	* @date ： 2017年3月27日 下午11:23:52     
	* @return void      
	* @param     @param board
	* @param     @param tr
	* @param     @param tc
	* @param     @param k
	* @param     @param dr
	* @param     @param dc 
	* @file    ChessBoard.java  
	* @throws  
	*/  
	public void chessBoard(int[][] board,int tr,int tc,int k, int dr,int dc)
	{
		
		if(Math.pow(2.0, k)==1)return;
		int t=tile++;
		//以单元格[(int) Math.pow(2.0, k-1)-1,(int) Math.pow(2.0, k-1)-1]为基准做判断
		//覆盖左上棋盘
		if(dr<=tr+(int) Math.pow(2.0, k-1)-1&&dc<=tc+(int) Math.pow(2.0, k-1)-1)//特殊方格在左上棋盘
		{
			chessBoard(board,tr,tc,k-1,dr,dc);
		}else{
			board[tr+(int) Math.pow(2.0, k-1)-1][tc+(int) Math.pow(2.0, k-1)-1]=t;
			chessBoard(board,tr,tc,k-1,tr+(int) Math.pow(2.0, k-1)-1,tc+(int) Math.pow(2.0, k-1)-1);
		}
		//覆盖右上棋盘
		if(dr<=tr+(int) Math.pow(2.0, k-1)-1&&dc>tc+(int) Math.pow(2.0, k-1)-1)//特殊方格在右上棋盘
		{
			chessBoard(board,tr,tc+(int) Math.pow(2.0, k-1),k-1,dr,dc);
		}else{
			board[tr+(int) Math.pow(2.0, k-1)-1][tc+(int) Math.pow(2.0, k-1)]=t;
			chessBoard(board,tr,tc+(int) Math.pow(2.0, k-1),k-1,tr+(int) Math.pow(2.0, k-1)-1,tc+(int) Math.pow(2.0, k-1));
		}
			
		//覆盖左下棋盘
		if(dr>tr+(int) Math.pow(2.0, k-1)-1&&dc<=tc+(int) Math.pow(2.0, k-1)-1)//特殊方格在左下棋盘
		{
			chessBoard(board,tr+(int) Math.pow(2.0, k-1),tc,k-1,dr,dc);
		}else{
			board[tr+(int) Math.pow(2.0, k-1)][tc+(int) Math.pow(2.0, k-1)-1]=t;
			chessBoard(board,tr+(int) Math.pow(2.0, k-1),tc,k-1,tr+(int) Math.pow(2.0, k-1),tc+(int) Math.pow(2.0, k-1)-1);
		}
		//覆盖右下棋盘
		if(dr>tr+(int) Math.pow(2.0, k-1)-1&&dc>tc+(int) Math.pow(2.0, k-1)-1)//特殊方格在右下棋盘
		{
			chessBoard(board,tr+(int) Math.pow(2.0, k-1),tc+(int) Math.pow(2.0, k-1),k-1,dr,dc);
		}else{
			board[tr+(int) Math.pow(2.0, k-1)][tc+(int) Math.pow(2.0, k-1)]=t;
			chessBoard(board,tr+(int) Math.pow(2.0, k-1),tc+(int) Math.pow(2.0, k-1),k-1,tr+(int) Math.pow(2.0, k-1),tc+(int) Math.pow(2.0, k-1));
			
		}
		
		
		
	}
	/**  
	*   
	* @Description: TODO   
	* @author ：何唯   
	* @date ： 2017年3月27日 下午11:24:05     
	* @return void      
	* @param     @param args 
	* @file    ChessBoard.java  
	* @throws  
	*/  
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		
		int k=scan.nextInt();
		int dr=scan.nextInt();
		int dc=scan.nextInt();
		int board[][]=new int[(int) Math.pow(2.0, k)][(int) Math.pow(2.0, k)];
		int t=0;
		while(dr>(int) Math.pow(2.0, k)-1||dr<0||dc<0||dc>(int) Math.pow(2.0, k))
		{
			System.out.println("Please Type again!");
			k=scan.nextInt();
		    dr=scan.nextInt();
			dc=scan.nextInt();
		}
		//创建棋盘对象
		ChessBoard cb=new ChessBoard();
		cb.chessBoard(board, 0, 0, k, dr, dc);
		//打印输出
		System.out.println("Result:");
		for(int[]i:board){
			  for(int j:i){
			      System.out.print(j+" ");
			    }
			    System.out.println();
			  }
			
	}
}