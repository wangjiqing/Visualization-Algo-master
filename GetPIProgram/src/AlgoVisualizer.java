import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private MonteCarloPiData data;

    private AlgoFrame frame;    // 视图
    private int N;  // 打点的总数

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        if (sceneWidth != sceneHeight) {
            throw new IllegalArgumentException("This demo must be run in a square!");
        }

        // 初始化数据
        Circle circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneWidth / 2);
        data = new MonteCarloPiData(circle);
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
            if (i % 100 == 0) {     // 一次打100个点
                frame.render(data);
                AlgoVisHelper.pause(DELAY);
                System.out.println(data.estimatePi());
            }

            int x = (int) (Math.random() * frame.getCanvasWidth());
            int y = (int) (Math.random() * frame.getCanvasHeight());

            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10000;

        new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
