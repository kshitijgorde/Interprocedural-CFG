import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridTypePentagon extends GridType
{
    static final int icontilesize = 36;
    static final int icontiletab = 10;
    private int blockSize;
    private int blockTab;
    private int offsetX;
    private int offsetY;
    private int widthX;
    private int widthY;
    private Coord firstShown;
    private Coord lastShown;
    private static final Coord border;
    
    public GridTypePentagon() {
        this.blockSize = 0;
        this.blockTab = 0;
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
        for (int i = n; i < n + this.width + 36; i += 72) {
            for (int j = n2; j < n2 + this.height + 36; j += 72) {
                graphics.drawLine(i - 36 + 10, j + 10, i + 36 - 10, j - 10);
                graphics.drawLine(i - 10, j - 36 + 10, i + 10, j - 10 + 36);
                graphics.drawLine(i - 36, j + 36, i - 36 + 10, j + 10);
                graphics.drawLine(i - 36 + 10, j + 10, i - 36 - 10, j - 10);
                graphics.drawLine(i - 36 - 10, j - 10, i - 36, j - 36);
                graphics.drawLine(i - 36, j - 36, i - 10, j + 10 - 36);
                graphics.drawLine(i - 10, j + 10 - 36, i + 10, j - 10 - 36);
                graphics.drawLine(i + 10, j - 10 - 36, i + 36, j - 36);
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
        int n3 = n / (this.blockSize * 2);
        n -= n3 * this.blockSize * 2;
        int n4 = n2 / (this.blockSize * 2);
        n2 -= n4 * this.blockSize * 2;
        if (n < 0) {
            n += this.blockSize * 2;
            --n3;
        }
        if (n2 < 0) {
            n2 += this.blockSize * 2;
            --n4;
        }
        int n5 = n3 + this.firstShown.x;
        int n6 = n4 + this.firstShown.y;
        if (n * this.blockTab - n2 * (this.blockSize - this.blockTab) > 0 && n + n2 < this.blockSize) {
            --n6;
            n2 += this.blockSize * 2;
        }
        else if ((n - 2 * this.blockSize) * this.blockTab - (n2 - 2 * this.blockSize) * (this.blockSize - this.blockTab) < 0 && n + n2 > 3 * this.blockSize) {
            ++n6;
            n2 -= this.blockSize * 2;
        }
        else if ((n2 - 2 * this.blockSize) * this.blockTab + n * (this.blockSize - this.blockTab) < 0 && n2 - n > this.blockSize) {
            --n5;
            n += this.blockSize * 2;
        }
        else if (n2 * this.blockTab + (n - 2 * this.blockSize) * (this.blockSize - this.blockTab) > 0 && n - n2 > this.blockSize) {
            ++n5;
            n -= this.blockSize * 2;
        }
        if (n5 < this.firstShown.x || n6 < this.firstShown.y) {
            return false;
        }
        if (n5 > this.lastShown.x || n6 > this.lastShown.y) {
            return false;
        }
        int n7 = 0;
        if ((n2 - this.blockSize) * this.blockTab - (n - this.blockSize) * (this.blockSize - this.blockTab) > 0) {
            n7 += 3;
        }
        if ((n - this.blockSize) * this.blockTab + (n2 - this.blockSize) * (this.blockSize - this.blockTab) > 0) {
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
                this.firstShown.sub(GridTypePentagon.border);
                this.lastShown.add(GridTypePentagon.border);
            }
            this.blockSize = 0;
            this.width = width;
            this.height = height;
            final int n = this.lastShown.x - this.firstShown.x + 1;
            final int n2 = this.lastShown.y - this.firstShown.y + 1;
            this.blockSize = (width - 10) / (n * 2 + 1);
            final int blockSize = (height - 10) / (n2 * 2 + 1);
            if (this.blockSize > blockSize) {
                this.blockSize = blockSize;
            }
            if (this.blockSize < 3) {
                this.blockSize = 3;
            }
            this.blockTab = this.blockSize * 5 / 18;
            this.widthX = (n * this.blockSize + this.blockTab) * 2;
            this.widthY = (n2 * this.blockSize + this.blockTab) * 2;
            this.offsetX = (width - this.widthX) / 2 + this.blockTab;
            this.offsetY = (height - this.widthY) / 2 + this.blockTab;
        }
    }
    
    public void paintComponent(final Component component, final Graphics graphics, final Board board, final Color[] array, final boolean b) {
        if (this.blockSize == 0 || this.width != component.getWidth() || this.height != component.getHeight()) {
            this.recalcSettings(component.getWidth(), component.getHeight(), board, b);
            if (this.blockSize == 0) {
                return;
            }
        }
        final int[] array2 = new int[5];
        final int[] array3 = new int[5];
        for (int i = this.firstShown.y - 1, n = this.offsetY + this.blockSize; i <= this.lastShown.y; ++i, n += this.blockSize + this.blockSize) {
            for (int j = this.firstShown.x - 1, n2 = this.offsetX + this.blockSize; j <= this.lastShown.x; ++j, n2 += this.blockSize + this.blockSize) {
                array2[0] = n2;
                array3[0] = n;
                array2[1] = n2 + this.blockSize - this.blockTab;
                array3[1] = n - this.blockTab;
                array2[2] = n2 + this.blockSize;
                array3[2] = n - this.blockSize;
                array2[3] = n2 + this.blockTab;
                array3[3] = n - this.blockSize - this.blockTab;
                array2[4] = n2 - this.blockTab;
                array3[4] = n - this.blockSize + this.blockTab;
                final int contents = board.getContents(j + 1, i + 1, 0);
                if (contents >= 0) {
                    graphics.setColor(this.getBlockColour(contents, component, array, b));
                    graphics.fillPolygon(array2, array3, 5);
                }
                if (j < this.lastShown.x) {
                    if (contents != board.getContents(j + 1, i, 2)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[3], array3[3], array2[4], array3[4]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[3], array3[3], array2[4], array3[4]);
                    }
                }
                if (j < this.lastShown.x) {
                    if (contents != board.getContents(j + 1, i, 1)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                }
                array2[2] = n2 + this.blockSize + this.blockTab;
                array3[2] = n + this.blockTab;
                array2[3] = n2 + this.blockSize;
                array3[3] = n + this.blockSize;
                array2[4] = n2 + this.blockTab;
                array3[4] = n + this.blockSize - this.blockTab;
                final int contents2 = board.getContents(j + 1, i + 1, 1);
                if (contents2 >= 0) {
                    graphics.setColor(this.getBlockColour(contents2, component, array, b));
                    graphics.fillPolygon(array2, array3, 5);
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
                array2[1] = n2 - this.blockSize + this.blockTab;
                array3[1] = n + this.blockTab;
                array2[2] = n2 - this.blockSize;
                array3[2] = n + this.blockSize;
                array2[3] = n2 - this.blockTab;
                array3[3] = n + this.blockSize + this.blockTab;
                final int contents3 = board.getContents(j + 1, i + 1, 2);
                if (contents3 >= 0) {
                    graphics.setColor(this.getBlockColour(contents3, component, array, b));
                    graphics.fillPolygon(array2, array3, 5);
                }
                if (i < this.lastShown.y) {
                    final int contents4 = board.getContents(j, i + 1, 1);
                    if (contents3 != contents4) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents4 == 0 || (b && contents4 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents2 != contents3) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[4], array3[4]);
                    }
                    else if (contents2 == 0 || (b && contents2 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[4], array3[4]);
                    }
                }
                array2[2] = n2 - this.blockSize - this.blockTab;
                array3[2] = n - this.blockTab;
                array2[3] = n2 - this.blockSize;
                array3[3] = n - this.blockSize;
                array2[4] = n2 - this.blockTab;
                array3[4] = n - this.blockSize + this.blockTab;
                final int contents5 = board.getContents(j + 1, i + 1, 3);
                if (contents5 >= 0) {
                    graphics.setColor(this.getBlockColour(contents5, component, array, b));
                    graphics.fillPolygon(array2, array3, 5);
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents5 != contents3) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents5 == 0 || (b && contents5 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents5 != contents) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[4], array3[4]);
                    }
                    else if (contents5 == 0 || (b && contents5 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[4], array3[4]);
                    }
                }
                if (i < this.lastShown.y) {
                    final int contents6 = board.getContents(j, i + 1, 1);
                    if (contents5 != contents6) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents6 == 0 || (b && contents6 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                if (i < this.lastShown.y) {
                    final int contents7 = board.getContents(j, i + 1, 0);
                    if (contents5 != contents7) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                    else if (contents7 == 0 || (b && contents7 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                }
                if (j < this.lastShown.x) {
                    final int contents8 = board.getContents(j + 1, i, 2);
                    if (contents5 != contents8) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[3], array3[3], array2[4], array3[4]);
                    }
                    else if (contents8 == 0 || (b && contents8 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[3], array3[3], array2[4], array3[4]);
                    }
                }
            }
        }
    }
    
    public void paintCentredOutline(final Graphics graphics, final Coord[] array, int n, int n2, final int n3) {
        n -= this.blockSize;
        n2 -= this.blockSize;
        if (array[n3].z == 0) {
            n -= this.blockSize / 3;
            n2 += this.blockSize * 2 / 3;
        }
        else if (array[n3].z == 1) {
            n -= this.blockSize * 2 / 3;
            n2 -= this.blockSize / 3;
        }
        else if (array[n3].z == 2) {
            n += this.blockSize / 3;
            n2 -= this.blockSize * 2 / 3;
        }
        else if (array[n3].z == 3) {
            n += this.blockSize * 2 / 3;
            n2 += this.blockSize / 3;
        }
        this.paintOutline(graphics, array, n, n2, n3);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.paintTileOutline(graphics, n3, n4 + n * 2 * this.blockSize, n5 + n2 * 2 * this.blockSize);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, final int n2, final int n3) {
        final int[] array = { 0, this.blockSize - this.blockTab, this.blockSize, this.blockTab, -this.blockTab };
        final int[] array2 = { 0, -this.blockTab, -this.blockSize, -this.blockSize - this.blockTab, -this.blockSize + this.blockTab };
        if (n > 1) {
            for (int i = 0; i < 5; ++i) {
                array[i] = -array[i];
                array2[i] = -array2[i];
            }
        }
        if (n == 1 || n == 3) {
            for (int j = 0; j < 5; ++j) {
                final int n4 = array[j];
                array[j] = -array2[j];
                array2[j] = n4;
            }
        }
        for (int k = 0; k < 5; ++k) {
            final int[] array3 = array;
            final int n5 = k;
            array3[n5] += n2 + this.blockSize;
            final int[] array4 = array2;
            final int n6 = k;
            array4[n6] += n3 + this.blockSize;
        }
        graphics.drawPolygon(array, array2, 5);
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
                n2 = coord2.x - coord.x + 1;
            }
            else {
                n = offsetX + (coord2.x - this.firstShown.x) * this.blockSize * 2;
                n2 = coord.x - coord2.x + 1;
            }
            int n3;
            int n4;
            if (coord2.y > coord.y) {
                n3 = offsetY + (coord.y - this.firstShown.y) * this.blockSize * 2;
                n4 = coord2.y - coord.y + 1;
            }
            else {
                n3 = offsetY + (coord2.y - this.firstShown.y) * this.blockSize * 2;
                n4 = coord.y - coord2.y + 1;
            }
            if (coord.equals(coord2)) {
                this.paintTileOutline(graphics, coord2.z, n, n3);
            }
            else {
                final int n5 = n + n2 * this.blockSize * 2;
                for (int i = 0, n6 = n3; i < n4; ++i, n6 += this.blockSize * 2) {
                    graphics.drawLine(n, n6, n - this.blockTab, n6 - this.blockTab + this.blockSize);
                    graphics.drawLine(n - this.blockTab, n6 - this.blockTab + this.blockSize, n + this.blockTab, n6 + this.blockTab + this.blockSize);
                    graphics.drawLine(n + this.blockTab, n6 + this.blockTab + this.blockSize, n, n6 + this.blockSize + this.blockSize);
                    graphics.drawLine(n5, n6, n5 - this.blockTab, n6 - this.blockTab + this.blockSize);
                    graphics.drawLine(n5 - this.blockTab, n6 - this.blockTab + this.blockSize, n5 + this.blockTab, n6 + this.blockTab + this.blockSize);
                    graphics.drawLine(n5 + this.blockTab, n6 + this.blockTab + this.blockSize, n5, n6 + this.blockSize + this.blockSize);
                }
                final int n7 = n3 + n4 * this.blockSize * 2;
                for (int j = 0, n8 = n; j < n2; ++j, n8 += this.blockSize * 2) {
                    graphics.drawLine(n8, n3, n8 + this.blockSize - this.blockTab, n3 + this.blockTab);
                    graphics.drawLine(n8 + this.blockSize - this.blockTab, n3 + this.blockTab, n8 + this.blockSize + this.blockTab, n3 - this.blockTab);
                    graphics.drawLine(n8 + this.blockTab + this.blockSize, n3 - this.blockTab, n8 + this.blockSize + this.blockSize, n3);
                    graphics.drawLine(n8, n7, n8 + this.blockSize - this.blockTab, n7 + this.blockTab);
                    graphics.drawLine(n8 + this.blockSize - this.blockTab, n7 + this.blockTab, n8 + this.blockSize + this.blockTab, n7 - this.blockTab);
                    graphics.drawLine(n8 + this.blockTab + this.blockSize, n7 - this.blockTab, n8 + this.blockSize + this.blockSize, n7);
                }
            }
        }
    }
    
    public void getRotate(final Coord coord, int i) {
        if (i >= this.numRotate) {
            final int x = coord.x;
            coord.x = coord.y;
            coord.y = x;
            if (coord.z == 0) {
                --coord.x;
                ++coord.y;
            }
            else if (coord.z == 1) {
                coord.z = 3;
                ++coord.y;
            }
            else if (coord.z != 2) {
                if (coord.z == 3) {
                    coord.z = 1;
                    --coord.x;
                }
            }
            i -= this.numRotate;
        }
        while (i > 0) {
            final int x2 = coord.x;
            coord.x = coord.y;
            coord.y = -x2;
            coord.z += ((coord.z == 0) ? 3 : -1);
            --i;
        }
    }
    
    public Coord getAdjacent(final Coord coord, final int n) {
        if (n < 0 || n >= 5) {
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
                coord2.z = 3;
                final Coord coord5 = coord2;
                ++coord5.x;
            }
            else if (coord.z == 1) {
                coord2.z = 0;
                final Coord coord6 = coord2;
                ++coord6.y;
            }
            else if (coord.z == 2) {
                coord2.z = 1;
                final Coord coord7 = coord2;
                --coord7.x;
            }
            else if (coord.z == 3) {
                coord2.z = 2;
                final Coord coord8 = coord2;
                --coord8.y;
            }
        }
        else if (n == 3) {
            if (coord.z == 0) {
                coord2.z = 2;
                final Coord coord9 = coord2;
                --coord9.y;
            }
            else if (coord.z == 1) {
                coord2.z = 3;
                final Coord coord10 = coord2;
                ++coord10.x;
            }
            else if (coord.z == 2) {
                coord2.z = 0;
                final Coord coord11 = coord2;
                ++coord11.y;
            }
            else if (coord.z == 3) {
                coord2.z = 1;
                final Coord coord12 = coord2;
                --coord12.x;
            }
        }
        else if (n == 4) {
            if (coord.z == 0) {
                coord2.z = 1;
                final Coord coord13 = coord2;
                --coord13.y;
            }
            else if (coord.z == 1) {
                coord2.z = 2;
                final Coord coord14 = coord2;
                ++coord14.x;
            }
            else if (coord.z == 2) {
                coord2.z = 3;
                final Coord coord15 = coord2;
                ++coord15.y;
            }
            else if (coord.z == 3) {
                coord2.z = 0;
                final Coord coord16 = coord2;
                --coord16.x;
            }
        }
        return coord2;
    }
    
    static {
        border = new Coord(2, 2, 0);
    }
}
