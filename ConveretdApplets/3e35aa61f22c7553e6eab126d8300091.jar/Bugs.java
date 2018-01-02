import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.applet.AudioClip;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bugs extends Applet implements Animation, MouseListener, MouseMotionListener
{
    Vector enemy;
    int mousex;
    int mousey;
    boolean in;
    boolean intro;
    int changeTime;
    int points;
    int lives;
    int levelI;
    int level;
    int highScore;
    int titleSpeed;
    int z;
    int zy;
    private AudioClip click;
    private AudioClip noclick;
    private AudioClip levelUp;
    private AudioClip bite;
    private Image bug;
    AnimationTimer timer;
    
    public String getAppletInfo() {
        return "Name: REDBUGS\r\nAuthor: Jake W. Holmes\r\n";
    }
    
    public void init() {
        this.enemy.addElement(new Circle(98, 100, 15, 0, 1, 1, 0, this.getNum(50, 300)));
        this.enemy.addElement(new Circle(20, 20, 15, 0, 1, 1, 0, this.getNum(50, 300)));
        this.enemy.addElement(new Circle(50, 34, 15, 0, 1, 1, 0, this.getNum(50, 300)));
        for (int i = 3; i < 11; ++i) {
            int j;
            int num;
            do {
                j = this.getNum(1, 4);
                num = this.getNum(0, 3);
            } while (j <= num);
            this.enemy.addElement(new Circle(this.getNum(50, 300), this.getNum(50, 300), 3, num, j, 1, this.getNum(1, 4), this.getNum(50, 300)));
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.points = 0;
        this.lives = 100;
        this.levelI = 0;
        this.level = 1;
        this.mousex = 1000;
        this.mousey = 1000;
        this.highScore = 0;
        try {
            this.click = this.getAudioClip(this.getCodeBase(), "click.au");
            this.levelUp = this.getAudioClip(this.getCodeBase(), "levelUp.au");
            this.bite = this.getAudioClip(this.getCodeBase(), "bite.au");
            this.noclick = this.getAudioClip(this.getCodeBase(), "noclick.au");
            this.bug = this.getImage(this.getDocumentBase(), "bug.gif");
        }
        catch (Exception ex) {}
        this.setBackground(Color.black);
        this.setForeground(Color.black);
    }
    
    public void reInit() {
        if (this.points > this.highScore) {
            this.highScore = this.points;
        }
        this.enemy.setElementAt(new Circle(98, 100, 15, 0, 1, 1, 0, this.getNum(50, 300)), 0);
        this.enemy.setElementAt(new Circle(20, 20, 15, 0, 1, 1, 0, this.getNum(50, 300)), 1);
        this.enemy.setElementAt(new Circle(50, 34, 15, 0, 1, 1, 0, this.getNum(50, 300)), 2);
        for (int i = 3; i < this.enemy.size(); ++i) {
            int j;
            int num;
            do {
                j = this.getNum(1, 4);
                num = this.getNum(0, 3);
            } while (j <= num);
            this.enemy.setElementAt(new Circle(this.getNum(50, 300), this.getNum(50, 300), 3, num, j, 1, this.getNum(1, 4), this.getNum(50, 300)), i);
        }
        this.intro = true;
        this.z = 400;
        this.zy = 175;
        this.points = 0;
        this.lives = 100;
        this.levelI = 0;
        this.level = 1;
        this.mousex = 1000;
        this.mousey = 1000;
        this.timer.delay = this.titleSpeed;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setPaintMode();
        if (this.intro) {
            graphics.setColor(Color.green);
            graphics.drawString("RED BUGS  (Programmed by Jake Holmes : April, 1999)", this.zy, this.z);
            graphics.setColor(Color.green);
            graphics.drawString("RED BUGS  (Programmed by Jake Holmes : April, 1999)", this.zy, this.z);
            return;
        }
        for (int i = 0; i < this.enemy.size(); ++i) {
            final Circle circle = this.enemy.elementAt(i);
            if (circle.skill == 0) {
                graphics.setColor(Color.green);
            }
            else {
                graphics.setColor(Color.red);
            }
            graphics.drawImage(this.bug, circle.x + circle.r / 2, circle.y + circle.r / 2, circle.r, circle.r, this);
            graphics.setColor(Color.green);
            graphics.drawString("HIGH SCORE: " + this.highScore, 10, 20);
            graphics.drawString("SCORE: " + this.points, 10, 35);
            graphics.drawString("LEVEL: " + this.level, 10, 65);
            graphics.drawString("RED BUGS  (Programmed by Jake Holmes : April, 1999)", 175, 20);
            if (this.lives < 50 && this.lives > 25) {
                graphics.setColor(Color.yellow);
            }
            if (this.lives <= 25) {
                graphics.setColor(Color.red);
            }
            graphics.drawString("LIFE ENERGY: " + this.lives, 10, 50);
        }
        graphics.setXORMode(Color.black);
        for (int j = 0; j < this.enemy.size(); ++j) {
            final Circle circle2 = this.enemy.elementAt(j);
            if (circle2.skill == 0) {
                graphics.setColor(Color.green);
            }
            else {
                graphics.setColor(Color.red);
            }
            graphics.drawImage(this.bug, circle2.x + circle2.r / 2, circle2.y + circle2.r / 2, circle2.r, circle2.r, this);
            graphics.setColor(Color.green);
            graphics.drawString("HIGH SCORE: " + this.highScore, 10, 20);
            graphics.drawString("SCORE: " + this.points, 10, 35);
            graphics.drawString("LEVEL: " + this.level, 10, 65);
            graphics.drawString("RED BUGS  (Programmed by Jake Holmes : April, 1999)", 175, 20);
            if (this.lives < 50 && this.lives > 25) {
                graphics.setColor(Color.yellow);
            }
            if (this.lives <= 25) {
                graphics.setColor(Color.red);
            }
            graphics.drawString("LIFE ENERGY: " + this.lives, 10, 50);
        }
    }
    
    public void animate() {
        if (this.intro) {
            if (this.z > 200) {
                --this.z;
            }
            else {
                ++this.zy;
            }
            this.repaint();
            if (this.zy > 640) {
                this.intro = false;
                this.timer.delay = 25;
            }
        }
        else {
            for (int i = 0; i < this.enemy.size(); ++i) {
                final Circle circle = this.enemy.elementAt(i);
                final int absVal = absVal(this.mousex - circle.x);
                final int absVal2 = absVal(this.mousey - circle.y);
                if (absVal < circle.attackRng && absVal2 < circle.attackRng && circle.skill > 0) {
                    this.in = true;
                }
                else {
                    this.in = false;
                }
                if (this.in) {
                    if (absVal2 > absVal && circle.skill < 4 && circle.skill > 0) {
                        circle.dx = circle.chaseSpeed;
                        circle.dy = 2 * circle.chaseSpeed;
                    }
                    if (absVal2 < absVal && circle.skill < 3 && circle.skill > 0) {
                        circle.dx = 2 * circle.chaseSpeed;
                        circle.dy = circle.chaseSpeed;
                    }
                    if (absVal2 == absVal && circle.skill > 1) {
                        circle.dx = 2 * circle.chaseSpeed;
                        circle.dy = 2 * circle.chaseSpeed;
                    }
                    if (circle.skill > 4) {
                        if (absVal == 0) {
                            circle.dx = 0;
                        }
                        else if (absVal2 == 0) {
                            circle.dy = 0;
                        }
                        else {
                            circle.dx = circle.ddx;
                            circle.dy = circle.ddy;
                        }
                    }
                    if (this.mousex < circle.x && circle.skill > 0) {
                        if (circle.dx > 0) {
                            circle.dx = -circle.dx;
                        }
                    }
                    else if (circle.dx < 0 && circle.skill > 0) {
                        circle.dx = -circle.dx;
                    }
                    if (this.mousey < circle.y && circle.skill > 0) {
                        if (circle.dy > 0) {
                            circle.dy = -circle.dy;
                        }
                    }
                    else if (circle.dy < 0 && circle.skill > 0) {
                        circle.dy = -circle.dy;
                    }
                }
                if (circle.skill > 0 && this.mousex < circle.x + circle.r && this.mousex > circle.x - circle.r && this.mousey < circle.y + circle.r && this.mousey > circle.y - circle.r) {
                    --this.lives;
                    this.bite.play();
                    if (this.lives < 0) {
                        this.reInit();
                    }
                }
                if (!this.in || circle.skill == 0) {
                    if (this.changeTime > 50) {
                        this.changeTime = 1;
                    }
                    if (circle.changeTime == this.changeTime) {
                        final int ddx = circle.ddx;
                        final int ddy = circle.ddy;
                        int num = this.getNum(ddx, ddy);
                        int num2 = this.getNum(ddx, ddy);
                        final int num3 = this.getNum(1, 50);
                        if (this.getNum(1, 2) == 2) {
                            num = -num;
                        }
                        if (this.getNum(1, 2) == 2) {
                            num2 = -num2;
                        }
                        circle.dx = num;
                        circle.dy = num2;
                        circle.changeTime = num3;
                    }
                }
                if (circle.x - circle.r + circle.dx < 0 || circle.x + circle.r + circle.dx > this.bounds().width) {
                    circle.dx = -circle.dx;
                }
                if (circle.y - circle.r + circle.dy < 0 || circle.y + circle.r + circle.dy > this.bounds().height) {
                    circle.dy = -circle.dy;
                }
                final Circle circle2 = circle;
                circle2.x += circle.dx;
                final Circle circle3 = circle;
                circle3.y += circle.dy;
            }
            ++this.changeTime;
            this.repaint();
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.in = true;
        for (int i = 0; i < this.enemy.size(); ++i) {
            final Circle circle = this.enemy.elementAt(i);
            if (circle.skill == 0) {
                circle.dy = circle.ddy * circle.chaseSpeed;
                circle.dx = circle.ddx * circle.chaseSpeed;
            }
        }
    }
    
    private static int absVal(int n) {
        if (n < 0) {
            n += n * 2;
        }
        return n;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mousex = 1000;
        this.mousey = 100000;
        for (int i = 0; i < this.enemy.size(); ++i) {
            final Circle circle = this.enemy.elementAt(i);
            circle.dy = circle.ddy;
            circle.dx = circle.ddx;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.mousex = mouseEvent.getX();
        this.mousey = mouseEvent.getY();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mousex = mouseEvent.getX();
        this.mousey = mouseEvent.getY();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.mousex = mouseEvent.getX();
        this.mousey = mouseEvent.getY();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.noclick.play();
        this.setCursor(Cursor.getPredefinedCursor(1));
        for (int i = 0; i < this.enemy.size(); ++i) {
            final Circle circle = this.enemy.elementAt(i);
            if (circle.skill == 0 && this.mousex < circle.x + circle.r + 3 && this.mousex > circle.x - circle.r - 3 && this.mousey < circle.y + circle.r + 3 && this.mousey > circle.y - circle.r - 3) {
                if (circle.r > 5) {
                    --circle.r;
                }
                this.points += 100;
                ++this.levelI;
                circle.x = this.getNum(25, 600);
                circle.y = this.getNum(25, 350);
                this.click.play();
                if (this.levelI == 12) {
                    this.levelI = 0;
                    ++this.level;
                    this.increaseLevel();
                    this.levelUp.play();
                }
            }
        }
    }
    
    public int getNum(final int n, final int n2) {
        return new RandomIntGenerator(n, n2).draw();
    }
    
    private void increaseLevel() {
        switch (this.level) {
            case 2: {
                for (int i = 0; i < 3; ++i) {
                    final Circle circle = this.enemy.elementAt(i);
                    circle.r = 15;
                    circle.dy = 2;
                }
            }
            case 3: {
                for (int j = 0; j < 3; ++j) {
                    final Circle circle2 = this.enemy.elementAt(j);
                    circle2.r = 15;
                    circle2.dy = 3;
                }
            }
            case 4: {
                for (int k = 8; k < this.enemy.size(); ++k) {
                    ((Circle)this.enemy.elementAt(k)).chaseSpeed = 2;
                }
            }
            case 5: {
                for (int l = 5; l < 8; ++l) {
                    ((Circle)this.enemy.elementAt(l)).chaseSpeed = 3;
                }
            }
            case 6: {
                final Circle circle3 = this.enemy.elementAt(2);
                circle3.skill = 1;
                circle3.r = 3;
            }
            case 7: {
                final Circle circle4 = this.enemy.elementAt(1);
                circle4.skill = 1;
                circle4.r = 3;
            }
            default: {
                if (this.timer.delay > 1) {
                    final AnimationTimer timer = this.timer;
                    --timer.delay;
                }
            }
        }
    }
    
    public void start() {
        this.timer.start_animation();
    }
    
    public void stop() {
        this.timer.pause_animation();
    }
    
    public Bugs() {
        this.enemy = new Vector(1, 2);
        this.in = false;
        this.intro = true;
        this.titleSpeed = 5;
        this.z = 400;
        this.zy = 175;
        this.timer = new AnimationTimer(this, this.titleSpeed);
    }
}
