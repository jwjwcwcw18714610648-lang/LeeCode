package com.itheima;

import java.util.*;

public class Trie {
    private Trie[] children;//代表a-z字母
    private boolean isEnd; //当前节点是否是一个单词的结尾
    public  Trie(){
        children=new Trie[26];
        isEnd=false;//无参构造方法
    }
    public void insert(String word){
        Trie node =this;//从当前节点开始
        for (int i = 0; i < word.length(); i++) {
            int charI=word.charAt(i);//遍历每一个字母
            int index=charI-'a';//将字符映射到0-25
            if(node.children[index]==null){
                node.children[index]=new Trie();//如果没有相应子节点 则创建

            }
            node=node.children[index];
        }
        node.isEnd=true;//填充完毕 说明到达字母的末尾

    }
    public boolean search(String word){
        Trie node =this;//从当前节点开始
        for (int i = 0; i < word.length(); i++) {
            int charI=word.charAt(i);
            int index=charI-'a';
            if(node.children[index]==null){
                return false;
            }
            node=node.children[index];
        }
      return node.isEnd;

    }
    public boolean startsWith(String word){
        Trie node =this;//从当前节点开始
        for (int i = 0; i < word.length(); i++) {
            int charI=word.charAt(i);
            int index=charI-'a';
            if(node.children[index]==null){
                return false;//word未耗尽 说明前缀不充分
            }
            node=node.children[index];
        }
        return true;
    }
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> ans=new ArrayList<List<Integer>>();//结果list
        List<Integer> input=new ArrayList<>();//原排列列表
        for(int num:nums){
            input.add(num);
        }
        int n=nums.length;
        backtrack(n, input, ans, 0);
        return ans;
    }
    public void backtrack(int n,List<Integer> input,List<List<Integer>> ans,int first){//first代表确定当前的前几个值 如 abc  确定a  找后两位的不同搭配
        if(first==n){
            ans.add(new ArrayList<>(input));
            return;
        }
        for (int i = first; i < n; i++) {//固定几位就有几次循环 比如固定第0位 abc都无位置 则3次 固定1位a固定 bc 无位置 循环两次 3x2等于6 等于a33
            Collections.swap(input,first,i);//该循环 放出三个递归函数调用（每次放完之后都要保持下一次循环的input还是abc） 分别代表第一位 固定a 固定b 固定c
            backtrack(n,input,ans, first+1);
            Collections.swap(input,first,i);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {//注：数字在进行位计算时 自动转化成二进制可进行按位操作
        int n=nums.length;
       List<Integer> temp=new ArrayList<>();
       List<List<Integer>> ans=new ArrayList<>();
       for (int i=0;i<(1<<n);i++){//1<<n 表示2的n次方并强制转化为二进制 //循环2的n次方-1次
           temp.clear();
           for (int j = 0; j < n; j++) {//填充对应的子集
               if((i&(1<<j))!=0){//将外层的二进制按位与 1代表加入
                   temp.add(nums[j]);
               }
           }
            ans.add(new ArrayList<>(temp));
       }return ans;
    }

}














