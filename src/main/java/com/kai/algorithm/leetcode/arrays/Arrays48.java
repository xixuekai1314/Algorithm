package com.kai.algorithm.leetcode.arrays;

import org.springframework.beans.BeanUtils;

/**
 * @ClassName: Arrays48
 * @Description: leetcode题库第四十八题
 *                  旋转图像 将图像顺时针旋转 90 度
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年03月23日 21:46:28
 **/
public class Arrays48 {
    //方法1 暴力法 真实旋转
    //对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置。
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        int[][] matrixNew = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixNew[j][n - 1 - i] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrixNew[i][j];
            }
        }
    }
    //方法2 先做 沿对角线翻转，再倒叙列
    public void rotate2(int[][] matrix) {
        //记录图像边长
        int n = matrix.length;
        //对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //列 倒叙
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
    //方法3 在方法1上进行优化，不在使用多余大内存
    public void rotate(int[][] matrix) {

    }




}
