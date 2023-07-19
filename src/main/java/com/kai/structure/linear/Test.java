package com.kai.structure.linear;

import com.kai.structure.linear.list.LinkedList;

/**
 * @ClassName: Test
 * @Description: 测试类
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年05月03日 10:31:11
 **/
public class Test {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        System.out.println(list.isEmpty());
        for (int i = 0; i < 12; i++) {
            list.add(i + "");
        }
        System.out.println(list.toString());
        System.out.println(list.isEmpty());
        list.add(0,"0位置添加");
        System.out.println(list.toString());
        list.addBefore("0","before");
        System.out.println(list.toString());
        list.addAfter("0","after");
        System.out.println(list.toString());
        System.out.println(list.contains("0"));
        System.out.println(list.get(0));
        list.set(0,"0位置修改");
        System.out.println(list.contains("0"));
        System.out.println(list.toString());
        list.remove("0");
        System.out.println(list.toString());
        list.remove(0);
        System.out.println(list.toString());
        list.clear();
        System.out.println(list.toString());
        System.out.println(list.size());
    }
}
