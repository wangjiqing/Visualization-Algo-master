import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private static int DELAY = 50;
    private MergeSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        data = new MergeSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Merge Sort Visualization", sceneWidth, sceneHeight);
            new Thread(() -> run()).start();
        });
    }

    // 动画逻辑
    private void run(){
        setData(-1, -1, -1);
        mergeSort(0, data.N() - 1);
        setData(0, data.N() - 1, data.N() - 1);
    }

    private void mergeSort(int l, int r) {
        if (l >= r) {
            return;
        }

        setData(l, r, -1);

        int mid = (l + r) / 2;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(data.numbers, l, r + 1);

        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid+1;
        for(int k = l ; k <= r; k++) {

            if(i > mid) {  // 如果左半部分元素已经全部处理完毕
                data.numbers[k] = aux[j-l];
                j++;
            } else if(j > r) {   // 如果右半部分元素已经全部处理完毕
                data.numbers[k] = aux[i-l];
                i++;
            } else if(aux[i-l] < aux[j-l]) {  // 左半部分所指元素 < 右半部分所指元素
                data.numbers[k] = aux[i-l];
                i++;
            } else {  // 左半部分所指元素 >= 右半部分所指元素
                data.numbers[k] = aux[j-l];
                j++;
            }
            setData(l, r, k);
        }
    }

    private void setData(int l, int r, int mergeIndex) {
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
