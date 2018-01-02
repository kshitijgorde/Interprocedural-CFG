// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import KJEgui.GetGraphics;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

public class Title
{
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    protected int _iOrientation;
    protected int _iWidth;
    protected int _iHeight;
    private int W;
    private int H;
    private int D;
    private String s;
    private Font f;
    private Color c;
    private Image i;
    public boolean b;
    
    public Title(final int iOrientation, final String s) {
        this.b = true;
        this._iOrientation = iOrientation;
        this.s = s;
        this.f = new Font("helvetica", 1, 13);
        this.c = Color.black;
    }
    
    public String getProperty() {
        return Graph.putStr(this.s);
    }
    
    public void paint(final Graphics text, final int n, final int n2, final int n3, final int n4, final Component component) {
        final int n5 = n + (n3 - this._iWidth) / 2;
        final int n6 = n2 + (n4 - this._iHeight) / 2;
        text.setFont(this.f);
        text.setColor(this.c);
        GetGraphics.setText(text);
        if (this.H != 0) {
            if (this._iOrientation == 1) {
                text.drawImage(this.i, n5, n6, component);
            }
            else {
                text.drawString(this.s, n5, n6 + this.W - this.D);
            }
        }
    }
    
    public void setColor(final Color c) {
        if (!this.c.equals(c)) {
            this.c = c;
            this.b = true;
        }
    }
    
    public void setFont(final Font f) {
        if (!this.f.equals(f)) {
            this.f = f;
            this.b = true;
        }
    }
    
    public void setProperty(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            switch (n) {
                case 0: {
                    this.setText(Graph.sgv(nextToken, ""));
                    break;
                }
            }
            ++n;
        }
        this.b = true;
    }
    
    public void setText(final String s) {
        if (!this.s.equals(s)) {
            this.s = s;
            this.b = true;
        }
    }
    
    public void setTitle(final Graphics graphics, final Component component, final Color color) {
        if (this.b) {
            this.H = 0;
            this.W = 0;
            this._iWidth = 0;
            this._iHeight = 0;
            if (this.s != null && !this.s.equals("")) {
                graphics.setFont(this.f);
                graphics.setColor(this.c);
                this.W = graphics.getFontMetrics().getHeight();
                this.H = graphics.getFontMetrics().stringWidth(this.s);
                this.D = graphics.getFontMetrics().getDescent();
                this._iWidth = this.H;
                this._iHeight = this.W;
                if (this._iOrientation == 1) {
                    this._iWidth = this.W;
                    this._iHeight = this.H;
                    this.i = Axis.getVerticalText(this.s, graphics, component, color);
                }
            }
            this.b = false;
        }
    }
}
