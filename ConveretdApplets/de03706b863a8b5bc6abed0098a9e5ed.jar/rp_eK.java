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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_eK implements ActionListener
{
    private /* synthetic */ rp_eG a;
    
    rp_eK(final rp_eG a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.a((this.a.b == 104) ? 103 : 104);
    }
}
