import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class WSGrid
{
    static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final int MAX = 100;
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean select;
    public int startX;
    public int startY;
    public int endX;
    public int endY;
    public int size;
    private int rows;
    private int cols;
    private Font font;
    private FontMetrics fm;
    private Color fgColor;
    private Color bgColor;
    private Color bdColor;
    private Color fdColor;
    private Color hiColor;
    private char[][] grid;
    
    public WSGrid(final int rows, final int cols, final int size, final Font font) {
        this.fgColor = Color.black;
        this.bgColor = Color.white;
        this.bdColor = Color.black;
        this.fdColor = Color.black;
        this.hiColor = Color.black;
        this.x = 0;
        this.y = 0;
        this.rows = rows;
        this.cols = cols;
        this.font = font;
        this.size = size;
        this.select = false;
        this.width = cols * this.size;
        this.height = rows * this.size;
    }
    
    public void setColors(final Color fgColor, final Color bgColor, final Color bdColor, final Color fdColor, final Color hiColor) {
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.bdColor = bdColor;
        this.fdColor = fdColor;
        this.hiColor = hiColor;
    }
    
    public void clear() {
        this.grid = new char[this.rows][this.cols];
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                this.grid[i][j] = ' ';
            }
        }
    }
    
    public boolean inside(final int n, final int n2) {
        return n > this.x && n < this.x + this.width && n2 > this.y && n2 < this.y + this.height;
    }
    
    public void fill() {
        this.clear();
        for (int i = 0; i < WordSearch.words.size(); ++i) {
            final WSWord wsWord = WordSearch.words.elementAt(i);
            final int n = (int)(Math.random() * this.rows);
            final int n2 = (int)(Math.random() * this.cols);
            boolean addWord = false;
            if (wsWord.grid.length() <= Math.max(this.rows, this.cols)) {
                int n3 = n;
                int n4 = n2;
                do {
                    addWord = this.addWord(wsWord, n3, n4);
                    if (!addWord && ++n4 > this.cols - 1) {
                        n4 = 0;
                        if (++n3 <= this.rows - 1) {
                            continue;
                        }
                        n3 = 0;
                    }
                } while (!addWord && (n3 != n || n4 != n2));
            }
            if (!addWord) {
                WordSearch.words.removeElementAt(i);
                --i;
            }
        }
        for (int j = 0; j < this.rows; ++j) {
            for (int k = 0; k < this.cols; ++k) {
                if (this.grid[j][k] == ' ') {
                    this.grid[j][k] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int)(Math.random() * "ABCDEFGHIJKLMNOPQRSTUVWXYZ".length()));
                }
            }
        }
    }
    
    private boolean addWord(final WSWord wsWord, final int n, final int n2) {
        int n3 = (int)(Math.random() * 3.0) - 1;
        int n4 = (int)(Math.random() * 3.0) - 1;
        int n5 = 0;
        boolean b;
        int n6;
        int n7;
        do {
            b = true;
            n6 = n + (wsWord.grid.length() - 1) * n3;
            n7 = n2 + (wsWord.grid.length() - 1) * n4;
            if ((n4 != 0 || n3 != 0) && n6 >= 0 && n6 < this.rows && n7 >= 0 && n7 < this.cols) {
                for (int i = 0; i < wsWord.grid.length(); ++i) {
                    final char c = this.grid[n + i * n3][n2 + i * n4];
                    if (c != ' ' && c != wsWord.grid.charAt(i)) {
                        b = false;
                    }
                }
            }
            else {
                b = false;
            }
            if (!b && ++n4 > 1) {
                n4 = -1;
                if (++n3 > 1) {
                    n3 = -1;
                }
            }
            ++n5;
        } while (!b && n5 <= 8);
        if (b) {
            for (int j = 0; j < wsWord.grid.length(); ++j) {
                this.grid[n + j * n3][n2 + j * n4] = wsWord.grid.charAt(j);
            }
            wsWord.endpt1 = new Point(n2, n);
            wsWord.endpt2 = new Point(n7, n6);
        }
        return b;
    }
    
    public boolean checkSelection() {
        final int min = Math.min(Math.max(this.startX / this.size, 0), this.cols - 1);
        final int min2 = Math.min(Math.max(this.startY / this.size, 0), this.rows - 1);
        final int min3 = Math.min(Math.max(this.endX / this.size, 0), this.cols - 1);
        final int min4 = Math.min(Math.max(this.endY / this.size, 0), this.rows - 1);
        final Point point = new Point(min, min2);
        final Point point2 = new Point(min3, min4);
        for (int i = 0; i < WordSearch.words.size(); ++i) {
            final WSWord wsWord = WordSearch.words.elementAt(i);
            if ((wsWord.endpt1.equals(point) && wsWord.endpt2.equals(point2)) || (wsWord.endpt1.equals(point2) && wsWord.endpt2.equals(point))) {
                return wsWord.found = true;
            }
        }
        return false;
    }
    
    public void draw(final Graphics graphics) {
        graphics.setColor(this.bgColor);
        graphics.fillRect(this.x, this.y, this.width, this.height);
        graphics.setColor(this.bdColor);
        graphics.drawRect(this.x, this.y, this.width - 1, this.height - 1);
        graphics.setColor(this.fdColor);
        for (int i = 0; i < WordSearch.words.size(); ++i) {
            final WSWord wsWord = WordSearch.words.elementAt(i);
            if (wsWord.found) {
                graphics.fillPolygon(this.getPolygon(this.x + this.size * wsWord.endpt1.x + this.size / 2, this.y + this.size * wsWord.endpt1.y + this.size / 2, this.x + this.size * wsWord.endpt2.x + this.size / 2, this.y + this.size * wsWord.endpt2.y + this.size / 2));
            }
        }
        if (WordSearch.words.size() > 0 && this.select) {
            final Polygon polygon = this.getPolygon(this.size * Math.min(Math.max(this.startX / this.size, 0), this.cols - 1) + this.size / 2, this.size * Math.min(Math.max(this.startY / this.size, 0), this.rows - 1) + this.size / 2, this.size * Math.min(Math.max(this.endX / this.size, 0), this.cols - 1) + this.size / 2, this.size * Math.min(Math.max(this.endY / this.size, 0), this.rows - 1) + this.size / 2);
            graphics.setColor(this.hiColor);
            graphics.fillPolygon(polygon);
            graphics.setColor(this.fgColor);
            graphics.drawPolygon(polygon);
            graphics.drawLine(polygon.xpoints[polygon.npoints - 1], polygon.ypoints[polygon.npoints - 1], polygon.xpoints[0], polygon.ypoints[0]);
        }
        graphics.setFont(this.font);
        graphics.setColor(this.fgColor);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        for (int j = 0; j < this.rows; ++j) {
            for (int k = 0; k < this.cols; ++k) {
                final Character c = new Character(this.grid[j][k]);
                graphics.drawString(c.toString(), this.x + k * this.size + (this.size - fontMetrics.stringWidth(c.toString())) / 2, this.y + j * this.size + (this.size - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent());
            }
        }
        graphics.setColor(this.fgColor);
        for (int l = 0; l < WordSearch.words.size(); ++l) {
            final WSWord wsWord2 = WordSearch.words.elementAt(l);
            if (wsWord2.found) {
                final Polygon polygon2 = this.getPolygon(this.x + this.size * wsWord2.endpt1.x + this.size / 2, this.y + this.size * wsWord2.endpt1.y + this.size / 2, this.x + this.size * wsWord2.endpt2.x + this.size / 2, this.y + this.size * wsWord2.endpt2.y + this.size / 2);
                graphics.drawPolygon(polygon2);
                graphics.drawLine(polygon2.xpoints[polygon2.npoints - 1], polygon2.ypoints[polygon2.npoints - 1], polygon2.xpoints[0], polygon2.ypoints[0]);
            }
        }
    }
    
    private Polygon getPolygon(final int n, final int n2, final int n3, final int n4) {
        final double n5 = n3 - n;
        final double n6 = n4 - n2;
        double atan = 0.0;
        if (n5 == 0.0) {
            if (n6 < 0.0) {
                atan = 1.5707963267948966;
            }
            else if (n6 > 0.0) {
                atan = 4.71238898038469;
            }
        }
        else if (n6 == 0.0) {
            if (n5 > 0.0) {
                atan = 0.0;
            }
            else {
                atan = 3.141592653589793;
            }
        }
        else {
            atan = Math.atan(Math.abs(n6 / n5));
            if (n5 < 0.0 && n6 < 0.0) {
                atan = 3.141592653589793 - atan;
            }
            if (n5 < 0.0 && n6 > 0.0) {
                atan += 3.141592653589793;
            }
            if (n5 > 0.0 && n6 > 0.0) {
                atan = 6.283185307179586 - atan;
            }
        }
        final double n7 = atan + 1.5707963267948966;
        final double n8 = n7 + 3.141592653589793;
        final Polygon polygon = new Polygon();
        final int n9 = (int)Math.round(this.size * Math.sin(0.7853981633974483) / 2.0);
        for (double n10 = n7; n10 < n7 + 3.141592653589793; n10 += 0.10471975511965977) {
            polygon.addPoint((int)Math.round(n + n9 * Math.cos(n10)), (int)Math.round(n2 - n9 * Math.sin(n10)));
        }
        polygon.addPoint((int)Math.round(n + n9 * Math.cos(n8)), (int)Math.round(n2 - n9 * Math.sin(n8)));
        for (double n11 = n8; n11 < n8 + 3.141592653589793; n11 += 0.10471975511965977) {
            polygon.addPoint((int)Math.round(n3 + n9 * Math.cos(n11)), (int)Math.round(n4 - n9 * Math.sin(n11)));
        }
        polygon.addPoint((int)Math.round(n3 + n9 * Math.cos(n7)), (int)Math.round(n4 - n9 * Math.sin(n7)));
        return polygon;
    }
}
