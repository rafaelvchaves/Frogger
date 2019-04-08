import java.awt.*;

/**
 * Created by student on 2/12/18.
 */
public class Log extends Sprite{
    private int x, y, size;

    public Log(int x, int y, int direction, int size, int speed){
        super(x, y, direction);
        setDir(direction);
        setSpeed(3);
        this.size = size;
        if (size == 1)
            setPic("logShort.png", getDir());
        else if (size == 2)
            setPic("logMedium.png", getDir());
        else if (size == 3)
            setPic("logLarge.png", getDir());
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    @Override
    public Point getLoc() {
        return super.getLoc();
    }
    @Override
    public void update() {
        super.update();
        if (getLoc().x > FroggerMain.FRAMEWIDTH) {
            setLoc(new Point(0, getLoc().y));
        }
        if (getLoc().x + 85< 0)
            setLoc(new Point(FroggerMain.FRAMEWIDTH, getLoc().y));
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
