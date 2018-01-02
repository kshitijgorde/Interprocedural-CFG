import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import symantec.itools.lang.Context;
import symantec.itools.awt.WrappingLabel;
import symantec.itools.awt.BorderPanel;
import java.awt.Button;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SharkAttack extends Applet implements Runnable
{
    Image bufferImg;
    Graphics buffer;
    Image sky;
    Image sea;
    Image title;
    Image gameOverMess;
    Image[] sharks;
    Image[] deaths;
    int viewY;
    MediaTracker tracker;
    int[] seaOffs;
    int[] seaDirs;
    int titleStart;
    int titleEnd;
    int titleZ;
    int titleY;
    int titleX;
    int titleSpeed;
    int gameOverX;
    transient Thread sharkThread;
    static final int initSharkLife = 50;
    int sharkLife;
    static final int INTRO = 0;
    static final int PLAY = 1;
    static final int GAMEOVER = 2;
    static final int START = 3;
    static final int LOADING = 4;
    int mode;
    int progress;
    boolean error;
    int startTime;
    static final int gameTime = 30;
    int timer;
    int turnCount;
    int score;
    public int hiScore;
    static final int initSharkDelay = 50;
    int sharkDelay;
    int sharkDelayCount;
    static final int MAXSHARKS = 20;
    Shark[] sharkees;
    int[] scoreToBeat;
    String[] messages;
    Label errorMess;
    Button playButton;
    Label scoreLabel;
    Label scoreDisplay;
    Label timeLabel;
    Label timeDisplay;
    Label topLabel;
    Label topDisplay;
    Label Copyright;
    BorderPanel highScoreMessage;
    WrappingLabel highScoreText;
    
    public void init() {
        Context.setApplet(this);
        this.setLayout(null);
        this.setSize(400, 275);
        this.setFont(new Font("Dialog", 0, 12));
        this.setForeground(new Color(16777215));
        this.setBackground(new Color(0));
        (this.errorMess = new Label("Image Load Error!!!", 1)).setVisible(false);
        this.errorMess.setBounds(0, 255, 400, 20);
        this.errorMess.setFont(new Font("Dialog", 1, 12));
        this.errorMess.setForeground(new Color(16711680));
        this.add(this.errorMess);
        (this.playButton = new Button()).setActionCommand("button");
        this.playButton.setLabel("Play");
        this.playButton.setBounds(325, 255, 75, 20);
        this.playButton.setFont(new Font("Dialog", 1, 12));
        this.playButton.setForeground(new Color(0));
        this.playButton.setBackground(new Color(12632256));
        this.add(this.playButton);
        this.playButton.setEnabled(false);
        (this.scoreLabel = new Label("Score:")).setBounds(0, 255, 48, 20);
        this.scoreLabel.setFont(new Font("Dialog", 1, 12));
        this.add(this.scoreLabel);
        (this.scoreDisplay = new Label("0", 1)).setBounds(48, 255, 36, 20);
        this.scoreDisplay.setFont(new Font("Dialog", 1, 12));
        this.add(this.scoreDisplay);
        (this.timeLabel = new Label("Time:")).setBounds(96, 255, 40, 20);
        this.timeLabel.setFont(new Font("Dialog", 1, 12));
        this.add(this.timeLabel);
        (this.timeDisplay = new Label("30", 1)).setBounds(132, 255, 40, 20);
        this.timeDisplay.setFont(new Font("Dialog", 1, 12));
        this.add(this.timeDisplay);
        (this.topLabel = new Label("Top:")).setBounds(228, 255, 40, 20);
        this.topLabel.setFont(new Font("Dialog", 1, 12));
        this.add(this.topLabel);
        (this.topDisplay = new Label("0", 1)).setBounds(276, 255, 36, 20);
        this.topDisplay.setFont(new Font("Dialog", 1, 12));
        this.add(this.topDisplay);
        (this.Copyright = new Label("Shark Attack                           (c) www.midlandpcservices.co.uk", 1)).setBounds(0, 0, 400, 20);
        this.Copyright.setFont(new Font("Dialog", 1, 12));
        this.add(this.Copyright);
        this.highScoreMessage = new BorderPanel();
        try {
            this.highScoreMessage.setLabel("High Score");
        }
        catch (PropertyVetoException ex) {}
        try {
            this.highScoreMessage.setBorderColor(new Color(16777215));
        }
        catch (PropertyVetoException ex2) {}
        try {
            this.highScoreMessage.setLabelColor(new Color(16777215));
        }
        catch (PropertyVetoException ex3) {}
        this.highScoreMessage.setLayout(null);
        this.highScoreMessage.setVisible(false);
        this.highScoreMessage.setBounds(25, 84, 350, 72);
        this.highScoreMessage.setFont(new Font("Dialog", 1, 14));
        this.add(this.highScoreMessage);
        this.highScoreText = new WrappingLabel();
        try {
            this.highScoreText.setText("Nice One!");
        }
        catch (PropertyVetoException ex4) {}
        try {
            this.highScoreText.setAlignStyle(1);
        }
        catch (PropertyVetoException ex5) {}
        this.highScoreText.setBounds(3, 0, 324, 31);
        this.highScoreText.setFont(new Font("Dialog", 1, 12));
        this.highScoreMessage.add(this.highScoreText);
        this.bufferImg = this.createImage(400, 235);
        this.buffer = this.bufferImg.getGraphics();
        this.tracker = new MediaTracker(this);
        int idNow = 0;
        this.sky = this.getImage(this.getDocumentBase(), "images/sky.jpg");
        this.sea = this.getImage(this.getDocumentBase(), "images/sea.gif");
        this.title = this.getImage(this.getDocumentBase(), "images/title.gif");
        this.gameOverMess = this.getImage(this.getDocumentBase(), "images/gameover.gif");
        this.tracker.addImage(this.sky, idNow);
        ++idNow;
        this.tracker.addImage(this.sea, idNow);
        ++idNow;
        this.tracker.addImage(this.title, idNow);
        ++idNow;
        this.tracker.addImage(this.gameOverMess, idNow);
        ++idNow;
        for (int i = 0; i < 6; ++i) {
            this.sharks[i] = this.getImage(this.getDocumentBase(), "images/shark" + (i + 1) + ".gif");
            this.deaths[i] = this.getImage(this.getDocumentBase(), "images/shark" + (i + 1) + "a.gif");
            this.tracker.addImage(this.sharks[i], idNow);
            ++idNow;
            this.tracker.addImage(this.deaths[i], idNow);
            ++idNow;
        }
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex6) {}
        this.resetSharks();
        final SymMouse aSymMouse = new SymMouse();
        this.addMouseListener(aSymMouse);
        this.playButton.addMouseListener(aSymMouse);
    }
    
    public int getHiScore() {
        return this.hiScore;
    }
    
    public void run() {
        this.sharkThread.setPriority(10);
        if (this.mode == 4) {
            this.repaint();
            for (int i = 0; i < 16; ++i) {
                while (true) {
                    try {
                        this.tracker.waitForID(i);
                    }
                    catch (InterruptedException ex) {
                        Thread.yield();
                    }
                    final boolean c = this.tracker.checkID(i);
                    final int s = this.tracker.statusID(i, true);
                    if (c && (s & 0x8) == 0x8) {
                        break;
                    }
                    if ((s & 0x2) == 0x2 || (s & 0x4) == 0x4) {
                        this.error = true;
                        break;
                    }
                    Thread.yield();
                    try {
                        Thread.sleep(100L);
                    }
                    catch (Exception ex2) {}
                }
                ++this.progress;
                this.repaint();
            }
            if (this.tracker.isErrorAny() || this.error) {
                this.showError();
            }
            this.mode = 3;
            this.playButton.setEnabled(true);
        }
        while (true) {
            this.moveSea();
            if (this.mode == 3) {
                this.setUpIntro();
            }
            if (this.mode == 0) {
                this.moveTitle();
                this.repaint();
            }
            if (this.mode == 1) {
                ++this.sharkDelayCount;
                if (this.sharkDelayCount >= this.sharkDelay) {
                    this.addShark();
                    this.sharkDelayCount = 0;
                }
                this.moveSharks();
                if (this.titleY > -25) {
                    this.titleY -= 10;
                }
                this.repaint();
                ++this.turnCount;
                if (this.turnCount == 10) {
                    --this.timer;
                    this.turnCount = 0;
                    if (this.timer == 0) {
                        this.endGame();
                    }
                    this.updateStats();
                }
            }
            if (this.mode == 2) {
                this.moveMess();
                this.diveSharks();
                this.repaint();
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex3) {}
        }
    }
    
    void showError() {
        this.errorMess.setVisible(true);
        this.stop();
    }
    
    void setUpIntro() {
        this.titleY = this.titleStart;
        this.mode = 0;
    }
    
    void moveTitle() {
        if (this.titleY > this.titleEnd) {
            this.titleY -= this.titleSpeed;
        }
    }
    
    public void start() {
        (this.sharkThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.sharkThread.stop();
        this.sharkThread = null;
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.bufferImg, 0, this.viewY, this);
    }
    
    public void update(final Graphics g) {
        if (this.mode == 4) {
            this.buffer.setColor(Color.white);
            this.buffer.drawRect(120, 90, 160, 20);
            this.buffer.setClip(121, 91, 159, 19);
            this.buffer.setColor(Color.red);
            this.buffer.fillRect(120, 90, this.progress * 10, 20);
            this.buffer.setColor(Color.black);
            this.buffer.drawString("LOADING", 180, 105);
            this.buffer.setClip(0, 0, 400, 250);
            g.drawImage(this.sky, 0, -250, this);
            if (this.progress >= 1) {
                g.drawImage(this.sea, 100, -25, this);
            }
            if (this.progress >= 2) {
                g.drawImage(this.title, 0, -200, this);
            }
            if (this.progress >= 3) {
                g.drawImage(this.gameOverMess, 0, -200, this);
            }
            if (this.progress >= 4) {
                g.drawImage(this.sharks[0], 0, -95, this);
            }
            if (this.progress >= 5) {
                g.drawImage(this.deaths[0], 0, -175, this);
            }
            if (this.progress >= 6) {
                g.drawImage(this.sharks[1], 100, -95, this);
            }
            if (this.progress >= 7) {
                g.drawImage(this.deaths[1], 100, -175, this);
            }
            if (this.progress >= 8) {
                g.drawImage(this.sharks[2], 200, -95, this);
            }
            if (this.progress >= 9) {
                g.drawImage(this.deaths[2], 200, -175, this);
            }
            if (this.progress >= 10) {
                g.drawImage(this.sharks[3], 300, -95, this);
            }
            if (this.progress >= 11) {
                g.drawImage(this.deaths[3], 300, -175, this);
            }
            if (this.progress >= 12) {
                g.drawImage(this.sharks[4], 400, -95, this);
            }
            if (this.progress >= 13) {
                g.drawImage(this.deaths[4], 400, -175, this);
            }
            if (this.progress >= 14) {
                g.drawImage(this.sharks[5], 500, -95, this);
            }
            if (this.progress >= 15) {
                g.drawImage(this.deaths[5], 500, -175, this);
            }
        }
        else {
            this.buffer.drawImage(this.sky, 0, 0, null);
            this.drawSea();
        }
        this.paint(g);
    }
    
    void moveSea() {
        for (int i = 0; i < 15; ++i) {
            final int[] seaOffs = this.seaOffs;
            final int n = i;
            seaOffs[n] += this.seaDirs[i];
            if (this.seaOffs[i] == -25) {
                this.seaDirs[i] = 1;
            }
            if (this.seaOffs[i] == 0) {
                this.seaDirs[i] = -1;
            }
        }
    }
    
    void drawSea() {
        int seaY = 80;
        int seaX = 0;
        for (int i = 0; i < 15; ++i) {
            seaX = this.seaOffs[i];
            for (int sL = 0; sL < 9; ++sL) {
                this.buffer.drawImage(this.sea, seaX, seaY, null);
                seaX += 50;
            }
            if (this.mode == 1 || this.mode == 2) {
                this.drawSharks(i - 1);
            }
            if (i == this.titleZ && this.mode != 2) {
                this.buffer.drawImage(this.title, this.titleX, this.titleY, null);
            }
            if (i == this.titleZ && this.mode == 2) {
                this.buffer.drawImage(this.gameOverMess, this.gameOverX, this.titleY, null);
            }
            seaY += 10;
        }
    }
    
    void resetSharks() {
        for (int i = 0; i < 20; ++i) {
            this.sharkees[i] = new Shark();
        }
    }
    
    void startGame() {
        this.timer = 30;
        this.turnCount = 0;
        this.score = 0;
        this.resetSharks();
        this.sharkees[0].createShark(50);
        this.sharkLife = 50;
        this.sharkDelayCount = 0;
        this.sharkDelay = 50;
        this.mode = 1;
    }
    
    void endGame() {
        if (this.score > this.hiScore) {
            this.hiScore = this.score;
            for (int i = 9; i > -1; --i) {
                if (this.hiScore > this.scoreToBeat[i]) {
                    try {
                        this.highScoreText.setText(this.messages[i]);
                    }
                    catch (PropertyVetoException ex) {}
                    this.highScoreMessage.setVisible(true);
                    break;
                }
            }
        }
        this.mode = 2;
        this.titleY = this.titleStart;
        this.playButton.setEnabled(true);
    }
    
    void moveMess() {
        if (this.titleY > -25) {
            this.titleY -= this.titleSpeed;
        }
        else {
            this.mode = 3;
            this.highScoreMessage.setVisible(false);
        }
    }
    
    void addShark() {
        int n = -1;
        for (int i = 0; i < 20; ++i) {
            if (this.sharkees[i].mode == 0) {
                n = i;
                break;
            }
        }
        if (n != -1) {
            this.sharkees[n].createShark(this.sharkLife);
        }
    }
    
    void moveSharks() {
        for (int i = 0; i < 20; ++i) {
            final boolean sunk = this.sharkees[i].moveShark();
            if (sunk) {
                if (this.countSharks() < 3) {
                    this.sharkees[i].createShark(this.sharkLife);
                }
                this.slowDown();
            }
        }
    }
    
    void drawSharks(final int sI) {
        for (int i = 0; i < 20; ++i) {
            if (this.sharkees[i].z == sI && this.sharkees[i].mode != 0) {
                final Point pos = this.sharkees[i].getPos();
                if (this.sharkees[i].mode == 1) {
                    this.buffer.drawImage(this.sharks[this.sharkees[i].type], pos.x, pos.y, null);
                }
                if (this.sharkees[i].mode == 2) {
                    this.buffer.drawImage(this.deaths[this.sharkees[i].type], pos.x, pos.y, null);
                }
            }
        }
    }
    
    void killSharks(final int mx, final int my) {
        boolean killedOne = false;
        for (int sZ = 11; sZ > 0; --sZ) {
            for (int i = 19; i > -1; --i) {
                if (this.sharkees[i].z == sZ) {
                    killedOne = this.sharkees[i].killShark(mx, my);
                    if (killedOne) {
                        ++this.score;
                        this.updateStats();
                        this.addShark();
                        this.repaint();
                        this.speedUp();
                        return;
                    }
                }
            }
        }
    }
    
    void diveSharks() {
        for (int i = 0; i < 20; ++i) {
            this.sharkees[i].dive();
        }
    }
    
    void updateStats() {
        this.scoreDisplay.setText(String.valueOf(this.score).toString());
        this.timeDisplay.setText(String.valueOf(this.timer).toString());
        this.topDisplay.setText(String.valueOf(this.hiScore).toString());
    }
    
    void slowDown() {
        if (this.sharkDelay < 50) {
            this.sharkDelay += 5;
        }
        if (this.sharkLife < 50) {
            this.sharkLife += 2;
        }
    }
    
    void speedUp() {
        if (this.sharkDelay > 20) {
            this.sharkDelay -= 5;
        }
        if (this.sharkLife > 25) {
            this.sharkLife -= 2;
        }
    }
    
    int countSharks() {
        int sharkCount = 0;
        for (int i = 0; i < 20; ++i) {
            if (this.sharkees[i].mode == 1) {
                ++sharkCount;
            }
        }
        return sharkCount;
    }
    
    void SharkAttack_MousePress(final MouseEvent event) {
        event.consume();
        if (this.mode == 1) {
            final int kX = event.getX();
            final int kY = event.getY();
            this.killSharks(kX, kY - this.viewY);
        }
    }
    
    void playButton_MouseRelease(final MouseEvent event) {
        this.playButton.setEnabled(false);
        this.highScoreMessage.setVisible(false);
        this.startGame();
    }
    
    public SharkAttack() {
        this.sharks = new Image[6];
        this.deaths = new Image[6];
        this.viewY = 20;
        this.seaOffs = new int[] { -3, -20, 0, -25, -10, -15, 0, -25, -7, -22, -4, -17, -8, -25, -6 };
        this.seaDirs = new int[] { -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1 };
        this.titleStart = 160;
        this.titleEnd = 25;
        this.titleZ = 5;
        this.titleY = this.titleStart;
        this.titleX = 105;
        this.titleSpeed = 5;
        this.gameOverX = 119;
        this.sharkLife = 50;
        this.mode = 4;
        this.error = false;
        this.timer = 30;
        this.sharkDelay = 50;
        this.sharkees = new Shark[20];
        this.scoreToBeat = new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
        this.messages = new String[] { "A Poor High Score!", "20 - Oh Dear...", "You can do much better!!!", "Click Faster!", "Try Cleaning Your Mouse!", "Getting there, but you are not a master yet!", "Good - Over Two Sharks Per Second!", "You'll break your mouse if you're not careful!", "WOW!!!", "You are an honourary Shark Attack Master!!!" };
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mouseReleased(final MouseEvent event) {
            final Object object = event.getSource();
            if (object == SharkAttack.this.playButton) {
                SharkAttack.this.playButton_MouseRelease(event);
            }
        }
        
        public void mousePressed(final MouseEvent event) {
            final Object object = event.getSource();
            if (object == SharkAttack.this) {
                SharkAttack.this.SharkAttack_MousePress(event);
            }
        }
    }
}
