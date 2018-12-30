import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    private static int DELAY = 1;   // 画面停留事件间隔
    private int[] money;        // 构造钱的持有者对象
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight){

        // 初始化100个人
        money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100; // 每个人初始的时候都有100块钱
        }

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("MoneyProgram", sceneWidth, sceneHeight);
            new Thread(() -> run()).start();
        });
    }

    // 动画逻辑
    private void run(){
        while (true) {
            frame.render(money);    // 创建
            AlgoVisHelper.pause(DELAY); // 停留事件
            // 随机分配，将index为i的钱分给index为j的人
            for (int i = 0; i < money.length; i++) {
                if (money[i] > 0) {
                    int j = (int) (Math.random() * money.length);
                    money[i] -= 1;
                    money[j] += 1;
                }
            }
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 800;

        new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
