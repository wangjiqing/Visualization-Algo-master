import java.awt.*;

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

        // 入口开始求解迷宫
        if (!go(data.getEntranceX(), data.getEntranceY())) {
            System.out.println("The maze has NO solution");
        }

        setData(-1, -1, false);
    }

    private void setData(int x, int y, boolean isPath) {
        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // 入口开始求解迷宫，求解成功返回true,否则返回false
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
