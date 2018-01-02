// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.FocusListener;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Panel;

public final class V extends Panel
{
    private final Image a;
    
    public V(final int n, final int n2, final int n3, final Image a) {
        super(null);
        this.setBackground(new Color(0));
        this.setBounds(n, n2, n3, 16);
        this.a = a;
        this.addFocusListener(new U(this));
        this.paint(this.getGraphics());
    }
    
    public final void paint(final Graphics graphics) {
        try {
            while (!graphics.drawImage(this.a, 0, 0, null)) {
                Thread.sleep(5L);
            }
        }
        catch (Exception ex) {}
    }
}
