package com.kai.algorithm.leetcode.arrays;

import java.util.*;

/**
 * @ClassName: Arrays15
 * @Description: leetcode题库第十五题
 *               三数之和为 0  数组  中等
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年03月14日 21:46:28
 **/
public class Arrays15 {
    //方法1 暴力法
    //方法2 hash匹配 模拟第一题的两数之和  一个结果数字匹配一个list（另外两个数字）
    //方法3 双指针法
    public static List<List<Integer>> threeSum(int[] nums) {
        //排序
        Arrays.sort(nums);
        //初始化返回值
        List<List<Integer>> returnList = new ArrayList<List<Integer>>();
        //初始化数据长度
        int n = nums.length;
        //相加结果初始化
        int sum = 0;
        //循环
        for (int i = 0; i < n; i++) {
            //当最小值已经大于0时，返回
            if (nums[i] > 0) return returnList;
            //当与前一之相同时，跳过
            if (i > 0 && nums[i] == nums[i-1]) continue;
            //左指针初始化
            int lp = i + 1;
            //右指针初始化
            int rp = n - 1;
            //当左指针小于右指针时一只循环下去
            while (lp < rp) {
                //取得相加结果
                sum = nums[i] + nums[lp] + nums[rp];
                if (sum == 0) {
                    returnList.add(Arrays.asList(nums[i], nums[lp], nums[rp]));
                    //这里其实少跳一位，要结合下边的lp++才可以准确跳转
                    while (lp < rp && nums[lp] == nums[lp + 1]) {
                        lp++;
                    }
                    //左指针自增
                    lp++;
                    //同上
                    while (lp < rp && nums[rp] == nums[rp - 1]) {
                        rp--;
                    }
                    //右指针自减
                    rp--;
                } else if (sum > 0) {
                    while (lp < rp && nums[rp] == nums[rp - 1]) {
                        rp--;
                    }
                    rp--;
                } else {
                    while (lp < rp && nums[lp] == nums[lp + 1]) {
                        lp++;
                    }
                    lp++;
                }
            }
        }
        //返回
        return returnList;
    }

    public static void main(String[] args) {
        int[] nums = {-8, 2, 10, -5, 8, -1, 21, 18, 69, -10, -1, 0, 12};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists.toString());
    }
}
