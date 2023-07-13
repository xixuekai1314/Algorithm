package com.kai.algorithm.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Arrays31
 * @Description: leetcode题库第三十一题
 * 取所有数字排列组合中刚好比你大的那个数
 *                  思路就是交换及排序（这里因为已经排序了，所以交换可以代替排序）
 *                  12435 的下一个数为
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年03月14日 21:46:28
 **/
public class Arrays31 {
    //方法1 暴力法
    //方法2
    public void nextPermutation(int[] nums) {
        //这里直接减2是方便后续寻找这个所谓的小值，因此下边的比对要是 n 和 n + 1 比对
        int n = nums.length - 2;
        //循环 找出应该交换的小值和大值
        //由下边可以看出要找出第一个前数小于后一个数的
        //139765421 -> 149765321 -> 141235679
        while (n >= 0 && nums[n] >= nums[n + 1]) {
            n--;
        }
        //结束循环后的 n 即为所谓的小数，那么接下来要在小数的右边找出刚好大于小数的那个数
        //因为此时小数的右边是从大到小的排列，因此再遍历一次即可
        //此处判断 n > 0 是为了预防整个数列是从大到小的排列，那样直接将整个序列逆转即可
        if (n >= 0) {
            int i = nums.length - 1;
            //此处要找出的那个数必须是大于所谓的小数的，不可等于，否则交换后的数字会变小
            while (i >= 0 && nums[n] >= nums[i]) {
                i--;
            }
            swap(nums, n, i);
        }
        reverse(nums, n + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
