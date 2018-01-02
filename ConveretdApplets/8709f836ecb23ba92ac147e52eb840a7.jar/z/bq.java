// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.BoundedRangeModel;
import java.util.Iterator;
import javax.swing.SwingUtilities;
import java.util.Observable;
import java.net.URL;
import java.awt.event.ComponentListener;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.Component;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

final class bq implements MouseListener
{
    private /* synthetic */ aO a;
    
    bq(final aO a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.a.c.d();
    }
}
