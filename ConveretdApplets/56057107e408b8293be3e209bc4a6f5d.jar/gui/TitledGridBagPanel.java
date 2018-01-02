// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;

public final class TitledGridBagPanel extends JPanel
{
    private GridBagConstraints m_constraints;
    private Vector m_rows;
    
    public TitledGridBagPanel() {
        this((String)null);
    }
    
    public TitledGridBagPanel(final String s) {
        this(s, null);
    }
    
    public TitledGridBagPanel(final String s, final Insets insets) {
        super(new GridBagLayout());
        if (s != null) {
            this.setBorder(new TitledBorder(s));
        }
        this.m_constraints = new GridBagConstraints();
        this.m_constraints.anchor = 17;
        this.setInsets(insets);
        this.m_rows = new Vector();
    }
    
    public void setInsets(Insets defaultInsets) {
        if (defaultInsets == null) {
            defaultInsets = this.getDefaultInsets();
        }
        this.m_constraints.insets = defaultInsets;
    }
    
    public Insets getDefaultInsets() {
        return new Insets(5, 5, 5, 5);
    }
    
    public void removeInsets() {
        this.m_constraints.insets = new Insets(0, 0, 0, 0);
    }
    
    public void setEnabled(final boolean b) {
        if (this.getBorder() instanceof TitledBorder) {
            final TitledBorder border = new TitledBorder(((TitledBorder)this.getBorder()).getTitle());
            if (!b) {
                border.setTitleColor(Color.gray);
            }
            this.setBorder(border);
        }
        super.setEnabled(b);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            components[i].setEnabled(b);
        }
    }
    
    public void setLayout(final LayoutManager layout) {
        if (!(layout instanceof GridBagLayout)) {
            throw new IllegalStateException("Layout is fixed to GridBagLayout!");
        }
        super.setLayout(layout);
    }
    
    public int addRow(final Component component) {
        return this.addRow(component, null);
    }
    
    public int addRow(final Component component, final Component component2) {
        return this.addRow(component, component2, 2);
    }
    
    public int addRow(final Component component, final Component component2, final int n) {
        return this.replaceRow(component, component2, this.getNextRow(), n);
    }
    
    public int addRow(final Component component, final Component component2, final Component component3, final Component component4) {
        return this.replaceRow(component, component2, component3, component4, this.getNextRow());
    }
    
    public int addRow(final Component component, final Component component2, final Component component3, final Component component4, final int n) {
        return this.replaceRow(component, component2, component3, component4, this.getNextRow(), n);
    }
    
    public int addRow(final Component component, final Component component2, final Component component3) {
        return this.replaceRow(component, component2, component3, this.getNextRow());
    }
    
    public int addDummyRow() {
        this.m_rows.addElement(new JLabel());
        return this.m_rows.size() - 1;
    }
    
    public void addDummyRows(int i) {
        while (i > 0) {
            this.m_rows.addElement(new JLabel());
            --i;
        }
    }
    
    public int addRow(final Component[] array, final int[] array2) {
        return this.replaceRow(array, array2, this.getNextRow());
    }
    
    public int replaceRow(final Component component, final Component component2, final int n) {
        return this.replaceRow(component, component2, n, 2);
    }
    
    public int replaceRow(final Component component, final Component component2, final int n, final int n2) {
        return this.replaceRow(new Component[] { component, component2 }, null, n, n2);
    }
    
    public int replaceRow(final Component component, final Component component2, final Component component3, final int n) {
        return this.replaceRow(new Component[] { component, component2, component3 }, null, n);
    }
    
    public int replaceRow(final Component component, final Component component2, final Component component3, final Component component4, final int n, final int n2) {
        return this.replaceRow(new Component[] { component, component2, component3, component4 }, null, n, n2);
    }
    
    public int replaceRow(final Component component, final Component component2, final Component component3, final Component component4, final int n) {
        return this.replaceRow(component, component2, component3, component4, n, 2);
    }
    
    public int getNextRow() {
        return this.m_rows.size();
    }
    
    public int replaceRow(final Component[] array, final int[] array2, final int n) {
        return this.replaceRow(array, array2, n, 2);
    }
    
    public int replaceRow(final Component[] array, final int[] array2, final int gridy, final int fill) {
        if (array != null && array.length > 0) {
            final Vector<Component> vector = new Vector<Component>();
            for (int i = 0; i < array.length; ++i) {
                vector.addElement(array[i]);
            }
            while (this.m_rows.size() < gridy - 1) {
                this.m_rows.addElement(new Vector<Vector>());
            }
            if (this.m_rows.size() > gridy) {
                final Vector<Component> vector2 = this.m_rows.elementAt(gridy);
                for (int j = 0; j < vector2.size(); ++j) {
                    this.remove(vector2.elementAt(j));
                }
                this.m_rows.removeElementAt(gridy);
            }
            this.m_rows.insertElementAt(vector, gridy);
            int[] array3;
            if (array2 != null) {
                array3 = array2;
            }
            else {
                array3 = new int[array.length];
                for (int k = 0; k < array3.length; ++k) {
                    array3[k] = 1;
                    int[] array4;
                    int n2;
                    for (int n = k; n + 1 < array.length && array[n + 1] == null; ++n, array4 = array3, n2 = k, ++array4[n2]) {}
                }
            }
            for (int l = 0; l < array.length; ++l) {
                if (array[l] != null) {
                    this.m_constraints.gridx = l;
                    this.m_constraints.gridy = gridy;
                    this.m_constraints.weightx = 1.0;
                    this.m_constraints.gridwidth = array3[l];
                    if (l == array.length - 1) {
                        this.m_constraints.weighty = 10.0;
                    }
                    else {
                        this.m_constraints.weighty = 0.0;
                    }
                    this.m_constraints.fill = fill;
                    this.add(array[l], this.m_constraints);
                }
            }
        }
        return gridy;
    }
}
