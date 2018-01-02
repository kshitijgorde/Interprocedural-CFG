// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.fileexplorer;

import com.grooveshark.ui.foldertree.FileSelectionState;
import java.io.File;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

public class FileExplorerEditor extends AbstractCellEditor implements TableCellEditor
{
    private static final long serialVersionUID = 8037539488910376053L;
    private FileExplorerModel model;
    
    public FileExplorerEditor(final FileExplorerModel model) {
        this.model = model;
    }
    
    public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected, final int row, final int column) {
        final File file = (File)value;
        final FileSelectionState state = this.model.getFileState(file);
        final FileExplorerCell cell = new FileExplorerCell();
        cell.setFile(file, state, false, true);
        return cell;
    }
    
    public Object getCellEditorValue() {
        return null;
    }
}
