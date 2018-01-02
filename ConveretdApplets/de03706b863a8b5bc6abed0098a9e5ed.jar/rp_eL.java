import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.Icon;
import java.awt.event.ComponentListener;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import net.eprevue.easyplan.selection.SelectionTreeView;
import javax.swing.ImageIcon;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_eL extends ComponentAdapter
{
    private /* synthetic */ rp_eG a;
    
    rp_eL(final rp_eG a) {
        this.a = a;
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        this.a.a(this.a.b());
        this.a.c.a(this.a.c.a().height);
    }
}
