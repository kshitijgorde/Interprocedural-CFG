import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridTypeDrafter extends GridType
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
    
    public GridTypeDrafter() {
        this.blockSize = 0;
        this.firstShown = new Coord();
        this.lastShown = new Coord();
        this.numRotate = 6;
        this.numReflect = 12;
        this.numDim = 2;
        this.numTypes = 12;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        graphics.setColor(component.getBackground());
        graphics.fillRect(n, n2, this.width, this.height);
        graphics.setColor(component.getForeground());
        for (int i = n2 - 13; i < n2 + this.height; i += 52) {
            for (int j = n; j < n + this.width; j += 90) {
                graphics.drawLine(j, i - 26, j + 90, i - 26);
                graphics.drawLine(j, i, j + 90, i);
                graphics.drawLine(j, i - 26, j, i + 26);
                graphics.drawLine(j + 45, i - 26, j + 45, i + 26);
                graphics.drawLine(j, i, j + 15, i - 26);
                graphics.drawLine(j + 15, i - 26, j + 45, i + 26);
                graphics.drawLine(j + 45, i + 26, j + 75, i - 26);
                graphics.drawLine(j + 75, i - 26, j + 90, i);
                graphics.drawLine(j + 90, i, j + 75, i + 26);
                graphics.drawLine(j + 75, i + 26, j + 45, i - 26);
                graphics.drawLine(j + 45, i - 26, j + 15, i + 26);
                graphics.drawLine(j + 15, i + 26, j, i);
                graphics.drawLine(j, i, j + 45, i - 26);
                graphics.drawLine(j + 45, i - 26, j + 90, i);
                graphics.drawLine(j + 90, i, j + 45, i + 26);
                graphics.drawLine(j + 45, i + 26, j, i);
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
        int n5 = n / (this.blockSize * 6);
        n -= n5 * (this.blockSize * 6);
        if (n < 0) {
            n += this.blockSize * 6;
            --n5;
        }
        n2 -= n5 * this.blockHeight * 2;
        final int n6 = n4 + n5;
        int n7 = n2 / (this.blockHeight * 4);
        n2 -= n7 * (this.blockHeight * 4);
        if (n2 < 0) {
            n += this.blockHeight * 4;
            --n7;
        }
        int n8 = n3 + n7;
        int n9 = n6 + n7;
        n -= this.blockSize * 2;
        n2 -= this.blockHeight * 2;
        if (n2 < 0) {
            if ((n - this.blockSize * 4) * this.blockHeight - n2 * this.blockSize > 0) {
                --n8;
                n -= this.blockSize * 6;
                n2 += this.blockHeight * 2;
            }
        }
        else if ((n - this.blockSize * 4) * this.blockHeight + n2 * this.blockSize > 0) {
            ++n9;
            n -= this.blockSize * 6;
            n2 -= this.blockHeight * 2;
        }
        if (n8 < 0 || n9 < 0) {
            return false;
        }
        final int n10 = n9 + this.firstShown.x;
        final int n11 = n8 + this.firstShown.y;
        if (n10 > this.lastShown.x || n11 > this.lastShown.y) {
            return false;
        }
        int n12 = 0;
        if (n2 > 0) {
            n12 += 6;
            n = -n;
            n2 = -n2;
        }
        if (n > 0) {
            n12 += 3;
            final int n13 = n;
            n = n2;
            n2 = -n13;
        }
        if (n * this.blockHeight - n2 * this.blockSize > 0) {
            n12 += 2;
        }
        else if (n * this.blockSize - n2 * this.blockHeight > 0) {
            ++n12;
        }
        coord.set(n10, n11, n12);
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
                this.firstShown.sub(GridTypeDrafter.border);
                this.lastShown.add(GridTypeDrafter.border);
            }
            this.width = width;
            this.height = height;
            final int n = this.lastShown.x - this.firstShown.x + (this.lastShown.y - this.firstShown.y);
            this.blockSize = (width - 10) / (n * 6 + 8);
            this.blockHeight = (height - 10) / (n * 2 + 4);
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
            this.widthX = this.blockSize * (n * 6 + 8);
            this.widthY = this.blockHeight * (n * 2 + 4);
            this.offsetX = (width - this.widthX) / 2 + ((this.lastShown.y - this.firstShown.y) * 6 + 2) * this.blockSize;
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
        int n = this.offsetX + this.blockSize * 2;
        int n2 = this.offsetY + this.blockHeight * 2;
        int i = this.firstShown.y - 1;
        int n3 = n2;
        while (i <= this.lastShown.y) {
            for (int j = this.firstShown.x - 1, n4 = n; j <= this.lastShown.x; ++j, n3 += this.blockHeight * 2, n4 += this.blockSize * 6) {
                array2[0] = n4;
                array3[0] = n3;
                array2[1] = n4 - this.blockSize * 4;
                array3[1] = n3;
                array2[2] = n4 - this.blockSize * 3;
                array3[2] = n3 - this.blockHeight;
                final int contents = board.getContents(j + 1, i + 1, 0);
                if (contents >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (i < this.lastShown.y) {
                    final int contents2 = board.getContents(j, i + 1, 7);
                    if (contents != contents2) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents2 == 0 || (b && contents2 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                array2[1] = n4 - this.blockSize * 2;
                array3[1] = n3 - this.blockHeight * 2;
                final int contents3 = board.getContents(j + 1, i + 1, 1);
                if (contents3 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents3, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (i < this.lastShown.y) {
                    final int contents4 = board.getContents(j, i + 1, 6);
                    if (contents3 != contents4) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents4 == 0 || (b && contents4 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents3 != contents) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                    else if (contents3 == 0 || (b && contents3 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                }
                array2[2] = n4;
                array3[2] = n3 - this.blockHeight * 2;
                final int contents5 = board.getContents(j + 1, i + 1, 2);
                if (contents5 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents5, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if ((i != this.lastShown.y || j != this.firstShown.x - 1) && (i != this.firstShown.y - 1 || j != this.lastShown.x)) {
                    final int contents6 = board.getContents(j, i, 9);
                    if (contents5 != contents6) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents6 == 0 || (b && contents6 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents3 != contents5) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents3 == 0 || (b && contents3 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                array2[1] = n4 + this.blockSize * 2;
                array3[1] = n3 - this.blockHeight * 2;
                final int contents7 = board.getContents(j + 1, i + 1, 3);
                if (contents7 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents7, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if ((i != this.lastShown.y || j != this.firstShown.x - 1) && (i != this.firstShown.y - 1 || j != this.lastShown.x)) {
                    final int contents8 = board.getContents(j, i, 8);
                    if (contents7 != contents8) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents8 == 0 || (b && contents8 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents7 != contents5) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                    else if (contents7 == 0 || (b && contents7 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                }
                array2[2] = n4 + this.blockSize * 3;
                array3[2] = n3 - this.blockHeight;
                final int contents9 = board.getContents(j + 1, i + 1, 4);
                if (contents9 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents9, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (j < this.lastShown.x) {
                    final int contents10 = board.getContents(j + 1, i, 11);
                    if (contents9 != contents10) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents10 == 0 || (b && contents10 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents7 != contents9) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents7 == 0 || (b && contents7 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                array2[1] = n4 + this.blockSize * 4;
                array3[1] = n3;
                final int contents11 = board.getContents(j + 1, i + 1, 5);
                if (contents11 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents11, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (j < this.lastShown.x) {
                    final int contents12 = board.getContents(j + 1, i, 10);
                    if (contents11 != contents12) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                    else if (contents12 == 0 || (b && contents12 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[1], array3[1], array2[2], array3[2]);
                    }
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents11 != contents9) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                    else if (contents11 == 0 || (b && contents11 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                }
                array2[2] = n4 + this.blockSize * 3;
                array3[2] = n3 + this.blockHeight;
                final int contents13 = board.getContents(j + 1, i + 1, 6);
                if (contents13 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents13, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents11 != contents13) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents11 == 0 || (b && contents11 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                array2[1] = n4 + this.blockSize * 2;
                array3[1] = n3 + this.blockHeight * 2;
                final int contents14 = board.getContents(j + 1, i + 1, 7);
                if (contents14 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents14, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents14 != contents13) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                    else if (contents14 == 0 || (b && contents14 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                }
                array2[2] = n4;
                array3[2] = n3 + this.blockHeight * 2;
                final int contents15 = board.getContents(j + 1, i + 1, 8);
                if (contents15 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents15, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents14 != contents15) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents14 == 0 || (b && contents14 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                array2[1] = n4 - this.blockSize * 2;
                array3[1] = n3 + this.blockHeight * 2;
                final int contents16 = board.getContents(j + 1, i + 1, 9);
                if (contents16 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents16, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents16 != contents15) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                    else if (contents16 == 0 || (b && contents16 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                }
                array2[2] = n4 - this.blockSize * 3;
                array3[2] = n3 + this.blockHeight;
                final int contents17 = board.getContents(j + 1, i + 1, 10);
                if (contents17 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents17, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents16 != contents17) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents16 == 0 || (b && contents16 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
                array2[1] = n4 - this.blockSize * 4;
                array3[1] = n3;
                final int contents18 = board.getContents(j + 1, i + 1, 11);
                if (contents18 >= 0 && i < this.lastShown.y && j < this.lastShown.x) {
                    graphics.setColor(this.getBlockColour(contents18, component, array, b));
                    graphics.fillPolygon(array2, array3, 3);
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents18 != contents17) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                    else if (contents18 == 0 || (b && contents18 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[2], array3[2]);
                    }
                }
                if (i < this.lastShown.y && j < this.lastShown.x) {
                    if (contents18 != contents) {
                        graphics.setColor(Color.BLACK);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                    else if (contents18 == 0 || (b && contents18 == -1)) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawLine(array2[0], array3[0], array2[1], array3[1]);
                    }
                }
            }
            ++i;
            n2 += this.blockHeight * 2;
            n -= this.blockSize * 6;
            n3 = n2;
        }
    }
    
    public void paintCentredOutline(final Graphics graphics, final Coord[] array, int n, int n2, final int n3) {
        n -= this.blockSize * 2;
        n2 -= this.blockHeight * 2;
        if (array[n3].z == 0) {
            n += this.blockSize * 7 / 3;
            n2 += this.blockHeight / 3;
        }
        else if (array[n3].z == 1) {
            n += this.blockSize * 5 / 3;
            n2 += this.blockHeight;
        }
        else if (array[n3].z == 2) {
            n += this.blockSize * 2 / 3;
            n2 += this.blockHeight * 4 / 3;
        }
        else if (array[n3].z == 3) {
            n -= this.blockSize * 2 / 3;
            n2 += this.blockHeight * 4 / 3;
        }
        else if (array[n3].z == 4) {
            n -= this.blockSize * 5 / 3;
            n2 += this.blockHeight;
        }
        else if (array[n3].z == 5) {
            n -= this.blockSize * 7 / 3;
            n2 += this.blockHeight / 3;
        }
        else if (array[n3].z == 6) {
            n -= this.blockSize * 7 / 3;
            n2 -= this.blockHeight / 3;
        }
        else if (array[n3].z == 7) {
            n -= this.blockSize * 5 / 3;
            n2 -= this.blockHeight;
        }
        else if (array[n3].z == 8) {
            n -= this.blockSize * 2 / 3;
            n2 -= this.blockHeight * 4 / 3;
        }
        else if (array[n3].z == 9) {
            n += this.blockSize * 2 / 3;
            n2 -= this.blockHeight * 4 / 3;
        }
        else if (array[n3].z == 10) {
            n += this.blockSize * 5 / 3;
            n2 -= this.blockHeight;
        }
        else if (array[n3].z == 11) {
            n += this.blockSize * 7 / 3;
            n2 -= this.blockHeight / 3;
        }
        this.paintOutline(graphics, array, n, n2, n3);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.paintTileOutline(graphics, n3, n4 + (n - n2) * 6 * this.blockSize, n5 + (n + n2) * 2 * this.blockHeight);
    }
    
    protected void paintTileOutline(final Graphics graphics, final int n, int n2, int n3) {
        n2 += this.blockSize * 2;
        n3 += this.blockHeight * 2;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = n % 6;
        if (n8 >= 3) {
            n8 = 5 - n8;
        }
        if (n8 == 0) {
            n4 -= this.blockSize * 4;
            n6 -= this.blockSize * 3;
            n7 -= this.blockHeight;
        }
        else if (n8 == 1) {
            n4 -= this.blockSize * 3;
            n5 -= this.blockHeight;
            n6 -= this.blockSize * 2;
            n7 -= this.blockHeight * 2;
        }
        else if (n8 == 2) {
            n4 -= this.blockSize * 2;
            n5 -= this.blockHeight * 2;
            n7 -= this.blockHeight * 2;
        }
        if (n >= 6) {
            n4 = -n4;
            n5 = -n5;
            n6 = -n6;
            n7 = -n7;
            n8 += 6;
        }
        if (n != n8) {
            n4 = -n4;
            n6 = -n6;
        }
        final int n9 = n4 + n2;
        final int n10 = n6 + n2;
        final int n11 = n5 + n3;
        final int n12 = n7 + n3;
        graphics.drawLine(n2, n3, n9, n11);
        graphics.drawLine(n9, n11, n10, n12);
        graphics.drawLine(n10, n12, n2, n3);
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
            int n9 = this.offsetX + (n5 - n7) * this.blockSize * 6;
            int n10 = this.offsetY + (n5 + n7) * this.blockHeight * 2;
            if (n5 == n6 && n7 == n8 && coord.z == coord2.z) {
                this.paintTileOutline(graphics, coord2.z, n9, n10);
            }
            else {
                for (int i = n5; i <= n6; ++i, n10 += this.blockHeight * 2, n9 += this.blockSize * 6) {
                    graphics.drawLine(n9, n10, n9 + this.blockSize * 4, n10);
                    graphics.drawLine(n9 + this.blockSize * 4, n10, n9 + this.blockSize * 6, n10 + this.blockHeight * 2);
                }
                for (int j = n7; j <= n8; ++j, n10 += this.blockHeight * 2, n9 -= this.blockSize * 6) {
                    graphics.drawLine(n9, n10, n9 - this.blockSize * 2, n10 + this.blockHeight * 2);
                    graphics.drawLine(n9 - this.blockSize * 2, n10 + this.blockHeight * 2, n9 - this.blockSize * 6, n10 + this.blockHeight * 2);
                }
                for (int k = n5; k < n6; ++k, n10 -= this.blockHeight * 2, n9 -= this.blockSize * 6) {
                    graphics.drawLine(n9, n10, n9 - this.blockSize * 2, n10 - this.blockHeight * 2);
                    graphics.drawLine(n9 - this.blockSize * 2, n10 - this.blockHeight * 2, n9 - this.blockSize * 6, n10 - this.blockHeight * 2);
                }
                graphics.drawLine(n9, n10, n9 - this.blockSize * 2, n10 - this.blockHeight * 2);
                graphics.drawLine(n9 - this.blockSize * 2, n10 - this.blockHeight * 2, n9, n10 - this.blockHeight * 4);
                for (int n11 = n10 - this.blockHeight * 4, l = n7; l < n8; ++l, n11 -= this.blockHeight * 2, n9 += this.blockSize * 6) {
                    graphics.drawLine(n9, n11, n9 + this.blockSize * 4, n11);
                    graphics.drawLine(n9 + this.blockSize * 4, n11, n9 + this.blockSize * 6, n11 - this.blockHeight * 2);
                }
            }
        }
    }
    
    public void getRotate(final Coord coord, int i) {
        if (i >= this.numRotate) {
            final int x = coord.x;
            coord.x = -coord.y;
            coord.y = -x;
            coord.z = 11 - coord.z;
            i -= this.numRotate;
        }
        while (i > 0) {
            final int x2 = coord.x;
            coord.x = x2 - coord.y;
            coord.y = x2;
            coord.z += ((coord.z > 9) ? -10 : 2);
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
            coord3.z += ((coord2.z == 11) ? -11 : 1);
        }
        else if (n == 1) {
            final Coord coord4 = coord2;
            coord4.z += ((coord2.z == 0) ? 11 : -1);
        }
        else if (n == 2) {
            if (coord.z == 0) {
                coord2.z = 7;
                final Coord coord5 = coord2;
                --coord5.x;
            }
            else if (coord.z == 1) {
                coord2.z = 6;
                final Coord coord6 = coord2;
                --coord6.x;
            }
            else if (coord.z == 2) {
                coord2.z = 9;
                final Coord coord7 = coord2;
                --coord7.x;
                final Coord coord8 = coord2;
                --coord8.y;
            }
            else if (coord.z == 3) {
                coord2.z = 8;
                final Coord coord9 = coord2;
                --coord9.x;
                final Coord coord10 = coord2;
                --coord10.y;
            }
            else if (coord.z == 4) {
                coord2.z = 11;
                final Coord coord11 = coord2;
                --coord11.y;
            }
            else if (coord.z == 5) {
                coord2.z = 10;
                final Coord coord12 = coord2;
                --coord12.y;
            }
            else if (coord.z == 6) {
                coord2.z = 1;
                final Coord coord13 = coord2;
                ++coord13.x;
            }
            else if (coord.z == 7) {
                coord2.z = 0;
                final Coord coord14 = coord2;
                ++coord14.x;
            }
            else if (coord.z == 8) {
                coord2.z = 3;
                final Coord coord15 = coord2;
                ++coord15.x;
                final Coord coord16 = coord2;
                ++coord16.y;
            }
            else if (coord.z == 9) {
                coord2.z = 2;
                final Coord coord17 = coord2;
                ++coord17.x;
                final Coord coord18 = coord2;
                ++coord18.y;
            }
            else if (coord.z == 10) {
                coord2.z = 5;
                final Coord coord19 = coord2;
                ++coord19.y;
            }
            else if (coord.z == 11) {
                coord2.z = 4;
                final Coord coord20 = coord2;
                ++coord20.y;
            }
        }
        return coord2;
    }
    
    static {
        border = new Coord(1, 1, 0);
    }
}
