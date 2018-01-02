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

public class Eliminator extends Applet implements Runnable
{
    static int MAX_X;
    static int MAX_Y;
    static int MAX_LVL;
    static int MAX_IMG;
    static int MAX_HIST;
    static int X_0;
    static int Y_0;
    Thread game0;
    private Image[] xImg;
    private int xPos;
    private int yPos;
    private int gLevel;
    private int mvPos;
    private int maxMove;
    private byte[][] currCanvas;
    private byte[] xMove;
    private byte firstTime;
    private Button btn1;
    private Button btnN;
    private Button btnP;
    private Button btnU;
    private static String stGameEnd;
    private static String stYouLost;
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
        for (int i = 0; i <= Eliminator.MAX_IMG; ++i) {
            mediaTracker.addImage(this.xImg[i] = this.extractImage(Eliminator.xCoord[i]), i + 1);
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
        panel.add(this.btnP);
        panel.add(this.btnN);
        panel.add(this.btn1);
        panel.add(this.btnU);
        this.add("South", panel);
        this.dat2Canvas(this.gLevel = 0);
        this.firstTime = 1;
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
                int1 = Integer.parseInt(this.getParameter("maxstep" + this.gLevel));
            }
            catch (NumberFormatException ex) {
                int1 = 0;
            }
            parameter = this.getParameter("data" + this.gLevel);
            if (int1 > 0) {
                this.maxMove = int1;
            }
            if (parameter == null || int1 <= 0) {
                parameter = Eliminator.biDat[this.gLevel];
                this.maxMove = Eliminator.aMove[this.gLevel];
            }
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            this.gLevel -= n;
            return false;
        }
        for (int i = 0; i <= Eliminator.MAX_Y; ++i) {
            for (int j = 0; j <= Eliminator.MAX_X; ++j) {
                this.currCanvas[i][j] = 0;
            }
        }
        int n3;
        int n2 = n3 = 0;
        for (int k = 0; k < parameter.length(); ++k) {
            byte b2 = (byte)Eliminator.mCode.indexOf(parameter.charAt(k));
            if (b2 > Eliminator.MAX_IMG) {
                final int n4;
                if ((n4 = b2 - Eliminator.MAX_IMG - 1) > 0) {
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
                    if (++n2 > Eliminator.MAX_Y) {
                        break;
                    }
                }
            }
            else if (n3 <= Eliminator.MAX_X) {
                if (b2 < 0) {
                    b2 = 0;
                }
                this.currCanvas[n2][n3++] = b2;
                b = b2;
            }
        }
        for (int n5 = 0; n5 < Eliminator.MAX_HIST; ++n5) {
            this.xMove[n5] = 0;
        }
        this.mvPos = 0;
        this.txt1.setText(" Level " + this.gLevel + " (" + this.maxMove + " step)");
        if (this.firstTime == 1) {
            this.firstTime = 0;
        }
        return true;
    }
    
    public boolean action(final Event event, final Object o) {
        int n = -1;
        if (event.target == this.btnP) {
            n = 44;
        }
        else if (event.target == this.btnN) {
            n = 46;
        }
        else if (event.target == this.btn1) {
            n = 27;
        }
        else if (event.target == this.btnU) {
            n = 8;
        }
        if (n > 0) {
            this.keyDown(event, n);
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int moveBall = 0;
        switch (n) {
            case 1004: {
                n3 = -1;
                n4 = 1;
                break;
            }
            case 1005: {
                n3 = 1;
                n4 = 3;
                break;
            }
            case 1006: {
                n2 = -1;
                n4 = 5;
                break;
            }
            case 1007: {
                n2 = 1;
                n4 = 7;
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
                this.txt1.setText(" Use ^P,<,>,^N, or Esc key.");
                break;
            }
        }
        if (n2 == 0 && n3 == 0) {
            if (n4 < 0 && this.dat2Canvas(n4 + 100)) {
                this.paint(null);
            }
            return true;
        }
        if (this.mvPos >= this.maxMove) {
            this.txt1.setText(Eliminator.stYouLost);
            return true;
        }
        for (int i = 1; i > 0; i = this.checkBoard()) {
            i = (moveBall = this.moveBall(n2, n3));
            if (i > 0) {}
        }
        if (moveBall > 0) {
            ++this.mvPos;
            this.txt1.setText(" Have " + (this.maxMove - this.mvPos) + " more step.");
        }
        int gameEnd = this.GameEnd();
        if (gameEnd == 1) {
            this.txt1.setText(Eliminator.stGameEnd);
            gameEnd = -98;
        }
        else if (gameEnd == -1 || this.mvPos >= this.maxMove) {
            this.txt1.setText(Eliminator.stYouLost);
            gameEnd = -99;
        }
        if (gameEnd <= -98) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
            if (this.dat2Canvas(gameEnd + 99)) {
                this.paint(null);
            }
        }
        return true;
    }
    
    public int moveBall(final int n, final int n2) {
        int n3 = 0;
        if (n != 0) {
            int y_0 = Eliminator.Y_0;
            for (int i = 0; i <= Eliminator.MAX_Y; ++i) {
                int n4;
                if (n == 1) {
                    n4 = Eliminator.MAX_X - 1;
                }
                else {
                    n4 = 1;
                }
                while (n4 > 0 && n4 < Eliminator.MAX_X) {
                    final byte b;
                    if ((b = this.currCanvas[i][n4]) >= 6) {
                        int n5 = n4;
                        int n6 = Eliminator.X_0 + 20 * n4;
                        byte b2;
                        while (n5 > 0 && n5 < Eliminator.MAX_X && (b2 = this.currCanvas[i][n5 + n]) == 0) {
                            try {
                                Thread.sleep(10L);
                            }
                            catch (InterruptedException ex) {}
                            ++n3;
                            this.currCanvas[i][n5] = 0;
                            this.g.drawImage(this.xImg[0], n6, y_0, this);
                            n5 += n;
                            n6 += 20 * n;
                            this.currCanvas[i][n5] = b;
                            this.g.drawImage(this.xImg[b], n6, y_0, this);
                        }
                    }
                    n4 -= n;
                }
                y_0 += 20;
            }
        }
        if (n2 != 0) {
            int x_0 = Eliminator.X_0;
            for (int j = 0; j <= Eliminator.MAX_X; ++j) {
                int n7;
                if (n2 == 1) {
                    n7 = Eliminator.MAX_Y - 1;
                }
                else {
                    n7 = 1;
                }
                while (n7 > 0 && n7 < Eliminator.MAX_Y) {
                    final byte b3;
                    if ((b3 = this.currCanvas[n7][j]) >= 6) {
                        int n8 = n7;
                        int n9 = Eliminator.Y_0 + 20 * n7;
                        byte b4;
                        while (n8 > 0 && n8 < Eliminator.MAX_Y && (b4 = this.currCanvas[n8 + n2][j]) == 0) {
                            try {
                                Thread.sleep(10L);
                            }
                            catch (InterruptedException ex2) {}
                            ++n3;
                            this.currCanvas[n8][j] = 0;
                            this.g.drawImage(this.xImg[0], x_0, n9, this);
                            n8 += n2;
                            n9 += 20 * n2;
                            this.currCanvas[n8][j] = b3;
                            this.g.drawImage(this.xImg[b3], x_0, n9, this);
                        }
                    }
                    n7 -= n2;
                }
                x_0 += 20;
            }
        }
        return n3;
    }
    
    public int checkBoard() {
        int n = 0;
        final byte b = 100;
        for (int i = 0; i < Eliminator.MAX_Y; ++i) {
            for (int j = 0; j < Eliminator.MAX_X; ++j) {
                final byte b2 = (byte)(this.currCanvas[i][j] % b);
                if (b2 >= 7) {
                    if (b2 == this.currCanvas[i][j + 1] % b) {
                        this.currCanvas[i][j] = (this.currCanvas[i][j + 1] = (byte)(b2 + b));
                        ++n;
                    }
                    if (b2 == this.currCanvas[i + 1][j] % b) {
                        this.currCanvas[i][j] = (this.currCanvas[i + 1][j] = (byte)(b2 + b));
                        ++n;
                    }
                }
            }
        }
        if (n == 0) {
            return 0;
        }
        for (int k = 2; k <= 6; ++k) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            int y_0 = Eliminator.Y_0;
            for (int l = 0; l < Eliminator.MAX_Y; ++l) {
                int x_0 = Eliminator.X_0;
                for (int n2 = 0; n2 < Eliminator.MAX_X; ++n2) {
                    if (this.currCanvas[l][n2] > Eliminator.MAX_IMG) {
                        if (k < 6) {
                            this.g.drawImage(this.xImg[k], x_0, y_0, this);
                        }
                        else {
                            this.currCanvas[l][n2] = 0;
                            this.g.drawImage(this.xImg[0], x_0, y_0, this);
                        }
                    }
                    x_0 += 20;
                }
                y_0 += 20;
            }
        }
        return n;
    }
    
    public int GameEnd() {
        int n = 1;
        final int[] array = new int[Eliminator.MAX_IMG + 1];
        for (int i = 0; i <= Eliminator.MAX_IMG; ++i) {
            array[i] = 0;
        }
        for (int j = 0; j <= Eliminator.MAX_Y; ++j) {
            for (int k = 0; k <= Eliminator.MAX_X; ++k) {
                final int[] array2 = array;
                final byte b = this.currCanvas[j][k];
                ++array2[b];
            }
        }
        for (int l = 7; l <= Eliminator.MAX_IMG; ++l) {
            final int n2 = array[l];
            if (n2 == 1) {
                n = -1;
                l = Eliminator.MAX_IMG + 1;
            }
            else if (n == 1 && n2 > 1) {
                n = 0;
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
        int y_0 = Eliminator.Y_0;
        for (int i = 0; i <= Eliminator.MAX_Y; ++i) {
            int x_0 = Eliminator.X_0;
            for (int j = 0; j <= Eliminator.MAX_X; ++j) {
                int n = this.currCanvas[i][j];
                if (this.firstTime == 1 || n > Eliminator.MAX_IMG) {
                    n = 0;
                }
                graphics.drawImage(this.xImg[n], x_0, y_0, this);
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
        this.g.setColor(new Color(255, 255, 204));
        final String text = "* Eli mi nator *";
        this.g.drawString(text, 15, n);
        this.txt1.setText(text);
        n += 20;
        this.g.drawString("(Java Applet version) 1999 by Riza PN", 15, n);
        n += 20;
        this.g.drawString("The goal is to eliminate all balls. Press", 15, n);
        n += 15;
        this.g.drawString("any arrow key to move the ball. Balls", 15, n);
        n += 15;
        this.g.drawString("will be eliminated if there are more", 15, n);
        n += 15;
        this.g.drawString("than 1 ball in a horizontal or vertical", 15, n);
        n += 15;
        this.g.drawString("direction. The game will be finished if", 15, n);
        n += 15;
        this.g.drawString("all balls are eliminated (YOU WIN), or if", 15, n);
        n += 15;
        this.g.drawString("there is no more possibility to eliminate", 15, n);
        n += 15;
        this.g.drawString("them (YOU LOST).", 15, n);
        n += 30;
        this.g.drawString("The idea is picked from my", 15, n);
        n += 15;
        this.g.drawString("game pocket (they call it IQ-Puzzle)", 15, n);
    }
    
    public Eliminator() {
        this.xImg = new Image[Eliminator.MAX_IMG + 1];
        this.currCanvas = new byte[Eliminator.MAX_Y + 1][Eliminator.MAX_X + 1];
        this.xMove = new byte[Eliminator.MAX_HIST];
        this.btn1 = new Button("X");
        this.btnN = new Button(">");
        this.btnP = new Button("<");
        this.btnU = new Button("U");
    }
    
    static {
        Eliminator.MAX_X = 9;
        Eliminator.MAX_Y = 10;
        Eliminator.MAX_LVL = 100;
        Eliminator.MAX_IMG = 11;
        Eliminator.MAX_HIST = 100;
        Eliminator.X_0 = 5;
        Eliminator.Y_0 = 5;
        Eliminator.stGameEnd = " [GAME END]";
        Eliminator.stYouLost = " [YOU LOSE]";
        Eliminator.mCode = " #    *.oO@+,123456789abcdefghij";
        Eliminator.xCoord = new int[][] { { 0, 0, 20, 20 }, { 20, 0, 20, 20 }, { 40, 0, 20, 20 }, { 60, 0, 20, 20 }, { 80, 0, 20, 20 }, { 100, 0, 20, 20 }, { 100, 20, 20, 20 }, { 0, 20, 20, 20 }, { 20, 20, 20, 20 }, { 40, 20, 20, 20 }, { 60, 20, 20, 20 }, { 80, 20, 20, 20 } };
        Eliminator.biDat = new String[] { ", #7, #o.o.o.#, # 5#, # # # #1, # # 3#, # 4#1, #6,", ",, 1#5, 1#.* 1#, 1# 3#, 1#. 2#, 1# 1.#1, 1#5,", ", 2#3, 1#1 .#1, 1# 3#, 1#. o #, 1# 1# #, 1# 1. #, 1#1o #1, 2#3,", ",, 1#5, 1# 1. #, 1#1 1#1, 1# 2.#, 1#. 1#1, 1#4,", ", 1#2, #1 #2, # . 1#1, #* . 1#1, # 1* . #, # # 1#2, #5,", ", #7, #o 3o#, # # 1# #, # 1. 2#, #1. . #1, # 1o . #, #7,", ", 1#5, #1 . *#1, # 4.#, # 5#, #. 4#, #1 3#1, 1#1* #1, 2#3,", ", #6, # 3.#, #. 2#1, # 1* 1#, # 3#1, #2.#1, 2#2,", ",,#9,# 1. 2* #,#1 1*. 1#1, # *. * #, #. 3.#, #7,", ", 2#4, 1#1 . #1, #1 4#, #. 3.#, # 2#1*#, #7,", ", 4#2, #4.#1, #. 4#, #1 2* #, 1#1 # #1, 2# #2, 2# 1.#, 2#4,", ", #6, # 4#, #1 .* #, #. 3#, #* 2.#, # 2* #, #6,", " #7, # 1. 2#,#1 1*# 1#,# 2.# 1#,#1 #1 2#, # 1* 2#, # 1. 2#, # 5#, # 5#, #7,", ", #6, # .# o#1, #1 3o#, 1# 3#1, 1#* 2.#, 1#.o 1#1, 1#5,", ", 1#5, #1. 2#, # 3.#, #1 * #1, # 4#, #1 2.#, 1#5,", ", 2#2, 1#1 #3, #1 4#, # 5#, # .o#.o#, #1o#2.#, 1#2 #2,", ", 3#2, #3O#1, # o. 1#, # # o.#2,#1 4O*#,# 5o#1,#1 O# 1#1, #6,", " 4#2, 2#2 #1, 1#1. 2#, 1# 2o#1, #2 . 1#, # 1o .#1, #1 1o#1, 1#4,", ", 1#5, #1 1o #1, #o 3.#, #. 2* #, #1 . 2#, 1#3 #1, 4#2,", ", #4, #. 1#1, # 3#1, # o o.#, #2. 1#, 2# 2#, 2#4,", ", 2#4, 1#1 O #, #1 3#1,#1.O 3#,# 2o 1#1,# 1#2.#,# O o 1#,#7,", " #2,#1 #4,#. 2O #,# 2#1.#1,# 1Oo# 1#,# o# 2#1,# 2. o #,#O 1#1 #1,#7,", " 1#2, 1# #2, 1# 1o#1, 1#*. o#1, 1# #O o#, #1 2# #, # . 2O#, #1O 2#1, 1#5,", "#8,# Oo.oO #,# 2o 2#,# 2O 2#,# 2o 2#,# o . O #,#1 1# 1#1, #6,", " 1#4, #1 # #1, # .# 1#1, #1 #. 1#, # 5#, #1 * 1.#, 1#1 2#1, 2#1 #1, 3#2,", " 1#5, #1 1# #1, #.*o.*.#1, # 6#, # #1 # 1#,#1 2#1 1#,# o 4#1,#1 4#1, #6,", " 1#2, 1# #, 1# #3, #1 3#1, # 1# . #, # . # 1#, #1 #. #1, 1#1 # #, 2#2 #, 4#2,", ", #5, # # 1#1, # 1. 1#,#2. * #1,# 3#. #,#1 2#3, #2 #, 2#2,", " #3,#1 1#3,# 2. 1#,#1 .#.#1, # 4#, # #o#o#, #1 1o #, 1#1 2#, 2#1 #1, 3#2,", ", #5, # 1o.#1, # . #o#1, # * 3#, #o 4#, #1 2.#1, 1#1 1#1, 2#3,", ", 1#5, 1# # .#1, 1# 1o 1#, #2. * #, #. 3#1, #1o 2#, 1# 3#, 1#5,", ", 4#2, 3#1*#, 2#1. #, 1#1. 1#1, #1 4#, # 4.#, #o 1* o#, #7,", "#7,# .oO* #,#1 4#1,#O . 3#,#o# 3#1,# #1 2#1,# 1o 3#,#. #1O. #,#5 1#, 4#3,", ", 1#5, 1#. 2#1, #1 # O #, # 1Oo# #, # 2Oo #, #1 o# 1#, 1# 2.#1, 1#5,", ", #5, # 1o #1, # O# o#, #o 1O.#1, # 5#, #1. 2O#, 1#6,", " #5, # O .#1, #1 2O#1, 1# o#. #, #1 3o#, #O #O 1#, # o 3#, #1 #4, 1#2,", " 1#4, #1O 1#, # o 1#1, #1. 2#, # O.o*#, #1 2.#1, 1# 2o #, 1#1 3#, 2#5,", " 1#6, 1# 1. o#, #1 o# 1#, # 2# 1#, #1 3#1, 1#. 2#1, 1# 2o #, 1#1 .# #, 2#5,", ", 1#4, #1 1.#1, # . # #1, #1 . 1o#, #2o *.#, # . * o#, #7,", " #7,#1 o 1O #,# 4o#1,#1 5#,# 1O 1.#1,#1. 2*#, #1o 2#, # . 1O#, #6," };
        Eliminator.aMove = new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10 };
    }
}
