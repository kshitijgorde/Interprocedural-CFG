// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Frame;
import java.applet.Applet;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Component;
import java.awt.Canvas;

public class bc extends Canvas
{
    protected String new;
    protected Component if;
    private Container a;
    private LayoutManager for;
    private boolean int;
    private final int do = 30;
    private final int try = 10;
    
    public bc(final String new1, final Component if1) {
        this.new = "";
        this.new = new1;
        (this.if = if1).addMouseListener(new a());
        this.setBackground(new Color(255, 255, 220));
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        graphics.drawString(this.new, 3, this.getSize().height - 3);
    }
    
    private void if() {
        this.a.setLayout(null);
        final FontMetrics fontMetrics = this.getFontMetrics(this.if.getFont());
        this.setSize(fontMetrics.stringWidth(this.new) + 10, fontMetrics.getHeight());
        this.setLocation(this.if.getLocationOnScreen().x - this.a.getLocationOnScreen().x, this.if.getLocationOnScreen().y - this.a.getLocationOnScreen().y + 30);
        if (this.a.getSize().width < this.getLocation().x + this.getSize().width) {
            this.setLocation(this.a.getSize().width - this.getSize().width, this.getLocation().y);
        }
        this.a.add(this, 0);
        this.a.validate();
        this.repaint();
        this.int = true;
    }
    
    private void a() {
        if (this.int) {
            this.a.remove(0);
            this.a.setLayout(this.for);
            this.a.validate();
        }
        this.int = false;
    }
    
    private void do() {
        Container a;
        for (a = this.if.getParent(); !(a instanceof Applet) && !(a instanceof Frame); a = a.getParent()) {}
        this.a = a;
        this.for = this.a.getLayout();
    }
    
    class a extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            bc.this.do();
            bc.this.if();
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            bc.this.a();
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            bc.this.a();
        }
    }
}
