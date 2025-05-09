import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DdsGame extends JFrame implements Runnable,MouseListener {

    private JLabel[] lm;
    private JPanel contentPane;
    private Thread t;
    private JLabel lscore;
    private int score=0;
    private Cursor c1,c2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DdsGame frame = new DdsGame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public DdsGame() {
        setResizable(false);
        setTitle("\u6253\u5730\u9F20\u6E38\u620F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        
        JMenuBar gameMenu = new JMenuBar();
        gameMenu.setToolTipText("");
        setJMenuBar(gameMenu);
        
        JMenu menu = new JMenu("\u6E38\u620F\u63A7\u5236");
        gameMenu.add(menu);
        
        JMenuItem mitstart = new JMenuItem("\u5F00\u59CB");
        mitstart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		t.start();
        	}
        });
        menu.add(mitstart);
        
        JMenuItem mitcontinue = new JMenuItem("\u7EE7\u7EED");
        menu.add(mitcontinue);
        
        JMenuItem mitpause = new JMenuItem("\u6682\u505C");
        menu.add(mitpause);
        
        JMenuItem mitexit = new JMenuItem("\u9000\u51FA");
        menu.add(mitexit);

        JMenu menu_1 = new JMenu("\u80CC\u666F\u97F3\u4E50");
        gameMenu.add(menu_1);

        JMenu menu_2 = new JMenu("\u5E2E\u52A9");
        gameMenu.add(menu_2);
        
        JMenuItem menuItem = new JMenuItem("\u82F1\u96C4\u699C");
        menu_2.add(menuItem);
        
        JMenuItem mitabout = new JMenuItem("\u5173\u4E8E");
        mitabout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MyAbout myabout = new MyAbout();
        		myabout.setModal(true);
        		myabout.setLocationRelativeTo(null);
        		myabout.setVisible(true);
        	}
        });
        menu_2.add(mitabout);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lm0 = new JLabel("");
        lm0.setIcon(new ImageIcon(DdsGame.class.getResource("/images/mouse.png")));
        lm0.setBounds(236, 227, 72, 67);
        contentPane.add(lm0);
        this.setLocationRelativeTo(null);

        lm=new JLabel[8];
        lm[1]=lm0;

        JLabel lm1 = new JLabel("");
        lm1.setIcon(new ImageIcon(DdsGame.class.getResource("/images/mouse.png")));
        lm1.setBounds(332, 227, 72, 67);
        contentPane.add(lm1);

        JLabel lm2 = new JLabel("");
        lm2.setIcon(new ImageIcon(DdsGame.class.getResource("/images/mouse.png")));
        lm2.setBounds(257, 276, 72, 67);
        contentPane.add(lm2);

        JLabel lm3 = new JLabel("");
        lm3.setIcon(new ImageIcon(DdsGame.class.getResource("/images/mouse.png")));
        lm3.setBounds(390, 276, 72, 67);
        contentPane.add(lm3);

        JLabel lm4 = new JLabel("");
        lm4.setIcon(new ImageIcon(DdsGame.class.getResource("/images/mouse.png")));
        lm4.setBounds(94, 303, 72, 67);
        contentPane.add(lm4);

        JLabel lm5 = new JLabel("");
        lm5.setIcon(new ImageIcon(DdsGame.class.getResource("/images/mouse.png")));
        lm5.setBounds(190, 332, 72, 67);
        contentPane.add(lm5);

        JLabel lm6 = new JLabel("");
        lm6.setIcon(new ImageIcon(DdsGame.class.getResource("/images/mouse.png")));
        lm6.setBounds(364, 343, 72, 67);
        contentPane.add(lm6);

        JLabel lm7 = new JLabel("");
        lm7.setIcon(new ImageIcon(DdsGame.class.getResource("/images/mouse.png")));
        lm7.setBounds(236, 385, 72, 67);
        contentPane.add(lm7);
        
        lscore = new JLabel("\u4F60\u7684\u5206\u6570\u662F\uFF1A0");
        lscore.setBounds(315, 45, 131, 18);
        contentPane.add(lscore);

        JLabel lback = new JLabel("");
        lback.setIcon(new ImageIcon(DdsGame.class.getResource("/images/background.jpg")));
        lback.setBounds(0, 0, 494, 465);
        contentPane.add(lback);

        lm=new JLabel[8];
        lm[0]=lm0;
        lm[1]=lm1;
        lm[2]=lm2;
        lm[3]=lm3;
        lm[4]=lm4;
        lm[5]=lm5;
        lm[6]=lm6;
        lm[7]=lm7;
        
        
        c1=Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(DdsGame.class.getResource("/images/icon.png")), new Point(20,20), "c1");
        c2=Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(DdsGame.class.getResource("/images/icon1.png")), new Point(20,20), "c2");
        
        for(int i=0;i<lm.length;i++){
            lm[i].setVisible(false);
            lm[i].addMouseListener(this);
        }
        t=new Thread(this);
        //t.start();
    }
    @Override
    public void run() {
        int index;
        int lastIndex = -1; // 记录上一次出现的地鼠编号，初始化为-1
        while (true) {
            do {
                index = (int)(Math.random() * 8);
            } while (index == lastIndex); // 避免与上一次一样的地鼠出现
            lastIndex = index; // 更新上一次的index
            lm[index].setVisible(true);
            try {
                Thread.sleep(1000); // 停留 1 秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lm[index].setVisible(false);
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < lm.length; i++) {
	        if (e.getSource() == lm[i] && lm[i].isVisible()) {
	            score += 1; // 每次点击加1分
	            lscore.setText("你的分数是：" + score + "!");
	            lm[i].setVisible(false); // 点中了就让它消失
	        }
	    }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setCursor(c1);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setCursor(c2);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setCursor(c1);
	}
}