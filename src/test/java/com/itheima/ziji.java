package com.itheima;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ziji {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> temp=new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0,nums);
        return ans;
    }
    public  void dfs(int cur,int[] nums){
        if(cur==nums.length){
            ans.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[cur]);
        dfs(cur+1,nums);
        temp.remove(temp.size()-1);//回溯

        dfs(cur+1,nums);
    }
}
