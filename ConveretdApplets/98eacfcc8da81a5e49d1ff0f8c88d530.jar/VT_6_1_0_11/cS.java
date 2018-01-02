// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import com.hw.client.util.c;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class cS extends JPanel
{
    private ImageIcon a;
    
    public cS(final String s) {
        this(c.a(s));
    }
    
    public cS(final String s, Component component) {
        this(c.a(s));
        component = component;
        this.add(component, "Center");
    }
    
    private cS(final ImageIcon a) {
        this.a = a;
        if (a.getIconHeight() > a.getIconWidth()) {
            this.setMaximumSize(new Dimension(1000, a.getIconHeight()));
        }
        else {
            this.setMaximumSize(new Dimension(a.getIconWidth(), 1000));
        }
        this.setLayout(new BorderLayout());
    }
    
    public Dimension getPreferredSize() {
        if (this.a.getIconHeight() > this.a.getIconWidth()) {
            return new Dimension(super.getPreferredSize().width, this.a.getIconHeight());
        }
        return new Dimension(this.a.getIconWidth(), super.getPreferredSize().height);
    }
    
    public Dimension getMinimumSize() {
        if (this.a.getIconHeight() > this.a.getIconWidth()) {
            return new Dimension(super.getMinimumSize().width, this.a.getIconHeight());
        }
        return new Dimension(this.a.getIconWidth(), super.getMinimumSize().height);
    }
    
    public void paintComponent(final Graphics graphics) {
        graphics.drawImage(this.a.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    public final ImageIcon c() {
        return this.a;
    }
}
