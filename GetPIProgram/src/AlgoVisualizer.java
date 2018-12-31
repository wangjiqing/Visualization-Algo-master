import java.awt.*;
import java.util.LinkedList;

public class AlgoVisualizer {

    private static int DELAY = 40;
    private Circle circle;  // 实例化一个圆
    private LinkedList<Point> points;   // 实例化点的集合
    private AlgoFrame frame;    // 视图
    private int N;  // 打点的总数

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        if (sceneWidth != sceneHeight) {
            throw new IllegalArgumentException("This demo must be run in a square!");
        }

        // 初始化数据
        circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneWidth / 2);
        points = new LinkedList<>();
        this.N = N;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("GetPIProgram", sceneWidth, sceneHeight);
            new Thread(() -> run()).start();
        });
    }

    // 动画逻辑
    private void run(){
        for (int i = 0; i < N; i++) {
            frame.render(circle, points);
            AlgoVisHelper.pause(DELAY);

            int x = (int) (Math.random() * frame.getCanvasWidth());
            int y = (int) (Math.random() * frame.getCanvasHeight());

            Point p = new Point(x, y);
            points.add(p);
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10000;

        new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
