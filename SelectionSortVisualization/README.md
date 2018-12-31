# 4.1 选择排序算法可视化（Selection Sort Visualization）

    交换最少的排序 

## 选择排序思路

    一、把如下的元素集合使用选择排序的方法从小打到排序
        ⑧ ⑥ ② ③ ① ⑤ ⑦ ④
        
    1. 找到元素①，与index为0的位置元素⑧交换位置
        ① ⑥ ② ③ ⑧ ⑤ ⑦ ④        
    2. 找到元素②，与index为1的位置元素⑥交换位置    
        ① ② ⑥ ③ ⑧ ⑤ ⑦ ④
    3. 找到元素③，与index为2的位置元素⑥交换位置
        ① ② ③ ⑥ ⑧ ⑤ ⑦ ④    
    4. 找到元素④，与index为3的位置元素⑥交换位置
        ① ② ③ ④ ⑧ ⑤ ⑦ ⑥
    5. 找到元素⑤，与index为4的位置元素⑧交换位置
        ① ② ③ ④ ⑤ ⑧ ⑦ ⑥
    6. 找到元素⑥，与index为5的位置元素⑧交换位置
        ① ② ③ ④ ⑤ ⑥ ⑦ ⑧
    7. 找到元素⑦，与index为6的位置元素⑦交换位置
        ① ② ③ ④ ⑤ ⑥ ⑦ ⑧
    8. 找到元素⑧，与index为7的位置元素⑧交换位置
        ① ② ③ ④ ⑤ ⑥ ⑦ ⑧
    
    完成选择排序
    
    二、SelectionSortData.java 可视化排序数据封装，可以通过其构造方法创建一个随机的int数组用于排序，指定数组元素个数及最大值
        
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
        
    三、AlgoFrame.java中初始化无序数组
    
        // 初始化数据随机无序数组
        private SelectionSortData data;
        public void render(SelectionSortData data){
            this.data = data;
            repaint();
        }
        
    四、AlgoFrame.java中具体的绘制代码如下（一个元素绘制一个矩形，高度为数组元素的大小）：
    
        // 具体绘制
        int w = canvasWidth / data.N();
        AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
        for (int i = 0; i < data.N(); i++) {
            AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
        }
        
    五、AlgoVisualizer.java中初始化
    
        private SelectionSortData data;
        
        // 初始化数据（构造方法中指定多少个数）
        data = new SelectionSortData(N, sceneHeight);
        
    六、AlgoVisualizer.java中实现选择排序的动画逻辑
    
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
        
    七、AlgoVisualizer.java
    
        public static void main(String[] args) {
        
            int sceneWidth = 800;
            int sceneHeight = 800;
            int N = 100;    // 设置100个可排序的元素
    
            new AlgoVisualizer(sceneWidth, sceneHeight, N);
        }
        
## 将选择排序可视化生动展示

    为了显示的生动，将已排好序的用红色标注，当前最小值用深蓝色标注，以浅蓝色为动态扫描最小值的情况
    
    1. 在数据层中添加如下三个变量
    
        // [0...orderedIndex)有序索引
        public int orderedIndex = -1;
        // 当前找到的最小元素的索引
        public int currentMinIndex = -1;
        // 当前正在比较的元素的索引
        public int currentCompareIndex = -1;
    
    2. AlgoFrame.java具体的绘制时涉及到数据位置变动所带来的颜色改变
    
        // 具体绘制
        int w = canvasWidth / data.N();
        for (int i = 0; i < data.N(); i++) {
            if (i < data.orderedIndex) {    // 当前元素i为有序索引，设置为红色
                AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
            } else {    // 当前元素i为非有序索引，设置为灰色
                AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);
            }
        
            if (i == data.currentCompareIndex) {    // 当前元素i为正在比较的索引，设置为浅蓝色
                AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
            }
        
            if (i == data.currentMinIndex) {    // 当前元素i为最小值的索引，设置为深蓝色
                AlgoVisHelper.setColor(g2d, AlgoVisHelper.Indigo);
            }
            AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
        }
        
    3. AlgoVisualizer.java中提取动态绘制的方法，动态改动数据的状态值
    
        // 提取动态绘制方法，动态改动数据的状态值
        private void setData(int orderedIndex, int currentCompareIndex, int currentMinIndex) {
            data.orderedIndex = orderedIndex;
            data.currentCompareIndex = currentCompareIndex;
            data.currentMinIndex = currentMinIndex;
        
            frame.render(data);
            AlgoVisHelper.pause(DELAY);
        }
        
    4. AlgoVisualizer.java修改动画逻辑
    
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