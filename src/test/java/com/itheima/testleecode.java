package com.itheima;

import org.junit.Test;

import javax.lang.model.element.NestingKind;
import java.beans.Introspector;
import java.util.*;

public class testleecode {
    @Test
    public void testcase1() {
        System.out.println("testcase1");
    }

    @Test
    public void testcase2() {

    }

    public int[] twonums(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(target - nums[i], i);
            }
        }
        return null;
    }

    public List<List<String>> leetcode2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public List<List<String>> leetcode2c(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (String str : strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }
            StringBuffer s = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) {
                    s.append((char) (i + 'a'));
                    s.append(count[i]);
                }

            }
            String key = s.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> num = new HashSet<Integer>();
        for (Integer i : nums) {
            num.add(i);
        }
        int max = 0;
        for (Integer i : num) {
            if (num.contains(i - 1)) {
                //说明 比集合中i值少的值存在 说明 该i值不能作为连续续列的开头
                continue;//直接跳过
            }
            int y = i + 1;//如果 i是连续续列的开头 设y为i开头的连续续列的下一个值
            while (num.contains(y)) {
                y++;//依次榨干集合中与i组成连续续列的值 最终y是最终的终点加1 y-x就是续列的长度
            }
            max = Math.max(max, y - i);//找出每次遍历 y-x最大的值
        }
        return max;
    }

    public void moveZeroes(int[] nums) {
        int leng = nums.length;
        int left = 0;
        int right = 0;
        while (right < leng) {
            if (nums[right] != 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;//交换两个指针的对应的数值
                left++;
            }
            right++;
        }
    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxS = 0;  //最大面积
//定义数组的左右指针
        while (left < right)/*无法僭越*/ {
            int LocalS = Math.min(height[left], height[right]) * (right - left);//求当前的面积
            maxS = Math.max(maxS, LocalS);//求历史最高值
            if (height[left] <= height[right])//如果左指针的高度小则向右移动一位
            {
                left++;
            } else //反之
            {
                right--;//同理 右指针应该减小一位
            }
        }
        return maxS;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) /*第一重循环：必经之路 每次固定一个数字 再通过左右指针确定另外两个数字*/ {
            //去重复 避免得到相同的结果
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            } //只需保证总是重复数字的左端数字参与下两轮遍历就可以 因为包含左端数字的遍历最全面（因为左右指针相差最多） 之后的重复数字遍历都无用
            int left = i + 1;
            int right = nums.length - 1;//确定除了当前数字的左右指针
            int target = -nums[i];//确定左右指针的值相加的目标值
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;//找到了也要接着找下一个三元组
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    ;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                    ;
                }//跳过重复元素 因为已经排完序了 只要包含关系就可
                else if ((nums[left] + nums[right]) < target) {
                    left++;
                } //找到了当前nums【i】的一个三元组 如果小于target就得再接再厉了
                else {
                    right--;
                }

            }

        }
        return result;
    }

    public int trap(int[] height) {
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        int leftmax = 0;
        int rightmax = 0;
        while (left < right) {
            leftmax = Math.max(leftmax, height[left]);//找到每次遍历的左右两边的最大值
            rightmax = Math.max(rightmax, height[right]);
            if (height[left] <= height[right]) {//如果左边的小于右边的 木桶原理 则这个单位只能藏leftmax-height[left]滴雨水
                result += leftmax - height[left];
                left++;
            } else {
                result += rightmax - height[right];
                right--;
            }
        }
        return result;
    }

    public int trap2(int[] height) {
        int leftmax = 0;
        int rightmax = 0;
        int sumS = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            leftmax = Math.max(leftmax, height[left]);
            rightmax = Math.max(rightmax, height[right]);
            if (height[left] <= height[right]) {
                sumS += leftmax - height[left];
                left++;
            } else {
                sumS += rightmax - height[right];
                right--;
            }//每次移动左或者右指针的时候都要加上移动的当前的目标的最多能装的雨滴值 比如移动左指针 就sumS就加上一个左边的坐标所对应的最大雨滴值leftmax-height[left];

        }
        return sumS;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }//过滤相同的元素 就是只遍历相同元素的第一个 后面的全部跳过
            target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;//找到了就一定要移动指针
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    ;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                    ;//找到了就得去重

                } else if ((nums[left] + nums[right]) < target) {
                    left++;
                } else {
                    right--;
                }

            }


        }
        return result;

    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int left = 0;
        int right = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            ;
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            right++;
            max = Math.max(max, right - left);
        }
        return max;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        List<Integer> result = new ArrayList<>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        if (pLen > sLen) {
            return result;
        }
        for (int i = 0; i < pLen; i++) {
            sCount[s.charAt(i) - 'a']++;
            pCount[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sCount, pCount)) {
            result.add(0);
        }
        for (int i = 0; i < sLen - pLen; i++) {
            sCount[s.charAt(i) - 'a']--;
            sCount[s.charAt(i + pLen) - 'a']++;
            if (Arrays.equals(sCount, pCount)) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public int subarraySum(int[] nums, int k) {
        int pre = 0;//计算数组的前n项和
        int count = 0;//用于计算最终结果
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);//初始化
        for (int i = 0; i < nums.length; i++) {
            pre = pre + nums[i];//计算前n项和
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);//pre是前n项和 扣去k 若还在map中 说明前n项和中的后面几项和为k
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);//将每次的前缀和装入map中并计数

        }
        return count;


    }


    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        int[] ans = new int[n - k + 1];//结果数组
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (pair1, pair2) -> pair1[0] != pair2[0]
                        ? pair2[0] - pair1[0]
                        : pair2[1] - pair1[1]
        );//定义有限队列  【0】代表值 【1】代表索引
        //且定义排序方式 ：降序排序 则优先队列的顶是最大值
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});//初始化前k个元素
        }
        ans[0] = pq.peek()[0];//取出优先队列的顶 其为当前的最大值
        for (int i = k; i < n; i++) {
            pq.offer(new int[]{nums[i], i});//依次插入后续元素
            while (pq.peek()[1] <= (i - k)) {
                pq.poll();//当队列顶的元素下标不属于当前窗口中时 就应该将其抹除 因为记录的当前最大值必须是当前窗口中的数值
            }
            ans[i - k + 1] = pq.peek()[0];//注入每次遍历时的最大值
        }
        return ans;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();//用于记录下标的单调队列（我们必须维持其为递减状态）
        for (int i = 0; i < k; i++) { //此乃初始化前k个元素{
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()])//如果尾插的数值大于当前的尾值 则破坏了单调递减的特性 必须修正！
            {
                deque.pollLast();
            }
            deque.offerLast(i);//插入下标
        }
        ans[0] = nums[deque.peekFirst()];//
        for (int i = k; i < n; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()])//如果尾插的数值大于当前的尾值 则破坏了单调递减的特性 必须修正！
            {
                deque.pollLast();
            }//依旧保持递减状态的单调队列
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }//除去过期元素
            deque.offerLast(i);//尾插
            ans[i - k + 1] = nums[deque.peekFirst()];//注入最大值
        }
        return ans;
    }


    public String minWindow(String s, String t) {
        Map<Character, Integer> tmap = new HashMap<>();//t的map
        Map<Character, Integer> nmap = new HashMap<>();//当前的窗口中的map
        for (int i = 0; i < t.length(); i++) {
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0) + 1);
        }//  初始化t的map
        int l = 0;
        int minLen = Integer.MAX_VALUE;  // 最小长度初始化为最大值
        int r = -1;
        int ansl = -1;
        int ansr = -1;//-1代表暂未找到
        int vaild = 0;//记录当前窗口内满足t子串的字符的数量
        //外层循环：扩张右边界
        while (r < s.length() - 1) {
            ++r;
            char localchar = s.charAt(r);//记录当前的右指针字母
            if (tmap.containsKey(localchar)) {//当当前的右指针字母包含在目标map中则将其加入当前的窗口map中
                nmap.put(localchar, nmap.getOrDefault(localchar, 0) + 1);
                if (nmap.get(localchar).equals(tmap.get(localchar))) {
                    vaild++;//如果当前窗口中的字母数量与目标map中的某个字母对应的数量相等 则将种类vaild加1
                }
            }
            //左指针右移
            while (vaild == tmap.size() && l <= r) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;  // 更新最小长度
                    ansl = l;             // 记录左边界
                    ansr = r + 1;         // 右边界（substring为左闭右开）
                }
                char leftchar = s.charAt(l);//记录当前的左指针字母
                if (tmap.containsKey(leftchar)) {//说明此次右移将有用的字母移除
                    if (tmap.get(leftchar).equals(nmap.get(leftchar))) {
                        vaild--;

                    }
                    nmap.put(leftchar, nmap.get(leftchar) - 1);//更新当前的窗口map
                }

                l++;
            }

        }


        return ansl == -1 ? "" : s.substring(ansl, ansr);
    }

    public String minWindowtest(String s, String t) {
        Map<Character, Integer> tmap = new HashMap<>();
        Map<Character, Integer> nmap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char localchar = t.charAt(i);
            tmap.put(localchar, tmap.getOrDefault(localchar, 0) + 1);
            //初始话目标map 存储t中的字母键值对
        }
        int l = 0;
        int r = -1;
        int ansl = -1;
        int ansr = -1;
        int minLen = Integer.MAX_VALUE;
        int vaild = 0;
        while (r < s.length() - 1) {//外层循环 向外扩张
            ++r;
            char rightchar = s.charAt(r);
            if (tmap.containsKey(rightchar)) {
                nmap.put(rightchar, nmap.getOrDefault(rightchar, 0) + 1);
                if (nmap.get(rightchar).equals(tmap.get(rightchar))) {
                    vaild++;//满足窗口内的字母数量与tmap中的某个字母的数数量相等 则有效值加1

                }
            }
            while (vaild == tmap.size() && l <= r) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;//更新最小的窗口长度
                    ansl = l;
                    ansr = r;

                }
                char leftchar = s.charAt(l);
                if (tmap.containsKey(leftchar)) {
                    if (tmap.get(leftchar).equals(nmap.get(leftchar))) {
                        vaild--;//由于右移而造成的条件不符合 将有效值减去1
                    }
                    nmap.put(leftchar, nmap.get(leftchar) - 1);

                }
                l++;
            }

        }

        return ansl == -1 ? "" : s.substring(ansl, ansr + 1);


    }

    public int maxSubArray(int[] nums) {
        //f(i)=max(f(i−1)+nums[i],nums[i])
        //设 f(i) 为以 nums[i] 结尾的连续子数组的最大和。
        int pre = 0;//记录除了当前元素之前的最大子序和
        int ansMax = nums[0];//将最大子序和初始化为第一个元素（动态规划思想）
        for (int x : nums) {
            pre = Math.max(x + pre, x);//探知当前的的x 是否满足结合之前的最大子序和会更大 还是会比自身还要小
            ansMax = Math.max(pre, ansMax);
        }
        return ansMax;
    }

    //通过前缀和来求解
    public int maxSubArray2(int[] nums) {
        int preN = 0;//前n项和
        int min = 0;//求最小的前缀和
        int result = Integer.MIN_VALUE;
        for (int x : nums) {
            preN += x;
            result = Math.max(result, preN - min);//主要思想是将每个前缀和都看作为一座高山
            //找出最高的山和最低的山 其差就是最大的自序和
            min = Math.min(min, preN);
        }
        return result;
    }
//[0]代表左端点 [1]代表右端点
    public int[][] merge(int[][] intervals) {
        if (intervals.length==0){
            return new int[0][0];//返回一个空数组
        }
        //用于记录根据各个集合左端点升序排列的集合
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, new java.util.Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]); // 按照左端点排序
            }
        });
        for(int[] localNum:intervals){
//如果result中为空 或者 result中的右端点 并未超过该循环的当前集合的左端点 即为不重叠
            if(result.isEmpty() ||result.get(result.size()-1)[1]<localNum[0]){
                result.add(localNum);//加入当前的集合
            }
            else {
                result.get(result.size() - 1)[1] = Math.max(localNum[1],result.get(result.size() - 1)[1]);
            }

        }return result.toArray(new int[result.size()][]);



    }
    public void rotate(int[] nums, int k) {
int length = nums.length;
int[]arr=new int[length];

        for (int i = 0; i < length; i++) {
            arr[((i+k)%length)] = nums[i];

        }System.arraycopy(arr,0,nums,0,length);
}
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(n, k);//不相干数组的元素环数
        for (int start = 0; start < count; start++) {//外层循环 循环次数是环数
            int current = start; //当前游标：被传递的下标
            int pre = nums[start];//接力变量 prev：将要传递的值
            do {
                int next = (current + k) % n;//确定被传递的下标（即瞬移k步的目标下标）
                int temp = nums[next];//保存一下将被改写的值
                nums[next] = pre;//完成传递
                pre = temp;//迭代
                current = next;//将当前游标指向被传递之下标

            } while (start != current);//直到迭代回到原点
        }
    }

    public int gcd(int x, int y) {                    // ⑮ 欧几里得算法：返回 x 与 y 的最大公约数
        return y > 0 ? gcd(y, x % y) : x;             // ⑯ 递归式：gcd(x,y)=gcd(y, x%y)，y==0 时返回 x
    }
    //三次反转法
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1) return;          // 边界：0或1个元素无需处理
        k %= n;                      // 步数归一化：k 可能大于 n
        if (k == 0) return;          // 刚好整圈，不变

        reverse(nums, 0, n - 1);     // ① 整体反转
        reverse(nums, 0, k - 1);     // ② 前 k 段反转
        reverse(nums, k, n - 1);     // ③ 后 n-k 段反转
    }

    // 原地反转闭区间 [start, end]
    private void reverse(int[] a, int start, int end) {
        while (start < end) {
            int t = a[start];
            a[start] = a[end];
            a[end] = t;
            start++;
            end--;
        }
    }
    public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] answer =  new int[n];
    int[] L =new int[n];
    int[] R=new int[n];
    L[0]=1;
    R[n-1]=1;
        for (int i = 1; i < n; i++) {
            L[i]=nums[i-1]*L[i-1];

        }
        for (int i = n-2; i >=0 ; i--) {
            R[i]=nums[i+1]*R[i+1];

        }
        for (int i = 0; i < n; i++) {
            answer[i]=L[i]*R[i];

        }return  answer;
    }
    //空间复杂度为1的
    public int[] productExceptSelf2(int[] nums){
        int n=nums.length;
        int right=1;
        int[] answer =new int[n];
        //通过answer先记录 左边的i项乘积
        answer[0]=1;
        for (int i = 1; i < n; i++) {
            answer[i]=answer[i-1]*nums[i-1];
        }
        for (int i = n-1; i >=0 ; i--) {
            answer[i]=answer[i]*right;
            right*=nums[i];
        }
        return answer;
    }
    public int firstMissingPositive(int[] nums) {
    int n=nums.length;
        for (int i = 0; i < n; i++) {
            if(nums[i]<=0||nums[i]>n)
            {
                nums[i]=n+1;
            }
        }
        for (int i = 0; i < n; i++) {
    int v=Math.abs(nums[i]);
    if(v>=1&&v<=n){
    nums[v-1]=-Math.abs(nums[v-1]);
}



        }
        for (int i = 0; i < n; i++) {
            if(nums[i]>0){
                return i+1;
            }


        }
        return n+1;
    }
    public int firstMissingPositive2(int[] nums){
        int n=nums.length;
        for (int i = 0; i < n; i++) {
            while(nums[i]>0&&nums[i]<n&&nums[i]!=nums[nums[i]-1])
            {
                int idx = nums[i]-1;
                int temp = nums[idx];
                nums[idx]=nums[i];
                nums[i]=temp;
            }


        }
        for (int i = 0; i < n; i++) {
            if(nums[i]!=i+1)
            {return i+1;}

        }return n+1;
    }
 public class ListNode {
   int val;
     ListNode next;
     ListNode(int x) {
         val = x;
        next = null;}
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }

}
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp ;
        Set<ListNode> set=new HashSet<>();
        temp=headA;
        while(temp!=null) {
            set.add(temp);
            temp=temp.next;
        }
        temp=headB;
        while(temp!=null) {
            if(set.contains(temp))
            {return temp;}
            temp=temp.next;
        }return null;
        }
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB){
        ListNode A=headA;
        ListNode B=headB;
        if(A==null||B==null) {
        return null;}
while(A!=B) {
    if(A==null) {
        A=headB;

    }
    else{A=A.next;}
    if (B==null){
        B=headA;
    }else{B=B.next;}
}return A;
    }
    public ListNode reverseList(ListNode head) {
ListNode pre=null;
ListNode local=head;
while(local!=null){
    ListNode next = local.next;
    local.next=pre;
    pre=local;
    local=next;
}return  pre;
    }
    public ListNode reverseList2(ListNode head) {
        if(head==null||head.next==null) {
        return head;
        }
        ListNode newHead=reverseList2(head.next);
        head.next.next=head;
        head.next=null;
        return newHead;
        }


    public boolean isPalindrome(ListNode head) {
//运用一个快指针（一次两步） 一个慢指针 （一次一步） 循环链表 最终慢指针将到达链表中部
ListNode endMid=toSlow(head);
ListNode fanzhuanEndMid=reverseList(endMid.next);
ListNode p1=head;
ListNode p2=fanzhuanEndMid;

boolean flag=true;
while(flag==true&&p2!=null){
    if(p1.val!=p2.val){
        flag=false;
    }
    p1=p1.next;
    p2=p2.next;
}
endMid.next= reverseList(fanzhuanEndMid);
return flag;
    }
public ListNode toSlow(ListNode a){
        ListNode fast=a;
        ListNode slow=a;
        while(fast.next != null && fast.next.next != null){
            slow=slow.next;
            fast=fast.next.next;


        }return slow;
}

    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null){
            return false;
        }
        ListNode slow;
        ListNode fast;
        slow=head;
        fast=head.next;//一开始 如果slow = fast = head，所以 slow == fast 一定成立。
        while(slow!=fast){
            if(fast==null||fast.next==null)
            {return false;}
            slow=slow.next;
            fast=fast.next.next;
        }return true;


    }
    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null){return null;}
ListNode slow;
ListNode fast;
slow=head;
        fast=head;
while(fast!=null&&fast.next!=null){
    fast=fast.next.next;
    slow=slow.next;
    if(slow==fast){
        ListNode pre=head;
        while(pre!=slow){
            pre=pre.next;
            slow=slow.next;

        }return pre;
    }
}return null;
    }
    public ListNode detectCyclehash(ListNode head){
        ListNode pos=head;
        Set<ListNode> set=new HashSet<>();
        while(pos!=null){
            if(set.contains(pos))return pos;
            else {
                set.add(pos);
            }
            pos=pos.next;
        }return null;
    }



    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }

    }
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode answer=new ListNode(-1);
        ListNode pre = answer;
        while(list1!=null&&list2!=null){
            if(list1.val<=list2.val){
                pre.next=list1;
                list1=list1.next;
            }
            else {
                pre.next=list2;
                list2=list2.next;
            }
            pre=pre.next;
        }
        pre.next=(list1==null)?list2:list1;
        return answer.next;
    }




    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode answer=null;
        ListNode index=null;
        int carry=0;
        while(l1!=null||l2!=null){
            int n1= l1!=null?l1.val:0;
            int n2= l2!=null?l2.val:0;
            int sum=n1+n2+carry;
            carry=sum/10;
            if(answer==null){
                answer=index=new ListNode(sum%10);


            }else {
                index.next=new ListNode(sum%10);
                index=index.next;
            }
            if(l1!=null)l1=l1.next;
            if(l2!=null)l2=l2.next;

        }
        if(carry>0)index.next=new ListNode(carry);
return answer;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
ListNode slow = new ListNode(0,head);
ListNode lazzy=slow;
ListNode fast=head;
        for (int i = 0; i < n; i++) {
            fast=fast.next;

        }
        while(fast!=null){
            lazzy=lazzy.next;
            fast=fast.next;

        }
        lazzy.next=lazzy.next.next;
return slow.next;
    }
    public ListNode swapPairs(ListNode head) {
if(head==null||head.next==null){
    return head;
}
else{
    ListNode newhead=head.next;
    head.next=swapPairs(newhead.next);
    newhead.next=head;
 return newhead;
}
    }
    // 只反转 [head, tail] 这段；返回 {新头, 新尾}
public ListNode[] myReverse(ListNode head, ListNode tail){
        ListNode prev=tail.next;
        ListNode p=head;
        while(prev!=tail)
    {
        ListNode nes=p.next;
        p.next=prev;
        prev=p;
        p=nes;
    }return new ListNode[]{tail,head};
}
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair =new ListNode(0,head);
        ListNode pre=hair;
        while(head!=null){
            ListNode tail=pre;
            for (int i = 0; i < k; i++) {tail=tail.next;
                if(tail==null){return hair.next;}

            }
            ListNode nex=tail.next;
            ListNode[] reversed=myReverse(head,tail);
            ListNode newhead=reversed[0];
            ListNode newtail=reversed[1];
            pre.next=newhead;
            newtail.next=nex;
            pre=newtail;
            head=newtail.next;

        }return hair.next;
    }
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        if(head==null){
            return null;
        }
        for(Node node=head;node!=null;node=node.next.next){
            Node newNode=new Node(node.val);
            newNode.next=node.next;
            node.next=newNode;
        }
        for(Node node=head;node!=null;node=node.next.next){
            Node newNode=node.next;

            newNode.random=(node.random!=null)?node.random.next:null;
        }
        Node answer=head.next;
        for(Node node=head;node!=null;node=node.next){
            Node newNode=node.next;
            node.next=node.next.next;
            newNode.next=(newNode.next!=null)?newNode.next.next:null;
        }
        return answer;
    }
    public ListNode sortList(ListNode head) {
        if(head==null){return null;}

int length=0;
        ListNode node=head;
        while(node!=null){length++;
        node=node.next;}
        ListNode answer=new ListNode(0,head);
        for (int i = 1; i < length; i=i*2) {
            ListNode pre=answer;//已合并部分的尾巴
            ListNode curr=answer.next;
            while(curr!=null){
                //切第一段链表
                ListNode head1=curr;//第一段的头
                for (int j = 1; j <i&&curr.next!=null ; j++) {curr=curr.next;

                    //循环结束时curr在第一段的尾部
                }
                ListNode head2=curr.next;//第二段的头
                curr.next=null;
                curr=head2;
                for (int j = 1; j <i&&curr.next!=null ; j++) {curr=curr.next;

                    //循环结束时curr在第一段的尾部
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next; // 第二段尾巴之后的起点
                    curr.next = null; // 断开第二段
                }
                ListNode merged = mergeTwoLists2(head1, head2);
                pre.next=merged;
                while(pre.next!=null){pre=pre.next;}
                curr=next;
            }

        }return answer.next;
    }
    class Status implements Comparable<Status> {
        int val;        // 节点的值
        ListNode ptr;   // 链表的当前节点

        // 构造函数
        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        // 实现 compareTo 方法，按节点值升序排序
        public int compareTo(Status status2) {
            return this.val - status2.val;  // 比较两个节点的值
        }

    }    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Status> queue=new PriorityQueue<Status>();
        for(ListNode listNode:lists){
            if(listNode!=null){
                queue.offer(new Status(listNode.val,listNode));
            }}
            ListNode answer=new ListNode(0);
            ListNode sign=answer;
            while(!queue.isEmpty()){
                Status curr=queue.poll();
                sign.next=curr.ptr;
                sign=sign.next;
                if(curr.ptr.next!=null){
                    queue.offer(new Status(curr.ptr.next.val,curr.ptr.next));

                }
            }

        return answer.next;

    }
    public class TreeNode {
     int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
   TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
       this.left = left;
         this.right = right;
     }
  }
//中序遍历的递归做法
public List<Integer> inorderTraversal(TreeNode root) {
    // 定义一个列表，用来存放遍历的结果
    List<Integer> answer = new ArrayList<>();

    // 调用递归函数，从根节点开始进行中序遍历
    inorder(root, answer);

    // 返回最终结果
    return answer;
}

    public void inorder(TreeNode root, List<Integer> answer) {
        // 递归终止条件：如果当前节点为空，直接返回
        if (root == null) {
            return;
        }

        // 1. 递归遍历左子树
        inorder(root.left, answer);

        // 2. 访问当前节点，把值加入到结果列表
        answer.add(root.val);

        // 3. 递归遍历右子树
        inorder(root.right, answer);
    }
//栈迭代法
    public List<Integer> inorderTraversal2(TreeNode root) {
        // 定义一个列表，用来存储中序遍历的结果
        List<Integer> answer = new ArrayList<>();

        // 定义一个栈（用 ArrayDeque 来实现），用来模拟递归过程
        // 递归时系统会自动维护调用栈，这里我们要自己维护
        Deque<TreeNode> deque = new ArrayDeque<>();

        // 只要当前节点不为空，或者栈中还有未处理的节点，就继续循环
        while (root != null || !deque.isEmpty()) {

            // 1. 一直往左子树方向走，并把沿途的节点压栈
            //    因为中序遍历要先处理左子树，所以要走到最左边
            while (root != null) {
                deque.push(root);   // 当前节点暂时存到栈里，等待将来访问
                root = root.left;   // 移动到它的左孩子
            }

            // 2. 到达最左侧（root==null）时，开始回退
            //    从栈中弹出一个节点，这个节点的左子树已经访问完毕
            root = deque.pop();

            // 3. 访问当前节点（即 "中序" 的中间部分：根节点）
            answer.add(root.val);

            // 4. 然后转向右子树，继续上面的过程
            root = root.right;
        }

        // 返回最终的中序遍历结果
        return answer;
    }

    public List<Integer> inorderTraversal3(TreeNode root) {
        // 用来存放遍历结果
        List<Integer> answer = new ArrayList<>();
        // 前驱节点（predecessor），用来寻找当前节点左子树中的“最右节点”
        TreeNode pre;

        // 遍历整棵树，直到 root 为空
        while (root != null) {
            // 情况 1：如果当前节点没有左子树
            if (root.left == null) {
                // 直接访问当前节点
                answer.add(root.val);
                // 转向右子树
                root = root.right;
            }
            // 情况 2：如果当前节点有左子树
            else {
                // 找到当前节点左子树的最右节点（即 root 在中序遍历中的前驱节点）
                pre = root.left;
                while (pre.right != null && pre.right != root) {
                    // 一直向右走，直到 pre.right 为 null（最右边）或者等于 root（线索已建立）
                    pre = pre.right;
                }

                // 子情况 2.1：如果前驱节点的右指针还没建立线索
                if (pre.right == null) {
                    // 建立一条临时线索：让前驱节点的右指针指回 root
                    // 这样在遍历完左子树后，可以通过这条线索回到 root
                    pre.right = root;
                    // 接着进入左子树，继续处理
                    root = root.left;
                }
                // 子情况 2.2：如果前驱节点的右指针已经指向 root，说明左子树已遍历完
                else {
                    // 访问当前节点（此时左子树已完成）
                    answer.add(root.val);
                    // 断开临时建立的线索，恢复树的原始结构
                    pre.right = null;
                    // 转向右子树，继续中序遍历
                    root = root.right;
                }
            }
        }
        // 返回最终的中序遍历结果
        return answer;
    }

    public int maxDepth(TreeNode root) {
if(root==null){
    return 0;}
    int leftMaxDepth=maxDepth(root.left);
    int rightMaxDepth=maxDepth(root.right);

 return Math.max(leftMaxDepth,rightMaxDepth)+1;
    }
    public int maxDepth2(TreeNode root){
        if(null==root)return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        int maxDe=0;
        queue.offer(root);
        while(!queue.isEmpty()){
            int sz=queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode node =queue.poll();
                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);


            }
            maxDe++;
        }return maxDe;
    }
    public TreeNode invertTree(TreeNode root) {
if(root==null){
    return null;
}
TreeNode right=invertTree(root.right);
TreeNode left=invertTree(root.left);
root.left=right;
root.right=left;
return root;
    }
    public TreeNode invertTree2(TreeNode root){
        if(root==null)return root;
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode curr=queue.poll();
            TreeNode temp=curr.left;

            curr.left=curr.right;
            curr.right=temp;
            if(curr.left!=null){
                queue.add(curr.left);
            }
            if(curr.right!=null){
                queue.add(curr.right);
            }
        }return root;
    }
    public boolean isSymmetric(TreeNode root) {
return check(root.left,root.right);

    }
    public boolean check(TreeNode left, TreeNode right){
        if(left==null&&right==null) return true;
        if(left==null||right==null){
            return false;
        }
        return left.val==right.val&&check(left.left,right.right)&&check(left.right,right.left);

    }
    public boolean check2(TreeNode left, TreeNode right){
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.add(left);
        queue.add(right);
        while (!queue.isEmpty()){
            TreeNode u=queue.poll();
            TreeNode v=queue.poll();
            if(u==null&&v==null){
                continue;//直到拿到应该对称的非空节点
                //且空节点下面无节点 所以不用插入
            }
            if(u==null||v==null||u.val!=v.val){
                return false;
            }
            //插入当前配对的两个节点的所有成对子节点（成对插入）
            queue.add(u.left);
            queue.add(v.right);
            queue.add(u.right);
            queue.add(v.left);
            //成对加入
        }
        return true;

    }
    int answer=0;
    public int diameterOfBinaryTree(TreeNode root) {
findD(root);
return answer;
    }
    public int findD(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftNodeNum=findD(root.left);//记录左子树为根的最大深度
        int rightNodeNum=findD(root.right);//记录右子树为根的最大深度
        answer=Math.max(answer,leftNodeNum+rightNodeNum);
        return Math.max(leftNodeNum,rightNodeNum)+1;//当前节点的深度
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root==null)return new ArrayList<>();
LinkedList<TreeNode> queue=new LinkedList<>();
List<List<Integer>> ans=new ArrayList<>();
queue.add(root);
while(!queue.isEmpty()){
    int size=queue.size();
    List<Integer> ansPiece=new ArrayList<>();//每次循环重建
    for (int i = 0; i < size; i++) {
        TreeNode curr=queue.poll();
        ansPiece.add(curr.val);
        if(curr.left!=null) queue.add(curr.left);
        if(curr.right!=null)queue.add(curr.right);
    }
    ans.add(ansPiece);
}return ans;
    }
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if(root==null)return new ArrayList<>();
        List<List<Integer>> ans=new ArrayList<>();
        levelFS(root,ans,0);
        return ans;
    }
    public void levelFS(TreeNode root,List<List<Integer>> ans,int level){
        if(root==null) return;
        List<Integer> ansPiece=new ArrayList<>();

      if (ans.size()<level+1){
          ans.add(ansPiece);//创建子集容器
      }
      ans.get(level).add(root.val);
      levelFS(root.left,ans,level+1);
      levelFS(root.right,ans,level+1);


    }
    public TreeNode sortedArrayToBST(int[] nums) {
return helper2SerchTree(nums,0,nums.length-1);
    }
    public TreeNode helper2SerchTree(int[] nums,int left,int right){
        if(left>right){
            return null;
        }
        int mid=(left+right)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=helper2SerchTree(nums,left,mid-1);
        root.right=helper2SerchTree(nums,mid+1,right);
        return root;
    }
    public boolean isValidBST(TreeNode root) {
return isSerchTree(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    public boolean isSerchTree(TreeNode root ,long min,long max){
        if(root==null) return true;
        else if (root.val<=min||root.val>=max){
            return false;
        }
        return isSerchTree(root.left,min, root.val)&&isSerchTree(root.right, root.val, max);
    }
    public boolean isValidBST2(TreeNode root){
        Deque<TreeNode> stack=new ArrayDeque<>();
        double min=-Double.MAX_VALUE;
        while(!stack.isEmpty()||root!=null){
            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();//第一次循环找到整个树中最小的值
            if(root.val<=min){
                return false;
            }
            min= root.val;//每次将min赋值为当前树的最小值（第一次是最小的值 第二次是第二小的值 第三次是第三小的值）
            root=root.right;
        }return true;
    }
    public int kthSmallest(TreeNode root, int k) {
Deque<TreeNode> stack=new ArrayDeque<>();
while(root!=null||!stack.isEmpty()){
    while(root!=null){
        stack.push(root);
        root=root.left;
    }
    root=stack.pop();
    k--;
    if(k==0){
        break;
    }
    root=root.right;
}return root.val;
    }
    public int kthSmallest2(TreeNode root, int k) {
        MyBst bst = new MyBst(root);
        return bst.kthSmallest(k);
    }
    public List<Integer> rightSideView(TreeNode root) {
List<Integer> ans=new ArrayList<>();
dfsRightTu(root,1,ans);
return  ans;
    }
    public void dfsRightTu(TreeNode node,int height,List<Integer> ans){
        if(node==null)return;
        if(ans.size()<height){
            ans.add(node.val);
        }
        dfsRightTu(node.right,height+1,ans);
        dfsRightTu(node.left,height+1,ans);
    }
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> ans=new ArrayList<>();
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(root!=null&&!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr=queue.poll();
                if(i==0){
                    ans.add(curr.val);

                }
                if(curr.right!=null) queue.add(curr.right);
                if(curr.left!=null) queue.add(curr.left);


            }
        }
        return  ans;
    }
    public void flatten(TreeNode root) {
        List<TreeNode> list=new ArrayList<>();
        preTraver(root,list);
        int size=list.size();
        for (int i = 0; i < size-1; i++) {
            TreeNode curr=list.get(i);
            TreeNode next=list.get(i+1);
            curr.left=null;
            curr.right=next;

        }

    }
    public void preTraver(TreeNode node ,List<TreeNode> ans){
        if(node==null){
            return;
        }else {
            ans.add(node);
            preTraver(node.left,ans);
            preTraver(node.right,ans);
        }
    }
    public void preTraver2(TreeNode node ,List<TreeNode> ans){
        if (node == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            ans.add(curr);
            if (curr.right != null) stack.push(curr.right); // 右子树先入栈
            if (curr.left != null) stack.push(curr.left);   // 左子树后入栈
        }
    }
    public List<Integer> inorderTraversal2re(TreeNode root) {
        List<Integer> ans=new ArrayList<>();
        TreeNode findLeftRight=new TreeNode();
        while(root!=null){
            if(root.left==null){
                ans.add(root.val);
                root=root.right;
            }
            else {
                findLeftRight=root.left;
                while(!(findLeftRight.right==null)&&findLeftRight.right!=root){
                    findLeftRight=findLeftRight.right;
                }
                if(findLeftRight.right==null){
                    findLeftRight.right=root;
                    root=root.left;
                }
                else{
                    ans.add(root.val);
                    findLeftRight.right=null;
                    root=root.right;
                }
            }
        }return ans;

    }
    public void flatten3(TreeNode root) {
        TreeNode cur=root;
        while(cur!=null){
            TreeNode next=cur.left;
            TreeNode pre=next;
            if(next!=null){
                while(pre.right!=null){
                    pre=pre.right;
                }
                pre.right=cur.right;
                cur.right=next;
                cur.left=null;
            }
            cur=cur.right;
        }
    }
    public TreeNode myBuildTree(int[] preorder,int[] inorder,int preLef,int preRit,int inLef,int inRit,Map<Integer,Integer> inorderMap){
        if(preLef>preRit){
            return null;
        }
        TreeNode root= new TreeNode(preorder[preLef]);//先得到根节点（先序遍历这一块）
        int getRootIndex=inorderMap.get(root.val);//查中序遍历中的根节点索引 便于后续分左右树
        int leftSize=getRootIndex-inLef;//得到当前左子树的节点个数
        //那么 我们的分割正式开始
        root.left=myBuildTree(preorder,inorder,preLef+1,preLef+leftSize, inLef, getRootIndex-1,inorderMap);
        root.right=myBuildTree(preorder,inorder,preLef+leftSize+1,preRit, getRootIndex+1, inRit,inorderMap);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
Map<Integer,Integer> inorderMap=new HashMap<>();
int n=preorder.length;
        for (int i = 0; i < n; i++) {
            inorderMap.put(inorder[i],i);
        }
        return myBuildTree(preorder,inorder,0,n-1,0,n-1,inorderMap);
    }


public TreeNode buildTree2(int[] preorder, int[] inorder) {
    if (preorder == null || preorder.length == 0) {
        return null;
    }
    TreeNode root = new TreeNode(preorder[0]);//最古老根节点 就是先序第一个
    Deque<TreeNode> stack = new LinkedList<TreeNode>();//栈
    stack.push(root);//压入最古老节点
    int inorderIndex = 0;
    for (int i = 1; i < preorder.length; i++) {
        int pre = preorder[i];//根节点的下一个节点的数值
        TreeNode node = stack.peek();//拿到栈顶节点 但不删除其在栈内的数据
        if (node.val != inorder[inorderIndex]) {//如果当前的节点 并不是中序遍历的根节点（从中序上看 该节点的左侧依然有节点）
            //那就说明该节点仍然有左子树
            node.left = new TreeNode(pre);//根据先序的规则
            stack.push(node.left);
        } else {//说明当前的原始根节点的所有左子树都被找到了 所以才会遍历到中序的根
            //因为中序的左子树全部输出完毕 → 才输出根 → 再输出右子树。
//2. 相等条件的含义
//当 stack.peek().val == inorder[inorderIndex]，说明中序“日程表”已经排到了栈顶节点 p，根据中序“左-根-右”顺序，p 的左子树必然已全部访问并弹掉；于是把 p 弹掉并 inorderIndex++，相当于中序里“输出 p”。
//3. 连续弹的过程
//弹掉 p 后，新栈顶是 p 的父节点；
//若父节点值仍等于新的 inorder[inorderIndex]，说明父节点的左子树（即 p 所在的整棵左子树）也空了，继续弹……
            while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                node = stack.pop();//循环取node，被抛弃的都是node的左子树
                inorderIndex++;//将使用过的节点遍历消失(当前相等的 和之前用过的）
            }
            node.right = new TreeNode(pre);
            stack.push(node.right);
        }
    }return root;
    }
    private int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        dfs(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return count;
    }

    private void dfs(TreeNode node, long remaining) {
        if (node == null) return;
        remaining -= node.val;
        if (remaining == 0) count++;
        dfs(node.left, remaining);
        dfs(node.right, remaining);
    }
    public int pathSum2(TreeNode root, int targetSum){
        Map<Long,Integer> preSum=new HashMap<>();
        preSum.put(0L,1);//0出现一次
        return dfsPreSum(root,targetSum,preSum,0);
    }
    public int dfsPreSum(TreeNode node, int targetSum, Map<Long,Integer> preSum,long curr){
        if(node==null)return 0;
        int cishu=0;
        curr+=node.val;
        cishu=preSum.getOrDefault(curr-targetSum,0);
        preSum.put( curr,preSum.getOrDefault(curr,0)+1);
        cishu+=dfsPreSum(node.left,targetSum,preSum,curr);
        cishu+=dfsPreSum(node.right,targetSum,preSum,curr);
        preSum.put(curr,preSum.getOrDefault(curr,0)-1);//把当前分支的前缀和删除 避免其他分支使用
        return cishu;

    }
    TreeNode anssss;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    dfsFather(root,p,q);
    return anssss;
    }
    public Boolean dfsFather(TreeNode curr, TreeNode p, TreeNode q){//深度查找curr是否包含p，g
        if(curr==null)return false;
        Boolean lefSon=dfsFather(curr.left,p,q);
        Boolean RigSon=dfsFather(curr.right,p,q);
        if((lefSon&&RigSon)||(curr.val==p.val||curr.val== q.val)&&(lefSon||RigSon)){
            anssss=curr;
        }
        return lefSon || RigSon || (curr.val == p.val || curr.val == q.val);
    }
    int sum_Max=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
    getMax(root);
    return sum_Max;
    }
    public int getMax(TreeNode node){
        if(node==null)return 0;
        /* ---------- 2. 后序遍历：先左右 ---------- */
        // 左子树单边最大贡献，若负则直接砍掉（取 0）
        int lefMax= Math.max(getMax(node.left),0);
        // 右子树单边最大贡献，若负则直接砍掉
        int rigMax=Math.max(getMax(node.right),0);
        /* ---------- 3. 构造“拱桥”路径并更新全局答案 ---------- */
        // 以当前节点为桥顶，左右臂都能要，和就是三者相加
        int maxAll=node.val+lefMax+rigMax;
        sum_Max=Math.max(sum_Max,maxAll);
        /* ---------- 4. 返回单边贡献给父节点 ---------- */
        // 父节点只能拉一条线，所以选左右里更大的那一边
        return node.val+Math.max(lefMax,rigMax);
    }
    public void dfsWater(char[][] gird,int r,int c){
        int nr= gird.length;
        int nc=gird[0].length;
        if(r<0||c<0||r>=nr||c>=nc||gird[r][c]=='0'){
            return;
        }
        gird[r][c]='0';
        dfsWater(gird, r-1, c);
        dfsWater(gird, r+1, c);
        dfsWater(gird, r, c+1);
        dfsWater(gird, r, c-1);
    }
    public int numIslands(char[][] grid) {
        int answer=0;
    if (grid==null) return 0;
        int nr= grid.length;
        int nc=grid[0].length;
        for (int r = 0; r < nr; r++) {
            for (int c = 0;  c< nc; c++) {
                if(grid[r][c]=='1'){
                    ++answer;
                    dfsWater(grid,r,c);
                }
            }

        }return answer;
    }
    public int numIslands2(char[][] grid) {
            int ans=0;
            int nr=grid.length;
            int nc=grid[0].length;
            for (int r = 0; r < nr; r++) {
                for (int c = 0; c < nc; c++) {
                    if(grid[r][c]=='1'){
                        ++ans;
                        grid[r][c]='0';
                        Queue<Integer> queue=new LinkedList<>();
                        queue.add(r*nc+c);
                        while(!queue.isEmpty()){
                            int id=queue.poll();
                            int currR=id/nc;
                            int currC=id%nc;
                            if(currR+1<nr&&grid[currR+1][currC]=='1'){
                                queue.add((currR+1)*nc+currC);
                                grid[currR+1][currC]='0';
                            }
                            if(currR-1>=0&&grid[currR-1][currC]=='1'){
                                queue.add((currR-1)*nc+currC);
                                grid[currR-1][currC]='0';}
                            if(currC-1>=0&&grid[currR][currC-1]=='1'){
                                queue.add(currR*nc+(currC-1));
                                grid[currR][currC-1]='0';}
                            if(currC+1<nc&&grid[currR][currC+1]=='1'){
                                queue.add(currR*nc+(currC+1));
                                grid[currR][currC+1]='0';}
                        }
                    }
                }
            }
                return ans;
            }



//腐烂橘子 r 是排 c是列
    int[] dr={-1,0,1,0};
    int[] dc={0,-1,0,1};
    public int orangesRotting(int[][] grid) {
    Queue<Integer> queue=new LinkedList<>();
    Map<Integer,Integer> beifulanshijian=new HashMap<>();
    int rsize= grid.length;
    int csize=grid[0].length;
    int ans=0;
        for (int r = 0; r < rsize; r++) {
            for (int c = 0; c < csize; c++) {
            if(grid[r][c]==2){
                int code=r*csize+c; //能够提取r,c坐标
                queue.add(code);
                beifulanshijian.put(code,0);//拿到时就已经腐烂
            }
            }}//填充完毕
            while(!queue.isEmpty()){
                int code=queue.remove();
                int tc=code%csize;
                int tr=code/csize;

                for (int k = 0; k < 4; k++) {

                    int nr=tr+dr[k];
                    int nc=tc+dc[k];//这样会使得每次遍历都可选择一个方向的橘子进行判断
                    if(0 <= nr && nr < rsize && 0 <= nc && nc < csize && grid[nr][nc]==1){
                        int NextCode=nr*csize+nc;
                        grid[nr][nc]=2;
                        queue.add(NextCode);
                        beifulanshijian.put(NextCode,beifulanshijian.get(code)+1);
                        ans=beifulanshijian.get(NextCode);
                    }
                }
            }
        for (int r = 0; r < rsize; r++) {
            for (int c = 0; c < csize; c++) {
                if(grid[r][c]==1){
                    return -1;
                }
            }}
        return ans;
        }
List<List<Integer>> linjietu=new ArrayList<>();
    int[] zhuangtai;//第i个节点为0代表节点没有访问过 1表示正在访问 2表示访问完毕
 Boolean returnAns=true;//结果 默认为ok

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        linjietu=new ArrayList<List<Integer>>();//给每个课程都创建一个空表;
        for (int i = 0; i < numCourses; i++) {
            linjietu.add(new ArrayList<Integer>());
        }
        zhuangtai=new int[numCourses];
        for(int[] local:prerequisites){
            linjietu.get(local[1]).add(local[0]);//填加b->a linjietu 先学b才能学a
        }
        for (int i = 0; i < numCourses&&returnAns; i++) {
            if(zhuangtai[numCourses]==0){
                Coursedfs(i);
            }
        }return returnAns;
    }
    public void Coursedfs(int i){
        zhuangtai[i]=1;
        for(int curr:linjietu.get(i)){//查学完i可以学的科目
            if(zhuangtai[curr]==0){
                Coursedfs(curr);
                if(!returnAns)return;
            }
            else if(zhuangtai[curr]==1){//说明 解释如a-》b b-》a  a作为dfs参数传入时 子循环会遍历到b  b的又是a  则第二次循环会因为zhuangtai[a]==1返回结束
            returnAns=false;
            }
        }zhuangtai[i]=2;
    }
    int[] RuDu;//一个节点的入度 说明有几个前置条件 为0时 说明无前置条件
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        linjietu=new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            linjietu.add(new ArrayList<Integer>());//每个节点都对应一个list
        }
        RuDu=new int[numCourses];//元素全部初始为0
        for(int[] IF :prerequisites){
            linjietu.get(IF[1]).add(IF[0]);
            //a->b 则会给b添加入度（一个前置条件）
            RuDu[IF[0]]++;

        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (RuDu[i] == 0) queue.add(i); // 把节点编号入队
        }
        int CompleteCourse=0;
        while(!queue.isEmpty()){
            CompleteCourse++;
            int local=queue.poll();;
            for(int v:linjietu.get(local)){
                RuDu[v]--;
                if(RuDu[v]==0){
                    queue.add(v);
                }
            }
        }return numCourses==CompleteCourse;
    }
    public int searchInsert(int[] nums, int target) {
        int n=nums.length;
        int left=0;
        int right=n-1;
        while(left<=right){
            int mid=(right-left)>>1+left;
            if(nums[mid]<target){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }return left;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int row=matrix.length;
        int col=matrix[0].length;
        int left=0;
        int right=row*col-1;
        while(left<=right) {
            int mid=((right-left)>>1)+left;
            if(target>matrix[mid/col][mid%col]){
                left=mid+1;
            }else if(target<matrix[mid/col][mid%col]){
                right=mid-1;
            }
            else {
                return true;
            }
        }return false;
    }
    public int[] searchRange(int[] nums, int target) {
        int first=-1;
        int last=-1;
        int left=0;
        int right=nums.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if(target==nums[mid]){
                first=mid;
                right=mid-1;//右边界左移
            }
            else if(target<nums[mid]){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        left=0;
        right=nums.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if(target==nums[mid]){
                last=mid;
                left=mid+1;//左边界右移
            }
            else if(target<nums[mid]){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }return new int[]{first,last};
    }
    public int search(int[] nums, int target) {
        // 边界条件：数组为空
        if (nums.length == 0) {
            return -1;
        }
        // 边界条件：数组只有一个元素
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=(right+left)/2;
            if(nums[mid]==target){
                return mid;
            }
            if(nums[mid]>=nums[0]){
                if(target<=nums[mid]&&target>=nums[0]){
                    right=mid-1;
                }
                else {
                    left=mid+1;
                }
            }
            else {
                if(nums[mid]<target&&nums[nums.length-1]>=target){

                    left=mid+1;
                }
                else {
                    right=mid-1;
                }
            }
        }
        return -1;
    }
    public int findMin(int[] nums) {
    int n=nums.length;
    int left=0;
    int right=n-1;
    while(left<=right){
        int mid=(left+right)/2;
        if(nums[mid]<nums[right]){//说明mid到right这块是有序的
            right=mid;
        }else{
            left=mid+1;
        }
    }
        return nums[left];
    }
    public boolean isValid(String s) {
        Deque<Character> deque=new ArrayDeque<>();;
        Map<Character,Character> map=new HashMap<Character,Character>(){{
            put(')', '('); // 右括号 ')' 对应左括号 '('
            put(']', '['); // 右括号 ']' 对应左括号 '['
            put('}', '{'); // 右括号 '}' 对应左括号 '{'
        }};
        if(s.length()%2==1)return false;
        for (int i = 0; i < s.length(); i++) {
            char charCurr=s.charAt(i);
            if(map.containsKey(charCurr)){
                if(deque.isEmpty()||deque.peek()!=map.get(charCurr)){
                    return false;
                }
                deque.pop();
            }
            else {
                deque.push(charCurr);
            }
        }
        return deque.isEmpty();

    }
    public String decodeString(String s) {
        Deque<Character> stack= new ArrayDeque<>();
        for (char c: s.toCharArray()){
            if(c!=']'){
                stack.push(c);
            }
            else {
                //遇见] 返回方框内的字母
                StringBuffer sb=new StringBuffer();
                while(!stack.isEmpty()&&Character.isLetter(stack.peek())){
                    sb.insert(0,stack.pop());
                }
                String sub=sb.toString();//获取方框内的字母串
                stack.pop();//弹出[

                //获取数字
                sb=new StringBuffer();
                while(!stack.isEmpty()&&Character.isDigit(stack.peek())){
                    sb.insert(0,stack.pop());
                }
                int count=Integer.valueOf(sb.toString());

                while(count>0){
                    for(char ch:sub.toCharArray()){
                        stack.push(ch);

                    }   count--;}
            }
        }
        StringBuffer ans=new StringBuffer();
        while (!stack.isEmpty()){ans.insert(0,stack.pop());}
        return ans.toString();
    }
    public int[] dailyTemperatures(int[] temperatures) {
        int length=temperatures.length;
        int[] ans=new int[length];
       Deque<Integer> stack=new ArrayDeque();
        for (int i = 0; i < length; i++) {
            int currT=temperatures[i];
            while(!stack.isEmpty()&&currT>temperatures[stack.peek()]){
                int preIndex=stack.pop();
                ans[preIndex]=i-preIndex;
            }
            stack.push(i);
        }return ans;
    }
        public int largestRectangleArea(int[] heights) {
            int length=heights.length;
            int[] left=new int[length];
            int[] right=new int[length];
            Deque<Integer> stack=new ArrayDeque<>();//存下标
            //填充左边界
            for (int i = 0; i < length; i++) {
                while(!stack.isEmpty()&&heights[i]<=heights[stack.peek()]){
                    stack.pop();
                }
                left[i]=(stack.isEmpty()?-1:stack.peek());
                stack.push(i);
            }
            stack.clear();
            for (int i = length-1; i >=0; i--) {
                while(!stack.isEmpty()&&heights[i]<=heights[stack.peek()]){
                    stack.pop();
                }
                right[i]=(stack.isEmpty()?length:stack.peek());
                stack.push(i);
            }
            int ans=0;
            for (int i = 0; i <length ; i++) {
                int width=right[i]-left[i]-1;
                int area=width*heights[i];
                ans=Math.max(area,ans);
            }
            return ans;
         }
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();//小根堆
        for (int num : nums) {
            minHeap.offer(num);
            if(minHeap.size()>k){//最后只留k个大的 在堆中 顶部（最小）的要抛弃
                minHeap.poll();
            }

        }return minHeap.poll();
    }
    }







