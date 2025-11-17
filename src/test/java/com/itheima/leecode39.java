package com.itheima;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leecode39 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return ans;
        // 初始调用时，从索引 0 开始搜索
        back(candidates, target, 0, new ArrayList<Integer>());
        return ans;
    }

    // 修正后的 back 函数签名
    // * target: 目标剩余值
    // * startIndex: 决定本次递归从 candidates 数组的哪个索引开始选取元素
    public void back(int[] candidates, int target, int startIndex, List<Integer> currList) {
        // 🎯 终止条件
        if (target == 0) {
            // 注意：必须添加 currList 的一个**新副本**
            ans.add(new ArrayList<>(currList));
            return;
        }

        // 剪枝条件 (可选，但推荐)
        if (target < 0) {
            return;
        }

        // 循环从 startIndex 开始，保证不出现重复的组合
        for (int i = startIndex; i < candidates.length; i++) {
            int curr = candidates[i];

            // 1. **选择**当前元素 (candidates[i])
            currList.add(curr);

            // 2. **i 不变的分支 (允许重复选取)**
            // 递归调用时，仍然传递当前的 i (即 startIndex = i)。
            // 这样在下一层递归中，循环可以从 i 处开始，允许再次选取 curr。
            back(candidates, target - curr, i, currList);

            // 3. **回溯**：撤销选择
            // 移除列表中的最后一个元素（即刚刚添加的 curr）
            currList.remove(currList.size() - 1);

            // 4. **i 递增的分支 (自动实现)**
            // for 循环的 i++ 会自动将搜索推进到下一个元素 (i+1)，
            // 从而实现“i递增”的分支。
        }
    }
}
