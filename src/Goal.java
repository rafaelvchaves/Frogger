import java.awt.*;

/**
 * Created by student on 2/13/18.
 */
public class Goal {
    int x;
    Color color;
    public Goal(int x, Color color){
        this.x = x;
        this.color = color;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(x, 0, FroggerMain.FRAMEWIDTH/8, 115);
    }
    public boolean contains (int xOther){
        if (xOther > x && xOther < x + FroggerMain.FRAMEWIDTH/8)
            return true;
        return false;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }
}
