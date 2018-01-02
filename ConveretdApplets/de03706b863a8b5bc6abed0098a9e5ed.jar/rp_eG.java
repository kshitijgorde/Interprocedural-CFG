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

// 
// Decompiled by Procyon v0.5.30
// 

class rp_eG extends rp_bA
{
    private static ImageIcon a;
    private rp_dl b;
    private SelectionTreeView b;
    private rp_r a;
    private rp_dl c;
    private SelectionTreeView c;
    private rp_r b;
    private JToggleButton a;
    private int b;
    
    public rp_eG(final rp_aJ rp_aJ) {
        super(rp_aJ);
        this.b = null;
        this.b = null;
        this.a = null;
        this.c = null;
        this.c = null;
        this.b = null;
        this.b = 104;
    }
    
    final rp_dl b() {
        if (this.b == 104) {
            return this.c;
        }
        return this.b;
    }
    
    final SelectionTreeView a() {
        if (this.b == 104) {
            return this.c;
        }
        return this.b;
    }
    
    final void a() {
        new rp_eI(this).c();
    }
    
    public final void a(final int b) {
        if (this.b != b) {
            this.b = b;
            this.removeAll();
            final JPanel panel;
            (panel = new JPanel(new BorderLayout())).add(this.a, "West");
            panel.add((this.b == 104) ? this.b : this.a, "Center");
            this.add(panel, "North");
            this.add(this.b(), "Center");
            this.validate();
            this.repaint();
        }
    }
    
    static {
        final ClassLoader classLoader = rp_eG.class.getClassLoader();
        try {
            rp_eG.a = new ImageIcon(classLoader.getResource("res/bt_type.png"));
        }
        catch (Exception ex) {
            System.out.println("EXC:" + ex);
        }
    }
}
