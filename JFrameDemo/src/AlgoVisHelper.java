import java.awt.*;
import java.awt.geom.Ellipse2D;

public class AlgoVisHelper {

    private AlgoVisHelper() {

    }

    /**
     * 设置笔画宽度
     * @param g2d
     * @param w
     */
    public static void setStrokeWidth(Graphics2D g2d, int w) {
        g2d.setStroke(new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));   // 设置笔画柔和度
    }

    /**
     * 绘制一个空心圆
     * @param g2d
     * @param x 圆点 x
     * @param y 圆点 y
     * @param r 半径
     */
    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circle);
    }

    /**
     * 绘制一个实心圆
     * @param g2d
     * @param x 圆点 x
     * @param y 圆点 y
     * @param r 半径
     */
    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circle);
    }

    /**
     * 设置线条颜色
     * @param g2d
     * @param color
     */
    public static void setColor(Graphics2D g2d, Color color) {
        g2d.setColor(color);
    }

    /**
     * 暂停一段时间
     * @param t
     */
    public static void pause(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
