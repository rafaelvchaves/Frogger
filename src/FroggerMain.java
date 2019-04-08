//Rafael Chaves

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FroggerMain extends JPanel {

    //instance fields for the general environment
    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 600;
    private Timer timer;
    private boolean[] keys;
    private boolean dead;
    private int lives = 3;
    private int level = 1;
    private int score = 0;
    private int sc;
    private int i = 0;
    //instance fields for frogger.
    private Sprite frog = new Frog(FRAMEWIDTH / 2, FRAMEHEIGHT - 30);
    private ArrayList<Log> logs = new ArrayList<Log>();
    private ArrayList<Sprite> obstacles = new ArrayList<Sprite>();
    private ArrayList<Goal> goals = new ArrayList<Goal>();
    Water water = new Water(0, 115, FRAMEWIDTH, 275);

    public FroggerMain() {
        keys = new boolean[512];
        int r = (int) (Math.random() * 5 + 3);
        for (int i = 0; i < 10; i++) {
            obstacles.add(new YellowCar(FRAMEWIDTH - 99 * i, FRAMEHEIGHT - 58, Sprite.WEST, r));
        }
        int r2 = (int) (Math.random() * 5 + 6);
        for (int i = 0; i < 7; i++) {
            obstacles.add(new WhiteCar1(249 * i, FRAMEHEIGHT - 85, Sprite.EAST, r2));
        }
        int r3 = (int) (Math.random() * 5 + 2);
        for (int i = 0; i < 5; i++) {
            obstacles.add(new PurpleCar(FRAMEWIDTH - 153 * i, FRAMEHEIGHT - 120, Sprite.WEST, r3));
        }
        for (int i = 0; i < 6; i++) {
            obstacles.add(new WhiteCar2(203 * i, FRAMEHEIGHT - 147, Sprite.EAST, r)); //120 to 150
        }
        for (int i = 0; i < 7; i++) {
            obstacles.add(new Truck(FRAMEWIDTH - 271 * i, FRAMEHEIGHT - 174, Sprite.WEST, r2)); //150 to 180
        }

        for (int i = 0; i < 2; i++) {
            logs.add(new Log(300 * i, FRAMEHEIGHT - 231, Sprite.EAST, 1, 2));
            logs.add(new Log(FRAMEWIDTH - 400 * i, FRAMEHEIGHT - 263, Sprite.WEST, 3, 2));
            logs.add(new Log(250 * i, FRAMEHEIGHT - 295, Sprite.EAST, 2, 3));
            logs.add(new Log(FRAMEWIDTH - 250 * i, FRAMEHEIGHT - 327, Sprite.WEST, 3, 2));
            logs.add(new Log(600 * i, FRAMEHEIGHT - 359, Sprite.EAST, 1, 2));
            logs.add(new Log(FRAMEWIDTH - 240 * i, FRAMEHEIGHT - 391, Sprite.WEST, 2, 2));
            logs.add(new Log(500 * i, FRAMEHEIGHT - 421, Sprite.EAST, 3, 5));
            logs.add(new Log(FRAMEWIDTH - 500 * i, FRAMEHEIGHT - 453, Sprite.WEST, 2, 1));
            logs.add(new Log(500 * i, FRAMEHEIGHT - 485, Sprite.EAST, 3, 5));


        }
        goals.add(new Goal(FRAMEWIDTH*5/16, Color.green));
        goals.add(new Goal(FRAMEWIDTH*9/16, Color.green));
//        goals.add(new Goal(FRAMEWIDTH*11/16));





        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //move the frog
                if (keys[KeyEvent.VK_UP]) {
                    frog.setDir(Sprite.NORTH);
                    frog.update();
                    keys[KeyEvent.VK_UP] = false; //probably.  Makes 1 move per button press.
                    score += 30;
                    if (level == 1)
                        sc = score;
                }
                if (keys[KeyEvent.VK_LEFT]) {
                    frog.setDir(Sprite.WEST);
                    frog.update();
                    keys[KeyEvent.VK_LEFT] = false; //probably.  Makes 1 move per button press.
                }
                if (keys[KeyEvent.VK_RIGHT]) {
                    frog.setDir(Sprite.EAST);
                    frog.update();
                    keys[KeyEvent.VK_RIGHT] = false;
                }
                if (keys[KeyEvent.VK_DOWN]) {
                    frog.setDir(Sprite.SOUTH);
                    frog.update();
                    keys[KeyEvent.VK_DOWN] = false;
                }

                for (Sprite s : obstacles) {
                    s.update();
                }
                for (Log l : logs) {
                    l.update();
                }
                for (Sprite s : obstacles) {
                    if (frog.intersects(s)) {
                        frog.setLoc(new Point(FRAMEWIDTH / 2, FRAMEHEIGHT - 30));
                        frog.setDir(90);
                        score = 0;
                        lives--;
                        if (lives < 0)
                            lives = 0;
                    }
                }
                for (Goal g : goals){
                    if (frog.getLoc().y < 120 && g.contains(frog.getLoc().x) && g.getColor() == Color.green) {
                        frog.setLoc(new Point(FRAMEWIDTH / 2, FRAMEHEIGHT - 30));
                        frog.setDir(90);
                        g.setColor(Color.orange);
                        i++;
                    }
                }
                if (i == 2 && level == 1){
                    frog.setLoc(new Point(FRAMEWIDTH / 2, FRAMEHEIGHT - 30));
                    frog.setDir(90);
                    level++;
                    lives = 3;
                    for (Sprite c : obstacles) {
                        c.setSpeed(c.getSpeed() + 3);
                    }
                    goals.add(new Goal(FRAMEWIDTH*5/16, Color.green));
                    goals.add(new Goal(FRAMEWIDTH*9/16, Color.green));
                    i = 0;
                }
                if (frog.getLoc().y > water.getMaxY() && frog.getLoc().y < water.getMinY()) {
                    dead = true;
                    for (Log log : logs) {
                        if (frog.intersects(log)) {
                            dead = false;
                            if (log.getDir() == Sprite.EAST)
                                frog.getLoc().translate(log.getSpeed(), 0);
                            else if (log.getDir() == Sprite.WEST)
                                frog.getLoc().translate(-log.getSpeed(), 0);


                        }
                    }
                    if (dead) {
                        frog.setLoc(new Point(FRAMEWIDTH / 2, FRAMEHEIGHT - 30));
                        frog.setDir(90);
                        if (level == 1)
                            score = 0;
                        if (level == 2)
                            score = sc;
                        lives--;
                        if (lives < 0)
                            lives = 0;
                    }
                }
                else if (level == 2 && i == 2) {
                    System.out.println("You Won!");
                    System.exit(0);
                }
                if (lives == 0) {
                    System.out.println("You Lost!");
                    System.exit(0);
                }


                repaint();
            }
        });
        timer.start();

        /*
        You probably don't need to modify this keyListener code.
         */
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {/*intentionally left blank*/ }

            //when a key is pressed, its boolean is switch to true.
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = true;
            }

            //when a key is released, its boolean is switched to false.
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = false;
            }
        });

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, FRAMEHEIGHT - 180, FRAMEWIDTH, 150);
        g2.setColor(new Color(25, 103, 32));
        g2.fillRect(0, 0, FRAMEWIDTH, 115);
        g2.setColor(new Color(48, 0, 245));
        g2.fillRect(0, FRAMEHEIGHT - 30, FRAMEWIDTH, 30);
        g2.fillRect(0, FRAMEHEIGHT - 210, FRAMEWIDTH, 30);
        g2.setColor(Color.BLACK);
        water.draw(g2);
        g2.setColor(Color.GREEN);
        for (Goal goal : goals)
            goal.draw(g2);
        for (Sprite s : obstacles)
            s.draw(g2);
        for (Log l : logs) {
            l.draw(g2);
        }
        frog.draw(g2);
        g2.setColor(Color.GREEN);
        g2.drawString("Lives: " + lives, FRAMEWIDTH - 60, FRAMEHEIGHT - 10);
        g2.drawString("Level: " + level, 0, FRAMEHEIGHT - 10);
        g2.drawString("Score: " + score, FRAMEWIDTH - 150, FRAMEHEIGHT - 10);

    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Frogger!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        FroggerMain panel = new FroggerMain();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}