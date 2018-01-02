// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.util.List;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

final class t implements ComponentListener
{
    private /* synthetic */ bj a;
    private /* synthetic */ bj b;
    
    t(final bj b, final bj a) {
        this.b = b;
        this.a = a;
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        this.a.removeAll();
        try {
            this.b.a(this.b.d);
        }
        catch (ah ah) {
            ah.printStackTrace();
        }
    }
}
