import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
            frame.addKeyListener(new AlgoKeyListener());
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
//        go1();

        // 使用随机队列生成随机迷宫
//        go2();

        // 使用随即队列生成随机性更强的迷宫
        go3();

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

    // 使用随机队列遍历
    private void go2() {
        RandomQueue<Position> queue = new RandomQueue<>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;

        // 打开迷雾
        data.openMist(first.getX(), first.getY());   // 打开迷雾
        while (queue.size() != 0) {
            Position curPos = queue.remove();

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    // 打开迷雾
                    data.openMist(newX, newY);
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
    }

    // 使用随机队列遍历
    private void go3() {
        LinkRandomQueue<Position> queue = new LinkRandomQueue<>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;

        // 打开迷雾
        data.openMist(first.getX(), first.getY());   // 打开迷雾
        while (queue.size() != 0) {
            Position curPos = queue.remove();

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    // 打开迷雾
                    data.openMist(newX, newY);
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
    }

    // 非递归实现迷宫求解，引入栈数据结构
    private boolean goMaze(int x, int y) {
        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("x, y are out of bound");
        }

        data.visited[x][y] = true;
        setPathData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY()) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (data.inArea(newX, newY) && data.maze[newX][newY] == MazeData.ROAD && !data.visited[newX][newY]) {
                if (goMaze(newX, newY)) {
                    return true;
                }
            }
        }

        // 回溯
        setPathData(x, y, false);
        return false;
    }

    private void setPathData(int x, int y, boolean isPath){
        if(data.inArea(x, y))
            data.path[x][y] = isPath;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // 键盘事件，走迷宫
    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                for (int i = 0; i < data.N(); i++) {
                    for (int j = 0; j < data.M(); j++) {
                        data.visited[i][j] = false;
                    }
                }
                new Thread(() -> goMaze(data.getEntranceX(), data.getEntranceY())).start();
            }
        }
    }

    public static void main(String[] args) {

        int N = 101;
        int M = 101;

        new AlgoVisualizer(N, M);
    }
}
