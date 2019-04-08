import java.awt.*;

public class WhiteCar2 extends Car {
    private int x, y;

    public WhiteCar2(int x, int y, int direction, int speed){
        super(x, y, direction, speed);
        setPic("car4.png", EAST);
        setDir(direction);
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
