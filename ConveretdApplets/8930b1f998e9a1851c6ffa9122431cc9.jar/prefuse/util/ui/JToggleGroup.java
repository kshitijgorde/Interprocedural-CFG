// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.accessibility.Accessible;
import javax.swing.event.ListSelectionEvent;
import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.AbstractButton;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionListener;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import javax.swing.ListModel;
import javax.swing.JPanel;

public class JToggleGroup extends JPanel
{
    public static final int CHECKBOX = 0;
    public static final int RADIO = 1;
    protected final int m_type;
    protected int m_margin;
    protected int m_spacing;
    protected int m_axis;
    protected ListModel m_data;
    protected ListSelectionModel m_sel;
    protected String[] m_labels;
    protected ButtonGroup m_group;
    private Listener m_lstnr;
    
    public JToggleGroup(final int n, final Object[] array) {
        this(n, new DefaultListModel(), new DefaultListSelectionModel());
        final DefaultListModel defaultListModel = (DefaultListModel)this.m_data;
        for (int i = 0; i < array.length; ++i) {
            defaultListModel.addElement(array[i]);
        }
        this.initUI();
    }
    
    public JToggleGroup(final int n, final ListModel listModel) {
        this(n, listModel, new DefaultListSelectionModel());
    }
    
    public JToggleGroup(final int type, final ListModel data, final ListSelectionModel sel) {
        this.m_margin = 0;
        this.m_spacing = 0;
        this.m_axis = 0;
        this.setLayout(new BoxLayout(this, this.m_axis));
        this.m_type = type;
        this.m_data = data;
        this.m_sel = sel;
        if (this.m_type == 1) {
            this.m_group = new ButtonGroup();
        }
        this.m_lstnr = new Listener();
        this.m_sel.addListSelectionListener(this.m_lstnr);
        if (this.m_data.getSize() > 0) {
            this.initUI();
        }
        this.setFocusable(false);
    }
    
    protected void initUI() {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof JToggleButton) {
                final JToggleButton toggleButton = (JToggleButton)component;
                toggleButton.removeActionListener(this.m_lstnr);
                if (this.m_group != null) {
                    this.m_group.remove(toggleButton);
                }
            }
        }
        this.removeAll();
        UILib.addStrut(this, this.m_axis, this.m_margin);
        for (int j = 0; j < this.m_data.getSize(); ++j) {
            if (j > 0) {
                UILib.addStrut(this, this.m_axis, this.m_spacing);
            }
            final Object element = this.m_data.getElementAt(j);
            final String s = (this.m_labels == null) ? element.toString() : this.m_labels[j];
            Accessible accessible;
            if (this.m_type == 0) {
                accessible = new JCheckBox(s);
            }
            else {
                accessible = new JRadioButton(s);
                this.m_group.add((AbstractButton)accessible);
            }
            ((JComponent)accessible).putClientProperty("idx", new Integer(j));
            ((AbstractButton)accessible).addActionListener(this.m_lstnr);
            this.add((Component)accessible);
        }
        UILib.addStrut(this, this.m_axis, this.m_margin);
        this.m_lstnr.valueChanged(null);
    }
    
    public void setAxisType(final int axis) {
        this.setLayout(new BoxLayout(this, axis));
        this.m_axis = axis;
        this.initUI();
    }
    
    public int getAxisType() {
        return this.m_axis;
    }
    
    public void setMargin(final int margin) {
        if (margin < 0) {
            throw new IllegalArgumentException("Margin is less than zero.");
        }
        this.m_margin = margin;
        this.initUI();
    }
    
    public int getMargin() {
        return this.m_margin;
    }
    
    public void setSpacing(final int spacing) {
        if (spacing < 0) {
            throw new IllegalArgumentException("Spacing is less than zero.");
        }
        this.m_spacing = spacing;
        this.initUI();
    }
    
    public int getSpacing() {
        return this.m_spacing;
    }
    
    public void setModel(final ListModel data) {
        this.m_data = data;
        this.initUI();
    }
    
    public ListModel getModel() {
        return this.m_data;
    }
    
    public void setSelectionModel(final ListSelectionModel sel) {
        this.m_sel.removeListSelectionListener(this.m_lstnr);
        (this.m_sel = sel).addListSelectionListener(this.m_lstnr);
        this.m_lstnr.valueChanged(null);
    }
    
    public ListSelectionModel getSelectionModel() {
        return this.m_sel;
    }
    
    public void setLabels(final String[] labels) {
        if (labels.length < this.m_data.getSize()) {
            throw new IllegalArgumentException("Alias array is too short");
        }
        this.m_labels = labels;
        this.initUI();
    }
    
    public void setBackground(final Color background) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            this.getComponent(i).setBackground(background);
        }
    }
    
    public void setForeground(final Color foreground) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            this.getComponent(i).setForeground(foreground);
        }
    }
    
    public void setFont(final Font font) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            this.getComponent(i).setFont(font);
        }
    }
    
    public void setGroupFocusable(final boolean focusable) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof JToggleButton) {
                component.setFocusable(focusable);
            }
        }
    }
    
    private class Listener implements ListSelectionListener, ActionListener
    {
        private boolean m_ignore;
        
        private Listener() {
            this.m_ignore = false;
        }
        
        public void valueChanged(final ListSelectionEvent listSelectionEvent) {
            if (this.m_ignore) {
                return;
            }
            this.m_ignore = true;
            if (JToggleGroup.this.m_type == 1) {
                final int minSelectionIndex = JToggleGroup.this.m_sel.getMinSelectionIndex();
                final boolean selected = minSelectionIndex >= 0;
                AbstractButton abstractButton = null;
                int i = 0;
                int n = 0;
                while (i < JToggleGroup.this.getComponentCount()) {
                    final Component component = JToggleGroup.this.getComponent(i);
                    if (component instanceof JToggleButton) {
                        abstractButton = (JToggleButton)component;
                        if (!selected && abstractButton.isSelected()) {
                            break;
                        }
                        if (selected && minSelectionIndex == n) {
                            break;
                        }
                        ++n;
                    }
                    ++i;
                }
                abstractButton.setSelected(selected);
            }
            else {
                int j = 0;
                int n2 = 0;
                while (j < JToggleGroup.this.getComponentCount()) {
                    final Component component2 = JToggleGroup.this.getComponent(j);
                    if (component2 instanceof JCheckBox) {
                        ((JCheckBox)component2).setSelected(JToggleGroup.this.m_sel.isSelectedIndex(n2++));
                    }
                    ++j;
                }
            }
            this.m_ignore = false;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (this.m_ignore) {
                return;
            }
            this.m_ignore = true;
            final JToggleButton toggleButton = (JToggleButton)actionEvent.getSource();
            final boolean selected = toggleButton.isSelected();
            final int intValue = (int)toggleButton.getClientProperty("idx");
            if (JToggleGroup.this.m_type == 1) {
                JToggleGroup.this.m_sel.setSelectionInterval(intValue, intValue);
            }
            else if (selected) {
                JToggleGroup.this.m_sel.addSelectionInterval(intValue, intValue);
            }
            else {
                JToggleGroup.this.m_sel.removeSelectionInterval(intValue, intValue);
            }
            this.m_ignore = false;
        }
    }
}
