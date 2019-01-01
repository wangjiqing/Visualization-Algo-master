import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private QuickSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, QuickSortData.Type dataType) {

        // 初始化数据
        data = new QuickSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> run()).start();
        });
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this(sceneWidth, sceneHeight, N, QuickSortData.Type.Default);
    }

    // 动画逻辑
    private void run(){
        setData(-1, -1, -1, -1, -1);
        quickSort(0, data.N() - 1);
        setData(-1, -1, -1, -1, -1);
    }

    private void quickSort(int l, int r) {
//        if (l >= r) {
//            return;
//        }

        if (l > r) {
            return;
        }

        if (l == r) {
            setData(l, r, l, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1);

        int p = partition(l, r);
        quickSort(l, p - 1);
        quickSort(p + 1, r);
    }

    private int partition(int l, int r) {
        // 解决近乎有序时算法退化为O(n²)的问题i
        int p = (int) (Math.random() * (r - l + 1)) + l;
        setData(l, r, -1, p, -1);
        data.swap(l, p);

        int v = data.get(l);
        setData(l, r, -1, l, -1);

        int j = l;  // arr[l+1...j] < v ; arr[j+1...i) > v
        for (int i = l + 1; i <= r; i++) {
            setData(l, r, -1, l, i);
            if (data.get(i) < v) {
                j++;
                data.swap(j, i);
                setData(l, r, -1, l, i);
            }
        }

        data.swap(l, j);
        setData(l, r, j, -1, -1);
        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curElement) {
        data.l = l;
        data.r = r;
        if (fixedPivot != -1) {
            data.fixedPivots[fixedPivot] = true;
        }
        data.curPivot = curPivot;
        data.curElement = curElement;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        // 完全随机数据排序
        new AlgoVisualizer(sceneWidth, sceneHeight, N);
        // 近乎有序数据排序
        new AlgoVisualizer(sceneWidth, sceneHeight, N, QuickSortData.Type.NearlyOrdered);
        // 完全相等数据排序（非常不建议用此方法排序）
        new AlgoVisualizer(sceneWidth, sceneHeight, N, QuickSortData.Type.Identical);
    }
}
