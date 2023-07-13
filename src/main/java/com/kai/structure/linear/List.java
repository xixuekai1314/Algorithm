package com.kai.structure.linear;

/**
 * @ClassName: list
 * @Description: list接口，list 规范
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年05月01日 09:18:02
 **/
public interface List<E> {
    //大小
    public int size();
    //获取元素
    public E get(int index);
    //设置元素
    public E set(int index, E element);
    //是否为空
    public boolean isEmpty();
    //是否包含
    public boolean contains(Object o);
    //元素所在位置
    public int indexOf(Object o);
    //添加
    public int add(E e);
    //在index位置添加
    public void add(int index, E element);
    //元素之前添加
    public int addBefore(Object obj, E e);
    //元素之后添加
    public int addAfter(Object obj, E e);
    //移除元素
    public boolean remove(Object o);
    //移除第i个元素
    public E remove(int index);
    //清空
    void clear();
}
