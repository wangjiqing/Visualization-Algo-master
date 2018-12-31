import java.util.Arrays;

public class InsertionSortData {

    public enum Type {
        Default,        // 默认数组
        NearlyOrdered   // 近乎有序
    }

    private int[] numbers;
    // [0...orderedIndex)有序
    public int orderedIndex = -1;
    // 当前正在处理的元素
    public int currentIndex = -1;

    public InsertionSortData(int N, int randomBound, Type dataType) {
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
        }

        if (dataType == Type.NearlyOrdered) {
            Arrays.sort(numbers);
            int swapTime = (int) (0.02 * N);
            for (int i = 0; i < swapTime; i++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                swap(a, b);
            }
        }
    }

    public InsertionSortData(int N, int randomBound) {
        this(N, randomBound, Type.Default);
    }

    // 数组长度
    public int N() {
        return numbers.length;
    }

    // 取出指定位置的元素
    public int get(int index) {
        if (index < 0 || index >= numbers.length) {
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        }
        return numbers[index];
    }

    // 交换元素
    public void swap(int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
