import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Scanner;

// 处理迷宫数据，生成迷宫
public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int N, M;   // N 行数， M 列数

    private char[][] maze;

    private int entranceX, entranceY;   // 入口坐标
    private int exitX, exitY;   // 出口坐标
    public boolean[][] visited; // 标识是否被访问过的点
    public boolean[][] path;    // 某个点是否时已经寻找到的路径
    public boolean[][] result;  // 最终路径

    public MazeData(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName cannot be null!");
        }

        Scanner scanner = null;
        try {
            InputStream is = getClass().getResourceAsStream(fileName);
            scanner = new Scanner(new BufferedInputStream(is), "UTF-8");

            // 读取第一行
            String nmline = scanner.nextLine();
            String[] nm = nmline.trim().split("\\s+");

            N = Integer.parseInt(nm[0]);
            M = Integer.parseInt(nm[1]);

            maze = new char[N][M];
            visited = new boolean[N][M];
            path = new boolean[N][M];
            result = new boolean[N][M];
            // 读取后续的N行
            for (int i = 0; i < N; i++) {
                String line = scanner.nextLine();

                // 每行保证有M个字符
                if (line.length() != M) {
                    throw new IllegalArgumentException("Maze file " + fileName + " is invalid!");
                }

                for (int j = 0; j < M; j++) {
                    maze[i][j] = line.charAt(j);
                    visited[i][j] = false;
                    path[i][j] = false;
                    result[i][j] = false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        // 初始化入口，出口坐标
        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;
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

    public char getMaze(int i, int j) {
        if (!inArea(i, j)) {
            throw new IllegalArgumentException("i or j is out of index in getMaze!");
        }
        return maze[i][j];
    }

    // 判断x,y坐标是否在迷宫的范围内
    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    // 控制台测试使用
    public void print() {
        System.out.println(N + " " + M);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}
