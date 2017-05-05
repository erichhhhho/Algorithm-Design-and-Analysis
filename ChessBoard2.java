
package Algorism;

import java.util.Scanner;
import java.lang.Math;

public class ChessBoard2{
	private static int t=0;
	private static void chessBoard(int[][] board,int tr,int tc,int k, int dr,int dc)
	{
		
		if(Math.pow(2.0, k)==2)return;
		t++;
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
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		
		int k=scan.nextInt();
		int dr=scan.nextInt();
		int dc=scan.nextInt();
		int board[][]=new int[(int) Math.pow(2.0, k)][(int) Math.pow(2.0, k)];
		int t=0;

		
		chessBoard(board, 0, 0, k, dr, dc);
		System.out.println("Result:");
		for(int[]i:board){
			  for(int j:i){
			      System.out.print(j+" ");
			    }
			    System.out.println();
			  }
			
	}
}