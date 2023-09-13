package recursion;

/**
 * @author 黄建永
 * @version 1.0
 */
public class EightQueen {
    private static int maxSize = 8;
    //存放结果，其中数组中元素索引代表行，元素值代表列，都是从0开始记数
    private static int[] array = new int[maxSize];
    private static int count = 0;//解法个数

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.queen(0);
        System.out.printf("共有%d中解法", count);
    }

    //放置第n个皇后
    private void queen(int n) {
        if (n == maxSize) {//已到第9行，退出
            print();
            return;
        }
        for (int i = 0; i < maxSize; i++) {
            array[n] = i; //第n个皇后放在i位置上
            if (judge(n)) { //判断刚添加的这个皇后的是否与前面的冲突
                //不冲突，继续添加下一个皇后
                queen(n + 1);
            }
        }
    }

    /**
     * 判断第n个皇后是否与前n-1个皇后冲突
     * 因为n从0开始，所以这里其实是判断第n+1个皇后是否与前n个皇后的位置冲突
     * @param n 第n个皇后
     * @return true代表不冲突
     * 同列       array[i]==array[n]   第i、n个皇后的列相同
     * 在同一斜线  |n-i| == |array[n]-array[i]| 绝对值
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //打印结果
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
