import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.Stack;

public class AlgoVisualizer {

    private static int blockSide = 8;

    private static int DELAY = 5;
    private MazeData data;        // 数据
    private AlgoFrame frame;    // 视图
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(int N, int M){

        // 初始化数据
        data = new MazeData(N, M);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);
            new Thread(() -> run()).start();
        });
    }

    // 动画逻辑
    private void run() {
        setData(-1, -1);

        // 递归调用(深度优先遍历)
//        go(data.getEntranceX(), data.getEntranceY() + 1);

        // 非递归调用(深度优先遍历)
//        go();

        // 广度优先遍历
        go1();

        setData(-1, -1);
    }

    private void setData(int x, int y) {
        if (data.inArea(x, y)) {
            data.maze[x][y] = MazeData.ROAD;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // 递归绘制
    private void go(int x, int y) {
        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("x, y are out of bound!");
        }

        data.visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0] * 2;
            int newY = y + d[i][1] * 2;
            if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                setData(x + d[i][0], y + d[i][1]);
                go(newX, newY);
            }
        }
    }

    // 非递归绘制
    private void go() {
        Stack<Position> stack = new Stack<>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        stack.push(first);
        data.visited[first.getX()][first.getY()] = true;

        while (!stack.isEmpty()) {
            Position curPos = stack.pop();

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    stack.push(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
    }

    // 广度优先遍历
    private void go1() {
        LinkedList<Position> queue = new LinkedList<>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        queue.addLast(first);
        data.visited[first.getX()][first.getY()] = true;

        while (queue.size() != 0) {
            Position curPos = queue.removeFirst();

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    queue.addLast(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
    }

    public static void main(String[] args) {

        int N = 101;
        int M = 101;

        new AlgoVisualizer(N, M);
    }
}
