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
import java.awt.Component;

final class br implements Runnable
{
    private /* synthetic */ Object a;
    private /* synthetic */ aO b;
    
    br(final aO b, final Object a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        if (this.a instanceof aN) {
            final aN an = (aN)this.a;
            aO.a(this.b, an.a, an.b);
            return;
        }
        if (this.a instanceof an) {
            final String a = ((an)this.a).a;
            final bj bj;
            (bj = this.b.d.get(a)).removeAll();
            this.b.f.remove(bj);
            this.b.f.validate();
            this.b.d.remove(a);
            if (this.b.d.size() == 0) {
                aO.e(this.b);
            }
            return;
        }
        if (this.a instanceof ba) {
            final ba ba = (ba)this.a;
            ((bj)this.b.d.get(ba.a)).a(ba.b);
            return;
        }
        if (this.a instanceof bs) {
            final bs bs = (bs)this.a;
            ((bj)this.b.d.get(bs.a)).a(bs.b);
            return;
        }
        if (this.a instanceof p) {
            this.b.d.get(((p)this.a).a).a();
        }
    }
}
