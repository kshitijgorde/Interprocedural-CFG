import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.FontMetrics;
import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class nTextArea
{
    private int x;
    private int y;
    private int width;
    private int height;
    private int numrows;
    private Vector names;
    private Vector rows;
    boolean lock;
    int rowHeight;
    int columnStart;
    int ascent;
    int offset;
    int visibleRows;
    Point m;
    FontMetrics fm;
    
    nTextArea(final int x, final int y, final int width, final int height, final FontMetrics fm, final Applet applet) {
        this.names = new Vector();
        this.rows = new Vector();
        this.columnStart = 0;
        this.offset = 0;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fm = fm;
        this.rowHeight = fm.getHeight();
        this.ascent = fm.getAscent() + fm.getLeading();
        this.visibleRows = height / this.rowHeight;
    }
    
    public void addRow(final String s, final String s2) {
        this.names.addElement(s);
        if (this.fm.stringWidth(s2) > this.width - this.fm.stringWidth(s)) {
            final char[] charArray = s2.toCharArray();
            final int n = 0;
            int n2 = s2.length() - 1;
            System.out.println(n2);
            while (this.fm.charsWidth(charArray, n, n2) > this.width - this.fm.stringWidth(s)) {
                --n2;
            }
            while (charArray[n + n2 - 1] != ' ') {
                --n2;
            }
            this.rows.addElement(new String(charArray, n, n2));
            ++this.numrows;
            int n3;
            int n4;
            for (n3 = n + n2, n4 = charArray.length - n3; this.fm.charsWidth(charArray, n3, n4) > this.width; n3 += n4, n4 = charArray.length - n3) {
                while (this.fm.charsWidth(charArray, n3, n4) > this.width) {
                    --n4;
                }
                this.names.addElement("");
                while (charArray[n3 + n4 - 1] != ' ') {
                    --n4;
                }
                this.rows.addElement(new String(charArray, n3, n4));
                ++this.numrows;
            }
            this.names.addElement("");
            this.rows.addElement(new String(charArray, n3, n4));
            ++this.numrows;
        }
        else {
            this.rows.addElement(s2);
            ++this.numrows;
        }
    }
    
    public void render(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(this.x, this.y, this.width, this.height);
        graphics.setColor(Color.black);
        graphics.drawRect(this.x, this.y, this.width, this.height);
        for (int n = 0; n < this.numrows && n * this.rowHeight + this.y + this.rowHeight <= this.y + this.height; ++n) {
            graphics.drawString((String)this.names.elementAt(n + this.offset), this.x + 2, n * this.rowHeight + this.y + this.ascent);
            this.columnStart = this.fm.stringWidth((String)this.names.elementAt(n + this.offset));
            graphics.setColor(Color.black);
            graphics.drawString((String)this.rows.elementAt(n + this.offset), this.x + 2 + this.columnStart, n * this.rowHeight + this.y + this.ascent);
        }
    }
}
