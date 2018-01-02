import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorList extends Panel
{
    Image \u00e1;
    Color \u00e2;
    Vector \u00e3;
    final int \u00e4 = 100;
    int \u00e5;
    int \u00e6;
    int \u00e7;
    int \u00e8;
    int \u00e9;
    int \u00ea;
    int \u00eb;
    int \u00ec;
    Color \u00ed;
    Color \u00ee;
    
    public ColorList() {
        this.\u00e5 = -1;
        this.\u00ea = 0;
        this.\u00eb = 0;
        this.\u00ec = 0;
        this.\u00ed = new Color(127, 127, 127);
        this.\u00ee = new Color(223, 223, 223);
        this.setLayout(null);
        this.setBackground(this.\u00e2 = Color.white);
        this.\u00e3 = new Vector(100);
        this.\u00e6 = 0;
        this.\u00e7 = 0;
        this.\u00e8 = 0;
        this.\u00e9 = 0;
    }
    
    void \u00e1(final String s) {
        this.\u00e2(new ListItem(s));
    }
    
    void \u00e1(final String s, final Color color) {
        this.\u00e2(new ListItem(s, color));
    }
    
    void \u00e2(final ListItem listItem) {
        final FontMetrics fontMetrics = this.getFontMetrics(listItem.\u0129());
        final int height = fontMetrics.getHeight();
        final int ascent = fontMetrics.getAscent();
        final int stringWidth = fontMetrics.stringWidth(listItem.getText());
        listItem.\u012e(ascent);
        listItem.\u0130(stringWidth);
        this.\u00ea = Math.max(stringWidth + listItem.\u012b(), this.\u00ea);
        this.\u00eb = 2 + (this.\u00e3.size() + 1) * height;
        listItem.\u0128(2, 2 + this.\u00e3.size() * height, this.size().width - 4, height);
        this.\u00e3.addElement(listItem);
        this.repaint();
    }
    
    void \u00e3() {
        for (int i = this.\u00e3.size() - 1; i >= 0; --i) {
            this.\u00e4(i);
        }
        this.\u00e3.removeAllElements();
        this.\u00e3.trimToSize();
        this.\u00e5 = -1;
    }
    
    boolean \u00e4(final int n) {
        if (n < this.\u00e3.size()) {
            final ListItem listItem = this.\u00e3.elementAt(n);
            final int \u012d = listItem.\u012c();
            this.\u00eb -= listItem.\u012a();
            this.\u00e3.removeElementAt(n);
            if (\u012d == this.\u00ea) {
                this.\u00ea = this.\u00e5();
            }
            if (this.\u00e5 == n) {
                this.\u00e5 = -1;
            }
            if (this.\u00e5 > n) {
                --this.\u00e5;
            }
            return true;
        }
        return false;
    }
    
    boolean \u00e4(final String s) {
        for (int i = 0; i < this.\u00e3.size(); ++i) {
            if (((ListItem)this.\u00e3.elementAt(i)).getText().equals(s)) {
                this.\u00e4(i);
                return true;
            }
        }
        return false;
    }
    
    int \u00e5() {
        int max = 0;
        for (int i = 0; i < this.\u00e3.size(); ++i) {
            max = Math.max(max, ((ListItem)this.\u00e3.elementAt(i)).\u012c());
        }
        return max;
    }
    
    int \u00e6() {
        return this.\u00e5;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if ((char)n == '\u03ec' || (char)n == '\u03ed') {
            if (this.\u00e5 > 0 && (char)n == '\u03ec') {
                this.\u00e3.elementAt(this.\u00e5).deselect();
                --this.\u00e5;
                this.\u00e3.elementAt(this.\u00e5).select();
            }
            if ((char)n == '\u03ed' && this.\u00e5 < this.\u00e3.size() - 1) {
                this.\u00e3.elementAt(this.\u00e5).deselect();
                ++this.\u00e5;
                this.\u00e3.elementAt(this.\u00e5).select();
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int i = this.\u00ec; i < this.\u00e3.size(); ++i) {
            final ListItem listItem = this.\u00e3.elementAt(i);
            if (n > listItem.x && n2 > listItem.y && n < listItem.x + listItem.\u00e6 && n2 < listItem.y + listItem.\u00e7) {
                if (this.\u00e5 >= 0 && this.\u00e5 != i) {
                    this.\u00e3.elementAt(this.\u00e5).deselect();
                    listItem.select();
                }
                this.\u00e5 = i;
                this.postEvent(new Event(this, 1001, "ColorList"));
                this.repaint();
                return false;
            }
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00e1 == null) {
            this.\u00e1 = this.createImage(this.\u00e6, this.\u00e7);
        }
        final Graphics graphics2 = this.\u00e1.getGraphics();
        graphics2.setColor(Color.white);
        graphics2.fillRect(0, 0, this.\u00e6, this.\u00e7);
        for (int i = this.\u00ec; i < this.\u00e3.size(); ++i) {
            final ListItem listItem = this.\u00e3.elementAt(i);
            final int x = listItem.x;
            final int y = listItem.y;
            if (listItem.\u00e5) {
                graphics2.setColor(listItem.\u0128);
            }
            else {
                graphics2.setColor(listItem.\u00e2);
            }
            graphics2.fillRect(x, y, listItem.\u00e6, listItem.\u00e7);
            graphics2.setColor(listItem.\u0129);
            graphics2.drawString(listItem.\u0104, x + 3 - listItem.\u012d, y + listItem.\u012b);
        }
        graphics2.setColor(Color.white);
        graphics2.drawRect(0, 0, this.\u00e6 - 1, this.\u00e7 - 1);
        graphics2.setColor(this.\u00ee);
        graphics2.drawRect(1, 1, this.\u00e6 - 3, this.\u00e7 - 3);
        graphics2.setColor(this.\u00ed);
        graphics2.drawLine(0, 0, this.\u00e6 - 2, 0);
        graphics2.drawLine(0, 0, 0, this.\u00e7 - 2);
        graphics2.setColor(Color.black);
        graphics2.drawLine(1, 1, this.\u00e6 - 2, 1);
        graphics2.drawLine(1, 1, 1, this.\u00e7 - 2);
        graphics.drawImage(this.\u00e1, 0, 0, null);
    }
    
    public void reshape(final int n, final int n2, final int \u00e6, final int \u00e7) {
        super.reshape(n, n2, \u00e6, \u00e7);
        this.\u00e6 = \u00e6;
        this.\u00e7 = \u00e7;
        this.\u00e8 = this.\u00e6 - 4;
        this.\u00e9 = this.\u00e7 - 4;
    }
    
    void select(final int \u00e5) {
        if (\u00e5 >= 0) {
            this.\u00e3.elementAt(\u00e5).select();
            this.\u00e5 = \u00e5;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
