// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalTreeUI;

public class TinyTreeUI extends MetalTreeUI
{
    public static ComponentUI createUI(final JComponent component) {
        return new TinyTreeUI();
    }
    
    protected void installDefaults() {
        super.installDefaults();
        if (this.tree.getCellRenderer() instanceof DefaultTreeCellRenderer) {
            final DefaultTreeCellRenderer defaultTreeCellRenderer = (DefaultTreeCellRenderer)this.tree.getCellRenderer();
            defaultTreeCellRenderer.setBackgroundNonSelectionColor(Theme.treeTextBgColor[Theme.style].getColor());
            defaultTreeCellRenderer.setBackgroundSelectionColor(Theme.treeSelectedBgColor[Theme.style].getColor());
            defaultTreeCellRenderer.setTextNonSelectionColor(Theme.treeTextColor[Theme.style].getColor());
            defaultTreeCellRenderer.setTextSelectionColor(Theme.treeSelectedTextColor[Theme.style].getColor());
            final UIDefaults defaults = UIManager.getDefaults();
            defaultTreeCellRenderer.setClosedIcon(defaults.getIcon("Tree.closedIcon"));
            defaultTreeCellRenderer.setOpenIcon(defaults.getIcon("Tree.openIcon"));
            defaultTreeCellRenderer.setLeafIcon(defaults.getIcon("Tree.leafIcon"));
        }
    }
}
