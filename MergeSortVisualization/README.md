# 4.3 归并排序算法可视化（Merge Sort Visualization）

## 实现归并排序的思路

    对元素进行分组排序，使用递归调用
    
        ⑧ ⑥ ② ③ ① ⑤ ⑦ ④
        
        1. ⑧ ⑥ ② ③ | ① ⑤ ⑦ ④
        
        2. ⑧ ⑥ | ② ③ | ① ⑤ | ⑦ ④
        
        3. ⑧ | ⑥ | ② | ③ | ① | ⑤ | ⑦ | ④
        
        4. ⑥ ⑧ | ② ③ | ① ⑤ | ④ ⑦
        
        5. ② ③ ⑥ ⑧ | ① ④ ⑤ ⑦
        
        6. ① ② ③ ④ ⑤ ⑥ ⑦ ⑧
        
## 实例排序代码
    
  [MergeSort.java](https://github.com/wangjiqing/Visualization-Algo-master/blob/master/MergeSortVisualization/src/MergeSort.java)


## 实现自顶向下的归并排序代码

  [AlgoVisualizer.java]()