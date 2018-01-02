import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridTypeTan extends GridType
{
    static final int icontilesize = 30;
    private int blockSize;
    private int offsetX;
    private int offsetY;
    private int widthX;
    private int widthY;
    private Coord firstShown;
    private Coord lastShown;
    private static final Coord border;
    
    public GridTypeTan() {
        this.blockSize = 0;
        this.firstShown = new Coord();
        this.lastShown = new Coord();
        this.numRotate = 4;
        this.numReflect = 8;
        this.numDim = 2;
        this.numTypes = 4;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        graphics.setColor(component.getBackground());
        graphics.fillRect(n, n2, this.width, this.height);
        graphics.setColor(component.getForeground());
        for (int i = n - 15; i < n + this.width + 30; i += 30) {
            for (int j = n2 - 15; j < n2 + this.height + 30; j += 30) {
                graphics.drawLine(i + 30, j + 30, i, j);
                graphics.drawLine(i, j, i + 30, j);
                graphics.drawLine(i + 30, j, i, j + 30);
                graphics.drawLine(i, j + 30, i, j);
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
        final int n3 = n / (this.blockSize * 2);
        n -= n3 * this.blockSize * 2;
        final int n4 = n2 / (this.blockSize * 2);
        n2 -= n4 * this.blockSize * 2;
        final int n5 = n3 + this.firstShown.x;
        final int n6 = n4 + this.firstShown.y;
        if (n5 > this.lastShown.x || n6 > this.lastShown.y) {
            return false;
        }
        int n7 = 0;
        if (n2 > n) {
            n7 += 3;
        }
        if (n + n2 > this.blockSize * 2) {
            n7 ^= 0x1;
        }
        coord.set(n5, n6, n7);
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
                this.firstShown.sub(GridTypeTan.border);
                this.lastShown.add(GridTypeTan.border);
            }
            this.blockSize = 0;
            this.width = width;
            this.height = height;
            final int n = this.lastShown.x - this.firstShown.x + 1;
            final int n2 = this.lastShown.y - this.firstShown.y + 1;
            this.blockSize = (width - 10) / n / 2;
            final int blockSize = (height - 10) / n2 / 2;
            if (this.blockSize > blockSize) {
                this.blockSize = blockSize;
            }
            if (this.blockSize < 3) {
                this.blockSize = 3;
            }
            this.widthX = n * this.blockSize * 2;
            this.widthY = n2 * this.blockSize * 2;
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
        final int[] array2 = new int[3];
        final int[] array3 = new int[3];
        for (int i = this.firstShown.y - 1, n = this.offsetY + this.blockSize; i <= this.lastShown.y; ++i, n += this.blockSize + this.blockSize) {
            for (int j = this.firstShown.x - 1, n2 = this.offsetX + this.blockSize; j <= this.lastShown.x; ++j, n2 += this.blockSize + this.blockSize) {
                array2[0] = n2;
                array3[0] = n;
                array2[1] = n2 + this.blockSize;
                array3[1] = n - this.blockSize;
                array2[2] = n2 - this.blockSize;
                array3[2] = n - this.blockSize;
                final int contents = board.getContents(j + 1, i + 1, 0);
                if (contents >= 0) {
                    graphics.setColor(this.getBlockColour(contents, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (j < this.lastShown.x) {
                    if (contents != board.getContents(j + 1, i, 2)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                array2[2] = n2 + this.blockSize;
                array3[2] = n + this.blockSize;
                final int contents2 = board.getContents(j + 1, i + 1, 1);
                if (contents2 >= 0) {
                    graphics.setColor(this.getBlockColour(contents2, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
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
                array2[1] = n2 - this.blockSize;
                array3[1] = n + this.blockSize;
                array2[2] = n2 + this.blockSize;
                array3[2] = n + this.blockSize;
                final int contents3 = board.getContents(j + 1, i + 1, 2);
                if (contents3 >= 0) {
                    graphics.setColor(this.getBlockColour(contents3, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents3 != contents2) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                    else if (contents3 == 0 || (b && contents3 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                }
                array2[2] = n2 - this.blockSize;
                array3[2] = n - this.blockSize;
                final int contents4 = board.getContents(j + 1, i + 1, 3);
                if (contents4 >= 0) {
                    graphics.setColor(this.getBlockColour(contents4, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents4 != contents3) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents4 == 0 || (b && contents4 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents4 != contents) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                    else if (contents4 == 0 || (b && contents4 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                }
                if (i < this.lastShown.y) {
                    if (contents4 != board.getContents(j, i + 1, 1)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents4 == 0 || (b && contents4 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
            }
        }
    }
    
    public void paintCentredOutline(final Graphics graphics, final Coord[] array, int n, int n2, final int n3) {
        n -= this.blockSize;
        n2 -= this.blockSize;
        if (array[n3].z == 0) {
            n2 += this.blockSize * 2 / 3;
        }
        else if (array[n3].z == 1) {
            n -= this.blockSize * 2 / 3;
        }
        else if (array[n3].z == 2) {
            n2 -= this.blockSize * 2 / 3;
        }
        else if (array[n3].z == 3) {
            n += this.blockSize * 2 / 3;
        }
        this.paintOutline(graphics, array, n, n2, n3);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.paintTileOutline(graphics, n3, n4 + n * 2 * this.blockSize, n5 + n2 * 2 * this.blockSize);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, int n2, int n3) {
        n2 += this.blockSize;
        n3 += this.blockSize;
        if (n == 0) {
            graphics.drawLine(n2, n3, n2 - this.blockSize, n3 - this.blockSize);
            graphics.drawLine(n2 - this.blockSize, n3 - this.blockSize, n2 + this.blockSize, n3 - this.blockSize);
            graphics.drawLine(n2 + this.blockSize, n3 - this.blockSize, n2, n3);
        }
        else if (n == 1) {
            graphics.drawLine(n2, n3, n2 + this.blockSize, n3 - this.blockSize);
            graphics.drawLine(n2 + this.blockSize, n3 - this.blockSize, n2 + this.blockSize, n3 + this.blockSize);
            graphics.drawLine(n2 + this.blockSize, n3 + this.blockSize, n2, n3);
        }
        else if (n == 2) {
            graphics.drawLine(n2, n3, n2 + this.blockSize, n3 + this.blockSize);
            graphics.drawLine(n2 + this.blockSize, n3 + this.blockSize, n2 - this.blockSize, n3 + this.blockSize);
            graphics.drawLine(n2 - this.blockSize, n3 + this.blockSize, n2, n3);
        }
        else if (n == 3) {
            graphics.drawLine(n2, n3, n2 - this.blockSize, n3 + this.blockSize);
            graphics.drawLine(n2 - this.blockSize, n3 + this.blockSize, n2 - this.blockSize, n3 - this.blockSize);
            graphics.drawLine(n2 - this.blockSize, n3 - this.blockSize, n2, n3);
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
                n = offsetX + (coord.x - this.firstShown.x) * this.blockSize * 2;
                n2 = (coord2.x - coord.x + 1) * this.blockSize * 2;
            }
            else {
                n = offsetX + (coord2.x - this.firstShown.x) * this.blockSize * 2;
                n2 = (coord.x - coord2.x + 1) * this.blockSize * 2;
            }
            int n3;
            int n4;
            if (coord2.y > coord.y) {
                n3 = offsetY + (coord.y - this.firstShown.y) * this.blockSize * 2;
                n4 = (coord2.y - coord.y + 1) * this.blockSize * 2;
            }
            else {
                n3 = offsetY + (coord2.y - this.firstShown.y) * this.blockSize * 2;
                n4 = (coord.y - coord2.y + 1) * this.blockSize * 2;
            }
            if (coord.equals(coord2)) {
                this.paintTileOutline(graphics, coord2.z, n, n3);
            }
            else {
                graphics.drawRect(n, n3, n2, n4);
            }
        }
    }
    
    public void getRotate(final Coord coord, int i) {
        if (i >= this.numRotate) {
            coord.x = -coord.x;
            if (coord.z == 1 || coord.z == 3) {
                coord.z = 4 - coord.z;
            }
            i -= this.numRotate;
        }
        while (i > 0) {
            final int x = coord.x;
            coord.x = coord.y;
            coord.y = -x;
            coord.z += ((coord.z == 0) ? 3 : -1);
            --i;
        }
    }
    
    public Coord getAdjacent(final Coord coord, final int n) {
        if (n < 0 || n >= 3) {
            return null;
        }
        final Coord coord2 = new Coord(coord);
        if (n == 0) {
            final Coord coord3 = coord2;
            coord3.z += ((coord2.z == 3) ? -3 : 1);
        }
        else if (n == 1) {
            final Coord coord4 = coord2;
            coord4.z += ((coord2.z == 0) ? 3 : -1);
        }
        else if (n == 2) {
            if (coord.z == 0) {
                final Coord coord5 = coord2;
                --coord5.y;
            }
            else if (coord.z == 1) {
                final Coord coord6 = coord2;
                ++coord6.x;
            }
            else if (coord.z == 2) {
                final Coord coord7 = coord2;
                ++coord7.y;
            }
            else if (coord.z == 3) {
                final Coord coord8 = coord2;
                --coord8.x;
            }
            final Coord coord9 = coord2;
            coord9.z ^= 0x2;
        }
        return coord2;
    }
    
    static {
        border = new Coord(2, 2, 0);
    }
}
