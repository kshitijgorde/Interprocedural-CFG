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

public class Shooter extends Applet implements Runnable
{
    static int MAX_X;
    static int MAX_Y;
    static int MAX_LVL;
    static int MAX_IMG;
    static int X_0;
    static int Y_0;
    static int BALL_CODE;
    Thread game0;
    private Image[] xImg;
    private int gLevel;
    private int mvPos;
    private int maxMove;
    private int shPos;
    private int dlyBall;
    private int num;
    private byte[][] currCanvas;
    private byte firstTime;
    private Button btn1;
    private Button btnN;
    private Button btnP;
    private Button btnBP;
    private Button btnBM;
    private static String stGameEnd;
    private static String stYouLose;
    private static String ascCode;
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
        mediaTracker.addImage(this.mainImg = this.getImage(codeBase, "Balls.gif"), 0);
        for (int i = 0; i <= Shooter.MAX_IMG; ++i) {
            mediaTracker.addImage(this.xImg[i] = this.extractImage(Shooter.xCoord[i]), i + 1);
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
        panel.add(this.btnBM);
        panel.add(this.btnBP);
        panel.add(this.btnP);
        panel.add(this.btnN);
        panel.add(this.btn1);
        this.add("South", panel);
        this.dat2Canvas(this.gLevel = 0);
        this.firstTime = 1;
        this.dlyBall = 5;
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
                parameter = Shooter.biDat[this.gLevel];
                this.maxMove = Shooter.aMove[this.gLevel];
            }
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            this.gLevel -= n;
            parameter = Shooter.biDat[this.gLevel];
            this.maxMove = Shooter.aMove[this.gLevel];
        }
        for (int i = 0; i <= Shooter.MAX_Y; ++i) {
            for (int j = 0; j <= Shooter.MAX_X; ++j) {
                this.currCanvas[i][j] = 0;
            }
        }
        int n3;
        int n2 = n3 = 0;
        for (int k = 0; k < parameter.length(); ++k) {
            byte b2 = (byte)Shooter.mCode.indexOf(parameter.charAt(k));
            if (b2 > Shooter.MAX_IMG) {
                final int n4;
                if ((n4 = b2 - Shooter.MAX_IMG - 1) > 0) {
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
                    if (++n2 > Shooter.MAX_Y) {
                        break;
                    }
                }
            }
            else if (n3 <= Shooter.MAX_X) {
                if (b2 < 0) {
                    b2 = 0;
                }
                this.currCanvas[n2][n3++] = b2;
                b = b2;
            }
        }
        this.mvPos = 0;
        this.shPos = 12;
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
    
    public boolean action(final Event event, final Object o) {
        int n = 0;
        int n2 = -1;
        if (event.target == this.btnP) {
            n2 = 44;
        }
        else if (event.target == this.btnN) {
            n2 = 46;
        }
        else if (event.target == this.btn1) {
            n2 = 27;
        }
        else if (event.target == this.btnBP) {
            n = 1;
            if (this.dlyBall < 1000) {
                this.dlyBall += 5;
            }
        }
        else if (event.target == this.btnBM) {
            n = 1;
            if (this.dlyBall > 5) {
                this.dlyBall -= 5;
            }
        }
        if (n2 > 0) {
            this.keyDown(event, n2);
        }
        else if (n == 1) {
            this.txt1.setText(" Animation delay = " + this.dlyBall);
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        boolean b = false;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        switch (n) {
            case 1004: {
                n2 = -1;
                break;
            }
            case 1005: {
                n2 = 1;
                break;
            }
            case 10: {
                b = true;
                break;
            }
            case 27: {
                if (this.mvPos == 0 && this.firstTime == 0) {
                    this.firstTime = 1;
                    this.paint(null);
                    return true;
                }
                n4 = -100;
                break;
            }
            case 44: {
                n4 = -101;
                break;
            }
            case 46: {
                n4 = -99;
                break;
            }
            case 14: {
                n4 = -90;
                break;
            }
            case 16: {
                n4 = -110;
                break;
            }
            default: {
                this.txt1.setText("Key #" + n + " is not defined");
                break;
            }
        }
        if ((!b && n2 == 0) || this.firstTime == 1) {
            if (n4 < 0 && this.dat2Canvas(n4 + 100)) {
                this.paint(null);
            }
            return true;
        }
        if (!b) {
            final byte b2 = this.currCanvas[this.shPos][9];
            final int shPos = this.shPos + n2;
            if (this.currCanvas[shPos][9] == 0) {
                this.currCanvas[this.shPos][9] = 0;
                this.g.drawImage(this.xImg[0], Shooter.X_0 + 180, Shooter.Y_0 + 20 * this.shPos, this);
                this.currCanvas[shPos][9] = b2;
                this.g.drawImage(this.xImg[b2], Shooter.X_0 + 180, Shooter.Y_0 + 20 * shPos, this);
                this.shPos = shPos;
            }
            return true;
        }
        if (this.moveBall(this.shPos, 1) <= 0) {
            this.txt1.setText(" Please see instruction ");
            return true;
        }
        final int gameEnd = this.GameEnd();
        if (gameEnd == 1) {
            this.txt1.setText(Shooter.stGameEnd);
            n3 = -98;
        }
        else if (gameEnd == -1) {
            this.txt1.setText(Shooter.stYouLose);
            n3 = -99;
        }
        else {
            ++this.mvPos;
            if (this.maxMove > 0) {
                if (this.mvPos >= this.maxMove) {
                    this.txt1.setText(Shooter.stYouLose);
                    n3 = -99;
                }
                else {
                    this.txt1.setText(" Have " + (this.maxMove - this.mvPos) + " more step.");
                }
            }
            if (n3 == 0) {
                return true;
            }
        }
        final byte b3 = this.currCanvas[this.shPos][9];
        int i = 0;
        int shPos2 = this.shPos;
        int n5 = -1;
        while (i < 3) {
            try {
                Thread.sleep(3 * this.dlyBall);
            }
            catch (InterruptedException ex) {}
            this.currCanvas[shPos2][9] = 0;
            this.g.drawImage(this.xImg[0], Shooter.X_0 + 180, Shooter.Y_0 + 20 * shPos2, this);
            shPos2 += n5;
            if (this.currCanvas[shPos2][9] != 0) {
                n5 = -n5;
                shPos2 += 2 * n5;
                ++i;
            }
            this.currCanvas[shPos2][9] = b3;
            this.g.drawImage(this.xImg[b3], Shooter.X_0 + 180, Shooter.Y_0 + 20 * shPos2, this);
            if (i == 1 && shPos2 == 12) {
                this.animBall(1, 12, 9, (byte)0);
                this.animBall(-1, 12, 9, (byte)3);
                break;
            }
        }
        if (this.dat2Canvas(n3 + 99)) {
            this.paint(null);
        }
        return true;
    }
    
    public void animBall(int n, final int n2, final int n3, final byte b) {
        int n4;
        if (n == 1) {
            n4 = 3;
        }
        else {
            n4 = 6;
            n = -1;
        }
        for (int i = 0; i <= 4; i = (byte)(i + 1)) {
            this.g.drawImage(this.xImg[n4], Shooter.X_0 + 20 * n3, Shooter.Y_0 + 20 * n2, this);
            try {
                Thread.sleep(this.dlyBall);
            }
            catch (InterruptedException ex) {}
            n4 = (byte)(n4 + n);
            if (i == 3) {
                n4 = b;
            }
        }
    }
    
    public int moveBall(final int n, final int n2) {
        boolean b = false;
        int n3 = 0;
        int n4 = -1;
        int n5 = 0;
        int n6 = 9;
        byte b2 = this.currCanvas[n][9];
        int n7 = n;
        while (n6 >= 0 && n7 < 13 && !b) {
            if (n2 > 0) {
                if (n2 == 1) {
                    if (n3 > 0 && this.currCanvas[n7][n6] != 0) {
                        this.animBall(1, n7, n6, (byte)0);
                    }
                    else {
                        try {
                            Thread.sleep(this.dlyBall);
                        }
                        catch (InterruptedException ex) {}
                        this.g.drawImage(this.xImg[0], Shooter.X_0 + 20 * n6, Shooter.Y_0 + 20 * n7, this);
                    }
                }
                this.currCanvas[n7][n6] = 0;
            }
            final byte b3 = this.currCanvas[n7 + n5][n6 + n4];
            if (b3 == b2 || (b2 == Shooter.BALL_CODE && b3 > Shooter.BALL_CODE)) {
                ++n3;
                if (b3 != 0) {
                    b2 = b3;
                }
                if (n2 == 0) {
                    return n3;
                }
            }
            else if (n5 == 0 && (b3 == 1 || b3 == 2)) {
                n4 = 0;
                n5 = 1;
            }
            else if (b3 > Shooter.BALL_CODE) {
                b = true;
            }
            if (b3 <= 2 || n3 > 0) {
                if (n2 > 0 && n3 > 0) {
                    int n8 = n7 - 1;
                    final byte b4 = (byte)(Shooter.BALL_CODE + 1);
                    while (true) {
                        final byte b5 = this.currCanvas[n8][n6];
                        if (b5 <= Shooter.BALL_CODE) {
                            break;
                        }
                        if (n2 == 1) {
                            try {
                                Thread.sleep(this.dlyBall / 2);
                            }
                            catch (InterruptedException ex2) {}
                            this.g.drawImage(this.xImg[0], Shooter.X_0 + 20 * n6, Shooter.Y_0 + 20 * n8, this);
                            this.g.drawImage(this.xImg[b5], Shooter.X_0 + 20 * n6, Shooter.Y_0 + 20 * (n8 + 1), this);
                        }
                        this.currCanvas[n8][n6] = 0;
                        this.currCanvas[n8 + 1][n6] = b5;
                        --n8;
                    }
                }
                n6 += n4;
                do {
                    n7 += n5;
                } while (n5 != 0 && n7 < 12 && this.currCanvas[n7][n6] == 2);
                if (n6 < 0 || n7 >= 13) {
                    continue;
                }
                if (n2 > 0) {
                    this.currCanvas[n7][n6] = b2;
                    if (n2 == 1) {
                        if (b) {
                            this.animBall(1, n7, n6, (byte)0);
                        }
                        this.g.drawImage(this.xImg[b2], Shooter.X_0 + 20 * n6, Shooter.Y_0 + 20 * n7, this);
                    }
                }
                if ((n6 == 0 || (n3 > 0 && this.currCanvas[n7 + 1][n6] == 0)) && n5 == 0) {
                    n4 = 0;
                    n5 = 1;
                }
                if (!b) {
                    continue;
                }
                b2 = b3;
            }
        }
        if (n2 > 0) {
            this.currCanvas[n][9] = b2;
            if (n2 == 1) {
                this.g.drawImage(this.xImg[b2], Shooter.X_0 + 180, Shooter.Y_0 + 20 * n, this);
            }
        }
        return n3;
    }
    
    public int GameEnd() {
        int n = 1;
        final int[] array = new int[Shooter.MAX_IMG + 1];
        for (int i = 0; i <= Shooter.MAX_IMG; ++i) {
            array[i] = 0;
        }
        for (int j = 0; j <= Shooter.MAX_Y; ++j) {
            for (int k = 0; k <= Shooter.MAX_X; ++k) {
                final int[] array2 = array;
                final byte b = this.currCanvas[j][k];
                ++array2[b];
            }
        }
        for (int l = Shooter.BALL_CODE + 1; l <= Shooter.MAX_IMG; ++l) {
            if (array[l] > 1) {
                n = -1;
                int n2 = 1;
                final byte b2 = this.currCanvas[this.shPos][9];
                while (n2 <= 13 && n < 0) {
                    final byte b3 = this.currCanvas[n2][9];
                    if (b3 != 1) {
                        this.currCanvas[n2][9] = b2;
                        if (this.moveBall(n2, 0) > 0) {
                            n = 0;
                        }
                        this.currCanvas[n2][9] = b3;
                    }
                    ++n2;
                }
                l = Shooter.MAX_IMG + 1;
            }
        }
        return n;
    }
    
    private Image extractImage(final int[] array) {
        return this.createImage(new FilteredImageSource(this.mainImg.getSource(), new CropImageFilter(array[0], array[1], array[2], array[3])));
    }
    
    public void paint(Graphics graphics) {
        if (graphics == null) {
            graphics = this.getGraphics();
        }
        int y_0 = Shooter.Y_0;
        final int n = Shooter.X_0 + 20 * (Shooter.MAX_X + 1);
        for (int i = 0; i <= Shooter.MAX_Y; ++i) {
            graphics.drawImage(this.xImg[1], Shooter.X_0 - 20, y_0, this);
            graphics.drawImage(this.xImg[1], n, y_0, this);
            int x_0 = Shooter.X_0;
            for (int j = 0; j <= Shooter.MAX_X; ++j) {
                int n2 = this.currCanvas[i][j];
                if (this.firstTime == 1 || n2 > Shooter.MAX_IMG) {
                    n2 = 0;
                }
                graphics.drawImage(this.xImg[n2], x_0, y_0, this);
                x_0 += 20;
            }
            y_0 += 20;
        }
        if (this.firstTime == 1) {
            this.doFirstTime();
        }
    }
    
    public void doFirstTime() {
        int n = 20;
        final int n2 = 5 + Shooter.X_0;
        this.g.setColor(new Color(255, 255, 204));
        final String text = "* Shooter v1.30 *";
        this.g.drawString(text, n2, n);
        this.txt1.setText(text);
        n += 20;
        this.g.drawString("(Java Applet version) 2000 by Riza PN", n2, n);
        n += 20;
        this.g.drawString("The goal is destroy all duplicated bombs", n2, n);
        n += 15;
        this.g.drawString("by shooting it with the key bomb (in the", n2, n);
        n += 15;
        this.g.drawString("bottom-right corner). Bomb can be", n2, n);
        n += 15;
        this.g.drawString("destroyed if the key has same color or it", n2, n);
        n += 15;
        this.g.drawString("is star. Use up and down arrow to move", n2, n);
        n += 15;
        this.g.drawString("the key, or press ENTER to run it.", n2, n);
        n += 15;
        this.g.drawString("It will run to the left, but if there is a wall,", n2, n);
        n += 15;
        this.g.drawString("it will go down. Game will be finished if it", n2, n);
        n += 15;
        this.g.drawString("is impossible to use a key (YOU LOSE)", n2, n);
        n += 15;
        this.g.drawString("or if no more duplicated ones (WIN)", n2, n);
        n += 20;
        this.g.drawString("Hope you enjoy it...", n2, n);
        n += 20;
        this.g.drawString("Salam", n2, n);
        n += 15;
        this.g.drawString("Riza Purwo Nugroho, Jakarta-Indonesia", n2, n);
    }
    
    public Shooter() {
        this.xImg = new Image[Shooter.MAX_IMG + 1];
        this.currCanvas = new byte[Shooter.MAX_Y + 1][Shooter.MAX_X + 1];
        this.btn1 = new Button("X");
        this.btnN = new Button(">");
        this.btnP = new Button("<");
        this.btnBP = new Button("+");
        this.btnBM = new Button("-");
    }
    
    static {
        Shooter.MAX_X = 9;
        Shooter.MAX_Y = 15;
        Shooter.MAX_LVL = 100;
        Shooter.MAX_IMG = 12;
        Shooter.X_0 = 30;
        Shooter.Y_0 = 5;
        Shooter.BALL_CODE = 7;
        Shooter.stGameEnd = " [GAME END]";
        Shooter.stYouLose = " [YOU LOSE]";
        Shooter.ascCode = "-ABCDEFGHIJKLMNOP";
        Shooter.mCode = " #=    *.oO@+,123456789abcdefghij";
        Shooter.xCoord = new int[][] { { 0, 0, 20, 20 }, { 20, 0, 20, 20 }, { 120, 0, 20, 20 }, { 40, 0, 20, 20 }, { 60, 0, 20, 20 }, { 80, 0, 20, 20 }, { 100, 0, 20, 20 }, { 100, 20, 20, 20 }, { 0, 20, 20, 20 }, { 20, 20, 20, 20 }, { 40, 20, 20, 20 }, { 60, 20, 20, 20 }, { 80, 20, 20, 20 } };
        Shooter.biDat = new String[] { "#9,#3,#2,#1,#,,,,,O+2,.O2,o3,+.2 4o,#9,", "#9,#3,#2,#1,#,,,,,O.2,+O2,o.2,.O2 4*,#9,", "#9,#3,#2,#1,#,,,,,.3,o2.,.3,oO+. 4*,#9,", "#9,#3,#2,#1,#,,,,,O.O.,O.O.,.O.O,+o.O 4*,#9,", "#9,#3,#2,#1,#,,,,,O+o.,+o.O,+o.O,+o.O 4*,#9,", "#9,#3=,#1,#,,,,, 5=,o1+1,.1o1 1=,oO2,.+oO 4+,#9,", "#9,#3,#2,#1,#,,,,,O+1o,oO1o,o2+,.O.o 4o,#9,", "#9,#3,#2,#1,#,,,,,+1O1,o+1o,o1.1,.o+1 4O,#9,", "#9,#3,#2,#1,#,,,,,o1+O,.oOo,o.+.,+Oo. 4+,#9,", "#9,#3,#2,#,#,,,,,O.o.,.+1.,oO.1,O+o. 4.,#9,", "#9,#3,#2,#,#,,,,,o.+O,+O2,.o+o,+.o. 4.,#9,", "#9,#3,#2,#1,#,,,,,O+1O,.O.1,o+.o,Oo1+ 4O,#9,", "#9,#3,#2,#1,#,,,,,O1+o,+.1+,o.+.,o+O+ 4o,#9,", "#9,#3,#2,#1,#,,,,,.o.O,.1o+,O.1+,+1o1 4.,#9,", "#9,#3,#2,#1,#,,,,,.2+,+1.+,o+.o,+1Oo 4*,#9,", "#9,#3,#2,#,#,,,,O4,O2oO,.O+oO,oOoO1,oO.1O 3*,#9,", "#9,#3,#2,#1,#,,,,O4,Oo+O1,O.1O1,O4,.3O 3O,#9,", "#9,#3,#2,#,#,,,,o.+2,.oO.1,oO3,o.O2,o+O2 3*,#9,", "#9,#3,#1,#1,#,,,,o4,o2Oo,oOo1+,oO.o1 =,+o3 3o,#9,", "#9,#3,#2,#1,#,,,,.1oO.,O.1oO,oO.1o,+2.+,o+.1O 3.,#9,", "#9,#3,#2,#1,#,,,,oO.+O,o+O.+,oO.O.,o+1.O,o1.O+ 3O,#9,", "#9,#3,#2,#1,#,,,,Oo1.+,.+.1+,.oO2,Oo3,+o+O1 3o,#9,", "#9,#3,#2,#,#,,,,O1.o.,.O1.+,o1Oo+,+.2o,o+1Oo 3+,#9,", "#9,#3,#2,#1,#,,,,Oo+oO,+Oo+o,o1O1o,.3o,O+O1. 3O,#9,", "#9,#3,#2,#,#,,,,.o2O,Oo.o1,+o+.o,oO.1o,+o.1o 3O,#9,", "#9,#3,#2,#1,#,,,,.Oo+o,o4,+.Oo+,+4,o+O.o 3o,#9,", "#9,#3,#1,#,,,,,O+oOo =,+.oO1,.+1.1 =,O+oO1,oOo.1 3O,#9,", "#9,#3,#2,#1,#,,,,.+.oO,O1o.o,+1o1O,o.+1O,o1O.o 3O,#9,", "#9,#3,#2,#1,#,,,,O.+Oo,.+o.+,oOo.O,.O.O.,+2o1 3O,#9,", "#9,#3,#2,#,#,,,,+1oO1,+1o+1,O1o+.,.1o+O,+Oo+O 3*,#9,", "#9,#3,#1,#1,#,,,,o.oO+,+.1O+,.Oo1.,+2oO =,OoOoO 3.,#9,", "#9,#3,#2,#1,#,,,,O3.,+o.Oo,o.+O+,.o1Oo,O+3 3+,#9,", "#9,#3,#2,#1,#,,,,+.1Oo,O+.1O,.Oo.+,o+1.O,O+1o1 3.,#9,", "#9,#3,#2,#1,#,,,,+O.O.,O.o.o,+1oOo,.1+.O,.Oo.o 3o,#9,", "#9,#3,#2,#,#,,,,Oo2.,o.+1o,oO2+,+o+o+,.+O2 3O,#9,", "#9,#3,#1,#,,,,O1.+2,O1.3=,O1.+2,O1o3=,+o+3,+O+3 2+,#9,", "#9,#3,#1,#1,#,,,+1O1o1,O1+o2,O.4,oO1.2,+2O2=,o.O+2 2+,#9,", "#9,#3=,#1,#,,,,o.1+oO,O3+O=,o1+1o1,+O3+=,o+1O+1,.o2+1 2O,#9,", "#9,#3,#2,#1,#,,,o1O2.,+Oo.O.,oO1.oO,Oo2O.,O.+1Oo,O.2O+ 2O,#9,", "#9,#3,#2,#1,#,,,.oOoO1,+O.O+O,.3Oo,O+o.+1,o1Oo1+,.1+.o+ 2O,#9,", "#9,#3,#2,#1,#,,,,Oo1.+,.+.1+,.oO2,Oo3,+o+o1 3o,#9,", "#9,#3,#2,#1,#,,,,.+.oO,O1o.o,+1o1O,o.+1o,o1O.o 3O,#9,", "#9,#3,#2,#,#,,,,+1oO1,+1o+1,O1o+.,.1o+O,+O.+O 3*,#9,", "#9,#3,#2,#1,#,,,,+O.O.,O.o.o,+1oOo,.1+.o,.Oo.o 3o,#9,", "#9,#3,#1,#,,,,O1.+2,O1.3=,O1.+2,+1o3=,+o+3,+O+3 2+,#9,", "#9,#3=,#1,#,,,,o.1+oO,O3+O=,o1+1O1,+O3+=,o+1O+1,.o2+1 2O,#9,", "#9,, =,=, 1=, 2=,,.1+2,.Oo+O,+O+O1,o.1Oo,.1+1o,Oo2O 3*,#9,", "#9,,, 2=3, 3=, 4=, 2=3,.2oO,.o3,O.+1O,.+.oO,O+o.+,O2+1 3*,#9,", "#9,#3,#2,#1,#,,,.O.1o,o+.1o,.1o+O,o1O+1,O+O1+,+4 3*,#9,", "#9,#3,#1,#,#,,,.o.1O,.2+1,Oo+1O,.o1Oo,+O1o+,+4 3*,#9,", "#9,#3,#2,#1,,,,+1o2,.o2O,+o.+.,+.1O.,O+O2,+4 3*,#9,", "#9,#, 2=,, =,,,.o+.o,O+.1O,.O.1+,O2o+,+1o1O,o+1oO 3*,#9,", "#9,#3,#2,#1,#,,,+.oO+,.2+.,O3.,.1oOo,o+o1O=,o+2O 3*,#9,", "#9,#2,#2,#1,#,=,=,+.1Oo,.2O1,o.o.o,OoO+o,O1+o+,o+3 3*,#9,", "#9,#3,#2,#,#,, =,+1.2,.o1.O=,.+2O,O1+1o =,.o.o1,OoO1o 3*,#9,", "#9,#3 2=,#2,#1 =,#, 3=,,O.O.1O,+.1O1o,.3+1,o+o+2,o1O+1o,oO1oOo 2*,#9," };
        Shooter.aMove = new int[] { 4, 5, 4, 9, 11, 8, 7, 7, 10, 10, 11, 12, 10, 9, 12, 13, 7, 13, 10, 13, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 9, 33, 33, 33, 33, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40 };
    }
}
