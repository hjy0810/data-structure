package stack;

import java.util.Scanner;

/**
 * @author 黄建永
 * @version 1.0
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        boolean loop = true;
        String key = "";
        Scanner scanner = new Scanner(System.in);

        //菜单
        while (loop) {
            System.out.println("====菜单===");
            System.out.println("list 遍历栈");
            System.out.println("push 入栈");
            System.out.println("pop 出栈");
            System.out.println("peek 获取栈顶元素");
            System.out.println("exit 退出菜单");
            key = scanner.next();
            switch (key) {
                case "list":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("输入一个整数");
                    int val = scanner.nextInt();
                    arrayStack.push(val);
                    break;
                case "pop":
                    try {
                        arrayStack.pop();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "peek":
                    arrayStack.peek();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                default:
                    break;
            }
        }
    }
}

class ArrayStack {
    public int maxSize;
    public int[] stack;
    public int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //入栈
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满，无法入栈");
            return;
        }
        top++;
        stack[top] = num;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空栈，无法出栈");
        }
        int val = stack[top];
        top--;
        return val;
    }

    //获取栈顶元素
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("空栈，无法获取栈顶元素");
        }
        return stack[top];
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("空栈，无法遍历");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}

