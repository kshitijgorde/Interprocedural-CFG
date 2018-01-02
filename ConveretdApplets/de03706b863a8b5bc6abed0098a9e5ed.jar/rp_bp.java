import java.io.File;
import java.security.AccessControlException;
import net.eprevue.easyplanreader.EPReader;
import java.awt.event.AdjustmentEvent;
import java.util.List;
import java.awt.Window;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.AbstractButton;
import java.awt.event.ComponentListener;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.Frame;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_bp extends ComponentAdapter
{
    private /* synthetic */ rp_es a;
    
    rp_bp(final rp_es a) {
        this.a = a;
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
        if (this.a.a < 2) {
            this.a.a++;
            this.a.setSize(300, 200);
            this.a.pack();
        }
    }
}
