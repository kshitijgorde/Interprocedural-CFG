import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridTypeCube extends GridType
{
    static final int icontilesize = 30;
    static final int iconsplit = 10;
    private int blockSize;
    private int offsetX;
    private int offsetY;
    private int offsetZ;
    private int widthX;
    private int widthY;
    static final int split = 10;
    private Coord firstShown;
    private Coord lastShown;
    private static final Coord border;
    
    public GridTypeCube() {
        this.blockSize = 0;
        this.firstShown = new Coord();
        this.lastShown = new Coord();
        this.numRotate = 24;
        this.numReflect = 48;
        this.numDim = 3;
        this.numTypes = 1;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        graphics.setColor(component.getBackground());
        graphics.fillRect(n, n2, this.width, this.height);
        graphics.setColor(component.getForeground());
        int n3 = (this.width - 40) / 12;
        if (n3 > 30) {
            n3 = 30;
        }
        final int n4 = (this.width - 20 - 12 * n3) / 2;
        final int n5 = (this.height - 5 * n3) / 2;
        for (int i = 0, n6 = n + n4; i < 3; ++i, n6 += n3 * 4 + 10) {
            for (int j = 0, n7 = n6; j < 5; ++j, n7 += n3) {
                graphics.drawLine(n7, n2 + n5, n7, n2 + n5 + n3 * 5);
            }
            for (int k = 0, n8 = n2 + n5; k < 6; ++k, n8 += n3) {
                graphics.drawLine(n6, n8, n6 + 4 * n3, n8);
            }
        }
    }
    
    public void reset() {
        this.blockSize = 0;
    }
    
    public boolean screen2Grid(final Coord coord, int i, int n) {
        if (this.blockSize == 0) {
            return false;
        }
        int n2 = 0;
        i -= this.offsetX;
        n -= this.offsetY;
        if (i < 0 || n < 0) {
            return false;
        }
        while (i > this.offsetZ) {
            i -= this.offsetZ;
            ++n2;
        }
        i /= this.blockSize;
        n /= this.blockSize;
        i += this.firstShown.x;
        n += this.firstShown.y;
        final int n3 = n2 + this.firstShown.z;
        if (i > this.lastShown.x || n > this.lastShown.y || n3 > this.lastShown.z) {
            return false;
        }
        coord.set(i, n, n3);
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
                this.firstShown.sub(GridTypeCube.border);
                this.lastShown.add(GridTypeCube.border);
            }
            this.blockSize = 0;
            this.width = width;
            this.height = height;
            final int n = this.lastShown.x - this.firstShown.x + 1;
            final int n2 = this.lastShown.y - this.firstShown.y + 1;
            final int n3 = this.lastShown.z - this.firstShown.z + 1;
            this.offsetZ = (width + 10 - 10) / n3;
            this.blockSize = (this.offsetZ - 10) / n;
            final int blockSize = (height - 10) / n2;
            if (this.blockSize > blockSize) {
                this.blockSize = blockSize;
            }
            if (this.blockSize < 5) {
                this.blockSize = 5;
            }
            this.widthX = n * this.blockSize;
            this.widthY = n2 * this.blockSize;
            this.offsetZ = this.widthX + 10;
            this.offsetY = (height - this.widthY) / 2;
            this.offsetX = (width - n3 * this.offsetZ + 10) / 2;
        }
    }
    
    public void paintComponent(final Component component, final Graphics graphics, final Board board, final Color[] array, final boolean b) {
        if (this.blockSize == 0 || this.width != component.getWidth() || this.height != component.getHeight()) {
            this.recalcSettings(component.getWidth(), component.getHeight(), board, b);
            if (this.blockSize == 0) {
                return;
            }
        }
        for (int i = this.firstShown.z, n = 0; i <= this.lastShown.z; ++i, n += this.offsetZ) {
            for (int j = this.firstShown.y - 1, offsetY = this.offsetY; j <= this.lastShown.y; ++j, offsetY += this.blockSize) {
                for (int k = this.firstShown.x - 1, n2 = n + this.offsetX; k <= this.lastShown.x; ++k, n2 += this.blockSize) {
                    final int contents = board.getContents(k + 1, j + 1, i);
                    if (contents >= 0) {
                        graphics.setColor(this.getBlockColour(contents, component, array, b));
                        graphics.fillRect(n2, offsetY, this.blockSize, this.blockSize);
                    }
                    if (j < this.lastShown.y) {
                        if (contents != board.getContents(k, j + 1, i)) {
                            graphics.setColor(Color.BLACK);
                            graphics.drawLine(n2, offsetY, n2, offsetY + this.blockSize);
                        }
                        else if (contents == 0 || (b && contents == -1)) {
                            graphics.setColor(Color.GRAY);
                            graphics.drawLine(n2, offsetY, n2, offsetY + this.blockSize);
                        }
                    }
                    if (k < this.lastShown.x) {
                        if (contents != board.getContents(k + 1, j, i)) {
                            graphics.setColor(Color.BLACK);
                            graphics.drawLine(n2, offsetY, n2 + this.blockSize, offsetY);
                        }
                        else if (contents == 0 || (b && contents == -1)) {
                            graphics.setColor(Color.GRAY);
                            graphics.drawLine(n2, offsetY, n2 + this.blockSize, offsetY);
                        }
                    }
                    if (contents > 0 || (contents == 0 && b)) {
                        graphics.setColor(Color.BLACK);
                        if (contents == board.getContents(k + 1, j + 1, i - 1)) {
                            graphics.drawLine(n2, offsetY, n2 + this.blockSize, offsetY + this.blockSize);
                            graphics.drawLine(n2 + this.blockSize, offsetY, n2, offsetY + this.blockSize);
                        }
                        if (contents == board.getContents(k + 1, j + 1, i + 1)) {
                            graphics.drawOval(n2 + 1, offsetY + 1, this.blockSize - 2, this.blockSize - 2);
                        }
                    }
                }
            }
        }
    }
    
    public void paintCentredOutline(final Graphics graphics, final Coord[] array, final int n, final int n2, final int n3) {
        final int n4 = this.blockSize / 2;
        this.paintOutline(graphics, array, n - n4, n2 - n4, n3);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.drawRect(n4 + n * this.blockSize + n3 * this.offsetZ, n5 + n2 * this.blockSize, this.blockSize, this.blockSize);
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
                n = offsetX + (coord.x - this.firstShown.x) * this.blockSize;
                n2 = (coord2.x - coord.x + 1) * this.blockSize;
            }
            else {
                n = offsetX + (coord2.x - this.firstShown.x) * this.blockSize;
                n2 = (coord.x - coord2.x + 1) * this.blockSize;
            }
            int n3;
            int n4;
            if (coord2.y > coord.y) {
                n3 = offsetY + (coord.y - this.firstShown.y) * this.blockSize;
                n4 = (coord2.y - coord.y + 1) * this.blockSize;
            }
            else {
                n3 = offsetY + (coord2.y - this.firstShown.y) * this.blockSize;
                n4 = (coord.y - coord2.y + 1) * this.blockSize;
            }
            int n5;
            int n6;
            if (coord2.z > coord.z) {
                n5 = (coord.z - this.firstShown.z) * this.offsetZ;
                n6 = coord2.z - coord.z;
            }
            else {
                n5 = (coord2.z - this.firstShown.z) * this.offsetZ;
                n6 = coord.z - coord2.z;
            }
            for (int i = 0; i <= n6; ++i, n5 += this.offsetZ) {
                graphics.drawRect(n + n5, n3, n2, n4);
            }
        }
    }
    
    public void getRotate(final Coord coord, int n) {
        int x = coord.x;
        int y = coord.y;
        int z = coord.z;
        if (n >= this.numRotate) {
            x = -x;
            y = -y;
            z = -z;
            n -= this.numRotate;
        }
        final int n2 = n >> 2;
        if (n2 >= 4) {
            final int n3 = x;
            x = y;
            y = z;
            z = n3;
        }
        else if (n2 >= 2) {
            final int n4 = x;
            x = z;
            z = y;
            y = n4;
        }
        if ((n2 & 0x1) != 0x0) {
            z = -z;
            y = -y;
        }
        if ((n & 0x1) != 0x0) {
            final int n5 = x;
            x = y;
            y = -n5;
        }
        if ((n & 0x2) != 0x0) {
            x = -x;
            y = -y;
        }
        coord.set(x, y, z);
    }
    
    public Coord getAdjacent(final Coord coord, final int n) {
        if (n < 0 || n > 5) {
            return null;
        }
        final Coord coord2 = new Coord(coord);
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
            final Coord coord7 = coord2;
            --coord7.z;
        }
        else if (n == 5) {
            final Coord coord8 = coord2;
            ++coord8.z;
        }
        return coord2;
    }
    
    static {
        border = new Coord(2, 2, 1);
    }
}
