package com.kai.structure.linear.list;

import java.util.Arrays;


/**
 * @ClassName: ArrayList
 * @Description: 数组
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年05月01日 16:14:35
 **/
public class ArrayList<E> implements List<E> {
    java.util.List list = new java.util.ArrayList();

    //默认长度
    private static final int DEFAULT_CAPACITY = 10;
    //明确要求是0长度数组
    private static final Object[] EMPTY_ELEMENTDATA = {};
    //无参构造用到的空数组，扩容的时候用于判断是新建还是扩容
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    //元素
    private Object[] elementData;
    //大小
    private int size;

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("参数错误: " + initialCapacity);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        checkIndex(index, size);
        return elementData(index);
    }

    @Override
    public E set(int index, E e) {
        checkIndex(index, size);
        E oldE = elementData(index);
        elementData[index] = e;
        return oldE;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        //这里目前分析有两种可能，一是代码阅读性更好，二是减少系统的一次寻址（可以看到所有有转换的地方都有循环）
        Object[] es = elementData;
        for (int i = 0; i < size; i++) {
            if (o.equals(es[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int add(E e) {
        //判断是否需要扩容
        if (size == elementData.length) grow();
        elementData[size] = e;
        return size += 1;
    }

    @Override
    public void add(int index, Object element) {
        //判断是否需要扩容
        if (size == elementData.length) {
            grow();
        }
        //调用move方法
        afterMove(elementData, index, size - index);
        elementData[index] = element;
        size += 1;
    }

    @Override
    public int addBefore(Object obj, Object o) {
        int index = indexOf(obj);
        if(index >= 0) add(index, o);
        return index;
    }

    @Override
    public int addAfter(Object obj, Object o) {
        int index = indexOf(obj);
        if(index >= 0) add(index + 1, o);
        return index;
    }

    @Override
    public boolean remove(Object o) {
        remove(indexOf(o));
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, size);
        E old = elementData(index);
        beforeMove(elementData, index, (size -= 1) - index);
        elementData[size] = null;
        return old;
    }

    @Override
    public void clear() {
        Object[] es = elementData;
        for (int i = 0; i < size; i++) es[i] = null;
        size = 0;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            E e = (E) elementData[i];
            sb.append(e == this ? "(this Collection)" : e);
            sb.append(',').append(' ');
        }
        return sb.append(']').toString();
    }

    /**
     * @Description: 私有方法
     */
    /**
     * @Description: 检查下标
     * @Author: Kai
     * @Date: 2023/5/2 18:19
     */
    private int checkIndex(int index, int size) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("index小于0或大于size");
        return index;
    }

    /**
     * @Description: 获取元素
     * @Author: Kai
     * @Date: 2023/5/2 18:20
     */
    private E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * @Description: 扩容
     * @Author: Kai
     * @Date: 2023/5/2 18:20
     */
    private Object[] grow() {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            //简单处理这里的关系
            return elementData = Arrays.copyOf(elementData, size + DEFAULT_CAPACITY);
        } else {
            return elementData = new Object[DEFAULT_CAPACITY];
        }
    }

    /**
     * @Description: 将第index位空出（将从index位开始的元素统一后移一位）
     * @Author: Kai
     * @Date: 2023/5/2 18:20
     */
    private void afterMove(Object elementData, int pos, int length) {
        //直接调用现成方法
        System.arraycopy(elementData, pos, elementData, pos + 1, length);
    }
    /**
     * @Description: 将第index位空出（将从index位开始的元素统一前移一位）
     * @Author: Kai
     * @Date: 2023/5/2 18:20
     */
    private void beforeMove(Object elementData, int pos, int length) {
        //直接调用现成方法
        System.arraycopy(elementData, pos + 1, elementData, pos, length);
    }
}
