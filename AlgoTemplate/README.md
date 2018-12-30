# 2.2 绘制可视化算法使用的JFrame Template

# 模板简介

    整理采用MVC形式

## AlogFrame.java

    视图层（V）：JFrame的子类，构造函数中构造了自己的画布，可以设置自己的数据，这里使用Object，
    具体的实现需要设置相应的数据类型；render中调用repaint()，进而调用JPanel中的PaintComponent,
    进而绘制我们需要的图形
    
    数据层（M）：这里没有指定，因为不同的应用数据不同
    
## AlgoVisualzer.java

    控制层（V）: 构造函数中初始化数据（需改动）和视图（需要确定是否添加鼠标键盘事件）
    通过run()方法添加动画逻辑；需要鼠标键盘交互，直接使用AlgoKeyListener和AlogMouseListener
    添加具体的代码即可
    
    main方法中指定调用
    
## AlogVisHelper.java

    画布绘制辅助类
      1. 常量颜色，直接受用AlgoVisHelper.COLOR即可
      2. 绘制实心圆/空心圆
      3. 绘制实心矩形/空心矩形（左上角坐标/宽/高）
      4. 读取工程中的图像放在窗口中（左上角坐标）
      5. 指定坐标中心绘制一个字符串
      ... 
      (有兴趣可以通过查询Java API改造/添加其中的方法)