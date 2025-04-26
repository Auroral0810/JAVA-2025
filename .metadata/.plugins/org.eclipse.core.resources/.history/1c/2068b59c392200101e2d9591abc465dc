import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {

    private final int MAX = 200;
    private int[] x1 = new int[MAX], y1 = new int[MAX]; // 蛇1
    private int[] x2 = new int[MAX], y2 = new int[MAX]; // 蛇2
    private int len1 = 3, len2 = 3;
    private int dir1 = 1, dir2 = 3;

    private int foodX, foodY;
    private Random rand = new Random();
    private Timer timer;

    private int score1 = 0, score2 = 0;
    private boolean cheatMode = false;
    private boolean player2Enabled = false; // 控制玩家2是否启用

    public GamePanel() {
        this.setFocusable(true);
        this.addKeyListener(this);
        initSnake();
        genFood();

        timer = new Timer(100, e -> {
            moveSnake();
            repaint();
        });
        timer.start();
    }

    private void initSnake() {
        for (int i = 0; i < len1; i++) {
            x1[i] = 100 - i * 25;
            y1[i] = 100;
        }
        for (int i = 0; i < len2; i++) {
            x2[i] = 600 + i * 25;
            y2[i] = 400;
        }
    }

    public void enableCheat() {
        cheatMode = true;
    }

    private void genFood() {
        foodX = rand.nextInt(32) * 25;
        foodY = rand.nextInt(24) * 25;
    }

    private void moveSnake() {
        moveBody(x1, y1, len1, dir1);
        if (player2Enabled) {
            moveBody(x2, y2, len2, dir2);
            if (cheatMode) {
                autoChaseFood();
            }
        }

        // 吃食物
        if (x1[0] == foodX && y1[0] == foodY) {
            len1++;
            score1 += 10;
            genFood();
        } else if (x2[0] == foodX && y2[0] == foodY) {
            len2++;
            score2 += 10;
            genFood();
        }

        checkDeath();
    }

    private void moveBody(int[] x, int[] y, int len, int dir) {
        for (int i = len - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (dir) {
            case 0:
                y[0] -= 25;
                break;
            case 1:
                x[0] += 25;
                break;
            case 2:
                y[0] += 25;
                break;
            case 3:
                x[0] -= 25;
                break;
        }
    }

    private void autoChaseFood() {
        // 优先按照水平方向和垂直方向追食物，同时避免碰撞墙
        if (Math.abs(x2[0] - foodX) > Math.abs(y2[0] - foodY)) {
            if (x2[0] < foodX && dir2 != 3 && x2[0] + 25 < 800) dir2 = 1;  // 向右，检查是否会超出边界
            else if (x2[0] > foodX && dir2 != 1 && x2[0] - 25 >= 0) dir2 = 3;  // 向左，检查是否会超出边界
        } else {
            if (y2[0] < foodY && dir2 != 0 && y2[0] + 25 < 600) dir2 = 2;  // 向下，检查是否会超出边界
            else if (y2[0] > foodY && dir2 != 2 && y2[0] - 25 >= 0) dir2 = 0;  // 向上，检查是否会超出边界
        }
    }


    private void checkDeath() {
        if (outOfBounds(x1[0], y1[0]) || (player2Enabled && outOfBounds(x2[0], y2[0]))) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "撞墙了，游戏结束！");
        }

        for (int i = 1; i < len1; i++) {
            if (x1[0] == x1[i] && y1[0] == y1[i]) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "蛇1撞到自己了！");
            }
        }
        if (player2Enabled) {
            for (int i = 1; i < len2; i++) {
                if (x2[0] == x2[i] && y2[0] == y2[i]) {
                    timer.stop();
                    JOptionPane.showMessageDialog(this, "蛇2撞到自己了！");
                }
            }

            for (int i = 0; i < len2; i++) {
                if (x1[0] == x2[i] && y1[0] == y2[i]) {
                    timer.stop();
                    JOptionPane.showMessageDialog(this, "蛇1撞到蛇2了！");
                }
            }
            for (int i = 0; i < len1; i++) {
                if (x2[0] == x1[i] && y2[0] == y1[i]) {
                    timer.stop();
                    JOptionPane.showMessageDialog(this, "蛇2撞到蛇1了！");
                }
            }
        }
    }

    private boolean outOfBounds(int x, int y) {
        return x < 0 || x > 800 || y < 0 || y > 600;
    }

    // 修改 resetGame 方法以正确重置游戏并能接收键盘输入
    private void resetGame() {
        // 重置蛇的位置和长度
        len1 = 3;
        len2 = 3;
        dir1 = 1;
        dir2 = 3;

        for (int i = 0; i < len1; i++) {
            x1[i] = 100 - i * 25;
            y1[i] = 100;
        }
        for (int i = 0; i < len2; i++) {
            x2[i] = 600 + i * 25;
            y2[i] = 400;
        }

        // 重置分数
        score1 = 0;
        score2 = 0;

        // 重新生成食物
        genFood();

        // 重启计时器
        timer.restart();

        // 确保面板获取焦点，以便接收键盘输入
        this.requestFocusInWindow();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.LIGHT_GRAY);
        for (int x = 0; x <= 800; x += 25) g.drawLine(x, 0, x, 600);
        for (int y = 0; y <= 600; y += 25) g.drawLine(0, y, 800, y);

        g.setColor(Color.ORANGE);
        g.fillOval(foodX, foodY, 25, 25);

        for (int i = 0; i < len1; i++) {
            g.setColor(i == 0 ? Color.RED : Color.GREEN);
            g.fillRect(x1[i], y1[i], 25, 25);
        }

        if (player2Enabled) {
            for (int i = 0; i < len2; i++) {
                g.setColor(i == 0 ? Color.BLUE : Color.CYAN);
                g.fillRect(x2[i], y2[i], 25, 25);
            }
        }

        g.setColor(Color.BLACK);
        g.drawString("蛇1 得分：" + score1, 10, 15);
        if (player2Enabled) {
            g.drawString("蛇2 得分：" + score2, 700, 15);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_UP && dir1 != 2) dir1 = 0;
        if (k == KeyEvent.VK_RIGHT && dir1 != 3) dir1 = 1;
        if (k == KeyEvent.VK_DOWN && dir1 != 0) dir1 = 2;
        if (k == KeyEvent.VK_LEFT && dir1 != 1) dir1 = 3;

        if (player2Enabled) {
            if (k == KeyEvent.VK_W && dir2 != 2) dir2 = 0;
            if (k == KeyEvent.VK_D && dir2 != 3) dir2 = 1;
            if (k == KeyEvent.VK_S && dir2 != 0) dir2 = 2;
            if (k == KeyEvent.VK_A && dir2 != 1) dir2 = 3;
        }

        // 按下空格键重新开始游戏
        if (k == KeyEvent.VK_SPACE) {
            resetGame();
        }

        // 按下E键启用蛇2自动吃食物功能
        if (k == KeyEvent.VK_E) {
            enableCheat();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}

    @Override public void keyTyped(KeyEvent e) {}

    // 主方法创建游戏窗口
    public static void main(String[] args) {
        JFrame frame = new JFrame("双人贪吃蛇大战 v1.0");
        GamePanel panel = new GamePanel();

        frame.setSize(850, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startGameBtn = new JButton("开始游戏");
        JButton enablePlayer2Btn = new JButton("启用玩家2");

        startGameBtn.addActionListener(e -> {
            panel.resetGame();  // 点击开始游戏时重置游戏状态
            panel.requestFocusInWindow();  // 确保面板重新能接收键盘输入
        });
        
        enablePlayer2Btn.addActionListener(e -> {
            panel.player2Enabled = !panel.player2Enabled;
            if (panel.player2Enabled) {
                enablePlayer2Btn.setText("禁用玩家2");
            } else {
                enablePlayer2Btn.setText("启用玩家2");
            }
        });

        buttonPanel.add(startGameBtn);
        buttonPanel.add(enablePlayer2Btn);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}