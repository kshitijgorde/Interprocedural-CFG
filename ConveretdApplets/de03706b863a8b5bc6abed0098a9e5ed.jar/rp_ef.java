import javax.swing.plaf.basic.BasicComboBoxUI;
import com.sun.java.swing.plaf.motif.MotifComboBoxUI;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.ListCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.JTree;
import javax.swing.JComboBox;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_ef extends JComboBox
{
    public JTree a;
    public boolean b;
    
    public rp_ef() {
        this(new JTree());
    }
    
    private rp_ef(final JTree tree) {
        this(tree, null);
    }
    
    private rp_ef(final JTree tree, final TreeCellRenderer treeCellRenderer) {
        this(tree, null, false);
    }
    
    public rp_ef(final JTree tree, final TreeCellRenderer cellRenderer, final boolean b) {
        this.b = false;
        this.a(tree);
        tree.setCellRenderer(cellRenderer);
        if (!b) {
            tree.expandPath(new TreePath(tree.getModel().getRoot()));
            tree.setRootVisible(false);
        }
    }
    
    public void a(final JTree a) {
        this.a = a;
        if (a != null) {
            this.setSelectedItem(a.getSelectionPath());
            this.setRenderer(new rp_cL(this));
        }
        this.updateUI();
    }
    
    public final void b() {
        for (int i = 0; i < this.a.getRowCount(); ++i) {
            this.a.expandRow(i);
        }
    }
    
    public void setSelectedItem(final Object selectedItem) {
        this.a.setSelectionPath((TreePath)selectedItem);
        this.getModel().setSelectedItem(selectedItem);
    }
    
    public void a() {
    }
    
    public void updateUI() {
        final ComboBoxUI comboBoxUI;
        BasicComboBoxUI ui;
        if ((comboBoxUI = (ComboBoxUI)UIManager.getUI(this)) instanceof MetalComboBoxUI) {
            ui = new rp_ff(this);
        }
        else if (comboBoxUI instanceof MotifComboBoxUI) {
            ui = new rp_aA(this);
        }
        else {
            ui = new rp_K(this);
        }
        this.setUI(ui);
    }
}
