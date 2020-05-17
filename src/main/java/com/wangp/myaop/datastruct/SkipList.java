package com.wangp.myaop.datastruct;

import com.sun.deploy.net.proxy.pac.PACFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author wangp
 * @Date 2020/5/15
 * @Version 1.0
 */
public class SkipList<T> {
    private static class SkipNode<E> {
        E val;//存储的数据
        double score;//跳跃表按照这个分数值进行从小到大排序。
        SkipNode<E> next, down;//next指针，down指向下一层的指针

        SkipNode() {
        }

        SkipNode(E val, double score) {
            this.val = val;
            this.score = score;
        }
    }

    private static final int max_level = 1 << 6;

    //跳表的数据结构
    private SkipNode<T> top;
    //跳表的深度
    private int level = 0;
    //跳表的抛硬币操作
    private Random random = new Random();

    //默认四重
    public SkipList() {
        this(4);
    }

    //跳表初始化
    public SkipList(int level) {
        this.level = level;
        int i = level;
        SkipNode<T> temp = null;
        SkipNode<T> prev = null;
        while (i-- != 0) {
            temp = new SkipNode<T>(null, Double.MIN_VALUE);
            temp.down = prev;
            prev = temp;
        }
        top = temp;
    }

    /**
     * 产生节点高度
     * 抛硬币
     *
     * @return
     */
    private int getRandomLevel() {
        int lev = 1;
        while (random.nextInt() % 2 == 0) {
            lev++;
        }
        return Math.min(lev, max_level);
    }

    public T get(double score) {
        SkipNode<T> t = top;
        while (t != null) {
            if (t.score == score) {
                return t.val;
            }
            if (t.next == null) {
                if (t.down != null) {
                    t = t.down;
                    continue;
                } else {
                    return null;
                }
            }
            if (t.score > score) {
                t = t.down;
            } else {
                t = t.next;
            }
        }
        return null;
    }

    public void put(double score, T val) {
        //1.找到需要插入的位置
        SkipNode<T> t = top, cur = null;//若cut不为空，表示当前score值的节点存在
        List<SkipNode<T>> path = new ArrayList<>();//记录每一层当前节点的前驱节点
        while (t != null) {
            if (t.score == score) {
                cur = t;
                break;//表示存在该值的点，表示需要更新该节点
            }
            if (t.next == null) {
                path.add(t);//需要向下寻找,先记录该节点
                if (t.down != null) {
                    t = t.down;
                    continue;
                } else {
                    break;
                }
            }
            if (t.next.score > score) {
                path.add(t);//需要向下查找，先记录该节点
                if (t.down == null) {
                    break;
                }
                t = t.down;
            } else
                t = t.next;
        }
        if (cur != null) {
            while (cur != null) {
                cur.val = val;
                cur = cur.down;
            }
        } else { //当前表中不存在score值的节点，需要从下到上插入
            int lev = getRandomLevel();
            if (lev > level) {
                SkipNode<T> temp = null;
                SkipNode<T> prev = top;
                while (level++ != lev) {
                    temp = new SkipNode<T>(null, Double.MIN_VALUE);
                    path.add(0, temp);//加到path的首部
                    temp.down = prev;
                    prev = temp;
                }
                top = temp;//头节点
                level = lev;//level长度增加到新的长度
            }
            //从后向前遍历path中的每一个节点，在其后面增加一个新的节点
            SkipNode<T> downTemp = null, temp = null, prev = null;
//            System.out.println("当前深度为"+level+",当前path长度为"+path.size());
            for (int i = level - 1; i >= level - lev; i--) {
                temp = new SkipNode<T>(val, score);
                prev = path.get(i);
                temp.next = prev.next;
                prev.next = temp;
                temp.down = downTemp;
                downTemp = temp;
            }
        }
    }

    /**
     * 根据score的值来删除节点。
     *
     * @param score
     */
    public void delete(double score) {
        //1,查找到节点列的第一个节点的前驱
        SkipNode<T> t = top;
        while (t != null) {
            if (t.next == null) {
                t = t.down;
                continue;
            }
            if (t.next.score == score) {
                // 在这里说明找到了该删除的节点
                t.next = t.next.next;
                t = t.down;
                //删除当前节点后，还需要继续查找之后需要删除的节点
                continue;
            }
            if (t.next.score > score)
                t = t.down;
            else
                t = t.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SkipNode<T> t = top, next = null;
        while (t != null) {
            next = t;
            while (next != null) {
                sb.append(next.score + " ");
                next = next.next;
            }
            sb.append("\n");
            t = t.down;
        }
        return sb.toString();
    }

    //blog: https://www.cnblogs.com/ljdblog/p/7645814.html

    public static void main(String[] args) {

    }
}
