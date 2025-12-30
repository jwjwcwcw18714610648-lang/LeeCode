package com.itheima;



import java.util.HashMap;
import java.util.Map;

public class MyBst {


   testleecode.TreeNode node;
   Map<testleecode.TreeNode,Integer> map=new HashMap<>();
   public MyBst(testleecode.TreeNode node){
       this.node=node;
       this.map=new HashMap<testleecode.TreeNode,Integer>();
       countNodeNum(node);

   }
    public int kthSmallest(int k){

        testleecode.TreeNode node1=node;
       while (node1!=null) {
           int left=getNodeNum(node1.left);
           if (left == k - 1) {
              break;
           }
           else if(left<k-1){
               node1=node1.right;
               k-=left+1;
           }else {
               node1=node1.left;
           }
       }return node1.val;
    }
public int countNodeNum(testleecode.TreeNode root){
       if(null==root)return 0;
       else {
           map.put(root,1+countNodeNum(root.left)+countNodeNum(root.right));
       }return map.getOrDefault(root,0);
}
public int getNodeNum(testleecode.TreeNode root){
       return map.getOrDefault(root,0);
}
}
