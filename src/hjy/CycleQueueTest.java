package hjy;

import java.util.Scanner;

/**
 * @author 黄建永
 * @version 1.0
 */
public class CycleQueueTest {
    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue(4);//有效数据最多为3个
        //测试
        boolean loop = true;
        char key = ' ';//
        Scanner scanner = new Scanner(System.in);

        //菜单
        while (loop) {
            System.out.println("s(showQueue):显示队列");
            System.out.println("a(addQueue):入队");
            System.out.println("g(getQueue):出队");
            System.out.println("p(peekQueue):显示队头元素");
            System.out.println("e(exit):退出测试");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字：");
                    int val = scanner.nextInt();
                    queue.addQueue(val);
                    break;
                case 'g':
                    try {
                        int res=queue.getQueue();
                        System.out.printf("取出的元素是%d",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        int res=queue.peekQueue();
                        System.out.printf("队头元素是%d",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出");
    }
}

class CircleQueue {
    private int front;
    private int rear;
    private int[] arr;
    private int maxSize;

    public CircleQueue(int size) {
        //front rear初始值都为0，rear这里指向的是最后一个元素的后一个元素
        maxSize = size;
        arr = new int[maxSize];
    }


    //是否空队列
    public boolean isEmpty() {
        return front == rear;
    }

    //是否满队列
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //入队
    public void addQueue(int num) {
        if (isFull()) {
            System.out.println("队列已满，无法入队");
            return;
        }
        arr[rear] = num;
        rear = (rear + 1) % maxSize;
    }

    //出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法出队");
        }
        int val = arr[front];
        front = (front + 1) % maxSize;
        return val;
    }

    //显示队头元素
    public int peekQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

    //显示所有队列元素
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
        }
        //从front遍历到rear
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //获取当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}

