// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JLabel;

public class l extends JLabel
{
    protected boolean a;
    protected boolean b;
    protected String c;
    protected String d;
    protected Color e;
    protected Color f;
    protected Vector g;
    
    public l() {
        this("");
    }
    
    public l(final String text) {
        this.a = false;
        this.b = false;
        this.e = Color.blue;
        this.f = Color.blue;
        this.addMouseListener(new b(this));
        this.setText(text);
        this.setForeground(this.f);
    }
    
    public void setText(final String c) {
        this.c = c;
        if (c == null || c.length() == 0) {
            super.setText("");
        }
        else if (this.a || this.b) {
            super.setText("<html><u>" + c + "</u>");
        }
        else {
            super.setText("<html>" + c);
        }
    }
    
    public void a(final Color color) {
        this.e = color;
        if (this.a) {
            this.setForeground(color);
        }
    }
    
    public void b(final Color color) {
        this.f = color;
        if (!this.a) {
            this.setForeground(color);
        }
    }
    
    protected void paintComponent(final Graphics graphics) {
        b.a.d.b.a(graphics, true);
        super.paintComponent(graphics);
    }
    
    public void a(final ActionListener actionListener) {
        if (this.g == null) {
            this.g = new Vector();
        }
        this.g.addElement(actionListener);
    }
    
    public void removeNotify() {
        super.removeNotify();
        this.g = null;
    }
    
    protected void a(final int n) {
        final ActionEvent actionEvent = new ActionEvent(this, 0, this.d, n);
        if (this.g != null) {
            for (int i = 0; i < this.g.size(); ++i) {
                ((ActionListener)this.g.elementAt(i)).actionPerformed(actionEvent);
            }
        }
    }
}
