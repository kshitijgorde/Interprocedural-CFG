import javax.swing.JComponent;
import javax.accessibility.Accessible;
import java.awt.Font;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.event.ItemListener;
import java.awt.Component;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

// 
// Decompiled by Procyon v0.5.30
// 

public class WLightList extends WList
{
    private JComboBox combo;
    private JScrollPane scroll;
    private JList list;
    private int size;
    
    public WLightList(final int n, final int size) {
        super(n);
        if (n == WList.LIST) {
            this.size = size;
            (this.list = new JList()).addListSelectionListener(new WLightList$WLightListListener(this, this));
            this.add(this.scroll = new JScrollPane(this.list));
        }
        else {
            this.add(this.combo = new JComboBox());
            this.combo.addItemListener(this);
            super.I = this.combo;
        }
    }
    
    public final void add(final Vector listData) {
        this.list.setListData(listData);
    }
    
    public final void add(final String s) {
        if (super.C == WList.CHOICE) {
            this.combo.addItem(s);
        }
        else {
            super.add(s);
        }
    }
    
    public final int getSelectedIndex() {
        if (super.C == WList.LIST) {
            return this.list.getSelectedIndex();
        }
        return this.combo.getSelectedIndex();
    }
    
    public final String getSelectedItem() {
        if (super.C == WList.LIST) {
            return this.list.getSelectedValue();
        }
        return (String)this.combo.getSelectedItem();
    }
    
    public final void setSelectedItem(final String selectedItem, final boolean b) {
        if (super.C == WList.LIST) {
            super.setSelectedItem(selectedItem, b);
        }
        else {
            if (super.Z != null && !this.combo.getSelectedItem().equals(selectedItem)) {
                super.B = b;
            }
            this.combo.setSelectedItem(selectedItem);
        }
    }
    
    public final void setSelectedIndex(final int n, final boolean b) {
        if (super.C == WList.LIST) {
            super.B = b;
            this.list.setSelectedIndex(n);
        }
        else {
            if (super.Z != null && this.combo.getSelectedIndex() != n) {
                super.B = b;
            }
            this.combo.setSelectedIndex(n);
        }
    }
    
    public final Object[] getSelectedObjects() {
        if (super.C == WList.LIST) {
            return this.list.getSelectedValues();
        }
        return super.getSelectedObjects();
    }
    
    public final Dimension getPreferredSize() {
        if (super.C == WList.LIST) {
            return new Dimension(400, 20 * this.size);
        }
        return super.getPreferredSize();
    }
    
    public final void removeAllItems() {
        if (super.C == WList.LIST) {
            this.list.setListData(new Vector());
        }
        else {
            this.combo.removeAllItems();
        }
    }
    
    public final Component getComponent() {
        if (super.C == WList.LIST) {
            return this.list;
        }
        return this.combo;
    }
    
    public final void setFont(final Font font) {
        if (!this.getFont().equals(font)) {
            super.setFont(font);
            Accessible accessible;
            if (super.C == WList.LIST) {
                accessible = this.list;
            }
            else {
                accessible = this.combo;
            }
            ((JComponent)accessible).updateUI();
        }
    }
}
