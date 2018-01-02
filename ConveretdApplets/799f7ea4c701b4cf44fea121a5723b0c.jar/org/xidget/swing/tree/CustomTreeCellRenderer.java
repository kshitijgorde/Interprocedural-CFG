// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tree;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.xidget.tree.Cell;
import javax.swing.Icon;
import org.xidget.tree.Row;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer
{
    private StringBuilder sb;
    
    public CustomTreeCellRenderer() {
        this.sb = new StringBuilder();
    }
    
    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        final Component treeCellRendererComponent = super.getTreeCellRendererComponent(tree, o, b, b2, b3, n, b4);
        final Row row = (Row)o;
        if (row.getParent() == null) {
            this.setText("ROOT");
            return treeCellRendererComponent;
        }
        final Cell cell = row.getCell(0);
        this.setIcon((Icon)cell.icon);
        this.sb.setLength(0);
        final List<Cell> cells = row.getCells();
        for (final Cell cell2 : cells) {
            if (cell2 != cell) {
                this.sb.append(", ");
            }
            this.sb.append((cell2.text != null) ? cell2.text : "");
        }
        for (int i = cells.size() - 1; i > 0; --i) {
            final Cell cell3 = cells.get(i);
            if (cell3.text != null && cell3.text.length() > 0) {
                break;
            }
            this.sb.setLength(this.sb.length() - 2);
        }
        this.setText(this.sb.toString());
        return treeCellRendererComponent;
    }
}
