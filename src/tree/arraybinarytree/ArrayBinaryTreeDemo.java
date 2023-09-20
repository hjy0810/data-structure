package tree.arraybinarytree;

/**
 * 学习于[堆排序（java版本）](https://blog.csdn.net/u011243684/article/details/84895262)
 * 二叉树的顺序存储结构
 * 要求：二叉树是完全二叉树
 * 实现：利用下面完全二叉树的两个特点直接在数组中遍历
 *      1。二叉树第n个结点的左子结点：2*n+1
 *      2。二叉树第n个结点的右子结点：2*n+2
 * 应用：堆排序的前置知识
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        //前序遍历  输出：1 2 4 5 3 6 7
//        arrayBinaryTree.preOrder();

        //中序遍历  输出：4 2 5 1 6 3 7
//        arrayBinaryTree.infixOrder();

        //后序遍历  输出：4 5 2 6 7 3 1
        arrayBinaryTree.postOrder();
    }
}

class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载前序遍历
    public void preOrder() {
        this.preOrder(0);
    }

    //前序遍历
    public void preOrder(int index) {
        if (this.arr == null || this.arr.length == 0) {
            System.out.println("数组中没有数据");
            return;
        }
        //前序输出当前结点
        System.out.println(this.arr[index]);
        //遍历左子树
        if (2 * index + 1 < this.arr.length) {
            preOrder(2 * index + 1);
        }
        //遍历右子树
        if (2 * index + 2 < this.arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //重载中序遍历
    public void infixOrder() {
        this.infixOrder(0);
    }

    //中序遍历
    public void infixOrder(int index) {
        if (this.arr == null || this.arr.length == 0) {
            System.out.println("数组中没有数据");
            return;
        }
        //遍历左子树
        if (2 * index + 1 < this.arr.length) {
            infixOrder(2 * index + 1);
        }
        //中序输出当前结点
        System.out.println(this.arr[index]);
        //遍历右子树
        if (2 * index + 2 < this.arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    //重载后序遍历
    public void postOrder() {
        this.postOrder(0);
    }

    //后序遍历
    public void postOrder(int index) {
        if (this.arr == null || this.arr.length == 0) {
            System.out.println("数组中没有数据");
            return;
        }
        //遍历左子树
        if (2 * index + 1 < this.arr.length) {
            postOrder(2 * index + 1);
        }
        //遍历右子树
        if (2 * index + 2 < this.arr.length) {
            postOrder(2 * index + 2);
        }
        //后序输出当前结点
        System.out.println(this.arr[index]);
    }
}
