package com.itheima;

import java.util.ArrayList;
import java.util.List;

public class leecode22 {
    public List<String> generateParenthesis(int n) {
        List<String> ans=new ArrayList<>();
        back(0,0,n,new StringBuffer(),ans);
        return ans;
    }
    public void back(int open,int close,int n,StringBuffer stringBuffer,List<String> ans){
        if(stringBuffer.length()==n*2){
            ans.add(stringBuffer.toString());
            return;
        }
        if(open<n){
            stringBuffer.append('(');
            back(open+1,close,n,stringBuffer,ans);
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
        if(close<open){
            stringBuffer.append(')');
            back(open,close+1,n,stringBuffer,ans);
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
    }
}
