// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.Icon;
import java.awt.event.KeyListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

final class S implements ComponentListener
{
    private /* synthetic */ af a;
    private /* synthetic */ af b;
    
    S(final af b, final af a) {
        this.b = b;
        this.a = a;
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
        ((ImageIcon)this.b.g.getIcon()).getImage().flush();
        this.a.remove(this.b.g);
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
        this.a.getContentPane().add(this.b.g, "Center");
        this.a.getContentPane().validate();
    }
}
