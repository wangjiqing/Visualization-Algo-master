public class MazeData {

    public static final char ROAD = ' ';    // 路
    public static final char WALL = '#';    // 墙

    private int N, M;
    public char[][] maze;
    public boolean[][] visited;
    public boolean[][] isMist;  // 实现迷雾效果使用
    public boolean[][] path;

    private int entranceX, entranceY;
    private int exitX, exitY;

    public MazeData(int N, int M) {
        if (N % 2 == 0 && M % 2 == 0) {
            throw new IllegalArgumentException("Our Maze Generalization Algorihtm requires the width and height of " +
                    "the maze are odd numbers");
        }

        this.N = N;
        this.M = M;

        maze = new char[N][M];
        visited = new boolean[N][M];
        isMist = new boolean[N][M];
        path = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i%2 == 1 && j%2 == 1) {
                    maze[i][j] = ROAD;
                } else {
                    maze[i][j] = WALL;
                }
                visited[i][j] = false;
                isMist[i][j] = true;
                path[i][j] = false;
            }
        }

        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;

        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    // 打开迷雾的方法
    public void openMist(int x, int y) {
        if (!inArea(x, y)) {
            throw new IllegalArgumentException("x or y is out of bound!");
        }

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (inArea(i, j)) {
                    isMist[i][j] = false;
                }

            }
        }
    }
}
