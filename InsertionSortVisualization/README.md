# 4.2 插入排序算法可视化（Insertion Sort Visualization）

    插入算法时O(n²)级别的算法

## 插入排序的思路

    一、把如下的元素集合使用插入排序的方法从小打到排序
            ⑧ ⑥ ② ③ ① ⑤ ⑦ ④
            
       1. 找到索引为0的位置⑧
       2. 找到索引为1的位置元素⑥，比较前一位元素，比它小，则交换位置
           ⑥ ⑧ ② ③ ① ⑤ ⑦ ④
       3. 找到索引为2的位置元素②，依次比较前一位元素，比它小，则依次交换位置
           ⑥ ② ⑧ ③ ① ⑤ ⑦ ④
           ② ⑥ ⑧ ③ ① ⑤ ⑦ ④
       4. 依照上面的规则，元素比较过程如下：
           ② ⑥ ③ ⑧ ① ⑤ ⑦ ④
           ② ③ ⑥ ⑧ ① ⑤ ⑦ ④
           
           ② ③ ⑥ ① ⑧ ⑤ ⑦ ④
           ② ③ ① ⑥ ⑧ ⑤ ⑦ ④
           ② ① ③ ⑥ ⑧ ⑤ ⑦ ④
           ① ② ③ ⑥ ⑧ ⑤ ⑦ ④
           
           ① ② ③ ⑥ ⑤ ⑧ ⑦ ④
           ① ② ③ ⑤ ⑥ ⑧ ⑦ ④
           
           ① ② ③ ⑤ ⑥ ⑦ ⑧ ④
           
           ① ② ③ ⑤ ⑥ ⑦ ④ ⑧ 
           ① ② ③ ⑤ ⑥ ④ ⑦ ⑧ 
           ① ② ③ ⑤ ④ ⑥ ⑦ ⑧
           ① ② ③ ④ ⑤ ⑥ ⑦ ⑧
    
    二、可视化实现
    
        见代码，这里不再赘述（同选择排序类似的代码编写逻辑）
        
        排序代码的主要逻辑：
        
            for (int i = 0; i < data.N(); i++) {
                for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--) {
                    data.swap(j, j - 1);
                }
            }
            
    三、插入排序的优化
    
        可以不交换数据而使用数据移动的方式  
        
        伪代码：
        
            int n = arr.length
            for (int i = 0; i < n; i++) {
                // 寻找元素arr[i]合适的插入位置
                Comparable e = arr[i];
                int j = i;
                for(; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[j] = e;
            } 
            
    四、当数组近乎有序的时候，插入排序算法“进化”为O(n)的算法。同样时O(n²)的算法，但是2n²和12n²差距很大。在n比较小的时候，插入排序比O(nlogn)
        的排序算法有优势。插入排序经常用作高级排序算法在处理到小样本时的一个优化
        
    五、对我们的可视化代码添加一个进入有序数组测试代码
    
        1. InsertionSortData.java 添加一个枚举类型判断
        
            public enum Type {
                Default,        // 默认数组
                NearlyOrdered   // 近乎有序
            }
        
        2. InsertionSortData.java 构造函数
        
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
            
        3. AlgoVisualizer.java中构造函数
        
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
            
        4. main函数
        
            public static void main(String[] args) {
                int sceneWidth = 800;
                int sceneHeight = 800;
                int N = 100;    // 设置100个可排序的元素
                // 构造一个普通的数组进行插入排序 O(n²)
                new AlgoVisualizer(sceneWidth, sceneHeight, N);
                // 构造一个近乎有序的数组进行插入排序    O(n)
                new AlgoVisualizer(sceneWidth, sceneHeight, N, InsertionSortData.Type.NearlyOrdered);
            }