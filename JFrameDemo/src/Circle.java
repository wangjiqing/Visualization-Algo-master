import java.awt.*;

public class Circle {
    /**
     * 圆的x,y坐标
     */
    public int x, y;
    /**
     * 圆的半径
     */
    private int r;
    /**
     * 圆的运行速度
     */
    public int vx, vy;

    /**
     * 给定的圆是否是实心圆，默认false
     */
    public boolean isFilled = false;

    /**
     * 构造方法
     * @param x
     * @param y
     * @param r
     * @param vx
     * @param vy
     */
    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * 获取圆的半径
     * @return
     */
    public int getR() {
        return r;
    }

    /**
     * 移动
     * @param minx
     * @param miny
     * @param maxx
     * @param maxy
     */
    public void move(int minx, int miny, int maxx, int maxy) {
        x += vx;
        y += vy;

        checkCollision(minx, miny, maxx, maxy);
    }

    /**
     * 边界碰撞检测
     * @param minx
     * @param miny
     * @param maxx
     * @param maxy
     */
    private void checkCollision(int minx, int miny, int maxx, int maxy) {
        if (x - r < minx) {
            x = r;
            vx = -vx;
        }
        if (x + r >= maxx) {
            x = maxx - r;
            vx = -vx;
        }
        if (y - r < miny) {
            y = r;
            vy = -vy;
        }
        if (y + r >= maxy) {
            y = maxy - r;
            vy = -vy;
        }
    }

    /**
     * 判断一个点到圆心的距离是否小于r
     * @param p
     * @return
     */
    public boolean contain(Point p) {
        return (x -p.x) * (x - p.x) + (y - p.y) * (y - p.y) <= r * r;
    }
}
