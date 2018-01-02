// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.lf5.viewer.categoryexplorer;

import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.JTree;

public class CategoryNodeEditorRenderer extends CategoryNodeRenderer
{
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
        final Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        return c;
    }
    
    public JCheckBox getCheckBox() {
        return super._checkBox;
    }
}
