package com.itheima;

import java.util.ArrayList;
import java.util.List;

public class leecode131 {
    List<List<String>> ans=new ArrayList<>();
    List<String> curr=new ArrayList<>();
    boolean[][] isHuiString;
    int n;
    public List<List<String>> partition(String s) {
        n=s.length();
        isHuiString=new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                isHuiString[i][j]=true;
            }
        }
        for(int i=n-1;i>=0;i--){
            for (int j = i+1; j < n; j++) {
                isHuiString[i][j]=(s.charAt(i)==s.charAt(j))&&isHuiString[i+1][j-1];
            }//初始化 标记出所有的回文子串
        }
        dfs(s,0);//把s的从0开始的回文子串全查出
        return ans;
    }
    public void dfs(String s,int index){

        if(index==n){
            ans.add(new ArrayList<>(curr));//curr是全局变量 如果只存入指针 那随时会变 所以我们要记录快照
            return;
        }
        for(int i=index;i<n;i++){
            if(isHuiString[index][i]){
                //如果满足条件则
                curr.add(s.substring(index,i+1));
                dfs(s,i+1);
                curr.remove(curr.size()-1);
            }
        }

    }
}
