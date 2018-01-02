import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Canvas;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.AudioClip;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class breakout extends Applet implements Runnable
{
    int ballCnt;
    int pauseValue;
    int ballVelY;
    int ballVelX;
    Graphics canGraf;
    int blockWidth;
    int blockHeight;
    int xstart;
    int ystart;
    int xincr;
    int yincr;
    int blocksPerLine;
    int numberOfLines;
    int ballYstart;
    int ballXstart;
    Color color;
    int paddleSize;
    Paddle paddle;
    int paddleWidth;
    int paddleHeight;
    Paddle paddle1;
    int paddle1Width;
    int paddle1Height;
    Paddle paddle2;
    int paddle2Width;
    int paddle2Height;
    Ball2 ball2;
    int ballWidth;
    int ballHeight;
    int count;
    int xcnt;
    int ycnt;
    Image offscreenImg;
    Graphics offscreenG;
    Thread thread;
    int z;
    Vector v;
    Enumeration e;
    Block[] blockArray;
    long startTime;
    Date date;
    int seconds;
    int minutes;
    boolean start;
    int xexit;
    AudioClip hitEdge;
    AudioClip missBall;
    AudioClip hitPaddle;
    AudioClip hitBlock1;
    AudioClip hitBlock2;
    AudioClip hitBlock3;
    AudioClip hitBlock4;
    AudioClip hitBlock5;
    AudioClip win;
    int oldVsize;
    Font f;
    FontMetrics fm;
    int fs;
    Button colorBtn;
    Choice choBlockSize;
    Choice choBallSpeed;
    TextField txtNumBlocks;
    TextField txtBalls;
    Button btnPause;
    Choice choBallSize;
    Choice choPaddle;
    Button btnStart;
    Canvas canvas1;
    TextField txtTime;
    
    public void init() {
        super.init();
        this.setLayout(null);
        this.addNotify();
        this.resize(466, 457);
        this.setBackground(new Color(16776960));
        (this.colorBtn = new Button("Change Color")).reshape(360, 108, 94, 24);
        this.add(this.colorBtn);
        (this.choBlockSize = new Choice()).addItem("");
        this.choBlockSize.addItem("10");
        this.choBlockSize.addItem("15");
        this.choBlockSize.addItem("20");
        this.choBlockSize.addItem("25");
        this.choBlockSize.addItem("30");
        this.choBlockSize.addItem("35");
        this.choBlockSize.addItem("40");
        this.choBlockSize.addItem("50");
        this.choBlockSize.addItem("60");
        this.add(this.choBlockSize);
        this.choBlockSize.reshape(384, 252, 48, 17);
        this.choBlockSize.setBackground(new Color(16777215));
        (this.choBallSpeed = new Choice()).addItem("");
        this.choBallSpeed.addItem("1");
        this.choBallSpeed.addItem("2");
        this.choBallSpeed.addItem("3");
        this.choBallSpeed.addItem("4");
        this.choBallSpeed.addItem("5");
        this.choBallSpeed.addItem("6");
        this.choBallSpeed.addItem("7");
        this.choBallSpeed.addItem("8");
        this.choBallSpeed.addItem("9");
        this.choBallSpeed.addItem("10");
        this.choBallSpeed.addItem("11");
        this.choBallSpeed.addItem("12");
        this.add(this.choBallSpeed);
        this.choBallSpeed.reshape(384, 156, 48, 17);
        this.choBallSpeed.setBackground(new Color(16777215));
        (this.txtNumBlocks = new TextField()).setEditable(false);
        this.txtNumBlocks.reshape(384, 360, 36, 26);
        this.txtNumBlocks.setBackground(new Color(16777215));
        this.add(this.txtNumBlocks);
        (this.txtBalls = new TextField()).setEditable(false);
        this.txtBalls.reshape(384, 408, 36, 26);
        this.txtBalls.setBackground(new Color(16777215));
        this.add(this.txtBalls);
        (this.btnPause = new Button("Resume")).reshape(360, 72, 94, 24);
        this.add(this.btnPause);
        (this.choBallSize = new Choice()).addItem("");
        this.choBallSize.addItem("1");
        this.choBallSize.addItem("2");
        this.choBallSize.addItem("3");
        this.choBallSize.addItem("4");
        this.choBallSize.addItem("5");
        this.choBallSize.addItem("6");
        this.choBallSize.addItem("7");
        this.choBallSize.addItem("8");
        this.choBallSize.addItem("9");
        this.choBallSize.addItem("10");
        this.choBallSize.addItem("11");
        this.choBallSize.addItem("12");
        this.choBallSize.addItem("13");
        this.choBallSize.addItem("14");
        this.choBallSize.addItem("15");
        this.choBallSize.addItem("16");
        this.choBallSize.addItem("17");
        this.choBallSize.addItem("18");
        this.choBallSize.addItem("19");
        this.choBallSize.addItem("20");
        this.add(this.choBallSize);
        this.choBallSize.reshape(384, 300, 48, 17);
        this.choBallSize.setBackground(new Color(16777215));
        (this.choPaddle = new Choice()).addItem("");
        this.choPaddle.addItem("10");
        this.choPaddle.addItem("20");
        this.choPaddle.addItem("30");
        this.choPaddle.addItem("40");
        this.choPaddle.addItem("50");
        this.choPaddle.addItem("60");
        this.choPaddle.addItem("70");
        this.choPaddle.addItem("80");
        this.choPaddle.addItem("90");
        this.choPaddle.addItem("100");
        this.choPaddle.addItem("150");
        this.choPaddle.addItem("200");
        this.add(this.choPaddle);
        this.choPaddle.reshape(384, 204, 48, 17);
        this.choPaddle.setBackground(new Color(16777215));
        (this.btnStart = new Button("Start")).reshape(360, 36, 94, 24);
        this.add(this.btnStart);
        (this.canvas1 = new Canvas()).reshape(12, 36, 337, 408);
        this.canvas1.setBackground(new Color(0));
        this.add(this.canvas1);
        (this.txtTime = new TextField()).setEditable(false);
        this.txtTime.reshape(144, 5, 72, 26);
        this.txtTime.setBackground(new Color(16777215));
        this.add(this.txtTime);
        this.hitEdge = this.getAudioClip(this.getCodeBase(), "edgeHit.au");
        this.hitBlock1 = this.getAudioClip(this.getCodeBase(), "blockHit1.au");
        this.hitBlock2 = this.getAudioClip(this.getCodeBase(), "blockHit2.au");
        this.hitBlock3 = this.getAudioClip(this.getCodeBase(), "blockHit3.au");
        this.hitBlock4 = this.getAudioClip(this.getCodeBase(), "blockHit4.au");
        this.hitBlock5 = this.getAudioClip(this.getCodeBase(), "blockHit5.au");
        this.missBall = this.getAudioClip(this.getCodeBase(), "missBall.au");
        this.hitPaddle = this.getAudioClip(this.getCodeBase(), "paddleHit.au");
        this.win = this.getAudioClip(this.getCodeBase(), "win.au");
        this.choBlockSize.select(Integer.toString(this.blockWidth));
        this.choBallSpeed.select(Integer.toString(this.ballVelY));
        this.choBallSize.select(Integer.toString(this.ballWidth));
        this.choPaddle.select(Integer.toString(this.paddleWidth));
        this.blocksPerLine = this.canvas1.size().width / this.blockWidth;
        while (this.blockWidth * this.blocksPerLine + this.blocksPerLine - this.blockWidth + 1 > this.canvas1.size().width) {
            --this.blocksPerLine;
        }
        this.offscreenImg = this.createImage(this.canvas1.size().width, this.canvas1.size().height);
        this.offscreenG = this.offscreenImg.getGraphics();
        this.paddle = new Paddle(this.paddleWidth, this.paddleHeight, this.canvas1.size().height - this.paddleHeight - 28, this.canvas1);
        this.paddle1 = new Paddle(this.paddle1Width, this.paddle1Height, this.canvas1.size().height - this.paddle1Height - 28, this.canvas1);
        this.paddle2 = new Paddle(this.paddle2Width, this.paddle2Height, this.canvas1.size().height - this.paddle2Height - 28, this.canvas1);
        this.ball2 = new Ball2(this.ballXstart, this.ballYstart, this.ballVelX, this.ballVelY, this.ballWidth, this.ballHeight, this.paddle, this.canvas1, this.paddle1, this.paddle2, this.hitEdge, this.hitPaddle);
        this.setUpBlocks();
        this.paddleSize = this.paddleWidth / 3;
        this.paddleWidth = this.paddleSize * 2;
        this.paddle1Width = this.paddleSize;
        this.paddle2Width = this.paddleSize;
        this.paddle.setWidth(this.paddleWidth);
        this.paddle1.setWidth(this.paddle1Width);
        this.paddle2.setWidth(this.paddle2Width);
        this.paddle.setXpos(this.paddle1Width);
        this.paddle1.setXpos(this.paddle.getXpos() - this.paddle1Width);
        this.paddle2.setXpos(this.paddle.getXpos() + this.paddle.width);
        this.ball2.setColor(Color.red);
        this.paddle.setColor(Color.green);
        this.paddle1.setColor(Color.green);
        this.paddle2.setColor(Color.green);
        this.setText();
        this.txtTime.setText("00:00:00");
        this.getSound();
        this.btnStart.disable();
        this.color = Color.yellow;
        if (System.getProperty("java.vendor").startsWith("Netscape")) {
            this.pauseValue = 15;
        }
        else {
            this.pauseValue = 25;
        }
        this.ball2.setXpos((int)Math.floor(Math.random() * 280.0 + 50.0));
        int temp = (int)Math.floor(Math.random() * 2.0 - 1.0);
        if (temp == 0) {
            temp = 1;
        }
        this.ball2.setXvel(this.ballVelX * temp);
    }
    
    public void start() {
        if (this.thread == null) {
            this.thread = new Thread(this);
        }
        this.thread.start();
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
        }
        this.thread = null;
    }
    
    public void run() {
        while (this.thread.isAlive()) {
            if (this.start) {
                this.setText();
                this.ball2.moveBall();
                synchronized (this.v) {
                    this.ball2.testCollision(this.v);
                }
                // monitorexit(this.v)
                this.testNoBlocks();
                this.checkBall();
            }
            this.repaint();
            this.pause(this.pauseValue);
        }
    }
    
    void setColor() {
        this.color = new Color((int)Math.floor(Math.random() * 256.0), (int)Math.floor(Math.random() * 256.0), (int)Math.floor(Math.random() * 256.0));
        final Graphics g = this.getGraphics();
        g.setColor(this.color);
        g.fillRect(0, 0, this.size().width, this.size().height);
        this.setBackground(this.color);
    }
    
    void setText() {
        this.txtTime.setText(this.setTime());
        final int newVsize = this.v.size();
        if (this.oldVsize != newVsize) {
            this.txtNumBlocks.setText(Integer.toString(newVsize));
        }
        this.oldVsize = this.v.size();
    }
    
    void checkBall() {
        final int ypos = this.ball2.getYpos();
        if (ypos >= this.canvas1.bounds().height + 10) {
            this.missBall.play();
            for (int x = 0; x < 30; ++x) {
                this.repaint();
                this.pause(20);
            }
            this.ball2.setYpos(this.ballYstart);
            this.ball2.setXpos((int)Math.floor(Math.random() * 280.0 + 50.0));
            this.ball2.setYvel(this.ballVelY);
            --this.ballCnt;
            this.txtBalls.setText(Integer.toString(this.ballCnt));
            if (this.ball2.getXvel() > 0) {
                this.ball2.setXvel(this.ballVelX);
            }
            else {
                this.ball2.setXvel(this.ballVelX * -1);
            }
        }
        if (this.ballCnt <= 0) {
            this.start = false;
            this.txtBalls.setText(Integer.toString(this.ballCnt));
            this.ball2.setVel(this.ballVelY, this.ballVelY);
            this.btnPause.disable();
            this.btnPause.setLabel("Pause");
            this.btnStart.enable();
            System.gc();
        }
    }
    
    void setUpBlocks() {
        int z = 0;
        this.xcnt = this.xstart;
        this.ycnt = this.ystart;
        this.ballCnt = 3;
        final Date date = new Date();
        this.startTime = date.getTime();
        this.seconds = 0;
        this.minutes = 0;
        this.txtBalls.setText(Integer.toString(this.ballCnt));
        this.blockArray = new Block[this.numberOfLines * this.blocksPerLine];
        if (this.v != null) {
            this.v.removeAllElements();
        }
        final int r = (int)Math.floor(Math.random() * 256.0);
        int g = (int)Math.floor(Math.random() * 84.0);
        final int b = (int)Math.floor(Math.random() * 256.0);
        for (int i = 0; i < this.numberOfLines; ++i) {
            this.color = new Color(r, g, b);
            for (int x = 0; x < this.blocksPerLine; ++x) {
                this.v.addElement(new Block(this.xcnt, this.ycnt, this.blockWidth, this.blockHeight, this.color, this.ystart, this.hitBlock1, this.hitBlock2, this.hitBlock3, this.hitBlock4, this.hitBlock5));
                this.blockArray[z] = this.v.lastElement();
                ++z;
                this.xcnt += this.xincr;
            }
            this.ycnt += this.yincr;
            this.xcnt = this.xstart;
            g += 43;
        }
        this.txtNumBlocks.setText(Integer.toString(this.v.size()));
        this.oldVsize = this.v.size();
    }
    
    void testNoBlocks() {
        if (this.v.size() > 0) {
            return;
        }
        this.txtNumBlocks.setText(Integer.toString(this.v.size()));
        this.start = false;
        this.btnPause.disable();
        for (int x = 0; x < 30; ++x) {
            this.repaint();
            this.pause(20);
        }
        this.win.play();
        System.gc();
    }
    
    void pause(final int time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException ex) {}
    }
    
    public void update(final Graphics g) {
        this.offscreenG.setColor(Color.black);
        this.offscreenG.fillRect(0, 0, this.size().width, this.size().height);
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.black);
        g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        g.drawString("Ball Speed", 380, 150);
        g.drawString("Paddle Size", 380, 195);
        g.drawString("Block Size", 380, 240);
        g.drawString("Ball Size", 380, 290);
        g.drawString("Blocks Left", 380, 350);
        g.drawString("Balls Left", 380, 400);
        if (this.ballCnt > 0 && this.v.size() > 0) {
            this.ball2.paint(this.offscreenG);
        }
        this.paddle.paint(this.offscreenG);
        this.paddle1.paint(this.offscreenG);
        this.paddle2.paint(this.offscreenG);
        if (this.ballCnt <= 0) {
            this.offscreenG.setColor(Color.red);
            this.f = new Font("TimesRoman", 1, 36);
            this.offscreenG.setFont(this.f);
            this.fm = this.offscreenG.getFontMetrics();
            this.fs = this.fm.stringWidth("Game Over");
            this.offscreenG.drawString("Game Over", (this.canvas1.bounds().width - this.fs) / 2, 225);
            this.f = new Font("TimesRoman", 1, 20);
            this.offscreenG.setFont(this.f);
            this.fm = this.offscreenG.getFontMetrics();
            this.fs = this.fm.stringWidth("Press Start");
            this.offscreenG.drawString("Press Start", (this.canvas1.bounds().width - this.fs) / 2, 30);
        }
        if (this.btnPause.getLabel().equals("Resume")) {
            this.offscreenG.setColor(Color.red);
            this.f = new Font("TimesRoman", 1, 20);
            this.offscreenG.setFont(this.f);
            this.fm = this.offscreenG.getFontMetrics();
            this.fs = this.fm.stringWidth("Press Resume to Start");
            this.offscreenG.drawString("Press Resume to Start", (this.canvas1.bounds().width - this.fs) / 2, 30);
        }
        if (this.v.size() <= 0) {
            this.offscreenG.setColor(Color.yellow);
            this.f = new Font("TimesRoman", 1, 36);
            this.offscreenG.setFont(this.f);
            this.fm = this.offscreenG.getFontMetrics();
            this.fs = this.fm.stringWidth("You Win!");
            this.offscreenG.drawString("You Win!", (this.canvas1.bounds().width - this.fs) / 2, 225);
            this.offscreenG.setColor(Color.red);
            this.f = new Font("TimesRoman", 1, 20);
            this.offscreenG.setFont(this.f);
            this.fm = this.offscreenG.getFontMetrics();
            this.fs = this.fm.stringWidth("Press Start");
            this.offscreenG.drawString("Press Start", (this.canvas1.bounds().width - this.fs) / 2, 30);
        }
        synchronized (this.v) {
            for (int i = 0; i < this.v.size(); ++i) {
                final Block block = this.v.elementAt(i);
                block.paint(this.offscreenG);
            }
        }
        // monitorexit(this.v)
        (this.canGraf = this.canvas1.getGraphics()).drawImage(this.offscreenImg, 0, 0, this);
    }
    
    String setTime() {
        this.date = new Date();
        final long thisTime = this.date.getTime();
        final long diffTime = thisTime - this.startTime;
        long hundOfSec = diffTime / 10L;
        if (hundOfSec > 99L) {
            this.startTime = thisTime;
            hundOfSec = 0L;
            ++this.seconds;
        }
        if (this.seconds > 59) {
            this.seconds = 0;
            ++this.minutes;
        }
        String sec = Long.toString(this.seconds);
        if (this.seconds <= 9) {
            sec = "0" + sec;
        }
        String hund = Long.toString(hundOfSec);
        if (hundOfSec <= 9L) {
            hund = "0" + hund;
        }
        String min = Long.toString(this.minutes);
        if (this.minutes <= 9) {
            min = "0" + min;
        }
        return min + ":" + sec + ":" + hund;
    }
    
    public boolean action(final Event event, final Object arg) {
        if (event.target == this.choPaddle && this.choPaddle.getSelectedItem() != "") {
            this.paddleWidth = Integer.valueOf(this.choPaddle.getSelectedItem());
            this.paddleSize = this.paddleWidth / 3;
            this.paddleWidth = this.paddleSize * 2;
            this.paddle1Width = this.paddleSize;
            this.paddle2Width = this.paddleSize;
            this.paddle.setWidth(this.paddleWidth);
            this.paddle1.setWidth(this.paddle1Width);
            this.paddle2.setWidth(this.paddle2Width);
            this.paddle.setXpos(this.canvas1.bounds().width - this.paddle.getWidth() - this.paddle1.getWidth());
            this.paddle1.setXpos(this.paddle.getXpos() - this.paddle1.getWidth());
            this.paddle2.setXpos(this.paddle.getXpos() + this.paddle.getWidth());
        }
        if (event.target == this.btnPause && this.ballCnt > 0) {
            if (this.btnPause.getLabel().equals("Pause")) {
                this.start = false;
                this.btnPause.setLabel("Resume");
                this.btnStart.disable();
            }
            else {
                this.start = true;
                this.btnPause.setLabel("Pause");
                this.startTime = this.date.getTime();
                this.btnStart.enable();
            }
        }
        if (event.target == this.choBallSize && this.choBallSize.getSelectedItem() != "") {
            this.ballWidth = Integer.valueOf(this.choBallSize.getSelectedItem());
            this.ballHeight = Integer.valueOf(this.choBallSize.getSelectedItem());
            this.ball2.setSize(this.ballWidth, this.ballHeight);
        }
        if (event.target == this.choBallSpeed && this.choBallSpeed.getSelectedItem() != "") {
            int ballVelX;
            int ballVelY = ballVelX = Integer.valueOf(this.choBallSpeed.getSelectedItem());
            this.ballVelY = ballVelY;
            this.ballVelX = ballVelX;
            if (this.ball2.getYvel() < 0) {
                ballVelY *= -1;
            }
            if (this.ball2.getXvel() < 0) {
                ballVelX *= -1;
            }
            this.ball2.setVel(ballVelX, ballVelY);
        }
        if (event.target == this.choBlockSize && this.choBlockSize.getSelectedItem() != "") {
            this.blockWidth = Integer.valueOf(this.choBlockSize.getSelectedItem());
            this.blockHeight = Integer.valueOf(this.choBlockSize.getSelectedItem());
            this.xincr = this.blockWidth + 1;
            this.yincr = this.blockHeight + 1;
            this.blocksPerLine = this.canvas1.size().width / this.blockWidth;
            while (this.blockWidth * this.blocksPerLine + this.blocksPerLine - this.blockWidth + 1 > this.canvas1.size().width) {
                --this.blocksPerLine;
            }
            this.setUpBlocks();
            this.btnPause.setLabel("Resume");
            this.btnPause.enable();
            this.btnStart.disable();
            this.ball2.setYpos(this.ballYstart);
            if (this.ball2.getXpos() < 12 || this.ball2.getXpos() > this.canvas1.bounds().width) {
                this.ball2.setXpos(this.ballXstart);
            }
            final int temp = Integer.valueOf(this.choBallSpeed.getSelectedItem());
            this.ball2.setVel(temp, temp);
            this.txtTime.setText("00:00:00");
            this.start = false;
        }
        if (event.target == this.btnStart) {
            this.start = true;
            this.setUpBlocks();
            this.ball2.setYpos(this.ballYstart);
            this.ball2.setXpos((int)Math.floor(Math.random() * 280.0 + 50.0));
            final int speed = Integer.valueOf(this.choBallSpeed.getSelectedItem());
            this.ball2.setYvel(speed);
            int temp2 = (int)Math.floor(Math.random() * 2.0 - 1.0);
            if (temp2 == 0) {
                temp2 = 1;
            }
            this.ball2.setXvel(speed * temp2);
            this.btnPause.enable();
            this.btnPause.setLabel("Pause");
        }
        if (event.target == this.colorBtn) {
            int vcnt = 0;
            int acnt = 0;
            this.setColor();
            this.pause(5);
            this.ball2.setColor();
            this.pause(5);
            this.paddle.setColor();
            this.paddle1.setColor(this.paddle.getColor());
            this.paddle2.setColor(this.paddle.getColor());
            this.pause(5);
            final int r = (int)Math.floor(Math.random() * 256.0);
            int g = (int)Math.floor(Math.random() * 84.0);
            final int b = (int)Math.floor(Math.random() * 256.0);
            for (int i = 0; i < this.numberOfLines; ++i) {
                this.color = new Color(r, g, b);
                for (int x = 0; x < this.blocksPerLine; ++x) {
                    if (vcnt + x >= this.v.size()) {
                        return true;
                    }
                    final Block blockV = this.v.elementAt(vcnt + x);
                    final Block blockA = this.blockArray[acnt + x];
                    if (this.blockSame(blockV, blockA)) {
                        blockV.setColor(this.color);
                    }
                    else {
                        --vcnt;
                    }
                }
                vcnt += this.blocksPerLine;
                acnt += this.blocksPerLine;
                g += 43;
            }
        }
        return super.action(event, arg);
    }
    
    boolean blockSame(final Block v, final Block a) {
        return v.xpos == a.xpos && v.ypos == a.ypos;
    }
    
    public boolean mouseMove(final Event evt, int x, final int y) {
        if (!this.start) {
            return false;
        }
        final int paddleStart = x - this.paddle.getWidth() / 2 - this.paddle1.getWidth() - 12;
        final int paddleEnd = x + this.paddle.getWidth() / 2 + this.paddle1.getWidth() - 12;
        if (paddleStart <= 0) {
            this.paddle.setXpos(this.paddle1.getWidth());
            this.paddle1.setXpos(this.paddle.getXpos() - this.paddle1.getWidth());
            this.paddle2.setXpos(this.paddle.getXpos() + this.paddle.getWidth());
            return true;
        }
        if (paddleEnd >= this.canvas1.bounds().width) {
            this.paddle.setXpos(this.canvas1.bounds().width - this.paddle.getWidth() - this.paddle1.getWidth());
            this.paddle1.setXpos(this.paddle.getXpos() - this.paddle1.getWidth());
            this.paddle2.setXpos(this.paddle.getXpos() + this.paddle.getWidth());
            return true;
        }
        x -= this.paddle.getWidth() / 2 + 12;
        this.paddle.setXpos(x);
        this.paddle1.setXpos(x - this.paddle1.getWidth());
        this.paddle2.setXpos(x + this.paddle.getWidth());
        return true;
    }
    
    void getSound() {
        this.hitBlock1.play();
        this.hitBlock1.stop();
        this.hitBlock2.play();
        this.hitBlock2.stop();
        this.hitBlock3.play();
        this.hitBlock3.stop();
        this.hitBlock4.play();
        this.hitBlock4.stop();
        this.hitBlock5.play();
        this.hitBlock5.stop();
        this.hitPaddle.play();
        this.hitPaddle.stop();
        this.hitEdge.play();
        this.hitEdge.stop();
        this.missBall.play();
        this.missBall.stop();
        this.win.play();
    }
    
    public breakout() {
        this.ballCnt = 3;
        this.pauseValue = 20;
        this.ballVelY = 6;
        this.ballVelX = this.ballVelY;
        this.blockWidth = 20;
        this.blockHeight = 20;
        this.xstart = 1;
        this.ystart = 50;
        this.xincr = 21;
        this.yincr = 21;
        this.blocksPerLine = 20;
        this.numberOfLines = 5;
        this.ballYstart = 220;
        this.ballXstart = 160;
        this.paddleWidth = 30;
        this.paddleHeight = 12;
        this.paddle1Width = 13;
        this.paddle1Height = 12;
        this.paddle2Width = 13;
        this.paddle2Height = 12;
        this.ballWidth = 10;
        this.ballHeight = 10;
        this.v = new Vector();
        this.e = this.v.elements();
        this.start = false;
    }
}
