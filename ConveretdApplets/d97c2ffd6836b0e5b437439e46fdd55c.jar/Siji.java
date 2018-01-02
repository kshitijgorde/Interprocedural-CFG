import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.net.URL;
import java.awt.Panel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.TextField;
import java.awt.Graphics;
import java.awt.Button;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Siji extends Applet implements Runnable
{
    static int MAX_X;
    static int MAX_Y;
    static int MAX_LVL;
    static int MAX_IMG;
    static int xRect;
    static int yRect;
    static int X_0;
    static int Y_0;
    static int BALL_CODE;
    static int maxHist;
    Thread game0;
    private Image[] xImg;
    private int gLevel;
    private int mvPos;
    private int maxMove;
    private int dlyBall;
    private int num;
    private int xPos;
    private int yPos;
    private byte[][] currCanvas;
    private byte firstTime;
    private int[] hst;
    private Button btn1;
    private Button btnN2;
    private Button btnN;
    private Button btnP;
    private Button btnP2;
    private Button btnU;
    private static String stGameEnd;
    private static String stYouLose;
    private static String mCode;
    private static int[][] xCoord;
    private static String[] biDat;
    private static int[] aMove;
    private Image mainImg;
    private Graphics g;
    private TextField txt1;
    
    public void init() {
        if (this.g == null) {
            this.g = this.getGraphics();
        }
        final URL codeBase = this.getCodeBase();
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.mainImg = this.getImage(codeBase, "Images.gif"), 0);
        for (int i = 0; i <= Siji.MAX_IMG; ++i) {
            mediaTracker.addImage(this.xImg[i] = this.extractImage(Siji.xCoord[i]), i + 1);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.setLayout(new BorderLayout());
        (this.txt1 = new TextField(17)).setForeground(new Color(255, 255, 204));
        this.txt1.setBackground(new Color(102, 51, 51));
        this.txt1.setEditable(false);
        final Panel panel = new Panel();
        panel.add(this.txt1);
        panel.add(this.btnP2);
        panel.add(this.btnP);
        panel.add(this.btnN);
        panel.add(this.btnN2);
        panel.add(this.btnU);
        panel.add(this.btn1);
        this.add("South", panel);
        this.dat2Canvas(this.gLevel = 0);
        this.firstTime = 1;
        this.dlyBall = 30;
    }
    
    public void start() {
        if (this.game0 == null) {
            (this.game0 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.game0 != null) {
            this.game0.stop();
            this.game0 = null;
        }
    }
    
    public void run() {
    }
    
    public boolean dat2Canvas(final int n) {
        byte b = 0;
        String parameter;
        try {
            this.gLevel += n;
            if (this.gLevel < 0) {
                this.gLevel = 0;
            }
            int int1;
            try {
                int1 = Integer.parseInt(this.getParameter("maxmove" + this.gLevel));
            }
            catch (NumberFormatException ex) {
                int1 = 0;
            }
            parameter = this.getParameter("data" + this.gLevel);
            if (int1 > 0) {
                this.maxMove = int1;
            }
            if (parameter == null || int1 <= 0) {
                parameter = Siji.biDat[this.gLevel];
                this.maxMove = Siji.aMove[this.gLevel];
            }
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            this.gLevel -= n;
            parameter = Siji.biDat[this.gLevel];
            this.maxMove = Siji.aMove[this.gLevel];
        }
        for (int i = 0; i <= Siji.MAX_Y; ++i) {
            for (int j = 0; j <= Siji.MAX_X; ++j) {
                this.currCanvas[i][j] = 0;
            }
        }
        int n3;
        int n2 = n3 = 0;
        for (int k = 0; k < parameter.length(); ++k) {
            byte b2 = (byte)Siji.mCode.indexOf(parameter.charAt(k));
            if (b2 > Siji.MAX_IMG) {
                final int n4;
                if ((n4 = b2 - Siji.MAX_IMG - 1) > 0) {
                    for (int l = 0; l < n4; ++l) {
                        try {
                            this.currCanvas[n2][n3++] = b;
                        }
                        catch (ArrayIndexOutOfBoundsException ex3) {
                            l = n4;
                        }
                    }
                }
                else {
                    n3 = 0;
                    if (++n2 > Siji.MAX_Y) {
                        break;
                    }
                }
            }
            else if (n3 <= Siji.MAX_X) {
                if (b2 < 0) {
                    b2 = 0;
                }
                this.currCanvas[n2][n3++] = b2;
                b = b2;
            }
        }
        final int n5 = -1;
        this.yPos = n5;
        this.xPos = n5;
        this.mvPos = 0;
        for (int n6 = 0; n6 <= Siji.maxHist; ++n6) {
            this.hst[n6] = 0;
        }
        if (this.maxMove > 0) {
            this.txt1.setText(" Level " + this.gLevel + " (" + this.maxMove + " step max.)");
        }
        else {
            this.txt1.setText(" Level " + this.gLevel);
        }
        if (this.firstTime == 1) {
            this.firstTime = 0;
        }
        return true;
    }
    
    public int checkStep(final int n, final int n2) {
        int n3 = 0;
        final byte b = (byte)(Siji.BALL_CODE - 1);
        final byte b2 = (byte)Siji.BALL_CODE;
        if (this.currCanvas[n2][n] == b2) {
            if ((this.currCanvas[n2 - 1][n] == b2 && this.currCanvas[n2 - 2][n] == b) || (this.currCanvas[n2 + 1][n] == b2 && this.currCanvas[n2 + 2][n] == b) || (this.currCanvas[n2][n - 1] == b2 && this.currCanvas[n2][n - 2] == b) || (this.currCanvas[n2][n + 1] == b2 && this.currCanvas[n2][n + 2] == b)) {
                n3 = 1;
            }
        }
        else {
            n3 = -1;
        }
        return n3;
    }
    
    public int gameEnd() {
        int n = 0;
        int n2 = 0;
        for (int n3 = 2; n3 < Siji.MAX_Y - 1 && n == 0; ++n3) {
            for (int n4 = 2; n4 < Siji.MAX_X - 1 && n == 0; ++n4) {
                final int checkStep = this.checkStep(n4, n3);
                if (checkStep >= 0) {
                    ++n2;
                    if (checkStep > 0) {
                        n = 1;
                    }
                }
            }
        }
        if (n2 > 1 && n == 0) {
            n = -1;
        }
        return n;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        int n3 = 0;
        final int xPos = (n - Siji.X_0) / Siji.xRect;
        final int yPos = (n2 - Siji.Y_0) / Siji.yRect;
        if (xPos < 0 || xPos > Siji.MAX_X || yPos < 0 || yPos > Siji.MAX_Y) {
            return true;
        }
        if (this.xPos >= 0 && this.yPos >= 0 && this.xPos <= Siji.MAX_X && this.yPos <= Siji.MAX_Y) {
            n3 = this.currCanvas[this.yPos][this.xPos];
        }
        int n4 = this.currCanvas[yPos][xPos];
        int n5 = this.checkStep(xPos, yPos);
        if (n4 == Siji.BALL_CODE + 1) {
            --n4;
            n5 = 1;
        }
        else if (n5 > 0) {
            ++n4;
            n5 = 1;
        }
        else {
            final int n6 = (xPos - this.xPos) / 2;
            final int n7 = xPos - n6;
            final int n8 = (yPos - this.yPos) / 2;
            final int n9 = yPos - n8;
            if (n3 == Siji.BALL_CODE + 1 && (((n6 == 1 || n6 == -1) && n8 == 0) || ((n8 == 1 || n8 == -1) && n6 == 0)) && this.currCanvas[n9][n7] == Siji.BALL_CODE) {
                this.changeCanvas(this.xPos, this.yPos, Siji.BALL_CODE - 1);
                this.changeCanvas(n7, n9, Siji.BALL_CODE - 1);
                n4 = (byte)Siji.BALL_CODE;
                this.hst[this.mvPos % Siji.maxHist] = 16 * yPos + xPos + 256 * (1 + n6 + 4 * (1 + n8));
                n3 = 0;
                ++this.mvPos;
                n5 = 2;
            }
        }
        if (n5 > 0) {
            if (n3 >= Siji.BALL_CODE) {
                this.changeCanvas(this.xPos, this.yPos, Siji.BALL_CODE);
            }
            this.changeCanvas(this.xPos = xPos, this.yPos = yPos, n4);
            if (n5 == 2) {
                n5 = this.gameEnd();
                if (n5 == 0) {
                    this.txt1.setText(Siji.stGameEnd);
                    n5 = -99;
                }
                else if (n5 == -1) {
                    this.txt1.setText(Siji.stYouLose);
                    n5 = -100;
                }
                else {
                    this.txt1.setText("Level " + this.gLevel + " step " + this.mvPos);
                }
            }
        }
        if (n5 <= -99) {
            try {
                Thread.sleep(400L);
            }
            catch (InterruptedException ex) {}
            if (this.dat2Canvas(n5 + 100)) {
                this.paint(null);
            }
        }
        return true;
    }
    
    public void changeCanvas(final int n, final int n2, final int n3) {
        this.currCanvas[n2][n] = (byte)n3;
        this.g.drawImage(this.xImg[n3], Siji.X_0 + Siji.xRect * n, Siji.Y_0 + Siji.yRect * n2, this);
    }
    
    public boolean action(final Event event, final Object o) {
        int n = 0;
        if (event.target == this.btnP) {
            n = 106;
        }
        else if (event.target == this.btnN) {
            n = 107;
        }
        else if (event.target == this.btn1) {
            n = 27;
        }
        else if (event.target == this.btnP2) {
            n = 104;
        }
        else if (event.target == this.btnN2) {
            n = 108;
        }
        if (n > 0) {
            this.keyDown(event, n);
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        int n2 = 0;
        switch (n) {
            case 8: {
                if (this.mvPos > 0) {
                    this.doUndo();
                    break;
                }
                break;
            }
            case 27: {
                if (this.mvPos == 0 && this.firstTime == 0) {
                    this.firstTime = 1;
                    this.paint(null);
                    return true;
                }
                n2 = 100;
                break;
            }
            case 104: {
                n2 = 89;
                break;
            }
            case 106: {
                n2 = 99;
                break;
            }
            case 107: {
                n2 = 101;
                break;
            }
            case 108: {
                n2 = 111;
                break;
            }
            default: {
                this.txt1.setText("Key " + n + " is not defined");
                return true;
            }
        }
        if (n2 > 0 && this.dat2Canvas(n2 - 100)) {
            this.paint(null);
        }
        return true;
    }
    
    private void doUndo() {
        --this.mvPos;
        final int n = this.hst[this.mvPos % Siji.maxHist];
        this.hst[this.mvPos] = 0;
        final int n2 = n / 256;
        final int n3 = n2 % 4 - 1;
        final int n4 = n2 / 4 % 4 - 1;
        int n5 = n % 16;
        int n6 = n / 16 % 16;
        this.txt1.setText("Back to step " + this.mvPos);
        int ball_CODE = Siji.BALL_CODE - 1;
        for (int i = 0; i < 3; ++i) {
            this.changeCanvas(n5, n6, ball_CODE);
            ball_CODE = Siji.BALL_CODE;
            n5 -= n3;
            n6 -= n4;
        }
    }
    
    private Image extractImage(final int[] array) {
        return this.createImage(new FilteredImageSource(this.mainImg.getSource(), new CropImageFilter(array[0], array[1], array[2], array[3])));
    }
    
    public void paint(Graphics graphics) {
        if (graphics == null) {
            graphics = this.getGraphics();
        }
        int y_0 = Siji.Y_0;
        final int n = Siji.X_0 + Siji.xRect * (Siji.MAX_X + 1);
        for (int i = 0; i <= Siji.MAX_Y; ++i) {
            int x_0 = Siji.X_0;
            for (int j = 0; j <= Siji.MAX_X; ++j) {
                int n2 = this.currCanvas[i][j];
                if (this.firstTime == 1 || n2 > Siji.MAX_IMG) {
                    n2 = 0;
                }
                graphics.drawImage(this.xImg[n2], x_0, y_0, this);
                x_0 += Siji.xRect;
            }
            y_0 += Siji.yRect;
        }
        if (this.firstTime == 1) {
            this.doFirstTime();
        }
    }
    
    public void doFirstTime() {
        int n = 20;
        final int n2 = Siji.X_0 + 15;
        this.g.setColor(new Color(255, 255, 204));
        final String text = "* Siji v0.9 *";
        this.g.drawString(text, n2, n);
        this.txt1.setText(text);
        n += 20;
        this.g.drawString("(Java Applet version) 2000 by Riza PN", n2, n);
        n += 20;
        this.g.drawString("Siji is a word of 'One' in Javanesse language.", n2, n);
        n += 20;
        this.g.drawString("The goal is to take all object until the last one by jump over", n2, n);
        n += 15;
        this.g.drawString("another object. Click one object, jump over another object,", n2, n);
        n += 15;
        this.g.drawString("move it to the blank place, and it will clear the middle object.", n2, n);
        n += 15;
        this.g.drawString("Repeat it until there is only one object in the screen.", n2, n);
        n += 15;
        this.g.drawString("Press U button or use Backspace to undo the last move (it", n2, n);
        n += 15;
        this.g.drawString("can do until 200 last steps). Press X button or Esc key to", n2, n);
        n += 15;
        this.g.drawString("restart the game. Use h,j,k,l to change the level.", n2, n);
        n += 15;
        this.g.drawString("Almost all level is based on its similar game from internet.", n2, n);
        n += 20;
        this.g.drawString("Hope you enjoy it...", n2, n);
        n += 20;
        this.g.drawString("Salam", n2, n);
        n += 15;
        this.g.drawString("Riza Purwo Nugroho, Jakarta-Indonesia", n2, n);
    }
    
    public Siji() {
        this.xImg = new Image[Siji.MAX_IMG + 1];
        this.currCanvas = new byte[Siji.MAX_Y + 1][Siji.MAX_X + 1];
        this.hst = new int[Siji.maxHist + 1];
        this.btn1 = new Button("X");
        this.btnN2 = new Button(">>");
        this.btnN = new Button(">");
        this.btnP = new Button("<");
        this.btnP2 = new Button("<<");
        this.btnU = new Button("U");
    }
    
    static {
        Siji.MAX_X = 12;
        Siji.MAX_Y = 10;
        Siji.MAX_LVL = 100;
        Siji.MAX_IMG = 5;
        Siji.xRect = 24;
        Siji.yRect = 24;
        Siji.X_0 = 5;
        Siji.Y_0 = 5;
        Siji.BALL_CODE = 4;
        Siji.maxHist = 200;
        Siji.stGameEnd = " [GAME END]";
        Siji.stYouLose = " [YOU LOSE]";
        Siji.mCode = " =#.xo,123456789ABCDEFGHIJKLMN";
        Siji.xCoord = new int[][] { { 0, 0, Siji.xRect, Siji.yRect }, { Siji.xRect, 0, Siji.xRect, Siji.yRect }, { 2 * Siji.xRect, 0, Siji.xRect, Siji.yRect }, { 0, Siji.yRect, Siji.xRect, Siji.yRect }, { Siji.xRect, Siji.yRect, Siji.xRect, Siji.yRect }, { 2 * Siji.xRect, Siji.yRect, Siji.xRect, Siji.yRect } };
        Siji.biDat = new String[] { ", 3#4, 2#1.x.#, 2#.x.1#, 2#x#x.#, 2#x.x.#, 2#.1#2, 2#.#1, 2#2,,", ",, 2#3, 2#.1#2, 2#.x1.#, 2#x3#1, 1#1.1x.1#, 1#.2#3, 1#1.#1, 2#2,,", ", 1#2, 1#.#5, 1#.#.x#.#, 1#.x.x.1#, 1#.x3.#, 1#1.1x1.#, 2#4.#, 6#2,,", ", 2#5, 2#x1.1#1, 1#1x1.2#, 1#.x1.2#, 1#1x2.#1, 2#1.x.1#, 3#.1#2, 3#3,,", ",, 4#4, 3#1.2#, 2#1.3#, 1#1x1.2#, 1#x3.#1, 1#1.x#2, 2#3,", ",, 1#4, 1#.2#1, 1#.x1.#, 1#1.2#2, 2#1.x.1#, 3#1x2#, 4#4,", ", 3#3, 3#.1#1, 2#1x1.#1, 2#.x.x1#, 2#.1x.1#, 2#1x1#2, 1#1.x.#, 1#.2#1, 1#4,", ",, 3#4, 2#1.x.#, 2#.2x#, 2#1.x1#1, 2#x1.1x#, 2#.3#1, 2#5,", ", 1#8, 1#x.x1.x.#, 1#1x#2x1#, 1#1.# #x1#, 1#.x#3.#, 1#.#2.2#, 1#.#2.#.#, 1#3.1#2, 4#3,,", ", 2#5, 2#.1x.#, 2#.x1#1, 2#.x1#1, 2#x.x.#1, 2#.1x1.#, 2#.#.1#1, 2#.#3, 2#2,,", ",,, 1#4, 1#.2#2, 1#.1x1.#, 1#.1x1#3, 1#.2x.x1#, 1#8,,", ", 2#3, 1#1.1#3, 1#.2x1.#, 1#1.x.x#1, 2#1x1#1, 3#.x#1, 3#.2#, 3#4,,", ", 5#2, 4#1x#1, 3#1x1.#, 3#.x2#1, 3#.2x1#, 3#.#.2#, 3#.3#1, 3#5,,", ", 7#3, 2#5x.#, 2#.x.#1x.#,#3x3.x.#,#.2#.#5,#.1x1.#,#.1#3,#3,,", ", 2#6, 1#1.1#x.#, 1#x1.3#, 1#.1#x1.#, 1#3x1#1, 4#1.1#, 5#3,,", ", 6#2, 3#3.#, 2#1.x.1#, 2#.1x.1#, 2#.x2#1, 2#x.x1#, 2#2.1#, 4#1.#, 5#2,,", ",, 6#2, 1#5.#, #1.1x1.1#, #.2x1.1#1, #2.x1.x1#, 2#1x1.1#1, 3#1.#2, 4#2,,", ", 4#2, 2#2.#3, 1#1x.4#, #1.x#5, #.x.#1, #x2.#, #2.#1, 2#2,,", ",, 6#2, 2#4.#, 2#.1x1.#, 2#x.x1.#, 2#x.x1.#, 2#.4#, 2#6,,", ",, 2#4, 1#1.2#3, 1#x1.1#1.#, 1#.x2#1.#, 1#1x.x1.1#, 2#1x.1#.#, 3#6,,,", ",, 1#6, 1#.1x.1#, 1#.x2.#, 1#2.x.#1, 3#x#.1#, 3#.#3, 3#2,,", ",, 1#3, #1.x#2, #.x1.x#2, #2.x1.1#, 2#x#1.#2, 2#1.4#, 3#6,,", ",, 2#7, 1#1.1#.x.#, 1#.3x1.#, 1#.x1.x.x#, 1#1.#.x1#1, 2#6,,", ",, 2#4, 2#.2#1, 2#.1x1#2, 2#.1x1.1#, 2#x1.1#2, 2#5,,", ",, 2#7, 2#.x#.2#, 2#x1.1x1#, 2#.x.2#1, 2#.x#3, 2#.#1, 2#2,,", ",, 1#4, 1#x2#1, 1#x1.x#, 1#2.1#2, 1#1.x2.#2, 1#.2x.3#, 1#.1#2.#2, 1#3 #2,,", ",,, 2#6, 2#.#x.1#1, 2#.1x#.1#, 2#.x1.x.#, 2#4x.#, 6#3,,", ",, 2#7, 2#.1x1.1#, 2#1x1.#.#, 3#2.x1#, 5#.1#1, 5#.1#, 5#3,,", ", 5#2, 1#4.#, 1#.4#, 1#.1x.1#1, 1#2.x2#, 3#1x2#, 4#1.#1, 5#2,,", ",, 2#3, 2#.x# #2, 2#.x#2.#, 2#x.#.2#, 1#1x3.#1, 1#x.1#1.1#, 1#6.#, 7#2,,", ", 3#4, 3#.2#, 1#2.x.#2, 1#.2x.2#, 1#.x4.#, 1#.2x.2#, 1#2.x.#2, 3#.2#, 3#4,,", ", 3#4, 3#x2#, 1#2x2#2, 1#.1x2.1#, 1#.1x.x.1#, 1#.6#, 1#2.2#2, 3#.2#, 3#4,,", ", 3#4, 3#.2#, 1#2.x.#2, 1#.1x2.1#, 1#.x4.#, 1#x6#, 1#2.2#2, 3#.2#, 3#4,,", ", 3#4, 3#.x.#, 1#2x2#2, 1#.x4.#, 1#.2x.2#, 1#.2x.2#, 1#2x2#2, 3#x2#, 3#4,,", ", 3#4, 3#.x.#, 1#2x2#2, 1#.x4.#, 1#.1x2.1#, 1#.x4.#, 1#2x2#2, 3#.x.#, 3#4,,", ", 3#4, 3#.x.#, 1#2x2#2, 1#.x4.#, 1#x2.x2#, 1#.x4.#, 1#2x2#2, 3#.x.#, 3#4,,", ", 3#4, 3#x2#, 1#2x2#2, 1#x6#, 1#x2.x2#, 1#x6#, 1#2x2#2, 3#x2#, 3#4,,", " 3#4, 3#x2#, 3#x2#, #3x2#3, #x8#, #x3.x3#, #x8#, #3x2#3, 3#x2#, 3#x2#, 3#4,,", " 2#9, 2#.1#.x1.1#, 2#.x#x2.1#,#3.x.x.1x.#,#.#1x2.x2.#,#.4x2.1x#,#.1#.#x5#,#.1#.1#x.x1.#,#4.x2.x1#, 3#2.3#1, 5#5,,", " 1#8, 1#.#.2#.#1, 1#.x.#.#.1#, 1#1x.5#, 1#.3x1.1#, 1#.1x2.x1#, 1#.x2.3#, #2x2#.x1#,#1x3.x3#,#x1.x1.x.#2,#9,,", "#3 #6,#.x#2.4#,#.x.x1.4#,#x2#x1.3#,#.x4.3#,#1.x3.#.1#,#x5.#3,#.x1#.2#,#.#6,#2,,", " 2#2, #2x#2,#1x.x2#2,#.x5.#,#.x1.x1.1#3,#x1.x1.2#1.#,#.x.1x2.3#,#.x5.3#,#.x3.5#,#.1x1.6#,#C,,", " 5#2, 5#.#,#6.#3,#.1#1.x1.2#1,#.4x.x3#,#.4x3.x#,#.2x4.x1#,#.3x6#,#.5x2.x#,#C,,", "#8,#.#1.x1.#2,#.1#x4.#,#x1.x4.#,#.#1.x4#1,#.x1.x2.2#,#.2x.x#.1#1,#1.2x.#3, #.2x.#, #.#.2#, #6,,", ", #B,#1.x#.1x1.2#,#.2x2.x.x1#,#2x2.1x1.1#, 1#x3.#.2#, 1#x3#.2#1, 1#x.x.x1.2#, 1#1.1#1.1#2, 2#7,,", " 5#2, 5#.#3,#6x.2#,#.6x1#1,#.x.x4.1#,#1x4.2#1,#x4.#.2#,#x1.x1.x1.#1,#.x2#.4#,#1.x1.2#3, #7,," };
        Siji.aMove = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
