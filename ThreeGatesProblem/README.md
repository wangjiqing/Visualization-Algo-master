# 3.4 使用蒙特卡洛算法模拟三门问题的解

## 什么是三门问题

    三门问题（Monty Hall problem）亦称为蒙提霍尔问题、蒙特霍问题或蒙提霍尔悖论，大致出自美国的电视游戏节目Let's Make a Deal。问题名字来自
    该节目的主持人蒙提·霍尔（Monty Hall）。参赛者会看见三扇关闭了的门，其中一扇的后面有一辆汽车，选中后面有车的那扇门可赢得该汽车，另外两扇门后
    面则各藏有一只山羊。当参赛者选定了一扇门，但未去开启它的时候，节目主持人开启剩下两扇门的其中一扇，露出其中一只山羊。主持人其后会问参赛者要不要
    换另一扇仍然关上的门。问题是：换另一扇门会否增加参赛者赢得汽车的机率？如果严格按照上述的条件，即主持人清楚地知道，自己打开的那扇门后是羊，那么
    答案是会。不换门的话，赢得汽车的几率是1/3。换门的话，赢得汽车的几率是2/3。
    
## 使用蒙特卡洛模拟换门不换门的操作

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
