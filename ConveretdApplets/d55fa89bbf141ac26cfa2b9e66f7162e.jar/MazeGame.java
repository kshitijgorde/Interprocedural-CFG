import java.awt.Point;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MazeGame extends Applet implements Runnable
{
    static final Color wallColor;
    static final Color pathColor;
    static final Color backColor;
    static final Color startColor;
    static final Color endColor;
    static final Color frameColor;
    static final Color bkgrndColor;
    static final Color panelColor;
    static final Color appletColor;
    static final int mcWidth = 410;
    static final int mcHeight = 310;
    static final int pcWidth = 150;
    static final int pcHeight = 80;
    static final int cpWidth = 150;
    static final int cpHeight = 210;
    private boolean playNewGame;
    private boolean gameOver;
    private int foodLeft;
    private Image food;
    private Image time;
    private Image home;
    private Image cheese;
    private Image ratImage;
    private Maze mz;
    private MazeCanvas mc;
    private PanelCanvas pc;
    private ControlPanel cp;
    private Thread mazeThread;
    private HungryRat rat;
    private int noOfRows;
    private int noOfCols;
    private int noOfSmellBlocks;
    
    public void init() {
        this.setLayout(null);
        this.setBackground(MazeGame.appletColor);
        (this.mc = new MazeCanvas(410, 310)).resize(410, 310);
        (this.pc = new PanelCanvas(150, 80)).resize(150, 80);
        this.mc.move(0, 0);
        this.pc.move(420, 0);
        (this.cp = new ControlPanel(this)).resize(150, 210);
        this.cp.move(430, 90);
        this.add(this.mc);
        this.add(this.pc);
        this.add(this.cp);
        this.validate();
        this.pc.init();
        this.mc.init();
        this.mc.loadingImages();
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.food = this.getImage(this.getDocumentBase(), "food.gif");
        this.time = this.getImage(this.getDocumentBase(), "time.gif");
        this.home = this.getImage(this.getDocumentBase(), "home.gif");
        this.cheese = this.getImage(this.getDocumentBase(), "nuts.gif");
        this.ratImage = this.getImage(this.getDocumentBase(), "rat.gif");
        mediaTracker.addImage(this.food, 0);
        mediaTracker.addImage(this.time, 1);
        mediaTracker.addImage(this.home, 2);
        mediaTracker.addImage(this.cheese, 3);
        mediaTracker.addImage(this.ratImage, 4);
        try {
            mediaTracker.waitForAll();
            final boolean b = !mediaTracker.isErrorAny();
        }
        catch (InterruptedException ex) {
            System.out.println("Unable to get all the images");
            return;
        }
        final int gameLevel = this.cp.getGameLevel();
        this.noOfRows = gameLevel;
        this.noOfCols = gameLevel;
        this.pc.drawStaticImage(this.food, this.time);
    }
    
    public void start() {
        if (this.mazeThread == null) {
            (this.mazeThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.mazeThread != null) {
            this.mazeThread.stop();
            this.mazeThread = null;
        }
        if (this.rat != null) {
            this.rat.stop();
        }
    }
    
    public void run() {
        while (true) {
            if (this.playNewGame) {
                this.startNewGame();
            }
            this.sleep(500);
        }
    }
    
    void generate() {
        this.mz = new MazeDFS(new Dimension(this.noOfRows, this.noOfCols), this.mc, this);
        this.mc.drawFresh(this.mz, this.home);
        (this.rat = new HungryRat(this.mz.getStart().x, this.mz.getStart().y, this.mc, this.mz, this.cp.getSensingPower(), this)).init();
    }
    
    void sleep(final int n) {
        try {
            Thread.currentThread();
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public boolean isGameOver() {
        return this.gameOver;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.isGameOver()) {
            return false;
        }
        final Dimension blockClicked = this.mc.blockClicked(n, n2);
        final int width = blockClicked.width;
        final int height = blockClicked.height;
        if (height >= this.noOfCols || width >= this.noOfRows) {
            return false;
        }
        if (width < 0 || height < 0) {
            return false;
        }
        this.rat.putFoodAt(new Point(width, height));
        this.foodLeft -= 20 * this.noOfSmellBlocks / this.noOfRows;
        this.updateGraphics();
        if (this.foodLeft <= 0) {
            this.gameLost();
        }
        return true;
    }
    
    public void updateGraphics() {
        this.pc.drawTimeBar(this.rat.getEnergy());
        this.pc.drawFoodBar(this.foodLeft);
        this.mc.drawAllRatAndFood(this.mz, this.home, this.rat.getRatPosition(), this.ratImage, this.rat.getFoodPosition(), this.cheese);
    }
    
    private void gameOver() {
        this.gameOver = true;
        this.foodLeft = 100;
        if (this.rat != null) {
            this.rat.gameOver();
            this.rat = null;
        }
    }
    
    public void gameWon() {
        this.gameOver();
        this.mc.gameWon();
    }
    
    public void gameLost() {
        this.gameOver();
        this.mc.gameLost();
    }
    
    public void makePlayAgain() {
        this.gameOver = true;
        this.playNewGame = true;
    }
    
    public void startNewGame() {
        if (this.rat != null) {
            this.rat.gameOver();
            this.rat = null;
        }
        if (this.mazeThread == null) {
            this.start();
        }
        final int gameLevel = this.cp.getGameLevel();
        this.noOfRows = gameLevel;
        this.noOfCols = gameLevel;
        this.noOfSmellBlocks = this.cp.getSensingPower();
        this.generate();
        this.playNewGame = false;
        int n = this.rat.getEnergy();
        this.pc.drawTimeBar(this.rat.getEnergy());
        this.pc.drawFoodBar(this.foodLeft);
        this.mc.drawAllRatAndFood(this.mz, this.home, this.rat.getRatPosition(), this.ratImage, this.rat.getFoodPosition(), this.cheese);
        this.gameOver = false;
        while (!this.gameOver) {
            this.sleep(200);
            if (this.rat == null) {
                break;
            }
            if (n != this.rat.getEnergy()) {
                n = this.rat.getEnergy();
                this.pc.drawTimeBar(n);
                if (n <= 0) {
                    this.gameLost();
                    return;
                }
            }
            if (this.rat.anyFoodNearBy(this.rat.getRatPosition(), this.noOfSmellBlocks).x != -1) {
                final Point point = new Point(this.rat.anyFoodNearBy(this.rat.getRatPosition(), this.noOfSmellBlocks).x, this.rat.anyFoodNearBy(this.rat.getRatPosition(), this.noOfSmellBlocks).y);
                this.rat.moveRatAt(point.x, point.y);
                this.rat.increaseEnergy();
                this.updateGraphics();
            }
            if (this.rat.getRatPosition().x == this.mz.getEnd().x && this.rat.getRatPosition().y == this.mz.getEnd().y) {
                this.gameWon();
                this.gameOver = true;
            }
        }
    }
    
    public MazeGame() {
        this.playNewGame = true;
        this.gameOver = false;
        this.foodLeft = 100;
    }
    
    static {
        wallColor = Color.white;
        pathColor = Color.yellow;
        backColor = Color.lightGray;
        startColor = Color.green;
        endColor = Color.red;
        frameColor = Color.gray;
        bkgrndColor = Color.darkGray;
        panelColor = Color.lightGray;
        appletColor = Color.black;
    }
}
