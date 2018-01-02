// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.awt.event.ItemListener;
import javax.swing.JTable;
import java.util.EventObject;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

public class NonNegativeIntegerCellEditor extends AbstractCellEditor implements TableCellEditor
{
    protected JComponent editorComponent;
    protected EditorDelegate delegate;
    protected int clickCountToStart;
    private NonNegativeIntegerField integerfield;
    
    public NonNegativeIntegerCellEditor(final NonNegativeIntegerField integerField) {
        this.clickCountToStart = 1;
        this.integerfield = new NonNegativeIntegerField(10);
        this.editorComponent = integerField;
        this.clickCountToStart = 2;
        integerField.addActionListener(this.delegate = new EditorDelegate() {
            @Override
            public void setValue(final Object value) {
                integerField.setValue(value);
            }
            
            @Override
            public Object getCellEditorValue() {
                Integer f;
                try {
                    f = integerField.getValue();
                }
                catch (Exception ex) {
                    f = new Integer(0);
                }
                return f;
            }
        });
    }
    
    public NonNegativeIntegerCellEditor() {
        this.clickCountToStart = 1;
        this.integerfield = new NonNegativeIntegerField(10);
        this.editorComponent = this.integerfield;
        this.clickCountToStart = 2;
        this.delegate = new EditorDelegate() {
            @Override
            public void setValue(final Object value) {
                NonNegativeIntegerCellEditor.this.integerfield.setValue(value);
            }
            
            @Override
            public Object getCellEditorValue() {
                Integer f;
                try {
                    f = NonNegativeIntegerCellEditor.this.integerfield.getValue();
                }
                catch (Exception ex) {
                    f = new Integer(0);
                }
                return f;
            }
        };
        this.integerfield.addActionListener(this.delegate);
    }
    
    public Component getComponent() {
        return this.editorComponent;
    }
    
    public void setClickCountToStart(final int count) {
        this.clickCountToStart = count;
    }
    
    public int getClickCountToStart() {
        return this.clickCountToStart;
    }
    
    @Override
    public Object getCellEditorValue() {
        return this.delegate.getCellEditorValue();
    }
    
    @Override
    public boolean isCellEditable(final EventObject anEvent) {
        return this.delegate.isCellEditable(anEvent);
    }
    
    @Override
    public boolean shouldSelectCell(final EventObject anEvent) {
        return this.delegate.shouldSelectCell(anEvent);
    }
    
    @Override
    public boolean stopCellEditing() {
        return this.delegate.stopCellEditing();
    }
    
    @Override
    public void cancelCellEditing() {
        this.delegate.cancelCellEditing();
    }
    
    @Override
    public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected, final int row, final int column) {
        this.delegate.setValue(value);
        return this.editorComponent;
    }
    
    protected class EditorDelegate implements ActionListener, ItemListener, Serializable
    {
        protected Object value;
        
        public Object getCellEditorValue() {
            return this.value;
        }
        
        public void setValue(final Object value) {
            this.value = value;
        }
        
        public boolean isCellEditable(final EventObject anEvent) {
            return !(anEvent instanceof MouseEvent) || ((MouseEvent)anEvent).getClickCount() >= NonNegativeIntegerCellEditor.this.clickCountToStart;
        }
        
        public boolean shouldSelectCell(final EventObject anEvent) {
            return true;
        }
        
        public boolean startCellEditing(final EventObject anEvent) {
            return true;
        }
        
        public boolean stopCellEditing() {
            NonNegativeIntegerCellEditor.this.fireEditingStopped();
            return true;
        }
        
        public void cancelCellEditing() {
            NonNegativeIntegerCellEditor.this.fireEditingCanceled();
        }
        
        @Override
        public void actionPerformed(final ActionEvent e) {
            NonNegativeIntegerCellEditor.this.stopCellEditing();
        }
        
        @Override
        public void itemStateChanged(final ItemEvent e) {
            NonNegativeIntegerCellEditor.this.stopCellEditing();
        }
    }
}
