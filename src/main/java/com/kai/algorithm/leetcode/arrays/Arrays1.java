package com.kai.algorithm.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Arrays1
 * @Description: leetcode题库第一题
 *               两数之和为目标值  数组  简单
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年03月14日 21:46:28
 **/
public class Arrays1 {
    //方法1 暴力法
    //方法2 hash匹配（hashmap寻找数据是O（1）的复杂度）
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
