// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.awt.Component;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JTable;

public class MyTable extends JTable
{
    private int currow;
    private int curcol;
    private boolean dirty;
    
    public MyTable() {
        this.currow = -1;
        this.curcol = -1;
        this.dirty = false;
        this.setFocusCycleRoot(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                MyTable.this.my_keyTyped(e);
            }
        });
    }
    
    public boolean isDirty() {
        return this.dirty;
    }
    
    public void setDirty(final boolean b) {
        this.dirty = b;
    }
    
    @Override
    public void editingStopped(final ChangeEvent e) {
        this.currow = -1;
        this.curcol = -1;
        this.dirty = true;
        super.editingStopped(e);
    }
    
    public void my_keyTyped(final KeyEvent e) {
        final TableModel tm = this.getModel();
        final int row = this.getSelectedRow();
        final int col = this.getSelectedColumn();
        if (tm.isCellEditable(row, col)) {
            final int c = e.getKeyChar();
            if (c != 9 && c != 10 && (row != this.currow || col != this.curcol)) {
                this.currow = row;
                this.curcol = col;
                final Component comp = this.getEditorComponent();
                if (comp != null && comp instanceof JTextComponent) {
                    ((JTextComponent)comp).setText("");
                }
                else if (comp instanceof JComboBox) {
                    final JTextComponent tcomp = (JTextComponent)((JComboBox)comp).getEditor().getEditorComponent();
                    tcomp.setText(new Character((char)c).toString());
                    tcomp.requestFocus();
                }
            }
        }
    }
}
