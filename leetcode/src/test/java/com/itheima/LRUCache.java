package com.itheima;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    static class DLinkNode{
        int key;
        int value;
        DLinkNode next;
        DLinkNode prev;
        DLinkNode(){};
        DLinkNode(int k,int v){
            this.key=k;
            this.value=v;

        }
    }
    private final Map<Integer,DLinkNode> cache=new HashMap<>();
    private int size=0;//当前实际元素数量
    private final int capacity;//容量上限
    private final DLinkNode head,tail;//伪头 伪尾
    //构造方法
    public LRUCache(int capacity){
        if(capacity<=0) throw new IllegalArgumentException("不合法");
        this.capacity=capacity;
        this.size=0;
        head=new DLinkNode();
        tail=new DLinkNode();
        head.next=tail;
        tail.prev=head;

    }
    //将节点插入到头后面
    public  void addToHead(DLinkNode node){
        node.prev=head;
        node.next=head.next;
        head.next.prev=node;
        head.next=node;
    }
    //摘除某一节点
    public void removeNode(DLinkNode node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    //将节点移到头：标记为最近使用
    public void moveTohead(DLinkNode node){
        removeNode(node);
        addToHead(node);
    }
    //弹出最久未使用的节点
    public DLinkNode removeTail(){
        DLinkNode node = tail.prev;
        removeNode(node);
        return node;
    }
public void put(int key,int value){
        DLinkNode node = cache.get(key);
        if(node!=null){
            node.value=value;
            moveTohead(node);
            return;
        }
        DLinkNode newNode= new DLinkNode(key,value);
    cache.put(key,newNode);
        addToHead(newNode);
        size++;
if(size>capacity){
DLinkNode deletet = removeTail();
cache.remove(deletet.key);
size--;
}




}
public int get(int key){
        DLinkNode node=cache.get(key);
        if(node!=null){
            moveTohead(node);
            return node.value;
        }
        else return -1;
}

}
