import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    private static int DELAY = 10;
    private AlgoFrame frame;    // 视图
    private SelectionSortData data;

    // 构造方法中指定多少个数
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        data = new SelectionSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection Sort Visualization", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> run()).start();
        });
    }

    // 动画逻辑（选择排序的逻辑代码）
    private void run(){
        frame.render(data);
        AlgoVisHelper.pause(DELAY);

        for (int i = 0; i < data.N(); i++) {
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < data.N(); j++) {
                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j;
                }
            }
            data.swap(i, minIndex);

            frame.render(data);
            AlgoVisHelper.pause(DELAY);
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;    // 设置100个可排序的元素

        new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
