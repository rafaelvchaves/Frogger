
import java.awt.*;

public class Water{
    int x, y, w, h;
    public Water(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void draw(Graphics2D g2){
        g2.setColor(new Color(39, 8, 179));
        g2.fillRect(x, y, w, h);
    }
    public int getMaxY(){
        return y;
    }
    public int getMinY(){
        return y + h;
    }

}