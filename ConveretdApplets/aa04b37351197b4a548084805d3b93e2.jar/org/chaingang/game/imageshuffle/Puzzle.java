// 
// Decompiled by Procyon v0.5.30
// 

package org.chaingang.game.imageshuffle;

import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Cursor;
import java.util.Random;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Puzzle extends JPanel implements MouseListener, MouseMotionListener
{
    private Image imgComplete;
    private int cols;
    private int rows;
    private int gutter;
    private int border;
    private Dimension totalSize;
    private Image[][] imgTile;
    private Rectangle[][] grid;
    private GridPos gridPos;
    private Point emptyPiece;
    private Random rand;
    private Cursor cursorDef;
    private Cursor cursorSelect;
    private Cursor cursorCurrent;
    private Color emptyColor;
    private boolean playState;
    private ActionListener parentListener;
    
    public Puzzle(final ActionListener parentListener, final Image imgComplete, final int cols, final int rows, final Color emptyColor, final Color background) {
        this.gutter = 1;
        this.border = 3;
        this.playState = false;
        this.parentListener = parentListener;
        this.imgComplete = imgComplete;
        this.cols = cols;
        this.rows = rows;
        this.emptyColor = emptyColor;
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.gridPos = new GridPos(cols, rows);
        final Dimension dimension = new Dimension(imgComplete.getWidth(null), imgComplete.getHeight(null));
        final Dimension dimension2 = new Dimension((dimension.width - this.gutter * cols) / cols, (dimension.height - this.gutter * rows) / rows);
        final Image imageSlice = this.getImageSlice(new Rectangle(0, 0, dimension.width / cols * cols - this.gutter * 2, dimension.height / rows * rows - this.gutter * 2), mediaTracker);
        this.waitForSlice(mediaTracker);
        this.imgComplete = imageSlice;
        this.totalSize = new Dimension(dimension2.width * cols + this.gutter * (cols - 1) + this.border * 2, dimension2.height * rows + this.gutter * (rows - 1) + this.border * 2);
        this.cursorDef = Cursor.getPredefinedCursor(0);
        this.cursorSelect = Cursor.getPredefinedCursor(12);
        this.setCursor(this.cursorCurrent = this.cursorDef);
        this.imgTile = new Image[cols][rows];
        this.grid = new Rectangle[cols][rows];
        for (int i = 0; i < cols; ++i) {
            for (int j = 0; j < rows; ++j) {
                final Rectangle rectangle = new Rectangle(i * dimension2.width + i * this.gutter, j * dimension2.height + j * this.gutter, dimension2.width - 1, dimension2.height - 1);
                this.grid[i][j] = new Rectangle(rectangle.x + this.border, rectangle.y + this.border, rectangle.width, rectangle.height);
                this.imgTile[i][j] = this.getImageSlice(rectangle, mediaTracker);
            }
        }
        this.waitForSlice(mediaTracker);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setPreferredSize(this.totalSize);
        this.setMaximumSize(this.totalSize);
        this.setMinimumSize(this.totalSize);
        this.setBackground(background);
    }
    
    public void setPlayState(final boolean playState) {
        this.playState = playState;
        this.setCursor(this.cursorCurrent = this.cursorDef);
        this.repaint(0L, 0, 0, this.getWidth(), this.getHeight());
    }
    
    public boolean getPlayState() {
        return this.playState;
    }
    
    public int getMoveCount() {
        return this.gridPos.getMoveCount();
    }
    
    public Point getPieceClicked(final Point point) {
        for (int i = 0; i < this.cols; ++i) {
            for (int j = 0; j < this.rows; ++j) {
                if (this.grid[i][j].contains(point)) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }
    
    private void drawTile(final Graphics graphics, final int n, final int n2) {
        final Rectangle rectangle = this.grid[n][n2];
        final Point currentPiece = this.gridPos.getCurrentPiece(n, n2);
        if (currentPiece.x == this.cols - 1 && currentPiece.y == this.rows - 1) {
            graphics.setColor(this.emptyColor);
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        else {
            graphics.drawImage(this.imgTile[currentPiece.x][currentPiece.y], rectangle.x, rectangle.y, this);
        }
    }
    
    private void drawTiles(final Graphics graphics) {
        for (int i = 0; i < this.cols; ++i) {
            for (int j = 0; j < this.rows; ++j) {
                this.drawTile(graphics, i, j);
            }
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.playState) {
            this.drawTiles(graphics);
        }
        else {
            graphics.drawImage(this.imgComplete, this.border, this.border, this);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.playState) {
            return;
        }
        final Point pieceClicked = this.getPieceClicked(mouseEvent.getPoint());
        if (this.gridPos.isLive(pieceClicked)) {
            final Rectangle rectangle = new Rectangle(this.grid[pieceClicked.x][pieceClicked.y]);
            rectangle.add(this.grid[this.gridPos.emptyPiece.x][this.gridPos.emptyPiece.y]);
            this.gridPos.move(pieceClicked);
            this.setCursor(this.cursorCurrent = this.cursorDef);
            if (this.gridPos.isWin()) {
                this.parentListener.actionPerformed(new ActionEvent(mouseEvent.getSource(), 0, "c_win"));
                this.repaint(0L, 0, 0, this.getWidth(), this.getHeight());
            }
            else {
                this.repaint(0L, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (!this.playState) {
            return;
        }
        final Cursor cursor = this.gridPos.isLive(this.getPieceClicked(mouseEvent.getPoint())) ? this.cursorSelect : this.cursorDef;
        if (cursor != this.cursorCurrent) {
            this.setCursor(cursor);
            this.cursorCurrent = cursor;
        }
    }
    
    public void cmdShuffle() {
        this.gridPos.shuffle();
        this.setPlayState(true);
    }
    
    private Image getImageSlice(final Rectangle rectangle, final MediaTracker mediaTracker) {
        final Image image = this.createImage(new FilteredImageSource(this.imgComplete.getSource(), new CropImageFilter(rectangle.x, rectangle.y, rectangle.width, rectangle.height)));
        mediaTracker.addImage(image, 2);
        return image;
    }
    
    private void waitForSlice(final MediaTracker mediaTracker) {
        try {
            mediaTracker.waitForID(2);
        }
        catch (InterruptedException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }
}
