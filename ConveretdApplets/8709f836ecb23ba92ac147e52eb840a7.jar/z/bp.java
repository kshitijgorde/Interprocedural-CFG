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
import javax.swing.JPanel;
import java.awt.event.ComponentEvent;
import javax.swing.JScrollPane;
import java.awt.event.ComponentListener;

final class bp implements ComponentListener
{
    private /* synthetic */ JScrollPane a;
    private /* synthetic */ as b;
    private /* synthetic */ aO c;
    
    bp(final aO c, final JScrollPane a, final as b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        this.b.a(this.a.getViewport().getExtentSize().width);
        this.b.b(this.a.getViewport().getExtentSize().height - 1);
        this.c.f.invalidate();
    }
}