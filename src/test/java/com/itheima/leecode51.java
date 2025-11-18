package com.itheima;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class leecode51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans= new ArrayList<>();
        List<List<String>> curr=new ArrayList<>();
        int[] queen=new int[n];//记录第i行皇后 放在第几列
        for (int i = 0; i < n; i++) {
            queen[i]=-1;//代表未放置
        }
        Set<Integer> Col=new HashSet();
        Set<Integer>  duijiaoxian1=new HashSet();
        Set<Integer>  duijiaoxian2=new HashSet();
        back(n,ans,Col,duijiaoxian1,duijiaoxian2,queen,0);
        return ans;
     }
     public void back (int n,List<List<String>> ans,Set<Integer>  Col,Set<Integer>  duijiaoxian1,Set<Integer>  duijiaoxian2,int[] queen,int row){
        if(row==n){
            List<String> board=generateBorad(queen,n);
            ans.add(board);
            return;

        }
         for (int col = 0; col < n; col++) {
           int d1=row+col;
           int d2=row-col;
           if(Col.contains(col)||duijiaoxian1.contains(d1)||duijiaoxian2.contains(d2)){
               continue;
           }
           Col.add(col);
           duijiaoxian1.add(d1);
           duijiaoxian2.add(d2);
           queen[row]=col;
           back(n, ans, Col, duijiaoxian1, duijiaoxian2, queen, row+1);
           Col.remove(col);
             duijiaoxian1.remove(d1);
             duijiaoxian2.remove(d2);
             queen[row]=-1;
         }

     }
     public List<String> generateBorad(int[] queen,int n){
        List<String> ans=new ArrayList<>();
         for (int row = 0; row < n; row++) {
             char[] temp =new char[n];
             for (int i = 0; i < n; i++) {
                temp[i]='.';
             }
             if(queen[row]!=-1){
                 temp[queen[row]]='Q';
             }
             ans.add(new String(temp));
         }return ans;
     }
}
