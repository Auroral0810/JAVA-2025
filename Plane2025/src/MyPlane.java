import java.awt.*;

public class MyPlane {

    int x, y;

    int myplanepicid = 0;
    Image[] myplanepic;

    public MyPlane(int x, int y) {
        this.x = x;
        this.y = y;

        myplanepic = new Image[6];
        for (int i = 0; i < myplanepic.length; i++) {
            myplanepic[i] = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/plan_" + i + ".png"));
        }
    }

    void drawMyplane(Graphics g){
        if(myplanepicid == 6 ) myplanepicid = 0;
        g.drawImage(myplanepic[myplanepicid++],x,y,null);
    }

    void setposition(int x,int y){
        this.x = x;
        this.y = y;
    }
}
