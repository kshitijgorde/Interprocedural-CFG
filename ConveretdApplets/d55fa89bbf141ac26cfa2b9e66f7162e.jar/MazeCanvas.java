import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class MazeCanvas extends Canvas
{
    static final int BORDER = 10;
    static final int MAX_CELL_SIZE = 20;
    private int width;
    private int height;
    private int mzWidth;
    private int mzHeight;
    private int cellSize;
    private int wOffset;
    private int hOffset;
    private int rows;
    private int cols;
    private int pathSize;
    private int nwPathOffset;
    private int sePathOffset;
    Image bufferImage;
    Graphics buffer;
    MazeGame mg;
    private Font font;
    
    MazeCanvas(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    private void drawInit(final Maze maze) {
        this.cols = maze.getCols();
        this.rows = maze.getRows();
        this.cellSize = Math.min(20, Math.min((this.width - 10) / this.cols, (this.height - 10) / this.rows));
        this.mzWidth = this.cellSize * this.cols;
        this.mzHeight = this.cellSize * this.rows;
        this.wOffset = (this.width - this.mzWidth) / 2;
        this.hOffset = (this.height - this.mzHeight) / 2;
        this.pathSize = this.cellSize / 2 - 1;
        this.nwPathOffset = (this.cellSize - this.pathSize) / 2;
        this.sePathOffset = this.cellSize - this.pathSize - this.nwPathOffset;
        if (this.buffer == null) {
            this.bufferImage = this.createImage(this.width, this.height);
            this.buffer = this.bufferImage.getGraphics();
        }
        this.buffer.setColor(MazeGame.appletColor);
        this.buffer.fillRect(0, 0, this.width, this.height);
        this.buffer.setColor(MazeGame.bkgrndColor);
        this.buffer.fillRect(this.wOffset, this.hOffset, this.mzWidth, this.mzHeight);
        this.buffer.setColor(MazeGame.frameColor);
        this.buffer.draw3DRect(this.wOffset - 2, this.hOffset - 2, this.mzWidth + 4, this.mzHeight + 4, true);
        this.buffer.drawRect(this.wOffset - 1, this.hOffset - 1, this.mzWidth + 2, this.mzHeight + 2);
    }
    
    public void init() {
        if (this.buffer == null) {
            this.bufferImage = this.createImage(this.width, this.height);
            this.buffer = this.bufferImage.getGraphics();
        }
    }
    
    public void drawMaze(final Maze maze) {
        this.drawInit(maze);
        this.buffer.setColor(MazeGame.wallColor);
        for (int i = 0; i < this.cols; ++i) {
            for (int j = 0; j < this.rows; ++j) {
                if (j != 0 && (maze.getCell(i, j) & 0x1) != 0x0) {
                    this.buffer.drawLine(this.wOffset + i * this.cellSize, this.hOffset + j * this.cellSize, this.wOffset + (i + 1) * this.cellSize, this.hOffset + j * this.cellSize);
                }
                if (i != 0 && (maze.getCell(i, j) & 0x8) != 0x0) {
                    this.buffer.drawLine(this.wOffset + i * this.cellSize, this.hOffset + j * this.cellSize, this.wOffset + i * this.cellSize, this.hOffset + (j + 1) * this.cellSize);
                }
            }
        }
        this.buffer.setColor(MazeGame.frameColor);
        this.buffer.draw3DRect(this.wOffset, this.hOffset, this.mzWidth, this.mzHeight, false);
    }
    
    void drawhome(final Point point, final Image image) {
        if (this.buffer == null) {
            System.out.println("Buffer Null");
        }
        this.buffer.drawImage(image, point.x * this.cellSize + this.wOffset + 20, point.y * this.cellSize + this.hOffset - 10, this);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.bufferImage != null) {
            graphics.drawImage(this.bufferImage, 0, 0, this);
        }
    }
    
    public Dimension blockClicked(final int n, final int n2) {
        return new Dimension((n - this.wOffset) / this.cellSize, (n2 - this.hOffset) / this.cellSize);
    }
    
    public void drawFresh(final Maze maze, final Image image) {
        this.drawMaze(maze);
        this.drawhome(maze.getEnd(), image);
    }
    
    public synchronized void drawAllRatAndFood(final Maze maze, final Image image, final Point point, final Image image2, final int[][] array, final Image image3) {
        final int n = point.x * this.cellSize + this.wOffset + (this.cellSize - image2.getWidth(null)) / 2;
        final int n2 = point.y * this.cellSize + this.hOffset + (this.cellSize - image2.getHeight(null)) / 2;
        boolean b = false;
        this.drawFresh(maze, image);
        for (int i = 0; i < maze.getCols(); ++i) {
            for (int j = 0; j < maze.getRows(); ++j) {
                if (array[i][j] == 1) {
                    if (i == point.x && j == point.y) {
                        b = true;
                    }
                    this.buffer.drawImage(image3, i * this.cellSize + this.wOffset + (this.cellSize - image3.getWidth(null)) / 2, j * this.cellSize + this.hOffset + (this.cellSize - image3.getHeight(null)) / 2, this);
                }
            }
        }
        if (b) {
            this.buffer.setColor(MazeGame.bkgrndColor);
            this.buffer.fillRect(this.wOffset + point.x * this.cellSize + 1, this.hOffset + point.y * this.cellSize + 1, this.cellSize - 2, this.cellSize - 2);
        }
        this.buffer.drawImage(image2, n, n2, this);
        this.repaint();
    }
    
    public void gameWon() {
        this.buffer.setColor(MazeGame.appletColor);
        this.buffer.fillRect(0, 0, this.width, this.height);
        this.setFontData();
        this.buffer.setColor(Color.red);
        this.buffer.drawString("Game Won", 135, 50);
        this.gameCopyRight();
        this.repaint();
    }
    
    public void gameLost() {
        this.buffer.setColor(MazeGame.appletColor);
        this.buffer.fillRect(0, 0, this.width, this.height);
        this.setFontData();
        this.buffer.setColor(Color.red);
        this.buffer.drawString("Game Lost", 135, 50);
        this.gameCopyRight();
        this.repaint();
    }
    
    private void gameCopyRight() {
        this.buffer.setColor(Color.white);
        this.buffer.drawString("(c) Copyright -98", 110, 140);
        this.buffer.drawString("Kaushik Raj <kraj@syr.edu>", 60, 170);
        this.buffer.setColor(Color.yellow);
        this.buffer.drawString("http://web.syr.edu/~kraj/", 70, 250);
        this.repaint();
    }
    
    private void setFontData() {
        this.font = new Font("Times", 0, 20);
        this.buffer.setFont(this.font);
    }
    
    public void loadingImages() {
        this.buffer.setColor(MazeGame.appletColor);
        this.buffer.fillRect(0, 0, this.width, this.height);
        this.setFontData();
        this.buffer.setColor(Color.red);
        this.buffer.drawString("Loading Images", 135, 50);
        this.gameCopyRight();
        this.repaint();
    }
}
