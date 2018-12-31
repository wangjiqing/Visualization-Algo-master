# 3.1 基于分钱问题实现的可视化随机模拟问题

## 何为分钱问题？

    房间里有100个人，每个人都有100元钱，他们在玩一个游戏。每轮游戏中，每隔人都要拿出一元钱随机给另一个人，最后这100个人的财富分布是怎样的？

## 代码设计思路

    1. 在AlgoVisualizer类中构造一个钱的持有者对象
    
        private int[] money;        // 构造钱的持有者对象

    2. 初始化100个money对象，每个对象中有100元钱
    
        // 初始化100个人
        money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100; // 每个人初始的时候都有100块钱
        }
        
    3. 在AlgoFrame类中将money对象创建出来
    
        // 将钱的持有者创建出来
        private int[] money;
        public void render(int[] money){
            this.money = money;
            repaint();
        }
        
    4. 动态绘制钱分配的柱状图
    
        // 绘制柱状图
        int w = canvasWidth / money.length;
        // 动态绘制柱状图，实现每次动态分钱的操作
        for (int i = 0; i < money.length; i++) {
            if (money[i] > 0) {
                AlgoVisHelper.setColor(g2d, AlgoVisHelper.Blue);
                AlgoVisHelper.fillRectangle(g2d, i*w + 1,
                        canvasHeight / 2 - money[i], w - 1, money[i]);
            } else if (money[i] < 0) {
                AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                AlgoVisHelper.fillRectangle(g2d, i*w + 1,
                        canvasHeight / 2, w - 1, -money[i]);
            }
        }
        
    5. 在AlgoVisualizer类中实现动画逻辑，每次随机一个人给另外一个人1块钱
    
        // 动画逻辑
            private void run(){
                while (true) {
                    Arrays.sort(money);
                    frame.render(money);    // 创建
                    AlgoVisHelper.pause(DELAY); // 停留事件
        
                    for (int k = 0; k < 50; k++) {
                        // 随机分配，将index为i的钱分给index为j的人
                        for (int i = 0; i < money.length; i++) {
        //                    if (money[i] > 0) {
                                int j = (int) (Math.random() * money.length);
                                money[i] -= 1;
                                money[j] += 1;
        //                    }
                        }
                    }
                }
            }
        
    6. 调整画布大小/名字/画面绘制时间间隔
    
        // 初始化视图名称
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("MoneyProgram", sceneWidth, sceneHeight);
            new Thread(() -> run()).start();
        });
        
        // 设置画面大小
        public static void main(String[] args) {
        
            int sceneWidth = 1000;
            int sceneHeight = 800;
        
            new AlgoVisualizer(sceneWidth, sceneHeight);
        }
        
        // 设置画面绘制时间间隔
        private static int DELAY = 1;   // 画面停留事件间隔