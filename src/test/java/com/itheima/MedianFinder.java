package com.itheima;

import org.junit.Test;

import java.util.PriorityQueue;

public class MedianFinder {
  PriorityQueue<Integer> maxQueue;// 大项堆
  PriorityQueue<Integer> minQueue;//小项堆

    public MedianFinder() {
    maxQueue=new PriorityQueue<>((a,b)->b-a);
    minQueue=new PriorityQueue<>((a,b)->a-b);
    }

    public void addNum(int num) {
        if(maxQueue.isEmpty()||num<=maxQueue.peek()){
            maxQueue.add(num);
        }else {
            minQueue.add(num);

        }
        if(maxQueue.size()-minQueue.size()>1){
            minQueue.add(maxQueue.poll());
        }
        if(minQueue.size()-maxQueue.size()>0){
            maxQueue.add(minQueue.poll());
        }

    }

    public double findMedian() {
        if(minQueue.size()<maxQueue.size()){
            //说明是奇数
            return maxQueue.peek();
        }


            return (minQueue.peek()+maxQueue.peek())/2.0;

    }
    @Test
    public void test4() {
        MedianFinder mf = new MedianFinder();
        int[] seq = {5, 2, 3, 4, 1, 6};
        for (int x : seq) {
            mf.addNum(x);
            System.out.println("插入 " + x + " 后中位数 = " + mf.findMedian());
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */