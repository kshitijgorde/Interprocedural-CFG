import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ani extends Applet
{
    int[][][] board;
    public static final int IMAGES = 11;
    Image[] tiles;
    boolean flippen;
    
    ani() {
        this.board = new int[12][16][4];
        this.tiles = new Image[11];
        this.flippen = false;
    }
    
    public boolean isFlipping() {
        return this.flippen;
    }
    
    public void setTiles(final Image[] tiles) {
        this.tiles = tiles;
    }
    
    public void setTri(final int n, final int n2, final int n3) {
        this.board[n][n2][0] = 2;
        this.board[n][n2][1] = n3;
        this.board[n][n2][2] = 40;
    }
    
    public void setFlip(final int n, final int n2, final int n3, final int n4) {
        this.board[n][n2][0] = 4;
        this.board[n][n2][1] = n3;
        this.board[n][n2][2] = n4;
        this.board[n][n2][3] = 0;
        this.flippen = true;
    }
    
    public void setHole(final int n, final int n2, final int n3) {
        this.board[n][n2][0] = 3;
        this.board[n][n2][1] = n3;
        this.board[n][n2][2] = 0;
    }
    
    public void draw(final Graphics graphics) {
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 16; ++j) {
                switch (this.board[i][j][0]) {
                    case 2: {
                        this.aniTri(i, j, graphics);
                        break;
                    }
                    case 3: {
                        this.aniHole(i, j, graphics);
                        break;
                    }
                    case 4: {
                        this.aniFlip(i, j, graphics);
                        break;
                    }
                }
            }
        }
    }
    
    public void aniTri(final int n, final int n2, final Graphics graphics) {
        final int n3 = n * 40;
        final int n4 = n2 * 40;
        graphics.drawImage(this.tiles[7], n3, n4, null);
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(n3 + (20 - this.board[n][n2][2] / 2), n4 + (20 - this.board[n][n2][2] / 2), this.board[n][n2][2], this.board[n][n2][2]);
        final int[] array = this.board[n][n2];
        final int n5 = 2;
        array[n5] -= this.board[n][n2][1];
        if (this.board[n][n2][2] < 0) {
            this.board[n][n2][0] = 0;
        }
    }
    
    public void aniHole(final int n, final int n2, final Graphics graphics) {
        final int n3 = n * 40;
        final int n4 = n2 * 40;
        graphics.drawImage(this.tiles[6], n3, n4, null);
        graphics.drawImage(this.tiles[this.board[n][n2][1]], n3 + this.board[n][n2][2], n4 + this.board[n][n2][2], 40 - this.board[n][n2][2] * 2, 40 - this.board[n][n2][2] * 2, this);
        final int[] array = this.board[n][n2];
        final int n5 = 2;
        ++array[n5];
        if (this.board[n][n2][2] > 20) {
            this.board[n][n2][0] = 0;
        }
    }
    
    public void aniFlip(final int n, final int n2, final Graphics graphics) {
        final int n3 = n * 40;
        final int n4 = n2 * 40;
        graphics.drawImage(this.tiles[0], n4, n3, null);
        final int abs = Math.abs(this.board[n][n2][3]);
        if (abs > 20) {
            final int n5 = 20 - (abs - 20);
            graphics.drawImage(this.tiles[this.board[n][n2][1]], n4 + n5, n3, 41 - n5 * 2, 41, this);
        }
        else {
            graphics.drawImage(this.tiles[this.board[n][n2][2]], n4 + abs, n3, 41 - abs * 2, 41, this);
        }
        final int[] array = this.board[n][n2];
        final int n6 = 3;
        ++array[n6];
        if (this.board[n][n2][3] > 40) {
            this.board[n][n2][0] = 0;
            this.flippen = false;
        }
    }
}
