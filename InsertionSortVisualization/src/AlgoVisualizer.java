import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    private static int DELAY = 10;
    private InsertionSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, InsertionSortData.Type dateType){

        // 初始化数据
        data = new InsertionSortData(N, sceneHeight, dateType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> run()).start();
        });
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this(sceneWidth, sceneHeight, N, InsertionSortData.Type.Default);
    }

    // 动画逻辑（插入排序逻辑）
    private void run(){
        // 初始化没有排序
        setData(0, -1);

        for (int i = 0; i < data.N(); i++) {
            // 已经排序的和正要排序的索引都为i
            setData(i, i);
            for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--) {
                data.swap(j, j - 1);
                // 已经排序的索引为i+1，正要排序的索引为j-1
                setData(i + 1, j - 1);
            }
        }
        // 最终排好序
        setData(data.N(), -1);
    }

    // 提取动态绘制方法，动态改动数据的状态值
    private void setData(int orderedIndex, int currentIndex) {
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;    // 设置100个可排序的元素
        // 构造一个普通的数组进行插入排序 O(n²)
        new AlgoVisualizer(sceneWidth, sceneHeight, N);
        // 构造一个近乎有序的数组进行插入排序    O(n)
        new AlgoVisualizer(sceneWidth, sceneHeight, N, InsertionSortData.Type.NearlyOrdered);
    }
}
