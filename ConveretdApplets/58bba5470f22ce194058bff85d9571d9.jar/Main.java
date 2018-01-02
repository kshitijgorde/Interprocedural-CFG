import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Component;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Main extends Applet implements Runnable
{
    public int score1;
    public int score2;
    public int maus_y;
    Ball puck;
    Player bat1;
    Player bat2;
    Computer bat3;
    Computer bat4;
    Thread th;
    public int whosgoal;
    private Image titleImg;
    private Image dbImage;
    private Graphics dbg;
    AudioClip kicknoise;
    AudioClip goalnoise;
    boolean play;
    boolean end;
    boolean title;
    
    public void init() {
        this.score1 = 0;
        this.score2 = 0;
        this.setBackground(Color.black);
        this.puck = new Ball(8, 250, 175, 0, 0, Color.black, this);
        this.bat1 = new Player(15, 175);
        this.bat2 = new Player(375, 175);
        this.bat3 = new Computer(475, 175);
        this.bat4 = new Computer(115, 175);
        this.play = false;
        this.end = false;
        this.title = true;
        this.titleImg = this.getImage(this.getCodeBase(), "Title.gif");
        (this.kicknoise = this.getAudioClip(this.getCodeBase(), "hit.au")).play();
        this.kicknoise.stop();
        (this.goalnoise = this.getAudioClip(this.getCodeBase(), "chime.au")).play();
        this.goalnoise.stop();
    }
    
    public void start() {
        (this.th = new Thread(this)).start();
    }
    
    public void stop() {
        this.th.stop();
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (true) {
            this.repaint();
            this.puck.move();
            this.bat3.ComputerMove(this.puck);
            this.whosgoal = this.puck.wheresBall();
            if (this.whosgoal != 0) {
                if (this.whosgoal == 1) {
                    ++this.score1;
                    this.goalnoise.play();
                    this.puck.pos_x = 250;
                    this.puck.vx = 4;
                    this.puck.vy = -4;
                    if (this.score1 == 10) {
                        this.play = false;
                        this.end = true;
                        this.puck.isStoped = true;
                        this.puck.vx = 0;
                        this.puck.vy = 0;
                    }
                }
                else if (this.whosgoal == 2) {
                    ++this.score2;
                    this.goalnoise.play();
                    this.puck.pos_x = 250;
                    this.puck.vx = 4;
                    this.puck.vy = 4;
                    if (this.score2 == 10) {
                        this.play = false;
                        this.end = true;
                        this.puck.isStoped = true;
                        this.puck.vx = 0;
                        this.puck.vy = 0;
                    }
                }
            }
            if (this.puck.vx < 0) {
                this.puck.PCollision(this.bat1, this.bat2, this.kicknoise);
            }
            else if (this.puck.vx > 0) {
                this.puck.CCollision(this.bat3, this.bat4, this.kicknoise);
            }
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {
                break;
            }
            Thread.currentThread().setPriority(10);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int maus_y) {
        this.maus_y = maus_y;
        if (this.maus_y > 25 & this.maus_y < 325) {
            this.bat1.PaddelMove(this.maus_y);
            this.bat2.PaddelMove(this.maus_y);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.play) {
            this.play = true;
            this.end = false;
            this.title = false;
            this.puck.vx = 4;
            this.puck.vy = 4;
            this.score1 = 0;
            this.score2 = 0;
            this.puck.isStoped = false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, 500, 8);
        graphics.fillRect(0, 342, 500, 8);
        graphics.fillRect(0, 0, 8, 75);
        graphics.fillRect(0, 275, 8, 75);
        graphics.fillRect(492, 0, 8, 75);
        graphics.fillRect(492, 275, 8, 75);
        this.puck.DrawBall(graphics);
        this.bat1.DrawPaddel(graphics);
        this.bat2.DrawPaddel(graphics);
        this.bat3.DrawPaddel(graphics);
        this.bat4.DrawPaddel(graphics);
        graphics.setColor(Color.yellow);
        graphics.drawString("Player:  " + this.score2, 50, 370);
        graphics.drawString("Computer:  " + this.score1, 400, 370);
        if (this.title) {
            graphics.drawImage(this.titleImg, 150, 70, this);
            graphics.drawString("This game was programmed by FBI", 155, 200);
            graphics.drawString("Please visit us at www.javacooperation.gmxhome.de", 125, 220);
            graphics.drawString("Click on Applet to start game!", 175, 240);
        }
        else if (this.end) {
            graphics.drawImage(this.titleImg, 150, 70, this);
            graphics.drawString("This game was programmed by FBI", 155, 200);
            graphics.drawString("Please visit us at www.javacooperation.gmxhome.de", 125, 220);
            if (this.score1 == 10) {
                graphics.drawString("Computer won the game!!", 180, 240);
            }
            else if (this.score2 == 10) {
                graphics.drawString("Player won the game!!", 180, 240);
            }
            graphics.drawString("Click on Applet to restart game!", 170, 260);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.dbImage == null) {
            this.dbImage = this.createImage(this.getSize().width, this.getSize().height);
            this.dbg = this.dbImage.getGraphics();
        }
        this.dbg.setColor(this.getBackground());
        this.dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.dbg.setColor(this.getForeground());
        this.paint(this.dbg);
        graphics.drawImage(this.dbImage, 0, 0, this);
    }
}
