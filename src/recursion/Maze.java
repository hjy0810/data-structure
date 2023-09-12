package recursion;

/**
 * @author 黄建永
 * @version 1.0
 */
public class Maze {
    public static void main(String[] args) {
        System.out.println("输出原地图");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(maze[i][j]+"\t");
            }
            System.out.println("\n");
        }
        findWay(enterX,enterY);
        System.out.println("输出新地图");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(maze[i][j]+"\t");
            }
            System.out.println("\n");
        }
    }

    private static int[][] maze = { //迷宫地图，其中1代表墙，0代表路
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 0, 0, 0, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private static int enterX=1;
    private static int enterY=0;
    private static int exitX=7;
    private static int exitY=8;

    //设置方向：按照上、右、下、左的方式找路
    private static int[][] directions={{-1,0} , {0,1}, {1,0}, {0,-1}};

    /**
     * @param x
     * @param y
     * @return
     * 说明：
     * 1。迷宫地图的信息初始为0或者1，代表路或者墙。
     *    该方法会修改经过路经的坐标的数值，设置2代表可以走通，设置3代表已经走过了，但是走不通。
     *    最后输出地图信息沿着2的路径就可以出迷宫
     */
    private static boolean findWay(int x,int y){
        if (x==exitX && y==exitY) {
            maze[x][y]=2;
            return true;
        }
        if (maze[x][y]==0) {
            maze[x][y]=2;
            for (int i = 0; i < directions.length; i++) {
                int newX=x+directions[i][0];
                int newY=y+directions[i][1];
                if (findWay(newX,newY)) {
                    return true;
                }
            }
            maze[x][y]=3;
        }
        return false;
    }

    private static boolean findWay2(int x,int y){
        if (x==exitX && y==exitY) {
            maze[x][y]=2;
            return true;
        }else {
            if (maze[x][y]==0) {
                maze[x][y]=2;
                if (findWay2(x-1,y)) {
                    return true;
                }else if (findWay2(x,y+1)) {
                    return true;
                }else if(findWay2(x+1,y)){
                    return true;
                }else if (findWay2(x,y-1)) {
                    return true;
                }else {
                    maze[x][y]=3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }

    private static boolean inMaze(int x,int y){
        return x>=0 && x<9 && y >=0 && y<9;
    }
}
