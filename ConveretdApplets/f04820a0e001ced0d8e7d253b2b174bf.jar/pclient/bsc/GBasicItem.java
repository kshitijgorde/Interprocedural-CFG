// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public abstract class GBasicItem
{
    public int px;
    public int py;
    public int width;
    public int height;
    public Color pcolor;
    public Color curcolor;
    protected ActionCommand actionCom;
    
    public GBasicItem(final int width, final int height, final Color pcolor) {
        this.actionCom = null;
        this.width = width;
        this.height = height;
        this.pcolor = pcolor;
        this.curcolor = this.pcolor;
    }
    
    public GBasicItem(final Color pcolor) {
        this.actionCom = null;
        this.pcolor = pcolor;
        this.curcolor = this.pcolor;
    }
    
    public boolean isAction() {
        return this.actionCom != null;
    }
    
    public void executeAction() {
        if (this.actionCom == null) {
            return;
        }
        this.actionCom.performAction();
    }
    
    public ActionCommand getActionCommand() {
        return this.actionCom;
    }
    
    public void setActionCommand(final ActionCommand actionCom) {
        this.actionCom = actionCom;
    }
    
    public void removeActionCommand() {
        this.actionCom = null;
    }
    
    public void setSize(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public abstract void draw(final Graphics p0, final int p1, final int p2);
    
    public void moveTo(final int n, final int n2, final Component component, final Color color, final int n3, final int n4) {
        this.setPosition(n, n2);
    }
    
    public void setPosition(final int px, final int py) {
        this.px = px;
        this.py = py;
    }
    
    public Rectangle getBounds() {
        final Rectangle rectangle = new Rectangle();
        rectangle.x = this.px;
        rectangle.y = this.py;
        rectangle.width = this.width;
        rectangle.height = this.height;
        return rectangle;
    }
    
    public boolean inBoundary(final int n, final int n2) {
        return n >= this.px && n <= this.px + this.width && n2 >= this.py && n2 <= this.py + this.height;
    }
    
    protected int calculateLength(final Font font, final String s) {
        return Toolkit.getDefaultToolkit().getFontMetrics(font).stringWidth(s);
    }
    
    protected int getBaseline(final Font font) {
        return Toolkit.getDefaultToolkit().getFontMetrics(font).getAscent();
    }
    
    protected int calculateFontHeight(final Font font) {
        final int n = 0;
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        return n + fontMetrics.getAscent() + fontMetrics.getDescent();
    }
}
