# 4.1 选择排序算法可视化（Selection Sort Visualization）

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