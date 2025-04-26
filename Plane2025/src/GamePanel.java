import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * GamePanel类 - 飞机游戏的核心游戏面板
 * 继承自JPanel，实现Runnable接口以支持多线程背景滚动
 * 负责游戏背景的绘制和滚动效果
 */
public class GamePanel extends JPanel implements Runnable {

    /**
     * 背景图片0，用于交替滚动显示
     */
    private Image bg0;

    /**
     * 背景图片1，用于交替滚动显示
     */
    private Image bg1;

    /**
     * 背景图片0的Y坐标，控制背景滚动位置
     */
    private int bg0y = 0;

    /**
     * 背景图片1的Y坐标，初始位置在屏幕上方(-480)
     */
    private int bg1y = -480;

    /**
     * 控制背景滚动的线程
     */
    private Thread t;

    /**
     * 飞机对象
     */
    MyPlane myPlane;

    /**
     * 构造函数 - 创建游戏面板并初始化资源
     * 加载背景图片并启动背景滚动线程
     */
    public GamePanel() {
        // 加载背景图片资源
        bg0 = this.getToolkit().getImage(GamePanel.class.getResource("/images/map_0.png"));
        bg1 = this.getToolkit().getImage(GamePanel.class.getResource("/images/map_1.png"));

        myPlane = new MyPlane(150, 380);


        // 创建并启动背景滚动线程
        t = new Thread(this);
        t.start();
    }

    /**
     * 重写paint方法，绘制游戏背景
     * 两张背景图交替绘制，形成无缝滚动效果
     *
     * @param g 图形上下文，用于绘制组件
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);  // 调用父类的paint方法，确保组件正确绘制

        // 绘制两张背景图片，位置由bg0y和bg1y决定
        g.drawImage(bg0, 0, bg0y, this);
        g.drawImage(bg1, 0, bg1y, this);

        myPlane.drawMyplane(g);
    }

    /**
     * 实现Runnable接口的run方法
     * 控制背景图片的滚动效果
     * 不断更新背景图片的Y坐标，并触发重绘
     */
    @Override
    public void run() {
        // 无限循环，持续更新背景位置
        while (true) {
            // 当背景图片0完全滚到屏幕下方，重置到屏幕上方
            if (bg0y >= 480) {
                bg0y = -480;
            }

            // 当背景图片1完全滚到屏幕下方，重置到屏幕上方
            if (bg1y >= 480) {
                bg1y = -480;
            }

            // 更新两张背景图片的Y坐标，向下移动10像素
            bg0y += 10;
            bg1y += 10;

            try {
                // 线程休眠100毫秒，控制滚动速度
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // 处理线程中断异常
                e.printStackTrace();
            }

            // 重绘面板，显示更新后的背景位置
            this.repaint();
        }
    }


    public void mouseMoved(MouseEvent e){
        myPlane.setposition(e.getX(),e.getY());
    }
}