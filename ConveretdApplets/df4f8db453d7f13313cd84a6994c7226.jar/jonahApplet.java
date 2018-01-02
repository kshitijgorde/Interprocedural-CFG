import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jonahApplet extends Applet implements Runnable, KeyListener
{
    AudioClip auTube;
    AudioClip auOuch;
    AudioClip auWhale;
    AudioClip auYum;
    private Font theFont;
    private Font scoreFont;
    private FontMetrics fm;
    private FontMetrics fm2;
    private int speed;
    private int fishW;
    private int fishH;
    private int foodW;
    private int foodH;
    private int[] whaleW;
    private int[] whaleH;
    private int totalTime;
    private int bestTime;
    private boolean gotTube;
    private boolean gotFish;
    private boolean gameOver;
    private boolean gotFood;
    private int foodCount;
    private int tubeCount;
    private int fishCount;
    private int innerX;
    private int innerY;
    private int fishX;
    private int fishY;
    private int foodX;
    private int foodY;
    private int odd;
    private int whaleCount;
    private int whaleNum;
    private int waterNum;
    private Image jonahend;
    private Image jonah;
    private Image tube;
    private Image tube1;
    private Image tube2;
    private Image fish;
    private Image fish1;
    private Image fish2;
    private Image food;
    private Image food1;
    private Image food2;
    private Image[] swim;
    private Image[] water;
    private Image[] whale;
    private Thread runner;
    private int jonahX;
    private int jonahY;
    private int[] whaleX;
    private int[] whaleY;
    private int rightNum;
    private int leftNum;
    private int upNum;
    private int downNum;
    
    public void init() {
        this.fm = this.getFontMetrics(this.theFont);
        this.fm2 = this.getFontMetrics(this.scoreFont);
        this.setBackground(Color.cyan);
        final MediaTracker mt = new MediaTracker(this);
        try {
            this.water[0] = this.getImage(new URL(this.getCodeBase(), "water1.gif"));
            this.water[1] = this.getImage(new URL(this.getCodeBase(), "water2.gif"));
            this.water[2] = this.getImage(new URL(this.getCodeBase(), "water3.gif"));
            this.swim[0] = this.getImage(new URL(this.getCodeBase(), "swim1.gif"));
            this.swim[1] = this.getImage(new URL(this.getCodeBase(), "swim2.gif"));
            this.swim[2] = this.getImage(new URL(this.getCodeBase(), "swim3.gif"));
            this.swim[3] = this.getImage(new URL(this.getCodeBase(), "swim4.gif"));
            this.whale[0] = this.getImage(new URL(this.getCodeBase(), "whale1.gif"));
            this.whale[1] = this.getImage(new URL(this.getCodeBase(), "whale2.gif"));
            this.whale[2] = this.getImage(new URL(this.getCodeBase(), "whale3.gif"));
            this.tube1 = this.getImage(new URL(this.getCodeBase(), "tube1.gif"));
            this.tube2 = this.getImage(new URL(this.getCodeBase(), "tube2.gif"));
            this.fish1 = this.getImage(new URL(this.getCodeBase(), "fish1.gif"));
            this.fish2 = this.getImage(new URL(this.getCodeBase(), "fish2.gif"));
            this.food1 = this.getImage(new URL(this.getCodeBase(), "food1.gif"));
            this.food2 = this.getImage(new URL(this.getCodeBase(), "food2.gif"));
            this.jonahend = this.getImage(new URL(this.getCodeBase(), "jonahend.gif"));
        }
        catch (MalformedURLException e) {
            System.out.println(e.toString());
        }
        try {
            mt.addImage(this.water[0], 1);
            mt.addImage(this.water[1], 2);
            mt.addImage(this.water[2], 3);
            mt.addImage(this.swim[0], 4);
            mt.addImage(this.swim[1], 5);
            mt.addImage(this.swim[2], 6);
            mt.addImage(this.swim[3], 7);
            mt.addImage(this.whale[0], 8);
            mt.addImage(this.whale[1], 9);
            mt.addImage(this.whale[2], 10);
            mt.addImage(this.tube1, 11);
            mt.addImage(this.tube2, 12);
            mt.addImage(this.fish1, 13);
            mt.addImage(this.fish2, 14);
            mt.addImage(this.food1, 15);
            mt.addImage(this.food2, 16);
            mt.addImage(this.jonahend, 17);
            mt.waitForAll();
        }
        catch (InterruptedException e2) {
            System.out.println(e2.toString());
        }
        if (this.fish2 == null) {
            System.out.println("NO");
        }
        this.jonah = this.swim[0];
        this.fishW = this.fish1.getWidth(this);
        this.fishH = this.fish1.getHeight(this);
        this.foodW = this.food1.getWidth(this);
        this.foodH = this.food1.getHeight(this);
        this.whaleW[0] = this.whale[0].getWidth(this);
        this.whaleW[1] = this.whale[1].getWidth(this);
        this.whaleW[2] = this.whale[2].getWidth(this);
        this.whaleH[0] = this.whale[0].getHeight(this);
        this.whaleH[1] = this.whale[1].getHeight(this);
        this.whaleH[2] = this.whale[2].getHeight(this);
        this.loadSounds();
        this.setLayout(null);
        this.setSize(450, 400);
        (this.runner = new Thread(this)).setPriority(5);
        this.runner.start();
        this.addKeyListener(this);
        this.requestFocus();
        this.whaleX[0] = (int)Math.random() * 450;
        this.whaleY[0] = (int)Math.random() * 400;
        this.innerX = (int)(Math.random() * 450.0);
        this.innerY = (int)(Math.random() * 400.0);
        this.fishX = (int)(Math.random() * 450.0);
        this.fishY = (int)(Math.random() * 400.0);
        this.foodX = (int)(Math.random() * 450.0);
        this.foodY = (int)(Math.random() * 400.0);
    }
    
    public void loadSounds() {
        try {
            this.auTube = this.getAudioClip(new URL(this.getCodeBase(), "tube.au"));
            this.auOuch = this.getAudioClip(new URL(this.getCodeBase(), "ouch.au"));
            this.auWhale = this.getAudioClip(new URL(this.getCodeBase(), "whale.au"));
            this.auYum = this.getAudioClip(new URL(this.getCodeBase(), "yum.au"));
        }
        catch (MalformedURLException ex) {}
        this.auTube.play();
        this.auTube.stop();
        this.auOuch.play();
        this.auOuch.stop();
        this.auWhale.play();
        this.auWhale.stop();
        this.auYum.play();
        this.auYum.stop();
    }
    
    public void paint(final Graphics g) {
        if (!this.gameOver) {
            g.drawImage(this.water[this.waterNum], 0, 0, this);
            for (int i = 0; i < this.whaleX.length; ++i) {
                g.drawImage(this.whale[this.whaleNum], this.whaleX[i] - this.whaleW[this.whaleNum] / 2, this.whaleY[i] - this.whaleH[this.whaleNum] / 2, this);
            }
            g.drawImage(this.jonah, this.jonahX - 12, this.jonahY - 12, this);
            if (this.food != null) {
                g.drawImage(this.food, this.foodX - this.foodW / 2, this.foodY - this.foodH / 2, this);
            }
            if (this.fish != null) {
                g.drawImage(this.fish, this.fishX - this.fishW / 2, this.fishY - this.fishH / 2, this);
            }
            if (this.tube != null) {
                g.drawImage(this.tube, this.innerX - 12, this.innerY - 12, this);
            }
            g.setColor(Color.red);
            g.setFont(this.scoreFont);
            final String drawTime = "Score:  " + String.valueOf(this.totalTime);
            final String allTime = "Best:  " + String.valueOf(this.bestTime);
            g.drawString(drawTime, 450 - this.fm2.stringWidth(drawTime) - 4, this.fm2.getHeight() + 2);
            g.drawString(allTime, 2, this.fm2.getHeight() + 4);
        }
        if (this.gameOver) {
            g.drawImage(this.water[0], 0, 0, this);
            g.drawImage(this.jonahend, this.getSize().width / 2 - this.jonahend.getWidth(this) / 2, this.getSize().height / 2 - this.jonahend.getHeight(this) / 2, this);
            g.setColor(Color.red);
            g.setFont(this.theFont);
            final String endString = "Game Over: Click 'Shift' + 's' to play again!";
            g.drawString(endString, (450 - this.fm.stringWidth(endString)) / 2, (400 - this.fm.getHeight()) / 2);
            g.setFont(this.scoreFont);
            final String drawTime = "Score:  " + String.valueOf(this.totalTime);
            final String allTime = "Best:  " + String.valueOf(this.bestTime);
            g.drawString(drawTime, 450 - this.fm2.stringWidth(drawTime) - 4, this.fm2.getHeight() + 2);
            g.drawString(allTime, 2, this.fm2.getHeight() + 4);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void run() {
        while (!this.gameOver) {
            this.repaint();
            Thread.yield();
            try {
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {}
            if (this.odd == 0) {
                ++this.waterNum;
                if (this.waterNum > 2) {
                    this.waterNum = 0;
                }
                this.odd = 1;
            }
            else {
                this.odd = 0;
            }
            if (this.fishCount > -1) {
                if (this.fishCount <= 4) {
                    this.fish = this.fish1;
                }
                if (this.fishCount > 4) {
                    this.fish = this.fish2;
                }
                ++this.fishCount;
            }
            if (this.foodCount > -1) {
                if (this.foodCount <= 5) {
                    this.food = this.food1;
                }
                if (this.foodCount > 5) {
                    this.food = this.food2;
                }
                ++this.foodCount;
            }
            if (this.tubeCount > -1) {
                if (this.tubeCount <= 7) {
                    this.tube = this.tube1;
                }
                if (this.tubeCount > 7) {
                    this.tube = this.tube2;
                }
                ++this.tubeCount;
            }
            final int num = (int)(Math.random() * 100.0);
            if (num > 20 && num < 26 && this.fishCount == -1) {
                this.fishCount = 0;
                this.fishX = (int)(Math.random() * 450.0);
                this.fishY = (int)(Math.random() * 400.0);
            }
            else if (this.fishCount > 6) {
                this.gotFish = false;
                this.fishCount = -1;
                this.fish = null;
            }
            final int num2 = (int)(Math.random() * 100.0);
            if (num2 > 50 && num2 < 56 && this.foodCount == -1) {
                this.foodCount = 0;
                this.foodX = (int)(Math.random() * 450.0);
                this.foodY = (int)(Math.random() * 400.0);
            }
            else if (this.foodCount > 8) {
                this.gotFood = false;
                this.foodCount = -1;
                this.food = null;
            }
            if (Math.random() * 100.0 < 5.0 && this.tubeCount == -1) {
                this.tubeCount = 0;
                this.innerX = (int)(Math.random() * 450.0);
                this.innerY = (int)(Math.random() * 400.0);
            }
            else if (this.tubeCount > 10) {
                this.gotTube = false;
                this.tubeCount = -1;
                this.tube = null;
            }
            final Rectangle jonahRect = new Rectangle(this.jonahX - 12, this.jonahY - 12, 25, 25);
            if (this.tubeCount > -1 && !this.gotTube) {
                final Rectangle tubeRect = new Rectangle(this.innerX - 12, this.innerY - 12, 25, 25);
                if (tubeRect.intersects(jonahRect)) {
                    this.auTube.play();
                    this.totalTime += 25;
                    this.tubeCount = 0;
                    this.gotTube = true;
                }
            }
            if (this.foodCount > -1 && !this.gotFood) {
                final Rectangle foodRect = new Rectangle(this.foodX - this.foodW / 2, this.foodY - this.foodH / 2, this.foodW, this.foodH);
                if (foodRect.intersects(jonahRect)) {
                    this.auTube.play();
                    this.totalTime += 40;
                    this.foodCount = 0;
                    this.gotFood = true;
                }
            }
            if (this.fishCount > -1 && !this.gotFish) {
                final Rectangle fishRect = new Rectangle(this.fishX - this.fishW / 2, this.fishY - this.fishH / 2, this.fishW, this.fishH);
                if (fishRect.intersects(jonahRect)) {
                    this.auTube.play();
                    this.totalTime += 50;
                    this.fishCount = 0;
                    this.gotFish = true;
                }
            }
            ++this.totalTime;
            ++this.whaleNum;
            if (this.whaleNum == 2 && this.whaleX.length > 0) {
                this.auWhale.play();
            }
            if (this.whaleNum > 2) {
                this.whaleNum = 0;
                if (this.whaleCount < 100) {
                    this.whaleCount += 5;
                }
                for (int i = 0; i < this.whaleX.length; ++i) {
                    this.whaleX[i] = (int)(Math.random() * 450.0);
                    this.whaleY[i] = (int)(Math.random() * 400.0);
                }
            }
            if (!this.gotFish && !this.gotTube && this.whaleNum == 2) {
                for (int i = 0; i < this.whaleX.length; ++i) {
                    final Rectangle whaleRect = new Rectangle(this.whaleX[i] - this.whaleW[this.whaleNum] / 2, this.whaleY[i] - this.whaleH[this.whaleNum] / 2, this.whaleW[this.whaleNum], this.whaleH[this.whaleNum]);
                    if (whaleRect.intersects(jonahRect)) {
                        this.auOuch.play();
                        if (this.totalTime > this.bestTime) {
                            this.bestTime = this.totalTime;
                        }
                        this.gameOver = true;
                        this.repaint();
                        this.runner.stop();
                    }
                }
            }
            if (this.whaleCount > 60 && this.whaleCount < 100) {
                final int[] tmpX = new int[this.whaleX.length + 1];
                final int[] tmpY = new int[this.whaleY.length + 1];
                this.whaleX = tmpX;
                this.whaleY = tmpY;
                for (int j = 0; j < this.whaleX.length; ++j) {
                    this.whaleX[j] = (int)(Math.random() * 450.0);
                    this.whaleY[j] = (int)(Math.random() * 400.0);
                }
                this.whaleCount = 0;
            }
            if (this.totalTime > 2500) {
                this.speed = 750;
            }
            else if (this.totalTime > 2000) {
                this.speed = 800;
            }
            else if (this.totalTime > 1500) {
                this.speed = 850;
            }
            else if (this.totalTime > 1000) {
                this.speed = 900;
            }
            else {
                if (this.totalTime <= 500) {
                    continue;
                }
                this.speed = 950;
            }
        }
    }
    
    public void keyPressed(final KeyEvent evt) {
    }
    
    public void keyReleased(final KeyEvent evt) {
    }
    
    public void keyTyped(final KeyEvent evt) {
    }
    
    public void processKeyEvent(final KeyEvent event) {
        if (event.getID() != 401) {
            return;
        }
        final String key = KeyEvent.getKeyText(event.getKeyCode());
        if (!this.gameOver && key.equals("P")) {
            if (this.runner != null) {
                this.runner.stop();
                this.runner = null;
            }
            else {
                (this.runner = new Thread(this)).start();
            }
        }
        if (!this.gameOver && this.runner != null) {
            final Graphics g = this.getGraphics();
            g.setClip(this.jonahX - 30, this.jonahY - 30, 60, 60);
            this.update(g);
            if (key.equals("Right")) {
                this.jonah = this.swim[1];
                if (this.gotFood) {
                    this.rightNum += 3;
                }
                else {
                    ++this.rightNum;
                }
                this.jonahX += this.rightNum / 3;
                if (this.jonahX > 438) {
                    this.jonahX = 438;
                }
                this.leftNum = 1;
                this.upNum = 1;
                this.downNum = 1;
            }
            if (key.equals("Left")) {
                this.jonah = this.swim[3];
                if (this.gotFood) {
                    this.leftNum += 3;
                }
                else {
                    ++this.leftNum;
                }
                this.jonahX -= this.leftNum / 3;
                if (this.jonahX < 12) {
                    this.jonahX = 12;
                }
                this.rightNum = 1;
                this.upNum = 1;
                this.downNum = 1;
            }
            if (key.equals("Up")) {
                this.jonah = this.swim[0];
                if (this.gotFood) {
                    this.upNum += 3;
                }
                else {
                    ++this.upNum;
                }
                this.jonahY -= this.upNum / 3;
                if (this.jonahY < 12) {
                    this.jonahY = 12;
                }
                this.rightNum = 1;
                this.downNum = 1;
                this.leftNum = 1;
            }
            if (key.equals("Down")) {
                this.jonah = this.swim[2];
                if (this.gotFood) {
                    this.downNum += 3;
                }
                else {
                    ++this.downNum;
                }
                this.jonahY += this.downNum / 3;
                if (this.jonahY > 388) {
                    this.jonahY = 388;
                }
                this.leftNum = 1;
                this.upNum = 1;
                this.rightNum = 1;
            }
            if (this.gotTube) {
                this.innerX = this.jonahX;
                this.innerY = this.jonahY;
            }
            if (this.gotFish) {
                this.fishX = this.jonahX;
                this.fishY = this.jonahY;
            }
            if (this.gotFood) {
                this.foodX = this.jonahX;
                this.foodY = this.jonahY;
            }
            g.setClip(this.jonahX - 12, this.jonahY - 12, 32, 32);
            g.drawImage(this.jonah, this.jonahX - 12, this.jonahY - 12, this);
            if (this.food != null) {
                g.drawImage(this.food, this.foodX - this.foodW / 2, this.foodY - this.foodH / 2, this);
            }
            if (this.fish != null) {
                g.drawImage(this.fish, this.fishX - this.fishW / 2, this.fishY - this.fishH / 2, this);
            }
            if (this.tube != null) {
                g.drawImage(this.tube, this.innerX - 12, this.innerY - 12, this);
            }
            if (this.tubeCount > -1 && !this.gotTube) {
                final Rectangle tubeRect = new Rectangle(this.innerX - 12, this.innerY - 12, 25, 25);
                final Rectangle jonahRect = new Rectangle(this.jonahX - 12, this.jonahY - 12, 25, 25);
                if (tubeRect.intersects(jonahRect)) {
                    this.auTube.play();
                    this.totalTime += 25;
                    this.tubeCount = 0;
                    this.gotTube = true;
                }
            }
            if (this.fishCount > -1 && !this.gotFish) {
                final Rectangle fishRect = new Rectangle(this.fishX - this.fishW / 2, this.fishY - this.fishH / 2, this.fishW, this.fishH);
                final Rectangle jonahRect = new Rectangle(this.jonahX - 12, this.jonahY - 12, 25, 25);
                if (fishRect.intersects(jonahRect)) {
                    this.auTube.play();
                    this.totalTime += 50;
                    this.fishCount = 0;
                    this.gotFish = true;
                }
            }
            if (this.foodCount > -1 && !this.gotFood) {
                final Rectangle foodRect = new Rectangle(this.foodX - this.foodW / 2, this.foodY - this.foodH / 2, this.foodW, this.foodH);
                final Rectangle jonahRect = new Rectangle(this.jonahX - 12, this.jonahY - 12, 25, 25);
                if (foodRect.intersects(jonahRect)) {
                    this.auYum.play();
                    this.totalTime += 40;
                    this.foodCount = 0;
                    this.gotFood = true;
                }
            }
            if (this.whaleNum == 2 && this.gotFish) {
                final Rectangle fishRect = new Rectangle(this.fishX - this.fishW / 2, this.fishY - this.fishH / 2, this.fishW, this.fishH);
                for (int i = 0; i < this.whaleX.length; ++i) {
                    final Rectangle whaleRect = new Rectangle(this.whaleX[i] - this.whaleW[2] / 2, this.whaleY[i] - this.whaleH[2] / 2, this.whaleW[2], this.whaleH[2]);
                    if (whaleRect.intersects(fishRect)) {
                        this.totalTime += 50;
                        final int[] tmpX = new int[this.whaleX.length - 1];
                        final int[] tmpY = new int[this.whaleX.length - 1];
                        int cnt = 0;
                        for (int i2 = 0; i2 < this.whaleX.length; ++i2) {
                            if (i2 != i) {
                                tmpX[cnt] = this.whaleX[i2];
                                tmpY[cnt] = this.whaleY[i2];
                                this.whaleCount = 0;
                                this.whaleNum = 0;
                                ++cnt;
                            }
                        }
                        this.whaleX = tmpX;
                        this.whaleY = tmpY;
                        break;
                    }
                }
            }
            if (!this.gotFish && !this.gotTube && this.whaleNum == 2) {
                final Rectangle jonahRect2 = new Rectangle(this.jonahX - 12, this.jonahY - 12, 25, 25);
                for (int i = 0; i < this.whaleX.length; ++i) {
                    final Rectangle whaleRect = new Rectangle(this.whaleX[i] - this.whaleW[this.whaleNum] / 2, this.whaleY[i] - this.whaleH[this.whaleNum] / 2, this.whaleW[this.whaleNum], this.whaleH[this.whaleNum]);
                    if (whaleRect.intersects(jonahRect2)) {
                        this.gameOver = true;
                        this.repaint();
                        this.auOuch.play();
                        if (this.totalTime > this.bestTime) {
                            this.bestTime = this.totalTime;
                        }
                        this.runner.stop();
                    }
                }
            }
        }
        else if (key.equals("S")) {
            this.totalTime = 0;
            this.whaleX = new int[1];
            this.whaleY = new int[1];
            this.whaleX[0] = (int)Math.random() * 450;
            this.whaleY[0] = (int)Math.random() * 400;
            this.whaleNum = 0;
            this.whaleCount = 0;
            this.gameOver = false;
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
        }
        this.gameOver = true;
    }
    
    public jonahApplet() {
        this.theFont = new Font("Times New Roman", 1, 20);
        this.scoreFont = new Font("Times New Roman", 1, 18);
        this.speed = 1000;
        this.whaleW = new int[3];
        this.whaleH = new int[3];
        this.gotTube = false;
        this.gotFish = false;
        this.gameOver = true;
        this.gotFood = false;
        this.foodCount = -1;
        this.tubeCount = -1;
        this.fishCount = -1;
        this.swim = new Image[4];
        this.water = new Image[3];
        this.whale = new Image[3];
        this.jonahX = 200;
        this.jonahY = 225;
        this.whaleX = new int[1];
        this.whaleY = new int[1];
        this.rightNum = 1;
        this.leftNum = 1;
        this.upNum = 1;
        this.downNum = 1;
    }
}
