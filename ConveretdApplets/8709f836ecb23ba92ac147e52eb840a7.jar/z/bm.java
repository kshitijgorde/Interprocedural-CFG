// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.BoundedRangeModel;
import java.util.Iterator;
import javax.swing.SwingUtilities;
import java.util.Observable;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.Hashtable;
import com.photochannel.easyUploader.b;
import java.util.Observer;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import java.awt.event.ComponentListener;

final class bm implements ComponentListener
{
    private /* synthetic */ JPanel a;
    private /* synthetic */ aO b;
    
    bm(final aO b, final JPanel a) {
        this.b = b;
        this.a = a;
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
        this.b.g.setBounds(this.a.getWidth() / 2 - this.b.g.getWidth() / 2, this.a.getHeight() / 2 - this.b.g.getHeight() / 2, this.b.g.getWidth(), this.b.g.getHeight());
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
}
