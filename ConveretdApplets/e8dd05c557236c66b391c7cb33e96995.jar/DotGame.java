import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.util.Vector;
import java.applet.AudioClip;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DotGame extends Applet implements Runnable
{
    String joe;
    Thread loopThread;
    Thread loadThread;
    Image dot;
    Image redBarHorz;
    Image redBarVert;
    Image blueBarHorz;
    Image blueBarVert;
    Image BackGround;
    Image offImage;
    Graphics offScreen;
    Graphics currentScreen;
    Font font;
    FontMetrics fm;
    int fontWidth;
    int fontHeight;
    FlowLayout lm;
    Choice cLevel;
    Choice cWturn;
    boolean loaded;
    boolean easyPlay;
    AudioClip beep;
    AudioClip drip;
    AudioClip gong;
    AudioClip laugh;
    AudioClip music;
    Vector vBrowse;
    Vector vDrawBar;
    Vector vGrid;
    Vector vCBrowse;
    int i;
    int j;
    int dotXpos;
    int dotYpos;
    int browseBar;
    int browseBefore;
    int hScore;
    int cScore;
    static int MAXLINES;
    boolean cTurn;
    boolean turnAgain;
    boolean oh;
    boolean playing;
    long test;
    long test2;
    long test3;
    long test4;
    long test5;
    long test6;
    int backup;
    int leftoff;
    int CNT;
    int BOX;
    int alert;
    int loop;
    int amt;
    int totcnt;
    int[] storage;
    int storeBar;
    
    static {
        DotGame.MAXLINES = 60;
    }
    
    public DotGame() {
        this.font = new Font("Helvetica", 1, 12);
        this.lm = new FlowLayout(0);
        this.cLevel = new Choice();
        this.cWturn = new Choice();
        this.loaded = false;
        this.easyPlay = true;
        this.vBrowse = new Vector();
        this.vDrawBar = new Vector();
        this.vGrid = new Vector();
        this.vCBrowse = new Vector();
        this.cTurn = false;
        this.turnAgain = false;
        this.BOX = 0;
        this.alert = 0;
        this.loop = 0;
        this.storage = new int[100];
        this.storeBar = -1;
    }
    
    public void addBar(final int n, final int n2) {
        this.i = 0;
        while (this.i < this.vBrowse.size()) {
            final MouseBrowse mouseBrowse = this.vBrowse.elementAt(this.i);
            if (mouseBrowse.CheckBrowse(n, n2)) {
                if (mouseBrowse.lockBar == -1) {
                    this.turnAgain = false;
                    mouseBrowse.lockBar = this.i;
                    mouseBrowse.color = true;
                    this.drip.play();
                    this.vDrawBar.addElement(new Integer(this.i));
                    this.j = 0;
                    while (this.j < this.vGrid.size()) {
                        final Grid grid = this.vGrid.elementAt(this.j);
                        if (grid.element == this.i) {
                            final MouseBrowse mouseBrowse2 = this.vBrowse.elementAt(grid.a);
                            final MouseBrowse mouseBrowse3 = this.vBrowse.elementAt(grid.b);
                            final MouseBrowse mouseBrowse4 = this.vBrowse.elementAt(grid.c);
                            if (mouseBrowse2.lockBar != -1 && mouseBrowse3.lockBar != -1 && mouseBrowse4.lockBar != -1) {
                                this.turnAgain = true;
                                ++this.hScore;
                                this.gong.play();
                                if (this.checkWonGame()) {
                                    return;
                                }
                            }
                        }
                        ++this.j;
                    }
                    if (!this.turnAgain) {
                        this.cTurn = true;
                        this.computersTurn();
                        return;
                    }
                }
                else {
                    this.beep.play();
                }
            }
            ++this.i;
        }
    }
    
    public void browseBar(final int dotXpos, final int dotYpos) {
        this.i = 0;
        while (this.i < this.vBrowse.size()) {
            if (this.vBrowse.elementAt(this.i).CheckBrowse(dotXpos, dotYpos)) {
                this.browseBar = this.i;
                this.repaint();
                break;
            }
            if (this.browseBar != -1) {
                this.browseBar = -1;
                this.repaint();
            }
            ++this.i;
        }
        this.dotXpos = dotXpos;
        this.dotYpos = dotYpos;
    }
    
    public boolean checkWonGame() {
        if (this.hScore + this.cScore == 25) {
            this.playing = false;
            this.cWturn.setEnabled(true);
            this.cLevel.setEnabled(true);
            if (this.cScore > this.hScore) {
                this.laugh.play();
            }
            return true;
        }
        return false;
    }
    
    public void computersTurn() {
        int i = -1;
        int n = 0;
        this.leftoff = 0;
        if (!this.easyPlay) {
            while (true) {
                i = this.generateWinMove();
                if (i != -1) {
                    break;
                }
                while (i == -1) {
                    i = this.generateMove();
                    if (++n > 1000) {
                        n = 0;
                        this.leftoff = 0;
                        while (true) {
                            final int forceMove = this.forceMove(this.leftoff++);
                            if (this.leftoff >= 1) {
                                this.leftoff = 0;
                                i = this.findLessScore();
                                this.repaint();
                                break;
                            }
                            final MouseBrowse mouseBrowse = this.vBrowse.elementAt(forceMove);
                            this.backup = mouseBrowse.lockBar;
                            mouseBrowse.lockBar = forceMove;
                            if (this.generateWinMove() == -1) {
                                i = mouseBrowse.lockBar;
                                mouseBrowse.lockBar = this.backup;
                                break;
                            }
                            final int lockBar = mouseBrowse.lockBar;
                            mouseBrowse.lockBar = this.backup;
                        }
                    }
                }
                if (n == 0) {
                    break;
                }
                final MouseBrowse mouseBrowse2 = this.vBrowse.elementAt(i);
                this.backup = mouseBrowse2.lockBar;
                mouseBrowse2.lockBar = i;
                if (this.generateWinMove() == -1) {
                    i = mouseBrowse2.lockBar;
                    mouseBrowse2.lockBar = this.backup;
                    break;
                }
                final int lockBar2 = mouseBrowse2.lockBar;
                mouseBrowse2.lockBar = this.backup;
            }
        }
        do {
            if (i == -1) {
                i = (int)(new Random().nextDouble() * 60.0);
            }
            final MouseBrowse mouseBrowse3 = this.vBrowse.elementAt(i);
            if (mouseBrowse3.lockBar == -1) {
                this.turnAgain = false;
                mouseBrowse3.lockBar = i;
                mouseBrowse3.color = false;
                this.drip.play();
                this.vDrawBar.addElement(new Integer(i));
                this.j = 0;
                while (this.j < this.vGrid.size()) {
                    final Grid grid = this.vGrid.elementAt(this.j);
                    if (grid.element == i) {
                        final MouseBrowse mouseBrowse4 = this.vBrowse.elementAt(grid.a);
                        final MouseBrowse mouseBrowse5 = this.vBrowse.elementAt(grid.b);
                        final MouseBrowse mouseBrowse6 = this.vBrowse.elementAt(grid.c);
                        if (mouseBrowse4.lockBar != -1 && mouseBrowse5.lockBar != -1 && mouseBrowse6.lockBar != -1) {
                            this.turnAgain = true;
                            ++this.cScore;
                            this.gong.play();
                        }
                    }
                    ++this.j;
                }
                this.update(this.currentScreen);
                this.pause(300);
                if (this.checkWonGame()) {
                    return;
                }
                if (!this.turnAgain) {
                    this.cTurn = false;
                    return;
                }
            }
            i = this.generateWinMove();
        } while (i != -1);
        this.computersTurn();
    }
    
    public void createBrowseObjects(final Vector vector) {
        vector.addElement(new MouseBrowse(23, 50, 49, 61));
        vector.addElement(new MouseBrowse(61, 50, 87, 61));
        vector.addElement(new MouseBrowse(101, 50, 127, 61));
        vector.addElement(new MouseBrowse(142, 50, 169, 61));
        vector.addElement(new MouseBrowse(182, 50, 211, 61));
        vector.addElement(new MouseBrowse(13, 61, 22, 95));
        vector.addElement(new MouseBrowse(53, 61, 62, 95));
        vector.addElement(new MouseBrowse(93, 61, 103, 95));
        vector.addElement(new MouseBrowse(132, 61, 143, 95));
        vector.addElement(new MouseBrowse(171, 61, 183, 95));
        vector.addElement(new MouseBrowse(212, 61, 223, 95));
        vector.addElement(new MouseBrowse(23, 91, 49, 102));
        vector.addElement(new MouseBrowse(61, 91, 87, 102));
        vector.addElement(new MouseBrowse(101, 91, 127, 102));
        vector.addElement(new MouseBrowse(142, 91, 169, 102));
        vector.addElement(new MouseBrowse(182, 91, 211, 102));
        vector.addElement(new MouseBrowse(13, 99, 22, 134));
        vector.addElement(new MouseBrowse(53, 99, 62, 134));
        vector.addElement(new MouseBrowse(93, 99, 103, 134));
        vector.addElement(new MouseBrowse(132, 99, 143, 134));
        vector.addElement(new MouseBrowse(171, 99, 183, 134));
        vector.addElement(new MouseBrowse(212, 99, 223, 134));
        vector.addElement(new MouseBrowse(23, 132, 49, 142));
        vector.addElement(new MouseBrowse(61, 132, 87, 142));
        vector.addElement(new MouseBrowse(101, 132, 127, 142));
        vector.addElement(new MouseBrowse(142, 132, 169, 142));
        vector.addElement(new MouseBrowse(182, 132, 211, 142));
        vector.addElement(new MouseBrowse(13, 138, 22, 174));
        vector.addElement(new MouseBrowse(53, 138, 62, 174));
        vector.addElement(new MouseBrowse(93, 138, 103, 174));
        vector.addElement(new MouseBrowse(132, 138, 143, 174));
        vector.addElement(new MouseBrowse(171, 138, 183, 174));
        vector.addElement(new MouseBrowse(212, 138, 223, 174));
        vector.addElement(new MouseBrowse(23, 172, 49, 182));
        vector.addElement(new MouseBrowse(61, 172, 87, 182));
        vector.addElement(new MouseBrowse(101, 172, 127, 182));
        vector.addElement(new MouseBrowse(142, 172, 169, 182));
        vector.addElement(new MouseBrowse(182, 172, 211, 182));
        vector.addElement(new MouseBrowse(13, 178, 22, 214));
        vector.addElement(new MouseBrowse(53, 178, 62, 214));
        vector.addElement(new MouseBrowse(93, 178, 103, 214));
        vector.addElement(new MouseBrowse(132, 178, 143, 214));
        vector.addElement(new MouseBrowse(171, 178, 183, 214));
        vector.addElement(new MouseBrowse(212, 178, 223, 214));
        vector.addElement(new MouseBrowse(23, 211, 49, 221));
        vector.addElement(new MouseBrowse(61, 211, 87, 221));
        vector.addElement(new MouseBrowse(101, 211, 127, 221));
        vector.addElement(new MouseBrowse(142, 211, 169, 221));
        vector.addElement(new MouseBrowse(182, 211, 211, 221));
        vector.addElement(new MouseBrowse(13, 219, 22, 254));
        vector.addElement(new MouseBrowse(53, 219, 62, 254));
        vector.addElement(new MouseBrowse(93, 219, 103, 254));
        vector.addElement(new MouseBrowse(132, 219, 143, 254));
        vector.addElement(new MouseBrowse(171, 219, 183, 254));
        vector.addElement(new MouseBrowse(212, 219, 223, 254));
        vector.addElement(new MouseBrowse(23, 251, 49, 262));
        vector.addElement(new MouseBrowse(61, 251, 87, 262));
        vector.addElement(new MouseBrowse(101, 251, 127, 262));
        vector.addElement(new MouseBrowse(142, 251, 169, 262));
        vector.addElement(new MouseBrowse(182, 251, 211, 262));
    }
    
    public void createGrid() {
        this.vGrid.addElement(new Grid(0, 6, 11, 5));
        this.vGrid.addElement(new Grid(6, 11, 5, 0));
        this.vGrid.addElement(new Grid(11, 5, 0, 6));
        this.vGrid.addElement(new Grid(5, 0, 6, 11));
        this.vGrid.addElement(new Grid(1, 7, 12, 6));
        this.vGrid.addElement(new Grid(7, 12, 6, 1));
        this.vGrid.addElement(new Grid(12, 6, 1, 7));
        this.vGrid.addElement(new Grid(6, 1, 7, 12));
        this.vGrid.addElement(new Grid(2, 8, 13, 7));
        this.vGrid.addElement(new Grid(8, 13, 7, 2));
        this.vGrid.addElement(new Grid(13, 7, 2, 8));
        this.vGrid.addElement(new Grid(7, 2, 8, 13));
        this.vGrid.addElement(new Grid(3, 9, 14, 8));
        this.vGrid.addElement(new Grid(9, 14, 8, 3));
        this.vGrid.addElement(new Grid(14, 8, 3, 9));
        this.vGrid.addElement(new Grid(8, 3, 9, 14));
        this.vGrid.addElement(new Grid(4, 10, 15, 9));
        this.vGrid.addElement(new Grid(10, 15, 9, 4));
        this.vGrid.addElement(new Grid(15, 9, 4, 10));
        this.vGrid.addElement(new Grid(9, 4, 10, 15));
        this.vGrid.addElement(new Grid(11, 17, 22, 16));
        this.vGrid.addElement(new Grid(17, 22, 16, 11));
        this.vGrid.addElement(new Grid(22, 16, 11, 17));
        this.vGrid.addElement(new Grid(16, 11, 17, 22));
        this.vGrid.addElement(new Grid(12, 18, 23, 17));
        this.vGrid.addElement(new Grid(18, 23, 17, 12));
        this.vGrid.addElement(new Grid(23, 17, 12, 18));
        this.vGrid.addElement(new Grid(17, 12, 18, 23));
        this.vGrid.addElement(new Grid(13, 19, 24, 18));
        this.vGrid.addElement(new Grid(19, 24, 18, 13));
        this.vGrid.addElement(new Grid(24, 18, 13, 19));
        this.vGrid.addElement(new Grid(18, 13, 19, 24));
        this.vGrid.addElement(new Grid(14, 20, 25, 19));
        this.vGrid.addElement(new Grid(20, 25, 19, 14));
        this.vGrid.addElement(new Grid(25, 19, 14, 20));
        this.vGrid.addElement(new Grid(19, 14, 20, 25));
        this.vGrid.addElement(new Grid(15, 21, 26, 20));
        this.vGrid.addElement(new Grid(21, 26, 20, 15));
        this.vGrid.addElement(new Grid(26, 20, 15, 21));
        this.vGrid.addElement(new Grid(20, 15, 21, 26));
        this.vGrid.addElement(new Grid(22, 28, 33, 27));
        this.vGrid.addElement(new Grid(28, 33, 27, 22));
        this.vGrid.addElement(new Grid(33, 27, 22, 28));
        this.vGrid.addElement(new Grid(27, 22, 28, 33));
        this.vGrid.addElement(new Grid(23, 29, 34, 28));
        this.vGrid.addElement(new Grid(29, 34, 28, 23));
        this.vGrid.addElement(new Grid(34, 28, 23, 29));
        this.vGrid.addElement(new Grid(28, 23, 29, 34));
        this.vGrid.addElement(new Grid(24, 30, 35, 29));
        this.vGrid.addElement(new Grid(30, 35, 29, 24));
        this.vGrid.addElement(new Grid(35, 29, 24, 30));
        this.vGrid.addElement(new Grid(29, 24, 30, 35));
        this.vGrid.addElement(new Grid(25, 31, 36, 30));
        this.vGrid.addElement(new Grid(31, 36, 30, 25));
        this.vGrid.addElement(new Grid(36, 30, 25, 31));
        this.vGrid.addElement(new Grid(30, 25, 31, 36));
        this.vGrid.addElement(new Grid(26, 32, 37, 31));
        this.vGrid.addElement(new Grid(32, 37, 31, 26));
        this.vGrid.addElement(new Grid(37, 31, 26, 32));
        this.vGrid.addElement(new Grid(31, 26, 32, 37));
        this.vGrid.addElement(new Grid(33, 39, 44, 38));
        this.vGrid.addElement(new Grid(39, 44, 38, 33));
        this.vGrid.addElement(new Grid(44, 38, 33, 39));
        this.vGrid.addElement(new Grid(38, 33, 39, 44));
        this.vGrid.addElement(new Grid(34, 40, 45, 39));
        this.vGrid.addElement(new Grid(40, 45, 39, 34));
        this.vGrid.addElement(new Grid(45, 39, 34, 40));
        this.vGrid.addElement(new Grid(39, 34, 40, 45));
        this.vGrid.addElement(new Grid(35, 41, 46, 40));
        this.vGrid.addElement(new Grid(41, 46, 40, 35));
        this.vGrid.addElement(new Grid(46, 40, 35, 41));
        this.vGrid.addElement(new Grid(40, 35, 41, 46));
        this.vGrid.addElement(new Grid(36, 42, 47, 41));
        this.vGrid.addElement(new Grid(42, 47, 41, 36));
        this.vGrid.addElement(new Grid(47, 41, 36, 42));
        this.vGrid.addElement(new Grid(41, 36, 42, 47));
        this.vGrid.addElement(new Grid(37, 43, 48, 42));
        this.vGrid.addElement(new Grid(43, 48, 42, 37));
        this.vGrid.addElement(new Grid(48, 42, 37, 43));
        this.vGrid.addElement(new Grid(42, 37, 43, 48));
        this.vGrid.addElement(new Grid(44, 50, 55, 49));
        this.vGrid.addElement(new Grid(50, 55, 49, 44));
        this.vGrid.addElement(new Grid(55, 49, 44, 50));
        this.vGrid.addElement(new Grid(49, 44, 50, 55));
        this.vGrid.addElement(new Grid(45, 51, 56, 50));
        this.vGrid.addElement(new Grid(51, 56, 50, 45));
        this.vGrid.addElement(new Grid(56, 50, 45, 51));
        this.vGrid.addElement(new Grid(50, 45, 51, 56));
        this.vGrid.addElement(new Grid(46, 52, 57, 51));
        this.vGrid.addElement(new Grid(52, 57, 51, 46));
        this.vGrid.addElement(new Grid(57, 51, 46, 52));
        this.vGrid.addElement(new Grid(51, 46, 52, 57));
        this.vGrid.addElement(new Grid(47, 53, 58, 52));
        this.vGrid.addElement(new Grid(53, 58, 52, 47));
        this.vGrid.addElement(new Grid(58, 52, 47, 53));
        this.vGrid.addElement(new Grid(52, 47, 53, 58));
        this.vGrid.addElement(new Grid(48, 54, 59, 53));
        this.vGrid.addElement(new Grid(54, 59, 53, 48));
        this.vGrid.addElement(new Grid(59, 53, 48, 54));
        this.vGrid.addElement(new Grid(53, 48, 54, 59));
    }
    
    public void destroy() {
        this.offScreen.dispose();
    }
    
    public void drawBars(final Graphics graphics, final boolean b, final int n) {
        Image image;
        Image image2;
        if (b) {
            image = this.redBarHorz;
            image2 = this.redBarVert;
        }
        else {
            image = this.blueBarHorz;
            image2 = this.blueBarVert;
        }
        switch (n) {
            case 0: {
                graphics.drawImage(image, 22, 52, this);
                break;
            }
            case 1: {
                graphics.drawImage(image, 62, 52, this);
                break;
            }
            case 2: {
                graphics.drawImage(image, 103, 52, this);
                break;
            }
            case 3: {
                graphics.drawImage(image, 142, 52, this);
                break;
            }
            case 4: {
                graphics.drawImage(image, 183, 52, this);
                break;
            }
            case 5: {
                graphics.drawImage(image2, 13, 60, this);
                break;
            }
            case 6: {
                graphics.drawImage(image2, 53, 60, this);
                break;
            }
            case 7: {
                graphics.drawImage(image2, 93, 60, this);
                break;
            }
            case 8: {
                graphics.drawImage(image2, 133, 60, this);
                break;
            }
            case 9: {
                graphics.drawImage(image2, 173, 60, this);
                break;
            }
            case 10: {
                graphics.drawImage(image2, 213, 60, this);
                break;
            }
            case 11: {
                graphics.drawImage(image, 22, 92, this);
                break;
            }
            case 12: {
                graphics.drawImage(image, 62, 92, this);
                break;
            }
            case 13: {
                graphics.drawImage(image, 103, 92, this);
                break;
            }
            case 14: {
                graphics.drawImage(image, 142, 92, this);
                break;
            }
            case 15: {
                graphics.drawImage(image, 183, 92, this);
                break;
            }
            case 16: {
                graphics.drawImage(image2, 13, 99, this);
                break;
            }
            case 17: {
                graphics.drawImage(image2, 53, 99, this);
                break;
            }
            case 18: {
                graphics.drawImage(image2, 93, 99, this);
                break;
            }
            case 19: {
                graphics.drawImage(image2, 133, 99, this);
                break;
            }
            case 20: {
                graphics.drawImage(image2, 173, 99, this);
                break;
            }
            case 21: {
                graphics.drawImage(image2, 213, 99, this);
                break;
            }
            case 22: {
                graphics.drawImage(image, 22, 132, this);
                break;
            }
            case 23: {
                graphics.drawImage(image, 62, 132, this);
                break;
            }
            case 24: {
                graphics.drawImage(image, 103, 132, this);
                break;
            }
            case 25: {
                graphics.drawImage(image, 142, 132, this);
                break;
            }
            case 26: {
                graphics.drawImage(image, 183, 132, this);
                break;
            }
            case 27: {
                graphics.drawImage(image2, 13, 139, this);
                break;
            }
            case 28: {
                graphics.drawImage(image2, 53, 139, this);
                break;
            }
            case 29: {
                graphics.drawImage(image2, 93, 139, this);
                break;
            }
            case 30: {
                graphics.drawImage(image2, 133, 139, this);
                break;
            }
            case 31: {
                graphics.drawImage(image2, 173, 139, this);
                break;
            }
            case 32: {
                graphics.drawImage(image2, 213, 139, this);
                break;
            }
            case 33: {
                graphics.drawImage(image, 22, 172, this);
                break;
            }
            case 34: {
                graphics.drawImage(image, 62, 172, this);
                break;
            }
            case 35: {
                graphics.drawImage(image, 103, 172, this);
                break;
            }
            case 36: {
                graphics.drawImage(image, 142, 172, this);
                break;
            }
            case 37: {
                graphics.drawImage(image, 183, 172, this);
                break;
            }
            case 38: {
                graphics.drawImage(image2, 13, 179, this);
                break;
            }
            case 39: {
                graphics.drawImage(image2, 53, 179, this);
                break;
            }
            case 40: {
                graphics.drawImage(image2, 93, 179, this);
                break;
            }
            case 41: {
                graphics.drawImage(image2, 133, 179, this);
                break;
            }
            case 42: {
                graphics.drawImage(image2, 173, 179, this);
                break;
            }
            case 43: {
                graphics.drawImage(image2, 213, 179, this);
                break;
            }
            case 44: {
                graphics.drawImage(image, 22, 212, this);
                break;
            }
            case 45: {
                graphics.drawImage(image, 62, 212, this);
                break;
            }
            case 46: {
                graphics.drawImage(image, 103, 212, this);
                break;
            }
            case 47: {
                graphics.drawImage(image, 142, 212, this);
                break;
            }
            case 48: {
                graphics.drawImage(image, 183, 212, this);
                break;
            }
            case 49: {
                graphics.drawImage(image2, 13, 219, this);
                break;
            }
            case 50: {
                graphics.drawImage(image2, 53, 219, this);
                break;
            }
            case 51: {
                graphics.drawImage(image2, 93, 219, this);
                break;
            }
            case 52: {
                graphics.drawImage(image2, 133, 219, this);
                break;
            }
            case 53: {
                graphics.drawImage(image2, 173, 219, this);
                break;
            }
            case 54: {
                graphics.drawImage(image2, 213, 219, this);
                break;
            }
            case 55: {
                graphics.drawImage(image, 22, 252, this);
                break;
            }
            case 56: {
                graphics.drawImage(image, 62, 252, this);
                break;
            }
            case 57: {
                graphics.drawImage(image, 103, 252, this);
                break;
            }
            case 58: {
                graphics.drawImage(image, 142, 252, this);
                break;
            }
            case 59: {
                graphics.drawImage(image, 183, 252, this);
                break;
            }
        }
    }
    
    public int findLessScore() {
        int n = 0;
        int cnt = 0;
        this.storeBar = -1;
        this.loop = 0;
        this.amt = 999;
        this.totcnt = 0;
        this.alert = 1;
        this.repaint();
        this.j = 0;
        while (this.j < this.storage.length) {
            this.storage[this.j] = -1;
            ++this.j;
        }
        this.i = 0;
        while (this.i < this.vBrowse.size()) {
            final int forceMove = this.forceMove(this.i);
            if (forceMove == -1) {
                this.oh = true;
                this.repaint();
                break;
            }
            final MouseBrowse mouseBrowse = this.vBrowse.elementAt(forceMove);
            if (mouseBrowse.lockBar == -1) {
                ++this.totcnt;
                mouseBrowse.lockBar = forceMove;
                this.storage[cnt++] = forceMove;
                final int storeBar = forceMove;
                while (true) {
                    final int generateWinMove = this.generateWinMove();
                    if (generateWinMove == -1) {
                        break;
                    }
                    this.storage[cnt++] = generateWinMove;
                    ((MouseBrowse)this.vBrowse.elementAt(generateWinMove)).lockBar = generateWinMove;
                    ++n;
                }
                this.j = 0;
                while (this.j < this.storage.length) {
                    if (this.storage[this.j] != -1) {
                        this.vBrowse.elementAt(this.storage[this.j]).lockBar = -1;
                        this.storage[this.j] = -1;
                    }
                    ++this.j;
                }
                if (n < this.amt) {
                    this.amt = n;
                    this.storeBar = storeBar;
                }
                this.BOX = n;
                this.CNT = cnt;
                this.loop = this.i;
                n = 0;
                cnt = 0;
            }
            ++this.i;
        }
        this.alert = 2;
        this.repaint();
        return this.storeBar;
    }
    
    public int forceMove(final int n) {
        for (int i = n; i < this.vBrowse.size(); ++i) {
            if (((MouseBrowse)this.vBrowse.elementAt(i)).lockBar == -1) {
                return i;
            }
        }
        return -1;
    }
    
    public int generateMove() {
        final int n = (int)(new Random().nextDouble() * 60.0);
        if (((MouseBrowse)this.vBrowse.elementAt(n)).lockBar == -1) {
            this.j = 0;
            while (this.j < this.vGrid.size()) {
                final Grid grid = this.vGrid.elementAt(this.j);
                if (grid.element == n) {
                    final MouseBrowse mouseBrowse = this.vBrowse.elementAt(grid.a);
                    final MouseBrowse mouseBrowse2 = this.vBrowse.elementAt(grid.b);
                    final MouseBrowse mouseBrowse3 = this.vBrowse.elementAt(grid.c);
                    this.test3 = grid.element;
                    this.test4 = grid.a;
                    this.test5 = grid.b;
                    this.test6 = grid.c;
                    if (mouseBrowse.lockBar == -1 && mouseBrowse2.lockBar == -1 && mouseBrowse3.lockBar == -1) {
                        return n;
                    }
                }
                ++this.j;
            }
        }
        this.test = 999L;
        return -1;
    }
    
    public int generateWinMove() {
        for (int i = 0; i < this.vBrowse.size(); ++i) {
            if (((MouseBrowse)this.vBrowse.elementAt(i)).lockBar == -1) {
                for (int j = 0; j < this.vGrid.size(); ++j) {
                    final Grid grid = this.vGrid.elementAt(j);
                    if (grid.element == i) {
                        final MouseBrowse mouseBrowse = this.vBrowse.elementAt(grid.a);
                        final MouseBrowse mouseBrowse2 = this.vBrowse.elementAt(grid.b);
                        final MouseBrowse mouseBrowse3 = this.vBrowse.elementAt(grid.c);
                        if (mouseBrowse.lockBar != -1 && mouseBrowse2.lockBar != -1 && mouseBrowse3.lockBar != -1) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    public String getAppletInfo() {
        return "DOT_TO_DOT, Copyright 1999 Joe Hillman";
    }
    
    public int getRandom(int n, final int n2) {
        n += (int)(new Random().nextDouble() * n2);
        return n;
    }
    
    public void init() {
        final Graphics graphics = this.getGraphics();
        this.size();
        this.fm = graphics.getFontMetrics();
        this.fontWidth = this.fm.getMaxAdvance();
        this.fontHeight = this.fm.getHeight();
        this.setLayout(this.lm);
        this.cLevel.addItem("Expert");
        this.cLevel.addItem("Beginner");
        this.add(this.cLevel);
        this.cWturn.addItem("Player moves first");
        this.cWturn.addItem("Computer moves first");
        this.add(this.cWturn);
        this.currentScreen = graphics;
        this.offImage = this.createImage(this.size().width, this.size().height);
        this.offScreen = this.offImage.getGraphics();
        this.createBrowseObjects(this.vBrowse);
        this.createGrid();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 112 && !this.playing) {
            this.playing = true;
            this.cTurn = false;
            this.turnAgain = false;
            this.hScore = 0;
            this.cScore = 0;
            this.alert = 0;
            if (this.vBrowse.size() > 0) {
                this.vBrowse.removeAllElements();
                this.vDrawBar.removeAllElements();
                this.vGrid.removeAllElements();
                this.createBrowseObjects(this.vBrowse);
                this.createGrid();
            }
            this.cWturn.setEnabled(false);
            this.cLevel.setEnabled(false);
            if (this.cLevel.getSelectedIndex() == 0) {
                this.easyPlay = false;
            }
            else {
                this.easyPlay = true;
            }
            if (this.cWturn.getSelectedIndex() == 0) {
                this.cTurn = false;
            }
            else {
                this.cTurn = true;
                this.computersTurn();
            }
        }
        return true;
    }
    
    public void loadSounds() {
        try {
            this.dot = this.getImage(new URL(this.getDocumentBase(), "images/Dot.gif"));
            this.redBarHorz = this.getImage(new URL(this.getDocumentBase(), "images/RedBarHorz.gif"));
            this.redBarVert = this.getImage(new URL(this.getDocumentBase(), "images/RedBarVert.gif"));
            this.blueBarHorz = this.getImage(new URL(this.getDocumentBase(), "images/BlueBarHorz.gif"));
            this.blueBarVert = this.getImage(new URL(this.getDocumentBase(), "images/BlueBarVert.gif"));
            if (this.getRandom(0, 2) == 0) {
                this.BackGround = this.getImage(new URL(this.getDocumentBase(), "images/Brain.jpg"));
            }
            else {
                this.BackGround = this.getImage(new URL(this.getDocumentBase(), "images/Future.jpg"));
            }
            this.beep = this.getAudioClip(new URL(this.getDocumentBase(), "sounds/Beep.au"));
            this.drip = this.getAudioClip(new URL(this.getDocumentBase(), "sounds/Drip.au"));
            this.gong = this.getAudioClip(new URL(this.getDocumentBase(), "sounds/Gong.au"));
            this.laugh = this.getAudioClip(new URL(this.getDocumentBase(), "sounds/Laugh.au"));
            (this.music = this.getAudioClip(new URL(this.getDocumentBase(), "sounds/Music.au"))).loop();
        }
        catch (MalformedURLException ex) {}
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.cTurn || !this.playing) {
            return true;
        }
        this.addBar(n, n2);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.cTurn || !this.playing) {
            return true;
        }
        this.browseBar(n, n2);
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.loaded && Thread.currentThread() == this.loadThread) {
            this.loadSounds();
            this.loaded = true;
            this.loadThread.stop();
        }
        while (Thread.currentThread() == this.loopThread) {
            this.repaint();
            try {
                currentTimeMillis += 50L;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {
                break;
            }
        }
    }
    
    public void start() {
        if (this.loopThread == null) {
            (this.loopThread = new Thread(this)).start();
        }
        if (!this.loaded && this.loadThread == null) {
            (this.loadThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.loopThread != null) {
            this.loopThread.stop();
            this.loopThread = null;
            this.music.stop();
        }
        if (this.loadThread != null) {
            this.loadThread.stop();
            this.loadThread = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.size();
        this.offScreen.setFont(this.font);
        this.offScreen.setColor(this.getBackground());
        this.offScreen.fillRect(0, 0, this.size().width, this.size().height);
        this.offScreen.setColor(Color.black);
        if (!this.loaded) {
            this.offScreen.drawString("Loading images and sounds...", 75, 150);
        }
        else {
            this.offScreen.drawImage(this.BackGround, 0, 0, this);
            if (this.playing) {
                this.drawBars(this.offScreen, true, this.browseBar);
            }
            this.j = 50;
            while (this.j < 260) {
                this.i = 10;
                while (this.i < 240) {
                    this.offScreen.drawImage(this.dot, this.i, this.j, this);
                    this.i += 40;
                }
                this.j += 40;
            }
            if (this.playing) {
                this.i = 0;
                while (this.i < this.vDrawBar.size()) {
                    final Integer n = this.vDrawBar.elementAt(this.i);
                    this.drawBars(this.offScreen, ((MouseBrowse)this.vBrowse.elementAt(n)).color, n);
                    ++this.i;
                }
            }
            this.offScreen.setColor(Color.black);
            this.offScreen.drawString("Human: " + this.hScore, 239, 60);
            this.offScreen.drawString("Computer: " + this.cScore, 239, 262);
            this.offScreen.drawString("Copyright 1999 by Joe Hillman", 11, 287);
            if (!this.playing) {
                this.offScreen.setColor(Color.red);
                final String s = "GAME OVER";
                final String s2 = "P - PLAY";
                this.offScreen.drawString(s, 238, 160);
                this.offScreen.drawString(s2, 247, 170);
                this.drawBars(this.offScreen, true, this.getRandom(0, 5));
                this.drawBars(this.offScreen, false, this.getRandom(5, 10));
                this.drawBars(this.offScreen, true, this.getRandom(10, 15));
                this.drawBars(this.offScreen, false, this.getRandom(15, 20));
                this.drawBars(this.offScreen, true, this.getRandom(20, 25));
                this.drawBars(this.offScreen, false, this.getRandom(25, 30));
                this.drawBars(this.offScreen, true, this.getRandom(30, 35));
                this.drawBars(this.offScreen, false, this.getRandom(35, 40));
                this.drawBars(this.offScreen, true, this.getRandom(40, 45));
                this.drawBars(this.offScreen, false, this.getRandom(45, 50));
                this.drawBars(this.offScreen, false, this.getRandom(50, 55));
                this.drawBars(this.offScreen, false, this.getRandom(55, 60));
            }
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
}
