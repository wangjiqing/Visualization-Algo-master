import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 40;
    private AlgoFrame frame;    // 视图
    private SelectionSortData data;

    // 构造方法中指定多少个数
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        data = new SelectionSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection Sort Visualization", sceneWidth, sceneHeight);
            new Thread(() -> run()).start();
        });
    }

    // 动画逻辑（选择排序的逻辑代码）
    private void run(){
        // 初始的时候没有元素有序 0，没有比较任何元素 -1，没有找到最小元素 -1
        setData(0, -1, -1);

        for (int i = 0; i < data.N(); i++) {
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            // 有序元素为i, 没有当前比较的索引，找到了最小的元素minIndex
            setData(i, -1, minIndex);
            for (int j = i + 1; j < data.N(); j++) {
                // 有序元素为i,当前比较元素j,最小元素为minIndex
                setData(i, j, minIndex);    //
                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j;
                    // 有序元素为i,当前比较元素j,最小元素为minIndex
                    setData(i, j, minIndex);    //
                }
            }
            data.swap(i, minIndex);
            // 有序元素为i + 1,没有当前比较元素,没有最小元素
            setData(i + 1, -1, -1); //
        }
        // 全部比较完成，没有比较任何元素 -1，没有找到最小元素 -1
        setData(data.N(), -1, -1);
    }

    // 提取动态绘制方法，动态改动数据的状态值
    private void setData(int orderedIndex, int currentCompareIndex, int currentMinIndex) {
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;    // 设置100个可排序的元素

        new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
