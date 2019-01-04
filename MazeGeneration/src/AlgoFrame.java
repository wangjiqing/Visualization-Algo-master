import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title){
        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    private MazeData data;
    public void render(MazeData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth / data.M();
            int h = canvasHeight / data.N();
            for (int i = 0; i < data.N(); i++) {
                for (int j = 0; j < data.M(); j++) {
                    if (data.isMist[i][j]) {    // 设置隐藏我们的迷宫模板
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Black);
                    } else if (data.maze[i][j] == MazeData.WALL) {  // 如果为#，为墙，设置为浅蓝色
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                    } else {    // 空白处设置为白色
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.White);
                    }

                    if (data.path[i][j]) {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Yellow);
                    }

                    AlgoVisHelper.fillRectangle(g2d, j * w, i * h, w, h);
                }
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


