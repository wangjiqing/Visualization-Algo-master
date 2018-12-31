import java.awt.*;
import java.util.LinkedList;

// 数据计算
public class MonteCarloPiData {
    private Circle circle;
    private LinkedList<Point> points;
    private int insideCircle = 0;

    public MonteCarloPiData(Circle circle) {
        this.circle = circle;
        points = new LinkedList<>();
    }

    public Circle getCircle() {
        return circle;
    }

    // 获取列表中的某个元素
    public Point getPoint(int i) {
        if (i < 0 || i >= points.size()) {
            throw new IllegalArgumentException("out of bound in getPoint!");
        }
        return points.get(i);
    }

    // 获取所有点的数量（估算正方形的面积）
    public int getPointsNumber() {
        return points.size();
    }

    // 添加一个点
    public void addPoint(Point p) {
        points.add(p);
        if (circle.contain(p)) {
            insideCircle++;
        }
    }

    // 估算Π的值
    public double estimatePi() {
        if (points.size() == 0) {
            return 0.0d;
        }
        int circleArea = insideCircle;
        int squareArea = points.size();
        return (double) circleArea * 4 / squareArea;
    }
}
