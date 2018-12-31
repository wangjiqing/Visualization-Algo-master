# 3.3 不需要可视化的蒙特卡洛模拟Π的近似值

## 去掉可视化打点操作，控制台直接模拟快速得到Π的近似值

## 代码设计思路

    1. 上一节中的Circle.java、MonteCarloPiData.java不动
    2. 新建MonteCarloExperiment.java，执行其中的main方法
    
        public class MonteCarloExperiment {
        
            private int squareSide;
            private int N;
            private int outputInterval = 100;
        
            public MonteCarloExperiment(int squareSide, int n) {
                if (squareSide <= 0 || n <= 0) {
                    throw new IllegalArgumentException("squareSide and n must larget larger than zero!");
                }
                this.squareSide = squareSide;
                N = n;
            }
        
            // 设置计算间隔
            public void setOutputInterval(int interval) {
                if (interval <= 0) {
                    throw new IllegalArgumentException("interval must be larger than zero!");
                }
                this.outputInterval = interval;
            }
        
            public void run() {
                Circle circle = new Circle(squareSide / 2, squareSide / 2, squareSide / 2);
                MonteCarloPiData data = new MonteCarloPiData(circle);
        
                for (int i = 0; i < N; i++) {
                    if (i % outputInterval == 0) {
                        System.out.println(data.estimatePi());
                    }
        
                    int x = (int) (Math.random() * squareSide);
                    int y = (int) (Math.random() * squareSide);
                    data.addPoint(new Point(x, y));
                }
            }
        
            public static void main(String[] args) {
                int squareSide = 800;
                int N = 10000000;
        
                MonteCarloExperiment exp = new MonteCarloExperiment(squareSide, N);
                exp.setOutputInterval(10000);
                exp.run();
            }
        }
