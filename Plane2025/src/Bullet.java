import java.awt.*;

public class Bullet {
    int x,y;
    Image[] bulletpic;
    int bulletpicid = 0;
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;

        bulletpic = new Image[4];
        for (int i = 0; i < bulletpic.length; i++) {
            bulletpic[i] = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/bullet_" + i + ".png"));
        }
    }

    void drawBullet(Graphics g){
        if(bulletpicid == 6 ) bulletpicid = 0;
        g.drawImage(bulletpic[bulletpicid++],x,y,null);
    }

    void setposition(int x,int y){
        this.x = x;
        this.y = y;
    }

}
