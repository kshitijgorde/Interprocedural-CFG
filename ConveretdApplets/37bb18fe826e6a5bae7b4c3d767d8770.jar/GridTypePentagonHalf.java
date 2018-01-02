import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridTypePentagonHalf extends GridType
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
    
    public GridTypePentagonHalf() {
        this.blockSize = 0;
        this.blockTab = 0;
        this.firstShown = new Coord();
        this.lastShown = new Coord();
        this.numRotate = 4;
        this.numReflect = 8;
        this.numDim = 2;
        this.numTypes = 8;
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
                graphics.drawLine(i, j + 36, i - 36 - 10, j - 10);
                graphics.drawLine(i - 36 - 10, j - 10, i - 36, j - 36);
                graphics.drawLine(i - 36, j - 36, i - 10, j + 10 - 36);
                graphics.drawLine(i - 36, j, i + 10, j - 10 - 36);
                graphics.drawLine(i + 10, j - 10 - 36, i + 36, j - 36);
                graphics.drawLine(i, j - 36, i + 36 - 10, j - 10);
                graphics.drawLine(i + 36, j, i + 10, j - 10 + 36);
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
        n -= this.blockSize;
        n2 -= this.blockSize;
        int n7 = 0;
        if (n2 * this.blockTab - n * (this.blockSize - this.blockTab) > 0) {
            n7 += 6;
        }
        if (n * this.blockTab + n2 * (this.blockSize - this.blockTab) > 0) {
            n7 ^= 0x2;
        }
        if (n < 0) {
            n = -n;
        }
        if (n2 < 0) {
            n2 = -n2;
        }
        if (n + n2 > this.blockSize) {
            ++n7;
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
                this.firstShown.sub(GridTypePentagonHalf.border);
                this.lastShown.add(GridTypePentagonHalf.border);
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
        final int[] array2 = new int[4];
        final int[] array3 = new int[4];
        for (int i = this.firstShown.y - 1, n = this.offsetY + this.blockSize; i <= this.lastShown.y; ++i, n += this.blockSize + this.blockSize) {
            for (int j = this.firstShown.x - 1, n2 = this.offsetX + this.blockSize; j <= this.lastShown.x; ++j, n2 += this.blockSize + this.blockSize) {
                array2[0] = n2;
                array3[0] = n - this.blockSize;
                array2[1] = n2 + this.blockSize - this.blockTab;
                array3[1] = n - this.blockTab;
                array2[2] = n2;
                array3[2] = n;
                array2[3] = n2 - this.blockTab;
                array3[3] = n - this.blockSize + this.blockTab;
                final int contents = board.getContents(j + 1, i + 1, 0);
                if (contents >= 0) {
                    graphics.setColor(this.getBlockColour(contents, component, array, b));
                    graphics.fillPolygon(array2, array3, 4);
                }
                if (j < this.lastShown.x) {
                    final int contents2 = board.getContents(j + 1, i, 5);
                    if (contents != contents2) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                    else if (contents2 == 0 || (b && contents2 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                }
                array2[0] = n2;
                array3[0] = n - this.blockSize;
                array2[1] = n2 + this.blockSize - this.blockTab;
                array3[1] = n - this.blockTab;
                array2[2] = n2 + this.blockSize;
                array3[2] = n - this.blockSize;
                array2[3] = n2 + this.blockTab;
                array3[3] = n - this.blockSize - this.blockTab;
                final int contents3 = board.getContents(j + 1, i + 1, 1);
                if (contents3 >= 0) {
                    graphics.setColor(this.getBlockColour(contents3, component, array, b));
                    graphics.fillPolygon(array2, array3, 4);
                }
                if (j < this.lastShown.x) {
                    final int contents4 = board.getContents(j + 1, i, 4);
                    if (contents3 != contents4) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                    else if (contents4 == 0 || (b && contents4 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                }
                if (j < this.lastShown.x) {
                    final int contents5 = board.getContents(j + 1, i, 3);
                    if (contents3 != contents5) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                    else if (contents5 == 0 || (b && contents5 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents3 != contents) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                array2[0] = n2;
                array3[0] = n;
                array2[1] = n2 + this.blockSize - this.blockTab;
                array3[1] = n - this.blockTab;
                array2[2] = n2 + this.blockSize;
                array3[2] = n;
                array2[3] = n2 + this.blockTab;
                array3[3] = n + this.blockSize - this.blockTab;
                final int contents6 = board.getContents(j + 1, i + 1, 2);
                if (contents6 >= 0) {
                    graphics.setColor(this.getBlockColour(contents6, component, array, b));
                    graphics.fillPolygon(array2, array3, 4);
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents6 != contents) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                array2[0] = n2 + this.blockSize;
                array3[0] = n;
                array2[1] = n2 + this.blockSize + this.blockTab;
                array3[1] = n + this.blockTab;
                array2[2] = n2 + this.blockSize;
                array3[2] = n + this.blockSize;
                array2[3] = n2 + this.blockTab;
                array3[3] = n + this.blockSize - this.blockTab;
                final int contents7 = board.getContents(j + 1, i + 1, 3);
                if (contents7 >= 0) {
                    graphics.setColor(this.getBlockColour(contents7, component, array, b));
                    graphics.fillPolygon(array2, array3, 4);
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents7 != contents6) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                    else if (contents7 == 0 || (b && contents7 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                }
                array2[0] = n2;
                array3[0] = n;
                array2[1] = n2 + this.blockTab;
                array3[1] = n + this.blockSize - this.blockTab;
                array2[2] = n2;
                array3[2] = n + this.blockSize;
                array2[3] = n2 - this.blockSize + this.blockTab;
                array3[3] = n + this.blockTab;
                final int contents8 = board.getContents(j + 1, i + 1, 4);
                if (contents8 >= 0) {
                    graphics.setColor(this.getBlockColour(contents8, component, array, b));
                    graphics.fillPolygon(array2, array3, 4);
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents8 != contents6) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents8 == 0 || (b && contents8 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                array2[0] = n2;
                array3[0] = n + this.blockSize;
                array2[1] = n2 - this.blockTab;
                array3[1] = n + this.blockSize + this.blockTab;
                array2[2] = n2 - this.blockSize;
                array3[2] = n + this.blockSize;
                array2[3] = n2 - this.blockSize + this.blockTab;
                array3[3] = n + this.blockTab;
                final int contents9 = board.getContents(j + 1, i + 1, 5);
                if (contents9 >= 0) {
                    graphics.setColor(this.getBlockColour(contents9, component, array, b));
                    graphics.fillPolygon(array2, array3, 4);
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents9 != contents8) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                    else if (contents9 == 0 || (b && contents9 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                }
                if (i < this.lastShown.y) {
                    final int contents10 = board.getContents(j, i + 1, 3);
                    if (contents9 != contents10) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                    else if (contents10 == 0 || (b && contents10 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                }
                array2[0] = n2;
                array3[0] = n;
                array2[1] = n2 - this.blockSize + this.blockTab;
                array3[1] = n + this.blockTab;
                array2[2] = n2 - this.blockSize;
                array3[2] = n;
                array2[3] = n2 - this.blockTab;
                array3[3] = n - this.blockSize + this.blockTab;
                final int contents11 = board.getContents(j + 1, i + 1, 6);
                if (contents11 >= 0) {
                    graphics.setColor(this.getBlockColour(contents11, component, array, b));
                    graphics.fillPolygon(array2, array3, 4);
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents11 != contents8) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents11 == 0 || (b && contents11 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                if (i < this.lastShown.y) {
                    final int contents12 = board.getContents(j, i + 1, 3);
                    if (contents11 != contents12) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents12 == 0 || (b && contents12 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents11 != contents) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                    else if (contents11 == 0 || (b && contents11 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                }
                array2[0] = n2 - this.blockSize;
                array3[0] = n;
                array2[1] = n2 - this.blockSize - this.blockTab;
                array3[1] = n - this.blockTab;
                array2[2] = n2 - this.blockSize;
                array3[2] = n - this.blockSize;
                array2[3] = n2 - this.blockTab;
                array3[3] = n - this.blockSize + this.blockTab;
                final int contents13 = board.getContents(j + 1, i + 1, 7);
                if (contents13 >= 0) {
                    graphics.setColor(this.getBlockColour(contents13, component, array, b));
                    graphics.fillPolygon(array2, array3, 4);
                }
                if (j < this.lastShown.x && i < this.lastShown.y) {
                    if (contents13 != contents11) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                    else if (contents13 == 0 || (b && contents13 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[3], array3[3], array2[0], array3[0]);
                    }
                }
                if (i < this.lastShown.y) {
                    final int contents14 = board.getContents(j, i + 1, 2);
                    if (contents13 != contents14) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents14 == 0 || (b && contents14 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                if (i < this.lastShown.y) {
                    final int contents15 = board.getContents(j, i + 1, 1);
                    if (contents13 != contents15) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents15 == 0 || (b && contents15 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                if (j < this.lastShown.x) {
                    final int contents16 = board.getContents(j + 1, i, 5);
                    if (contents13 != contents16) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                    else if (contents16 == 0 || (b && contents16 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[2], array3[2], array2[3], array3[3]);
                    }
                }
            }
        }
    }
    
    public void paintCentredOutline(final Graphics graphics, final Coord[] array, int n, int n2, final int n3) {
        n -= this.blockSize;
        n2 -= this.blockSize;
        final int n4 = (1 - 2 * this.blockTab) / 4;
        final int n5 = this.blockSize / 2;
        if (array[n3].z == 0) {
            n += n4;
            n2 += n5;
        }
        else if (array[n3].z == 1) {
            n -= n5;
            n2 += this.blockSize + n4;
        }
        else if (array[n3].z == 2) {
            n -= n5;
            n2 += n4;
        }
        else if (array[n3].z == 3) {
            n -= this.blockSize + n4;
            n2 -= n5;
        }
        else if (array[n3].z == 4) {
            n -= n4;
            n2 -= n5;
        }
        else if (array[n3].z == 5) {
            n += n5;
            n2 -= this.blockSize + n4;
        }
        else if (array[n3].z == 6) {
            n += n5;
            n2 -= n4;
        }
        else if (array[n3].z == 7) {
            n += this.blockSize + n4;
            n2 += n5;
        }
        this.paintOutline(graphics, array, n, n2, n3);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.paintTileOutline(graphics, n3, n4 + n * 2 * this.blockSize, n5 + n2 * 2 * this.blockSize);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, final int n2, final int n3) {
        final int[] array = { 0, this.blockSize - this.blockTab, 0, -this.blockTab };
        final int[] array2 = { 0, -this.blockTab, -this.blockSize, -this.blockSize + this.blockTab };
        if ((n & 0x1) != 0x0) {
            for (int i = 0; i < 4; ++i) {
                final int n4 = array[i];
                array[i] = array2[i] + this.blockSize;
                array2[i] = n4 - this.blockSize;
            }
        }
        if ((n & 0x4) != 0x0) {
            for (int j = 0; j < 4; ++j) {
                array[j] = -array[j];
                array2[j] = -array2[j];
            }
        }
        if ((n & 0x2) != 0x0) {
            for (int k = 0; k < 4; ++k) {
                final int n5 = array[k];
                array[k] = -array2[k];
                array2[k] = n5;
            }
        }
        for (int l = 0; l < 4; ++l) {
            final int[] array3 = array;
            final int n6 = l;
            array3[n6] += n2 + this.blockSize;
            final int[] array4 = array2;
            final int n7 = l;
            array4[n7] += n3 + this.blockSize;
        }
        graphics.drawPolygon(array, array2, 4);
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
            if (coord.z == 0 || coord.z == 1) {
                --coord.x;
                ++coord.y;
                coord.z = 1 - coord.z;
            }
            else if (coord.z == 2 || coord.z == 3) {
                coord.z = 9 - coord.z;
                ++coord.y;
            }
            else if (coord.z == 4 || coord.z == 5) {
                coord.z = 9 - coord.z;
            }
            else if (coord.z == 6 || coord.z == 7) {
                coord.z = 9 - coord.z;
                --coord.x;
            }
            i -= this.numRotate;
        }
        while (i > 0) {
            final int x2 = coord.x;
            coord.x = coord.y;
            coord.y = -x2;
            coord.z += ((coord.z <= 1) ? 6 : -2);
            --i;
        }
    }
    
    public Coord getAdjacent(final Coord coord, final int n) {
        if (n < 0 || n >= 4) {
            return null;
        }
        final Coord coord2 = new Coord(coord);
        if (n == 0) {
            final Coord coord3 = coord2;
            coord3.z ^= 0x1;
        }
        else if (n == 1) {
            if (coord.z == 0) {
                coord2.z = 5;
                final Coord coord4 = coord2;
                --coord4.y;
            }
            else if (coord.z == 1) {
                coord2.z = 4;
                final Coord coord5 = coord2;
                --coord5.y;
            }
            else if (coord.z == 2) {
                coord2.z = 7;
                final Coord coord6 = coord2;
                ++coord6.x;
            }
            else if (coord.z == 3) {
                coord2.z = 6;
                final Coord coord7 = coord2;
                ++coord7.x;
            }
            else if (coord.z == 4) {
                coord2.z = 1;
                final Coord coord8 = coord2;
                ++coord8.y;
            }
            else if (coord.z == 5) {
                coord2.z = 0;
                final Coord coord9 = coord2;
                ++coord9.y;
            }
            else if (coord.z == 6) {
                coord2.z = 3;
                final Coord coord10 = coord2;
                --coord10.x;
            }
            else if (coord.z == 7) {
                coord2.z = 2;
                final Coord coord11 = coord2;
                --coord11.x;
            }
        }
        else if (n == 2) {
            if (coord.z == 0) {
                coord2.z = 2;
            }
            else if (coord.z == 1) {
                coord2.z = 7;
                final Coord coord12 = coord2;
                ++coord12.x;
            }
            else if (coord.z == 2) {
                coord2.z = 4;
            }
            else if (coord.z == 3) {
                coord2.z = 1;
                final Coord coord13 = coord2;
                ++coord13.y;
            }
            else if (coord.z == 4) {
                coord2.z = 6;
            }
            else if (coord.z == 5) {
                coord2.z = 3;
                final Coord coord14 = coord2;
                --coord14.x;
            }
            else if (coord.z == 6) {
                coord2.z = 0;
            }
            else if (coord.z == 7) {
                coord2.z = 5;
                final Coord coord15 = coord2;
                --coord15.y;
            }
        }
        else if (n == 3) {
            if (coord.z == 0) {
                coord2.z = 6;
            }
            else if (coord.z == 1) {
                coord2.z = 3;
                final Coord coord16 = coord2;
                --coord16.y;
            }
            else if (coord.z == 2) {
                coord2.z = 0;
            }
            else if (coord.z == 3) {
                coord2.z = 5;
                final Coord coord17 = coord2;
                ++coord17.x;
            }
            else if (coord.z == 4) {
                coord2.z = 2;
            }
            else if (coord.z == 5) {
                coord2.z = 7;
                final Coord coord18 = coord2;
                ++coord18.y;
            }
            else if (coord.z == 6) {
                coord2.z = 4;
            }
            else if (coord.z == 7) {
                coord2.z = 1;
                final Coord coord19 = coord2;
                --coord19.x;
            }
        }
        return coord2;
    }
    
    static {
        border = new Coord(2, 2, 0);
    }
}
