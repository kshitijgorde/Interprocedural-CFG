import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Font;
import java.awt.Event;
import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Button;
import java.awt.Graphics;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Boulderdash extends Applet implements Runnable
{
    private static final String VERSION = "1.0";
    private static final String ID = "200009131200";
    Vector redraw;
    Vector sounds;
    Graphics g;
    Thread botThread;
    boolean paused;
    private static final int SLEEP_DELAY = 2750;
    int maxtimer;
    int timer;
    int level;
    int score;
    int accumulatedScore;
    int scorePerDiamond;
    int scorePerExtraDiamond;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    private Button buttonPrev;
    private Button buttonRestart;
    private Button buttonNext;
    Coord coordPlayer;
    Coord coordExit;
    int numNeededDiamonds;
    int numFulfilledDiamonds;
    boolean levelDone;
    boolean playerAlmostDead;
    boolean playerDead;
    int[] arrNumNeededDiamonds;
    int[] arrScorePerDiamond;
    int[] arrScorePerExtraDiamond;
    int[] arrMaxtimer;
    String[] arrLevelTitle;
    String[] arrLevelAuthor;
    String[] arrImageFilename;
    private Image[] img;
    private static final int NUM_IMAGES = 15;
    private static final String imgRep = ".:#WMPO+*BFA][R";
    private String imageFilename;
    private static int[][] imgCoord;
    private static final int NUM_SOUNDS = 13;
    private static final int MOVE_MAGICWALL_SOUND = 0;
    private static final int MOVE_ROCK_SOUND = 1;
    private static final int MOVE_DIAMOND_SOUND = 2;
    private static final int MOVE_OPENEXIT_SOUND = 3;
    private static final int MOVE_AMOEBA_SOUND = 4;
    private static final int MOVE_SMASH_SOUND = 5;
    private static final int MOVE_NULL_SOUND = 6;
    private static final int MOVE_DIRT_SOUND = 7;
    private static final int MOVE_PLAYERSMASH_SOUND = 8;
    private static final int MOVE_DIED_SOUND = 9;
    private static final int MOVE_CONGRATULATIONS_SOUND = 10;
    private static final int MOVE_COMPLETE_SOUND = 11;
    private static final int MOVE_ROCK2_SOUND = 12;
    private static String[] soundFilename;
    private AudioClip[] moveSound;
    private static final int X_DIMEN = 20;
    private static final int Y_DIMEN = 20;
    private static final int SQUARE_DIMEN = 20;
    private static final int CON_DIMEN = 40;
    private static final int VER_DIMEN = 12;
    private static final int SIG_DIMEN = 24;
    private char[][] levelMap;
    private int NUM_LEVELS;
    private String levelTitle;
    private String levelAuthor;
    private String[][] levelData;
    Vector bots;
    
    public Boulderdash() {
        this.redraw = new Vector();
        this.sounds = new Vector();
        this.maxtimer = 1;
        this.scorePerDiamond = 5;
        this.scorePerExtraDiamond = 10;
        this.buttonPrev = new Button("< Prev");
        this.buttonRestart = new Button("Restart");
        this.buttonNext = new Button("Next >");
        this.imageFilename = "images.gif";
        this.bots = new Vector();
    }
    
    public void init() {
        if (this.g == null) {
            this.g = this.getGraphics();
        }
        this.requestFocus();
        final String parameter = this.getParameter("levels");
        if (parameter == null) {
            return;
        }
        this.NUM_LEVELS = Integer.parseInt(parameter);
        this.levelData = new String[this.NUM_LEVELS][20];
        this.arrNumNeededDiamonds = new int[this.NUM_LEVELS];
        this.arrScorePerDiamond = new int[this.NUM_LEVELS];
        this.arrScorePerExtraDiamond = new int[this.NUM_LEVELS];
        this.arrMaxtimer = new int[this.NUM_LEVELS];
        this.arrLevelTitle = new String[this.NUM_LEVELS];
        this.arrLevelAuthor = new String[this.NUM_LEVELS];
        this.arrImageFilename = new String[this.NUM_LEVELS];
        for (int i = 0; i < this.NUM_LEVELS; ++i) {
            for (int j = 0; j < 20; ++j) {
                this.levelData[i][j] = this.getParameter("LEVEL_DATA_" + (i + 1) + "_" + (j + 1));
            }
            this.arrNumNeededDiamonds[i] = Integer.parseInt(this.getParameter("NEEDED_DIAMONDS_" + (i + 1)));
            this.arrScorePerDiamond[i] = Integer.parseInt(this.getParameter("POINTS_PER_DIAMOND_" + (i + 1)));
            this.arrScorePerExtraDiamond[i] = Integer.parseInt(this.getParameter("POINTS_PER_EXTRA_DIAMOND_" + (i + 1)));
            this.arrLevelTitle[i] = this.getParameter("LEVEL_TITLE_" + (i + 1));
            if (this.arrLevelTitle[i] == null) {
                this.arrLevelTitle[i] = "Unnamed";
            }
            this.arrLevelAuthor[i] = this.getParameter("LEVEL_AUTHOR_" + (i + 1));
            if (this.arrLevelAuthor[i] == null) {
                this.arrLevelAuthor[i] = "Anonymous";
            }
            this.arrImageFilename[i] = this.getParameter("IMAGEFILE_" + (i + 1));
            if (this.arrImageFilename[i] == null) {
                this.arrImageFilename[i] = "images.gif";
            }
        }
        this.coordPlayer = new Coord();
        this.coordExit = new Coord();
        this.coordPlayer.x = 2;
        this.coordPlayer.y = 2;
        this.coordExit.x = 1;
        this.coordExit.y = 1;
        this.levelMap = new char[20][];
        for (int k = 0; k < 20; ++k) {
            this.levelMap[k] = new char[20];
        }
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.add(this.buttonPrev);
        panel.add(this.buttonRestart);
        panel.add(this.buttonNext);
        this.add("South", panel);
        this.setupLevel(this.level = 1);
    }
    
    private void loadImages() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), this.imageFilename);
        mediaTracker.addImage(image, 0);
        this.img = new Image[15];
        for (int i = 0; i < 15; ++i) {
            mediaTracker.addImage(this.img[i] = this.splitSourceImage(image, Boulderdash.imgCoord[i]), i + 1);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Boulderdash.init() caught " + ex);
            ex.printStackTrace();
        }
    }
    
    private Image splitSourceImage(final Image image, final int[] array) {
        return this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(array[0], array[1], array[2], array[3])));
    }
    
    private void setupLevel(final int n) {
        this.g.setColor(Color.black);
        this.g.fillRect(0, 0, 400, 400);
        this.levelTitle = new String(this.arrLevelTitle[n - 1]);
        this.levelAuthor = new String(this.arrLevelAuthor[n - 1]);
        this.numNeededDiamonds = this.arrNumNeededDiamonds[n - 1];
        this.scorePerDiamond = this.arrScorePerDiamond[n - 1];
        this.scorePerExtraDiamond = this.arrScorePerExtraDiamond[n - 1];
        this.imageFilename = new String(this.arrImageFilename[n - 1]);
        this.loadImages();
        this.levelDone = false;
        this.playerAlmostDead = false;
        this.playerDead = false;
        this.bots = new Vector();
        this.numFulfilledDiamonds = 0;
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                this.levelMap[j][i] = this.levelData[n - 1][i].charAt(j);
                this.drawCoord(j, i);
                if (this.levelMap[j][i] == '[' || this.levelMap[j][i] == ']') {
                    this.coordExit.x = j;
                    this.coordExit.y = i;
                }
                else if (this.levelMap[j][i] == 'P') {
                    this.coordPlayer.x = j;
                    this.coordPlayer.y = i;
                }
                else if (this.levelMap[j][i] == 'B' || this.levelMap[j][i] == 'F') {
                    this.bots.addElement(new BotPosition(j, i, 1, (int)((this.levelMap[j][i] != 'F') ? 1 : 0)));
                }
            }
        }
        this.updateStatus();
    }
    
    public void start() {
        if (this.botThread == null && !this.paused) {
            (this.botThread = new Thread(this)).start();
        }
    }
    
    public void run() {
        if (this.paused) {
            return;
        }
        int n = 0;
        Coord coord = new Coord();
        while (true) {
            if (this.playerAlmostDead && n != 0) {
                for (int n2 = 18; n2 >= 0 && n != 0; --n2) {
                    for (int n3 = 0; n3 < 20 && n != 0; ++n3) {
                        if (this.levelMap[n3][n2] == '*') {
                            n = 0;
                        }
                    }
                }
                if (n != 0) {
                    this.playerAlmostDead = false;
                    this.playerDead = true;
                }
            }
            n = 0;
            for (int i = 18; i >= 0; --i) {
                for (int j = 0; j < 20; ++j) {
                    boolean b = false;
                    boolean b2 = false;
                    boolean b3 = false;
                    if ((i - 1 > 0 && this.levelMap[j][i - 1] == 'A') || (j + 1 < 20 && this.levelMap[j + 1][i] == 'A') || (i + 1 < 20 && this.levelMap[j][i + 1] == 'A') || (j - 1 > 0 && this.levelMap[j - 1][i] == 'A' && (this.levelMap[j][i] == 'B' || this.levelMap[j][i] == 'F'))) {
                        b3 = true;
                        coord = new Coord(j, i);
                    }
                    if (this.levelMap[j][i] == '*') {
                        this.levelMap[j][i] = '.';
                        this.redrawAndSound(new Coord(j, i), 6);
                        n = 1;
                    }
                    else if (this.playerDead) {
                        this.niceBanner("Oh no, you've died!");
                        try {
                            Thread.sleep(2750L);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        this.score = this.accumulatedScore;
                        this.setupLevel(this.level);
                        this.paused = false;
                    }
                    if (this.levelMap[j][i] == 'P') {
                        if ((i - 1 > 0 && (this.levelMap[j][i - 1] == 'B' || this.levelMap[j][i - 1] == 'F')) || (j + 1 < 20 && (this.levelMap[j + 1][i] == 'B' || this.levelMap[j + 1][i] == 'F')) || (i + 1 < 20 && (this.levelMap[j][i + 1] == 'B' || this.levelMap[j][i + 1] == 'F')) || (j - 1 > 0 && (this.levelMap[j - 1][i] == 'B' || this.levelMap[j - 1][i] == 'F'))) {
                            b = true;
                            coord = new Coord(j, i);
                            this.playerAlmostDead = true;
                        }
                    }
                    else if (this.levelMap[j][i] == 'O' || this.levelMap[j][i] == '+' || b3 || b) {
                        char c;
                        if (this.levelMap[j][i] == 'O') {
                            c = 'O';
                        }
                        else if (this.levelMap[j][i] == '+') {
                            c = '+';
                        }
                        else {
                            c = '?';
                        }
                        if (c != '?' && this.levelMap[j][i + 1] == '.') {
                            this.levelMap[j][i + 1] = c;
                            this.levelMap[j][i] = '.';
                            this.redrawAndSound(new Coord(j, i), 1);
                            this.redrawAndSound(new Coord(j, i + 1), 12);
                            if (i + 2 < 20 && (this.levelMap[j][i + 2] == 'B' || this.levelMap[j][i + 2] == 'F' || this.levelMap[j][i + 2] == 'P')) {
                                b2 = true;
                                coord = new Coord(j, i + 2);
                            }
                        }
                        else if (c != '?' && (this.levelMap[j][i + 1] == 'O' || this.levelMap[j][i + 1] == '+')) {
                            if (this.levelMap[j + 1][i + 1] == '.' && this.levelMap[j + 1][i] == '.') {
                                this.levelMap[j + 1][i + 1] = c;
                                this.levelMap[j][i] = '.';
                                this.redrawAndSound(new Coord(j, i), 12);
                                this.redrawAndSound(new Coord(j + 1, i + 1), 1);
                                if (i + 2 < 20 && ((this.levelMap[j + 1][i + 2] == 'B' && this.levelMap[j + 1][i + 2] == 'F') || this.levelMap[j + 1][i + 2] == 'P')) {
                                    b2 = true;
                                    coord = new Coord(j + 1, i + 2);
                                }
                            }
                            else if (this.levelMap[j - 1][i + 1] == '.' && this.levelMap[j - 1][i] == '.') {
                                this.levelMap[j - 1][i + 1] = c;
                                this.levelMap[j][i] = '.';
                                this.redrawAndSound(new Coord(j, i), 12);
                                this.redrawAndSound(new Coord(j - 1, i + 1), 12);
                                if (i + 2 < 20 && ((this.levelMap[j - 1][i + 2] == 'B' && this.levelMap[j - 1][i + 2] == 'F') || this.levelMap[j - 1][i + 2] == 'P')) {
                                    b2 = true;
                                    coord = new Coord(j - 1, i + 2);
                                }
                            }
                        }
                    }
                    Label_1719: {
                        if ((b || b3 || b2) && (this.levelMap[coord.x][coord.y] == 'B' || this.levelMap[coord.x][coord.y] == 'F' || this.levelMap[coord.x][coord.y] == 'P')) {
                            final boolean b4 = this.levelMap[coord.x][coord.y] == 'B';
                            if (this.levelMap[coord.x][coord.y] == 'P') {
                                if (!b && !b2) {
                                    break Label_1719;
                                }
                                this.playerAlmostDead = true;
                            }
                            for (int k = coord.x - 1; k <= coord.x + 1; ++k) {
                                for (int l = coord.y - 1; l <= coord.y + 1; ++l) {
                                    if (this.levelMap[k][l] == '.' || this.levelMap[k][l] == ':' || this.levelMap[k][l] == 'W' || this.levelMap[k][l] == 'M' || this.levelMap[k][l] == '+' || this.levelMap[k][l] == 'A' || (k == coord.x && l == coord.y - 1) || (k == coord.x && l == coord.y && k > 0 && k < 20 && l > 0 && l < 20)) {
                                        if (b4) {
                                            this.levelMap[k][l] = '+';
                                        }
                                        else {
                                            this.levelMap[k][l] = '*';
                                        }
                                        this.redrawAndSound(new Coord(k, l), 5);
                                        for (int n4 = 0; n4 < this.bots.size(); ++n4) {
                                            final BotPosition botPosition = this.bots.elementAt(n4);
                                            if (botPosition.x == coord.x && botPosition.y == coord.y) {
                                                this.bots.removeElementAt(n4);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.levelMap[j][i] == 'M' && i > 1 && (this.levelMap[j][i - 1] == 'O' || this.levelMap[j][i - 1] == '+')) {
                        if (this.levelMap[j][i + 1] == '.') {
                            if (this.levelMap[j][i - 1] == 'O') {
                                this.levelMap[j][i + 1] = '+';
                                this.redrawAndSound(new Coord(j, i + 1), 0);
                            }
                            else {
                                this.levelMap[j][i + 1] = 'O';
                                this.redrawAndSound(new Coord(j, i + 1), 0);
                            }
                        }
                        this.levelMap[j][i - 1] = '.';
                        this.redrawAndSound(new Coord(j, i - 1), 6);
                    }
                    if (this.levelMap[j][i] == 'A' && this.timer >= this.maxtimer && Math.random() < 0.1) {
                        int n5;
                        int n6;
                        if (Math.random() < 0.5) {
                            n5 = (int)(Math.round(Math.random() * 2.0) - 1L);
                            n6 = 0;
                        }
                        else {
                            n6 = (int)(Math.round(Math.random() * 2.0) - 1L);
                            n5 = 0;
                        }
                        if (j + n5 < 20 && j + n5 > 0 && i + n6 < 20 && i + n6 > 0 && (this.levelMap[j + n5][i + n6] == '.' || this.levelMap[j + n5][i + n6] == ':')) {
                            this.levelMap[j + n5][i + n6] = 'A';
                            this.redrawAndSound(new Coord(j + n5, i + n6), 4);
                        }
                    }
                }
            }
            if (this.timer >= this.maxtimer) {
                for (int n7 = 0; n7 < this.bots.size(); ++n7) {
                    final BotPosition botPosition2 = this.bots.elementAt(n7);
                    final Coord coord2 = new Coord(botPosition2.x, botPosition2.y);
                    if (botPosition2.heading == 0) {
                        if (botPosition2.direction == 0 && this.levelMap[botPosition2.x + 1][botPosition2.y] == '.') {
                            final BotPosition botPosition3 = botPosition2;
                            ++botPosition3.x;
                            botPosition2.heading = 1;
                        }
                        else if (botPosition2.direction == 1 && this.levelMap[botPosition2.x - 1][botPosition2.y] == '.') {
                            final BotPosition botPosition4 = botPosition2;
                            --botPosition4.x;
                            botPosition2.heading = 3;
                        }
                        else if (this.levelMap[botPosition2.x][botPosition2.y - 1] == '.') {
                            final BotPosition botPosition5 = botPosition2;
                            --botPosition5.y;
                        }
                        else {
                            botPosition2.heading = 2;
                        }
                    }
                    if (botPosition2.heading == 1) {
                        if (botPosition2.direction == 0 && this.levelMap[botPosition2.x][botPosition2.y + 1] == '.') {
                            final BotPosition botPosition6 = botPosition2;
                            ++botPosition6.y;
                            botPosition2.heading = 2;
                        }
                        else if (botPosition2.direction == 1 && this.levelMap[botPosition2.x][botPosition2.y - 1] == '.') {
                            final BotPosition botPosition7 = botPosition2;
                            --botPosition7.y;
                            botPosition2.heading = 0;
                        }
                        else if (this.levelMap[botPosition2.x + 1][botPosition2.y] == '.') {
                            final BotPosition botPosition8 = botPosition2;
                            ++botPosition8.x;
                        }
                        else {
                            botPosition2.heading = 3;
                        }
                    }
                    if (botPosition2.heading == 2) {
                        if (botPosition2.direction == 0 && this.levelMap[botPosition2.x - 1][botPosition2.y] == '.') {
                            final BotPosition botPosition9 = botPosition2;
                            --botPosition9.x;
                            botPosition2.heading = 3;
                        }
                        else if (botPosition2.direction == 1 && this.levelMap[botPosition2.x + 1][botPosition2.y] == '.') {
                            final BotPosition botPosition10 = botPosition2;
                            ++botPosition10.x;
                            botPosition2.heading = 1;
                        }
                        else if (this.levelMap[botPosition2.x][botPosition2.y + 1] == '.') {
                            final BotPosition botPosition11 = botPosition2;
                            ++botPosition11.y;
                        }
                        else {
                            botPosition2.heading = 0;
                        }
                    }
                    if (botPosition2.heading == 3) {
                        if (botPosition2.direction == 0 && this.levelMap[botPosition2.x][botPosition2.y - 1] == '.') {
                            final BotPosition botPosition12 = botPosition2;
                            --botPosition12.y;
                            botPosition2.heading = 0;
                        }
                        else if (botPosition2.direction == 1 && this.levelMap[botPosition2.x][botPosition2.y + 1] == '.') {
                            final BotPosition botPosition13 = botPosition2;
                            ++botPosition13.y;
                            botPosition2.heading = 2;
                        }
                        else if (this.levelMap[botPosition2.x - 1][botPosition2.y] == '.') {
                            final BotPosition botPosition14 = botPosition2;
                            --botPosition14.x;
                        }
                        else {
                            botPosition2.heading = 1;
                        }
                    }
                    this.bots.setElementAt(botPosition2, n7);
                    if (botPosition2.x != coord2.x || botPosition2.y != coord2.y) {
                        this.levelMap[coord2.x][coord2.y] = '.';
                        this.levelMap[botPosition2.x][botPosition2.y] = (char)((botPosition2.direction == 0) ? 70 : 66);
                        this.redrawAndSound(new Coord(coord2.x, coord2.y), 6);
                        this.redrawAndSound(new Coord(botPosition2.x, botPosition2.y), 6);
                    }
                }
            }
            boolean b5 = true;
            for (int n8 = 1; n8 < 19 && b5; ++n8) {
                for (int n9 = 1; n9 < 19 && b5; ++n9) {
                    if (this.levelMap[n8][n9] == 'A' && (this.levelMap[n8][n9 - 1] == '.' || this.levelMap[n8][n9 - 1] == ':' || this.levelMap[n8 - 1][n9] == '.' || this.levelMap[n8 - 1][n9] == ':' || this.levelMap[n8][n9 + 1] == '.' || this.levelMap[n8][n9 + 1] == ':' || this.levelMap[n8 + 1][n9] == '.' || this.levelMap[n8 + 1][n9] == ':')) {
                        b5 = false;
                    }
                }
            }
            if (b5) {
                for (int n10 = 1; n10 < 19; ++n10) {
                    for (int n11 = 1; n11 < 19; ++n11) {
                        if (this.levelMap[n10][n11] == 'A') {
                            this.levelMap[n10][n11] = '+';
                            this.redrawAndSound(new Coord(n10, n11), 6);
                        }
                    }
                }
            }
            this.redrawCoords();
            if (this.timer >= this.maxtimer) {
                this.timer = 0;
            }
            ++this.timer;
            try {
                Thread.sleep(150L);
            }
            catch (InterruptedException ex2) {
                System.out.println("Interrupted");
            }
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.buttonPrev && this.level - 1 > 0) {
            this.score = this.accumulatedScore;
            this.setupLevel(--this.level);
        }
        else if (event.target == this.buttonRestart) {
            this.score = this.accumulatedScore;
            this.setupLevel(this.level);
        }
        else if (event.target == this.buttonNext && this.level + 1 < this.NUM_LEVELS + 1) {
            this.score = this.accumulatedScore;
            this.setupLevel(++this.level);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n > 0 && n < 400 && n2 > 440 && n2 < 464) {
            this.g.setFont(new Font("Helvetica", 3, 14));
            for (int i = 0; i < 3; ++i) {
                this.g.setColor(Color.magenta);
                this.g.fillRect(0, 440, 400, 24);
                try {
                    Thread.sleep(150L);
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                this.g.setColor(Color.blue);
                this.g.fillRect(0, 440, 400, 24);
                this.g.setColor(Color.yellow);
                this.g.drawString("Click here to visit this applet's homepage!", 45, 456);
                try {
                    Thread.sleep(150L);
                }
                catch (InterruptedException ex2) {
                    ex2.printStackTrace();
                }
            }
            try {
                this.getAppletContext().showDocument(new URL("http://www.ultranet.com/~dkoelle/Boulderdash"));
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.paused) {
            return false;
        }
        final Coord coord = new Coord();
        final Coord coord2 = new Coord();
        final Coord coord3 = new Coord();
        final Coord coord4 = new Coord();
        int n2 = 0;
        if (n == 1004) {
            coord.x = 0;
            coord.y = -1;
        }
        else if (n == 1007) {
            coord.x = 1;
            coord.y = 0;
        }
        else if (n == 1005) {
            coord.x = 0;
            coord.y = 1;
        }
        else if (n == 1006) {
            coord.x = -1;
            coord.y = 0;
        }
        else if (n == 27) {
            this.playerAlmostDead = true;
            this.levelMap[this.coordPlayer.x][this.coordPlayer.y] = '*';
            this.redrawAndSound(this.coordPlayer, 8);
        }
        coord3.x = this.coordPlayer.x + coord.x;
        coord3.y = this.coordPlayer.y + coord.y;
        if (coord3.x < 0) {
            coord3.x = 0;
        }
        else if (coord3.x > 20) {
            coord3.x = 20;
        }
        if (coord3.y < 0) {
            coord3.y = 0;
        }
        else if (coord3.y > 20) {
            coord3.y = 20;
        }
        coord2.x = coord.x * 2;
        coord2.y = coord.y * 2;
        coord4.x = this.coordPlayer.x + coord2.x;
        coord4.y = this.coordPlayer.y + coord2.y;
        if (this.levelMap[coord3.x][coord3.y] == '.' || this.levelMap[coord3.x][coord3.y] == ':') {
            if (this.levelMap[coord3.x][coord3.y] == '.') {
                this.redrawAndSound(coord3, 6);
            }
            else {
                this.redrawAndSound(coord3, 6);
            }
            n2 = 1;
        }
        else if (this.levelMap[coord3.x][coord3.y] == 'O' && coord4.y != coord3.y - 1) {
            if (coord4.x >= 0 && coord4.x <= 20 && coord4.y >= 0 && coord4.y <= 20 && this.levelMap[coord4.x][coord4.y] == '.') {
                this.levelMap[coord4.x][coord4.y] = this.levelMap[coord3.x][coord3.y];
                this.redrawAndSound(coord4, 1);
                this.redrawAndSound(coord3, 6);
                n2 = 1;
            }
        }
        else if (this.levelMap[coord3.x][coord3.y] == '+') {
            ++this.numFulfilledDiamonds;
            this.score += ((this.numFulfilledDiamonds < this.numNeededDiamonds) ? this.scorePerDiamond : this.scorePerExtraDiamond);
            this.updateScore();
            this.redrawAndSound(coord3, 2);
            if (this.numFulfilledDiamonds == this.numNeededDiamonds) {
                this.levelMap[this.coordExit.x][this.coordExit.y] = '[';
                this.redrawAndSound(this.coordExit, 3);
            }
            n2 = 1;
        }
        else if (this.levelMap[coord3.x][coord3.y] == '[') {
            this.redrawAndSound(coord3, 6);
            n2 = 1;
        }
        if (event.shiftDown() && n2 != 0) {
            this.levelMap[coord3.x][coord3.y] = '.';
            n2 = 0;
        }
        if (n2 != 0) {
            this.levelMap[coord3.x][coord3.y] = 'P';
            this.levelMap[this.coordPlayer.x][this.coordPlayer.y] = '.';
            this.drawCoord(this.coordPlayer.x, this.coordPlayer.y);
            this.coordPlayer.x = coord3.x;
            this.coordPlayer.y = coord3.y;
        }
        this.redrawCoords();
        if (this.coordPlayer.x == this.coordExit.x && this.coordPlayer.y == this.coordExit.y) {
            this.paused = true;
            this.levelDone = true;
            if (this.level == this.NUM_LEVELS) {
                this.niceBanner("All levels complete!");
            }
            else {
                this.niceBanner("Congratulations!");
            }
            try {
                Thread.sleep(2750L);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.accumulatedScore = this.score;
            this.paused = false;
            if (this.level + 1 < this.NUM_LEVELS + 1) {
                ++this.level;
            }
            else {
                this.level = 1;
            }
            this.setupLevel(this.level);
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                this.drawCoord(i, j);
            }
        }
        this.updateStatus();
    }
    
    public void updateStatus() {
        this.g.setColor(Color.lightGray);
        this.g.fillRect(0, 400, 400, 40);
        this.g.setColor(Color.black);
        this.g.setFont(new Font("Helvetica", 1, 14));
        this.g.drawString(this.levelTitle, 12, 412);
        this.g.setFont(new Font("Helvetica", 2, 12));
        this.g.drawString("by " + this.levelAuthor, 12, 426);
        this.g.setFont(new Font("Helvetica", 1, 10));
        this.g.drawString("Level " + this.level + " / " + this.NUM_LEVELS, 12, 438);
        this.g.setFont(new Font("Helvetica", 0, 12));
        this.g.drawString("Score: " + this.score, 200, 412);
        this.g.drawString("Diamonds: " + this.numFulfilledDiamonds + " / " + ((this.numFulfilledDiamonds < this.numNeededDiamonds) ? this.numNeededDiamonds : 0), 200, 424);
        this.g.drawString("Diamond Points: " + ((this.numFulfilledDiamonds < this.numNeededDiamonds) ? this.scorePerDiamond : this.scorePerExtraDiamond), 200, 436);
        this.g.setColor(Color.blue);
        this.g.fillRect(0, 440, 400, 24);
        this.g.setColor(Color.yellow);
        this.g.setFont(new Font("Helvetica", 3, 14));
        this.g.drawString("Click here to visit this applet's homepage!", 45, 456);
        this.g.setColor(Color.black);
        this.g.fillRect(0, 464, 400, 12);
        this.g.setColor(Color.white);
        this.g.setFont(new Font("Helvetica", 1, 10));
        this.g.drawString("Java Boulderdash  -  Version 1.0  -  ID 200009131200", 55, 474);
    }
    
    public void updateScore() {
        this.g.setColor(Color.lightGray);
        this.g.fillRect(200, 400, 400, 40);
        this.g.setColor(Color.black);
        this.g.setFont(new Font("Helvetica", 0, 12));
        this.g.drawString("Score: " + this.score, 200, 412);
        this.g.drawString("Diamonds: " + this.numFulfilledDiamonds + " / " + ((this.numFulfilledDiamonds < this.numNeededDiamonds) ? this.numNeededDiamonds : 0), 200, 424);
        this.g.drawString("Diamond Points: " + ((this.numFulfilledDiamonds < this.numNeededDiamonds) ? this.scorePerDiamond : this.scorePerExtraDiamond), 200, 436);
    }
    
    public void drawCoord(final int n, final int n2) {
        for (int i = 0; i < ".:#WMPO+*BFA][R".length(); ++i) {
            if (".:#WMPO+*BFA][R".charAt(i) == this.levelMap[n][n2]) {
                this.g.drawImage(this.img[i], n * 20, n2 * 20, null);
            }
        }
    }
    
    public void redrawCoords() {
        try {
            for (int i = 0; i < this.redraw.size(); ++i) {
                final Coord coord = this.redraw.elementAt(i);
                this.drawCoord(coord.x, coord.y);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.redraw.removeAllElements();
        this.sounds.removeAllElements();
    }
    
    public void redrawAndSound(final Coord coord, final int n) {
        this.redraw.addElement(coord);
    }
    
    public void niceBanner(final String s) {
        this.g.setColor(Color.darkGray);
        this.g.fillRect(40, 80, 320, 100);
        this.g.setColor(Color.blue);
        this.g.fillRect(60, 100, 280, 60);
        this.g.setColor(Color.yellow);
        this.g.setFont(new Font("Helvetica", 3, 20));
        this.g.drawString(s, 110, 135);
    }
    
    public String getAppletInfo() {
        return "Boulderdash Applet, (C) 2000 by David Koelle\nInspired by the game, \"Super Boulderdash\"\n\nPlease visit http://www.ultranet.com/~dkoelle/Boulderdash to find out\nhow to use and modify this applet!";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "LEVELS", "int", "Number of levels included in this .html file" }, { "LEVEL_NAME_x", "String", "Name of Level x" }, { "LEVEL_AUTHOR_x", "String", "Author of Level x" }, { "LEVEL_DATA_x_r", "String", "Map for Level x. 'r' is 1 - 20, representing a row of the map" }, { "NEEDED_DIAMONDS_x", "int", "Number of diamonds needed to open the exit for Level x" }, { "POINTS_PER_DIAMOND_x", "int", "Number of points awarded for getting a diamond for Level x" }, { "POINTS_PER_EXTRA_DIAMOND_x", "int", "Number of points awarded for getting a diamond after all needed diamonds have been obtained for Level x" }, { "IMAGEFILE_x", "String", "Filename of the image file to use for Level x" } };
    }
    
    static {
        Boulderdash.imgCoord = new int[][] { { 0, 0, 20, 20 }, { 20, 0, 20, 20 }, { 40, 0, 20, 20 }, { 0, 20, 20, 20 }, { 20, 20, 20, 20 }, { 40, 20, 20, 20 }, { 0, 40, 20, 20 }, { 20, 40, 20, 20 }, { 40, 40, 20, 20 }, { 0, 60, 20, 20 }, { 20, 60, 20, 20 }, { 40, 60, 20, 20 }, { 0, 80, 20, 20 }, { 20, 80, 20, 20 }, { 40, 80, 20, 20 } };
        Boulderdash.soundFilename = new String[] { "magicwall.wav", "rock.wav", "diamond.au", "openexit.wav", "amoeba.au", "smash.au", "null.au", "dirt.au", "playersmash.au", "died.wav", "congrats.wav", "complete.wav", "rock2.wav" };
    }
}
