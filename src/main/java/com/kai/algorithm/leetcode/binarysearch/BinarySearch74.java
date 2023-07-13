package com.kai.algorithm.leetcode.binarysearch;

/**
 * @ClassName: BinarySearch74
 * @Description: 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *                      每行中的整数从左到右按升序排列。
 *                      每行的第一个整数大于前一行的最后一个整数。
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年04月01日 09:49:13
 **/
public class BinarySearch74 {
    //方法1 暴力法
    //方法2 确定在某一行，然后查找
    public static boolean searchMatrix2(int[][] matrix, int target) {
        //行数
        int row = matrix.length;
        //列数
        int col = matrix[0].length;
        //特殊判断
        if (matrix[0][0] > target || matrix[row - 1][col - 1] < target) return false;
        //因为是顺序排列，所以判断每行第一个与下一行第一个数的大小
        for (int i = 0; i < row - 1; i++) {
            if (matrix[i][0]<= target && target < matrix[i + 1][0]) {
                row = i + 1;
                break;
            }
        }
        for (int i = 0; i < matrix[row - 1].length; i++) {
            if (matrix[row - 1][i] == target) {
                return true;
            }
        }
        return false;
    }

    //方法3 二分查找
    public static boolean searchMatrix(int[][] matrix, int target) {
        //总行数
        int row = matrix.length;
        //总列数
        int col = matrix[0].length;
        //特殊判断
        if (matrix[0][0] > target || matrix[row - 1][col - 1] < target) return false;
        //重新定义循环内部起始位
        int start = 0;
        int end = row * col - 1;
        //二分查找
        while (start <= end) {
            //中间位置
            int mid = (start + end) / 2;
            //数组值
            int midElement = matrix[mid / col][mid % col];
            if (midElement > target) end = mid - 1;
            else if (midElement < target) start = mid + 1;
            else return true;
        }
        return false ;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 2;
        System.out.println(searchMatrix(matrix, target));

    }
}
