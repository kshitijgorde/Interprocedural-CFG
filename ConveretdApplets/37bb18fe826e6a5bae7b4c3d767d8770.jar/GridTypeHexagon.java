import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridTypeHexagon extends GridType
{
    static final int icontilesize = 15;
    static final int icontileheight = 26;
    private int blockSize;
    private int blockHeight;
    private int offsetX;
    private int offsetY;
    private int widthX;
    private int widthY;
    private Coord firstShown;
    private Coord lastShown;
    private static final Coord border;
    
    public GridTypeHexagon() {
        this.blockSize = 0;
        this.firstShown = new Coord();
        this.lastShown = new Coord();
        this.numRotate = 6;
        this.numReflect = 12;
        this.numDim = 2;
        this.numTypes = 1;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        graphics.setColor(component.getBackground());
        graphics.fillRect(n, n2, this.width, this.height);
        graphics.setColor(component.getForeground());
        for (int i = n2 - 13; i < n2 + this.height; i += 52) {
            for (int j = n; j < n + this.width; j += 90) {
                graphics.drawLine(j, i, j + 15, i + 26);
                graphics.drawLine(j, i, j + 15, i - 26);
                graphics.drawLine(j + 15, i - 26, j + 45, i - 26);
                graphics.drawLine(j + 60, i, j + 45, i - 26);
                graphics.drawLine(j + 60, i, j + 45, i + 26);
                graphics.drawLine(j + 60, i, j + 90, i);
            }
        }
    }
    
    public void reset() {
        this.blockSize = 0;
    }
    
    public boolean screen2Grid(final Coord coord, int n, int n2) {
        if (this.blockSize == 0) {
            return false;
        }
        n -= this.offsetX;
        n2 -= this.offsetY;
        final int n3 = 0;
        final int n4 = 0;
        int n5 = n / (this.blockSize * 3);
        n -= n5 * (this.blockSize * 3);
        if (n < 0) {
            n += this.blockSize * 3;
            --n5;
        }
        n2 -= n5 * this.blockHeight;
        final int n6 = n4 + n5;
        int n7 = n2 / (this.blockHeight * 2);
        n2 -= n7 * (this.blockHeight * 2);
        if (n2 < 0) {
            n += this.blockHeight * 2;
            --n7;
        }
        int n8 = n3 + n7;
        int n9 = n6 + n7;
        if ((n - this.blockSize * 2) * this.blockHeight - n2 * this.blockSize > 0) {
            --n8;
        }
        else if ((n - this.blockSize * 4) * this.blockHeight + n2 * this.blockSize > 0) {
            ++n9;
        }
        if (n8 < 0 || n9 < 0) {
            return false;
        }
        final int n10 = n9 + this.firstShown.x;
        final int n11 = n8 + this.firstShown.y;
        if (n10 > this.lastShown.x || n11 > this.lastShown.y) {
            return false;
        }
        coord.set(n10, n11, 0);
        return true;
    }
    
    private void recalcSettings(final int width, final int height, final Board board, final boolean b) {
        if (width <= 0 || height <= 0) {
            this.blockSize = 0;
        }
        else {
            this.firstShown.set();
            this.lastShown.set();
            final Iterator iterator = board.getIterator();
            if (iterator.hasNext()) {
                final Map.Entry<Coord, V> entry = iterator.next();
                this.firstShown.set(entry.getKey());
                this.lastShown.set(entry.getKey());
                while (iterator.hasNext()) {
                    final Map.Entry<Coord, V> entry2 = iterator.next();
                    this.firstShown.min(entry2.getKey());
                    this.lastShown.max(entry2.getKey());
                }
            }
            if (b) {
                this.firstShown.sub(GridTypeHexagon.border);
                this.lastShown.add(GridTypeHexagon.border);
            }
            this.width = width;
            this.height = height;
            final int n = this.lastShown.x - this.firstShown.x + (this.lastShown.y - this.firstShown.y);
            this.blockSize = (width - 10) / (n * 3 + 4);
            this.blockHeight = (height - 10) / (n + 2);
            if (this.blockSize * 19 > this.blockHeight * 11) {
                this.blockSize = this.blockHeight * 11 / 19;
            }
            else {
                this.blockHeight = this.blockSize * 19 / 11;
            }
            if (this.blockSize < 3 || this.blockHeight < 5) {
                this.blockSize = 3;
                this.blockHeight = 5;
            }
            this.widthX = this.blockSize * (n * 3 + 4);
            this.widthY = this.blockHeight * (n + 2);
            this.offsetX = (width - this.widthX) / 2 + ((this.lastShown.y - this.firstShown.y) * 3 + 1) * this.blockSize;
            this.offsetY = (height - this.widthY) / 2;
        }
    }
    
    public void paintComponent(final Component component, final Graphics graphics, final Board board, final Color[] array, final boolean b) {
        if (this.blockSize == 0 || this.width != component.getWidth() || this.height != component.getHeight()) {
            this.recalcSettings(component.getWidth(), component.getHeight(), board, b);
            if (this.blockSize == 0) {
                return;
            }
        }
        final int[] array2 = new int[6];
        final int[] array3 = new int[6];
        int offsetX = this.offsetX;
        int offsetY = this.offsetY;
        int i = this.firstShown.y - 1;
        int n = offsetY;
        while (i <= this.lastShown.y) {
            for (int j = this.firstShown.x - 1, n2 = offsetX; j <= this.lastShown.x; ++j, n += this.blockHeight, n2 += this.blockSize * 3) {
                array2[0] = n2;
                array3[0] = n;
                array2[1] = n2 + this.blockSize + this.blockSize;
                array3[1] = n;
                array2[2] = n2 + this.blockSize * 3;
                array3[2] = n + this.blockHeight;
                array2[3] = n2 + this.blockSize + this.blockSize;
                array3[3] = n + this.blockHeight + this.blockHeight;
                array2[4] = n2;
                array3[4] = n + this.blockHeight + this.blockHeight;
                array2[5] = n2 - this.blockSize;
                array3[5] = n + this.blockHeight;
                final int contents = board.getContents(j + 1, i + 1, 0);
                if (contents >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents, component, array, b));
                    graphics.fillPolygon(array2, array3, 6);
                }
                if ((i != this.lastShown.y || j != this.firstShown.x - 1) && (i != this.firstShown.y - 1 || j != this.lastShown.x)) {
                    if (contents != board.getContents(j, i, 0)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                if (j < this.lastShown.x) {
                    if (contents != board.getContents(j + 1, i, 0)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                if (i < this.lastShown.y) {
                    if (contents != board.getContents(j, i + 1, 0)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[5], array3[5], array2[0], array3[0]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[5], array3[5], array2[0], array3[0]);
                    }
                }
            }
            ++i;
            offsetY += this.blockHeight;
            offsetX -= this.blockSize * 3;
            n = offsetY;
        }
    }
    
    public void paintCentredOutline(final Graphics graphics, final Coord[] array, final int n, final int n2, final int n3) {
        this.paintOutline(graphics, array, n - this.blockSize * 2, n2 - this.blockHeight, n3);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = n4 + (n - n2) * 3 * this.blockSize + this.blockSize * 2;
        final int n7 = n5 + (n + n2 + 1) * this.blockHeight;
        graphics.drawLine(n6 - this.blockSize, n7 - this.blockHeight, n6 + this.blockSize, n7 - this.blockHeight);
        graphics.drawLine(n6 + this.blockSize, n7 - this.blockHeight, n6 + this.blockSize * 2, n7);
        graphics.drawLine(n6 + this.blockSize * 2, n7, n6 + this.blockSize, n7 + this.blockHeight);
        graphics.drawLine(n6 + this.blockSize, n7 + this.blockHeight, n6 - this.blockSize, n7 + this.blockHeight);
        graphics.drawLine(n6 - this.blockSize, n7 + this.blockHeight, n6 - this.blockSize * 2, n7);
        graphics.drawLine(n6 - this.blockSize * 2, n7, n6 - this.blockSize, n7 - this.blockHeight);
    }
    
    public void paintOutline(final Graphics graphics, final Coord coord, final Coord coord2) {
        if (this.blockSize == 0) {
            return;
        }
        if (coord != null && coord2 != null) {
            final int n = (coord.x > coord2.x) ? coord.x : coord2.x;
            final int n2 = coord.x + coord2.x - n;
            final int n3 = (coord.y > coord2.y) ? coord.y : coord2.y;
            final int n4 = coord.y + coord2.y - n3;
            final int n5 = n2 - this.firstShown.x;
            final int n6 = n - this.firstShown.x;
            final int n7 = n4 - this.firstShown.y;
            final int n8 = n3 - this.firstShown.y;
            int n9 = this.offsetX + (n5 - n7) * this.blockSize * 3;
            int n10 = this.offsetY + (n5 + n7) * this.blockHeight;
            for (int i = n5; i <= n6; ++i, n10 += this.blockHeight, n9 += this.blockSize * 3) {
                graphics.drawLine(n9, n10, n9 + this.blockSize * 2, n10);
                graphics.drawLine(n9 + this.blockSize * 2, n10, n9 + this.blockSize * 3, n10 + this.blockHeight);
            }
            for (int j = n7; j <= n8; ++j, n10 += this.blockHeight, n9 -= this.blockSize * 3) {
                graphics.drawLine(n9, n10, n9 - this.blockSize, n10 + this.blockHeight);
                graphics.drawLine(n9 - this.blockSize, n10 + this.blockHeight, n9 - this.blockSize * 3, n10 + this.blockHeight);
            }
            for (int k = n5; k < n6; ++k, n10 -= this.blockHeight, n9 -= this.blockSize * 3) {
                graphics.drawLine(n9, n10, n9 - this.blockSize, n10 - this.blockHeight);
                graphics.drawLine(n9 - this.blockSize, n10 - this.blockHeight, n9 - this.blockSize * 3, n10 - this.blockHeight);
            }
            graphics.drawLine(n9, n10, n9 - this.blockSize, n10 - this.blockHeight);
            graphics.drawLine(n9 - this.blockSize, n10 - this.blockHeight, n9, n10 - this.blockHeight * 2);
            for (int n11 = n10 - this.blockHeight * 2, l = n7; l < n8; ++l, n11 -= this.blockHeight, n9 += this.blockSize * 3) {
                graphics.drawLine(n9, n11, n9 + this.blockSize * 2, n11);
                graphics.drawLine(n9 + this.blockSize * 2, n11, n9 + this.blockSize * 3, n11 - this.blockHeight);
            }
        }
    }
    
    public void getRotate(final Coord coord, int i) {
        if (i >= this.numRotate) {
            coord.x = coord.y - coord.x;
            i -= this.numRotate;
        }
        while (i > 0) {
            final int x = coord.x;
            coord.x = x - coord.y;
            coord.y = x;
            --i;
        }
    }
    
    public Coord getAdjacent(final Coord coord, final int n) {
        if (n < 0 || n >= 6) {
            return null;
        }
        final Coord coord2 = new Coord(coord);
        if (n == 0) {
            final Coord coord3 = coord2;
            --coord3.x;
        }
        else if (n == 1) {
            final Coord coord4 = coord2;
            --coord4.x;
            final Coord coord5 = coord2;
            --coord5.y;
        }
        else if (n == 2) {
            final Coord coord6 = coord2;
            --coord6.y;
        }
        else if (n == 3) {
            final Coord coord7 = coord2;
            ++coord7.x;
        }
        else if (n == 4) {
            final Coord coord8 = coord2;
            ++coord8.x;
            final Coord coord9 = coord2;
            ++coord9.y;
        }
        else if (n == 5) {
            final Coord coord10 = coord2;
            ++coord10.y;
        }
        return coord2;
    }
    
    static {
        border = new Coord(2, 2, 0);
    }
}
