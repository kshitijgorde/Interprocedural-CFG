import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridTypeSquare extends GridType
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
    
    public GridTypeSquare() {
        this.blockSize = 0;
        this.firstShown = new Coord();
        this.lastShown = new Coord();
        this.numRotate = 4;
        this.numReflect = 8;
        this.numDim = 2;
        this.numTypes = 1;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        graphics.setColor(component.getBackground());
        graphics.fillRect(n, n2, this.width, this.height);
        graphics.setColor(component.getForeground());
        for (int i = 15; i < this.width; i += 30) {
            graphics.drawLine(n + i, n2, n + i, n2 + this.height);
        }
        for (int j = 15; j < this.height; j += 30) {
            graphics.drawLine(n, n2 + j, n + this.width, n2 + j);
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
        n /= this.blockSize;
        n2 /= this.blockSize;
        n += this.firstShown.x;
        n2 += this.firstShown.y;
        if (n > this.lastShown.x || n2 > this.lastShown.y) {
            return false;
        }
        coord.set(n, n2, 0);
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
                this.firstShown.sub(GridTypeSquare.border);
                this.lastShown.add(GridTypeSquare.border);
            }
            this.blockSize = 0;
            this.width = width;
            this.height = height;
            final int n = this.lastShown.x - this.firstShown.x + 1;
            final int n2 = this.lastShown.y - this.firstShown.y + 1;
            this.blockSize = (width - 10) / n;
            final int blockSize = (height - 10) / n2;
            if (this.blockSize > blockSize) {
                this.blockSize = blockSize;
            }
            if (this.blockSize < 5) {
                this.blockSize = 5;
            }
            this.widthX = n * this.blockSize;
            this.widthY = n2 * this.blockSize;
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
        for (int i = this.firstShown.y - 1, offsetY = this.offsetY; i <= this.lastShown.y; ++i, offsetY += this.blockSize) {
            for (int j = this.firstShown.x - 1, offsetX = this.offsetX; j <= this.lastShown.x; ++j, offsetX += this.blockSize) {
                final int contents = board.getContents(j + 1, i + 1, 0);
                if (contents >= 0) {
                    graphics.setColor(this.getBlockColour(contents, component, array, b));
                    graphics.fillRect(offsetX, offsetY, this.blockSize, this.blockSize);
                }
                if (i < this.lastShown.y) {
                    if (contents != board.getContents(j, i + 1, 0)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(offsetX, offsetY, offsetX, offsetY + this.blockSize);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(offsetX, offsetY, offsetX, offsetY + this.blockSize);
                    }
                }
                if (j < this.lastShown.x) {
                    if (contents != board.getContents(j + 1, i, 0)) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(offsetX, offsetY, offsetX + this.blockSize, offsetY);
                    }
                    else if (contents == 0 || (b && contents == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(offsetX, offsetY, offsetX + this.blockSize, offsetY);
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
        graphics.drawRect(n4 + n * this.blockSize, n5 + n2 * this.blockSize, this.blockSize, this.blockSize);
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
            graphics.drawRect(n, n3, n2, n4);
        }
    }
    
    public void getRotate(final Coord coord, int i) {
        if (i >= this.numRotate) {
            coord.x = -coord.x;
            i -= this.numRotate;
        }
        while (i > 0) {
            final int x = coord.x;
            coord.x = coord.y;
            coord.y = -x;
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
