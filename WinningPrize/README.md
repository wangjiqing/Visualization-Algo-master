# 3.4 使用蒙特卡洛算法模拟简单的中奖问题

    假设一个游戏中的开出史诗级武器的概率是20%，现在一次性打开5个宝箱，获得史诗级武器的概率是多少？
    
## 代码模拟实现

    public class WinningPrize {
    
        private double chance;  // 开出史诗武器的概率
        private int playTime;   // 每次开几个
        private int N;          // 一共开几轮
    
        public WinningPrize(double chance, int playTime, int N) {
            if (chance < 0.0d || chance > 1.0d) {
                throw new IllegalArgumentException("chance must be between 0 and 1");
            }
            if (playTime <= 0 || N <= 0) {
                throw new IllegalArgumentException("playTime Or N must be larger than 0!");
            }
    
            this.chance = chance;
            this.playTime = playTime;
            this.N = N;
        }
    
        public void run() {
            int wins = 0;
            for (int i = 0; i < N; i++) {
                if (play()) {
                    wins++;
                }
            }
    
            System.out.println("winning rate: " + (double) wins / N);
        }
    
        // 模拟开史诗武器
        private boolean play() {
            for (int i = 0; i < playTime; i++) {
                if (Math.random() < chance) {
                    return true;
                }
            }
            return false;
        }
    
        public static void main(String[] args) {
            double chance = 0.2;
            int playTime = 5;
    
            int N = 1000000;    // 每次实验操作5次
            WinningPrize exp = new WinningPrize(chance, playTime, N);
            exp.run();
        }
    }



