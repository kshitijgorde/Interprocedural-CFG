// 
// Decompiled by Procyon v0.5.30
// 

package org.chaingang.game.imageshuffle;

import java.awt.Rectangle;
import java.util.Random;
import java.awt.Point;

public class GridPos
{
    public int cols;
    public int rows;
    Point[][] currentPos;
    Point emptyPiece;
    private Random rand;
    private int moveCount;
    private Rectangle lastSwap;
    
    public GridPos(final int cols, final int rows) {
        this.cols = cols;
        this.rows = rows;
        this.currentPos = new Point[cols][rows];
        this.rand = new Random();
        this.shuffle();
    }
    
    public Point getCurrentPiece(final Point point) {
        return this.currentPos[point.x][point.y];
    }
    
    public Point getCurrentPiece(final int n, final int n2) {
        return this.currentPos[n][n2];
    }
    
    public int getMoveCount() {
        return this.moveCount;
    }
    
    public boolean isWin() {
        for (int i = 0; i < this.cols; ++i) {
            for (int j = 0; j < this.rows; ++j) {
                final Point point = this.currentPos[i][j];
                if (point.x != i || point.y != j) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isLive(final Point point) {
        return point != null && ((this.emptyPiece.x == point.x && (this.emptyPiece.y == point.y - 1 || this.emptyPiece.y == point.y + 1)) || (this.emptyPiece.y == point.y && (this.emptyPiece.x == point.x - 1 || this.emptyPiece.x == point.x + 1)));
    }
    
    public Point[] getLiveList() {
        final Point[] array = new Point[4];
        int n = 0;
        if (this.emptyPiece.x > 0) {
            array[n++] = new Point(this.emptyPiece.x - 1, this.emptyPiece.y);
        }
        if (this.emptyPiece.x < this.cols - 1) {
            array[n++] = new Point(this.emptyPiece.x + 1, this.emptyPiece.y);
        }
        if (this.emptyPiece.y > 0) {
            array[n++] = new Point(this.emptyPiece.x, this.emptyPiece.y - 1);
        }
        if (this.emptyPiece.y < this.rows - 1) {
            array[n++] = new Point(this.emptyPiece.x, this.emptyPiece.y + 1);
        }
        final Point[] array2 = new Point[n];
        for (int i = 0; i < n; ++i) {
            array2[i] = array[i];
        }
        return array2;
    }
    
    public Point[] subtractPointFromList(final Point[] array, final Point point) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals(point)) {
                final Point[] array2 = new Point[array.length - 1];
                int n = 0;
                for (int j = 0; j < array.length; ++j) {
                    if (i != j) {
                        array2[n++] = array[j];
                    }
                }
                return array2;
            }
        }
        return array;
    }
    
    public void move(final Point point, final Point point2) {
        ++this.moveCount;
        final Point point3 = this.currentPos[point.x][point.y];
        this.currentPos[point.x][point.y] = this.currentPos[point2.x][point2.y];
        this.currentPos[point2.x][point2.y] = point3;
    }
    
    public void move(final Point emptyPiece) {
        this.move(emptyPiece, this.emptyPiece);
        this.emptyPiece = emptyPiece;
    }
    
    public void shuffle() {
        int n = 1000;
        Point point = this.emptyPiece;
        final Point[] array = new Point[4];
        for (int i = 0; i < this.cols; ++i) {
            for (int j = 0; j < this.rows; ++j) {
                this.currentPos[i][j] = new Point(i, j);
            }
        }
        this.emptyPiece = new Point(this.cols - 1, this.rows - 1);
        while (n-- > 0) {
            final Point[] subtractPointFromList = this.subtractPointFromList(this.getLiveList(), point);
            point = this.emptyPiece;
            this.move(subtractPointFromList[this.rand.nextInt(subtractPointFromList.length)]);
        }
        this.moveCount = 0;
    }
}
