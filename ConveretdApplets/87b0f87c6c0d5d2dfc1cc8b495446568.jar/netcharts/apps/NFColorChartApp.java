// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import netcharts.util.NFColor;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import netcharts.graphics.NFActiveLabel;
import java.util.StringTokenizer;
import netcharts.util.NFDebug;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Vector;
import netcharts.graphics.NFLabel;
import netcharts.graphics.NFRegion;
import netcharts.graphics.NFActiveRegion;
import netcharts.graphics.NFDwellObserver;
import java.applet.Applet;

public class NFColorChartApp extends Applet implements NFDwellObserver
{
    public boolean showFillWhenSelected;
    public int numColumns;
    private String[] a;
    private String b;
    private NFActiveRegion c;
    private NFRegion d;
    private NFLabel e;
    private Vector f;
    private Image g;
    private Dimension h;
    private Component i;
    private String j;
    
    public NFColorChartApp() {
        this.showFillWhenSelected = true;
        this.numColumns = 4;
        this.b = "black, white, blue, lightblue,red, pink, green, lightgreen,cyan, magenta, yellow, orange,slategray, darkgray, gray, lightgray";
        this.d = new NFRegion();
        this.e = new NFLabel();
        this.f = new Vector();
        this.g = null;
        this.h = new Dimension();
        this.i = null;
        this.j = null;
    }
    
    public void init() {
        this.init(this);
    }
    
    public void init(final Component i) {
        this.i = i;
        this.c = new NFActiveRegion(100L, this);
        this.e.setColor(Color.black);
        this.d.setColor(Color.yellow);
        this.e.setRegion(this.d);
        this.c.setLabel(this.e, i);
        this.c.setClickCount(1);
        if (i instanceof Applet) {
            this.a((Applet)i);
        }
        this.a();
    }
    
    public void addObserver(final NFDwellObserver nfDwellObserver) {
        this.c.addObserver(nfDwellObserver);
    }
    
    public void setLabel(final NFLabel e) {
        this.e = e;
        this.c.setLabel(e, this);
    }
    
    public void setRegion(final NFRegion nfRegion) {
        this.d = nfRegion;
        this.e.setRegion(nfRegion);
    }
    
    public void setColorTable(final String b) {
        this.b = b;
        this.a();
    }
    
    private void a(final Applet applet) {
        if (applet == null) {
            NFDebug.print("NFColorChartApp: No Applet Specified");
        }
        final String parameter = applet.getParameter("NumColumns");
        if (parameter != null) {
            this.numColumns = Integer.parseInt(parameter);
        }
        final String parameter2 = applet.getParameter("ColorTable");
        if (parameter2 != null) {
            this.b = parameter2;
        }
    }
    
    private void a() {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.b, " ,\n\t");
        if (stringTokenizer.countTokens() == 0) {
            NFDebug.print("NFColorChartApp: No colors defined");
            return;
        }
        this.a = new String[stringTokenizer.countTokens()];
        this.c.removeAllLabels();
        this.f = new Vector();
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            this.a[n] = stringTokenizer.nextToken();
            final NFActiveLabel nfActiveLabel = new NFActiveLabel(this.a[n++], null, null);
            this.f.addElement(nfActiveLabel);
            this.c.addLabel(nfActiveLabel);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.c.mousePos(n, n2);
        return false;
    }
    
    public void stop() {
        this.c.stop();
    }
    
    public void start() {
        this.c.start();
    }
    
    public boolean dwellDisplay(final boolean b, final NFActiveLabel nfActiveLabel) {
        if (b && this.j != null) {
            return true;
        }
        if (!b) {
            this.repaint();
        }
        return false;
    }
    
    public boolean dwellPress(final Event event, final int n, final int n2, final NFActiveLabel nfActiveLabel) {
        if (!this.showFillWhenSelected) {
            return false;
        }
        this.j = nfActiveLabel.label;
        this.repaint();
        return false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.bounds();
        if (this.j != null) {
            Color color = NFColor.get(this.j);
            if (color == null) {
                NFDebug.print("NFColorChartApp: Unknown color <" + this.j + ">");
                color = Color.red;
            }
            graphics.setColor(color);
            graphics.fillRect(0, 0, bounds.width, bounds.height);
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
            this.e.draw(graphics, bounds.width / 2, bounds.height / 2, this.j);
            return;
        }
        if (this.g == null || bounds.width != this.h.width || bounds.height != this.h.height) {
            if (this.g != null) {
                this.g.flush();
            }
            this.g = this.createImage(bounds.width, bounds.height);
            this.h = new Dimension(bounds.width, bounds.height);
            final Graphics graphics2 = this.g.getGraphics();
            this.draw(graphics2, this.h);
            graphics2.dispose();
            this.e.setParentBounds(bounds);
        }
        graphics.drawImage(this.g, 0, 0, null);
    }
    
    public void draw(final Graphics graphics, final Dimension dimension) {
        final Color white = Color.white;
        final Color black = Color.black;
        final int numColumns = this.numColumns;
        int n = this.a.length / numColumns;
        if (this.a.length % numColumns != 0) {
            ++n;
        }
        final double n2 = dimension.width / numColumns;
        final double n3 = dimension.height / n;
        int n4 = (int)n2;
        int n5 = (int)n3;
        if (n2 > n4) {
            ++n4;
        }
        if (n3 > n5) {
            ++n5;
        }
        graphics.setColor(Color.lightGray);
        graphics.fillRect(0, 0, dimension.width, dimension.height);
        int n6 = 0;
        try {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < numColumns; ++j) {
                    final int n7 = (int)(j * n2);
                    final int n8 = (int)(i * n3);
                    this.f.elementAt(n6).setBounds(n7, n8, n4, n5);
                    Color color = NFColor.get(this.a[n6]);
                    if (color == null) {
                        NFDebug.print("NFColorChartApp: Unknown color <" + this.a[n6] + ">");
                        color = Color.red;
                    }
                    graphics.setColor(color);
                    graphics.fillRect(n7, n8, n4, n5);
                    ++n6;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        graphics.setColor(black);
        for (int k = 0; k < n; ++k) {
            final int n9 = (int)(k * n3);
            graphics.drawLine(0, n9, dimension.width - 1, n9);
        }
        graphics.drawLine(0, dimension.height - 1, dimension.width - 1, dimension.height - 1);
        for (int l = 0; l < numColumns; ++l) {
            final int n10 = (int)(l * n2);
            graphics.drawLine(n10, 0, n10, dimension.height - 1);
        }
        graphics.drawLine(dimension.width - 1, 0, dimension.width - 1, dimension.height - 1);
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        return this.c.mouseDown(event, n, n2);
    }
    
    public synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.j != null) {
            this.j = null;
            this.repaint();
        }
        return false;
    }
}
