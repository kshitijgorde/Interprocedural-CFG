import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridTypeOctagon extends GridType
{
    static final int icontilesize = 10;
    private int blockSize;
    private int offsetX;
    private int offsetY;
    private int widthX;
    private int widthY;
    private Coord firstShown;
    private Coord lastShown;
    private static final Coord border;
    
    public GridTypeOctagon() {
        this.blockSize = 0;
        this.firstShown = new Coord();
        this.lastShown = new Coord();
        this.numRotate = 4;
        this.numReflect = 8;
        this.numDim = 2;
        this.numTypes = 2;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        graphics.setColor(component.getBackground());
        graphics.fillRect(n, n2, this.width, this.height);
        graphics.setColor(component.getForeground());
        for (int i = n - 5; i < n + this.width + 10; i += 30) {
            for (int j = n2 - 5; j < n2 + this.height + 10; j += 30) {
                graphics.drawLine(i + 10, j + 30, i, j + 20);
                graphics.drawLine(i, j + 20, i, j + 10);
                graphics.drawLine(i, j + 10, i + 10, j);
                graphics.drawLine(i + 10, j, i + 20, j);
                graphics.drawLine(i + 20, j, i + 30, j + 10);
                graphics.drawLine(i + 20, j + 30, i + 30, j + 20);
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
        if (n < 0 || n2 < 0) {
            return false;
        }
        int n3 = n / (this.blockSize * 3);
        n -= n3 * this.blockSize * 3;
        int n4 = n2 / (this.blockSize * 3);
        n2 -= n4 * this.blockSize * 3;
        int n5 = 0;
        if (n + n2 < this.blockSize) {
            n5 = 1;
            --n3;
            --n4;
        }
        else if (n + n2 > 5 * this.blockSize) {
            n5 = 1;
        }
        else if (n - n2 > 2 * this.blockSize) {
            n5 = 1;
            --n4;
        }
        else if (n2 - n > 2 * this.blockSize) {
            n5 = 1;
            --n3;
        }
        if (n3 < 0 || n4 < 0) {
            return false;
        }
        final int n6 = n3 + this.firstShown.x;
        final int n7 = n4 + this.firstShown.y;
        if (n6 > this.lastShown.x || n7 > this.lastShown.y) {
            return false;
        }
        coord.set(n6, n7, n5);
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
            this.firstShown.z = 0;
            this.lastShown.z = 0;
            if (b) {
                this.firstShown.sub(GridTypeOctagon.border);
                this.lastShown.add(GridTypeOctagon.border);
            }
            this.blockSize = 0;
            this.width = width;
            this.height = height;
            final int n = this.lastShown.x - this.firstShown.x + 1;
            final int n2 = this.lastShown.y - this.firstShown.y + 1;
            this.blockSize = (width - 10) / (n * 3 + 1);
            final int blockSize = (height - 10) / (n2 * 3 + 1);
            if (this.blockSize > blockSize) {
                this.blockSize = blockSize;
            }
            if (this.blockSize < 3) {
                this.blockSize = 3;
            }
            this.widthX = (3 * n + 1) * this.blockSize;
            this.widthY = (3 * n2 + 1) * this.blockSize;
            this.offsetX = (width - this.widthX) / 2;
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
        final int[] array2 = new int[8];
        final int[] array3 = new int[8];
        for (int i = this.firstShown.y - 1, offsetY = this.offsetY; i <= this.lastShown.y; ++i, offsetY += this.blockSize * 3) {
            for (int j = this.firstShown.x - 1, offsetX = this.offsetX; j <= this.lastShown.x; ++j, offsetX += this.blockSize * 3) {
                array2[0] = offsetX + this.blockSize * 3;
                array3[0] = offsetY + this.blockSize * 2;
                array2[1] = offsetX + this.blockSize * 2;
                array3[1] = offsetY + this.blockSize * 3;
                array2[2] = offsetX + this.blockSize;
                array3[2] = offsetY + this.blockSize * 3;
                array2[3] = offsetX;
                array3[3] = offsetY + this.blockSize * 2;
                array2[4] = offsetX;
                array3[4] = offsetY + this.blockSize;
                array2[5] = offsetX + this.blockSize;
                array3[5] = offsetY;
                array2[6] = offsetX + this.blockSize * 2;
                array3[6] = offsetY;
                array2[7] = offsetX + this.blockSize * 3;
                array3[7] = offsetY + this.blockSize;
                final int contents = board.getContents(j + 1, i + 1, 0);
                if (contents >= 0) {
                    graphics.setColor(this.getBlockColour(contents, component, array, b));
                    graphics.fillPolygon(array2, array3, 8);
                }
                if (i < this.lastShown.y) {
                    if (contents != board.getContents(j, i + 1, 1)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                }
                if (i < this.lastShown.y) {
                    if (contents != board.getContents(j, i + 1, 0)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[3], array3[3], array2[4], array3[4]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[3], array3[3], array2[4], array3[4]);
                    }
                }
                if ((j != this.lastShown.x || i != this.firstShown.y - 1) && (j != this.firstShown.x - 1 || i != this.lastShown.y)) {
                    if (contents != board.getContents(j, i, 1)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[4], array3[4], array2[5], array3[5]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[4], array3[4], array2[5], array3[5]);
                    }
                }
                if (j < this.lastShown.x) {
                    if (contents != board.getContents(j + 1, i, 0)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[5], array3[5], array2[6], array3[6]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[5], array3[5], array2[6], array3[6]);
                    }
                }
                if (j < this.lastShown.x) {
                    if (contents != board.getContents(j + 1, i, 1)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[6], array3[6], array2[7], array3[7]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[6], array3[6], array2[7], array3[7]);
                    }
                }
                array2[2] = offsetX + this.blockSize * 3;
                array3[2] = offsetY + this.blockSize * 4;
                array2[3] = offsetX + this.blockSize * 4;
                array3[3] = offsetY + this.blockSize * 3;
                final int contents2 = board.getContents(j + 1, i + 1, 1);
                if (contents2 >= 0) {
                    graphics.setColor(this.getBlockColour(contents2, component, array, b));
                    graphics.fillPolygon(array2, array3, 4);
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents2 != contents) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents2 == 0 || (b && contents2 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
            }
        }
    }
    
    public void paintCentredOutline(final Graphics graphics, final Coord[] array, final int n, final int n2, final int n3) {
        int n4 = 3 * this.blockSize;
        if (array[n3].z == 0) {
            n4 /= 2;
        }
        this.paintOutline(graphics, array, n - n4, n2 - n4, n3);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.paintTileOutline(graphics, n3, n4 + n * 3 * this.blockSize, n5 + n2 * 3 * this.blockSize);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, int n2, int n3) {
        if (n == 1) {
            n2 += this.blockSize * 3;
            n3 += this.blockSize * 3;
            graphics.drawLine(n2 - this.blockSize, n3, n2, n3 - this.blockSize);
            graphics.drawLine(n2, n3 - this.blockSize, n2 + this.blockSize, n3);
            graphics.drawLine(n2 + this.blockSize, n3, n2, n3 + this.blockSize);
            graphics.drawLine(n2, n3 + this.blockSize, n2 - this.blockSize, n3);
        }
        else {
            graphics.drawLine(n2 + this.blockSize, n3, n2 + this.blockSize * 2, n3);
            graphics.drawLine(n2 + this.blockSize * 2, n3, n2 + this.blockSize * 3, n3 + this.blockSize);
            graphics.drawLine(n2 + this.blockSize * 3, n3 + this.blockSize, n2 + this.blockSize * 3, n3 + this.blockSize * 2);
            graphics.drawLine(n2 + this.blockSize * 3, n3 + this.blockSize * 2, n2 + this.blockSize * 2, n3 + this.blockSize * 3);
            graphics.drawLine(n2 + this.blockSize * 2, n3 + this.blockSize * 3, n2 + this.blockSize, n3 + this.blockSize * 3);
            graphics.drawLine(n2 + this.blockSize, n3 + this.blockSize * 3, n2, n3 + this.blockSize * 2);
            graphics.drawLine(n2, n3 + this.blockSize * 2, n2, n3 + this.blockSize);
            graphics.drawLine(n2, n3 + this.blockSize, n2 + this.blockSize, n3);
        }
    }
    
    public void paintOutline(final Graphics graphics, final Coord coord, final Coord coord2) {
        if (this.blockSize == 0) {
            return;
        }
        if (coord != null && coord2 != null) {
            final int offsetX = this.offsetX;
            final int offsetY = this.offsetY;
            int n;
            int n2;
            if (coord2.x > coord.x) {
                n = offsetX + (coord.x - this.firstShown.x) * this.blockSize * 3;
                n2 = coord2.x - coord.x + 1;
            }
            else {
                n = offsetX + (coord2.x - this.firstShown.x) * this.blockSize * 3;
                n2 = coord.x - coord2.x + 1;
            }
            int n3;
            int n4;
            if (coord2.y > coord.y) {
                n3 = offsetY + (coord.y - this.firstShown.y) * this.blockSize * 3;
                n4 = coord2.y - coord.y + 1;
            }
            else {
                n3 = offsetY + (coord2.y - this.firstShown.y) * this.blockSize * 3;
                n4 = coord.y - coord2.y + 1;
            }
            if (coord.equals(coord2)) {
                this.paintTileOutline(graphics, coord.z, n, n3);
            }
            else {
                final int n5 = n3 + n4 * this.blockSize * 3;
                for (int i = 0, n6 = n; i < n2; ++i, n6 += this.blockSize * 3) {
                    graphics.drawLine(n6, n3 + this.blockSize, n6 + this.blockSize, n3);
                    graphics.drawLine(n6 + this.blockSize, n3, n6 + this.blockSize * 2, n3);
                    graphics.drawLine(n6 + this.blockSize * 2, n3, n6 + this.blockSize * 3, n3 + this.blockSize);
                    graphics.drawLine(n6 + this.blockSize, n5, n6 + this.blockSize * 2, n5);
                    graphics.drawLine(n6 + this.blockSize * 2, n5, n6 + this.blockSize * 3, n5 + this.blockSize);
                    graphics.drawLine(n6 + this.blockSize * 3, n5 + this.blockSize, n6 + this.blockSize * 4, n5);
                }
                final int n7 = n + n2 * this.blockSize * 3;
                for (int j = 0, n8 = n3; j < n4; ++j, n8 += this.blockSize * 3) {
                    graphics.drawLine(n + this.blockSize, n8, n, n8 + this.blockSize);
                    graphics.drawLine(n, n8 + this.blockSize, n, n8 + this.blockSize * 2);
                    graphics.drawLine(n, n8 + this.blockSize * 2, n + this.blockSize, n8 + this.blockSize * 3);
                    graphics.drawLine(n7, n8 + this.blockSize, n7, n8 + this.blockSize * 2);
                    graphics.drawLine(n7, n8 + this.blockSize * 2, n7 + this.blockSize, n8 + this.blockSize * 3);
                    graphics.drawLine(n7 + this.blockSize, n8 + this.blockSize * 3, n7, n8 + this.blockSize * 4);
                }
            }
        }
    }
    
    public void getRotate(final Coord coord, int i) {
        if (i >= this.numRotate) {
            coord.x = -coord.x;
            if (coord.z == 1) {
                --coord.x;
            }
            i -= this.numRotate;
        }
        while (i > 0) {
            final int x = coord.x;
            coord.x = coord.y;
            coord.y = -x;
            if (coord.z == 1) {
                --coord.y;
            }
            --i;
        }
    }
    
    public Coord getAdjacent(final Coord coord, final int n) {
        if (n < 0 || n >= 8 || (n >= 4 && coord.z == 1)) {
            return null;
        }
        final Coord coord2 = new Coord(coord);
        if (coord.z == 0) {
            if (n == 0) {
                final Coord coord3 = coord2;
                --coord3.x;
            }
            else if (n == 1) {
                final Coord coord4 = coord2;
                ++coord4.x;
            }
            else if (n == 2) {
                final Coord coord5 = coord2;
                --coord5.y;
            }
            else if (n == 3) {
                final Coord coord6 = coord2;
                ++coord6.y;
            }
            else if (n == 4) {
                coord2.z = 1;
                final Coord coord7 = coord2;
                --coord7.x;
            }
            else if (n == 5) {
                coord2.z = 1;
                final Coord coord8 = coord2;
                --coord8.x;
                final Coord coord9 = coord2;
                --coord9.y;
            }
            else if (n == 6) {
                coord2.z = 1;
                final Coord coord10 = coord2;
                --coord10.y;
            }
            else if (n == 7) {
                coord2.z = 1;
            }
        }
        else if (coord.z == 1) {
            coord2.z = 0;
            if (n == 0) {
                final Coord coord11 = coord2;
                ++coord11.x;
            }
            else if (n == 1) {
                final Coord coord12 = coord2;
                ++coord12.x;
                final Coord coord13 = coord2;
                ++coord13.y;
            }
            else if (n == 2) {
                final Coord coord14 = coord2;
                ++coord14.y;
            }
        }
        return coord2;
    }
    
    static {
        border = new Coord(2, 2, 0);
    }
}
