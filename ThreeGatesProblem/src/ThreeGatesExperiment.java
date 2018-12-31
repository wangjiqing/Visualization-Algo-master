public class ThreeGatesExperiment {

    private int N;

    public ThreeGatesExperiment(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be larger than 0!");
        }
        N = n;
    }

    public void run(boolean chageDoor) {
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play(chageDoor)) {
                wins++;
            }
        }
        System.out.println(chageDoor ? "change" : "Not Change");
        System.out.println("winning rate: " + (double) wins / N);
    }

    // changeDoor : true 不换门  false 换门
    private boolean play(boolean changeDoor) {
        // 有奖的门编号
        int prizeDoor = (int) (Math.random() * 3);
        // 选择的编号
        int playerChoice = (int) (Math.random() * 3);
        // 如果有奖编号等于选择编号
        if (playerChoice == prizeDoor) {
            // 换门 = 不中奖，不换门 = 中奖
            return changeDoor ? false : true;
        } else {
            return changeDoor ? true : false;
        }
    }

    public static void main(String[] args) {
        // 试玩次数
        int N = 10000000;
        ThreeGatesExperiment exp = new ThreeGatesExperiment(N);
        // 不换门
        exp.run(true);
        System.out.println();
        // 换门
        exp.run(false);
    }
}
