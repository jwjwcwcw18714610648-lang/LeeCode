package com.itheima;

public class leecode79 {
    public boolean exist(char[][] board, String word) {
        int row=board.length;
        int cow=board[0].length;
        boolean[][] visid=new boolean[row][cow];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cow; j++) {
                boolean flag=check(board,word,0,i,j,visid);
                if(flag){
                    return true;
                }
            }

        }return false;
    }
    public boolean check(char[][] board,String word,int index,int currRow,int currCow,boolean[][] visid){
        if(board[currRow][currCow]!=word.charAt(index)){
            return false;
        }
        if(index==word.length()-1){
            return true;
        }
        boolean result=false;
        visid[currRow][currCow]=true;
        int[][] directiongs={{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] dir:directiongs){
            int newRow=dir[1]+currRow;
            int newCow=dir[0]+currCow;
            if(0<=newRow&&newRow<board.length&&0<=newCow&&newCow<board[0].length){
                if(!visid[newRow][newCow]){

                    boolean flag=check(board, word, index+1, newRow, newCow, visid);
                    if(flag==true){
                        result=true;
                        return result;
                    }
                }

            }

        } visid[currRow][currCow]=false;
        return result;
    }
}
