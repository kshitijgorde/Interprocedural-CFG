// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.fileexplorer;

import com.grooveshark.ui.foldertree.FileSelectionState;
import java.io.File;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class FileExplorerRenderer implements TableCellRenderer
{
    private FileExplorerModel model;
    private FileExplorerCell cell;
    
    public FileExplorerRenderer(final FileExplorerModel model, final FileExplorerCell cell) {
        this.model = model;
        this.cell = cell;
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final File file = (File)value;
        final FileSelectionState state = this.model.getFileState(file);
        this.cell.setFile(file, state, row % 2 == 0, isSelected);
        return this.cell;
    }
}
