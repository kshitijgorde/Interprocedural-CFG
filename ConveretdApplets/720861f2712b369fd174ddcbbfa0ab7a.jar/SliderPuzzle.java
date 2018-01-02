import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SliderPuzzle extends Applet
{
    int[] divs;
    int[] randomSquares;
    int diff;
    int xCut;
    int yCut;
    int xLoc;
    int yLoc;
    boolean won;
    Image puzzle;
    Image bg;
    Image[][] cutPuzzle;
    Point[][] grid;
    
    public void getRandomValues(int numVals) {
        (this.randomSquares = new int[numVals])[numVals - 1] = numVals - 1;
        final boolean[] isNew = new boolean[--numVals];
        for (int i = 0; i < numVals; ++i) {
            isNew[i] = false;
        }
        for (int i = 0; i < numVals; ++i) {
            float rNum;
            int temp;
            for (rNum = (float)Math.random(), temp = (int)(rNum * numVals); isNew[temp]; temp = (int)(rNum * numVals)) {
                rNum = (float)Math.random();
            }
            isNew[temp] = true;
            this.randomSquares[i] = temp;
        }
    }
    
    private void cutImage() {
        final MediaTracker mt = new MediaTracker(this);
        this.cutPuzzle = new Image[this.divs[this.diff]][this.divs[this.diff]];
        this.xCut = this.puzzle.getWidth(null) / this.divs[this.diff];
        this.yCut = this.puzzle.getHeight(null) / 2 / this.divs[this.diff];
        final ImageProducer allProducer = this.puzzle.getSource();
        for (int x = 0; x < this.divs[this.diff]; ++x) {
            for (int y = 0; y < this.divs[this.diff]; ++y) {
                final ImageFilter filter = new CropImageFilter(x * this.xCut, y * this.yCut, this.xCut, this.yCut);
                mt.addImage(this.cutPuzzle[x][y] = this.createImage(new FilteredImageSource(allProducer, filter)), 0);
            }
        }
        final ImageFilter filter = new CropImageFilter(0, this.puzzle.getHeight(null) / 2, this.puzzle.getWidth(null), this.puzzle.getHeight(null) / 2);
        mt.addImage(this.bg = this.createImage(new FilteredImageSource(allProducer, filter)), 0);
        try {
            mt.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.getRandomValues(this.divs[this.diff] * this.divs[this.diff]);
        this.grid = new Point[this.divs[this.diff]][this.divs[this.diff]];
        for (int x = 0; x < this.divs[this.diff]; ++x) {
            for (int y = 0; y < this.divs[this.diff]; ++y) {
                final int rnum = this.randomSquares[this.divs[this.diff] * x + y];
                final int indexY = rnum / this.divs[this.diff];
                final int indexX = rnum - this.divs[this.diff] * indexY;
                try {
                    if (x == this.divs[this.diff] - 1 && y == this.divs[this.diff] - 1) {
                        this.grid[x][y] = null;
                    }
                    else {
                        this.grid[x][y] = new Point(indexX, indexY);
                        System.out.println("Create (" + String.valueOf(x) + "," + String.valueOf(y) + ") with square (" + String.valueOf(indexX) + "," + String.valueOf(indexY) + ")");
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("X: " + String.valueOf(indexX) + " Y: " + String.valueOf(indexY));
                }
            }
        }
        System.out.println("*******************");
    }
    
    private void checkForWin() {
        boolean isWin = true;
        for (int x = 0; x < this.divs[this.diff]; ++x) {
            for (int y = 0; y < this.divs[this.diff]; ++y) {
                if (this.grid[x][y] != null && (this.grid[x][y].x != x || this.grid[x][y].y != y)) {
                    isWin = false;
                    break;
                }
            }
            if (!isWin) {
                break;
            }
        }
        this.won = isWin;
    }
    
    public void update(final Graphics g) {
        g.drawImage(this.bg, this.xLoc, this.yLoc, null);
        if (this.won) {
            for (int x = 0; x < this.divs[this.diff]; ++x) {
                for (int y = 0; y < this.divs[this.diff]; ++y) {
                    g.drawImage(this.cutPuzzle[x][y], this.xLoc + x * this.xCut, this.yLoc + y * this.yCut, null);
                }
            }
        }
        else {
            for (int x = 0; x < this.divs[this.diff]; ++x) {
                for (int y = 0; y < this.divs[this.diff]; ++y) {
                    if (this.grid[x][y] != null) {
                        g.drawImage(this.cutPuzzle[this.grid[x][y].x][this.grid[x][y].y], this.xLoc + x * this.xCut, this.yLoc + y * this.yCut, null);
                    }
                }
            }
            for (int x = 0; x <= this.divs[this.diff]; ++x) {
                for (int y = 0; y <= this.divs[this.diff]; ++y) {
                    g.setColor(Color.white);
                    g.drawLine(this.xLoc + x * this.xCut, this.yLoc, this.xLoc + x * this.xCut, this.yLoc + this.puzzle.getHeight(null) / 2);
                    g.drawLine(this.xLoc, this.yLoc + y * this.yCut, this.xLoc + this.puzzle.getWidth(null), this.yLoc + y * this.yCut);
                }
            }
        }
    }
    
    public void mouseClick(final int mX, final int mY) {
        final int[] yLocs = { -1, 0, 1, 0 };
        final int[] xLocs = { 0, 1, 0, -1 };
        int ySquare = 0;
        int xSquare = 0;
        if (!this.won) {
            xSquare = (mX - this.xLoc) / this.xCut;
            ySquare = (mY - this.yLoc) / this.yCut;
            System.out.println("Computing Square: (" + String.valueOf(xSquare) + "," + String.valueOf(ySquare) + ")");
            if (this.grid[ySquare][xSquare] != null) {
                int i = 0;
                do {
                    if (xSquare + xLocs[i] < 0 || xSquare + xLocs[i] >= this.divs[this.diff] || ySquare + yLocs[i] < 0 || ySquare + yLocs[i] >= this.divs[this.diff]) {
                        System.out.println("Index outta range on: (" + String.valueOf(xSquare + xLocs[i]) + "," + String.valueOf(ySquare + yLocs[i]) + ")");
                    }
                    else {
                        if (this.grid[xSquare + xLocs[i]][ySquare + yLocs[i]] == null) {
                            System.out.println("Switched: " + String.valueOf(xSquare) + "," + String.valueOf(ySquare) + " with " + String.valueOf(xSquare + xLocs[i]) + "," + String.valueOf(ySquare + yLocs[i]) + " i: " + String.valueOf(i));
                            this.grid[xSquare + xLocs[i]][ySquare + yLocs[i]] = this.grid[xSquare][ySquare];
                            this.grid[xSquare][ySquare] = null;
                            break;
                        }
                        continue;
                    }
                } while (++i < 4);
            }
            this.checkForWin();
        }
    }
    
    SliderPuzzle(final Image img, final int dif, final int x1, final int y1, final int x2, final int y2) {
        this.divs = new int[] { 3, 6, 9 };
        System.out.println("***** NEW RUN *****");
        this.puzzle = img;
        this.diff = dif - 1;
        this.xLoc = (x2 - x1) / 2 + x1 - img.getWidth(null) / 2;
        this.yLoc = (y2 - y1) / 2 + y1 - img.getHeight(null) / 4;
        System.out.println("Position: (" + String.valueOf(this.xLoc) + "," + String.valueOf(this.yLoc) + ")");
        this.cutImage();
    }
    
    public void solvePuzzle() {
        this.won = true;
    }
}
