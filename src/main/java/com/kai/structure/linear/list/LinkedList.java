package com.kai.structure.linear.list;

import com.kai.structure.linear.List;
import org.springframework.boot.context.properties.bind.Nested;

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
        return indexOf(o) >= 0;
    }

    /**
     * @Description: 目前猜测这里区分空和equals还是为了减少一次调用
     * 虽然这么写会造成代码的冗余，但是减少了equals再调用null判断的这个过程
     * @Author: Kai
     * @Date: 2023/7/13 17:22
     */
    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int add(E e) {
        linkLast(e);
        return size;
    }

    @Override
    public void add(int index, E element) {
        isPositionIndex(index);
        if (index == size) linkLast(element);
        else linkBefore(element, node(index));
    }

    @Override
    public int addBefore(Object obj, E e) {
        /**
         * @Description: 其实这里这个这样可以实现功能   linkBefore(e, Node(obj));
         *                  但是这里要返回int，就先用两次循环吧，后续有时间可以优化一下
         * @Author: Kai
         * @Date: 2023/7/18 17:11
         */
        int x = indexOf(obj);
        if (x == -1) throw new IllegalArgumentException("参数不存在");
        linkBefore(e, node(x));
        return x;
    }

    @Override
    public int addAfter(Object obj, E e) {
        int x = indexOf(obj);
        if (x == -1) throw new IllegalArgumentException("参数不存在");
        linkAfter(e, node(x));
        return x;
    }

    @Override
    public boolean remove(Object o) {
        unlink(node(o));
        return true;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public void clear() {
        unlinkAll();
    }


    /**
     * @Description: 私有方法
     */
    /**
     * @Description: 基本节点
     * @Author: Kai
     * @Date: 2023/7/7 08:18
     */
    public static class Node<E> {
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
     * 通过查找源码发现这里其实还有一处调用，在获取node的时候，只是被注释掉了
     * 所以这里是判断正确的，校验方法要再套一层
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
            throw new IndexOutOfBoundsException("index小于0或大于等于size");
    }

    /**
     * @Description: 判断index的是否超出约束条件（此处的约束条件不是正确性，范围比正确性大 1）
     * 正常的index应该是小于size的，但是这里给迭代器和添加使用了
     * 因为正常是size-1个元素，但是第size-1个元素有next节点（null），也就是可以视为第size个元素
     * @Author: Kai
     * @Date: 2023/7/18 14:16
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * @Description: 校验index的是否超出约束条件，并抛出异常
     * @Author: Kai
     * @Date: 2023/7/18 15:06
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
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
         *                  index是从 0 开始的，也就是node（3） 获取的是第四个元素
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

    /**
     * @Description: 获取o所在节点
     * 这个只能顺查，没有办法提前预知o位于前半段还是后半段
     * @Author: Kai
     * @Date: 2023/7/18 17:03
     */
    public Node<E> node(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return x;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return x;
            }
        }
        throw new IllegalArgumentException("参数不存在");
    }

    /**
     * @Description: 在头部新增
     * @Author: Kai
     * @Date: 2023/7/18 14:07
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    /**
     * @Description: 在尾部新增
     * @Author: Kai
     * @Date: 2023/7/18 13:56
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    /**
     * @Description: 在succ前新增，是因为调用这里使用的是 node（index）
     * 这里的常规理解是 index 从 1 开始的
     * 而正常 开发习惯 index 从 0 开始的
     * 所以这里是在 succ前新增
     * @Author: Kai
     * @Date: 2023/7/18 15:09
     */
    void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) first = newNode;
        else pred.next = newNode;
        size++;
    }

    /**
     * @Description: 在succ后新增
     * 这个方法不是linkedList里的方法
     * @Author: Kai
     * @Date: 2023/7/18 15:09
     */
    void linkAfter(E e, Node<E> succ) {
        final Node<E> next = succ.next;
        final Node<E> newNode = new Node<>(succ, e, next);
        succ.next = newNode;
        //前节点的next 指向新节点
        if (next == null) last = newNode;
        else next.prev = newNode;
        size++;
    }

    /**
     * @Description: 清除节点x
     * 这里要将节点x的前节点，后节点，内容全部清除
     * 源代码是这么解释的：清除不是必须的，但是有助于垃圾回收并且有迭代器可以关联到也会释放内存
     * Clearing all of the links between nodes is "unnecessary"
     * but
     * helps a generational GC if the discarded nodes inhabit more than one generation
     * is sure to free memory even if there is a reachable Iterator
     * @Author: Kai
     * @Date: 2023/7/18 17:49
     */
    E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    /**
     * @Description: 这里其实多一层跳转会增加寻址的消耗
     * 但是放在这里可以方便关联上边关于 全部设置成 null 的解释
     * @Author: Kai
     * @Date: 2023/7/18 17:54
     */
    void unlinkAll() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList{");
        for (Node<E> x = first; x != null; ) {
            sb.append(" ").append(x.item).append(",");
            x = x.next;
        }
        sb.append("}");
        return sb.toString();
    }
}
