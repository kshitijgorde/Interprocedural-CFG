import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class TimeLine extends Canvas implements Runnable
{
    Image line;
    Image all;
    Graphics gLine;
    Graphics gAll;
    FindIt2Frame fi2f;
    Thread tmThread;
    private Color bgColor;
    int timeLeft;
    boolean timeUp;
    int length;
    boolean newLevel;
    boolean punish;
    int tspeed;
    int punishSec;
    
    public TimeLine(final FindIt2Frame fi2f) {
        this.timeLeft = 280;
        this.timeUp = true;
        this.length = 280;
        this.newLevel = true;
        this.tspeed = 500;
        this.punishSec = 10;
        this.setBackground(this.bgColor = fi2f.bgColor);
        this.fi2f = fi2f;
        if (this.tmThread == null) {
            (this.tmThread = new Thread(this)).start();
        }
        this.line = fi2f.getEmptyImage(300, 40);
        this.gLine = this.line.getGraphics();
        this.all = fi2f.getEmptyImage(400, 60);
        (this.gAll = this.all.getGraphics()).setColor(this.bgColor);
        this.gLine.setColor(this.bgColor);
    }
    
    public void badClick() {
        this.fi2f.addToScore(-10);
        this.setTspeed(100, false);
        if (this.timeLeft < 0) {
            this.timeLeft = 0;
        }
    }
    
    public int getLength() {
        return this.length;
    }
    
    private int getLevel() {
        return this.fi2f.lvl.getLevel();
    }
    
    public int getTimeLeft() {
        return this.timeLeft;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.bgColor);
        this.gLine.setColor(this.bgColor);
        this.gAll.setColor(this.bgColor);
        this.gAll.fillRect(0, 0, 400, 400);
        if (this.newLevel) {
            for (int i = 0; i < this.getLength(); ++i) {
                if (this.getLevel() % 6 == 0) {
                    if (i < 255) {
                        this.gLine.setColor(new Color(0, 0, i));
                    }
                    this.gLine.drawLine(i + 10, 0, i + 10, 40);
                }
                else if (this.getLevel() % 6 == 1) {
                    if (i < 255) {
                        this.gLine.setColor(new Color(0, i, 0));
                    }
                    this.gLine.drawLine(i + 10, 0, i + 10, 40);
                }
                else if (this.getLevel() % 6 == 2) {
                    if (i < 255) {
                        this.gLine.setColor(new Color(i, 0, 0));
                    }
                    this.gLine.drawLine(i + 10, 0, i + 10, 40);
                }
                else if (this.getLevel() % 6 == 3) {
                    if (i < 255) {
                        this.gLine.setColor(new Color(i, i, 0));
                    }
                    this.gLine.drawLine(i + 10, 0, i + 10, 40);
                }
                else if (this.getLevel() % 6 == 4) {
                    if (i < 255) {
                        this.gLine.setColor(new Color(i, 0, i));
                    }
                    this.gLine.drawLine(i + 10, 0, i + 10, 40);
                }
                else if (this.getLevel() % 6 == 5) {
                    if (i < 255) {
                        this.gLine.setColor(new Color(0, i, i));
                    }
                    this.gLine.drawLine(i + 10, 0, i + 10, 40);
                }
                graphics.fillRect(0, 0, 400, 400);
            }
            this.newLevel = false;
            this.gLine.setColor(Color.black);
            this.gLine.fillArc(0, 0, 30, 40, 90, 180);
            this.gLine.setColor(this.bgColor);
        }
        this.gAll.drawImage(this.line, 10, 10, this);
        this.gAll.setColor(this.bgColor);
        this.gAll.fillRect(20 + this.timeLeft, 10, 10 + this.getLength() - this.timeLeft, 40);
        graphics.drawImage(this.all, 0, 0, this);
    }
    
    public void reset() {
        this.length = 280;
        this.setTspeed(800 / this.fi2f.lvl.getLevel(), true);
        this.timeLeft = this.length;
        this.newLevel = true;
        this.repaint();
    }
    
    public void run() {
        while (this.fi2f.runThreads) {
            if (!this.timeUp) {
                this.timeLeft -= 2;
                if (this.timeLeft < 1) {
                    this.timeUpRoutine();
                }
                try {
                    Thread.sleep(this.tspeed);
                    if (this.punish) {
                        --this.punishSec;
                        if (this.punishSec < 1) {
                            this.punish = false;
                            if (this.fi2f.lvl.getLevel() != 0) {
                                this.setTspeed(800 / this.fi2f.lvl.getLevel(), true);
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    this.timeUp = true;
                    this.fi2f.runThreads = false;
                    System.out.println(ex);
                }
                this.repaint();
            }
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex2) {}
        }
    }
    
    public void setTspeed(final int tspeed, final boolean b) {
        if (!b) {
            this.punish = true;
            this.punishSec += 10;
            this.tspeed = 10;
        }
        else if (tspeed > 150) {
            this.tspeed = tspeed;
        }
        else {
            this.tspeed = 150;
        }
    }
    
    private void timeUpRoutine() {
        this.timeUp = true;
        this.fi2f.gameOver();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
