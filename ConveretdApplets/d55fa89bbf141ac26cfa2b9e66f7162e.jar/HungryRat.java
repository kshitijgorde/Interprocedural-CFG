import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class HungryRat implements Runnable
{
    private int ratX;
    private int ratY;
    private int oldx;
    private int oldy;
    private Thread ratThread;
    private int click;
    private int foodclick;
    private final int RatReactionTime = 3;
    private final int noOfSmellBlocks;
    private final int noOfBlocks = 1;
    private MazeCanvas mc;
    private Maze mz;
    private MazeGame mg;
    private int[][] foodAt;
    private boolean gameOver;
    
    public HungryRat(final int n, final int n2, final MazeCanvas mc, final Maze mz, final int noOfSmellBlocks, final MazeGame mg) {
        this.foodclick = 100;
        this.gameOver = false;
        this.ratX = n;
        this.ratY = n2;
        this.oldx = n;
        this.oldy = n2;
        this.mc = mc;
        this.mz = mz;
        this.mg = mg;
        this.noOfSmellBlocks = noOfSmellBlocks;
        this.foodclick = 100;
        this.foodAt = new int[mz.getCols()][mz.getRows()];
        for (int i = 0; i < mz.getCols(); ++i) {
            for (int j = 0; j < mz.getRows(); ++j) {
                this.foodAt[i][j] = 0;
            }
        }
    }
    
    public synchronized void moveRatAt(final int ratX, final int ratY) {
        if (ratX >= this.mz.getCols() || ratY >= this.mz.getRows()) {
            return;
        }
        this.oldx = this.ratX;
        this.oldy = this.ratY;
        this.ratX = ratX;
        this.ratY = ratY;
        this.removeFoodFrom(new Point(ratX, ratY));
        this.click = 0;
    }
    
    public Point getRatPosition() {
        return new Point(this.ratX, this.ratY);
    }
    
    public Point getOldPosition() {
        return new Point(this.oldx, this.oldy);
    }
    
    public boolean isReachable(final int n, final int n2) {
        return this.mz.isReachable(new Point(this.ratX, this.ratY), new Point(n, n2), 1);
    }
    
    public void run() {
        while (!this.gameOver) {
            ++this.click;
            this.sleep(1000);
            if (this.anyFoodNearBy(this.getRatPosition(), this.noOfSmellBlocks).x != -1) {
                final Point point = new Point(this.anyFoodNearBy(this.getRatPosition(), this.noOfSmellBlocks).x, this.anyFoodNearBy(this.getRatPosition(), this.noOfSmellBlocks).y);
                this.moveRatAt(point.x, point.y);
                this.increaseEnergy();
                this.mg.updateGraphics();
                this.click = 0;
            }
            if (this.click >= 3) {
                this.click = 0;
                this.decreaseEnergy();
                int n;
                int n2;
                do {
                    n = (int)(Math.random() * 10.0 % 2.0);
                    n2 = (int)(Math.random() * 10.0 % 2.0);
                    if (this.randomBoolean()) {
                        n *= -1;
                    }
                    if (this.randomBoolean()) {
                        n2 *= -1;
                    }
                } while ((n == 0 && n2 == 0) || !this.isReachable(this.getRatPosition().x + n, this.getRatPosition().y + n2));
                this.moveRatAt(this.getRatPosition().x + n, this.getRatPosition().y + n2);
                this.mg.updateGraphics();
            }
            if (this.foodclick <= 0) {
                this.mg.gameLost();
                this.gameOver = true;
            }
        }
    }
    
    public void init() {
        this.gameOver = false;
        if (this.ratThread == null) {
            (this.ratThread = new Thread(this)).start();
            System.out.println("ratThread started");
        }
    }
    
    public void stop() {
        this.gameOver = true;
        if (this.ratThread != null) {
            this.ratThread.stop();
            this.ratThread = null;
            System.out.println("ratThread stop");
        }
    }
    
    public void sleep(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {}
    }
    
    public boolean randomBoolean() {
        return (int)(Math.random() * 10.0 % 2.0) == 1;
    }
    
    public boolean hasChanged() {
        boolean b = false;
        if (this.oldx != this.ratX) {
            b = true;
        }
        if (this.oldy != this.ratY) {
            b = true;
        }
        return b;
    }
    
    public void putFoodAt(final Point point) {
        this.foodAt[point.x][point.y] = 1;
    }
    
    public boolean removeFoodFrom(final Point point) {
        if (this.foodAt[point.x][point.y] == 0) {
            return false;
        }
        this.foodAt[point.x][point.y] = 0;
        return true;
    }
    
    public Point anyFoodNearBy(final Point point, final int n) {
        int n2 = 9999;
        Point point2 = new Point(-1, -1);
        if (this.foodAt[point.x][point.y] == 1) {
            return point;
        }
        for (int i = -n; i < n + 1; ++i) {
            for (int j = -n; j < n + 1; ++j) {
                if (point.x + i >= 0 && point.y + j >= 0 && point.x + i < this.mz.getCols() && point.y + j < this.mz.getRows() && this.foodAt[point.x + i][point.y + j] == 1 && this.mz.isReachable(point, new Point(point.x + i, point.y + j), n)) {
                    final int path = this.mz.getPath(point, new Point(point.x + i, point.y + j));
                    if (path < n2) {
                        n2 = path;
                        point2 = new Point(point.x + i, point.y + j);
                    }
                }
            }
        }
        return point2;
    }
    
    public void gameOver() {
        this.gameOver = true;
        this.stop();
    }
    
    public void increaseEnergy() {
        this.foodclick += 10;
        if (this.foodclick > 100) {
            this.foodclick = 100;
        }
    }
    
    public void decreaseEnergy() {
        this.foodclick -= 10;
    }
    
    public int getEnergy() {
        return this.foodclick;
    }
    
    public int getTimeLeft() {
        return (3 - this.click) * 100 / 3;
    }
    
    public int[][] getFoodPosition() {
        return this.foodAt;
    }
}
