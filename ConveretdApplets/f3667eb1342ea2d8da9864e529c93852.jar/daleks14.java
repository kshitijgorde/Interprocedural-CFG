import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class daleks14 extends Applet implements MouseListener, MouseMotionListener, KeyListener, Runnable
{
    Image aboutPic;
    boolean showAbout;
    Frame frame2;
    String str;
    String helptxt;
    boolean showGrid;
    boolean screwdriverUsed;
    boolean lastStand;
    boolean allDead;
    boolean animLevel;
    boolean animRegen;
    boolean animIntro;
    boolean animTele;
    boolean newRubble;
    boolean doneLoading;
    boolean levelComplete;
    boolean playerDead;
    Thread kicker;
    MediaTracker tracker;
    int currentPic;
    animatedIntroClass animateIntro;
    int numDaleks;
    int levelMultiple;
    int baseNumDaleks;
    int highScore;
    int level;
    int maxX;
    int maxY;
    int screwdrivers;
    int score;
    int imgH;
    int imgW;
    Image[] HeroPic;
    Image[] EnemyPic;
    Image[] rubblePic;
    Image deadPic;
    Image[] digit;
    Image telePic;
    Image nextScreen;
    Graphics offScreenGC;
    int faceLeft;
    int faceRight;
    int faceMe;
    int faceUp;
    String title;
    int numWidgets;
    int drStartX;
    int drStartY;
    int drX;
    int drY;
    int oldX;
    int oldY;
    int HeroFacing;
    boolean drA;
    int[] dalX;
    int[] dalY;
    int[] dalF;
    int maxEnemies;
    boolean[] dalR;
    boolean[] dalA;
    int nX;
    int nY;
    int newF;
    
    public daleks14() {
        this.showAbout = false;
        this.str = "";
        this.helptxt = "";
        this.showGrid = true;
        this.screwdriverUsed = false;
        this.lastStand = false;
        this.allDead = false;
        this.animLevel = false;
        this.animRegen = false;
        this.animIntro = false;
        this.animTele = false;
        this.newRubble = false;
        this.doneLoading = false;
        this.levelComplete = false;
        this.playerDead = false;
        this.kicker = null;
        this.currentPic = 0;
        this.animateIntro = new animatedIntroClass();
        this.numDaleks = 0;
        this.levelMultiple = 4;
        this.baseNumDaleks = 2;
        this.highScore = 150;
        this.level = 1;
        this.maxX = 30;
        this.maxY = 20;
        this.screwdrivers = 2;
        this.score = 0;
        this.imgH = 32;
        this.imgW = 32;
        this.HeroPic = new Image[10];
        this.EnemyPic = new Image[4];
        this.rubblePic = new Image[4];
        this.digit = new Image[10];
        this.faceLeft = 0;
        this.faceRight = 1;
        this.faceMe = 2;
        this.faceUp = 3;
        this.title = "Birdbrain 2002";
        this.numWidgets = 50;
        this.drStartX = 15;
        this.drStartY = 10;
        this.HeroFacing = this.faceMe;
        this.drA = true;
        this.maxEnemies = 100;
        this.dalR = new boolean[100];
        this.dalA = new boolean[100];
        this.nX = 0;
        this.nY = 0;
        this.newF = this.HeroFacing;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        final int x = point.x;
        final int y = point.y;
        final int n = this.drX * this.imgW;
        final int n2 = this.drY * this.imgH;
        char c = 'x';
        int n3 = 0;
        boolean b = false;
        if (this.animIntro) {
            n3 = 1;
            b = false;
            this.animIntro = false;
            this.resetGame();
            this.setupBoard();
            this.repaint();
        }
        if (this.levelComplete && n3 == 0) {
            n3 = 1;
            b = false;
            this.oldX = this.drX;
            this.oldY = this.drY;
            this.animLevel = true;
            this.repaint();
            this.setupBoard();
            this.repaint();
        }
        if (this.playerDead && n3 == 0) {
            n3 = 1;
            b = false;
            this.playerDead = false;
            this.oldX = this.drStartX;
            this.oldY = this.drStartY;
            this.prepareScreen();
            this.animateIntro.updateBar(this.nextScreen);
            this.animateIntro.startFadeIn();
            this.animateIntro.start();
            this.animIntro = true;
            this.repaint();
        }
        if (n3 == 0 && y > 320 && y < 336) {
            if (x > 0 && x < 64) {
                c = 'T';
                b = true;
                n3 = 1;
            }
            if (x > 63 && x < 128 && !this.playerDead) {
                c = 'Z';
                b = true;
                n3 = 1;
            }
            if (x > 192 && x < 255) {
                c = 'S';
                b = true;
                n3 = 1;
            }
            if (x > 320 && x < 383) {
                c = 'G';
                b = true;
                n3 = 1;
            }
            if (x > 385 && x < 445) {
                this.frame2.setVisible(true);
                b = true;
                n3 = 1;
            }
            if (x > 449) {
                this.showAbout = true;
                b = true;
                n3 = 1;
            }
        }
        if (x > n - 32 && x < n + 64 && y > n2 - 32 && y < n2 + 64 && n3 == 0) {
            this.play(this.getCodeBase(), "beep.au");
            this.nX = x / 32;
            this.nY = y / 32;
            if (this.nX < this.drX && this.nY > this.drY) {
                c = '1';
            }
            if (this.nX == this.drX && this.nY > this.drY) {
                c = '2';
            }
            if (this.nX > this.drX && this.nY > this.drY) {
                c = '3';
            }
            if (this.nX < this.drX && this.nY == this.drY) {
                c = '4';
            }
            if (this.nX == this.drX && this.nY == this.drY) {
                c = '5';
            }
            if (this.nX > this.drX && this.nY == this.drY) {
                c = '6';
            }
            if (this.nX < this.drX && this.nY < this.drY) {
                c = '7';
            }
            if (this.nX == this.drX && this.nY < this.drY) {
                c = '8';
            }
            if (this.nX > this.drX && this.nY < this.drY) {
                c = '9';
            }
            b = true;
        }
        if (b && this.movePlayer(c)) {
            if (this.screwdriverUsed) {
                this.calcSonic();
                this.play(this.getCodeBase(), "sonic.au");
            }
            this.moveDaleks();
            if (this.newRubble) {
                this.play(this.getCodeBase(), "crash.au");
            }
        }
        this.repaint();
        this.checkDaleks();
        if (this.drA && this.allDead) {
            this.levelComplete = true;
            this.prepareScreen();
            this.repaint();
        }
        else if (!this.drA) {
            this.prepareScreen();
            this.repaint();
            this.playerDead = true;
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.doneLoading) {
            this.handleKeyPress(keyEvent.getKeyCode());
        }
    }
    
    public void handleKeyPress(final int n) {
        final char c = (char)n;
        this.getAppletContext().showStatus("ASCIImove = " + c);
        if (this.movePlayer(c)) {
            if (this.lastStand) {
                this.repaint();
            }
            else {
                if (this.screwdriverUsed) {
                    this.calcSonic();
                    this.play(this.getCodeBase(), "sonic.au");
                }
                this.moveDaleks();
                if (this.newRubble) {
                    this.play(this.getCodeBase(), "crash.au");
                }
            }
        }
        this.repaint();
        this.checkDaleks();
        if (this.drA && this.allDead) {
            this.levelComplete = true;
            this.prepareScreen();
            this.repaint();
        }
        else if (!this.drA) {
            this.prepareScreen();
            this.repaint();
            this.playerDead = true;
        }
    }
    
    public void getHTMLParameters() {
        if (this.getParameter("maxX") != null) {
            this.maxX = Integer.parseInt(this.getParameter("maxX"));
        }
        if (this.getParameter("maxY") != null) {
            this.maxY = Integer.parseInt(this.getParameter("maxY"));
        }
        if (this.getParameter("imgH") != null) {
            this.imgH = Integer.parseInt(this.getParameter("imgH"));
        }
        if (this.getParameter("imgW") != null) {
            this.imgW = Integer.parseInt(this.getParameter("imgW"));
        }
        if (this.getParameter("levelMultiple") != null) {
            this.levelMultiple = Integer.parseInt(this.getParameter("levelMultiple"));
        }
        if (this.getParameter("baseNum") != null) {
            this.baseNumDaleks = Integer.parseInt(this.getParameter("baseNum"));
        }
        if (this.getParameter("maxEnemies") != null) {
            this.maxEnemies = Integer.parseInt(this.getParameter("maxEnemies"));
        }
    }
    
    public static void main() {
    }
    
    public void init() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.helptxt += "How to Play\n\n";
        this.helptxt += "You control the red bird. Use your brain & weapons to win.\n";
        this.helptxt += "The object of the game is to destroy all of the blue birds\n";
        this.helptxt += "by zapping them or causing them to crash into each other.\n\n";
        this.helptxt += "The red birds are not very bright. They have a one track\n";
        this.helptxt += "mind and will always move toward you.  If 2 of them try to\n";
        this.helptxt += "occupy the same space, they will both be destroyed. If one\n";
        this.helptxt += "of them occupy the same space you are in, you are killed.\n\n";
        this.helptxt += "The sequence of play is, you move, they move. Each turn you\n";
        this.helptxt += "have the option of moving to an adjacent space, teleporting\n";
        this.helptxt += "to an unknown space, or using your zapper.\n\n";
        this.helptxt += "The game is completely mouse driven.\n\n";
        this.helptxt += "MOVING - Click on any of the 8 adjacent spaces to the red\n";
        this.helptxt += "         bird.  Be careful though, if one the blue birds\n";
        this.helptxt += "         moves to the same space, the game is over.\n\n";
        this.helptxt += "TELEPORTING - If you can't see a safe move, you can click\n";
        this.helptxt += "         the teleport button. This will transport you to a\n";
        this.helptxt += "         random unoccupied space. The danger is that a blue\n";
        this.helptxt += "         bird might be able to get you on the next move.\n";
        this.helptxt += "         There is no limit to the number of times you can\n";
        this.helptxt += "         teleport.\n\n";
        this.helptxt += "ZAPPER - Alternately, you can click the zapper button. This\n";
        this.helptxt += "         will destroy all blue birds in the 8 adjacent spaces\n";
        this.helptxt += "         around your red bird. Your ability to use the zapper\n";
        this.helptxt += "         is limited though. Use it wisely.\n\n";
        this.helptxt += "BRAIN - Don't just move to a safe space just because you can.\n";
        this.helptxt += "        Try to make moves that will cause the blue birds to\n";
        this.helptxt += "        crash into each other.  The fewer moves you make, the\n";
        this.helptxt += "        higher your score will be.\n\n";
        this.helptxt += "SUICIDE - If you are tired of playing, just click the suicide\n";
        this.helptxt += "        button. The blue birds will execute an unstoppable\n";
        this.helptxt += "        attack and do you in. The game will be over.\n\n";
        this.helptxt += "Good luck and have fun.\n\n";
        (this.frame2 = new Frame("Birdbrain Help")).setBackground(SystemColor.control);
        final TextArea textArea = new TextArea(this.helptxt, 20, 70);
        textArea.setEditable(false);
        this.frame2.add(textArea, "Center");
        this.frame2.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                daleks14.this.frame2.setVisible(false);
            }
        });
        this.frame2.setSize(360, 200);
        this.getHTMLParameters();
        this.dalX = new int[this.maxEnemies];
        this.dalY = new int[this.maxEnemies];
        this.dalF = new int[this.maxEnemies];
        this.drStartX = this.maxX / 2;
        this.drStartY = this.maxY / 2;
        this.HeroPic[0] = this.getImage(this.getCodeBase(), "herol.gif");
        this.HeroPic[1] = this.getImage(this.getCodeBase(), "heror.gif");
        this.HeroPic[2] = this.getImage(this.getCodeBase(), "herof.gif");
        this.HeroPic[3] = this.getImage(this.getCodeBase(), "herou.gif");
        this.HeroPic[4] = this.getImage(this.getCodeBase(), "herof.gif");
        this.HeroPic[5] = this.getImage(this.getCodeBase(), "herof.gif");
        this.HeroPic[6] = this.getImage(this.getCodeBase(), "herof.gif");
        this.HeroPic[7] = this.getImage(this.getCodeBase(), "herof.gif");
        this.HeroPic[8] = this.getImage(this.getCodeBase(), "herof.gif");
        this.HeroPic[9] = this.getImage(this.getCodeBase(), "herof.gif");
        this.EnemyPic[0] = this.getImage(this.getCodeBase(), "enemyl.gif");
        this.EnemyPic[1] = this.getImage(this.getCodeBase(), "enemyr.gif");
        this.EnemyPic[2] = this.getImage(this.getCodeBase(), "enemyf.gif");
        this.EnemyPic[3] = this.getImage(this.getCodeBase(), "enemyu.gif");
        this.rubblePic[0] = this.getImage(this.getCodeBase(), "enemyd1.gif");
        this.rubblePic[1] = this.getImage(this.getCodeBase(), "enemyd2.gif");
        this.rubblePic[2] = this.getImage(this.getCodeBase(), "enemyd1.gif");
        this.rubblePic[3] = this.getImage(this.getCodeBase(), "enemyd2.gif");
        this.deadPic = this.getImage(this.getCodeBase(), "herodead.gif");
        this.telePic = this.getImage(this.getCodeBase(), "teleport.gif");
        this.aboutPic = this.getImage(this.getCodeBase(), "aboutbb.gif");
        for (int i = 0; i < 10; ++i) {
            this.digit[i] = this.getImage(this.getCodeBase(), i + ".gif");
        }
        this.tracker = new MediaTracker(this);
        for (int j = 0; j < 10; ++j) {
            this.tracker.addImage(this.HeroPic[j], j);
            this.tracker.addImage(this.digit[j], j + 10);
        }
        for (int k = 0; k < 4; ++k) {
            this.tracker.addImage(this.EnemyPic[k], 20 + k);
        }
        for (int l = 0; l < 4; ++l) {
            this.tracker.addImage(this.rubblePic[l], 24 + l);
        }
        this.tracker.addImage(this.deadPic, 28);
        this.tracker.addImage(this.aboutPic, 29);
        this.tracker.checkAll(true);
        this.nextScreen = this.createImage(this.maxX * this.imgW, this.maxY * this.imgH + 25 + 16);
        this.offScreenGC = this.nextScreen.getGraphics();
        this.prepareScreen();
        this.animateIntro.setInfo(this.nextScreen, this.maxX, this.maxY, this.imgH, this.imgW, this.title, this.numWidgets, this.telePic);
        this.animateIntro.start();
        this.animIntro = true;
        this.repaint();
    }
    
    void resetGame() {
        this.level = 0;
        this.score = 0;
        this.screwdrivers = 1;
    }
    
    public void setupBoard() {
        ++this.level;
        this.levelComplete = false;
        this.playerDead = false;
        ++this.screwdrivers;
        if (this.level > 5) {
            ++this.screwdrivers;
        }
        this.HeroFacing = this.faceMe;
        this.drX = this.drStartX;
        this.drY = this.drStartY;
        this.drA = true;
        this.allDead = false;
        this.animRegen = true;
        this.numDaleks = this.level * this.levelMultiple + this.baseNumDaleks;
        if (this.numDaleks > this.maxEnemies) {
            this.numDaleks = this.maxEnemies;
        }
        for (int i = 0; i < this.numDaleks; ++i) {
            for (boolean b = true; b; b = true) {
                b = false;
                this.dalX[i] = (int)(Math.random() * this.maxX);
                this.dalY[i] = (int)(Math.random() * this.maxY);
                for (int j = 0; j < i; ++j) {
                    if (this.dalX[j] == this.dalX[i] && this.dalY[j] == this.dalY[i]) {
                        b = true;
                    }
                }
                if (this.dalX[i] == this.drX && this.dalY[i] == this.drY) {}
            }
            this.dalA[i] = true;
            this.dalR[i] = false;
        }
    }
    
    public void checkFace(final int n) {
        int n2 = 0;
        if (this.dalX[n] < this.drX) {
            n2 = this.faceRight;
        }
        if (this.dalX[n] > this.drX) {
            n2 = this.faceLeft;
        }
        if (this.dalX[n] == this.drX && this.dalY[n] <= this.drY) {
            n2 = this.faceMe;
        }
        if (this.dalX[n] == this.drX && this.dalY[n] > this.drY) {
            n2 = this.faceUp;
        }
        this.dalF[n] = n2;
    }
    
    public void calcSonic() {
        --this.screwdrivers;
        for (int i = 0; i < this.numDaleks; ++i) {
            if (Math.abs(this.drX - this.dalX[i]) <= 1 && Math.abs(this.drY - this.dalY[i]) <= 1 && this.dalA[i]) {
                this.dalA[i] = false;
                this.dalR[i] = false;
            }
        }
    }
    
    public void pause(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void animateRegen(final Graphics graphics) {
        graphics.clipRect(0, 0, this.maxX * this.imgW, this.maxY * this.imgH + 1);
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.maxX * this.imgW, this.maxY * this.imgH + 1);
        final int n = (this.maxX > this.maxY) ? (this.maxX / 2 + 2) : (this.maxY / 2 + 2);
        for (int i = 0; i < n; ++i) {
            final int n2 = i * this.imgW;
            final int n3 = i * this.imgH * this.maxY / this.maxX;
            graphics.drawImage(this.HeroPic[this.HeroFacing], n2, n3, this);
            graphics.drawImage(this.HeroPic[this.HeroFacing], this.maxX * this.imgW - n2, n3, this);
            graphics.drawImage(this.HeroPic[this.HeroFacing], this.maxX * this.imgW - n2, this.maxY * this.imgH - n3, this);
            graphics.drawImage(this.HeroPic[this.HeroFacing], n2, this.maxY * this.imgH - n3, this);
            final int n4 = (i - 3) * this.imgW;
            final int n5 = (i - 3) * this.imgH * this.maxY / this.maxX;
            graphics.fillRect(n4, n5, this.imgW, this.imgH);
            graphics.fillRect(n4, this.maxY * this.imgH - n5, this.imgW, this.imgH);
            graphics.fillRect(this.maxX * this.imgW - n4, this.maxY * this.imgH - n5, this.imgW, this.imgH);
            graphics.fillRect(this.maxX * this.imgW - n4, n5, this.imgW, this.imgH);
            this.pause(50L);
        }
        for (int j = n - 3; j < n; ++j) {
            final int n6 = j * this.imgW;
            final int n7 = j * this.imgH * this.maxY / this.maxX;
            graphics.fillRect(n6, n7, this.imgW, this.imgH);
            graphics.fillRect(n6, this.maxY * this.imgH - n7, this.imgW, this.imgH);
            graphics.fillRect(this.maxX * this.imgW - n6, this.maxY * this.imgH - n7, this.imgW, this.imgH);
            graphics.fillRect(this.maxX * this.imgW - n6, n7, this.imgW, this.imgH);
            this.pause(30L);
        }
        this.animRegen = false;
    }
    
    public void animateTele(final Graphics graphics) {
        this.play(this.getCodeBase(), "tele.au");
        graphics.clipRect(0, 0, this.maxX * this.imgW, this.maxY * this.imgH);
        graphics.setColor(Color.red);
        for (int i = this.maxX * this.imgW + 8; i > 8; i -= 8) {
            graphics.drawLine(this.drX * this.imgW - i, this.drY * this.imgH - i, this.drX * this.imgW + i, this.drY * this.imgH - i);
            graphics.drawLine(this.drX * this.imgW - i, this.drY * this.imgH + i, this.drX * this.imgW + i, this.drY * this.imgH + i);
            this.pause(20L);
        }
        this.animTele = false;
    }
    
    public void animateSonic(final Graphics graphics) {
        graphics.clipRect(0, 0, this.maxX * this.imgW, this.maxY * this.imgH);
        graphics.setColor(Color.blue);
        for (int i = 4; i < 24; i += 2) {
            graphics.drawOval(this.drX * this.imgW - i + this.imgW / 2, this.drY * this.imgH - i + this.imgH / 2, i * 2, i * 2);
            this.pause(40L);
        }
        graphics.setColor(Color.white);
        this.screwdriverUsed = false;
    }
    
    public void animateLevel(final Graphics graphics) {
        graphics.clipRect(0, 0, this.maxX * this.imgW, this.maxY * this.imgH);
        graphics.setColor(Color.blue);
        for (int i = 0; i < this.maxX * this.imgW; i += 3) {
            graphics.drawRect(this.oldX * this.imgW + this.imgW / 2 - i, this.oldY * this.imgH + this.imgH / 2 - i, i * 2, i * 2);
            this.pause(10L);
        }
        this.animLevel = false;
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.animIntro) {
            this.animateIntro.drawIntroScreen(graphics, this.doneLoading, this.currentPic);
            if (!this.doneLoading) {
                if (this.tracker.checkID(this.currentPic, true)) {
                    ++this.currentPic;
                }
                if (this.currentPic > 26) {
                    this.doneLoading = true;
                    this.prepareScreen();
                }
            }
            this.repaint();
        }
        else {
            if (this.animLevel) {
                graphics.drawImage(this.nextScreen, 0, 0, this);
                this.animateLevel(graphics);
            }
            if (this.animRegen) {
                this.animateRegen(graphics);
            }
            if (this.screwdriverUsed) {
                this.animateSonic(graphics);
            }
            if (this.animTele) {
                this.animateTele(graphics);
            }
            if (this.lastStand) {
                while (!this.allDead && this.drA) {
                    this.moveDaleks();
                    this.checkDaleks();
                    if (this.newRubble) {
                        this.play(this.getCodeBase(), "crash.au");
                    }
                    this.prepareScreen();
                    graphics.drawImage(this.nextScreen, 0, 0, this);
                }
                if (this.allDead && this.drA) {
                    this.levelComplete = true;
                }
                else {
                    this.playerDead = true;
                }
                this.lastStand = false;
            }
            this.prepareScreen();
            graphics.drawImage(this.nextScreen, 0, 0, this);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.animIntro) {
            this.prepareScreen();
            graphics.drawImage(this.nextScreen, 0, 0, this);
        }
        else {
            this.animateIntro.drawIntroScreen(graphics, this.doneLoading, this.currentPic);
        }
    }
    
    public void paintNumber(final int n, final int n2, final int n3) {
        final String string = new Integer(n).toString();
        for (int i = 0; i < string.length(); ++i) {
            this.offScreenGC.drawImage(this.digit[Character.digit(string.charAt(i), 10)], n2 + i * 12, n3 - 13, this);
        }
    }
    
    public synchronized void prepareScreen() {
        this.offScreenGC.setColor(Color.green);
        this.offScreenGC.clipRect(0, 0, this.maxX * this.imgW, this.maxY * this.imgH + 25 + 16);
        this.offScreenGC.fillRect(0, 0, this.maxX * this.imgW, this.maxY * this.imgH + 4 + 16);
        if (this.showGrid) {
            final int n = 32;
            this.offScreenGC.setColor(Color.white);
            for (int i = 0; i < this.maxY; ++i) {
                this.offScreenGC.drawLine(0, i * n, this.maxX * this.imgW, i * n);
            }
            for (int j = 0; j < this.maxX; ++j) {
                this.offScreenGC.drawLine(j * n, 0, j * n, this.maxY * this.imgH);
            }
        }
        final int n2 = 16;
        this.offScreenGC.setColor(Color.darkGray);
        this.offScreenGC.drawLine(0, this.maxY * this.imgH + 1 + n2, this.maxX * this.imgW, this.maxY * this.imgH + 1 + n2);
        this.offScreenGC.drawLine(0, this.maxY * this.imgH + 3 + n2, this.maxX * this.imgW, this.maxY * this.imgH + 3 + n2);
        this.offScreenGC.setColor(Color.lightGray);
        this.offScreenGC.drawLine(0, this.maxY * this.imgH + 2 + n2, this.maxX * this.imgW, this.maxY * this.imgH + 2 + n2);
        this.offScreenGC.setColor(Color.black);
        this.offScreenGC.fillRect(0, this.maxY * this.imgH + 4 + n2, this.maxX * this.imgW, 21);
        if (this.doneLoading) {
            this.offScreenGC.setFont(new Font("TimesRoman", 0, 12));
            if (this.score > this.highScore) {
                this.highScore = this.score;
            }
            final int n3 = this.maxY * this.imgH + 19 + n2;
            this.offScreenGC.setColor(Color.white);
            this.offScreenGC.drawString("SCORE: ", 5, n3);
            this.paintNumber(this.score, 51, n3);
            this.offScreenGC.drawString("ZAPPER AMMO: ", 124, n3);
            this.paintNumber(this.screwdrivers, 221, n3);
            this.offScreenGC.drawString("LEVEL: ", 260, n3);
            this.paintNumber(this.level, 303, n3);
            this.offScreenGC.drawString("HIGHSCORE: ", 338, n3);
            this.paintNumber(this.highScore, 415, n3);
            this.offScreenGC.drawImage(this.telePic, 0, 322, this);
            this.offScreenGC.setColor(Color.red);
            this.offScreenGC.drawRect(0, 0, this.maxX * this.imgW - 1, this.maxY * this.imgH + 1);
            for (int k = 0; k < this.numDaleks; ++k) {
                if (this.dalA[k]) {
                    this.checkFace(k);
                    this.offScreenGC.drawImage(this.EnemyPic[this.dalF[k]], this.dalX[k] * this.imgW, this.dalY[k] * this.imgH, this);
                }
                else if (this.dalR[k]) {
                    this.offScreenGC.drawImage(this.rubblePic[this.dalF[k]], this.dalX[k] * this.imgW, this.dalY[k] * this.imgH, this);
                }
            }
            if (!this.playerDead) {
                this.offScreenGC.drawImage(this.HeroPic[this.HeroFacing], this.drX * this.imgW, this.drY * this.imgH, this);
            }
            else {
                final String s = "Game Over";
                this.offScreenGC.drawImage(this.deadPic, this.drX * this.imgW, this.drY * this.imgH, this);
                this.offScreenGC.setColor(Color.black);
                this.offScreenGC.setFont(new Font("Helvetica", 0, 36));
                this.offScreenGC.drawString(s, this.maxX * this.imgW / 2 - 104, 79);
                this.offScreenGC.setColor(Color.white);
                this.offScreenGC.drawString(s, this.maxX * this.imgW / 2 - 100, 75);
            }
            if (this.showAbout) {
                this.offScreenGC.drawImage(this.aboutPic, 10, 10, this);
                this.showAbout = false;
            }
            if (this.levelComplete) {
                final String s2 = "Level Completed!";
                this.offScreenGC.setColor(Color.black);
                this.offScreenGC.setFont(new Font("Helvetica", 0, 36));
                this.offScreenGC.drawString(s2, this.maxX * this.imgW / 2 - 124, 79);
                this.offScreenGC.setColor(Color.white);
                this.offScreenGC.drawString(s2, this.maxX * this.imgW / 2 - 120, 75);
            }
        }
    }
    
    public void start() {
        if (this.kicker == null) {
            this.kicker = new Thread(this);
        }
        this.repaint();
    }
    
    public void stop() {
        this.kicker = null;
    }
    
    public void run() {
        this.repaint();
    }
    
    public boolean movePlayer(final char c) {
        int drX = this.drX;
        int drY = this.drY;
        boolean b = false;
        boolean b2 = true;
        switch (c) {
            case '1': {
                --drX;
                ++drY;
                this.newF = this.faceLeft;
                break;
            }
            case '2': {
                ++drY;
                this.newF = this.faceMe;
                break;
            }
            case '3': {
                ++drX;
                ++drY;
                this.newF = this.faceRight;
                break;
            }
            case '4': {
                --drX;
                this.newF = this.faceLeft;
                break;
            }
            case '5': {
                this.newF = this.faceMe;
                break;
            }
            case '6': {
                ++drX;
                this.newF = this.faceRight;
                break;
            }
            case '7': {
                --drX;
                --drY;
                this.newF = this.faceLeft;
                break;
            }
            case '8': {
                --drY;
                this.newF = this.faceUp;
                break;
            }
            case '9': {
                ++drX;
                --drY;
                this.newF = this.faceRight;
                break;
            }
            case 'T': {
                if (this.playerDead) {
                    break;
                }
                b = true;
                this.newF = this.faceMe;
                this.score -= 5;
                if (this.score < 0) {
                    this.score = 0;
                    break;
                }
                break;
            }
            case 'Z': {
                if (!this.playerDead) {
                    this.screwdriverUsed = true;
                    this.prepareScreen();
                    this.repaint();
                    break;
                }
            }
            case 'S': {
                this.lastStand = true;
                break;
            }
            case 'A': {
                ++this.screwdrivers;
                this.play(this.getCodeBase(), "beep.au");
                this.prepareScreen();
                this.repaint();
                b2 = false;
                break;
            }
            case 'G': {
                if (this.showGrid) {
                    this.showGrid = false;
                }
                else {
                    this.showGrid = true;
                }
                this.prepareScreen();
                this.repaint();
                b2 = false;
                break;
            }
            default: {
                b2 = false;
                break;
            }
        }
        if (drX < 0 || drX > this.maxX - 1 || drY < 0 || drY > this.maxY - 1) {
            this.play(this.getCodeBase(), "nono.au");
            b2 = false;
        }
        if (b) {
            boolean b3 = false;
            int n = 0;
            while (!b3) {
                b3 = true;
                ++n;
                drX = (int)(Math.random() * this.maxX);
                drY = (int)(Math.random() * this.maxY);
                for (int i = 0; i < this.numDaleks; ++i) {
                    if (drX == this.dalX[i] && drY == this.dalY[i] && (this.dalA[i] || this.dalR[i])) {
                        b3 = false;
                    }
                    if (drX + 1 == this.dalX[i] && drY == this.dalY[i] && (this.dalA[i] || this.dalR[i])) {
                        b3 = false;
                    }
                    if (drX - 1 == this.dalX[i] && drY == this.dalY[i] && (this.dalA[i] || this.dalR[i])) {
                        b3 = false;
                    }
                    if (drX == this.dalX[i] && drY + 1 == this.dalY[i] && (this.dalA[i] || this.dalR[i])) {
                        b3 = false;
                    }
                    if (drX == this.dalX[i] && drY - 1 == this.dalY[i] && (this.dalA[i] || this.dalR[i])) {
                        b3 = false;
                    }
                    if (drX + 1 == this.dalX[i] && drY + 1 == this.dalY[i] && (this.dalA[i] || this.dalR[i])) {
                        b3 = false;
                    }
                    if (drX - 1 == this.dalX[i] && drY - 1 == this.dalY[i] && (this.dalA[i] || this.dalR[i])) {
                        b3 = false;
                    }
                    if (drX - 1 == this.dalX[i] && drY + 1 == this.dalY[i] && (this.dalA[i] || this.dalR[i])) {
                        b3 = false;
                    }
                    if (drX + 1 == this.dalX[i] && drY - 1 == this.dalY[i] && (this.dalA[i] || this.dalR[i])) {
                        b3 = false;
                    }
                }
                if (n > 19) {
                    b3 = true;
                }
            }
            this.animTele = true;
        }
        if (this.screwdriverUsed && this.screwdrivers == 0) {
            b2 = false;
            this.screwdriverUsed = false;
        }
        for (int j = 0; j < this.numDaleks; ++j) {
            if (drX == this.dalX[j] && drY == this.dalY[j] && this.dalR[j]) {
                b2 = false;
            }
        }
        if (b2) {
            this.drX = drX;
            this.drY = drY;
            this.HeroFacing = this.newF;
        }
        return b2;
    }
    
    public void checkDaleks() {
        this.allDead = true;
        for (int i = 0; i < this.numDaleks; ++i) {
            if (this.dalA[i]) {
                this.allDead = false;
            }
        }
    }
    
    public void moveDaleks() {
        for (int i = 0; i < this.numDaleks; ++i) {
            if (this.dalA[i]) {
                if (this.dalX[i] < this.drX) {
                    final int[] dalX = this.dalX;
                    final int n = i;
                    ++dalX[n];
                }
                if (this.dalY[i] < this.drY) {
                    final int[] dalY = this.dalY;
                    final int n2 = i;
                    ++dalY[n2];
                }
                if (this.dalX[i] > this.drX) {
                    final int[] dalX2 = this.dalX;
                    final int n3 = i;
                    --dalX2[n3];
                }
                if (this.dalY[i] > this.drY) {
                    final int[] dalY2 = this.dalY;
                    final int n4 = i;
                    --dalY2[n4];
                }
                if (this.dalX[i] == this.drX && this.dalY[i] == this.drY) {
                    this.drA = false;
                }
            }
        }
        this.newRubble = false;
        for (int j = 0; j < this.numDaleks; ++j) {
            for (int k = j + 1; k < this.numDaleks; ++k) {
                if (this.dalX[j] == this.dalX[k] && this.dalY[j] == this.dalY[k] && (this.dalA[j] || this.dalR[j]) && (this.dalA[k] || this.dalR[k])) {
                    if (this.dalA[j]) {
                        this.score += this.level * 2;
                        this.newRubble = true;
                    }
                    if (this.dalA[k]) {
                        this.score += this.level * 2;
                        this.newRubble = true;
                    }
                    this.dalA[j] = (this.dalA[k] = false);
                    this.dalR[j] = (this.dalR[k] = true);
                }
            }
        }
    }
}
