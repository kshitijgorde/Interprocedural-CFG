import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.applet.AudioClip;
import java.util.Random;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShootBalls extends Applet implements MouseListener, Runnable
{
    private Image dbImage;
    private Graphics dbg;
    Thread th;
    GameTimer mytimer;
    private Ball ball;
    private Color ballcolor;
    Cursor c;
    Random rnd;
    private Color[] color_array;
    private Ball[] ball_array;
    int level;
    int maxballs;
    int maxspeed;
    private int x;
    private int y;
    public int ball_width;
    public int ball_height;
    public int appletsize_x;
    public int appletsize_y;
    int x_speed;
    int y_speed;
    private int count;
    private int test;
    int numcolors;
    public int timeleft;
    int colorIndex;
    private int score;
    private int hits;
    private int miss;
    public boolean movetonextlevel;
    private boolean gotHit;
    public boolean end_of_game;
    private boolean alive;
    public AudioClip hitnoise;
    public AudioClip gunnoise;
    public AudioClip startnoise;
    public AudioClip gameovernoise;
    Image gameOverImg;
    Font small;
    Font large;
    Font realsmall;
    
    public ShootBalls() {
        this.rnd = new Random();
        this.level = 1;
        this.y = 0;
        this.ball_width = 20;
        this.ball_height = 20;
        this.appletsize_x = 464;
        this.appletsize_y = 264;
        this.y_speed = 0;
        this.count = 0;
        this.test = 0;
        this.numcolors = 6;
        this.colorIndex = 0;
        this.miss = 0;
        this.movetonextlevel = false;
        this.gotHit = false;
        this.end_of_game = false;
        this.alive = true;
        this.small = new Font("TimesRoman", 0, 12);
        this.large = new Font("TimesRoman", 0, 25);
        this.realsmall = new Font("TimesRoman", 0, 10);
    }
    
    public void init() {
        this.end_of_game = false;
        this.setGameLevel(this.level);
        this.mytimer = new GameTimer(60);
        this.setCursor(this.c = new Cursor(1));
        this.setBackground(Color.black);
        this.gameOverImg = this.getImage(this.getCodeBase(), "gameOver.gif");
        this.color_array = new Color[] { Color.blue, Color.yellow, Color.orange, Color.white, Color.green, Color.red };
        this.ball_array = new Ball[this.maxballs];
        this.gunnoise = this.getAudioClip(this.getCodeBase(), "gun.au");
        this.hitnoise = this.getAudioClip(this.getCodeBase(), "score.au");
        this.startnoise = this.getAudioClip(this.getCodeBase(), "startmusic.au");
        this.gameovernoise = this.getAudioClip(this.getCodeBase(), "gameover.au");
        this.startnoise.play();
        this.count = 0;
        while (this.count < this.maxballs) {
            if (this.count < this.maxballs) {
                this.x = (int)(Math.random() * this.appletsize_x);
                if (this.x < 20) {
                    this.x = 25;
                }
                this.y = (int)(Math.random() * this.appletsize_y);
                if (this.y < 20) {
                    this.y = 25;
                }
                this.x_speed = (int)(Math.random() * this.maxspeed);
                if (this.x_speed < 1) {
                    this.x_speed = 1;
                }
                this.y_speed = (int)(Math.random() * this.maxspeed);
                if (this.y_speed < 1) {
                    this.y_speed = 1;
                }
                this.colorIndex = (int)(Math.random() * this.numcolors);
                if (this.colorIndex < 0) {
                    this.colorIndex = 0;
                }
                this.ballcolor = this.color_array[this.colorIndex];
                this.ball = new Ball(this.x, this.y, this.ball_width, this.ball_height, this.ballcolor, this.x_speed, this.y_speed, this.maxspeed, this.alive, this.appletsize_x, this.appletsize_y);
                this.ball_array[this.count] = this.ball;
            }
            ++this.count;
        }
    }
    
    public void start() {
        new Thread(this).start();
    }
    
    public void stop() {
    }
    
    public void destroy() {
        this.removeMouseListener(this);
        if (this.th != null) {
            this.th = null;
        }
    }
    
    public void run() {
        this.addMouseListener(this);
        while (!this.end_of_game) {
            this.timeleft = this.mytimer.getTime();
            if (this.timeleft == 0) {
                this.end_of_game = true;
            }
            this.test = 0;
            this.count = 0;
            while (this.count < this.maxballs) {
                if (!this.ball_array[this.count].alive) {
                    ++this.test;
                    if (this.test == this.maxballs) {
                        if (this.level < 5) {
                            ++this.level;
                            this.movetonextlevel = true;
                            this.repaint();
                            this.delay(6000);
                            this.movetonextlevel = false;
                            this.setGameLevel(this.level);
                            this.init();
                        }
                        else {
                            this.end_of_game = true;
                        }
                    }
                }
                ++this.count;
            }
            this.count = 0;
            while (this.count < this.maxballs) {
                if (this.ball_array[this.count].alive) {
                    this.ball_array[this.count].move();
                }
                ++this.count;
            }
            this.repaint();
            this.delay(20);
        }
        this.delay(2000);
        this.gameovernoise.play();
        this.repaint();
        this.destroy();
    }
    
    public void delay(final int n) {
        try {
            final Thread th = this.th;
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final double n = mouseEvent.getX();
        final double n2 = mouseEvent.getY();
        this.gunnoise.play();
        this.count = 0;
        while (this.count < this.maxballs) {
            if (this.ball_array[this.count].userHit(n, n2)) {
                this.ball_array[this.count].killBall();
                this.gotHit = true;
                if (this.ball_array[this.count].color == Color.red) {
                    this.score += 5;
                    break;
                }
                if (this.ball_array[this.count].color == Color.white) {
                    this.score += 1000;
                    break;
                }
                if (this.ball_array[this.count].color == Color.yellow) {
                    this.score += 50;
                    break;
                }
                if (this.ball_array[this.count].color == Color.orange) {
                    this.score += 100;
                    break;
                }
                if (this.ball_array[this.count].color == Color.blue) {
                    this.score += 200;
                    break;
                }
                if (this.ball_array[this.count].color == Color.green) {
                    this.score += 500;
                    break;
                }
                this.hitnoise.play();
            }
            else {
                this.gotHit = false;
            }
            ++this.count;
        }
        if (!this.gotHit) {
            this.score -= 100;
        }
        if (this.score < 0) {
            this.score = 0;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.repaint();
    }
    
    public void setGameLevel(final int n) {
        if (n == 1) {
            this.maxballs = 20;
            this.maxspeed = 10;
            this.ball_height = 40;
            this.ball_width = 40;
        }
        if (n == 2) {
            this.maxballs = 20;
            this.maxspeed = 10;
            this.ball_height = 30;
            this.ball_width = 30;
        }
        if (n == 3) {
            this.maxballs = 30;
            this.maxspeed = 10;
            this.ball_height = 25;
            this.ball_width = 25;
        }
        if (n == 4) {
            this.maxballs = 40;
            this.maxspeed = 10;
            this.ball_height = 15;
            this.ball_width = 15;
        }
        if (n == 5) {
            this.maxballs = 50;
            this.maxspeed = 10;
            this.ball_height = 10;
            this.ball_width = 10;
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
    
    public void paint(final Graphics graphics) {
        if (this.movetonextlevel) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.appletsize_x, this.appletsize_y);
            graphics.setColor(Color.yellow);
            graphics.setFont(this.large);
            graphics.drawString("Moving to Level: " + this.level, 150, 150);
        }
        graphics.setColor(Color.yellow);
        graphics.setFont(this.realsmall);
        graphics.drawString("Ver 1.01, Copyright(c) 2002 By Charles L. Wilson         http://www.getimage.com", 30, 295);
        final String string = Integer.toString(this.score);
        final String string2 = Integer.toString(this.level);
        final String string3 = Integer.toString(this.timeleft);
        graphics.setColor(Color.white);
        graphics.setFont(this.small);
        graphics.drawString("SCORE= " + string, 40, 280);
        graphics.drawString("SKILL LEVEL= " + string2, 370, 280);
        graphics.drawString("Time:" + string3, 229, 280);
        this.count = 0;
        while (this.count < this.maxballs) {
            if (this.ball_array[this.count].alive) {
                this.ball_array[this.count].drawBall(graphics);
            }
            ++this.count;
        }
        if (this.end_of_game) {
            graphics.drawImage(this.gameOverImg, 125, 12, this);
        }
    }
}
