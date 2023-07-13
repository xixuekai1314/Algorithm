package com.kai.structure.linear.list;

import com.kai.structure.linear.List;

/**
 * @ClassName: LinkedList
 * @Description: 链表
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年07月07日 08:09:24
 **/
public class LinkedList<E> implements List<E> {

    //头节点
    private Node<E> first;
    //尾节点
    private Node<E> last;
    //大小
    private int size;

    public LinkedList() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> old = node(index);
        E oldItem = old.item;
        old.item = element;
        return oldItem;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int add(E e) {
        return 0;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public int addBefore(Object obj, E e) {
        return 0;
    }

    @Override
    public int addAfter(Object obj, E e) {
        return 0;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public void clear() {

    }




    /**
     * @Description: 私有方法
     */
    /**
     * @Description: 基本节点
     * @Author: Kai
     * @Date: 2023/7/7 08:18
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * @Description: 判断index的正确性
     *                  通过查找源码发现这里其实还有一处调用，在获取node的时候，只是被注释掉了
     *                  所以这里是判断正确的，校验方法要再套一层
     * @Author: Kai
     * @Date: 2023/7/12 15:03
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }
    /**
     * @Description: 校验index的正确性，并抛出异常
     * @Author: Kai
     * @Date: 2023/7/12 15:04
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("index小于0或大于size");
    }

    /**
     * @Description: 获取第index个node元素
     * @Author: Kai
     * @Date: 2023/7/12 15:15
     */
    Node<E> node(int index) {
        /**
         * @Description: 源码中这里的校验被注释掉了，应该是最开始写在了获取的地方
         *                  后续增加了其他方法之后，不适合在较为靠后的位置校验（比如addall方法）
         *                  将所有校验提前，而注释了这里
         *                  // assert isElementIndex(index);
         * @Author: Kai
         * @Date: 2023/7/12 15:11
         */
        //顺查还是逆查
        if (index < (size >> 1)) {
            Node<E> x = first;
            //这里用小于并且.next可以减少一次循环（不返回第xindex个本体）
            //同时少了一条多余的赋值语句，也就是返回本体的那条
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            //原理同上
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
}
