import java.awt.BorderLayout;  // 导入布局管理器
import java.awt.EventQueue;    // 导入事件队列处理类，用于在EDT线程中运行GUI代码

import javax.swing.JFrame;     // 导入JFrame类，作为主窗口
import javax.swing.JPanel;     // 导入JPanel类，作为内容面板
import javax.swing.border.EmptyBorder;  // 导入边框类，用于设置面板边距

/**
 * PlaneGame类 - 飞机游戏的主窗口类
 * 继承自JFrame，作为整个应用程序的容器窗口
 */
public class PlaneGame extends JFrame {

	/** 主内容面板，用于放置游戏组件 */
	private JPanel contentPane;

	/**
	 * 程序入口点
	 * 在事件分发线程(EDT)中创建并显示游戏窗口
	 * @param args 命令行参数(未使用)
	 */
	public static void main(String[] args) {
		// invokeLater确保GUI代码在事件分发线程中执行，避免线程安全问题
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaneGame frame = new PlaneGame();  // 创建游戏主窗口
					frame.setVisible(true);             // 显示游戏窗口
				} catch (Exception e) {
					e.printStackTrace();                // 打印异常堆栈信息
				}
			}
		});
	}

	/**
	 * 构造函数 - 创建并初始化游戏窗口
	 * 设置窗口标题、关闭行为、大小和位置等属性
	 * 添加游戏面板到窗口中
	 */
	public PlaneGame() {
		setTitle("\u98DE\u673A\u6E38\u620F");  // 设置窗口标题为"飞机游戏"(使用Unicode编码)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 设置关闭窗口时退出应用程序
		setBounds(100, 100, 679, 776);  // 设置窗口位置(x, y)和大小(width, height)

		// 创建内容面板
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  // 设置内容面板的边距(上、左、下、右)
		setContentPane(contentPane);  // 将内容面板设置为窗口的内容面板
		contentPane.setLayout(null);  // 使用绝对布局(无布局管理器)

		// 创建游戏面板并添加到内容面板
		GamePanel gamePanel = new GamePanel();  // 创建游戏面板(包含游戏的核心逻辑和显示)
		gamePanel.setBounds(0, 0, 482, 720);    // 设置游戏面板的位置和大小
		contentPane.add(gamePanel);             // 将游戏面板添加到内容面板中
	}


}