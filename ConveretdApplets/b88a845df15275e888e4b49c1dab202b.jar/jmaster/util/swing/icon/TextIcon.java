// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.icon;

import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import jmaster.util.log.B;
import java.awt.Color;
import java.awt.Font;
import jmaster.util.log.A;
import javax.swing.Icon;

public class TextIcon extends ResizingIcon implements Icon
{
    public static final int HALIGN_LEFT = 0;
    public static final int HALIGN_CENTER = 1;
    public static final int HALIGN_RIGHT = 2;
    public static final int VALIGN_TOP = 0;
    public static final int VALIGN_MIDDLE = 1;
    public static final int VALIGN_BOTTOM = 2;
    protected A a;
    private boolean X;
    private Font Y;
    private Color _;
    private String f;
    private int c;
    private int d;
    private boolean W;
    private String Z;
    private int b;
    private int e;
    
    public TextIcon() {
        this.a = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.X = true;
        this.c = 0;
        this.d = 2;
        this.W = false;
    }
    
    public Color getColor() {
        return this._;
    }
    
    public void setColor(final Color _) {
        this._ = _;
    }
    
    public Font getFont() {
        return this.Y;
    }
    
    public void setFont(final Font y) {
        this.Y = y;
    }
    
    public String getText() {
        return this.f;
    }
    
    public void setText(final String f) {
        this.f = f;
    }
    
    public boolean isAntialiasing() {
        return this.X;
    }
    
    public void setAntialiasing(final boolean x) {
        this.X = x;
    }
    
    public boolean isRenderFrame() {
        return this.W;
    }
    
    public void setRenderFrame(final boolean w) {
        this.W = w;
    }
    
    public int getHalign() {
        return this.c;
    }
    
    public void setHalign(final int c) {
        this.c = c;
    }
    
    public int getValign() {
        return this.d;
    }
    
    public void setValign(final int d) {
        this.d = d;
    }
    
    public void prepare(final Graphics2D graphics2D) {
        if (this.f == null) {
            this.f = "";
        }
        final FontMetrics fontMetrics = graphics2D.getFontMetrics((this.Y == null) ? graphics2D.getFont() : this.Y);
        String s = this.f;
        int b = fontMetrics.stringWidth(s);
        boolean b2 = false;
        while (b > this.getWidth() && s.length() > 1) {
            s = s.substring(0, s.length() - 1);
            b = fontMetrics.stringWidth(s + "...");
            b2 = true;
        }
        if (b2) {
            this.Z = s + "...";
        }
        else {
            this.Z = this.f;
        }
        this.b = b;
        this.e = fontMetrics.getHeight();
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        this.prepare(graphics2D);
        Object renderingHint = null;
        if (this.X) {
            renderingHint = graphics2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        final Font font = graphics.getFont();
        graphics.setFont(this.Y);
        final Color color = graphics.getColor();
        graphics.setColor(this._);
        final int width = this.getWidth();
        final int height = this.getHeight();
        int n3 = 0;
        int n4 = 0;
        switch (this.getHalign()) {
            case 0: {
                n3 = 0;
                break;
            }
            case 1: {
                n3 = (width - this.b) / 2;
                break;
            }
            case 2: {
                n3 = width - this.b;
                break;
            }
        }
        switch (this.getValign()) {
            case 0: {
                n4 = 0;
                break;
            }
            case 1: {
                n4 = (height - this.e) / 2;
                break;
            }
            case 2: {
                n4 = height - this.e;
                break;
            }
        }
        graphics.drawString(this.Z, n + n3, n2 + n4 + this.e);
        if (this.W) {
            graphics.drawRect(n, n2, this.getWidth() - 1, this.getHeight() - 1);
            int n5 = 0;
            int n6 = 0;
            switch (this.getHalign()) {
                case 0: {
                    n5 = 0;
                    break;
                }
                case 1: {
                    n5 = width / 2;
                }
            }
            switch (this.getValign()) {
                case 0: {
                    n6 = 0;
                    break;
                }
                case 1: {
                    n6 = height / 2;
                    break;
                }
                case 2: {
                    n6 = height - 1;
                    break;
                }
            }
            graphics.setColor(Color.RED);
            graphics.drawLine(n + n5, n2 + n6, n + n5, n2 + n6);
        }
        graphics.setFont(font);
        graphics.setColor(color);
        if (this.X) {
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, renderingHint);
        }
    }
}
