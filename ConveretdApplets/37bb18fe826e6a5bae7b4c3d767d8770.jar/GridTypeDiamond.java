import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridTypeDiamond extends GridType
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
    
    public GridTypeDiamond() {
        this.blockSize = 0;
        this.firstShown = new Coord();
        this.lastShown = new Coord();
        this.numRotate = 2;
        this.numReflect = 4;
        this.numDim = 2;
        this.numTypes = 1;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        graphics.setColor(component.getBackground());
        graphics.fillRect(n, n2, this.width, this.height);
        graphics.setColor(component.getForeground());
        for (int i = n2 - 13; i < n2 + this.height; i += 52) {
            graphics.drawLine(n, i, n + this.width, i);
            graphics.drawLine(n, i + 26, n + this.width, i + 26);
            for (int j = n; j < n + this.width; j += 30) {
                graphics.drawLine(j + 30, i, j, i + 52);
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
        int n5 = n2 / this.blockHeight;
        n2 -= n5 * this.blockHeight;
        if (n2 < 0) {
            n2 += this.blockHeight;
            --n5;
        }
        n += n5 * this.blockSize;
        final int n6 = n3 + n5;
        int n7 = n / (this.blockSize * 2);
        n -= n7 * (this.blockSize * 2);
        if (n < 0) {
            n += this.blockSize * 2;
            --n7;
        }
        int n8 = n4 + n7;
        if ((n - this.blockSize * 2) * this.blockHeight + n2 * this.blockSize > 0) {
            ++n8;
        }
        if (n6 < 0 || n8 < 0) {
            return false;
        }
        final int n9 = n8 + this.firstShown.x;
        final int n10 = n6 + this.firstShown.y;
        if (n9 > this.lastShown.x || n10 > this.lastShown.y) {
            return false;
        }
        coord.set(n9, n10, 0);
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
                this.firstShown.sub(GridTypeDiamond.border);
                this.lastShown.add(GridTypeDiamond.border);
            }
            this.width = width;
            this.height = height;
            final int n = (this.lastShown.x - this.firstShown.x + 1) * 2 + (this.lastShown.y - this.firstShown.y + 1);
            final int n2 = this.lastShown.y - this.firstShown.y + 1;
            this.blockSize = (width - 10) / n;
            this.blockHeight = (height - 10) / n2;
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
            this.widthX = this.blockSize * n;
            this.widthY = this.blockHeight * n2;
            this.offsetX = (width - this.widthX) / 2 + (this.lastShown.y - this.firstShown.y + 1) * this.blockSize;
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
        final int[] array2 = new int[4];
        final int[] array3 = new int[4];
        for (int offsetX = this.offsetX, i = this.firstShown.y - 1, offsetY = this.offsetY; i <= this.lastShown.y; ++i, offsetY += this.blockHeight, offsetX -= this.blockSize) {
            for (int j = this.firstShown.x - 1, n = offsetX; j <= this.lastShown.x; ++j, n += this.blockSize * 2) {
                array2[0] = n;
                array3[0] = offsetY;
                array2[1] = n + this.blockSize * 2;
                array3[1] = offsetY;
                array2[2] = n + this.blockSize;
                array3[2] = offsetY + this.blockHeight;
                array2[3] = n - this.blockSize;
                array3[3] = offsetY + this.blockHeight;
                final int contents = board.getContents(j + 1, i + 1, 0);
                if (contents >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents, component, array, b));
                    graphics.fillPolygon(array2, array3, 4);
                }
                if (i < this.lastShown.y) {
                    if (contents != board.getContents(j, i + 1, 0)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[3], array3[3]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[3], array3[3]);
                    }
                }
                if (j < this.lastShown.x) {
                    if (contents != board.getContents(j + 1, i, 0)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
            }
        }
    }
    
    public void paintCentredOutline(final Graphics graphics, final Coord[] array, final int n, final int n2, final int n3) {
        this.paintOutline(graphics, array, n - this.blockSize / 2, n2 - this.blockHeight / 2, n3);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = n4 + (n * 2 - n2) * this.blockSize;
        final int n7 = n5 + n2 * this.blockHeight;
        graphics.drawLine(n6, n7, n6 + this.blockSize * 2, n7);
        graphics.drawLine(n6 + this.blockSize * 2, n7, n6 + this.blockSize, n7 + this.blockHeight);
        graphics.drawLine(n6 + this.blockSize, n7 + this.blockHeight, n6 - this.blockSize, n7 + this.blockHeight);
        graphics.drawLine(n6 - this.blockSize, n7 + this.blockHeight, n6, n7);
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
            final int n9 = n6 - n5 + 1;
            final int n10 = n8 - n7 + 1;
            final int n11 = this.offsetX + (n5 * 2 - n7) * this.blockSize;
            final int n12 = this.offsetY + n7 * this.blockHeight;
            graphics.drawLine(n11, n12, n11 + this.blockSize * 2 * n9, n12);
            graphics.drawLine(n11 + this.blockSize * 2 * n9, n12, n11 + (2 * n9 - n10) * this.blockSize, n12 + n10 * this.blockHeight);
            graphics.drawLine(n11 + (2 * n9 - n10) * this.blockSize, n12 + n10 * this.blockHeight, n11 - n10 * this.blockSize, n12 + n10 * this.blockHeight);
            graphics.drawLine(n11 - n10 * this.blockSize, n12 + n10 * this.blockHeight, n11, n12);
        }
    }
    
    public void getRotate(final Coord coord, int n) {
        if (n >= this.numRotate) {
            final int x = coord.x;
            coord.x = coord.y;
            coord.y = x;
            n -= this.numRotate;
        }
        if (n > 0) {
            coord.x = -coord.x;
            coord.y = -coord.y;
        }
    }
    
    public Coord getAdjacent(final Coord coord, final int n) {
        if (n < 0 || n >= 4) {
            return null;
        }
        final Coord coord2 = new Coord(coord);
        if (n == 0) {
            final Coord coord3 = coord2;
            ++coord3.x;
        }
        else if (n == 1) {
            final Coord coord4 = coord2;
            --coord4.x;
        }
        else if (n == 2) {
            final Coord coord5 = coord2;
            ++coord5.y;
        }
        else if (n == 3) {
            final Coord coord6 = coord2;
            --coord6.y;
        }
        return coord2;
    }
    
    static {
        border = new Coord(2, 2, 0);
    }
}
