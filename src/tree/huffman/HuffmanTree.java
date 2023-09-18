package tree.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 构造Huffman树：
 * 1。给定n个权值作为n个叶子结点，构造一棵二叉树，若该树的带权路径长度(wpl)达到最小，
 * 称这样的二叉树为最优二叉树，也称为哈夫曼树(Huffman Tree)
 * 2。树的带权路径长度：树的带权路径长度规定为 所有叶子结点的带权路径长度之和，记为WPL(weighted path length) ,
 * WPL最小的就是赫夫曼树
 * 3。构成赫夫曼树的步骤：
 * 1）将每一个数据从小到大进行排序,每个数据都是一个节点,每个节点可以看成是一颗最简单的二叉树
 * 2）取出根节点权值最小的两颗二叉树
 * 3）组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4）再将这颗新的二叉树加入到之前的序列中，以根节点的权值大小再次排序
 * 5)不断重复 1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {2, 13, 7, 8, 10, 20};
        Node huffmanTreeRoot = createHuffmanTree(arr);

        //前序遍历 =》 60 23 10 13 37 17 8 9 2 7 20
        huffmanTreeRoot.preOrder();
    }

    public static Node createHuffmanTree(int[] arr) {
        List<Node> list = new ArrayList<>();
        for (int item : arr) {
            list.add(new Node(item));
        }

        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(leftNode.val + rightNode.val);
            parent.left = leftNode;
            parent.right = rightNode;

            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }
}

/**
 * 为了让Node对象能够使用Collections集合排序，实现Comparable接口
 */
class Node implements Comparable<Node> {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.val - o.val;
    }

    public void preOrder() {
        System.out.println(this.val);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}

