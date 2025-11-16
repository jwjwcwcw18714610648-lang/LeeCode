package com.itheima;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class zuijinzuxian {
 public class TreeNode {
   int val;
    TreeNode left;
    TreeNode right;
   TreeNode(int x) { val = x; }

}
Map<Integer,TreeNode> parent=new HashMap<>();
Set<Integer> set=new HashSet<>();
public void dfs(TreeNode node){

if(node.left!=null){
    parent.put(node.left.val,node);
    dfs(node.left);

}
if(node.right!=null){
    parent.put(node.right.val,node);
    dfs(node.right);
}
}
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
    dfs(root);
    while(p!=null){
        set.add(p.val);
        p=parent.get(p.val);
    }
    while(q!=null){
        if(set.contains(q.val)){
            return q;
        }
        q=parent.get(q.val);
    }
    return null;
    }
}

