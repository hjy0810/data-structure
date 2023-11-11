package leetcode.debug.list;

/**
 * @author:hjy
 * @date 2023/11/11
 */
public class addTwoNumbers_2 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(9);
        ListNode node4 = new ListNode(9);
        ListNode list1=node1;
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=null;

        ListNode node2_1=new ListNode(9);
        ListNode list2=node2_1;
        node2_1.next=null;

        Solution solution = new Solution();
        ListNode resList  = solution.addTwoNumbers(list1,list2);
        System.out.println(resList);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode list=new ListNode(0);
        ListNode p1=l1,p2=l2,p=list;
        int dec=0;
        while(p1!=null && p2!=null){
            p.next=new ListNode((p1.val+p2.val + dec)%10);
            dec=(p1.val+p2.val + dec)/10;
            p=p.next;
            p1=p1.next;
            p2=p2.next;
        }
        if(p1==null){
            while(p2!=null){
                p.next=new ListNode((p2.val+dec)%10);
                dec=(p2.val+dec)/10;
                p=p.next;
                p2=p2.next;
            }
        }
        if(p2==null){
            while(p1!=null){
                p.next=new ListNode((p1.val+dec)%10);
                dec=(p1.val+dec)/10;
                p=p.next;
                p1=p1.next;
            }
        }
        if(dec==1){
            p.next=new ListNode(1);
            p.next.next=null;
        }else{
            p=null;
        }
        return list.next;
    }
}