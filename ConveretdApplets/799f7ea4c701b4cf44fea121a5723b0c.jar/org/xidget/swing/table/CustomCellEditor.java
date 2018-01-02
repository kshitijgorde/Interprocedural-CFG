// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.table;

import java.util.Iterator;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;
import org.xidget.tree.Cell;
import java.util.List;
import javax.swing.JSlider;
import javax.swing.text.JTextComponent;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.JComponent;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xmodel.xpath.expression.IContext;
import org.xidget.ifeature.IBindFeature;
import org.xidget.tree.Row;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.IModelObject;
import org.xidget.IXidget;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

public class CustomCellEditor extends AbstractCellEditor implements TableCellEditor
{
    private CellEditorListener listener;
    private IXidget editor;
    private IModelObject editorSource;
    private StatefulContext editorContext;
    
    public CustomCellEditor() {
        this.addCellEditorListener(this.listener = new CellEditorListener() {
            @Override
            public void editingCanceled(final ChangeEvent changeEvent) {
                CustomCellEditor.this.cleanupEditor();
            }
            
            @Override
            public void editingStopped(final ChangeEvent changeEvent) {
                CustomCellEditor.this.editorSource.setValue(CustomCellEditor.this.editorContext.getObject().getValue());
                CustomCellEditor.this.cleanupEditor();
            }
        });
    }
    
    @Override
    public Component getTableCellEditorComponent(final JTable table, final Object o, final boolean b, final int n, final int n2) {
        final List<Row> rows = ((CustomTableModel)table.getModel()).getRows();
        if (rows.size() <= n) {
            return null;
        }
        final Row row = rows.get(n);
        final Cell cell = row.getCell(n2);
        if (cell == null || cell.source == null) {
            return null;
        }
        this.editor = findEditor(row, n2);
        if (this.editor == null) {
            return null;
        }
        final IBindFeature bindFeature = this.editor.getFeature(IBindFeature.class);
        StatefulContext[] array;
        for (int length = (array = bindFeature.getBoundContexts().toArray(new StatefulContext[0])).length, i = 0; i < length; ++i) {
            bindFeature.unbind(array[i]);
        }
        this.editorSource = cell.source;
        bindFeature.bind(this.editorContext = new StatefulContext(row.getContext(), this.editorSource.cloneTree()));
        final Object[] lastWidgets = this.editor.getFeature(IWidgetCreationFeature.class).getLastWidgets();
        if (lastWidgets.length == 0) {
            return null;
        }
        final JComponent component = (JComponent)lastWidgets[0];
        component.setBorder(null);
        if (component instanceof JComboBox) {
            ((JComboBox<?>)component).putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        }
        else if (component instanceof JTextComponent) {
            ((JTextComponent)component).selectAll();
        }
        else if (!(component instanceof JSlider)) {
            throw new UnsupportedOperationException("The specified editor widget is not supported.");
        }
        return component;
    }
    
    @Override
    public Object getCellEditorValue() {
        return this.editor.getFeature(ISingleValueWidgetFeature.class).getValue();
    }
    
    private void cleanupEditor() {
        if (this.editorContext != null) {
            this.editor.getFeature(IBindFeature.class).unbind(this.editorContext);
            this.editorContext = null;
            this.editorSource = null;
        }
    }
    
    public static IXidget findEditor(final Row row, final int n) {
        final IXidget table = row.getTable();
        final IModelObject modelObject = table.getConfig().getChildren("cell").get(n);
        for (final IXidget xidget : table.getChildren()) {
            final IModelObject config = xidget.getConfig();
            if (config.getParent().isType("cell") && modelObject == config.getParent()) {
                return xidget;
            }
        }
        return null;
    }
}
