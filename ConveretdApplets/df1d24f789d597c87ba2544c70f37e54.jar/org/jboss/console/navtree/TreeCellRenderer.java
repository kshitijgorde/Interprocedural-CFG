// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.navtree;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.JTree;
import java.util.HashMap;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeCellRenderer extends DefaultTreeCellRenderer
{
    protected TreeContext ctx;
    protected static HashMap cache;
    
    public TreeCellRenderer(final TreeContext ctx) {
        this.ctx = null;
        this.ctx = ctx;
    }
    
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (value instanceof NodeWrapper) {
            final NodeWrapper node = (NodeWrapper)value;
            final String targetUrl = node.getIconUrl();
            ImageIcon img = TreeCellRenderer.cache.get(targetUrl);
            if (img != null) {
                this.setIcon(img);
            }
            else {
                URL target = null;
                try {
                    target = new URL(this.ctx.localizeUrl(targetUrl));
                }
                catch (Exception ex) {}
                if (target != null) {
                    try {
                        img = new ImageIcon(target);
                        TreeCellRenderer.cache.put(targetUrl, img);
                        this.setIcon(img);
                    }
                    catch (Exception ex2) {}
                }
            }
            final String desc = node.getDescription();
            if (desc != null) {
                this.setToolTipText(desc);
            }
        }
        return this;
    }
    
    public TreeCellRenderer() {
        this.ctx = null;
    }
    
    static {
        TreeCellRenderer.cache = new HashMap();
    }
}
