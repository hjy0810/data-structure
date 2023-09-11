package linkedList;

/**
 * @author 黄建永
 * @version 1.0
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();

        Node node1 = new Node(1,"name1");
        Node node2 = new Node(3,"name3");
        Node node3 = new Node(7,"name7");
        Node node4 = new Node(5,"name5");

        linkedList.add2(node1);
        linkedList.add2(node2);
        linkedList.add2(node3);
        linkedList.add2(node4);

        linkedList.list();

        linkedList.update(new Node(10,"name10"));
        linkedList.update(new Node(5,"name55"));
        //修改后
        System.out.println("修改后列表");
        linkedList.list();

        linkedList.delete(new Node(10,"name10"));
        linkedList.delete(new Node(3,"name3"));
        System.out.println("删除后列表");
        linkedList.list();
    }
}

class SingleLinkedList{
    Node head = new Node(0,"");
    
    //添加结点到尾部
    public void add(Node node){
        Node temp=head;
        //1.找到最后一个节点
//        while (temp.next!=null){
//            temp=temp.next;
//        }
        while(true){
            if (temp.next==null) {
                break;
            }
            temp=temp.next;
        }
        //2.把node插入到这个节点的后面
        temp.next=node;
        node.next=null;
    }

    //添加结点到指定位置
    public void add2(Node node){
        Node temp=head;
        //1.找到比待插入结点小的前一个结点
        while (temp.next!=null && temp.next.value<node.value){
            temp=temp.next;
        }
        //2把结点插入当前结点后面
        node.next=temp.next;
        temp.next=node;
    }

    //改
    public void update(Node node){
        Node temp=head;
        boolean flag=false;//是否找到该结点
        if (temp.next==null) {
            System.out.println("链表为空");
            return;
        }
        while (true){
            if (temp.next==null) {
                break;
            }
            if (temp.value==node.value) {
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag) {
            temp.name=node.name;
        }else{
            System.out.println("没找到该位置");
        }
    }

    //删
    public void delete(Node node){
        Node temp=head;
        boolean flag=false;
        while (true){
            if (temp.next==null) {
                System.out.println("链表为空");
                break;
            }
            if (temp.next.value==node.value) {
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag) {
            temp.next=temp.next.next;
        }else{
            System.out.println("要删除的结点不存在");
        }
    }

    //打印链表
    public void list(){
        if (head.next==null) {
            System.out.println("链表为空");
            return;
        }
        Node temp=head.next;
        while (temp!=null){
            System.out.println(temp);
            temp=temp.next;
        }
    }

    //单链表中有效结点的个数（不包含头结点）
    public int getLength(Node node){
        if (node.next==null){
            return 0;
        }
        int length=0;
        Node cur=node.next;
        while (cur!=null){
            length++;
            cur=cur.next;
        }
        return length;
    }


}

class Node{
    public int value;
    public String name;
    public Node next;

    public Node(int value,String name){
        this.value = value;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}