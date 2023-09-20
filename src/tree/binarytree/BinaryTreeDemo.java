package tree.binarytree;

import java.util.Stack;

/**
 * 二叉树
 *  遍历与查询
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binTree = new BinaryTree();

        Node node1 = new Node(1, "Julie1");
        Node node2 = new Node(2, "Julie2");
        Node node3 = new Node(3, "Julie3");
        Node node4 = new Node(4, "Julie4");
        Node node5 = new Node(5, "Julie5");
        Node node6 = new Node(6, "Julie6");
        Node node7 = new Node(7, "Julie7");
        //手动创建二叉树
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        //赋根结点
        binTree.setRoot(node1);

        System.out.println("前序遍历结果：");
//        binTree.preOrder();
//        System.out.println("中序遍历结果：");
        binTree.infixOrder();
//        System.out.println("后序遍历结果：");
//        binTree.postOrder();

        //查找id为6的结点信息
//        Node res1 = binTree.preOrderSearch(6);//找了6次
//        System.out.println(res1);

//        Node res2 = binTree.infixOrderSearch(6);//找了5次
//        System.out.println(res2);

//        Node res3 = binTree.postOrderSearch(6);//找了4次
//        System.out.println(res3);


    }
}

class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            //前序遍历递归
//            this.root.preOrder();
            //前序遍历非递归
            this.root.preOrder2();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            //中序遍历递归
//            this.root.infixOrder();
            //中序遍历非递归
            this.root.infixOrder2();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            //后序遍历递归
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public Node preOrderSearch(int id) {
        if (this.root != null) {
            return this.root.preOrderSearch(id);
        } else {
            System.out.println("二叉树为空，无法前序遍历查找");
            return null;
        }
    }

    //中序查找
    public Node infixOrderSearch(int id) {
        if (this.root != null) {
            return this.root.infixOrderSearch(id);
        } else {
            System.out.println("二叉树为空，无法中序遍历查找");
            return null;
        }
    }

    //后序查找
    public Node postOrderSearch(int id) {
        if (this.root != null) {
            return this.root.postOrderSearch(id);
        } else {
            System.out.println("二叉树为空，无法后序遍历查找");
            return null;
        }
    }
}

class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //前序遍历非递归
    public void preOrder2(){
        Stack<Node> stack=new Stack<>();
        stack.push(this);
        while (!stack.isEmpty()){
            Node temp=stack.pop();
            System.out.println(temp);
            if (temp.right!=null) {
                stack.push(temp.right);
            }
            if (temp.left!=null) {
                stack.push(temp.left);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //中序遍历非递归
    public void infixOrder2(){
        Stack<Node> stack=new Stack<>();
        Node cur=this;
        while (cur!=null || !stack.isEmpty()){
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            if (!stack.isEmpty()) {
                cur=stack.pop();
                System.out.println(cur);
                cur=cur.right;
            }
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public Node preOrderSearch(int id) {
        System.out.println("前序遍历～");
        if (this.id == id) {
            return this;
        }
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(id);
        }
        return resNode;
    }

    //中序查找
    public Node infixOrderSearch(int id) {
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("中序遍历～");
        if (this.id == id) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(id);
        }
        return resNode;
    }

    //后序查找
    public Node postOrderSearch(int id) {
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("后序遍历～");
        if (this.id == id) {
            return this;
        }
        return resNode;
    }
}
