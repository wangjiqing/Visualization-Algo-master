import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class AlgoVisualizer {

    private static int DELAY = 5;
    private MazeData data;        // 数据
    private static int blockSide = 8;   // 一个小格像素为8
    private AlgoFrame frame;    // 视图

    // 表示某一点的 左 下 右 上 的偏移量
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(String mazeFile) {

        // 初始化数据
        data = new MazeData(mazeFile);

        int sceneHeight = data.N() * blockSide; // 画布高
        int sceneWidth = data.M() * blockSide;  // 画布宽

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze Solver Visualization", sceneWidth, sceneHeight);
            new Thread(() -> run()).start();
        });
    }

    // 动画逻辑
    private void run(){
        setData(-1, -1, false);

        // （使用递归方法）入口开始求解迷宫
//        if (!go(data.getEntranceX(), data.getEntranceY())) {
//            System.out.println("The maze has NO solution");
//        }

        // 非递归实现迷宫求解
//        go(data);

        // 广度优先遍历实现迷宫求解
        go1(data);

        setData(-1, -1, false);
    }

    private void setData(int x, int y, boolean isPath) {
        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // 非递归实现迷宫求解，引入栈数据结构
    private void go(MazeData data) {
        Stack<Position> stack = new Stack<>();
        Position entrance = new Position(data.getEntranceX(), data.getEntranceY());
        stack.push(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;

        boolean isSolved = false;
        while (!stack.isEmpty()) {
            Position curPos = stack.pop();
            setData(curPos.getX(), curPos.getY(), true);

            if (curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()) {
                isSolved = true;
                // 回头标记迷宫路径
                findPath(curPos);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0];
                int newY = curPos.getY() + d[i][1];

                if (data.inArea(newX, newY)
                        && data.getMaze(newX, newY) == MazeData.ROAD
                        && !data.visited[newX][newY]) {
                    stack.push(new Position(newX, newY, curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }

        if (!isSolved) {
            System.out.println("The maze has no Sulution!");
        }
    }

    // 广度优先遍历求解迷宫
    private void go1(MazeData data) {
        LinkedList<Position> queue = new LinkedList<>();
        Position entrance = new Position(data.getEntranceX(), data.getEntranceY());
        queue.addLast(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;

        boolean isSolved = false;
        while (queue.size() != 0) {
            Position curPos = queue.pop();
            setData(curPos.getX(), curPos.getY(), true);

            if (curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()) {
                isSolved = true;
                // 回头标记迷宫路径
                findPath(curPos);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0];
                int newY = curPos.getY() + d[i][1];

                if (data.inArea(newX, newY)
                        && data.getMaze(newX, newY) == MazeData.ROAD
                        && !data.visited[newX][newY]) {
                    queue.addLast(new Position(newX, newY, curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }

        if (!isSolved) {
            System.out.println("The maze has no Sulution!");
        }
    }

    // 回溯路径
    private void findPath(Position des) {
        Position cur = des;
        while (cur != null) {
            data.result[cur.getX()][cur.getY()] = true;
            cur = cur.getPrev();
        }
    }

    // （递归算法）入口开始求解迷宫，求解成功返回true,否则返回false
    private boolean go(int x, int y) {
        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("x, y are out of bound!");
        }
        // 设置被访问的点为true
        data.visited[x][y] = true;

        setData(x, y, true);

        // 如果当前坐标等于终点坐标，直接返回
        if (x == data.getExitX() && y == data.getExitY()) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            // 判断新的点在迷宫区域，并且新的点在空白处（路）,并且之前没有被访问过
            if (data.inArea(newX, newY)
                    && data.getMaze(newX, newY) == MazeData.ROAD
                    && !data.visited[newX][newY]) {
                if (go(newX, newY)) {
                    return true;
                }
            }
        }
        // 回溯，这里不是我们的解
        setData(x, y, false);
        return false;
    }

    public static void main(String[] args) {
        String mazeFile = "maze_101_101.txt";
        new AlgoVisualizer(mazeFile);
    }
}
