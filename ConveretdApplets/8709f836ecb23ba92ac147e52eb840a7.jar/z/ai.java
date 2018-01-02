// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import java.util.Observable;
import java.util.Iterator;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Observer;
import java.awt.event.MouseListener;

final class ai implements Runnable
{
    private /* synthetic */ Object a;
    private /* synthetic */ aS b;
    
    ai(final aS b, final Object a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        final bl bl;
        if ((bl = (bl)this.a).a) {
            aS.a(this.b, bl.b);
            return;
        }
        aS.a(this.b, bl.b, bl.c, bl.d, bl.e, bl.f);
    }
}
