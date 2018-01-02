import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.util.Date;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Event;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bustout extends Applet implements Runnable
{
    int[] m_nKeyBuffer;
    Thread m_thread;
    boolean m_bInitialized;
    final int m_nPlayfieldX = 10;
    final int m_nPlayfieldY = 10;
    final int m_nPlayfieldWidth = 180;
    final int m_nPlayfieldHeight = 300;
    Image m_imagePlayfield;
    final int m_nBrickColumns = 6;
    final int m_nBrickRows = 6;
    final int m_nTotalBricks = 36;
    int m_nBricksLeft;
    boolean[] m_bBrickPresent;
    int m_nBrickX0;
    final int m_nBrickY0 = 40;
    int m_nBrickWidth;
    int m_nBrickHeight;
    Image m_imageBrick;
    int m_nPaddleX;
    final int m_nPaddleY = 270;
    int m_nPaddleWidth;
    int m_nPaddleHeight;
    Color m_colorPaddle;
    int m_nFramesSinceLaunch;
    long m_lLaunchTime;
    boolean m_bDrawBall;
    final int m_nLaunchLatency = 2000;
    final int m_nHistoryFrames = 10;
    long[] m_lTime;
    double m_dBallX;
    double m_dBallY;
    double m_dTargetVelocity;
    int m_nBallX;
    int m_nBallY;
    int m_nBallSide;
    double m_dBallXvelocity;
    double m_dBallYvelocity;
    Image m_imageBall;
    int m_nBallsLeft;
    Image m_imageThreeBallsLeft;
    Image m_imageTwoBallsLeft;
    Image m_imageLastBall;
    boolean m_bLaunchingBall;
    final int m_nLevels = 3;
    int m_ndxLevel;
    boolean m_bNewLevel;
    Image[] m_imageLevelNumber;
    Image[] m_imageBackgrnd;
    boolean m_bGameOver;
    long m_lTimeGameOver;
    Image m_imageGameOver;
    Image m_imageStart;
    Image m_imageLoading;
    MediaTracker m_mediaTracker;
    AudioClip m_acRebound;
    AudioClip m_acPaddle;
    AudioClip m_acExplode;
    AudioClip m_acLostBall;
    
    public void start() {
        if (this.m_thread == null) {
            (this.m_thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_thread != null) {
            this.m_thread.stop();
            this.m_thread = null;
        }
    }
    
    private synchronized void NewGame(final boolean b) {
        this.m_nBallsLeft = 3;
        this.SetLevel(0);
        if (b) {
            this.m_bGameOver = false;
            this.LaunchBall();
            return;
        }
        this.m_bGameOver = true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        int nPaddleX = n - 10 - this.m_nPaddleWidth / 2;
        if (nPaddleX < 0) {
            nPaddleX = 0;
        }
        if (nPaddleX + this.m_nPaddleWidth > 180) {
            nPaddleX = 180 - this.m_nPaddleWidth;
        }
        this.m_nPaddleX = nPaddleX;
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        this.m_nKeyBuffer[3] = this.m_nKeyBuffer[2];
        this.m_nKeyBuffer[2] = this.m_nKeyBuffer[1];
        this.m_nKeyBuffer[1] = this.m_nKeyBuffer[0];
        this.m_nKeyBuffer[0] = n;
        if (this.m_nKeyBuffer[3] == 73 && this.m_nKeyBuffer[2] == 78 && this.m_nKeyBuffer[1] == 70 && this.m_nKeyBuffer[0] == 79) {
            this.stop();
            final Graphics graphics = this.getGraphics();
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final Dimension size = this.size();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, size.width, size.height);
            graphics.setColor(Color.black);
            graphics.drawString("Bustout", 20, fontMetrics.getHeight());
            graphics.drawString("(c) 1997 Edward R. Hobbs", 20, 2 * fontMetrics.getHeight());
            graphics.drawString("edhobbs@aol.com", 20, 3 * fontMetrics.getHeight());
            graphics.drawString("All rights reserved.", 20, 4 * fontMetrics.getHeight());
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m_bGameOver) {
            this.NewGame(true);
        }
        return true;
    }
    
    private synchronized void LaunchBall() {
        this.m_lTime[0] = new Date().getTime();
        this.m_lLaunchTime = this.m_lTime[0];
        this.m_nFramesSinceLaunch = 0;
        --this.m_nBallsLeft;
        this.m_dBallX = (180 - this.m_nBallSide) / 2;
        this.m_dBallY = 40 + 6 * this.m_nBrickHeight + this.m_nBallSide;
        this.m_nBallX = (int)this.m_dBallX;
        this.m_nBallY = (int)this.m_dBallY;
        this.m_dBallXvelocity = 0.0;
        this.m_dBallYvelocity = this.m_dTargetVelocity;
    }
    
    private void UpdateBall() {
        final long time = new Date().getTime();
        final int n = this.m_nFramesSinceLaunch % 10;
        final int n2 = (int)(time - this.m_lLaunchTime);
        if (this.m_nFramesSinceLaunch < 10 || n2 < 2000) {
            this.m_bLaunchingBall = true;
            if ((n2 / 250 & 0x1) == 0x0) {
                this.m_bDrawBall = true;
            }
            else {
                this.m_bDrawBall = false;
            }
            this.m_lTime[n] = time;
            return;
        }
        this.m_bDrawBall = true;
        this.m_bLaunchingBall = false;
        this.m_bNewLevel = false;
        final double n3 = (int)(time - this.m_lTime[n]) / 10 / 1000.0;
        final double n4 = this.m_dBallXvelocity * n3;
        double n5 = this.m_dBallYvelocity * n3;
        if (n5 > 10.0) {
            n5 = 10.0;
        }
        if (n5 < -10.0) {
            n5 = -10.0;
        }
        this.m_dBallX += n4;
        this.m_dBallY += n5;
        this.m_lTime[n] = time;
        this.m_nBallX = (int)this.m_dBallX;
        this.m_nBallY = (int)this.m_dBallY;
        if (this.m_nBallX < 0) {
            this.m_nBallX = 0;
            this.m_dBallX = this.m_nBallX;
            this.m_dBallXvelocity *= -1.0;
            this.SoundEffect(this.m_acRebound);
        }
        if (this.m_nBallX + this.m_nBallSide > 180) {
            this.m_nBallX = 180 - this.m_nBallSide;
            this.m_dBallX = this.m_nBallX;
            this.m_dBallXvelocity *= -1.0;
            this.SoundEffect(this.m_acRebound);
        }
        if (this.m_nBallY < 0) {
            this.m_nBallY = 0;
            this.m_dBallY = this.m_nBallY;
            this.m_dBallYvelocity *= -1.0;
            this.SoundEffect(this.m_acRebound);
        }
        if (this.m_nBallY > 300) {
            this.SoundEffect(this.m_acLostBall);
            if (this.m_nBallsLeft > 0) {
                this.LaunchBall();
            }
            else {
                this.m_bGameOver = true;
                this.m_lTimeGameOver = time;
            }
        }
        if (this.m_nBallY + this.m_nBallSide >= 270 && this.m_nBallY <= 270 && this.m_dBallYvelocity > 0.0 && this.m_nBallX + this.m_nBallSide / 2 >= this.m_nPaddleX && this.m_nBallX + this.m_nBallSide / 2 <= this.m_nPaddleX + this.m_nPaddleWidth) {
            this.m_nBallY = 270 - this.m_nBallSide;
            this.m_dBallY = this.m_nBallY;
            this.m_dBallXvelocity = this.m_dBallYvelocity * (this.m_nBallX + this.m_nBallSide / 2 - (this.m_nPaddleX + this.m_nPaddleWidth / 2)) / (this.m_nPaddleWidth / 2);
            this.m_dBallYvelocity *= -1.0;
            this.SoundEffect(this.m_acPaddle);
        }
        int n6 = 0;
        int n7 = 0;
        int nBallY;
        if (this.m_dBallYvelocity < 0.0) {
            nBallY = this.m_nBallY;
        }
        else {
            nBallY = this.m_nBallY + this.m_nBallSide;
        }
        final int n8 = this.m_nBallX + this.m_nBallSide / 2;
        final int n9 = (nBallY - 40) / this.m_nBrickHeight;
        final int n10 = (n8 - this.m_nBrickX0) / this.m_nBrickWidth;
        if (n9 >= 0 && n9 < 6 && n10 >= 0 && n10 < 6) {
            n6 = n9 * 6 + n10;
            if (this.m_bBrickPresent[n6]) {
                this.m_dBallYvelocity *= -1.0;
                n7 = 1;
            }
        }
        if (n7 == 0) {
            int nBallX;
            if (this.m_dBallXvelocity < 0.0) {
                nBallX = this.m_nBallX;
            }
            else {
                nBallX = this.m_nBallX + this.m_nBallSide;
            }
            final int n11 = (this.m_nBallY + this.m_nBallSide / 2 - 40) / this.m_nBrickHeight;
            final int n12 = (nBallX - this.m_nBrickX0) / this.m_nBrickWidth;
            if (n11 >= 0 && n11 < 6 && n12 >= 0 && n12 < 6) {
                n6 = n11 * 6 + n12;
                if (this.m_bBrickPresent[n6]) {
                    this.m_dBallXvelocity *= -1.0;
                    n7 = 1;
                }
            }
        }
        if (n7 != 0) {
            this.m_bBrickPresent[n6] = false;
            this.SoundEffect(this.m_acExplode);
            --this.m_nBricksLeft;
            if (this.m_nBricksLeft <= 0) {
                if (this.m_ndxLevel < 2) {
                    this.SetLevel(this.m_ndxLevel + 1);
                    ++this.m_nBallsLeft;
                    this.LaunchBall();
                    return;
                }
                this.m_bGameOver = true;
                this.m_lTimeGameOver = time;
            }
        }
    }
    
    public void run() {
        if (!this.m_bInitialized) {
            this.m_imageBrick = this.getImage(this.getDocumentBase(), "brick.gif");
            this.m_mediaTracker.addImage(this.m_imageBrick, 0);
            this.m_imageBall = this.getImage(this.getDocumentBase(), "ball.gif");
            this.m_mediaTracker.addImage(this.m_imageBall, 1);
            this.m_imageBackgrnd[0] = this.getImage(this.getDocumentBase(), "level1.jpg");
            this.m_mediaTracker.addImage(this.m_imageBackgrnd[0], 2);
            this.m_imageLevelNumber[0] = this.getImage(this.getDocumentBase(), "level1.gif");
            this.m_mediaTracker.addImage(this.m_imageLevelNumber[0], 3);
            this.m_imageBackgrnd[1] = this.getImage(this.getDocumentBase(), "level2.jpg");
            this.m_mediaTracker.addImage(this.m_imageBackgrnd[1], 4);
            this.m_imageLevelNumber[1] = this.getImage(this.getDocumentBase(), "level2.gif");
            this.m_mediaTracker.addImage(this.m_imageLevelNumber[1], 5);
            this.m_imageBackgrnd[2] = this.getImage(this.getDocumentBase(), "level3.jpg");
            this.m_mediaTracker.addImage(this.m_imageBackgrnd[2], 6);
            this.m_imageLevelNumber[2] = this.getImage(this.getDocumentBase(), "level3.gif");
            this.m_mediaTracker.addImage(this.m_imageLevelNumber[2], 7);
            this.m_imageGameOver = this.getImage(this.getDocumentBase(), "gameover.gif");
            this.m_mediaTracker.addImage(this.m_imageGameOver, 8);
            this.m_imageThreeBallsLeft = this.getImage(this.getDocumentBase(), "three.gif");
            this.m_mediaTracker.addImage(this.m_imageThreeBallsLeft, 9);
            this.m_imageTwoBallsLeft = this.getImage(this.getDocumentBase(), "two.gif");
            this.m_mediaTracker.addImage(this.m_imageTwoBallsLeft, 10);
            this.m_imageLastBall = this.getImage(this.getDocumentBase(), "one.gif");
            this.m_mediaTracker.addImage(this.m_imageLastBall, 11);
            this.m_imageStart = this.getImage(this.getDocumentBase(), "start.gif");
            this.m_mediaTracker.addImage(this.m_imageStart, 12);
            final Dimension size = this.size();
            this.m_imageLoading = this.createImage(size.width, size.height);
            final Graphics graphics = this.m_imageLoading.getGraphics();
            final int n = 160;
            final int n2 = 25;
            final int n3 = 2;
            final int n4 = (size.width - n) / 2;
            final int n5 = size.height * 2 / 3 - n2 / 2;
            final int n6 = n4 + n3;
            final int n7 = n5 + n3;
            final int n8 = n - 2 * n3;
            final int n9 = n2 - 2 * n3;
            final int n10 = 13;
            int n11 = -1;
            graphics.setColor(new Color(255, 255, 255));
            graphics.fillRect(0, 0, size.width, size.height);
            graphics.setColor(new Color(0, 0, 0));
            for (int i = 0; i < n3; ++i) {
                graphics.drawRect(n4 + i, n5 + i, n - 2 * i - 1, n2 - 2 * i - 1);
            }
            graphics.setFont(new Font("Helvetica", 0, 14));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final String s = "Loading graphics...";
            graphics.drawString(s, (size.width - fontMetrics.stringWidth(s)) / 2, n5 - fontMetrics.getDescent() - 2);
            graphics.setFont(new Font("Helvetica", 1, 24));
            final FontMetrics fontMetrics2 = graphics.getFontMetrics();
            final String s2 = "Bustout!";
            graphics.drawString(s2, (size.width - fontMetrics2.stringWidth(s2)) / 2, size.height / 3);
            graphics.dispose();
            while (true) {
                int n12 = 0;
                for (int j = 0; j < n10; ++j) {
                    if (this.m_mediaTracker.checkID(j, true)) {
                        ++n12;
                    }
                }
                if (n12 > n11) {
                    n11 = n12;
                    final Graphics graphics2 = this.m_imageLoading.getGraphics();
                    graphics2.setColor(new Color(0, 0, 255));
                    graphics2.fillRect(n6, n7, n8 * n11 / n10, n9);
                    graphics2.dispose();
                    final Graphics graphics3 = this.getGraphics();
                    graphics3.drawImage(this.m_imageLoading, 0, 0, size.width, size.height, null);
                    graphics3.dispose();
                }
                if (n11 >= n10) {
                    break;
                }
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            this.m_imageLoading.flush();
            (this.m_acRebound = this.getAudioClip(this.getDocumentBase(), "rebound.au")).play();
            (this.m_acPaddle = this.getAudioClip(this.getDocumentBase(), "paddle.au")).play();
            (this.m_acExplode = this.getAudioClip(this.getDocumentBase(), "explode.au")).play();
            (this.m_acLostBall = this.getAudioClip(this.getDocumentBase(), "lostball.au")).play();
            this.m_nBrickWidth = this.m_imageBrick.getWidth(null);
            this.m_nBrickHeight = this.m_imageBrick.getHeight(null);
            this.m_nBrickX0 = (180 - this.m_nBrickWidth * 6) / 2;
            this.m_nBallSide = this.m_imageBall.getWidth(null);
            this.NewGame(false);
            this.m_bInitialized = true;
            this.repaint();
        }
        while (true) {
            if (!this.m_bGameOver) {
                this.UpdateBall();
            }
            this.DrawPlayfield();
            final Graphics graphics4 = this.getGraphics();
            graphics4.drawImage(this.m_imagePlayfield, 10, 10, null);
            graphics4.dispose();
        }
    }
    
    public Bustout() {
        this.m_nKeyBuffer = new int[4];
        this.m_bBrickPresent = new boolean[36];
        this.m_nPaddleWidth = 30;
        this.m_nPaddleHeight = 10;
        this.m_colorPaddle = new Color(255, 255, 132);
        this.m_lTime = new long[10];
        this.m_dTargetVelocity = 200.0;
        this.m_imageLevelNumber = new Image[3];
        this.m_imageBackgrnd = new Image[3];
        this.m_mediaTracker = new MediaTracker(this);
    }
    
    private void DrawPlayfield() {
        final Graphics graphics = this.m_imagePlayfield.getGraphics();
        graphics.drawImage(this.m_imageBackgrnd[this.m_ndxLevel], 0, 0, null);
        int n = 0;
        do {
            int n2 = 0;
            do {
                if (this.m_bBrickPresent[n * 6 + n2]) {
                    graphics.drawImage(this.m_imageBrick, this.m_nBrickX0 + n2 * this.m_nBrickWidth, 40 + n * this.m_nBrickHeight, null);
                }
            } while (++n2 < 6);
        } while (++n < 6);
        if (this.m_bGameOver) {
            Image image;
            if (new Date().getTime() - this.m_lTimeGameOver > 2000L) {
                image = this.m_imageStart;
            }
            else {
                image = this.m_imageGameOver;
            }
            graphics.drawImage(image, (180 - image.getWidth(null)) / 2, (300 - image.getHeight(null)) / 2, null);
        }
        else {
            graphics.setColor(this.m_colorPaddle);
            graphics.fillRect(this.m_nPaddleX, 270, this.m_nPaddleWidth, this.m_nPaddleHeight);
            if (this.m_bDrawBall) {
                graphics.drawImage(this.m_imageBall, this.m_nBallX, this.m_nBallY, null);
            }
            if (this.m_bLaunchingBall) {
                Image image2 = null;
                switch (this.m_nBallsLeft) {
                    case 2: {
                        image2 = this.m_imageThreeBallsLeft;
                        break;
                    }
                    case 1: {
                        image2 = this.m_imageTwoBallsLeft;
                        break;
                    }
                    case 0: {
                        image2 = this.m_imageLastBall;
                        break;
                    }
                }
                graphics.drawImage(image2, (180 - image2.getWidth(null)) / 2, (300 - image2.getHeight(null)) / 2, null);
                if (this.m_bNewLevel) {
                    graphics.drawImage(this.m_imageLevelNumber[this.m_ndxLevel], (180 - this.m_imageLevelNumber[this.m_ndxLevel].getWidth(null)) / 2, 10, null);
                }
            }
        }
        ++this.m_nFramesSinceLaunch;
    }
    
    private synchronized void SetLevel(final int ndxLevel) {
        this.m_ndxLevel = ndxLevel;
        this.m_nBricksLeft = 36;
        int n = 0;
        do {
            this.m_bBrickPresent[n] = true;
        } while (++n < 36);
        switch (this.m_ndxLevel) {
            case 0: {
                this.m_dTargetVelocity = 175.0;
                this.m_nPaddleWidth = 40;
                this.m_bNewLevel = false;
            }
            case 1: {
                this.m_dTargetVelocity = 210.0;
                this.m_nPaddleWidth = 30;
                this.m_bNewLevel = true;
            }
            case 2: {
                this.m_dTargetVelocity = 250.0;
                this.m_nPaddleWidth = 25;
                this.m_bNewLevel = true;
                break;
            }
        }
    }
    
    private void SoundEffect(final AudioClip audioClip) {
        audioClip.play();
    }
    
    public void init() {
        this.m_imagePlayfield = this.createImage(180, 300);
        this.start();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (!this.m_bInitialized) {
            return;
        }
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, size.width, size.height);
    }
}
