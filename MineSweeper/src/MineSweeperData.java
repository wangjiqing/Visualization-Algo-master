public class MineSweeperData {
    public static final String blockImageURL = "resources/block.png";
    public static final String flayImageURL = "resources/flag";
    public static final String mineImageURL = "resources/mine.png";
    public static String numberImageURL(int num) {
        if (num < 0 || num > 8) {
            throw new IllegalArgumentException("No such a number image!");
        }
        return "resources/" + num + ".png";
    }

    private int N, M;   // 声明画布长宽
    private boolean[][] mines;  // 有雷的格子

    public MineSweeperData(int N, int M, int mineNumber) {
        if (N <= 0 || M <= 0) {
            throw new IllegalArgumentException("Mine sweep size is board!");
        }

        if (mineNumber < 0 || mineNumber > N * M) {
            throw new IllegalArgumentException("Mine number is larger than the size!");
        }

        this.N = N;
        this.M = M;
        this.mines = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                mines[i][j] = false;
            }
        }

        mines[0][0] = true;
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    // 坐标是否在画布上
    public boolean isMine(int x, int y) {
        if (!inArea(x, y)) {
            throw new IllegalArgumentException("Out of index in isMine function!");
        }
        return mines[x][y];
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
