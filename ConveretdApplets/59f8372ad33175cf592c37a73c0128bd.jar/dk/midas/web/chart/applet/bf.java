// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Container;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.net.URL;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Panel;

public class bf extends Panel
{
    Image try;
    int width;
    int height;
    int new;
    int for;
    av parent;
    Applet int;
    URL a;
    String byte;
    a do;
    Rectangle case;
    Applet if;
    
    public bf(final av parent, final String s, final String s2, final String byte1, final Applet int1) {
        this.try = null;
        this.width = 0;
        this.height = 0;
        this.new = 0;
        this.for = 0;
        this.a = null;
        this.byte = "_blank";
        this.do = null;
        this.case = null;
        this.byte = byte1;
        String string = s;
        this.parent = parent;
        URL url = null;
        this.int = int1;
        if (string.indexOf("http://") < 0) {
            string = this.int.getCodeBase().toString() + string;
        }
        try {
            url = new URL(string);
        }
        catch (Exception ex) {
            System.out.println("Couldn't create URL for " + url + "  " + ex);
        }
        if (url != null) {
            this.try = this.int.getImage(url);
            this.width = this.try.getWidth(this.int);
            this.height = this.try.getHeight(this.int) + 5;
        }
        if (s2 != null && !s2.equals("")) {
            try {
                this.a = new URL(s2);
            }
            catch (Exception ex2) {
                System.out.println("Couldn't create URL for " + this.a + "  " + ex2);
            }
        }
    }
    
    public void a(final av parent) {
        this.parent = parent;
    }
    
    public Dimension getSize() {
        this.width = this.try.getWidth(this.int);
        this.height = this.try.getHeight(this.int);
        this.case = this.a();
        return new Dimension(this.width, this.height);
    }
    
    public boolean contains(final int n, final int n2) {
        this.getSize();
        final boolean b = this.case.contains(n, n2) && this.a != null;
        if (b) {
            this.parent.setCursor(new Cursor(12));
        }
        else {
            this.parent.setCursor(new Cursor(0));
        }
        return b;
    }
    
    public void a(final int new1, final int for1) {
        this.new = new1;
        this.for = for1;
    }
    
    public Rectangle a() {
        return new Rectangle(this.new, this.for, this.width, this.height);
    }
    
    public void do() {
        if (this.a != null) {
            this.int.getAppletContext().showDocument(this.a, this.byte);
        }
    }
    
    public void a(final Graphics graphics) {
        if (this.try != null) {
            graphics.drawImage(this.try, this.new, this.for, this.parent);
            if (this.do != null) {
                this.removeMouseListener(this.do);
            }
            this.addMouseListener(this.do = new a());
        }
    }
    
    private void if() {
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Applet); parent = parent.getParent()) {}
        if (parent != null) {
            this.if = (Applet)parent;
        }
    }
    
    class a extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            if (bf.this.a != null) {
                bf.this.setCursor(new Cursor(12));
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            bf.this.setCursor(new Cursor(0));
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (bf.this.a != null) {
                bf.this.int.getAppletContext().showDocument(bf.this.a, bf.this.byte);
            }
        }
    }
}
