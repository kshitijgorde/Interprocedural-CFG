// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.applets.joggle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Hashtable;
import com.guymcarthur.Debuggable;
import java.awt.Canvas;

public class JoggleCanvas extends Canvas implements Debuggable
{
    private JoggleBoard board;
    private Hashtable images;
    private int cellWidth;
    private int cellHeight;
    private Rectangle[][] rects;
    private int width;
    private int height;
    private boolean debug;
    
    public boolean isDebug() {
        return this.debug;
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public void paint(final Graphics graphics) {
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        for (int i = 0; i < this.board.rows; ++i) {
            for (int j = 0; j < this.board.cols; ++j) {
                graphics.drawImage((Image)this.images.get(this.board.getValue(i, j)), i * this.width / this.board.rows, j * this.height / this.board.cols, this);
            }
        }
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMaximumSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMinimumSize() {
        final Image image = this.images.elements().nextElement();
        return new Dimension(this.board.rows * image.getWidth(this), this.board.cols * image.getHeight(this));
    }
    
    public int[] getCell(final int n, final int n2) {
        for (int i = 0; i < this.board.rows; ++i) {
            for (int j = 0; j < this.board.cols; ++j) {
                if (this.rects[i][j].contains(n, n2)) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { -1, -1 };
    }
    
    public void selectCell(final int n, final int n2, final Color color) {
        final Rectangle rectangle = this.rects[n][n2];
        final Graphics graphics = this.getGraphics();
        graphics.setColor(color);
        graphics.drawRect(rectangle.x + 4, rectangle.y + 3, rectangle.width - 7, rectangle.height - 7);
    }
    
    public JoggleCanvas(final JoggleBoard board, final Hashtable images) {
        this.debug = false;
        this.board = board;
        this.images = images;
        final Image image = images.elements().nextElement();
        this.cellHeight = image.getHeight(this);
        this.cellWidth = image.getWidth(this);
        this.rects = new Rectangle[board.rows][board.cols];
        for (int i = 0; i < board.rows; ++i) {
            for (int j = 0; j < board.cols; ++j) {
                this.rects[i][j] = new Rectangle(i * this.cellWidth, j * this.cellHeight, this.cellWidth, this.cellHeight);
            }
        }
        if (this.debug) {
            System.out.println("Preferred size = " + this.getPreferredSize());
        }
    }
}
