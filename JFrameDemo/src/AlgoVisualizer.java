import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Controller层
public class AlgoVisualizer {

    private Circle[] circles;
    private ALgoFrame frame;
    private boolean isAnimated = true; // 控制动画启停

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < circles.length; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            frame = new ALgoFrame("Welcome", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());    // 绑定键盘监听器
            frame.addMouseListener(new AlogMouseListener());    // 绑定鼠标监听器
            new Thread(() -> run()).start();
        });
    }

    // 动画逻辑
    private void run() {
        while (true) {
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(15); // 暂停5毫秒
            // 更新数据
            for (Circle circle : circles) {
                if (isAnimated) {
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
                }
            }
        }
    }

    // 键盘监听器
    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    // 鼠标监听器
    private class AlogMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            event.translatePoint(0,
                    - (frame.getBounds().height - frame.getCanvasHeight()));

            for (Circle circle : circles) {
                if (circle.contain(event.getPoint())) {
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }

    // 方法入口，绘制一个800*800的frame，里面有10个动态的圆，可以通过鼠标左键单机变化颜色，可以通过空格键控制动画启停
    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;
        new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
