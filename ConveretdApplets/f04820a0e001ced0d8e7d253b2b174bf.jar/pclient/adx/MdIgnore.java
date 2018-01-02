// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JComponent;
import java.awt.Container;
import javax.swing.Icon;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.Frame;
import javax.swing.JDialog;
import java.beans.PropertyChangeListener;

public class MdIgnore implements PropertyChangeListener
{
    private JDialog dialogWin;
    private Frame parentFrame;
    private DefaultListModel modListModel;
    private JList modList;
    private JOptionPane optionPane;
    private String deleteString;
    private String clearString;
    
    public MdIgnore(final Frame parentFrame) {
        this.dialogWin = null;
        this.deleteString = "Delete";
        this.clearString = "Clear All";
        this.parentFrame = parentFrame;
        this.buildUI();
    }
    
    public void add(final String s) {
        if (s != null) {
            if (this.isIgnored(s)) {
                return;
            }
            this.modListModel.addElement(s);
        }
    }
    
    public boolean isIgnored(final String s) {
        if (s == null) {
            return false;
        }
        for (int size = this.modListModel.size(), i = 0; i < size; ++i) {
            if (s.equals(this.modListModel.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    public void showWin() {
        if (this.dialogWin == null) {
            this.buildUI();
        }
        this.dialogWin.setVisible(true);
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        propertyChangeEvent.getPropertyName();
        if (!this.dialogWin.isVisible() || propertyChangeEvent.getSource() != this.optionPane) {
            return;
        }
        final Object value = this.optionPane.getValue();
        if (value == JOptionPane.UNINITIALIZED_VALUE) {
            return;
        }
        this.optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
        if (this.deleteString.equals(value)) {
            final int selectedIndex = this.modList.getSelectedIndex();
            if (selectedIndex < 0) {
                return;
            }
            this.modListModel.remove(selectedIndex);
        }
        else if (this.clearString.equals(value)) {
            if (this.modListModel.size() == 0) {
                return;
            }
            final int showConfirmDialog = JOptionPane.showConfirmDialog(this.dialogWin, "Are you sure you want to clear all ignored user names?", "Clear Ignored", 0);
            if (showConfirmDialog == 0) {
                this.modListModel.clear();
            }
            else if (showConfirmDialog == 1) {}
        }
    }
    
    private void buildUI() {
        final Object[] array = { "Igored Users", this.getCenter() };
        final Object[] array2 = { this.deleteString, this.clearString };
        (this.optionPane = new JOptionPane(array, -1, 0, null, array2, array2[0])).addPropertyChangeListener(this);
        (this.dialogWin = new JDialog(this.parentFrame, false)).setLocationRelativeTo(this.parentFrame);
        this.dialogWin.setResizable(true);
        this.dialogWin.setContentPane(this.optionPane);
        this.dialogWin.pack();
    }
    
    private JComponent getCenter() {
        final DefaultListModel<Object> modListModel = new DefaultListModel<Object>();
        final JList modList = new JList(modListModel);
        modList.setVisibleRowCount(16);
        modList.setSelectionMode(0);
        final JScrollPane scrollPane = new JScrollPane(modList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(100, 120));
        scrollPane.setPreferredSize(new Dimension(120, 160));
        this.modListModel = modListModel;
        this.modList = modList;
        return scrollPane;
    }
}
