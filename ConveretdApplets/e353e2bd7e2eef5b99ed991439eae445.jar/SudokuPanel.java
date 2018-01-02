import java.awt.event.KeyEvent;
import java.awt.Event;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import com.pzzl.utils.Tag;
import java.io.PrintStream;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import com.pzzl.utils.ImagePanel;
import com.pzzl.utils.ImageButton;
import java.awt.Color;
import com.pzzl.utils.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SudokuPanel extends Panel
{
    Color \u02c0;
    Color \u02c1;
    Color \u02d0;
    Color \u02d1;
    Color \u02e0;
    String \u02e1;
    String \u02e2;
    boolean \u012b;
    String \u02e3;
    boolean displayResult;
    String \u02e4;
    int \u0388;
    int \u0389;
    int \u038a;
    int \u038e;
    int \u038f;
    int \u0390;
    Color \u0391;
    Color \u0392;
    String \u0393;
    ImageButton \u0394;
    ImagePanel \u018c;
    int \u018d;
    int \u018e;
    int \u018f;
    int \u0190;
    Color \u0191;
    Color \u0192;
    String \u0193;
    boolean checkMoves;
    boolean revealMove;
    int \u0395;
    int \u0396;
    int \u0397;
    boolean showHint;
    int \u0398;
    int \u0399;
    StringBuffer \u039a;
    int \u039b;
    int \u039c;
    Move[] \u039d;
    Move[] \u039e;
    public Grid solutionGrid;
    public Grid startGrid;
    public Grid currentGrid;
    public Grid maskGrid;
    int \u039f;
    int \u03a0;
    int \u03a1;
    int \u03a3;
    int \u03a4;
    int \u03a5;
    boolean hideString;
    public boolean showCandidates;
    Image \u00cd;
    String \u00cc;
    int \u03a6;
    int \u03a7;
    int \u03a8;
    int \u03a9;
    private Image \u03aa;
    private Image \u03ab;
    private Graphics \u03ac;
    private Graphics \u03ad;
    Image \u0158;
    Image \u015b;
    Image \u0159;
    Image \u015a;
    Image \u015c;
    Image \u015d;
    Image \u015e;
    int \u03ae;
    int \u03af;
    boolean[][] \u03b0;
    boolean[][] \u00c2;
    int[][] \u03b1;
    int[][] \u03b2;
    int[] \u03b3;
    Image[] \u03b4;
    Image[] \u03b5;
    Image[] \u03b6;
    Image[] \u03b7;
    Image[] \u03b8;
    Image[] \u03b9;
    Rectangle \u0294;
    boolean \u03ba;
    boolean \u03bb;
    ImageButton[] \u03bc;
    int \u03bd;
    int \u03be;
    boolean \u03bf;
    int \u03c0;
    int \u03c1;
    final int \u03c2 = 0;
    final int \u03c3 = 1;
    final int \u03c4 = 2;
    final int \u03c5 = 3;
    final int \u03c6 = 4;
    final int \u03c7 = 5;
    final int \u03c8 = 50;
    final int \u03c9 = 25;
    final int \u03ca = 25;
    final int \u03cb = 25;
    final int \u03cc = 10;
    final int \u03cd = 12;
    final int \u03ce = 5;
    int \u03d0;
    int \u03d1;
    int \u03d2;
    String \u03d3;
    
    public SudokuPanel(final Color \u02e0, final Color \u02c1, final Color \u02d0, final Color \u02d1, final Color \u02c0, final Image \u0159, final Image \u015b, final Image \u015b2, final Image \u01592, final Image \u015d, final Image \u015d2, final Image \u015f, final String \u02e1, final String \u02e2) {
        this.\u012b = false;
        this.displayResult = false;
        this.\u02e4 = "";
        this.\u0388 = 1;
        this.\u0389 = 1;
        this.\u038a = 2;
        this.\u038e = 2;
        this.\u038f = 50;
        this.\u0390 = 20;
        this.\u0391 = this.\u02e0;
        this.\u0392 = Color.white;
        this.\u0393 = "OK";
        this.\u018d = 2;
        this.\u018e = 2;
        this.\u018f = 200;
        this.\u0190 = 100;
        this.\u0191 = this.\u02e0;
        this.\u0192 = Color.white;
        this.\u0193 = "Message Panel";
        this.checkMoves = false;
        this.revealMove = false;
        this.\u039d = new Move[144];
        this.\u039e = new Move[144];
        this.hideString = false;
        this.showCandidates = false;
        this.\u03ba = false;
        this.\u03bb = false;
        this.\u02e1 = \u02e1;
        this.\u02e2 = \u02e2;
        this.\u02e0 = \u02e0;
        this.\u02c1 = \u02c1;
        this.\u02d0 = \u02d0;
        this.\u02d1 = \u02d1;
        this.\u02c0 = \u02c0;
        this.\u0158 = \u0159;
        this.\u015b = \u015b2;
        this.\u0159 = \u01592;
        this.\u015a = \u015b;
        this.\u015c = \u015d;
        this.\u015d = \u015d2;
        this.\u015e = \u015f;
        this.setLayout(null);
        this.setBackground(Color.black);
        this.\u03d2 = this.\u02c0(this.\u03d3);
        this.\u03b4 = new Image[25];
        this.\u03b5 = new Image[25];
        this.\u03b6 = new Image[25];
        this.\u03b7 = new Image[25];
        this.\u03b3 = new int[25];
        this.\u03b1 = new int[25][25];
        this.\u03b0 = new boolean[25][25];
        this.\u00c2 = new boolean[25][25];
        this.\u03b2 = new int[25][25];
        this.\u03b8 = new Image[25];
        this.\u03b9 = new Image[25];
        this.\u03bc = new ImageButton[25];
        this.\u018c = new ImagePanel();
        this.\u018f = 200;
        this.\u0190 = 100;
        this.\u018d = this.getSize().width / 2 - this.\u018f / 2;
        this.\u018e = this.getSize().width / 2 - this.\u018f / 2;
        this.\u018c.setBounds(this.\u018d, this.\u018e, this.\u018f, this.\u0190);
        this.\u018c.setBackground(this.\u0191);
        this.\u018c.setForeground(this.\u0192);
        this.\u018c.setString(this.\u0193);
        this.\u018c.addMouseListener(new SudokuPanel$1(this));
        this.add(this.\u018c);
        this.\u0394 = new ImageButton();
        this.\u038f = 50;
        this.\u0390 = 20;
        this.\u038a = this.\u018f / 2 - this.\u038f / 2;
        this.\u038e = this.\u0190 / 2 - this.\u038f / 2;
        this.\u0394.setBounds(this.\u038a, this.\u038e, this.\u038f, this.\u0390);
        this.\u0394.setBackground(Color.white);
        this.\u0394.setForeground(this.\u0392);
        this.\u0394.setString(this.\u0393);
        this.\u0394.addMouseListener(new SudokuPanel$2(this));
        this.\u018c.add(this.\u0394);
        this.\u0394.show();
        for (int i = 0; i < 144; ++i) {
            this.\u039d[i] = new Move();
        }
        for (int j = 0; j < 144; ++j) {
            this.\u039e[j] = new Move();
        }
        this.\u03a6 = \u0159.getWidth(this);
        this.\u03a7 = \u0159.getHeight(this);
        this.\u03ae = 10;
        this.createDragItems();
        this.createPencilItems();
        this.createstartItems();
        this.\u03bc = new ImageButton[this.\u03ae];
        for (int k = 0; k < this.\u03ae; ++k) {
            final int n = 3;
            final int n2 = 1;
            final int n3 = 32;
            final int n4 = 32;
            final int n5 = 320;
            final int n6 = n + k * n4 + k * n2 + k / 3 * n - k / 3 * n2;
            (this.\u03bc[k] = new ImageButton(this.\u03b7[k], this.\u03b8[k], this.\u03b9[k])).setBounds(n5, n6, n3, n4);
            this.\u03bc[k].setBackground(null);
            this.\u03bc[k].setForeground(Color.black);
            this.\u03bc[k].setString("[" + (k + 1) + "]");
            this.\u03bc[k].hideString();
            final int n7 = k;
            final int n8 = n5;
            final int n9 = n6;
            this.\u03bc[k].addMouseMotionListener(new SudokuPanel$3(n7, this, n8, n9));
            this.\u03bc[k].addMouseListener(new SudokuPanel$4(n7, this, this, n8, n9));
            this.add(this.\u03bc[k]);
        }
        this.reset();
        this.repaint();
    }
    
    public void showGrid() {
        this.showGrid(System.out);
    }
    
    public void setPencilMode(final boolean \u012b) {
        this.\u012b = \u012b;
        this.requestFocus();
    }
    
    public void showGrid(final PrintStream printStream) {
        if (this.solutionGrid.rows() == 9) {
            this.\u039f = 3;
            this.\u03a0 = 6;
            this.\u03a1 = 999;
            this.\u03a3 = 2;
            this.\u03a4 = 5;
            this.\u03a5 = 999;
        }
        if (this.solutionGrid.rows() == 12) {
            this.\u039f = 3;
            this.\u03a0 = 7;
            this.\u03a1 = 11;
            this.\u03a3 = 2;
            this.\u03a4 = 5;
            this.\u03a5 = 8;
        }
        printStream.println();
        printStream.print("SOLUTION \t \t");
        if (this.solutionGrid.rows() == 12) {
            printStream.print("\t");
        }
        printStream.print("START    \t \t");
        if (this.solutionGrid.rows() == 12) {
            printStream.print("\t");
        }
        printStream.println("CURRENT");
        for (int i = 0; i < this.solutionGrid.rows(); ++i) {
            for (int j = 0; j < this.solutionGrid.columns(); ++j) {
                printStream.print((char)(this.solutionGrid.cell[i][j].get() + 48));
                if (j == this.\u039f || j == this.\u03a0 || j == this.\u03a1) {
                    printStream.print(" ");
                }
            }
            printStream.print("\t \t");
            for (int k = 0; k < this.startGrid.columns(); ++k) {
                printStream.print((char)(this.startGrid.cell[i][k].get() + 48));
                if (k == this.\u039f || k == this.\u03a0 || k == this.\u03a1) {
                    printStream.print(" ");
                }
            }
            printStream.print("\t \t");
            for (int l = 0; l < this.currentGrid.columns(); ++l) {
                printStream.print((char)(this.currentGrid.cell[i][l].get() + 48));
                if (l == this.\u039f || l == this.\u03a0 || l == this.\u03a1) {
                    printStream.print(" ");
                }
            }
            if (i == this.\u03a3 || i == this.\u03a4 || i == this.\u03a5) {
                printStream.println();
            }
            printStream.println();
        }
        printStream.println();
    }
    
    public void setShowCandidates(final boolean showCandidates) {
        this.showCandidates = showCandidates;
        this.buildOffscreenBufferImage();
        this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
        this.repaint();
    }
    
    private int \u02c0(final String s) {
        if (s != null) {
            try {
                return Integer.parseInt(s);
            }
            catch (Exception ex) {
                return 0;
            }
        }
        return 0;
    }
    
    public boolean setPuzzle(String tagValue) {
        this.\u03c1 = 1;
        tagValue = this.getTagValue(tagValue, "<DATA>");
        if (tagValue.length() == 0) {
            return false;
        }
        final String tagValue2 = this.getTagValue(tagValue, "<NAME>");
        if (tagValue2.length() == 0) {
            return false;
        }
        this.\u02e3 = tagValue2;
        if (this.getTagValue(tagValue, "<TITLE>").length() == 0) {
            return false;
        }
        final String tagValue3 = this.getTagValue(tagValue, "<STARTGRID>");
        if (tagValue3.length() == 0) {
            return false;
        }
        final String tagValue4 = this.getTagValue(tagValue, "<SOLUTIONGRID>");
        if (tagValue4.length() == 0) {
            return false;
        }
        final String tagValue5 = this.getTagValue(tagValue, "<SOLUTIONPATH>");
        if (tagValue5.length() == 0) {
            return false;
        }
        int n = tagValue5.indexOf("[");
        int n2 = tagValue5.indexOf("]");
        while (n > 0 && n2 > 0) {
            final String substring = tagValue5.substring(n + 1, n2);
            final String substring2 = substring.substring(substring.indexOf("(") + 1, substring.indexOf(","));
            final String substring3 = substring.substring(substring.indexOf(",") + 1, substring.indexOf(")"));
            final String substring4 = substring.substring(substring.indexOf(":") + 1, substring.indexOf(" "));
            n = tagValue5.indexOf("[", n2 + 1);
            n2 = tagValue5.indexOf("]", n2 + 1);
            try {
                final int int1 = Integer.parseInt(substring2);
                final int int2 = Integer.parseInt(substring3);
                final int int3 = Integer.parseInt(substring4);
                ++this.\u039c;
                this.\u039e[this.\u039c].set(0, int1, int2, int3);
            }
            catch (Exception ex) {}
        }
        final Tag tag = new Tag("ROWS");
        final Tag tag2 = new Tag("COLUMNS");
        this.\u03bd = tag.getInteger(tagValue3);
        this.\u03be = tag2.getInteger(tagValue3);
        this.startGrid = new Grid(this.\u03bd, this.\u03be, this.\u03be);
        this.solutionGrid = new Grid(this.\u03bd, this.\u03be, this.\u03be);
        this.currentGrid = new Grid(this.\u03bd, this.\u03be, this.\u03be);
        this.maskGrid = new Grid(this.\u03bd, this.\u03be, this.\u03be);
        final String removeTagValue = this.removeTagValue(this.removeTagValue(tagValue3, "<ROWS>"), "<COLUMNS>");
        final String substring5 = removeTagValue.substring(6, removeTagValue.length() - 7);
        final int n3 = (substring5.indexOf(124) - 1) / 2;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        final String trim = substring5.trim();
        int n8 = 0;
        boolean b = false;
        while (!b) {
            int n9 = trim.indexOf(" ", n8);
            if (n9 < 0) {
                n9 = trim.length();
                b = true;
            }
            final String substring6 = trim.substring(n8, n9);
            if (n4 == 0 && substring6.startsWith("-")) {
                n4 = n5;
            }
            if (!substring6.equals("") && !substring6.equals("|") && !substring6.startsWith("-")) {
                if (substring6.equals(".")) {
                    this.\u03b2[n7][n6] = 0;
                    this.startGrid.cell[n7][n6].set(0);
                }
                else {
                    final int int4 = Integer.parseInt(substring6);
                    this.\u03b2[n7][n6] = int4;
                    this.startGrid.cell[n7][n6].set(int4);
                }
                if (++n7 == this.\u03bd) {
                    n7 = 0;
                    ++n6;
                    if (n4 == 0) {
                        ++n5;
                    }
                }
            }
            n8 = n9 + 1;
        }
        final String removeTagValue2 = this.removeTagValue(this.removeTagValue(tagValue4, "<ROWS>"), "<COLUMNS>");
        final String substring7 = removeTagValue2.substring(6, removeTagValue2.length() - 7);
        int n10 = 0;
        int n11 = 0;
        final String trim2 = substring7.trim();
        int n12 = 0;
        boolean b2 = false;
        while (!b2) {
            int n13 = trim2.indexOf(" ", n12);
            if (n13 < 0) {
                n13 = trim2.length();
                b2 = true;
            }
            final String substring8 = trim2.substring(n12, n13);
            if (!substring8.equals("") && !substring8.equals("|") && !substring8.startsWith("-")) {
                if (substring8.equals(".")) {
                    this.solutionGrid.cell[n10][n11].set(0);
                }
                else {
                    this.solutionGrid.cell[n10][n11].set(Integer.parseInt(substring8));
                }
                if (++n11 == this.\u03bd) {
                    n11 = 0;
                    ++n10;
                }
            }
            n12 = n13 + 1;
        }
        for (int i = 0; i < this.\u03be; ++i) {
            for (int j = 0; j < this.\u03bd; ++j) {
                this.maskGrid.cell[i][j].set(i / n3 + j / n4 * n4);
            }
        }
        this.currentGrid.copy(this.startGrid);
        this.\u03a8 = this.\u03a6 / this.\u03bd;
        this.\u03a9 = this.\u03a7 / this.\u03be;
        this.currentGrid.setMaskAndSolution(this.maskGrid, this.solutionGrid);
        this.\u039a = new StringBuffer();
        this.currentGrid.scan(false);
        this.reset();
        return true;
    }
    
    public int getTagValueInt(final String s, final String s2, final String s3) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, String.valueOf(s2) + s3);
        String nextToken;
        try {
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            else {
                nextToken = "0";
            }
        }
        catch (NoSuchElementException ex) {
            nextToken = "0";
        }
        int int1;
        try {
            int1 = Integer.parseInt(nextToken);
        }
        catch (Exception ex2) {
            int1 = 0;
        }
        return int1;
    }
    
    public void reset() {
        this.\u03c0 = 0;
        this.\u03af = 0;
        for (int i = 0; i < this.\u03bd; ++i) {
            this.\u03b3[i] = 0;
        }
        for (int j = 0; j < this.\u03bd; ++j) {
            for (int k = 0; k < this.\u03be; ++k) {
                this.\u03b1[j][k] = this.\u03b2[j][k];
                this.currentGrid.cell[j][k].set(this.\u03b2[j][k]);
                this.\u03b0[j][k] = false;
            }
        }
        if (this.\u03aa != null) {
            this.buildOffscreenBufferImage();
            this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
            this.repaint();
        }
    }
    
    public String getTagValue(final String s, final String s2) {
        final String lowerCase = s.toLowerCase();
        final String lowerCase2 = s2.toLowerCase();
        final int n = lowerCase.indexOf(lowerCase2, 0) + lowerCase2.length();
        final int index = lowerCase.indexOf("</" + lowerCase2.substring(1, lowerCase2.length()), 0);
        if (n >= 0 & index > 0) {
            return s.substring(n, index);
        }
        return "";
    }
    
    public String removeTagValue(final String s, final String s2) {
        final String lowerCase = s.toLowerCase();
        final String lowerCase2 = s2.toLowerCase();
        final int index = lowerCase.indexOf(lowerCase2, 0);
        final int index2 = lowerCase.indexOf("</" + lowerCase2.substring(1, lowerCase2.length()), 0);
        if (index >= 0 & index2 > 0) {
            return s.substring(0, index).concat(s.substring(index2 + s2.length() + 1, s.length()));
        }
        return s;
    }
    
    public void createDragItemsold() {
        final int n = 0;
        final int n2 = 32;
        final int n3 = 32;
        for (int i = 0; i < this.\u03ae; ++i) {
            this.\u03b4[i] = this.getToolkit().createImage(new FilteredImageSource(this.\u015b.getSource(), new CropImageFilter(n + i * n2, 0, n3, n3)));
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.\u03b4[i], 0);
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        final int n4 = 33;
        final int n5 = 34;
        final int n6 = 34;
        for (int j = 0; j < this.\u03ae; ++j) {
            this.\u03b7[j] = this.getToolkit().createImage(new FilteredImageSource(this.\u015c.getSource(), new CropImageFilter(0, j * n4, n5, n6)));
            try {
                final MediaTracker mediaTracker2 = new MediaTracker(this);
                mediaTracker2.addImage(this.\u03b7[j], 0);
                mediaTracker2.waitForID(0);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        for (int k = 0; k < this.\u03ae; ++k) {
            this.\u03b8[k] = this.getToolkit().createImage(new FilteredImageSource(this.\u015d.getSource(), new CropImageFilter(0, k * n4, n5, n6)));
            try {
                final MediaTracker mediaTracker3 = new MediaTracker(this);
                mediaTracker3.addImage(this.\u03b8[k], 0);
                mediaTracker3.waitForID(0);
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
        for (int l = 0; l < this.\u03ae; ++l) {
            this.\u03b9[l] = this.getToolkit().createImage(new FilteredImageSource(this.\u015e.getSource(), new CropImageFilter(0, l * n4, n5, n6)));
            try {
                final MediaTracker mediaTracker4 = new MediaTracker(this);
                mediaTracker4.addImage(this.\u03b9[l], 0);
                mediaTracker4.waitForID(0);
            }
            catch (Exception ex4) {
                ex4.printStackTrace();
            }
        }
    }
    
    public void createDragItems() {
        final int n = 0;
        final int n2 = 32;
        final int n3 = 32;
        for (int i = 0; i < this.\u03ae; ++i) {
            this.\u03b4[i] = this.getToolkit().createImage(new FilteredImageSource(this.\u015b.getSource(), new CropImageFilter(n + i * n2, 0, n3, n3)));
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.\u03b4[i], 0);
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        final int n4 = 32;
        final int n5 = 32;
        final int n6 = 32;
        for (int j = 0; j < this.\u03ae; ++j) {
            this.\u03b7[j] = this.getToolkit().createImage(new FilteredImageSource(this.\u015c.getSource(), new CropImageFilter(0, j * n4, n5, n6)));
            try {
                final MediaTracker mediaTracker2 = new MediaTracker(this);
                mediaTracker2.addImage(this.\u03b7[j], 0);
                mediaTracker2.waitForID(0);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        for (int k = 0; k < this.\u03ae; ++k) {
            this.\u03b8[k] = this.getToolkit().createImage(new FilteredImageSource(this.\u015d.getSource(), new CropImageFilter(0, k * n4, n5, n6)));
            try {
                final MediaTracker mediaTracker3 = new MediaTracker(this);
                mediaTracker3.addImage(this.\u03b8[k], 0);
                mediaTracker3.waitForID(0);
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
        for (int l = 0; l < this.\u03ae; ++l) {
            this.\u03b9[l] = this.getToolkit().createImage(new FilteredImageSource(this.\u015e.getSource(), new CropImageFilter(0, l * n4, n5, n6)));
            try {
                final MediaTracker mediaTracker4 = new MediaTracker(this);
                mediaTracker4.addImage(this.\u03b9[l], 0);
                mediaTracker4.waitForID(0);
            }
            catch (Exception ex4) {
                ex4.printStackTrace();
            }
        }
    }
    
    public void createPencilItems() {
        final int n = 0;
        final int n2 = 32;
        final int n3 = 32;
        for (int i = 0; i < this.\u03ae; ++i) {
            this.\u03b5[i] = this.getToolkit().createImage(new FilteredImageSource(this.\u0159.getSource(), new CropImageFilter(n + i * n2, 0, n3, n3)));
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.\u03b5[i], 0);
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void createstartItems() {
        final int n = 0;
        final int n2 = 32;
        final int n3 = 32;
        for (int i = 0; i < this.\u03ae; ++i) {
            this.\u03b6[i] = this.getToolkit().createImage(new FilteredImageSource(this.\u015a.getSource(), new CropImageFilter(n + i * n2, 0, n3, n3)));
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.\u03b6[i], 0);
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void buildOffscreenBufferImage() {
        switch (this.\u03c1) {
            case 1: {
                this.drawBoardSituation();
            }
            case 3: {
                this.drawBoardSituation();
            }
            case 2: {
                this.displayHelp();
            }
            default: {}
        }
    }
    
    public void drawBoardSituation() {
        this.\u03ad.setColor(this.\u02e0);
        this.\u03ad.fillRect(this.\u0158.getWidth(this), 0, this.size().width - this.\u0158.getWidth(this), this.\u0158.getHeight(this));
        this.\u03ad.drawImage(this.\u0158, this.\u03d0, this.\u03d1, this.\u0158.getWidth(this), this.\u0158.getHeight(this), this);
        for (int i = 0; i < this.\u03bd; ++i) {
            this.\u03b3[i] = 0;
        }
        for (int j = 0; j < this.\u03bd; ++j) {
            for (int k = 0; k < this.\u03be; ++k) {
                Color color = Color.white;
                Color color2 = Color.black;
                if (this.\u03b1[j][k] != 0) {
                    final int[] \u03b3 = this.\u03b3;
                    final int n = this.\u03b1[j][k] - 1;
                    ++\u03b3[n];
                    if (this.\u03c1 == 1 || this.\u03c1 == 3) {
                        Image image = this.\u03b4[this.\u03b1[j][k] - 1];
                        if (this.\u03b1[j][k] == this.\u03b2[j][k]) {
                            color = this.\u02c1;
                            color2 = this.\u02d0;
                            image = this.\u03b6[this.\u03b1[j][k] - 1];
                        }
                        if (j == this.\u0398 && k == this.\u0399 && this.showHint) {
                            color = this.\u02c1(j, k);
                        }
                        if (j == this.\u0395 && k == this.\u0396 && this.revealMove) {
                            color = new Color(204, 255, 255);
                            color2 = Color.black;
                        }
                        if (this.checkMoves && this.\u03b1[j][k] != this.solutionGrid.cell[k][j].get()) {
                            color = this.\u02d1;
                            color2 = Color.black;
                        }
                        if (this.\u03b0[j][k]) {
                            image = this.\u03b5[this.\u03b1[j][k] - 1];
                        }
                        this.drawBox(j, k, color2, color, image);
                    }
                }
                else {
                    final Color white = Color.white;
                    Color color3 = Color.gray;
                    if (j == this.\u0398 && k == this.\u0399 && this.showHint) {
                        this.\u02c1(j, k);
                        color3 = Color.black;
                    }
                    if (this.showCandidates) {
                        this.drawCandidates(j, k, color3);
                    }
                    if (this.\u00c2[j][k]) {
                        this.drawPossibleAccordingToUser(j, k, Color.red);
                    }
                }
            }
        }
        this.drawDragItems(this.\u03b3);
        this.drawCursor(this.\u0388, this.\u0389, this.\u02c0, this.\u02c0);
        if (this.displayResult) {
            this.displayResult(this.\u02e4);
        }
    }
    
    private Color \u02c1(final int n, final int n2) {
        if (this.\u03b1[n][n2] != 0) {
            return new Color(255, 204, 204);
        }
        return new Color(255, 255, 204);
    }
    
    public void drawDragItems(final int[] array) {
        final int n = 3;
        final int n2 = 1;
        final int n3 = 32;
        final int n4 = 32;
        final int n5 = 320;
        for (int i = 0; i < this.\u03bd; ++i) {
            final int n6 = n + i * n4 + i * n2 + i / 3 * n - i / 3 * n2;
            if (this.\u03bf && i == this.\u03c0) {
                final int n7 = i;
                ++array[n7];
            }
            if (array[i] < this.\u03bd) {
                this.\u03bc[i].show();
                this.\u03ad.drawImage(this.\u03b7[i], n5, n6, n3, n4, this);
            }
            else {
                this.\u03bc[i].hide();
                this.\u03ad.setColor(this.\u02e0);
                this.\u03ad.fillRect(n5, n6, n3, n4);
            }
        }
    }
    
    public void drawCandidates(final int n, final int n2, final Color color) {
        final int n3 = 3;
        final int n4 = 1;
        final int n5 = 32;
        final int n6 = 32;
        final int n7 = n3 + n * n5 + n * n4 + n / 3 * n3 - n / 3 * n4;
        final int n8 = n3 + (n2 + 1) * n6 + (n2 + 1) * n4 + n2 / 3 * n3 - n2 / 3 * n4;
        this.\u03ad.setColor(color);
        for (int i = 0; i < this.\u03bd; ++i) {
            if (this.currentGrid.cell[n][n2].isPossible(i + 1)) {
                this.\u03ad.drawString(String.valueOf(i + 1), n7 + i % 3 * 8 + 4, n8 + i / 3 * 10 - 2 - 20);
            }
        }
    }
    
    public void drawPossibleAccordingToUser(final int n, final int n2, final Color color) {
        final int n3 = 3;
        final int n4 = 1;
        final int n5 = 32;
        final int n6 = 32;
        final int n7 = n3 + n * n5 + n * n4 + n / 3 * n3 - n / 3 * n4;
        final int n8 = n3 + (n2 + 1) * n6 + (n2 + 1) * n4 + n2 / 3 * n3 - n2 / 3 * n4;
        this.\u03ad.setColor(color);
        for (int i = 0; i < this.\u03bd; ++i) {
            if (this.currentGrid.cell[n][n2].isPossibleAccordingToUser(i + 1)) {
                this.\u03ad.drawString(String.valueOf(i + 1), n7 + i % 3 * 8 + 4, n8 + i / 3 * 10 - 2 - 20);
            }
        }
    }
    
    public void drawCursor(final int n, final int n2, final Color color, final Color color2) {
        final int n3 = 3;
        final int n4 = 1;
        final int n5 = 32;
        final int n6 = 32;
        final int n7 = n3 + n * n5 + n * n4 + n / 3 * n3 - n / 3 * n4;
        final int n8 = n3 + n2 * n6 + n2 * n4 + n2 / 3 * n3 - n2 / 3 * n4;
        this.\u03ad.setColor(color);
        this.\u03ad.drawRect(n7, n8, n5 - 1, n6 - 1);
    }
    
    public void drawBox(final int n, final int n2, final Color color, final Color color2, final Image image) {
        final int n3 = 3;
        final int n4 = 1;
        final int n5 = 32;
        final int n6 = 32;
        final int n7 = n3 + n * n5 + n * n4 + n / 3 * n3 - n / 3 * n4;
        final int n8 = n3 + n2 * n6 + n2 * n4 + n2 / 3 * n3 - n2 / 3 * n4;
        this.\u03ad.setColor(color2);
        this.\u03ad.fillRect(n7, n8, n5, n6);
        if (image != null) {
            this.\u03ad.drawImage(image, n7, n8, n5, n6, this);
        }
    }
    
    public void displayHelp() {
        this.drawBoardSituation();
        this.\u03ad.setColor(new Color(204, 255, 204));
        this.\u03ad.fillRect(20 + this.\u03d0, 20 + this.\u03d1, this.\u03a6 - 40, this.\u03a7 - 40);
        this.\u03ad.setColor(Color.black);
        this.\u03ad.drawRect(20 + this.\u03d0, 20 + this.\u03d1, this.\u03a6 - 40, this.\u03a7 - 40);
        this.\u03ad.drawString("HELP", 40 + this.\u03d0, 40 + this.\u03d1);
        this.\u03ad.setColor(new Color(204, 255, 255));
        this.\u03ad.fillRect(this.\u0294.x, this.\u0294.y, this.\u0294.width, this.\u0294.height);
        this.\u03ad.setColor(Color.black);
        this.\u03ad.drawRect(this.\u0294.x, this.\u0294.y, this.\u0294.width, this.\u0294.height);
        this.\u03ad.drawString("OK", this.\u0294.x + 15, this.\u0294.y + 15);
    }
    
    public void displayResult(final String s) {
        this.\u018f = 200;
        this.\u0190 = 60;
        this.\u018d = this.getSize().width / 2 - this.\u018f / 2;
        this.\u018e = this.getSize().height / 2 - this.\u0190 / 2;
        this.\u03ad.setColor(this.\u02e0);
        this.\u03ad.fillRect(this.\u018d, this.\u018e, this.\u018f, this.\u0190);
        this.\u03ad.setColor(Color.black);
        this.\u03ad.drawRect(this.\u018d, this.\u018e, this.\u018f, this.\u0190);
        this.\u03ad.drawString(s, this.\u018d + this.\u018f / 2 - this.getFontMetrics(this.getFont()).stringWidth(s) / 2, this.\u018e + this.\u0190 / 2);
    }
    
    public void displaySolutionWrong() {
        this.drawBoardSituation();
        this.\u03ad.setColor(new Color(204, 255, 204));
        this.\u03ad.fillRect(20 + this.\u03d0, 20 + this.\u03d1, this.\u03a6 - 40, this.\u03a7 - 40);
        this.\u03ad.setColor(Color.black);
        this.\u03ad.drawRect(20 + this.\u03d0, 20 + this.\u03d1, this.\u03a6 - 40, this.\u03a7 - 40);
        this.\u03ad.drawString(this.\u02e2, 40 + this.\u03d0, 40 + this.\u03d1);
        this.\u03ad.setColor(new Color(204, 255, 255));
    }
    
    public void displayMessage(final String s) {
        this.drawBoardSituation();
        this.\u03ad.setColor(new Color(204, 255, 204));
        this.\u03ad.fillRect(20 + this.\u03d0, 20 + this.\u03d1, this.\u03a6 - 40, this.\u03a7 - 40);
        this.\u03ad.setColor(Color.black);
        this.\u03ad.drawRect(20 + this.\u03d0, 20 + this.\u03d1, this.\u03a6 - 40, this.\u03a7 - 40);
        this.\u03ad.drawString(s, 40 + this.\u03d0, 40 + this.\u03d1);
        this.\u03ad.setColor(this.\u02e0);
        this.\u03ad.fillRect(this.\u0294.x, this.\u0294.y, this.\u0294.width, this.\u0294.height);
        this.\u03ad.setColor(Color.black);
        this.\u03ad.drawRect(this.\u0294.x, this.\u0294.y, this.\u0294.width, this.\u0294.height);
        this.\u03ad.drawString("OK", this.\u0294.x + 15, this.\u0294.y + 15);
    }
    
    public void displaySystemMessage(final String s, final boolean b) {
        this.drawBoardSituation();
        int n = 20;
        for (String s2 = this.getTagValue(s, "<LINE>"); s2.length() != 0; s2 = this.getTagValue(s, "<LINE>"), n += 20) {
            this.\u03ad.setColor(new Color(204, 255, 204));
            this.\u03ad.fillRect(20 + this.\u03d0, 20 + this.\u03d1, this.\u03a6 - 40, this.\u03a7 - 40);
            this.\u03ad.setColor(Color.black);
            this.\u03ad.drawRect(20 + this.\u03d0, 20 + this.\u03d1, this.\u03a6 - 40, this.\u03a7 - 40);
            this.\u03ad.drawString(s2, 40 + this.\u03d0, 40 + this.\u03d1 + n);
        }
        if (b) {
            this.\u03ad.setColor(new Color(204, 255, 255));
            this.\u03ad.fillRect(this.\u0294.x, this.\u0294.y, this.\u0294.width, this.\u0294.height);
            this.\u03ad.setColor(Color.black);
            this.\u03ad.drawRect(this.\u0294.x, this.\u0294.y, this.\u0294.width, this.\u0294.height);
            this.\u03ad.drawString("OK", this.\u0294.x + 15, this.\u0294.y + 15);
        }
    }
    
    public boolean boardFull() {
        int n = 0;
        final int n2 = this.\u03be * this.\u03bd;
        for (int i = 0; i < this.\u03bd; ++i) {
            for (int j = 0; j < this.\u03be; ++j) {
                if (this.\u03b1[i][j] > 0 || this.\u03b2[i][j] > 0) {
                    ++n;
                }
            }
        }
        return n == n2;
    }
    
    public boolean checkSolution() {
        for (int i = 0; i < this.\u03bd - 1; ++i) {
            for (int j = 0; j < this.\u03be - 1; ++j) {
                for (int k = i + 1; k < this.\u03bd; ++k) {
                    if (this.\u03b1[i][j] == this.\u03b1[k][j] || this.\u03b1[i][j] == this.\u03b2[k][j]) {
                        return false;
                    }
                }
                for (int l = j + 1; l < this.\u03be; ++l) {
                    if (this.\u03b1[i][j] == this.\u03b1[i][l] || this.\u03b1[i][j] == this.\u03b2[i][l]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void verwerkPuzzelString(final String s) {
    }
    
    public synchronized void updateRoomUserList(final String s) {
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.displayResult = false;
        this.requestFocus();
        if (n > 0 && n < this.\u03bd * this.\u03a8 + this.\u03d0 && n2 > 0 && n2 < this.\u03be * this.\u03a9 + this.\u03d1) {
            this.\u0388 = (n - this.\u03d0) / this.\u03a8;
            this.\u0389 = (n2 - this.\u03d1) / this.\u03a9;
            this.buildOffscreenBufferImage();
            this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
            this.repaint();
        }
        this.requestFocus();
        return super.mouseDown(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.\u03c1 != 50 && this.\u03bf) {
            this.\u03bf = false;
            if (n > 0 && n < this.\u03bd * this.\u03a8 + this.\u03d0 && n2 > 0 && n2 < this.\u03be * this.\u03a9 + this.\u03d1) {
                final int n3 = (n - this.\u03d0) / this.\u03a8;
                final int n4 = (n2 - this.\u03d1) / this.\u03a9;
                if (this.\u03b2[n3][n4] == 0) {
                    this.\u02d0(1, n3, n4, this.\u03c0 + 1);
                }
                this.currentGrid.determinePossible();
                this.currentGrid.scan(false);
                this.\u03c0 = 0;
                if (this.boardFull()) {
                    if (this.checkSolution()) {
                        this.\u02e4 = this.\u02e1;
                    }
                    else {
                        this.\u02e4 = this.\u02e2;
                    }
                    this.displayResult = true;
                    this.buildOffscreenBufferImage();
                    this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
                    this.repaint();
                }
                else {
                    this.buildOffscreenBufferImage();
                    this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
                    this.repaint();
                }
            }
            else {
                this.buildOffscreenBufferImage();
                this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
                this.repaint();
            }
        }
        this.requestFocus();
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.\u03c1 != 3) {
            if (this.\u03bf) {
                this.drawDragItem(this.\u03c0, n, n2);
            }
            else if (this.checkDragItem(n, n2)) {
                this.\u03bf = true;
                this.buildOffscreenBufferImage();
                this.drawDragItem(this.\u03c0, n, n2);
            }
        }
        this.requestFocus();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.\u03c1 != 3 && this.\u03bf) {
            this.drawDragItem(this.\u03c0, n, n2);
        }
        return true;
    }
    
    public boolean checkDragItem(final int n, final int n2) {
        for (int i = 0; i < this.\u03ae; ++i) {
            if (this.\u03b3[i] < this.\u03bd) {
                final int n3 = this.\u03bd * this.\u03a8 + this.\u03d0;
                final int n4 = i * this.\u03a9 + this.\u03d1;
                if (n > n3 && n < n3 + this.\u03a8 && n2 > n4 && n2 < n4 + this.\u03a9) {
                    this.\u03c0 = i;
                    return true;
                }
            }
        }
        if (n > this.\u03d0 && n < this.\u03bd * this.\u03a8 + this.\u03a8 + this.\u03d0 && n2 > this.\u03d1 && n2 < this.\u03be * this.\u03a9 + this.\u03a9 + this.\u03d1) {
            final int n5 = (n - this.\u03d0) / this.\u03a8;
            final int n6 = (n2 - this.\u03d1) / this.\u03a9;
            if (this.\u03b1[n5][n6] != 0 && this.\u03b2[n5][n6] == 0) {
                this.\u03c0 = this.\u03b1[n5][n6] - 1;
                this.\u02d0(0, n5, n6, 0);
                this.currentGrid.determinePossible();
                this.buildOffscreenBufferImage();
                this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
                this.repaint();
                return true;
            }
        }
        return false;
    }
    
    public void showHint() {
        for (int i = 1; i < this.\u039c + 1; ++i) {
            if (this.currentGrid.cell[this.\u039e[i].getRow()][this.\u039e[i].getColumn()].get() != this.\u039e[i].getValue()) {
                this.\u0398 = this.\u039e[i].getRow();
                this.\u0399 = this.\u039e[i].getColumn();
                this.showHint = true;
                this.revealMove = false;
                this.currentGrid.determinePossible();
                this.\u0388 = this.\u0398;
                this.\u0389 = this.\u0399;
                this.buildOffscreenBufferImage();
                this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
                this.repaint();
                break;
            }
        }
        this.requestFocus();
    }
    
    public void revealMove() {
        for (int i = 1; i < this.\u039c + 1; ++i) {
            if (this.currentGrid.cell[this.\u039e[i].getRow()][this.\u039e[i].getColumn()].get() != this.\u039e[i].getValue()) {
                this.\u0395 = this.\u039e[i].getRow();
                this.\u0396 = this.\u039e[i].getColumn();
                this.\u0397 = this.\u039e[i].getValue();
                this.revealMove = true;
                this.\u02d0(0, this.\u0395, this.\u0396, this.\u0397);
                this.currentGrid.determinePossible();
                this.buildOffscreenBufferImage();
                this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
                this.repaint();
                break;
            }
        }
        if (this.boardFull()) {
            if (this.checkSolution()) {
                this.\u02e4 = this.\u02e1;
            }
            else {
                this.\u02e4 = this.\u02e2;
            }
            this.displayResult = true;
            this.buildOffscreenBufferImage();
            this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
            this.repaint();
        }
        this.requestFocus();
    }
    
    public void checkMoves(final boolean checkMoves) {
        this.checkMoves = checkMoves;
        this.currentGrid.determinePossible();
        this.buildOffscreenBufferImage();
        this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
        this.repaint();
        this.requestFocus();
    }
    
    public void drawDragItem(final int n, final int n2, final int n3) {
        final int \u03c8 = this.\u03a8;
        final int \u03c9 = this.\u03a9;
        final int n4 = n2 - \u03c8 / 2;
        final int n5 = n3 - \u03c9 / 2;
        this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
        this.\u03ac.drawImage(this.\u03b7[this.\u03c0], n4, n5, this.\u03a8, this.\u03a9, this);
        this.repaint();
    }
    
    public void updateSystemMessage(final String s) {
    }
    
    public void updateUserlist(final String s) {
    }
    
    public void updatePuzzle(final String s) {
        final int \u02c0 = this.\u02c0(this.getTagValue(s, "<X>"));
        final int \u02c02 = this.\u02c0(this.getTagValue(s, "<Y>"));
        final boolean b = true;
        this.\u03b1[\u02c0][\u02c02] = (b ? 1 : 0);
        this.\u03b2[\u02c0][\u02c02] = (b ? 1 : 0);
    }
    
    public void updateChat(final String s) {
    }
    
    public SudokuPanel(final int n, final int n2, final int n3, final int n4) {
        this.\u012b = false;
        this.displayResult = false;
        this.\u02e4 = "";
        this.\u0388 = 1;
        this.\u0389 = 1;
        this.\u038a = 2;
        this.\u038e = 2;
        this.\u038f = 50;
        this.\u0390 = 20;
        this.\u0391 = this.\u02e0;
        this.\u0392 = Color.white;
        this.\u0393 = "OK";
        this.\u018d = 2;
        this.\u018e = 2;
        this.\u018f = 200;
        this.\u0190 = 100;
        this.\u0191 = this.\u02e0;
        this.\u0192 = Color.white;
        this.\u0193 = "Message Panel";
        this.checkMoves = false;
        this.revealMove = false;
        this.\u039d = new Move[144];
        this.\u039e = new Move[144];
        this.hideString = false;
        this.showCandidates = false;
        this.\u03ba = false;
        this.\u03bb = false;
        this.reshape(n, n2, n3, n4);
    }
    
    public void setString(final String \u00ec) {
        this.\u00cc = \u00ec;
    }
    
    public void setImage(final Image image, final Image image2) {
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void hideString() {
        this.hideString = true;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    private void \u02d0(final int n, final int \u03ad, final int \u03ae, final int n2) {
        final int value = this.currentGrid.cell[\u03ad][\u03ae].get();
        final boolean b = this.\u03b0[\u03ad][\u03ae];
        this.\u03b0[\u03ad][\u03ae] = this.\u012b;
        this.\u03b1[\u03ad][\u03ae] = n2;
        this.currentGrid.cell[\u03ad][\u03ae].set(n2);
        this.showHint = false;
        this.\u039d[this.\u039b].set(n, \u03ad, \u03ae, n2, this.\u012b, value, b);
        this.\u0388 = \u03ad;
        this.\u0389 = \u03ae;
        ++this.\u039b;
    }
    
    public void undoMove() {
        if (this.\u039b > 0) {
            --this.\u039b;
            final int type = this.\u039d[this.\u039b].getType();
            final int row = this.\u039d[this.\u039b].getRow();
            final int column = this.\u039d[this.\u039b].getColumn();
            this.\u03b0[row][column] = this.\u039d[this.\u039b].getPreviousMode();
            this.currentGrid.cell[row][column].set(this.\u039d[this.\u039b].getPreviousValue());
            this.\u03b1[row][column] = this.\u039d[this.\u039b].getPreviousValue();
            this.currentGrid.determinePossible();
            this.\u0388 = row;
            this.\u0389 = column;
            this.buildOffscreenBufferImage();
            this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
            this.repaint();
            this.\u03c1 = 1;
            if (type == 1 || type == 3 || type == 2 || type == 4) {
                this.\u02d1(type);
                if (type == 1) {
                    this.undoMove();
                }
            }
        }
        this.requestFocus();
    }
    
    private void \u02d1(final int i) {
        while (i == this.\u039d[this.\u039b - 1].getType()) {
            this.undoMove();
        }
    }
    
    private void \u02e0(final int n, final int \u03ad, final int \u03ae, final int n2) {
        if (!this.currentGrid.cell[\u03ad][\u03ae].isPossibleAccordingToUser(n2)) {
            this.currentGrid.cell[\u03ad][\u03ae].setPossibleAccordingToUser(n2);
        }
        else {
            this.currentGrid.cell[\u03ad][\u03ae].setNotPossibleAccordingToUser(n2);
        }
        this.\u00c2[\u03ad][\u03ae] = true;
        this.\u03b0[\u03ad][\u03ae] = false;
        this.\u03b1[\u03ad][\u03ae] = 0;
        this.currentGrid.cell[\u03ad][\u03ae].set(0);
        this.\u0388 = \u03ad;
        this.\u0389 = \u03ae;
    }
    
    public boolean processKey(final Event event, final int n) {
        this.displayResult = false;
        final char c = (char)n;
        if (n == 1006 && this.\u0388 > 0) {
            --this.\u0388;
        }
        if (n == 1007 && this.\u0388 < this.\u03bd - 1) {
            ++this.\u0388;
        }
        if (n == 1005 && this.\u0389 < this.\u03be - 1) {
            ++this.\u0389;
        }
        if (n == 1004 && this.\u0389 > 0) {
            --this.\u0389;
        }
        if (n == 127) {
            if (!event.shiftDown()) {
                if (this.checkMoves) {
                    for (int i = 0; i < this.\u03bd; ++i) {
                        for (int j = 0; j < this.\u03be; ++j) {
                            if (this.\u03b1[i][j] != this.solutionGrid.cell[j][i].get()) {
                                this.\u02d0(2, i, j, 0);
                            }
                        }
                    }
                    this.currentGrid.determinePossible();
                    this.currentGrid.scan(false);
                    this.\u03c0 = 0;
                }
                else {
                    if (this.\u03b2[this.\u0388][this.\u0389] == 0) {
                        this.\u02d0(0, this.\u0388, this.\u0389, 0);
                    }
                    this.currentGrid.determinePossible();
                    this.currentGrid.scan(false);
                    this.\u03c0 = 0;
                }
            }
            else {
                for (int k = 0; k < this.\u03bd; ++k) {
                    for (int l = 0; l < this.\u03be; ++l) {
                        if (this.\u03b0[k][l]) {
                            this.\u02d0(3, k, l, 0);
                        }
                    }
                }
                this.currentGrid.determinePossible();
                this.currentGrid.scan(false);
                this.\u03c0 = 0;
            }
        }
        if (n == 1025 && event.shiftDown()) {
            for (int n2 = 0; n2 < this.\u03bd; ++n2) {
                for (int n3 = 0; n3 < this.\u03be; ++n3) {
                    if (this.\u03b0[n2][n3]) {
                        this.\u02d0(4, n2, n3, this.\u03b1[n2][n3]);
                        this.\u03b0[n2][n3] = false;
                    }
                }
            }
            this.currentGrid.determinePossible();
            this.currentGrid.scan(false);
            this.\u03c0 = 0;
        }
        if (n == 32) {
            if (this.\u03b2[this.\u0388][this.\u0389] == 0) {
                this.\u02d0(0, this.\u0388, this.\u0389, 0);
            }
            this.currentGrid.determinePossible();
            this.currentGrid.scan(false);
            this.\u03c0 = 0;
        }
        if (n == 8) {
            if (this.\u03b2[this.\u0388][this.\u0389] == 0) {
                this.\u02d0(0, this.\u0388, this.\u0389, 0);
            }
            if (this.\u0388 > 0) {
                --this.\u0388;
            }
            else if (this.\u0389 > 0) {
                this.\u0388 = this.\u03bd - 1;
                --this.\u0389;
            }
            this.currentGrid.determinePossible();
            this.currentGrid.scan(false);
            this.\u03c0 = 0;
        }
        if (c > '0' && c <= '9') {
            if (event.modifiers != 0) {
                if (this.\u03b2[this.\u0388][this.\u0389] == 0) {
                    this.\u02e0(0, this.\u0388, this.\u0389, Integer.parseInt(String.valueOf(c)));
                }
            }
            else {
                if (this.\u03b2[this.\u0388][this.\u0389] == 0) {
                    this.\u02d0(0, this.\u0388, this.\u0389, Integer.parseInt(String.valueOf(c)));
                    for (int notPossibleAccordingToUser = 1; notPossibleAccordingToUser < 10; ++notPossibleAccordingToUser) {
                        this.currentGrid.cell[this.\u0388][this.\u0389].setNotPossibleAccordingToUser(notPossibleAccordingToUser);
                    }
                }
                this.currentGrid.determinePossible();
                this.currentGrid.scan(false);
                this.\u03c0 = 0;
                if (this.boardFull()) {
                    if (this.checkSolution()) {
                        this.\u02e4 = this.\u02e1;
                    }
                    else {
                        this.\u02e4 = this.\u02e2;
                    }
                    this.displayResult = true;
                    this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
                    this.repaint();
                }
                else {
                    this.buildOffscreenBufferImage();
                    this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
                    this.repaint();
                }
            }
        }
        this.buildOffscreenBufferImage();
        this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u03aa != null) {
            graphics.drawImage(this.\u03aa, 0, 0, this);
        }
        else {
            this.\u03aa = this.createImage(this.size().width, this.size().height);
            this.\u03ab = this.createImage(this.size().width, this.size().height);
            this.\u03ac = this.\u03aa.getGraphics();
            this.\u03ad = this.\u03ab.getGraphics();
            this.buildOffscreenBufferImage();
            this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
            graphics.drawImage(this.\u03aa, 0, 0, this);
        }
        if (this.\u00cc != null && !this.hideString) {
            graphics.drawString(this.\u00cc, this.size().width / 2 - this.getFontMetrics(this.getFont()).stringWidth(this.\u00cc) / 2, this.size().height / 2 + this.getFont().getSize() / 2);
        }
    }
    
    public String getCurrentSituation() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<NAME>" + this.\u02e3 + "</NAME>");
        sb.append("<BOARD>");
        for (int i = 0; i < this.\u03bd; ++i) {
            for (int j = 0; j < this.\u03be; ++j) {
                sb.append(String.valueOf(this.\u03b1[i][j]) + " ");
            }
        }
        sb.append("</BOARD>");
        return sb.toString();
    }
    
    public String getCurrentSituationSubmit() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<NAME>" + this.\u02e3 + "</NAME>");
        sb.append("<BOARD>");
        for (int i = 0; i < this.\u03bd; ++i) {
            for (int j = 0; j < this.\u03be; ++j) {
                sb.append(String.valueOf(this.\u03b1[j][i]) + " ");
            }
        }
        sb.append("</BOARD>");
        return sb.toString();
    }
    
    public String getCurrentEmptySituation() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<NAME>" + this.\u02e3 + "</NAME>");
        sb.append("<BOARD>");
        for (int i = 0; i < this.\u03bd; ++i) {
            for (int j = 0; j < this.\u03be; ++j) {
                if (this.\u03b2[i][j] > 0) {
                    sb.append(String.valueOf(this.\u03b1[i][j]) + " ");
                }
                else {
                    sb.append("0 ");
                }
            }
        }
        sb.append("</BOARD>");
        return sb.toString();
    }
    
    public String getCurrentSolution() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<NAME>" + this.\u02e3 + "</NAME>");
        sb.append("<BOARD>");
        for (int i = 0; i < this.\u03bd; ++i) {
            for (int j = 0; j < this.\u03be; ++j) {
                sb.append(String.valueOf(this.solutionGrid.cell[j][i].get()) + " ");
            }
        }
        sb.append("</BOARD>");
        return sb.toString();
    }
    
    public void setCurrentSituation(final String s) {
        this.getTagValue(s, "<NAME>");
        String s2 = this.getTagValue(s, "<BOARD>");
        for (int i = 0; i < this.\u03bd; ++i) {
            for (int j = 0; j < this.\u03be; ++j) {
                final int n = 0;
                final int index = s2.indexOf(" ");
                this.\u03b1[i][j] = Integer.parseInt(s2.substring(n, index));
                s2 = s2.substring(index + 1);
            }
        }
        this.buildOffscreenBufferImage();
        this.\u03ac.drawImage(this.\u03ab, 0, 0, this);
        this.repaint();
    }
    
    static void \u011d(final SudokuPanel sudokuPanel, final int n, final int n2, final int n3, final int n4) {
        sudokuPanel.\u02d0(n, n2, n3, n4);
    }
    
    static Graphics \u02e1(final SudokuPanel sudokuPanel) {
        return sudokuPanel.\u03ac;
    }
    
    static Image \u02e2(final SudokuPanel sudokuPanel) {
        return sudokuPanel.\u03ab;
    }
}
