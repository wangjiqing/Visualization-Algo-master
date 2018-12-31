public class SelectionSortData {

    private int[] numbers;

    public SelectionSortData(int N, int randomBound) {
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
        }
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
