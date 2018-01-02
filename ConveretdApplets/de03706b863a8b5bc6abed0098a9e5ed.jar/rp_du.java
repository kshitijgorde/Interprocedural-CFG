import java.awt.event.KeyListener;
import java.awt.IllegalComponentStateException;
import javax.swing.tree.TreePath;
import java.awt.Dimension;
import javax.swing.JTree;
import javax.swing.border.Border;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.JPopupMenu;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_du extends JPopupMenu implements ComboPopup
{
    protected rp_ef a;
    private JScrollPane a;
    private MouseMotionListener a;
    private MouseListener a;
    private MouseListener b;
    private JList a;
    
    public rp_du(final JComboBox comboBox) {
        this.b = new rp_fi(this);
        this.a = new JList();
        this.a = (rp_ef)comboBox;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BorderLayout());
        this.setLightWeightPopupEnabled(comboBox.isLightWeightPopupEnabled());
        final JTree a;
        if ((a = this.a.a) != null) {
            (this.a = new JScrollPane(a)).setBorder(null);
            this.add(this.a, "Center");
            a.addMouseListener(this.b);
        }
    }
    
    public final void show() {
        this.a.a();
        this.setPreferredSize(new Dimension(this.a.getSize().width, 120));
        final Object selectedItem;
        if ((selectedItem = this.a.getSelectedItem()) != null) {
            this.a.a.setSelectionPath((TreePath)selectedItem);
        }
        try {
            this.show(this.a, 0, this.a.getHeight());
        }
        catch (IllegalComponentStateException ex) {}
        this.a.a.requestFocus();
    }
    
    public final void hide() {
        this.setVisible(false);
        this.a.firePropertyChange("popupVisible", true, false);
    }
    
    public final JList getList() {
        return this.a;
    }
    
    public final MouseMotionListener getMouseMotionListener() {
        if (this.a == null) {
            this.a = new rp_fk(this);
        }
        return this.a;
    }
    
    public final KeyListener getKeyListener() {
        return null;
    }
    
    public final void uninstallingUI() {
    }
    
    public final MouseListener getMouseListener() {
        if (this.a == null) {
            this.a = new rp_M(this);
        }
        return this.a;
    }
    
    protected final void a() {
        if (this.isVisible()) {
            this.hide();
            return;
        }
        this.show();
    }
}
