/**
 * Created by student on 2/7/18.
 */
public class Car extends Sprite{
    public Car(int x, int y, int direction, int speed) {
        super(x, y, direction);
    }

    @Override
    public void update() {
        super.update();

        if(getLoc().x >= FroggerMain.FRAMEWIDTH){
            setDir(getDir()+180);
        }
        if (getLoc().x <= 0)
            setDir(getDir()+ 180);

    }
}
