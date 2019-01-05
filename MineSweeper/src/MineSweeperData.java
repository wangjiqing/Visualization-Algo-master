public class MineSweeperData {
    public static final String blockImageURL = "resources/block.png";
    public static final String flayImageURL = "resources/flag.png";
    public static final String mineImageURL = "resources/mine.png";
    public static String numberImageURL(int num) {
        if (num < 0 || num > 8) {
            throw new IllegalArgumentException("No such a number image!");
        }
        return "resources/" + num + ".png";
    }

    private int N, M;   // 声明画布长宽
    private boolean[][] mines;  // 有雷的格子
    private int[][] numbers;    // 存储没有雷的方向有多少个雷
    public boolean[][] open;    // 格子是否是打开的
    public boolean[][] flags;   // 是否被标记

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
        open = new boolean[N][M];
        flags = new boolean[N][M];
        numbers = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                mines[i][j] = false;
                open[i][j] = false;
                flags[i][j] = false;
                numbers[i][j] = 0;
            }
        }

        // 随机生成雷
        generateMines(mineNumber);
        calculateNumbers();
    }

    private void generateMines(int mineNumber) {
        for (int i = 0; i < mineNumber; i++) {
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }

        for (int i = M * M - 1; i >= 0; i--) {
            int iX = i / M;
            int iY = i % M;

            int randNumber = (int) (Math.random() * (i + 1));
            int randX = randNumber / M;
            int randY = randNumber % M;

            swap(iX, iY, randX, randY);
        }
    }

    // 计算每个不是雷的地方的数字标记
    private void calculateNumbers() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mines[i][j]) {
                    numbers[i][j] = -1;
                }
                numbers[i][j] = 0;
                for (int ii = i - 1; ii <= i + 1; ii++) {
                    for (int jj = j - 1; jj <= j + 1; jj++) {
                        if (inArea(ii, jj) && mines[ii][jj]) {
                            numbers[i][j]++;
                        }
                    }
                }
            }
        }
    }

    private void swap(int x1, int y1, int x2, int y2) {
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
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

    public int getNumbers(int x, int y) {
        if (!inArea(x, y)) {
            throw new IllegalArgumentException("out of index!");
        }
        return numbers[x][y];
    }

    // 打开一片没有雷区的位置
    public void open(int x, int y) {
        if (!inArea(x, y)) {
            throw new IllegalArgumentException("out of index in open function!");
        }

        if (isMine(x, y)) {
            throw new IllegalArgumentException("Cannot open an mine block in open");
        }

        open[x][y] = true;
        if (numbers[x][y] > 0) {
            return;
        }

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (inArea(i, j) && !open[i][j] && !mines[i][j]) {
                    open(i, j);
                }
            }
        }
    }

}
