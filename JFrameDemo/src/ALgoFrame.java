import javax.swing.*;
import java.awt.*;

public class ALgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public ALgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();   // 创建JPanel画布
        setContentPane(canvas);     // 渲染到JFrame
        pack();     // 调整JFrame大小同JPanel大小适应

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public ALgoFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private Circle[] circles;
    public void render(Circle[] circles) {
        this.circles = circles;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // 具体绘制
            AlgoVisHelper.setStrokeWidth(g2d, 1);
            AlgoVisHelper.setColor(g2d, Color.RED);
            for (Circle circle : circles) {
                if (!circle.isFilled) {  // 如果是个实心圆，就变换成空心圆
                    AlgoVisHelper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
                } else {    // 如果是个空心圆，就变换成实心圆
                    AlgoVisHelper.fillCircle(g2d, circle.x, circle.y, circle.getR());
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            // 设置JPanel大小
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
