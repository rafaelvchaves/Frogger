import java.awt.*;

public class YellowCar extends Car {
    private int x, y;

    public YellowCar(int x, int y, int direction, int speed){
        super(x, y, direction, speed);
        setDir(direction);
        if (getDir() == EAST)
            setPic("car3.png", EAST);
        else
            setPic("car3.png", WEST);

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
