package linkedList;

/**
 * @author 黄建永
 * @version 1.0
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingledLinkedList circleSingledLinkedList = new CircleSingledLinkedList();

//        circleSingledLinkedList.add(5);
//        circleSingledLinkedList.list();

        System.out.println("输出顺序是");
        circleSingledLinkedList.josephu(5, 1, 2);
    }
}

class CircleSingledLinkedList {
    Node3 first = new Node3(0);

    public void add(int num) {
        if (num < 1) {
            System.out.println("数据不合理");
            return;
        }
        Node3 temp = null;
        for (int i = 0; i < num; i++) {
            Node3 node = new Node3(i);
            if (i == 0) {
                first = node;
                first.next = first;
                temp = first;
            } else {
                temp.next = node;
                node.next = first;
                temp = node;
            }
        }
    }

    //遍历单向循环列表
    public void list() {
        if (first.next == null) {
            System.out.println("链表为空");
            return;
        }
        Node3 temp = first;
        while (true) {
            System.out.println(temp.value);
            if (temp.next == first) {
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * Josephu问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人
     * 从1开始报数，数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，
     * 依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
     */
    public void josephu(int n, int k, int m) {
        this.add(n);
        if (k < 1 || m < 1) {
            return;
        }
        //辅助结点，让其指向环形链表的最后一个结点
        Node3 temp = first;
        while (true) {
            if (temp.next == first) {
                break;
            }
            temp = temp.next;
        }
        //先找到编号为k的人的结点，移动k-1次
        for (int i = 0; i < k - 1; i++) {
            first = first.next;
            temp = temp.next;
        }

        while (true) {
            if (first.next == first) {
                break;
            }
            //找到要开始报数的人的结点，移动m-1次
            for (int i = 0; i < m - 1; i++) {
                first = first.next;
                temp = temp.next;
            }
            System.out.println(first.value);
            first = first.next;
            temp.next = first;
        }
        //最后出圈的人
        System.out.println("最后出圈的是");
        System.out.println(first.value);
    }
}

class Node3 {
    public int value;
    public Node3 next;

    public Node3(int value) {
        this.value = value;
    }
}
