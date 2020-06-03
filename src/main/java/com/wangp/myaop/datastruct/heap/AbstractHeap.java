package com.wangp.myaop.datastruct.heap;

import java.util.Comparator;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2020/6/3 19:45
 */
public abstract class AbstractHeap<E> implements Heap<E> {
    protected int size;
    protected Comparator<E> comparator;


    public AbstractHeap(Comparator comparator) {
        this.comparator = comparator;
    }

    public AbstractHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) :
                ((Comparable<E>) e1).compareTo(e2);
    }
}
