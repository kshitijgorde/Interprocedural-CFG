import java.awt.Event;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JRBall extends Applet implements Runnable
{
    private int COURT_WIDTH;
    private int COURT_HEIGHT;
    private int COURT_DEPTH;
    int m_iBallsLeft;
    int m_iScore;
    int m_iAces;
    int m_iPasses;
    int m_iXSpeed;
    int m_iYSpeed;
    int m_iZSpeed;
    int m_iHits;
    int m_iStatus;
    public static final int COVER_PAGE = -11;
    public static final int NEW_GAME = -10;
    public static final int SERVING = -9;
    public static final int PLAYING = -8;
    public static final int GAME_OVER = -7;
    AudioClip m_acBounce;
    AudioClip m_acMiss;
    AudioClip m_acHit;
    Location m_lBall;
    Location m_lHole;
    int m_iSpeed;
    Thread m_tRunner;
    int m_iNumBalls;
    int m_iHoleSize;
    Color m_cBall1;
    Color m_cBall2;
    Color m_cBall3;
    Image m_imgHole;
    Image m_imgWall;
    Color m_cWall;
    Color m_cText;
    Image m_imgBall;
    Image offscreenImage;
    Graphics offscreen;
    private Banner ban;
    
    public void stop() {
        if (this.m_tRunner != null) {
            this.m_tRunner = null;
        }
    }
    
    public void newBall() {
        this.m_iXSpeed = 0;
        this.m_iYSpeed = 0;
        this.m_iZSpeed = 1;
        this.m_iHits = 0;
        final Random random = new Random();
        this.m_lHole.setLocation((int)(random.nextDouble() * (this.COURT_WIDTH - this.m_iHoleSize)), (int)(random.nextDouble() * (this.COURT_HEIGHT - this.m_iHoleSize)), 0);
        this.m_lBall.setLocation(this.size().width / 2, (this.size().height - 60) / 2, 1);
    }
    
    public void checkOnWalls() {
        final int radius = this.getRadius();
        if (this.m_lBall.getX() - radius <= 0 && this.m_iXSpeed < 0) {
            this.m_iXSpeed = Math.abs(this.m_iXSpeed);
            this.m_acBounce.play();
        }
        else if (this.m_lBall.getX() + radius >= this.COURT_WIDTH && this.m_iXSpeed > 0) {
            this.m_iXSpeed = -1 * Math.abs(this.m_iXSpeed);
            this.m_acBounce.play();
        }
        if (this.m_lBall.getY() - radius <= 0 && this.m_iYSpeed < 0) {
            this.m_iYSpeed = Math.abs(this.m_iYSpeed);
            this.m_acBounce.play();
        }
        else if (this.m_lBall.getY() + radius >= this.COURT_HEIGHT && this.m_iYSpeed > 0) {
            this.m_iYSpeed = -1 * Math.abs(this.m_iYSpeed);
            this.m_acBounce.play();
        }
        if (this.m_lBall.getZ() <= 0) {
            this.m_iZSpeed *= -1;
            this.m_acBounce.play();
            return;
        }
        if (this.m_lBall.getZ() >= this.COURT_DEPTH) {
            this.m_iZSpeed = 0;
            ++this.m_iPasses;
            --this.m_iBallsLeft;
            this.m_iScore += 20;
            this.m_iStatus = -9;
            if (this.m_iBallsLeft == 0) {
                this.m_iStatus = -7;
            }
            this.m_acMiss.play();
        }
    }
    
    public void paint(final Graphics graphics) {
        this.getAppletContext().showStatus("JRBall ©2000 by Branton Boehm, realapplets.com");
        if (this.m_iStatus == -11) {
            graphics.setColor(this.m_cWall);
            graphics.fillRect(0, 0, this.size().width, this.size().height - 60);
            graphics.setColor(this.m_cText);
            graphics.drawRoundRect(3, 3, this.size().width - 6, this.size().height - 6 - 60, 50, 50);
            final Image image = this.getImage(this.getCodeBase(), this.getParameter("titleimage"));
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForAll();
                graphics.drawImage(image, (this.size().width - image.getWidth(this)) / 2, 10, this);
            }
            catch (InterruptedException ex) {}
            final Font font = new Font("Helvetica", 1, 24);
            graphics.setFont(font);
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            graphics.drawString(this.getParameter("titlecaption"), (this.size().width - fontMetrics.stringWidth(this.getParameter("titlecaption"))) / 2, 10 + fontMetrics.getHeight() + image.getHeight(this));
            final Font font2 = new Font("Verdana", 0, 12);
            graphics.setFont(font2);
            final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
            graphics.drawString("JRBall created by Branton Boehm", (this.size().width - fontMetrics2.stringWidth("JRBall created by Branton Boehm")) / 2, this.size().height - 2 * fontMetrics2.getHeight() - 5 - 60);
            graphics.drawString("This is an unregistered copy", (this.size().width - fontMetrics2.stringWidth("This is an unregistered copy")) / 2, this.size().height - fontMetrics2.getHeight() - 5 - 60);
            final Font font3 = new Font("Verdana", 0, 16);
            graphics.setFont(font3);
            final FontMetrics fontMetrics3 = this.getFontMetrics(font3);
            try {
                graphics.drawString("Loading Images...", (this.size().width - fontMetrics3.stringWidth("Loading Images...")) / 2, this.size().height - 65 - 60);
                final MediaTracker mediaTracker2 = new MediaTracker(this);
                if (this.m_imgWall != null) {
                    mediaTracker2.addImage(this.m_imgWall, 0);
                }
                mediaTracker2.waitForAll();
                mediaTracker2.removeImage(this.m_imgWall);
                if (this.m_imgHole != null) {
                    mediaTracker2.addImage(this.m_imgHole, 0);
                }
                mediaTracker2.waitForAll();
                mediaTracker2.removeImage(this.m_imgHole);
            }
            catch (InterruptedException ex2) {}
            graphics.setColor(this.m_cWall);
            graphics.fillRect((this.size().width - fontMetrics3.stringWidth("Loading Images...")) / 2, this.size().height - 65 - fontMetrics3.getHeight() - 60, fontMetrics3.stringWidth("Loading Images..."), fontMetrics3.getHeight() + 5);
            graphics.setColor(this.m_cText);
            graphics.drawString("Loading Sounds...", (this.size().width - fontMetrics3.stringWidth("Loading Sounds...")) / 2, this.size().height - 65 - 60);
            this.m_acBounce.play();
            this.m_acHit.play();
            this.m_acMiss.play();
            graphics.setColor(this.m_cWall);
            graphics.fillRect((this.size().width - fontMetrics3.stringWidth("Loading Sounds...")) / 2, this.size().height - 65 - fontMetrics3.getHeight() - 60, fontMetrics3.stringWidth("Loading Sounds..."), fontMetrics3.getHeight() + 5);
            graphics.setColor(this.m_cText);
            graphics.drawString("Click to Play", (this.size().width - fontMetrics3.stringWidth("Click to Play")) / 2, this.size().height - 65 - 60);
            return;
        }
        if (this.m_iStatus == -10) {
            graphics.setColor(this.m_cWall);
            graphics.fillRect(0, 0, this.size().width, this.size().height - 60);
            graphics.setColor(this.m_cText);
            graphics.drawRoundRect(3, 3, this.size().width - 6, this.size().height - 6 - 60, 50, 50);
            final Font font4 = new Font("Helvetica", 1, 20);
            final FontMetrics fontMetrics4 = this.getFontMetrics(font4);
            graphics.setFont(font4);
            graphics.drawString("Select Speed", (this.size().width - fontMetrics4.stringWidth("Select Speed")) / 2, 10 + fontMetrics4.getHeight());
            final int n = this.size().width / 2;
            final int n2 = 50;
            final Font font5 = new Font("Helvetica", 0, 12);
            graphics.setFont(font5);
            final FontMetrics fontMetrics5 = this.getFontMetrics(font5);
            int n3 = 0;
            do {
                graphics.setColor(this.m_cText);
                graphics.fillRect(n - 60 + 25 * n3, n2, 20, 20);
                graphics.setColor(this.m_cWall);
                graphics.drawString(Integer.toString(n3 + 1), n - 50 + 25 * n3 - fontMetrics5.stringWidth(Integer.toString(n3 + 1)) / 2, n2 + fontMetrics5.getHeight() + (20 - fontMetrics5.getHeight()) / 2);
            } while (++n3 < 5);
            graphics.setColor(this.m_cText);
            graphics.drawString("(slowest)", n - 60, n2 + 20 + 5 + fontMetrics5.getHeight());
            graphics.drawString("(fastest)", n + 60 - fontMetrics5.stringWidth("(fastest)"), n2 + 20 + 5 + fontMetrics5.getHeight());
            return;
        }
        if (this.m_imgWall != null) {
            int n4 = 0;
            while (true) {
                int n5 = 0;
                while (true) {
                    this.offscreen.drawImage(this.m_imgWall, this.COURT_DEPTH + this.m_imgWall.getWidth(this) * n5, this.COURT_DEPTH + this.m_imgWall.getHeight(this) * n4, this);
                    if (this.m_imgWall.getWidth(this) * (n5 + 1) > this.COURT_WIDTH) {
                        break;
                    }
                    ++n5;
                }
                if (this.m_imgWall.getHeight(this) * (n4 + 1) > this.COURT_HEIGHT) {
                    break;
                }
                ++n4;
            }
        }
        else {
            this.offscreen.setColor(this.m_cWall);
            this.offscreen.fillRect(this.COURT_DEPTH, this.COURT_DEPTH, this.COURT_WIDTH, this.COURT_HEIGHT);
        }
        this.offscreen.setColor(this.m_cWall);
        this.offscreen.fillPolygon(new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH, 0 }, new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH + this.COURT_HEIGHT, 2 * this.COURT_DEPTH + this.COURT_HEIGHT - 1 }, 4);
        this.offscreen.fillPolygon(new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH + this.COURT_WIDTH, 2 * this.COURT_DEPTH + this.COURT_WIDTH - 1 }, new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH, 0 }, 4);
        this.offscreen.fillPolygon(new int[] { 2 * this.COURT_DEPTH + this.COURT_WIDTH - 1, this.COURT_DEPTH + this.COURT_WIDTH, this.COURT_DEPTH + this.COURT_WIDTH, 2 * this.COURT_DEPTH + this.COURT_WIDTH - 1 }, new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH + this.COURT_HEIGHT, 2 * this.COURT_DEPTH + this.COURT_HEIGHT - 1 }, 4);
        this.offscreen.fillPolygon(new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH + this.COURT_WIDTH, 2 * this.COURT_DEPTH + this.COURT_WIDTH - 1 }, new int[] { 2 * this.COURT_DEPTH + this.COURT_HEIGHT - 1, this.COURT_DEPTH + this.COURT_HEIGHT, this.COURT_DEPTH + this.COURT_HEIGHT, 2 * this.COURT_DEPTH + this.COURT_HEIGHT - 1 }, 4);
        this.offscreen.setColor(this.m_cText);
        this.offscreen.drawPolygon(new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH + this.COURT_WIDTH, 2 * this.COURT_DEPTH + this.COURT_WIDTH - 1 }, new int[] { 2 * this.COURT_DEPTH + this.COURT_HEIGHT - 1, this.COURT_DEPTH + this.COURT_HEIGHT, this.COURT_DEPTH + this.COURT_HEIGHT, 2 * this.COURT_DEPTH + this.COURT_HEIGHT - 1 }, 4);
        this.offscreen.drawPolygon(new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH, 0 }, new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH + this.COURT_HEIGHT, 2 * this.COURT_DEPTH + this.COURT_HEIGHT - 1 }, 4);
        this.offscreen.drawPolygon(new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH + this.COURT_WIDTH, 2 * this.COURT_DEPTH + this.COURT_WIDTH - 1 }, new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH, 0 }, 4);
        this.offscreen.drawPolygon(new int[] { 2 * this.COURT_DEPTH + this.COURT_WIDTH - 1, this.COURT_DEPTH + this.COURT_WIDTH, this.COURT_DEPTH + this.COURT_WIDTH, 2 * this.COURT_DEPTH + this.COURT_WIDTH - 1 }, new int[] { 0, this.COURT_DEPTH, this.COURT_DEPTH + this.COURT_HEIGHT, 2 * this.COURT_DEPTH + this.COURT_HEIGHT - 1 }, 4);
        if (this.m_imgHole != null) {
            this.offscreen.drawImage(this.m_imgHole, this.COURT_DEPTH + this.m_lHole.getX(), this.COURT_DEPTH + this.m_lHole.getY(), this.m_iHoleSize, this.m_iHoleSize, this);
            this.offscreen.setColor(Color.black);
            this.offscreen.drawRect(this.COURT_DEPTH + this.m_lHole.getX(), this.COURT_DEPTH + this.m_lHole.getY(), this.m_iHoleSize, this.m_iHoleSize);
        }
        else {
            this.offscreen.setColor(this.m_cText);
            this.offscreen.fillRect(this.COURT_DEPTH + this.m_lHole.getX(), this.COURT_DEPTH + this.m_lHole.getY(), this.m_iHoleSize, this.m_iHoleSize);
        }
        if (this.m_imgBall != null) {
            this.offscreen.drawImage(this.m_imgBall, this.COURT_DEPTH + this.m_lBall.getX() - this.getRadius(), this.COURT_DEPTH + this.m_lBall.getY() - this.getRadius(), 2 * this.getRadius(), 2 * this.getRadius(), this);
        }
        else {
            for (int i = 0; i < this.getRadius(); i += 3) {
                if (i % 2 == 0) {
                    this.offscreen.setColor(this.m_cBall2);
                }
                else if (this.getRadius() > 2 * this.COURT_DEPTH - 10 && i < 12) {
                    this.offscreen.setColor(this.m_cBall3);
                }
                else {
                    this.offscreen.setColor(this.m_cBall1);
                }
                this.offscreen.fillOval(this.COURT_DEPTH + this.m_lBall.getX() - this.getRadius() + i, this.COURT_DEPTH + this.m_lBall.getY() - this.getRadius() + i, 2 * this.getRadius() - 2 * i, 2 * this.getRadius() - 2 * i);
            }
        }
        final String string = "Balls: " + this.m_iBallsLeft + " Score: " + this.m_iScore + " Aces: " + this.m_iAces + " Passes: " + this.m_iPasses;
        final FontMetrics fontMetrics6 = this.getFontMetrics(graphics.getFont());
        this.offscreen.setColor(this.m_cText);
        this.offscreen.drawString(string, this.COURT_DEPTH, (this.COURT_DEPTH - fontMetrics6.getHeight()) / 2 + fontMetrics6.getHeight());
        String s = "";
        switch (this.m_iStatus) {
            case -9: {
                s = "Click to serve next ball";
                break;
            }
            case -7: {
                s = "GAME OVER -- Click to start new game";
                break;
            }
        }
        this.offscreen.drawString(s, (this.size().width - fontMetrics6.stringWidth(s)) / 2, this.COURT_DEPTH + this.COURT_HEIGHT + 3 + fontMetrics6.getHeight());
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public JRBall() {
        this.COURT_DEPTH = 25;
    }
    
    public void checkOnHole() {
        final int radius = this.getRadius();
        if (this.m_lBall.getX() - radius >= this.m_lHole.getX() && this.m_lBall.getX() + radius <= this.m_lHole.getX() + this.m_iHoleSize && this.m_lBall.getY() - radius >= this.m_lHole.getY() && this.m_lBall.getY() + radius <= this.m_lHole.getY() + this.m_iHoleSize && this.m_lBall.getZ() == this.m_lHole.getZ()) {
            final boolean ixSpeed = false;
            this.m_iZSpeed = (ixSpeed ? 1 : 0);
            this.m_iYSpeed = (ixSpeed ? 1 : 0);
            this.m_iXSpeed = (ixSpeed ? 1 : 0);
            --this.m_iBallsLeft;
            this.m_iStatus = -9;
            if (this.m_iBallsLeft == 0) {
                this.m_iStatus = -7;
            }
            if (this.m_iHits == 1) {
                ++this.m_iAces;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void readParams() {
        final String parameter = this.getParameter("numballs");
        if (parameter == null) {
            this.m_iNumBalls = 5;
        }
        else {
            this.m_iNumBalls = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("holesize");
        if (parameter2 == null) {
            this.m_iHoleSize = 50;
        }
        else {
            this.m_iHoleSize = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("ballcolor1");
        if (parameter3 == null) {
            this.m_cBall1 = Color.red;
        }
        else {
            this.m_cBall1 = this.stringToColor(parameter3);
        }
        final String parameter4 = this.getParameter("ballcolor2");
        if (parameter4 == null) {
            this.m_cBall2 = Color.blue;
        }
        else {
            this.m_cBall2 = this.stringToColor(parameter4);
        }
        final String parameter5 = this.getParameter("ballcolor3");
        if (parameter5 == null) {
            this.m_cBall3 = Color.yellow;
        }
        else {
            this.m_cBall3 = this.stringToColor(parameter5);
        }
        final String parameter6 = this.getParameter("bgcolor");
        if (parameter6 == null) {
            this.m_cWall = Color.gray;
        }
        else {
            this.m_cWall = this.stringToColor(parameter6);
        }
        final String parameter7 = this.getParameter("fontcolor");
        if (parameter7 == null) {
            this.m_cText = Color.white;
        }
        else {
            this.m_cText = this.stringToColor(parameter7);
        }
        final String parameter8 = this.getParameter("holeimage");
        if (parameter8 == null) {
            this.m_imgHole = null;
        }
        else {
            this.m_imgHole = this.getImage(this.getCodeBase(), parameter8);
        }
        final String parameter9 = this.getParameter("wallimage");
        if (parameter9 == null) {
            this.m_imgWall = null;
        }
        else {
            this.m_imgWall = this.getImage(this.getCodeBase(), parameter9);
        }
        final String parameter10 = this.getParameter("ballimage");
        if (parameter10 == null) {
            this.m_imgBall = null;
            return;
        }
        this.m_imgBall = this.getImage(this.getCodeBase(), parameter10);
    }
    
    public void start() {
        if (this.m_tRunner == null) {
            (this.m_tRunner = new Thread(this)).setDaemon(true);
            this.m_tRunner.start();
        }
    }
    
    public void addBanner() {
        System.out.println("\n**********************************************************************************************");
        System.out.println("* Unregistered applet, written by Branton Boehm, contact at branton@realapplets.com          *");
        System.out.println("* Get the registerd version without banner from http://www.realapplets.com                   *");
        System.out.println("* Removal of banner without registration is punishable in a court of law.                    *");
        System.out.println("**********************************************************************************************\n");
        this.setLayout(null);
        final Dimension size = this.getSize();
        (this.ban = new Banner(size.width, this)).setBounds(0, size.height - 60, size.width, 60);
        this.add(this.ban);
    }
    
    public int getRadius() {
        return 2 * (this.m_lBall.getZ() + 1);
    }
    
    public boolean mouseDown(final Event event, int n, int n2) {
        n -= this.COURT_DEPTH;
        n2 -= this.COURT_DEPTH;
        if (this.m_iStatus == -11) {
            this.m_iStatus = -10;
            this.repaint();
            return true;
        }
        if (this.m_iStatus == -10) {
            final int n3 = this.COURT_WIDTH / 2;
            boolean b = false;
            int n4 = 0;
            do {
                if (n > n3 - 60 + 25 * n4 && n < n3 - 60 + 25 * n4 + 20 && n2 > 50 - this.COURT_DEPTH && n2 < 70 - this.COURT_DEPTH) {
                    this.m_iSpeed = 100 - n4 * 15;
                    b = true;
                }
            } while (++n4 < 5);
            if (b) {
                this.m_iStatus = -9;
                this.beginNewGame();
                this.newBall();
                this.repaint();
            }
            return true;
        }
        if (this.m_iStatus == -9) {
            this.newBall();
            this.m_iStatus = -8;
            return true;
        }
        if (this.m_iStatus == -8) {
            if ((int)Math.sqrt(Math.pow(n - this.m_lBall.getX(), 2.0) + Math.pow(n2 - this.m_lBall.getY(), 2.0)) <= this.getRadius()) {
                this.m_iXSpeed = (this.m_lBall.getX() - n) / 2;
                this.m_iYSpeed = (this.m_lBall.getY() - n2) / 2;
                this.m_iZSpeed = -1;
                ++this.m_iHits;
                if (this.m_lBall.getX() + this.m_iXSpeed < 0) {
                    this.m_lBall.setX(0);
                }
                if (this.m_lBall.getX() + this.m_iXSpeed > this.COURT_WIDTH) {
                    this.m_lBall.setX(this.COURT_WIDTH);
                }
                if (this.m_lBall.getY() + this.m_iYSpeed < 0) {
                    this.m_lBall.setY(0);
                }
                if (this.m_lBall.getY() + this.m_iYSpeed > this.COURT_HEIGHT) {
                    this.m_lBall.setX(this.COURT_HEIGHT);
                }
                ++this.m_iScore;
                this.m_acHit.play();
            }
            else {
                this.m_iScore += 2;
            }
            return true;
        }
        if (this.m_iStatus == -7) {
            this.m_iStatus = -10;
            this.repaint();
            return true;
        }
        return false;
    }
    
    public void run() {
        while (this.m_tRunner == Thread.currentThread()) {
            if (this.m_iStatus == -8) {
                this.checkOnWalls();
                this.checkOnHole();
                this.m_lBall.setLocation(this.m_lBall.getX() + this.m_iXSpeed, this.m_lBall.getY() + this.m_iYSpeed, this.m_lBall.getZ() + this.m_iZSpeed);
                this.repaint();
            }
            try {
                Thread.sleep(this.m_iSpeed);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        this.m_acBounce = this.getAudioClip(this.getCodeBase(), "wall.au");
        this.m_acMiss = this.getAudioClip(this.getCodeBase(), "miss.au");
        this.m_acHit = this.getAudioClip(this.getCodeBase(), "swing.au");
        this.offscreenImage = this.createImage(this.size().width, this.size().height - 60);
        this.offscreen = this.offscreenImage.getGraphics();
        this.COURT_WIDTH = this.size().width - 2 * this.COURT_DEPTH;
        this.COURT_HEIGHT = this.size().height - 2 * this.COURT_DEPTH - 60;
        this.readParams();
        this.m_iStatus = -11;
        this.addBanner();
    }
    
    private Color stringToColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    public void beginNewGame() {
        this.m_lBall = new Location();
        this.m_lHole = new Location();
        this.m_iBallsLeft = this.m_iNumBalls;
        this.m_iScore = 0;
        this.m_iAces = 0;
        this.m_iPasses = 0;
        this.m_iStatus = -9;
        this.m_iBallsLeft = this.m_iNumBalls;
    }
}
