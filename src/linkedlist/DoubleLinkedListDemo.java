package linkedlist;

/**
 * @author 黄建永
 * @version 1.0
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        Node2 node1 = new Node2(1, "name1");
        Node2 node3 = new Node2(3, "name3");
        Node2 node5 = new Node2(5, "name5");
        Node2 node7 = new Node2(7, "name7");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //添加到尾部
//        doubleLinkedList.add(node1);
//        doubleLinkedList.add(node3);
//        doubleLinkedList.add(node7);
//        doubleLinkedList.add(node5);

        //添加到指定位置
        doubleLinkedList.add2(node1);
        doubleLinkedList.add2(node3);
        doubleLinkedList.add2(node5);
        doubleLinkedList.add2(node7);

        //打印
        doubleLinkedList.list();

        //删
        doubleLinkedList.delete(node7);
        doubleLinkedList.delete(node1);
        System.out.println("删除后的列表");
        doubleLinkedList.list();

        //改
        doubleLinkedList.update(new Node2(3, "name33"));
        System.out.println("修改后的列表");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    //头结点
    Node2 head = new Node2(0, "");

    //增(添加到尾部)
    public void add(Node2 node) {
        Node2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    //增（根据value值添加到合适位置）
    public void add2(Node2 node) {
        Node2 temp = head;
        boolean flag = false;
        //执行完循环以后找到插入位置的前一个位置
        while (true) {
            if (temp.next == null) {//没找到，插入到尾部
                break;
            }
            if (temp.next.value > node.value) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            node.next = temp.next;
            temp.next = node;
            node.pre = temp;
            temp.next.pre = node;
        } else {
            temp.next = node;
            node.pre = temp;
        }
    }

    //删
    public void delete(Node2 node) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        Node2 temp = head.next;
        boolean flag = false;//是否有和node.value相同的结点
        while (true) {
            if (temp == null) {//没找到
                break;
            }
            if (temp.value == node.value) {
                flag = true;
                break;//找到了，退出循环
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {//如果是最后一个结点，不需要加改句代码，否则会出现空指针异常
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("没有这个结点");
        }
    }

    //改
    public void update(Node2 node) {
        if (head.next == null) {
            System.out.println("链表为空，无法修改");
            return;
        }
        Node2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.value == node.value) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
        } else {
            System.out.println("没有这个结点");
        }
    }

    //遍历链表
    public void list() {
        if (head.next == null) {
            System.out.println("双向链表为空");
            return;
        }
        Node2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class Node2 {
    public int value;
    public String name;
    public Node2 pre;
    public Node2 next;

    public Node2(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node2{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
