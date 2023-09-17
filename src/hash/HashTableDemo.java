package hash;

import java.util.Scanner;

/**
 * 简易哈希表
 * 问题：有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,住址..),
 * 当输入该员工的id时,要求查找到该员工的所有信息.
 * 要求: 不使用数据库,尽量节省内存,速度越快越好=>哈希表(散列)
 * 目标：创建一个哈希表用于存放员工信息，并实现员工信息的增删改查
 * 实现方式：hash表用数组+链表的方式实现
 *
 * @author hjy
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(8);
        System.out.println("=====菜单=====");
        System.out.println("a: 添加(add)");
        System.out.println("d: 删除(delete)");
        System.out.println("u: 修改(update)");
        System.out.println("s: 查询(search)");
        System.out.println("l: 遍历(list)");
        System.out.println("e: 退出(exit)");
        Scanner scanner = new Scanner(System.in);
        String key;
        while (true) {
            key = scanner.next();
            switch (key) {
                case "a":
                    System.out.println("请输入员工id");
                    int id = scanner.nextInt();
                    System.out.println("请输入员工name");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "l":
                    hashTable.list();
                    break;
                case "d":
                    System.out.println("输入待删除员工的id");
                    id = scanner.nextInt();
                    hashTable.delete(id);
                    break;
                case "u":
                    System.out.println("输入待更新员工的id");
                    id = scanner.nextInt();
                    System.out.println("输入待该员工新的name");
                    name = scanner.next();
                    emp = new Emp(id, name);
                    hashTable.update(emp);
                    break;
                case "s":
                    System.out.println("输入想要查询的员工id");
                    id = scanner.nextInt();
                    hashTable.search(id);
                    break;
                case "e":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

/**
 * 员工信息类
 * 简化为只有id和name
 */
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * 链表类
 * head指针指向第一个emp结点，头结点需特殊处理（增删）
 */
class LinkedList {
    //头指针，指向第一个emp
    private Emp head;

    /**
     * 增：添加到合适的位置（根据ID大小）
     *
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        //当前emp的id比第一个emp的id小，需修改head指向，后面的不需要
        if (head.id > emp.id) {
            emp.next = head;
            head = emp;
            return;
        }
        //双指针法找到添加的位置
        Emp cur = head.next;
        Emp pre = head;
        while (cur != null) {
            if (cur.id > emp.id) {
                break;
            }
            cur = cur.next;
            pre = pre.next;
        }
        //cur包含两层：通过break退出的emp要插在链表中间，通过while条件退出的要添加到最后
        emp.next = cur;
        pre.next = emp;
    }

    /**
     * 删：需要找到链表中待删除结点的前一个结点
     *
     * @param id
     */
    public void delete(int id) {
        if (head == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        //特殊处理头结点
        if (head.id == id) {
            head = head.next;
            System.out.println("已删除id为 " + id + " 的员工信息");
            return;
        }
        //双指针找到待删除emp的位置
        Emp pre = head;
        Emp cur = head.next;
        while (cur != null) {
            if (cur.id == id) {
                pre.next = cur.next;
                pre = cur;
                cur = cur.next;
                System.out.println("已删除id为 " + id + " 的员工信息");
                return;
            }
            pre = cur;
            cur = cur.next;
        }
        System.out.println("没有id为 " + id + " 的员工信息");
    }

    /**
     * 改
     *
     * @param emp
     */
    public void update(Emp emp) {
        Emp cur = head;
        while (cur != null) {
            if (emp.id == cur.id) {
                cur.name = emp.name;
                System.out.println("已修改id为 " + emp.id + " 的员工名为" + emp.name);
                return;
            }
            cur = cur.next;
        }
        System.out.println("没有该员工，无法修改");
    }

    //查
    public void search(int id) {
        Emp cur = head;
        while (cur != null) {
            if (cur.id == id) {
                System.out.println("id为 " + cur.id + " 的员工名为 " + cur.name);
                return;
            }
            cur = cur.next;
        }
        System.out.println("没有id为 " + id + " 的员工");
    }

    //遍历
    public void list(int index) {
        if (head == null) {
            System.out.println("第 " + (index + 1) + " 链表为空");
            return;
        }
        Emp cur = head;
        System.out.print("第 " + (index + 1) + " 链表为");
        while (cur!=null) {
            System.out.printf("\t->员工id为%d，姓名为%s", cur.id, cur.name);
            cur = cur.next;
        }
        System.out.println();
    }
}

class HashTable {
    LinkedList[] empLinkedListArray;
    int size;

    //初始化hash表
    public HashTable(int size) {
        this.size = size;
        this.empLinkedListArray = new LinkedList[this.size];

        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new LinkedList();
        }
    }

    //散列函数
    public int hashFunc(int id) {
        return id % size;
    }

    //增
    public void add(Emp emp) {
        int hashNum = hashFunc(emp.id);
        empLinkedListArray[hashNum].add(emp);
    }

    //删
    public void delete(int id) {
        int hashNum = hashFunc(id);
        empLinkedListArray[hashNum].delete(id);
    }

    //改
    public void update(Emp emp) {
        int hashNum = hashFunc(emp.id);
        empLinkedListArray[hashNum].update(emp);
    }

    //查
    public void search(int id) {
        int hashNum = hashFunc(id);
        empLinkedListArray[hashNum].search(id);
    }

    //遍历
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }
}
