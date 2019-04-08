import java.awt.*;

/**
 * Created by student on 2/8/18.
 */
public class Truck extends Car {
    private int x, y;

    public Truck(int x, int y, int direction, int speed) {
        super(x, y, direction, speed);
        setDir(direction);
        if (getDir() == EAST)
            setPic("truck.png", EAST);
        else
            setPic("truck.png", WEST);

        setSpeed(speed);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    @Override
    public void update() {
        super.update();
    }
}


